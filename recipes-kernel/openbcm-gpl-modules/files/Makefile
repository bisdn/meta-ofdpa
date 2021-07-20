ccflags-y			+= -I$(src)/include
ccflags-y			+= -I$(src)/systems/bde/linux/include
ccflags-y			+= -I$(src)/systems/bde/shared/include
ccflags-y			+= -I$(src)/systems/linux/kernel/modules/include

ccflags-$(CONFIG_X86)		+= -DSAL_BDE_DMA_MEM_DEFAULT=32
ccflags-$(CONFIG_ARM)		+= -DSAL_BDE_DMA_MEM_DEFAULT=16 -DIPROC_CMICD

ccflags-$(CONFIG_64BIT)		+= -DPTRS_ARE_64BITS -DLONGS_ARE_64BITS -DPHYS_ADDRS_ARE_64BITS

ifneq ($(CONFIG_BIG_ENDIAN),)
  ccflags-y			+= -DBE_HOST=1 -DSYS_BE_PIO=1 -DSYS_BE_PACKET=0 -DSYS_BE_OTHER=1
else
  ccflags-y			+= -DLE_HOST=1 -DSYS_BE_PIO=0 -DSYS_BE_PACKET=0 -DSYS_BE_OTHER=0
endif

SHARED_OBJS			:= systems/bde/linux/shared/mpool.o \
				   systems/linux/kernel/modules/shared/gmodule.o

linux-kernel-bde-y		:= $(SHARED_OBJS)
linux-kernel-bde-y		+= systems/bde/linux/kernel/linux-kernel-bde.o
linux-kernel-bde-y		+= systems/bde/linux/kernel/linux_dma.o
linux-kernel-bde-y		+= systems/bde/linux/kernel/linux_shbde.o
linux-kernel-bde-y		+= systems/bde/shared/shbde_iproc.o
linux-kernel-bde-y		+= systems/bde/shared/shbde_mdio.o
linux-kernel-bde-y		+= systems/bde/shared/shbde_pci.o

linux-user-bde-y		:= $(SHARED_OBJS)
linux-user-bde-y		+= systems/bde/linux/user/kernel/linux-user-bde.o

obj-m				:= linux-kernel-bde.o linux-user-bde.o

SRC				:= $(shell pwd)

all:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC)

modules_install:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) modules_install

clean:
	$(MAKE) -C $(KERNEL_SRC) M=$(SRC) clean
