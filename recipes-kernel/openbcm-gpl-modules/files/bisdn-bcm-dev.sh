#!/bin/sh

# sanity check
[ "$DEVPATH" = "/module/linux_user_bde" ] || [ "$DEVPATH" = "/module/linux_kernel_bde" ] || [ "$DEVPATH" = "/module/linux_bcm_knet" ] || exit 0
                                                                                                  
DEV=$(echo $DEVPATH | sed -e 's#/module/##' | sed -e 's#_#-#g')                                   
                                                               
[ "$ACTION" = "remove" ] && {                                  
        [ -e /dev/$DEV ] && rm -f /dev/$DEV
}                                          
                                           
[ "$ACTION" = "add" ] && {                 
        major=$(grep $DEV /proc/devices| cut -d' ' -f1)
        mknod /dev/$DEV c "${major}" 0                 
}                                                      
