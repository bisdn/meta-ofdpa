DESCRIPTION = ""
LICENSE = "CLOSED"

# Include SDK version, and OF-DPA and OpenBCM source revisions in version
PV = "3.0.5.0-EA5+sdk-${SDK_VERSION}+gitAUTOINC+${@'${SRCREV_ofdpa}'[:10]}_${@'${SRCREV_sdk}'[:10]}"

PR = "r6"
SDK_VERSION = "6.5.22"
SRCREV_ofdpa = "a1b1e04908432e7ec42827febcd91ab9f9519127"
SRCREV_sdk = "f01ceb9cf4238b762cc4422e7ebe1c38a113464e"

DEPENDS = "python3 onl"
RDEPENDS_${PN} += "libgcc udev openbcm-gpl-modules"

RDEPENDS_${PN} += " ${@bb.utils.contains('OFDPA_SWITCH_SUPPORT', 'BCM56770', '${PN}-firmware-bcm56770', '', d)}"

SRC_URI = " \
 ${FEEDDOMAIN}/${FEEDURIPREFIX}/ipk/${MACHINE_ARCH}/ofagent_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P};sha256sum=${@d.getVarFlag('SHA256SUMS', '${MACHINE}.ofagent', 1)} \
 ${FEEDDOMAIN}/${FEEDURIPREFIX}/ipk/${MACHINE_ARCH}/ofdpa_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P};sha256sum=${@d.getVarFlag('SHA256SUMS', '${MACHINE}.ofdpa', 1)} \
 ${FEEDDOMAIN}/${FEEDURIPREFIX}/ipk/${MACHINE_ARCH}/ofdpa-firmware-bcm56770_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P};sha256sum=${@d.getVarFlag('SHA256SUMS', '${MACHINE}.ofdpa-firmware-bcm56770', 1)} \
 ${FEEDDOMAIN}/${FEEDURIPREFIX}/ipk/${MACHINE_ARCH}/ofdpa-firmware-bcm56870_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P};sha256sum=${@d.getVarFlag('SHA256SUMS', '${MACHINE}.ofdpa-firmware-bcm56870', 1)} \
 ${FEEDDOMAIN}/${FEEDURIPREFIX}/ipk/${MACHINE_ARCH}/python3-ofdpa_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P};sha256sum=${@d.getVarFlag('SHA256SUMS', '${MACHINE}.python3-ofdpa', 1)} \
"

SHA256SUMS[accton-as4610.ofagent] = "8820fb5e631cf5666aa1f9ae7ccc252e3d7bec3299e6668dc0938f063cf48be5"
SHA256SUMS[accton-as4610.ofdpa] = "5ab7a0887eaf15241a4b7877e6cfc99b09fe8ad332a5c511fe0ef41066bd875e"
SHA256SUMS[accton-as4610.ofdpa-firmware-bcm56770] = "07f1d10f6adf5b32f302ff73ae807b30e7a49a858d2e28672d6eaa0fa8a9a6ff"
SHA256SUMS[accton-as4610.ofdpa-firmware-bcm56870] = "162b9c1e52c90284a49e1d7565d6d04c07593f0ff284b893ac6cb7a096e0fe40"
SHA256SUMS[accton-as4610.python3-ofdpa] = "7b964936fce7275c04ab0f0e46339c6ae773e45021c97b35c2ce31f0f89d997e"

SHA256SUMS[accton-as7726-32x.ofagent] = "997fd26b91d0e91b0709eca77f05f2c2e6ceab8e2fe88836866438d8f5a7aee9"
SHA256SUMS[accton-as7726-32x.ofdpa] = "ba43f451a2879cb67c0b498b3220aa852cf36dd0bd72698fbe9b8a810b24b5a4"
SHA256SUMS[accton-as7726-32x.ofdpa-firmware-bcm56770] = "3985181a7efb4e92b7100e7e5479de261a893c9136128b9eca7ea0aa251b7c38"
SHA256SUMS[accton-as7726-32x.ofdpa-firmware-bcm56870] = "37fd6e3efa3d7c271946f982892788664401289bd1cc821ed934ddb43b913701"
SHA256SUMS[accton-as7726-32x.python3-ofdpa] = "636af6f3a4011aeeb61a242349a988d85d1b79c9e43588dee25c7dccf73ba961"

