TARGET_FPU = "hard"

require conf/machine/include/tune-ppce500mc.inc
require conf/machine/include/soc-family.inc

MACHINEOVERRIDES =. "e500mc:"

require conf/machine/include/qoriq-ppc.inc
