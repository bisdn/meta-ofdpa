LICENSE = "CLOSED"

# Nightly packages are regenerated regularily
BB_STRICT_CHECKSUM = "1"

SRC_URI = " \
 http://repo.bisdn.de/pub/onie/${MACHINE}/packages-v${DISTRO_VERSION}/ipk/${PACKAGE_ARCH}/${BPN}_${@'${PV}'.replace('AUTOINC', '0')}-${PR}_${PACKAGE_ARCH}.ipk;subdir=${P};;sha256sum=${@d.getVarFlag('SHA256SUM', '${PACKAGE_ARCH}', 1)} \
"

SHA256SUM[cortexa9-vfp] = "9578f89c0277cad0b2ef1a73d1db4a2d76a88cf624e4ce6b922ff0f5205230bd"
SHA256SUM[corei7-64] = "7f540d4289944ea324c8218c6537200449ececef80f9906607156fb8b772b475"

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
