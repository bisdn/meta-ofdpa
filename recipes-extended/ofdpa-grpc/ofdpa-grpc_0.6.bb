LICENSE = "CLOSED"

inherit systemd

include ofdpa-grpc.inc

SRCREV = "a323a61ca506f5b9f9691e559a2c5b833c619435"

DEPENDS += "grpc gflags glog protobuf openssl ofdpa systemd"

SYSTEMD_SERVICE:${PN}:append = "ofdpa-grpc.service"

INSANE_SKIP:${PN} = "ldflags"
