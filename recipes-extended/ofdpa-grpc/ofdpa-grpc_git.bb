LICENSE = "CLOSED"

inherit systemd

include ofdpa-grpc.inc

SRCREV = "84a188a00256e0a6ad502970de524a6485f1bf4b"

DEPENDS += "grpc gflags glog protobuf openssl ofdpa"

SYSTEMD_SERVICE:${PN}:append = "ofdpa-grpc.service"

INSANE_SKIP:${PN} = "ldflags"
