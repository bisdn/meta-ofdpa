LICENSE = "CLOSED"

inherit systemd

include ofdpa-grpc.inc

SRCREV = "03471865f9fabb58736796b33c63a70929cf950e"

DEPENDS += "grpc gflags glog protobuf openssl ofdpa"

SYSTEMD_SERVICE:${PN}:append = "ofdpa-grpc.service"

INSANE_SKIP:${PN} = "ldflags"
