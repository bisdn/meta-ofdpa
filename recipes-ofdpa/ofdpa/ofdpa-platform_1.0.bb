SUMMARY = "OF-DPA platform configuration"
DESCRIPTION = "Switch ASIC platform configuration files for OF-DPA (Broadcom SDK)"

LICENSE = "Broadcom-OpenBCM & Apache-2.0"
NO_GENERIC_LICENSE[Broadcom-OpenBCM] = "LICENSE.Broadcom-OpenBCM"
LIC_FILES_CHKSUM = "\
    file://LICENSE.Broadcom-OpenBCM;md5=1513e460208bceb2722d7e38e260aa44 \
    file://LICENSE;md5=31b8e124402d908f7a5fd17902e7d4e7 \
"

# head of master as of 2023-9-20
SRCREV = "3e1af2a30c3d29df6b892b4e7109a174d29cc57c"

SRC_URI = "\
    git://github.com/sonic-net/sonic-buildimage.git;protocol=https;branch=master \
    file://led \
    file://platform \
    file://LICENSE.Broadcom-OpenBCM;subdir=git/ \
"

S = "${WORKDIR}/git"
B = "${WORKDIR}/build"

inherit allarch

FILES:${PN} = " \
    ${datadir}/ofdpa/led \
    ${datadir}/ofdpa/platform \
"

SONIC_PLATFORM_OVERRIDES = "\
    celestica/x86_64-cel_questone_2-r0/Questone_2A=celestica/x86_64-cel_questone_2a-r0 \
"

do_patch:append() {
    bb.build.exec_func('do_copy_subplatforms', d)
}

do_copy_subplatforms() {
        for override in ${SONIC_PLATFORM_OVERRIDES}; do
            source=${override%=*}
            target=${override#*=}
            base=$(dirname ${S}/device/${source})

            cp -fr ${S}/device/${source} ${S}/device/${target}
            for file in led_proc_init.soc platform_asic; do
                echo "${base}/${file} => ${S}/device/${target}/${file}"
                if [ -e "${base}/${file}" ] && [ ! -e "${S}/device/${target}/${file}" ]; then
                    cp ${base}/${file} ${S}/device/${target}
                fi
            done
        done
}

do_configure() {
        /usr/bin/true
}

do_compile() {
        rm -rf ${B}
        mkdir -p ${B}

        for vendor in ${S}/device/*; do
            for device in ${vendor}/*; do
                # our OF-DPA expects ONL platform names
                onl_platform="$(basename ${device} | tr '_' '-')"
                [ -e "${device}/platform_asic" ] || continue

                # we only support broadcom devices
                platform_asic=$(cat ${device}/platform_asic)
                [ "${platform_asic}" = "broadcom" ] || continue

                if [ -f "${device}/default_sku" ]; then
                    default_sku=$(cut -d' ' -f1 ${device}/default_sku)
                    sai_profile="${device}/${default_sku}/sai.profile"
                    paths="${device}/${default_sku} ${device}"
                else
                    sai_profile=$(find "${device}" -name 'sai.profile' | sort | head -n 1)
                    paths=${device}
                fi

                if [ -e "$sai_profile" ]; then
                    # sai_profile tells us the config to use
                    if ! grep -q 'SAI_INIT_CONFIG_FILE' ${sai_profile}; then
                        continue
                    fi
                    bcm_config=$(sed -n 's/SAI_INIT_CONFIG_FILE=//p' ${sai_profile})
                    bcm_config=${bcm_config#/usr/share/sonic/hwsku/}
                fi

                if [ -f "${device}/${bcm_config}" ]; then
                    bcm_config=${device}/${bcm_config}
                else
                    # devices may have more than one config, copy the first only for now
                    bcm_config=$(find ${device} -name $(basename ${bcm_config}) | sort | head -n 1)
                fi

                # some platforms are missing the config
                [ -n "${bcm_config}" ] || continue

                # we currently do not support .yaml configuration
                case "${bcm_config}" in
                    *.bcm)
                        ;;
                    *)
                        continue
                        ;;
                esac

                # there is also a preinit_cmd_file, but it only duplicates
                # functionality we already do for TD3

                postinit=$(sed -n 's/^sai_postinit_cmd_file=//p' ${sai_profile})
                postinit=${postinit#/usr/share/sonic/hwsku/}
                postinit=${postinit#/usr/share/sonic/platform/}

                mkdir -p "${B}/${onl_platform}"
                cp "${bcm_config}" ${B}/${onl_platform}/config.bcm

                rm -f ${B}/${onl_platform}/rc.soc

                for file in ${postinit} led_proc_init.soc; do
                    for path in ${paths}; do
                        [ -f "${path}/${file}" ] || continue
                        cp "${path}/${file}" "${B}/${onl_platform}"

                        #fixup paths
                        sed -i -e "s|/usr/share/sonic/platform|${datadir}/ofdpa/platform/${onl_platform}|" \
                            ${B}/${onl_platform}/${file}
                        sed -i -e "s|/usr/share/sonic/hwsku|${datadir}/ofdpa/platform/${onl_platform}|" \
                            ${B}/${onl_platform}/${file}

                        # copy referenced files
                        loads=$(grep -i '^m0 load\|^rcload\|^led load\|^led . load' ${B}/${onl_platform}/${file} | awk '{print $NF}')
                        for extra_file in ${loads}; do
                            # commands may have an extra ;
                            relpath=${extra_file%;}
                            # convert to relative path
                            relpath=${relpath#${datadir}/ofdpa/platform/${onl_platform}/}
                            subdir=$(dirname ${relpath})
                            for extra_path in ${paths}; do
                                echo "f=${extra_path}/${relpath}"
                                [ -f "${extra_path}/${relpath}" ] || continue

                                mkdir -p "${B}/${onl_platform}/${subdir}"
                                cp "${extra_path}/${relpath}" "${B}/${onl_platform}/${subdir}"
                            done
                        done

                        echo "rcload ${datadir}/ofdpa/platform/${onl_platform}/${file}" >> ${B}/${onl_platform}/rc.soc
                        break
                    done
                done
                # make sure there is at least one command
                echo "exit 0" >> ${B}/${onl_platform}/rc.soc
            done
        done

        for device in ${WORKDIR}/platform/*; do
            cp -r "${device}" "${B}"
        done
}

do_install() {
        install -d -m0755 ${D}${datadir}/ofdpa/led
        install -m 0644 ${WORKDIR}/led/*.json ${D}${datadir}/ofdpa/led
        install -d -m0755 ${D}${datadir}/ofdpa/platform

        for device in ${B}/*; do
            cp -r "${device}" "${D}${datadir}/ofdpa/platform"
        done
}
