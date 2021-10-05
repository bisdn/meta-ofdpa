DESCRIPTION = ""
LICENSE = "CLOSED"

# Include SDK version, and OF-DPA and OpenBCM source revisions in version
PV = "3.0.5.0-EA5+sdk-${SDK_VERSION}+gitAUTOINC+${@'${SRCREV_ofdpa}'[:10]}_${@'${SRCREV_sdk}'[:10]}"

PR = "r4"
SDK_VERSION = "6.5.22"
SRCREV_ofdpa = "ce97ed9907a369c08774f724587f73c2f6039ab1"
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

SHA256SUMS[accton-as4610.ofagent] = "2b3561b1e24a9b694ce186cd52d5338af0b6cd95b2ab4cfe57cf0abead936037"
SHA256SUMS[accton-as4610.ofdpa] = "73a8203cb9572835cc24c96faf8b62f05c8ce215495e0134e5cb371d4ae6c401"
SHA256SUMS[accton-as4610.ofdpa-firmware-bcm56770] = "9aa1a6ee0e5a279ebf959b450cdb8850b7c100ef35ef459b825a07437adcf035"
SHA256SUMS[accton-as4610.ofdpa-firmware-bcm56870] = "0dd5043d27398cb3c2b1e0bad174f506a8ee44b34a2be65fc71be49929e6230b"
SHA256SUMS[accton-as4610.python3-ofdpa] = "e921cc68a10b05d6c1197f221e013b1eaccfcff356913dc10ba9c3f83e98ea90"

SHA256SUMS[accton-as7726-32x.ofagent] = "0e458fe7ee1b84fdcc309262ffca7a72a957c038cdf8f0ee8012d12f96dbe999"
SHA256SUMS[accton-as7726-32x.ofdpa] = "a56ada3dea380c7a6943556bcc4755d6e4959a33e86be7d46d10de6d30f1f031"
SHA256SUMS[accton-as7726-32x.ofdpa-firmware-bcm56770] = "f3f813614d7afdea1dcf2ecc8fff7dffe86a4a69e0887d8b51c34816f00016bd"
SHA256SUMS[accton-as7726-32x.ofdpa-firmware-bcm56870] = "e17db0cff5978f2ac69ec657816ce6f64ce16d1c5d2383c712689d6f5e5dbf2f"
SHA256SUMS[accton-as7726-32x.python3-ofdpa] = "63393d141c8261d0be01db33ea17811a258bf4f3da66cce1096678da69d7e1e8"

SHA256SUMS[agema-ag5648.ofagent] = "95181afee4e5a7cfa7a2b546bf2bf80280ec8647bfb63f6ceab777b4229966c8"
SHA256SUMS[agema-ag5648.ofdpa] = "b03a24fb88181c4ae43d7a514b65b83db7b99359329b4f22cfe212484c17dcbd"
SHA256SUMS[agema-ag5648.ofdpa-firmware-bcm56770] = "913c81877d9a3d673d58cc698a99517cef3e9525ea447cf1a22219ed0077f5c4"
SHA256SUMS[agema-ag5648.ofdpa-firmware-bcm56870] = "92f5ebddfc92584f418d64a65e28bb1a570ffce84cb5aa1c3bc417c594f79c15"
SHA256SUMS[agema-ag5648.python3-ofdpa] = "51b1bf54e67894493cd6ecfc6410c51cd7ca3ef2b20eaf053489083e7fb3ec88"

SHA256SUMS[agema-ag7648.ofagent] = "79abfdda660552dcaf17ad08db84e36904415370fd453dabe6b3b1f444493b68"
SHA256SUMS[agema-ag7648.ofdpa] = "8c2511fa8ec8214ea9adeb9daca409e37384443823c9e42899d47ce28a785b8c"
SHA256SUMS[agema-ag7648.ofdpa-firmware-bcm56770] = "7807f9446d42007dbc1cb21f20364b9480222c288ac51297fc5ac5a1f901d029"
SHA256SUMS[agema-ag7648.ofdpa-firmware-bcm56870] = "7f669d487954229ff2f24574a32dc61610aea686d1d65608281601dfa162cf93"
SHA256SUMS[agema-ag7648.python3-ofdpa] = "e43917f8945e1956645062ad94d552030d52307e54fa44d825b6864237b2c48b"

SHA256SUMS[cel-questone-2a.ofagent] = "c24947f9469eeca7d8ca87e4e736089d23df196f5ef7ed917486641936b49fd3"
SHA256SUMS[cel-questone-2a.ofdpa] = "8cee672dea0403ff5d4ba0821a5496f30b8309ea9307b46ad2b80ca7cb0ed8d3"
SHA256SUMS[cel-questone-2a.ofdpa-firmware-bcm56770] = "bb73837858faa9e568637afab3d5092377bd40449fd59431656c0bbe11a00fa5"
SHA256SUMS[cel-questone-2a.ofdpa-firmware-bcm56870] = "9e07b7d9e85a6e929985cf37d825e5b4d5a8f943e769d3fa8142f2dbbafabfcc"
SHA256SUMS[cel-questone-2a.python3-ofdpa] = "e52d2b975803904042f72f62aaf6f99259631c8a64f736aea3b58d341b84c504"

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
