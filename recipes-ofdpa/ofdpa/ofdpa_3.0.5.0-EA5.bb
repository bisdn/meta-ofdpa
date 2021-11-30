DESCRIPTION = ""
LICENSE = "CLOSED"

# Include SDK version, and OF-DPA and OpenBCM source revisions in version
PV = "3.0.5.0-EA5+sdk-${SDK_VERSION}+gitAUTOINC+${@'${SRCREV_ofdpa}'[:10]}_${@'${SRCREV_sdk}'[:10]}"

PR = "r6.1"
SDK_VERSION = "6.5.22"
SRCREV_ofdpa = "c2d7f9f3af8730338d4e7b8936e27991f94778a4"
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

SHA256SUMS[accton-as4610.ofagent] = "d58b1457b601457668c8056f0c8f6fb06718f0a990f4451c586f294be92f8217"
SHA256SUMS[accton-as4610.ofdpa] = "2cda90546e3082b42baca5fc1a39b28cd616f09ebe2a2d0fdbcb7e0456cba08d"
SHA256SUMS[accton-as4610.ofdpa-firmware-bcm56770] = "6028a057e34e2b2f5cd6e32c6b5cbf03e7f5126dbd62f421a24eda115d4a5eb3"
SHA256SUMS[accton-as4610.ofdpa-firmware-bcm56870] = "b8d762c706ef0085d129db61d5bfad4288c98afb6c8d827387fadae2de28586f"
SHA256SUMS[accton-as4610.python3-ofdpa] = "d7342f3adf563ca98753e1c5661e2a222a16afb5abf3e3299f9884a3557a9d1c"

SHA256SUMS[accton-as7726-32x.ofagent] = "d8e6436f7e2e2e9195b50a2234c1af78281ab64285d219208a75da60891aaed5"
SHA256SUMS[accton-as7726-32x.ofdpa] = "d131adfa8ed8264d3f122639b40afcb38a6b08e3b979adc86c66beaf97526319"
SHA256SUMS[accton-as7726-32x.ofdpa-firmware-bcm56770] = "4f0b35be10dffc94d2ad4d96f0e9a3c229544a8ac8480e7d0f3800ccde798715"
SHA256SUMS[accton-as7726-32x.ofdpa-firmware-bcm56870] = "351ffd1379afacdbe0a3ad71da3951637105bf98876bde0d36361183809bae55"
SHA256SUMS[accton-as7726-32x.python3-ofdpa] = "fdca87b5c412f25598343c8e9a711d156a323bac586c768b6325fddacc1f664a"

SHA256SUMS[agema-ag5648.ofagent] = "e08f2ecf635519241a7f26fa8caca59fb3977c3157000270bc1357737e28cff9"
SHA256SUMS[agema-ag5648.ofdpa] = "629fe37a0c851964273b1293c467ece81f6a2fc6f87b96732cac0b5cea069bf4"
SHA256SUMS[agema-ag5648.ofdpa-firmware-bcm56770] = "b42cbd52aca42c965d591f83b1e7eb87954eef556a9e17b585b9cf993eb791fd"
SHA256SUMS[agema-ag5648.ofdpa-firmware-bcm56870] = "126c20612093091c404f8b2fc45101a09f78a5f8bbb33d9d219edf6e2fbb6e08"
SHA256SUMS[agema-ag5648.python3-ofdpa] = "85fd146e59a535b4c3e485e0e1bf6675a2e3b541190860c1520b2c7e922933e7"

SHA256SUMS[agema-ag7648.ofagent] = "9af264abe82ce4b78cb6b5222cf0961e44e4461ae6178ba4b33d80d6fd1d5ea6"
SHA256SUMS[agema-ag7648.ofdpa] = "3c5637dc62ebc402c1debbe0e853c62ae7d1a5d11aec92a8634d3eb75b2a25ec"
SHA256SUMS[agema-ag7648.ofdpa-firmware-bcm56770] = "6db1568279edad292d1f9a51bda5a5097ca6c2468f200156e11d74de105d0099"
SHA256SUMS[agema-ag7648.ofdpa-firmware-bcm56870] = "f54f5c08e6845062d71a79d5212a50a9814304c1f4ef13549331a692ae19fb85"
SHA256SUMS[agema-ag7648.python3-ofdpa] = "a755df6e985e31946b54b470e82c4322e880349908a3e354b25fc9c359aa36d4"

SHA256SUMS[cel-questone-2a.ofagent] = "07d5cc18c9f3739df9aa65db3086deebd25e9b665b7a2f4ba1c63adfc83a92d7"
SHA256SUMS[cel-questone-2a.ofdpa] = "50fefc041633af428fa6ddfe4020b912df6bc4422bb2006f9f307621da4bd6a7"
SHA256SUMS[cel-questone-2a.ofdpa-firmware-bcm56770] = "6ae4cdef8fc1e593c7e33b4948bff19f6ba58fcefce29605dfc0eb441425c80a"
SHA256SUMS[cel-questone-2a.ofdpa-firmware-bcm56870] = "6de5912c8720e0c458079dcba85b0a3cef9f65e28e6e1bc952e62d9bfeb13dd7"
SHA256SUMS[cel-questone-2a.python3-ofdpa] = "adb7ff39a70302463d4e917a365e731157ad8398150a098c37d8a798576a20ad"

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
