DESCRIPTION = ""
LICENSE = "CLOSED"

PR = "r40"
SDK_VERSION = "6.5.24"
SRCREV_ofdpa = "55b00d96f9891ec7ebbf510e6bc3bdb932bc242a"
SRCREV_sdk = "0b149ddfa3878e65eb217a11dddb999d3e205d03"

inherit systemd python3-dir

include ofdpa.inc

DEPENDS += "python3 onl"

RDEPENDS:${PN} += "libgcc udev openbcm-gpl-modules ofdpa-platform"

RDEPENDS:${PN}:append:x86-64 = " \
             ${PN}-firmware-bcm56370 \
             ${PN}-firmware-bcm56770 \
             ${PN}-firmware-bcm56870 \
"

SYSTEMD_PACKAGES = "${PN} ofagent"
SYSTEMD_SERVICE:${PN} = "ofdpa.service"
SYSTEMD_SERVICE:ofagent = "ofagent.service"
SYSTEMD_AUTO_ENABLE = "enable"

INSANE_SKIP:python3-${PN} = "ldflags"

PACKAGES =+ "\
             ofagent \
             python3-${PN} \
             ${PN}-firmware-bcm56370 \
             ${PN}-firmware-bcm56770 \
             ${PN}-firmware-bcm56870 \
             "

FILES:${PN} += "\
            ${sbindir}/ofdpa \
            ${sysconfdir}/default/ofdpa \
            ${sysconfdir}/default/ofdpa_log.cfg \
            ${sysconfdir}/ofdpa \
            ${systemd_unitdir}/system/ofdpa.service \
            ${sbindir}/client* \
            ${libdir}/librpc_client*${SOLIBS} \
            ${datadir}/ofdpa/rc.soc \
            "

FILES:ofagent = "${sbindir}/ofagent \
                 ${sysconfdir}/default/ofagent \
                 ${systemd_unitdir}/system/ofagent.service"

FILES:python3-${PN} = " \
                      ${PYTHON_SITEPACKAGES_DIR} \
                      ${sbindir}/ofdpa*.py \
                      "

FILES:${PN}-firmware-bcm56370 = " \
            ${nonarch_base_libdir}/firmware/brcm/bcm56370*.pkg \
            "

FILES:${PN}-firmware-bcm56770 = " \
            ${nonarch_base_libdir}/firmware/brcm/bcm56770*.pkg \
            "

FILES:${PN}-firmware-bcm56870 = " \
            ${nonarch_base_libdir}/firmware/brcm/bcm56870*.pkg \
            "

CONFFILES:${PN} = " \
  ${sysconfdir}/default/ofdpa \
  ${sysconfdir}/default/ofdpa_log.cfg \
  ${sysconfdir}/ofdpa/rc.soc \
"

CONFFILES:ofagent = "${sysconfdir}/default/ofagent"
