LICENSE = "GPLv2"
LIC_FILES_CHKSUM = " \
	file://systems/bde/linux/user/kernel/linux-user-bde.c;endline=15;md5=ac2e812a539f3a9b3802636f1d3ef09c \
	file://systems/bde/linux/kernel/linux-kernel-bde.c;endline=15;md5=ac2e812a539f3a9b3802636f1d3ef09c \
	"

inherit module

SRC_URI = " \
          file://include;subdir=src \
          file://systems;subdir=src \
          file://Makefile;subdir=src \
          file://bisdn-bcm-dev.sh \
          file://bisdn-bcm-dev.rules \
          file://patches/0001-gmodule-update-proc-code-for-linux-5.6.patch \
          file://patches/0002-kernel-modules-add-dropped-defines-to-work-with-5.9.patch \
          file://patches/0003-linux-kernel-bde-update-API-usage-for-5.10.patch \
          file://patches/0004-bcm-knet-proc_code.patch \
          file://patches/xgs_iproc_compat.patch \
          "

SRC_URI_append_agema-ag7648 = " \
    file://linux_kernel_bde.conf \
"

S = "${WORKDIR}/src"

FILES_${PN} += "\
            ${sbindir}/bisdn-bcm-dev.sh \
            ${sysconfdir}/udev/rules.d/bisdn-bcm-dev.rules \
            "
FILES_${PN}_append_agema-ag7648 = "${sysconfdir}/modprobe.d/linux_kernel_bde.conf"

do_install_append() {
	# update path marker in udev rules
	sed -i -e "s#SBINDIR#${sbindir}#" ${WORKDIR}/bisdn-bcm-dev.rules

	install -m 0755 -d ${D}${sbindir} \
			   ${D}${sysconfdir}/udev/rules.d
	install -m 0755 ${WORKDIR}/bisdn-bcm-dev.sh    ${D}${sbindir}
	install -m 0644 ${WORKDIR}/bisdn-bcm-dev.rules ${D}${sysconfdir}/udev/rules.d
}

do_install_append_agema-ag7648() {
        install -d ${D}${sysconfdir}/modprobe.d/
        install -m 0644 ${WORKDIR}/*.conf ${D}${sysconfdir}/modprobe.d/
}

KERNEL_MODULE_AUTOLOAD += " linux-kernel-bde linux-user-bde linux-bcm-knet"

# The inherit of module.bbclass will automatically name module packages with
# "kernel-module-" prefix as required by the oe-core build environment.
