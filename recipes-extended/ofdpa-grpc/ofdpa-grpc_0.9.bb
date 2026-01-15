LICENSE = "CLOSED"

inherit systemd

include ofdpa-grpc.inc

SRCREV = "46149d7586b3ac9a768276b414afabba1bc86c9b"

DEPENDS += "grpc gflags glog protobuf openssl ofdpa systemd"

SYSTEMD_SERVICE:${PN}:append = "ofdpa-grpc.service"

INSANE_SKIP:${PN} = "ldflags"
