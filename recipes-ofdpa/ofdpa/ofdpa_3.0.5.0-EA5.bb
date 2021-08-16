DESCRIPTION = ""
LICENSE = "CLOSED"

# Include SDK version, and OF-DPA and OpenBCM source revisions in version
PV = "3.0.5.0-EA5+sdk-${SDK_VERSION}+gitAUTOINC+${@'${SRCREV_ofdpa}'[:10]}_${@'${SRCREV_sdk}'[:10]}"

PR = "r3"
SDK_VERSION = "6.5.22"
SRCREV_ofdpa = "8a370b491aa5731de3287805879614569f06085a"
SRCREV_sdk = "f01ceb9cf4238b762cc4422e7ebe1c38a113464e"

DEPENDS = "python3 onl"
RDEPENDS_${PN} += "libgcc udev openbcm-gpl-modules"

RDEPENDS_${PN} += " ${@bb.utils.contains('OFDPA_SWITCH_SUPPORT', 'BCM56770', '${PN}-firmware-bcm56770', '', d)}"

# Nightly packages are regenerated regularily
BB_STRICT_CHECKSUM = "1"

SRC_URI = " \
 http://repo.bisdn.de/pub/onie/${MACHINE}/packages-v${DISTRO_VERSION}/ipk/${MACHINE_ARCH}/ofagent_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P};sha256sum=${@d.getVarFlag('SHA256SUMS', '${MACHINE}.ofagent', 1)} \
 http://repo.bisdn.de/pub/onie/${MACHINE}/packages-v${DISTRO_VERSION}/ipk/${MACHINE_ARCH}/ofdpa_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P};sha256sum=${@d.getVarFlag('SHA256SUMS', '${MACHINE}.ofdpa', 1)} \
 http://repo.bisdn.de/pub/onie/${MACHINE}/packages-v${DISTRO_VERSION}/ipk/${MACHINE_ARCH}/ofdpa-firmware-bcm56770_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P};sha256sum=${@d.getVarFlag('SHA256SUMS', '${MACHINE}.ofdpa-firmware-bcm56770', 1)} \
 http://repo.bisdn.de/pub/onie/${MACHINE}/packages-v${DISTRO_VERSION}/ipk/${MACHINE_ARCH}/python3-ofdpa_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P};sha256sum=${@d.getVarFlag('SHA256SUMS', '${MACHINE}.python3-ofdpa', 1)} \
"

SHA256SUMS[accton-as4610.ofagent] = "d7f8c976365c12b0821d658725edb87c39c09294d705fdadc482c2d7b981e86d"
SHA256SUMS[accton-as4610.ofdpa] = "1a03b575eda3ec88cd0a9fe313c7b135c74716ee5b931fa8f80d036447b0fe07"
SHA256SUMS[accton-as4610.ofdpa-firmware-bcm56770] = "ff31d4865188a05b02a3e5a51f413efe242d553469d830903e81b72cfb3c0099"
SHA256SUMS[accton-as4610.python3-ofdpa] = "8347ff7426455d42783cf48ffe2d14c7725d8ba59ab4ede26d17d87d777c723c"

SHA256SUMS[agema-ag5648.ofagent] = "b4a21f469589aa7d6b9ec6d774f0331449155c61411e6937c25bba3288113f1c"
SHA256SUMS[agema-ag5648.ofdpa] = "9e456e155d05d295e0e29d53ee401d126dd781304f77e95be3ece4c0ccc38dd4"
SHA256SUMS[agema-ag5648.ofdpa-firmware-bcm56770] = "382421f9c2cb78f34feb8cfae17fbac6d629fd24e0f413dceeed77d81ca00978"
SHA256SUMS[agema-ag5648.python3-ofdpa] = "459f931f959d6716c2f91fc9c2122a6acd573b66dc5521c3887f371ea5e6d95a"

SHA256SUMS[agema-ag7648.ofagent] = "9b80832d73074724ae3c25594c5021ffd8d27c23dccb9818cfec7f02634bcb3b"
SHA256SUMS[agema-ag7648.ofdpa] = "3c8ab34b767c45378e890e34b5e7e6cae8e9b8a52f0961f692a585922cd214f1"
SHA256SUMS[agema-ag7648.ofdpa-firmware-bcm56770] = "929cdfe3e10a5c1913cf8b33f7b82be26d31b944dfe8433da066dcb2951272ae"
SHA256SUMS[agema-ag7648.python3-ofdpa] = "765700b380e353026cc18eed165f90e67dd2d5544355c28f8115418effe9d9e4"

SHA256SUMS[cel-questone-2a.ofagent] = "5d43be3b01a2ef21eda2c81360b07e5f41a95a95e3c6919ac3b70997bfb71b0c"
SHA256SUMS[cel-questone-2a.ofdpa] = "b5d1bf144f82e3129deac2c6059c84e988dc6db59d6aa67da7ed7e8f54c6db62"
SHA256SUMS[cel-questone-2a.ofdpa-firmware-bcm56770] = "301ade3b49019a68fc8d05f69ebe4091a5c819167e6f4446d6c8c49b422239f7"
SHA256SUMS[cel-questone-2a.python3-ofdpa] = "68b7c210bc29ed8522f87834ee8ea61cac57d11f1be2c869d1c3e5b7c54d65e9"

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

PACKAGES =+ "ofagent python3-${PN} ${PN}-firmware-bcm56770"

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

CONFFILES_${PN} = " \
  ${sysconfdir}/default/ofdpa \
  ${sysconfdir}/ofdpa/rc.soc \
"

CONFFILES_ofagent = "${sysconfdir}/default/ofagent"
