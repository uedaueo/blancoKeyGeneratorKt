package blanco.keygenerator

import blanco.keygenerator.valueobjects.BlancoKeyGeneratorKeyPhrase
import blanco.sample.BlancoKeyGeneratorKtTableSample
import org.junit.Test
import kotlin.text.Charsets.UTF_8

class BlancoKeyGeneratorUtilsTest {

    @Test
    fun testLong2b64() {
        val encoded = BlancoKeyGeneratorUtils.long2b64(-131072, 3)
        println("test: " + encoded)
        val decoded = BlancoKeyGeneratorUtils.b642long(encoded)
        println("test: " + decoded)
    }

    @Test
    fun testLong2base32() {
        val base = 32
        val encoded = BlancoKeyGeneratorUtils.long2baseN(-1, base, 3)
        println("test: " + encoded + "(" + base + ")")
        val decoded = BlancoKeyGeneratorUtils.baseN2long(encoded, base)
        println("test: " + decoded)
    }

    @Test
    fun testLong2base16() {
        val base = 16
        val encoded = BlancoKeyGeneratorUtils.long2baseN(2047, base, 3)
        println("test: " + encoded + "(" + base + ")")
        val decoded = BlancoKeyGeneratorUtils.baseN2long(encoded, base)
        println("test: " + decoded)
    }

    @Test
    fun testLong2base2() {
        val base = 2
        val encoded = BlancoKeyGeneratorUtils.long2baseN(-4, base, 3)
        println("test: " + encoded + "(" + base + ")")
        val decoded = BlancoKeyGeneratorUtils.baseN2long(encoded, base)
        println("test: " + decoded)
    }

    @Test
    fun testLong2Key() {
        val pharse: BlancoKeyGeneratorKeyPhrase = BlancoKeyGeneratorKtTableSample.keyList[0].keyPart[1]
        val encoded = BlancoKeyGeneratorUtils.long2key(123456789, pharse)
        println(encoded)
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
