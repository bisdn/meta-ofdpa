LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = " \
	file://sdk-6.5.24/systems/bde/linux/user/kernel/linux-user-bde.c;endline=15;md5=ac2e812a539f3a9b3802636f1d3ef09c \
	file://sdk-6.5.24/systems/bde/linux/kernel/linux-kernel-bde.c;endline=15;md5=ac2e812a539f3a9b3802636f1d3ef09c \
	"

inherit module

SRCREV = "6d2330ce1b4fc49d681cffcf09af408661be4c62"

SDK_VERSION = "6.5.24"
PV:append = "+"

SRC_URI = " \
          git://github.com/bisdn/OpenBCM.git;protocol=https;branch=master \
          file://Makefile;subdir=git \
          file://bisdn-bcm-dev.sh \
          file://bisdn-bcm-dev.rules \
          file://patches/0001-gmodule-update-proc-code-for-linux-5.6.patch \
          file://patches/0002-kernel-modules-add-dropped-defines-to-work-with-5.9.patch \
          file://patches/0003-linux-kernel-bde-update-API-usage-for-5.10.patch \
          file://patches/0004-bcm-knet-update-API-for-linux-5.6.0.patch \
          file://patches/0005-bcm-knet-strip-vlan-tag-if-received-untagged-at-port.patch \
          file://patches/0006-bcm-knet-report-link-state.patch \
          file://patches/0007-bcm-knet-allow-setting-speed-duplex.patch \
          file://patches/0008-bcm-knet-implement-get_link_ksettings.patch \
          file://patches/0009-bcm-knet-fix-race-between-creation-and-open.patch \
          file://patches/0010-bcm-knet-use-fully-randomized-mac-addresses.patch \
          file://patches/0011-bcm-knet-allow-marking-packets-as-offloaded.patch \
          file://patches/0012-bcm-knet-replace-open-coded-mac-change-code-with-eth.patch \
          file://patches/0013-bcm-knet-update-API-for-kernel-5.19.patch \
          file://patches/0014-linux-kernel-bde-use-platform_get_irq.patch \
          file://patches/0015-bcm-knet-extract-and-update-DSCP-for-IP-packets.patch \
          file://patches/0016-bcm-knet-fix-reported-tx-bytes.patch \
          file://patches/0017-bcm-knet-switch-to-stat64.patch \
          file://patches/0018-kcom-add-a-message-for-pushing-hw-counters-to-netifs.patch \
          file://patches/0019-bcm-knet-expose-hw-counters-on-port-netifs.patch \
          file://patches/0020-bcm-knet-do-not-mark-BPDU-packets-as-offloaded.patch \
          file://patches/0021-bde-handle-renamed-MAX_ORDER-in-linux-6.8.patch \
          file://patches/0022-bcm-knet-update-API-for-kernel-6.11.patch \
          file://patches/0023-KNET-add-base-support-for-tracked-interfaces.patch \
          file://patches/0024-KNET-update-link-state-of-tracked-interfaces.patch \
          "

SRC_URI:append:agema-ag7648 = " \
    file://linux_kernel_bde.conf \
"

S = "${WORKDIR}/git"

FILES:${PN} += "\
            ${sbindir}/bisdn-bcm-dev.sh \
            ${sysconfdir}/udev/rules.d/bisdn-bcm-dev.rules \
            "
FILES:${PN}:append:agema-ag7648 = "${sysconfdir}/modprobe.d/linux_kernel_bde.conf"


EXTRA_OEMAKE += " SDK_VERSION=${SDK_VERSION}"

do_install:append() {
	# update path marker in udev rules
	sed -i -e "s#SBINDIR#${sbindir}#" ${WORKDIR}/bisdn-bcm-dev.rules

	install -m 0755 -d ${D}${sbindir} \
			   ${D}${sysconfdir}/udev/rules.d
	install -m 0755 ${WORKDIR}/bisdn-bcm-dev.sh    ${D}${sbindir}
	install -m 0644 ${WORKDIR}/bisdn-bcm-dev.rules ${D}${sysconfdir}/udev/rules.d
}

do_install:append:agema-ag7648() {
        install -d ${D}${sysconfdir}/modprobe.d/
        install -m 0644 ${WORKDIR}/*.conf ${D}${sysconfdir}/modprobe.d/
}

KERNEL_MODULE_PROBECONF:append = " linux-kernel-bde"
module_conf_linux-kernel-bde = "softdep linux-kernel-bde post: linux-user-bde linux-bcm-knet"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
