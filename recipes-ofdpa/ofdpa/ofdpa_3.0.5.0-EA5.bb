DESCRIPTION = ""
LICENSE = "CLOSED"

PR = "r0.8"

DEPENDS = "python3 onl"
RDEPENDS_${PN} += "libgcc udev openbcm-gpl-modules"

RDEPENDS_${PN} += " ${@bb.utils.contains('OFDPA_SWITCH_SUPPORT', 'BCM56770', '${PN}-firmware-bcm56770', '', d)}"

# Nightly packages are regenerated regularily
BB_STRICT_CHECKSUM = "1"

SRC_URI = " \
 http://repo.bisdn.de/pub/onie/${MACHINE}/packages-v${DISTRO_VERSION}/ipk/${MACHINE_ARCH}/ofagent_${PV}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P};sha256sum=${@d.getVarFlag('SHA256SUMS', '${MACHINE}.ofagent', 1)} \
 http://repo.bisdn.de/pub/onie/${MACHINE}/packages-v${DISTRO_VERSION}/ipk/${MACHINE_ARCH}/ofdpa_${PV}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P};sha256sum=${@d.getVarFlag('SHA256SUMS', '${MACHINE}.ofdpa', 1)} \
 http://repo.bisdn.de/pub/onie/${MACHINE}/packages-v${DISTRO_VERSION}/ipk/${MACHINE_ARCH}/ofdpa-firmware-bcm56770_${PV}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P};sha256sum=${@d.getVarFlag('SHA256SUMS', '${MACHINE}.ofdpa-firmware-bcm56770', 1)} \
 http://repo.bisdn.de/pub/onie/${MACHINE}/packages-v${DISTRO_VERSION}/ipk/${MACHINE_ARCH}/python3-ofdpa_${PV}-${PR}_${MACHINE_ARCH}.ipk;subdir=${P};sha256sum=${@d.getVarFlag('SHA256SUMS', '${MACHINE}.python3-ofdpa', 1)} \
"

SHA256SUMS[accton-as4610.ofagent] = "8586ab3caa1ff6cf62097220b4f917fc036887da9684ad3a1b8088b3cc9c50a3"
SHA256SUMS[accton-as4610.ofdpa] = "9a0415b50d662f09476d157dc2561ea13955cc2815321165685de407e9cfb020"
SHA256SUMS[accton-as4610.ofdpa-firmware-bcm56770] = "ad08f44f39631776c5278976233d16c6a4f11aa31070d5325fb7716be1bea3d5"
SHA256SUMS[accton-as4610.python3-ofdpa] = "151d55736e662275ef5cbe319ac94825be4beaa34254dafdfb3e7a5fcf4c4c7c"

SHA256SUMS[agema-ag5648.ofagent] = "2ee0811a9c2cf104dad1ee60836c6e42b5d0427366474dabdf9e830871425620"
SHA256SUMS[agema-ag5648.ofdpa] = "196c00a361c357be64932c8a6e5aba872706050701ba4d23f92a2051daad7afa"
SHA256SUMS[agema-ag5648.ofdpa-firmware-bcm56770] = "c5c69e3048cf23a044f205096bde4164d9dcbe1a2b703ab02e6ac2a8c4ae24e2"
SHA256SUMS[agema-ag5648.python3-ofdpa] = "59b2a30b9cf72bfd444033bb339cc6f2b18e3c57d0487edda83f04a6bd3ebfae"

SHA256SUMS[agema-ag7648.ofagent] = "64fd53fda4c370bb2985b74d9813b6f63d947dd50332948f9de6c7dee3133eb8"
SHA256SUMS[agema-ag7648.ofdpa] = "87ffcc2badec208ba06be984eaa0a61700436f62e3f809b9918f5994194b2969"
SHA256SUMS[agema-ag7648.ofdpa-firmware-bcm56770] = "4662a6b3fc16b5e1ed1f664005abdc303500626c153e0e2bb9e1bf2689ff0269"
SHA256SUMS[agema-ag7648.python3-ofdpa] = "1150b8beb3b03fde4991f79627970952ebc60066e706335584b3d1c1eb1cfd5c"

SHA256SUMS[cel-questone-2a.ofagent] = "7bab7483eaf7944dfe04102ee02520eb09e2aad63c62709eb6aa955015444259"
SHA256SUMS[cel-questone-2a.ofdpa] = "ac63388f0a94d37206e50c5d5bb19971644bad33d70c0a6abebf655a8de82e08"
SHA256SUMS[cel-questone-2a.ofdpa-firmware-bcm56770] = "5e3dbe391fcb9604bbcb36d24f8a6041c834caebf2414e4a5d3dd52fa5bc3043"
SHA256SUMS[cel-questone-2a.python3-ofdpa] = "d55abe51ba902209dc68c4ef37de8f4d7c1a4174ac0b358089f310ea67aec482"

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

PACKAGES =+ "ofagent python3-${PN} ${PN}-firmware-bcm56770"

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

CONFFILES_${PN} = " \
  ${sysconfdir}/default/ofdpa \
  ${sysconfdir}/ofdpa/rc.soc \
"

CONFFILES_ofagent = "${sysconfdir}/default/ofagent"
