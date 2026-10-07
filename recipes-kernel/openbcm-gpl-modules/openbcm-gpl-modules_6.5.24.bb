LICENSE = "GPL-2.0-only"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/GPL-2.0-only;md5=801f80980d171dd6425610833a22dbe6"

inherit module

SRCREV = "13423f8045f7c2a694b82a863ed3dd4d0dc4805d"

SDK_VERSION = "6.5.24"
PV:append = "+"

SRC_URI = " \
          git://github.com/bisdn/OpenBCM.git;protocol=https;branch=bisdn/main \
          file://Makefile;subdir=git \
          file://bisdn-bcm-dev.sh \
          file://bisdn-bcm-dev.rules \
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
