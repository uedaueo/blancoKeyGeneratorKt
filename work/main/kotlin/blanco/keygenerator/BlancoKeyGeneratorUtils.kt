/*
 * This source code is auto-generated by blanco Framework.
 */
package blanco.keygenerator

import blanco.keygenerator.enums.BlancoKeyGeneratorKeyTypes
import blanco.keygenerator.exceptions.BlancoKeyGeneratorException
import blanco.keygenerator.valueobjects.BlancoKeyGeneratorKeyPhrase
import java.nio.ByteBuffer
import java.nio.CharBuffer
import java.nio.charset.Charset
import java.nio.charset.CharsetEncoder
import java.nio.charset.CoderResult
import java.nio.charset.CodingErrorAction
import java.security.MessageDigest
import java.util.Base64
import kotlin.math.pow
import kotlin.text.Charsets.UTF_8

/**
 * Utilities for blancoKeyGenerator
 */
class BlancoKeyGeneratorUtils {
    companion object {
        /**
         * Convert string item into key string
         */
        @JvmStatic
        fun string2key(item: String, keyPhrase: BlancoKeyGeneratorKeyPhrase): String {
            var result = ""
            if (keyPhrase.type == BlancoKeyGeneratorKeyTypes.RAW.type) {
                result = item;
            } else if (keyPhrase.type == BlancoKeyGeneratorKeyTypes.B64.type) {
                result = Base64.getUrlEncoder().withoutPadding().encodeToString(item.toByteArray(UTF_8))
            } else if (keyPhrase.type == BlancoKeyGeneratorKeyTypes.HEX.type) {
                result = printHexBinary(item.toByteArray(UTF_8))
            } else if (keyPhrase.type == BlancoKeyGeneratorKeyTypes.SHA1.type || keyPhrase.type == BlancoKeyGeneratorKeyTypes.SHA256.type) {
                result = hashString(item, keyPhrase.type, UTF_8)
            } else {
                throw BlancoKeyGeneratorException(keyPhrase.type + " is not valid for string type key.")
            }
            return truncateStringByBytes(result, UTF_8, keyPhrase.length, false)
        }

        /**
         * Convert key into string.
         */
        @JvmStatic
        fun key2string(encoded: String, keyPhrase: BlancoKeyGeneratorKeyPhrase) {
            var decoded: String = ""
            if (keyPhrase.type == BlancoKeyGeneratorKeyTypes.RAW.type) {
                decoded = encoded;
            } else if (keyPhrase.type == BlancoKeyGeneratorKeyTypes.B64.type) {
                println(" hoge " + Base64.getUrlDecoder().decode(encoded))
                decoded = String(Base64.getUrlDecoder().decode(encoded), UTF_8)
            } else if (keyPhrase.type == BlancoKeyGeneratorKeyTypes.HEX.type) {
                decoded = String(parseHexBinary(encoded), UTF_8)
            } else if (keyPhrase.type == BlancoKeyGeneratorKeyTypes.SHA1.type || keyPhrase.type == BlancoKeyGeneratorKeyTypes.SHA256.type) {
                decoded = hashString(encoded, keyPhrase.type, UTF_8)
            } else {
                throw BlancoKeyGeneratorException(keyPhrase.type + " is not valid for string type key.")
            }
        }

        /**
         * Convert integer item into key string.
         */
        @JvmStatic
        fun integer2key(item: Long, keyPhrase: BlancoKeyGeneratorKeyPhrase): String {
            var result = ""
            if (keyPhrase.type == BlancoKeyGeneratorKeyTypes.RAW.type) {
                result = item.toString()
            } else if (keyPhrase.type == BlancoKeyGeneratorKeyTypes.B64.type) {
                result = Base64.getUrlEncoder().withoutPadding().encodeToString(Long.toString().toByteArray(UTF_8))
            } else if (keyPhrase.type == BlancoKeyGeneratorKeyTypes.HEX.type) {
                result = item.toString(16)
            } else if (keyPhrase.type == BlancoKeyGeneratorKeyTypes.BIN.type) {
                result = item.toString(2)
            } else if (keyPhrase.type == BlancoKeyGeneratorKeyTypes.SHA1.type || keyPhrase.type == BlancoKeyGeneratorKeyTypes.SHA256.type) {
                result = hashString(item.toString(), keyPhrase.type, UTF_8)
            } else {
                throw BlancoKeyGeneratorException(keyPhrase.type + " is not valid for integer type key.")
            }
            return truncateStringByBytes(result, UTF_8, keyPhrase.length, true)
        }

        @JvmStatic
        fun hashString(target: String, hashType:String, charset: Charset): String {
            val hashByte = MessageDigest.getInstance(hashType).digest(target.toByteArray(charset))
            return printHexBinary(hashByte)
        }

        @JvmStatic
        fun truncateStringByBytes(original: String, charset:Charset, limit: Int, leftPadding: Boolean): String {
            val byteBuffer: ByteBuffer = ByteBuffer.allocate(limit)
            val charBuffer: CharBuffer = CharBuffer.wrap(original)
            val encoder: CharsetEncoder = charset.newEncoder()
                .onMalformedInput(CodingErrorAction.REPLACE)
                .onUnmappableCharacter(CodingErrorAction.REPLACE)
                .reset()
            val coderResult: CoderResult = encoder.encode(charBuffer, byteBuffer, true)
            if (!coderResult.isOverflow) {
                var result = original
                if (leftPadding) {
                    val olength = original.length
                    result = ("%" + limit + "s").format(original)
                }
                return result
            }
            encoder.flush(byteBuffer)
            return charBuffer.flip().toString()
        }

        /**
         * encode integer as 6 bytes big endian expression base64
         */
        @JvmStatic
        fun integer2b64(value: Int, length: Int): String {
            if (length < 0 || length > 5) {
                // out of the range of integer.
                throw BlancoKeyGeneratorException("Length should be between 1 and 5, because of integer is 32 bit digit.")
            }
            val expectedBits = 6 * length
            val maxValue = 2.toDouble().pow(expectedBits.toDouble()).toInt()
            if (value >= maxValue) {
                throw BlancoKeyGeneratorException("%d is exceed the limit of %d length of base64 encoding.".format(value, length))
            }

            /* encode integer as 6 bytes big endian expression */
            val encoded = Base64
                .getUrlEncoder()
                .withoutPadding()
                .encodeToString(ByteArray(6) {
                    i -> (value.toLong() shr ((5 - i) * 8)).toByte()
                })
            return encoded.substring(8 - length)
        }

        @JvmStatic
        fun b642integer(base64: String): Int {
            val length = base64.length
            if (length < 1 || length > 6) {
                throw BlancoKeyGeneratorException("Length should be between 1 and 6, because of integer is shown under 6 characters.")
            }
            var encoded = base64
            for (i in 0..7 - length) {
                encoded = "A" + encoded
            }
            val bytes = Base64.getUrlDecoder().decode(encoded)
            /* Big Endian */
            var result: Int = 0
            for (i in 0..5) {
                val value = bytes.getOrNull(i) ?: 0
                result = result shl 8
                result = result or value.toUByte().toInt()
            }
            return result
        }

        @JvmField
        val hexCode: CharArray = "0123456789ABCDEF".toCharArray()

        @JvmStatic
        fun printHexBinary(data: ByteArray): String {
            val r: StringBuffer = StringBuffer()
            for (b in data) {
                r.append(hexCode[(b.toInt() ushr 4) and  0xf])
                r.append(hexCode[(b.toInt() and 0x0f)])
            }
            return r.toString()
        }

        @JvmStatic
        fun parseHexBinary(hexString: String): ByteArray {
            val len = hexString.length
            if ((len % 2) != 0 ) {
                throw BlancoKeyGeneratorException("hexBinary needs to be even-length: " + hexString)
            }

            val out: ByteArray = ByteArray(len / 2)
            var isHigh = true;
            var buf: Int = 0
            var counter = 0
            hexString.forEach {
                val v = hexToBin(it)
                if (isHigh) {
                    buf = v shl 4
                    isHigh = false
                } else {
                    buf += v
                    out[counter / 2] = buf.toByte()
                    buf = 0
                    counter += 2
                    isHigh = true
                }
            }

            return out
        }

        @JvmStatic
        fun hexToBin(ch: Char): Int {
            if ('0' <= ch && ch <= '9') {
                return ch - '0';
            }
            if ('A' <= ch && ch <= 'F') {
                return ch - 'A' + 10;
            }
            if ('a' <= ch && ch <= 'f') {
                return ch - 'a' + 10;
            }
            return -1;
        }
    }
}