SHA256SUMS[agema-ag5648.ofagent] = "559541c79370497a25aa98077211839f4ef706035f83a411fa1313a70335fb42"
SHA256SUMS[agema-ag5648.ofdpa] = "088eb0a6fcf727697d17d54b5ca46ee19c9df2294a4682382bbc373deee9aca9"
SHA256SUMS[agema-ag5648.ofdpa-firmware-bcm56770] = "3056b8f6dfad0eb2023ae7138dfeb6d5eb092c1197dfced45bac734d89f286c2"
SHA256SUMS[agema-ag5648.ofdpa-firmware-bcm56870] = "53e59cca24aa7a224858d372843350d737e60be8146ce65835b6fbea680d88a6"
SHA256SUMS[agema-ag5648.python3-ofdpa] = "6068c2911d75a078c23ea6c735e9753aa66140394d1a56918f4dc9ba48704614"

SHA256SUMS[agema-ag7648.ofagent] = "a52e3e025ba85d759baf3a48123666146d273cab803abd7411d5b467baff1eb7"
SHA256SUMS[agema-ag7648.ofdpa] = "2930f5aae1b07249a1eb6b33ea932f6b60a389a35a8a31b861c212107abb7f29"
SHA256SUMS[agema-ag7648.ofdpa-firmware-bcm56770] = "01eea76504ef9f3fcf1b000d2a0ba35c0adc0da6d5719de2c6a2cab3be2a1574"
SHA256SUMS[agema-ag7648.ofdpa-firmware-bcm56870] = "bd594e1015bc6c5ef06cb67e577a0943a1aa8ff945bddc36fc890f84163b5471"
SHA256SUMS[agema-ag7648.python3-ofdpa] = "1b0851bd517c7a9aefd985396886cb4ce2588f1d9fe98eb9e5c2d9dd31b518a6"

SHA256SUMS[cel-questone-2a.ofagent] = "d79357768eaf085d569a3e1d4204de1960dad84a993d9949660176cdfc47956f"
SHA256SUMS[cel-questone-2a.ofdpa] = "d5c9bd56440ff51c08b791fc79340e8f8c7010294feadd5109b331d1daffff00"
SHA256SUMS[cel-questone-2a.ofdpa-firmware-bcm56770] = "c7b6bde055d7a4714180c73cd095b8cb88ce4fa669f90558cfda2d0e424bbeb2"
SHA256SUMS[cel-questone-2a.ofdpa-firmware-bcm56870] = "bb994019c0a52a653ca01a6684e13de5875769a772bd029b9edbee4f77fa6418"
SHA256SUMS[cel-questone-2a.python3-ofdpa] = "d9bc02f27d9e2b5a5cb31f6cdc47b4e1c450e1954a1edbd96a47bc550604a70a"

inherit bin_package systemd python3-dir

# for some reason ipk doesn't trigger xz-native as a dependency
do_unpack[depends] += " xz-native:do_populate_sysroot"

# fails because no license file in the ipk
deltask do_populate_lic

SYSTEMD_PACKAGES = "${PN} ofagent"
SYSTEMD_SERVICE_${PN} = "ofdpa.service"
SYSTEMD_SERVICE_ofagent = "ofagent.service"
SYSTEMD_AUTO_ENABLE = "enable"

# systemd.class will append to existing presets, so remove them to avoid
# duplicating their contents.
do_install_append() {
	rm -f ${D}/lib/systemd/system-preset/*
}

INSANE_SKIP_${PN} = "ldflags"
INSANE_SKIP_ofagent = "ldflags"
INSANE_SKIP_python3-${PN} = "ldflags"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# this is machine specific
PACKAGE_ARCH = "${MACHINE_ARCH}"

PACKAGES =+ "ofagent python3-${PN} ${PN}-firmware-bcm56770 ${PN}-firmware-bcm56870"

FILES_${PN} += "\
            ${sbindir}/ofdpa \
            ${sysconfdir}/default/ofdpa \
            ${sysconfdir}/ofdpa \
            ${systemd_unitdir}/system/ofdpa.service \
            ${sbindir}/client* \
            ${libdir}/librpc_client*${SOLIBS} \
            "

FILES_ofagent = "${sbindir}/ofagent \
                 ${sysconfdir}/default/ofagent \
                 ${systemd_unitdir}/system/ofagent.service"

FILES_python3-${PN} = " \
                      ${PYTHON_SITEPACKAGES_DIR} \
                      ${sbindir}/ofdpa*.py \
                      "
FILES_${PN}-firmware-bcm56770 = " \
           ${nonarch_base_libdir}/firmware/brcm/bcm56770*.pkg \
           "

FILES_${PN}-firmware-bcm56870 = " \
           ${nonarch_base_libdir}/firmware/brcm/bcm56870*.pkg \
           "

CONFFILES_${PN} = " \
  ${sysconfdir}/default/ofdpa \
  ${sysconfdir}/ofdpa/rc.soc \
"

CONFFILES_ofagent = "${sysconfdir}/default/ofagent"
