LICENSE = "CLOSED"

inherit systemd

include ofdpa-grpc.inc

SRCREV = "8932fc50d5321b5ca418b4d17fdfc0ffbb00ef26"
PR = "r1"

DEPENDS += "grpc gflags glog protobuf openssl ofdpa systemd"

SYSTEMD_SERVICE:${PN}:append = "ofdpa-grpc.service"

INSANE_SKIP:${PN} = "ldflags"
