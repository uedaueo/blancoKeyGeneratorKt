package blanco.keygenerator.sample

import blanco.sample.BlancoKeyGeneratorKtTableSample
import org.junit.Test

class BlancoKeyGeneratorKtTableSampleTest {

    @Test
    fun testTableHeader() {
        val tableHeader = BlancoKeyGeneratorKtTableSample.tableHeader()
        println(tableHeader)
    }

    @Test
    fun testPinPointIndex() {
        val pinPointIndex = BlancoKeyGeneratorKtTableSample.pinPointIndex("PrimaryKey01", 123456789, "ValueValueValue")

        println(pinPointIndex)
    }
}
