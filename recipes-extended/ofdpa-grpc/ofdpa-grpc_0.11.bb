LICENSE = "CLOSED"

inherit systemd

include ofdpa-grpc.inc

SRCREV = "8f3d79d7ef0c17b7397ccd90b60d3404a13a96a6"

DEPENDS += "grpc gflags glog protobuf openssl ofdpa systemd"

SYSTEMD_SERVICE:${PN}:append = "ofdpa-grpc.service"

INSANE_SKIP:${PN} = "ldflags"

CONFFILES:${PN} = "${sysconfdir}/ofdpa-grpc.conf"
