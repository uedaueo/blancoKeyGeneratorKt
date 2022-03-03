package blanco.keygenerator

import blanco.keygenerator.valueobjects.BlancoKeyGeneratorKeyPhrase
import blanco.sample.BlancoKeyGeneratorKtTableSample
import org.junit.Test
import kotlin.text.Charsets.UTF_8

class BlancoKeyGeneratorUtilsTest {

    @Test
    fun testInteger2b64() {
        val encoded = BlancoKeyGeneratorUtils.integer2b64(127, 2)
        println(encoded)
        val decoded = BlancoKeyGeneratorUtils.b642integer(encoded)
        println(decoded)
    }

    @Test
    fun testString2key() {
        val phrase: BlancoKeyGeneratorKeyPhrase = BlancoKeyGeneratorKtTableSample.keyList[0].valuePart[0]
        val encoded = BlancoKeyGeneratorUtils.string2key("ValueValueValue", phrase)
        println(encoded)
        val decoded = BlancoKeyGeneratorUtils.key2string(encoded, phrase)
        println(decoded)
    }

    @Test
    fun testPrintHexBinary() {
        val source = "0123456789ABC"
        val hex = BlancoKeyGeneratorUtils.printHexBinary(source.toByteArray(UTF_8))
        println(hex)
        val parsed = BlancoKeyGeneratorUtils.parseHexBinary(hex)
        println(String(parsed, UTF_8))
    }
}