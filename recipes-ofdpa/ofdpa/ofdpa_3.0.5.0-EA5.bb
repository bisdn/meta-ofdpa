DESCRIPTION = ""
LICENSE = "CLOSED"

# Include SDK version, and OF-DPA and OpenBCM source revisions in version
PV = "3.0.5.0-EA5+sdk-${SDK_VERSION}+gitAUTOINC+${@'${SRCREV_ofdpa}'[:10]}_${@'${SRCREV_sdk}'[:10]}"

PR = "r3"
SDK_VERSION = "6.5.22"
SRCREV_ofdpa = "ce97ed9907a369c08774f724587f73c2f6039ab1"
SRCREV_sdk = "f01ceb9cf4238b762cc4422e7ebe1c38a113464e"

DEPENDS = "python3 onl"
RDEPENDS_${PN} += "libgcc udev openbcm-gpl-modules"

RDEPENDS_${PN} += " ${@bb.utils.contains('OFDPA_SWITCH_SUPPORT', 'BCM56770', '${PN}-firmware-bcm56770', '', d)}"

# Nightly packages are regenerated regularily
BB_STRICT_CHECKSUM = "0"

SRC_URI = " \
 http://repo.bisdn.de/nightly_builds/${MACHINE}/master/packages_latest-build/ipk/${MACHINE_ARCH}/ofagent_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P} \
 http://repo.bisdn.de/nightly_builds/${MACHINE}/master/packages_latest-build/ipk/${MACHINE_ARCH}/ofdpa_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P} \
 http://repo.bisdn.de/nightly_builds/${MACHINE}/master/packages_latest-build/ipk/${MACHINE_ARCH}/ofdpa-firmware-bcm56770_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P} \
 http://repo.bisdn.de/nightly_builds/${MACHINE}/master/packages_latest-build/ipk/${MACHINE_ARCH}/ofdpa-firmware-bcm56870_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P} \
 http://repo.bisdn.de/nightly_builds/${MACHINE}/master/packages_latest-build/ipk/${MACHINE_ARCH}/python3-ofdpa_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P} \
"

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
