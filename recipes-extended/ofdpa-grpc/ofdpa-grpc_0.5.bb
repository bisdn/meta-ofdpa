LICENSE = "CLOSED"

inherit systemd

include ofdpa-grpc.inc

SRCREV = "d293d675f2cd6a4b83403bd9ce6c33c9e228fceb"

DEPENDS += "grpc gflags glog protobuf openssl ofdpa"

SYSTEMD_SERVICE:${PN}:append = "ofdpa-grpc.service"

INSANE_SKIP:${PN} = "ldflags"
