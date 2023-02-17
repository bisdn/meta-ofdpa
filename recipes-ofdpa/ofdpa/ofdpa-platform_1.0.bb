SUMMARY = "OF-DPA platform configuration"
DESCRIPTION = "Switch ASIC platform configuration files for OF-DPA (Broadcom SDK)"

LICENSE = "Broadcom-OpenBCM"
NO_GENERIC_LICENSE[Broadcom-OpenBCM] = "${WORKDIR}/LICENSE.Broadcom-OpenBCM"
LIC_FILES_CHKSUM = "file://${WORKDIR}/LICENSE.Broadcom-OpenBCM;md5=1513e460208bceb2722d7e38e260aa44"

SRC_URI = "\
    file://led \
    file://platform \
    file://LICENSE.Broadcom-OpenBCM \
"

S = "${WORKDIR}"

inherit allarch

FILES:${PN} = " \
    ${datadir}/ofdpa/led \
    ${datadir}/ofdpa/platform \
"

do_install() {
        install -d -m0755 ${D}${datadir}/ofdpa/led
        install -m 0644 ${WORKDIR}/led/*.json ${D}${datadir}/ofdpa/led
        install -d -m0755 ${D}${datadir}/ofdpa/platform
        cp -R ${WORKDIR}/platform/* ${D}${datadir}/ofdpa/platform
}

