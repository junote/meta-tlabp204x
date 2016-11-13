DESCRIPTION = "qe microcode binary"
SECTION = "qe-ucode"
LICENSE = "Freescale-EULA"
LIC_FILES_CHKSUM = "file://EULA;md5=c9ae442cf1f9dd6c13dfad64b0ffe73f"

python () {
    if not d.getVar("QE_UCODE", True):
        machine = d.getVar("MACHINE", True)
        raise bb.parse.SkipPackage("QE_UCODE not set in \
            meta-tlabp204x/conf/machine/%s.conf" % machine)
}

inherit deploy

SRC_URI = "git://git.freescale.com/ppc/sdk/qe-ucode.git;branch=sdk-v2.0.x"
SRCREV= "a95f42aef152fc613f11099d7e7bc25b44c09836"

S = "${WORKDIR}/git"

do_install () {
    install -d ${D}/boot
    install -m 644 ${QE_UCODE} ${D}/boot/
}

do_deploy () {
    install -d ${DEPLOYDIR}/boot
    install -m 644 ${QE_UCODE} ${DEPLOYDIR}/boot/
}
addtask deploy before do_build after do_install

PACKAGES += "${PN}-image"
FILES_${PN}-image += "/boot/*"
ALLOW_EMPTY_${PN} = "1"
COMPATIBLE_MACHINE = "(t1)"
