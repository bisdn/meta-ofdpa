LICENSE = "CLOSED"

inherit systemd

include ofdpa-grpc.inc

SRCREV = "24232344992b46ee86be4dd14319ebde4e886a96"

DEPENDS += "grpc gflags glog protobuf openssl ofdpa systemd"

SYSTEMD_SERVICE:${PN}:append = "ofdpa-grpc.service"

INSANE_SKIP:${PN} = "ldflags"
