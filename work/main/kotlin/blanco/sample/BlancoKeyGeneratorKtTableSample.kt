package blanco.sample

import blanco.keygenerator.BlancoKeyGeneratorConstants
import blanco.keygenerator.valueobjects.BlancoKeyGeneratorKey
import blanco.keygenerator.valueobjects.BlancoKeyGeneratorKeyPhrase
import blanco.keygenerator.BlancoKeyGeneratorUtils

class BlancoKeyGeneratorKtTableSample {
    companion object {
        /* table meta information */
        const val displayName: String = "tableSample01"
        const val bucketId: String = "bucket01"
        const val tableIdLength: Int = 2
        const val tableNumber: Int = 1
        const val keyIdLength: Int = 2
        const val maxKeyLength: Int = 900
        const val recordSequenceLength: Int = 2
        const val tableVersion: Int = 1

        /* Key List */
        @JvmField
        val keyList: List<BlancoKeyGeneratorKey> = listOf(
            BlancoKeyGeneratorKey(
                1,
                "pinPointIndex",
                listOf(
                    BlancoKeyGeneratorKeyPhrase("keyItem01", "b64", 64),
                    BlancoKeyGeneratorKeyPhrase("keyItem02", "bin", 128)
                ),
                listOf(
                    BlancoKeyGeneratorKeyPhrase("valueItem01", "b64", 256)
                )
            ),
            BlancoKeyGeneratorKey(
                2,
                "dateOrderIndex",
                listOf(
                    BlancoKeyGeneratorKeyPhrase("keyItem03", "bin", 128)
                ),
                listOf(
                    BlancoKeyGeneratorKeyPhrase("keyItem01", "b64", 64),
                    BlancoKeyGeneratorKeyPhrase("keyItem02", "raw", 20),
                    BlancoKeyGeneratorKeyPhrase("valueItem01", "raw", 256)
                )
            ),
            BlancoKeyGeneratorKey(
                3,
                "numberOrderIndex",
                listOf(
                    BlancoKeyGeneratorKeyPhrase("keyItem02", "bin", 128)
                ),
                listOf(
                    BlancoKeyGeneratorKeyPhrase("keyItem01", "b64", 64),
                    BlancoKeyGeneratorKeyPhrase("keyItem02", "raw", 20),
                    BlancoKeyGeneratorKeyPhrase("valueItem01", "raw", 256)
                )
            ),
            BlancoKeyGeneratorKey(
                4,
                "divideRecordIndex",
                listOf(
                    BlancoKeyGeneratorKeyPhrase("keyItem02", "bin", 128)
                ),
                listOf(
                    BlancoKeyGeneratorKeyPhrase("keyItem01", "b64", 64),
                    BlancoKeyGeneratorKeyPhrase("keyItem02", "raw", 20),
                    BlancoKeyGeneratorKeyPhrase("valueItem01", "raw", 4096)
                )
            )
        )

        @JvmStatic
        fun tableHeader(): String {
            return BlancoKeyGeneratorUtils.long2b64(tableNumber.toLong(), tableIdLength)
        }

        @JvmStatic
        fun pinPointIndex(keyItem01: String, keyItem02: Long, valueItem01: String): String {
            return tableHeader() + BlancoKeyGeneratorUtils.long2b64(1, keyIdLength) +
                    BlancoKeyGeneratorUtils.string2key(keyItem01, keyList[0].keyPart[0]) + BlancoKeyGeneratorConstants.KEY_SEPARATOR +
                    BlancoKeyGeneratorUtils.long2key(keyItem02, keyList[0].keyPart[1]) + BlancoKeyGeneratorConstants.KEY_SEPARATOR +
                    BlancoKeyGeneratorUtils.string2key(valueItem01, keyList[0].valuePart[0])
        }

//        fun pinPointIndex(): String {
//
//        }
    }
}
