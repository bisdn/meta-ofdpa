LICENSE = "CLOSED"

# Nightly packages are regenerated regularily
BB_STRICT_CHECKSUM = "1"

SRC_URI = " \
 ${FEEDDOMAIN}/${FEEDURIPREFIX}/ipk/${PACKAGE_ARCH}/${BPN}_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${PACKAGE_ARCH}.ipk;subdir=${P};;sha256sum=${@d.getVarFlag('SHA256SUM', '${PACKAGE_ARCH}', 1)} \
"

SHA256SUM[cortexa9-vfp] = "d66a68a1d5bac5b2664488cfd6bf263914d0e93558c36c283d6cf20e8fbf69c0"
SHA256SUM[corei7-64] = "7a297741bfb143dc68fad8a4e4e626a137ec63cdeeb400e8a1749050a5cc6dd4"

# Manually calculate expanded ${SRCPV} value
PV = "0.3+gitAUTOINC+${@'${SRCREV}'[0:10]}"
SRCREV = "c8281e4cd6c62e653e4c21963b3e27d8e228f33f"

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
