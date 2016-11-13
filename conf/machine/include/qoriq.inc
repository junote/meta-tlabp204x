# Provides the QorIQ common settings

PREFERRED_PROVIDER_virtual/kernel ?= "linux-qoriq"
PREFERRED_PROVIDER_virtual/bootloader ?= "u-boot-qoriq"
PREFERRED_PROVIDER_u-boot ?= "u-boot-qoriq"
PREFERRED_PROVIDER_cryptodev-linux = "cryptodev-qoriq-linux"
PREFERRED_PROVIDER_cryptodev-module = "cryptodev-qoriq-module"
PREFERRED_PROVIDER_cryptodev-tests = "cryptodev-qoriq-tests"
PREFERRED_PROVIDER_openssl = "openssl-qoriq"
PREFERRED_PROVIDER_qemu = "qemu-qoriq"

MACHINE_FEATURES = "keyboard pci ext2 ext3 serial"
MACHINE_EXTRA_RRECOMMENDS += "udev-rules-qoriq kernel-modules"

IMAGE_CLASSES += "image_types_uboot"
EXTRA_IMAGEDEPENDS += "u-boot"

MACHINEOVERRIDES =. "qoriq:"
