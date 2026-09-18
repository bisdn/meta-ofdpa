LICENSE = "CLOSED"

inherit systemd

include ofdpa-grpc.inc

SRCREV = "094931e74ada4a2a994101a210bb1277fd7eb371"

DEPENDS += "grpc gflags glog protobuf openssl ofdpa systemd"

SYSTEMD_SERVICE:${PN}:append = "ofdpa-grpc.service"

INSANE_SKIP:${PN} = "ldflags"

CONFFILES:${PN} = "${sysconfdir}/ofdpa-grpc.conf"
