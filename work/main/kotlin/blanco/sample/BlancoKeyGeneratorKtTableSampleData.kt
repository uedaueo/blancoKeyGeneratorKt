package blanco.sample

import blanco.sample.valueobjectkt.simple.SimpleSample

data class BlancoKeyGeneratorKtTableSampleData
    constructor(
        var keyItem01: String = "PrimaryKey",
        var keyItem02: Int = 0,
        var keyItem03: Int = 0,
        var valueItem01: String = "文字列項目"
    ) {
    var valueItem02: SimpleSample = SimpleSample()
}
