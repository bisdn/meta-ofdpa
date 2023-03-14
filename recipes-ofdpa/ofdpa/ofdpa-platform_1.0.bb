SUMMARY = "OF-DPA platform configuration"
DESCRIPTION = "Switch ASIC platform configuration files for OF-DPA (Broadcom SDK)"

LICENSE = "Broadcom-OpenBCM & Apache-2.0"
NO_GENERIC_LICENSE[Broadcom-OpenBCM] = "${WORKDIR}/LICENSE.Broadcom-OpenBCM"
LIC_FILES_CHKSUM = "\
    file://${WORKDIR}/LICENSE.Broadcom-OpenBCM;md5=1513e460208bceb2722d7e38e260aa44 \
    file://${WORKDIR}/git/LICENSE;md5=31b8e124402d908f7a5fd17902e7d4e7 \
"

# head of master as of 2023-9-20
SRCREV = "382d68fe42ea2fd8aaf02db1a9c94c8932abce47"

SRC_URI = "\
    git://github.com/sonic-net/sonic-buildimage.git;protocol=https;branch=master \
    file://led \
    file://platform \
    file://LICENSE.Broadcom-OpenBCM \
"

S = "${WORKDIR}/git"

inherit allarch

SONIC_CUSTOM_LEDS = "\
"

FILES:${PN} = " \
    ${datadir}/ofdpa/led \
    ${datadir}/ofdpa/platform \
"

do_configure() {
        /usr/bin/true
}

do_compile() {
        /usr/bin/true
}

do_install() {
        install -d -m0755 ${D}${datadir}/ofdpa/led
        install -m 0644 ${WORKDIR}/led/*.json ${D}${datadir}/ofdpa/led
        install -d -m0755 ${D}${datadir}/ofdpa/platform
        cp -R ${WORKDIR}/platform/* ${D}${datadir}/ofdpa/platform

        for sonic_custom_led in ${SONIC_CUSTOM_LEDS}; do
             onl_platform="$(basename ${sonic_custom_led} | tr '_' '-')"
             install -m 0644 ${S}/device/${sonic_custom_led}/custom_led.bin \
                 ${D}${datadir}/ofdpa/platform/${onl_platform}/
        done
}

