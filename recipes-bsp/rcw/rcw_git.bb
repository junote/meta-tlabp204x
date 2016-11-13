SUMMARY = "Reset Configuration Word"
DESCRIPTION = "Reset Configuration Word - hardware boot-time parameters for the QorIQ targets"
LICENSE = "Freescale-EULA"
LIC_FILES_CHKSUM = "file://EULA;md5=c9ae442cf1f9dd6c13dfad64b0ffe73f"

LIC_FILES_CHKSUM = "file://rcw.py;beginline=8;endline=28;md5=9ba0b28922dd187b06b6c8ebcfdd208e"

inherit deploy siteinfo

SRC_URI = "git://git.freescale.com/ppc/sdk/rcw.git;branch=sdk-v2.0.x"
SRCREV = "1af724b64347170e9d9ba8e8f277dd30026a1a7e"

S = "${WORKDIR}/git"

EXTRA_OEMAKE = "BOARDS=${@d.getVar('MACHINE', True).replace('-64b','').replace('-${SITEINFO_ENDIANNESS}','')} DESTDIR=${D}/boot/rcw/"

do_install () {
    oe_runmake install
}
do_install_append_ls102xa () {
    for f in `find ${D}/boot/rcw/ -name "*qspiboot*"`;do
        f_swap=`echo $f |sed -e 's/qspiboot/qspiboot_swap/'`
        tclsh ${STAGING_BINDIR_NATIVE}/byte_swap.tcl $f $f_swap 8
    done
}

do_deploy () {
    install -d ${DEPLOYDIR}/rcw
    cp -r ${D}/boot/rcw/* ${DEPLOYDIR}/rcw/
}
addtask deploy after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/boot"

ALLOW_EMPTY_${PN} = "1"

PACKAGE_ARCH = "${MACHINE_ARCH}"
COMPATIBLE_MACHINE = "(qoriq-ppc)"
