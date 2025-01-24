LICENSE = "CLOSED"

inherit systemd

include ofdpa-grpc.inc

SRCREV = "58ce2e6cacca758b5fc55c66447982e982098ceb"

DEPENDS += "grpc gflags glog protobuf openssl ofdpa systemd"

SYSTEMD_SERVICE:${PN}:append = "ofdpa-grpc.service"

INSANE_SKIP:${PN} = "ldflags"
