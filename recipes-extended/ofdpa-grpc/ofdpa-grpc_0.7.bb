LICENSE = "CLOSED"

inherit systemd

include ofdpa-grpc.inc

SRCREV = "a4de834e81cfc8b6f6767379a763650088db9faa"

DEPENDS += "grpc gflags glog protobuf openssl ofdpa systemd"

SYSTEMD_SERVICE:${PN}:append = "ofdpa-grpc.service"

INSANE_SKIP:${PN} = "ldflags"
