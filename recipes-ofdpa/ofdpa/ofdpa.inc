# Include SDK version, and OF-DPA and OpenBCM source revisions in version
PV = "3.0.5.0-EA5+sdk-${SDK_VERSION}+gitAUTOINC+${@'${SRCREV_ofdpa}'[:10]}_${@'${SRCREV_sdk}'[:10]}"

# Nightly packages are regenerated regularily
BB_STRICT_CHECKSUM = "0"

# When building from the bisdn-linux repo, FEEDDOMAIN and FEEDURIPREFIX are
# pulled in from
# https://github.com/bisdn/meta-switch/blob/master/conf/distro/bisdn-linux.conf
SRC_URI = " \
 ${FEEDDOMAIN}/${FEEDURIPREFIX}/ipk/${PACKAGE_ARCH}/ofagent_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${PACKAGE_ARCH}.ipk;subdir=${P} \
 ${FEEDDOMAIN}/${FEEDURIPREFIX}/ipk/${PACKAGE_ARCH}/ofdpa_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${PACKAGE_ARCH}.ipk;subdir=${P} \
 ${FEEDDOMAIN}/${FEEDURIPREFIX}/ipk/${PACKAGE_ARCH}/ofdpa-firmware-bcm56370_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${PACKAGE_ARCH}.ipk;subdir=${P} \
 ${FEEDDOMAIN}/${FEEDURIPREFIX}/ipk/${PACKAGE_ARCH}/ofdpa-firmware-bcm56770_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${PACKAGE_ARCH}.ipk;subdir=${P} \
 ${FEEDDOMAIN}/${FEEDURIPREFIX}/ipk/${PACKAGE_ARCH}/ofdpa-firmware-bcm56870_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${PACKAGE_ARCH}.ipk;subdir=${P} \
 ${FEEDDOMAIN}/${FEEDURIPREFIX}/ipk/${PACKAGE_ARCH}/python3-ofdpa_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${PACKAGE_ARCH}.ipk;subdir=${P} \
"

inherit bin_package

# for some reason ipk doesn't trigger xz-native as a dependency
do_unpack[depends] += " xz-native:do_populate_sysroot"

# fails because no license file in the ipk
deltask do_populate_lic

# systemd.class will append to existing presets, so remove them to avoid
# duplicating their contents.
do_install:append() {
	rm -f ${D}/lib/systemd/system-preset/*
}

INSANE_SKIP:${PN} = "ldflags"
INSANE_SKIP:ofagent = "ldflags"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"
