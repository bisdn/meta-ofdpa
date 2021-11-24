LICENSE = "CLOSED"

# Nightly packages are regenerated regularily
BB_STRICT_CHECKSUM = "0"

SRC_URI = " \
 ${FEEDDOMAIN}/${FEEDURIPREFIX}/ipk/${PACKAGE_ARCH}/${BPN}_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${PACKAGE_ARCH}.ipk;subdir=${P} \
"

# Manually calculate expanded ${SRCPV} value
PV = "0.3+gitAUTOINC+${@'${SRCREV}'[0:10]}"
SRCREV = "3449f76800dd89fd3040dcacac720876b720fab5"

DEPENDS = "grpc gflags glog protobuf openssl ofdpa"



inherit systemd bin_package

# for some reason ipk doesn't trigger xz-native as a dependency
do_unpack[depends] += " xz-native:do_populate_sysroot"

SYSTEMD_SERVICE_${PN}_append = "ofdpa-grpc.service"

INSANE_SKIP_${PN} = "ldflags"

INHIBIT_PACKAGE_STRIP = "1"
INHIBIT_SYSROOT_STRIP = "1"
INHIBIT_PACKAGE_DEBUG_SPLIT  = "1"

# systemd.class will append to existing presets, so remove them to avoid
# duplicating their contents.
do_install_append() {
	rm -f ${D}/lib/systemd/system-preset/*
}
