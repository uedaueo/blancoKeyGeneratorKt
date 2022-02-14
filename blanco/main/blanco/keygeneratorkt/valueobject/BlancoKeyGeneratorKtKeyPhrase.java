package blanco.keygeneratorkt.valueobject;

/**
 * キージェネレータのキー構成要素定義をあらわすバリューオブジェクトクラス。このクラスの設定情報をもとにフィールドとセッター・ゲッターが自動生成されます。
 */
public class BlancoKeyGeneratorKtKeyPhrase {
    /**
     * 項目名を指定します。必須項目です。
     *
     * フィールド: [name]。
     */
    private String fName;

    /**
     * キー文字列タイプを指定染ます。必須項目です。
     *
     * raw
     * hex
     * bin
     * sha1
     * sha256
     * フィールド: [type]。
     */
    private String fType;

    /**
     * この項目の桁数を指定染ます。必須項目です。
     *
     * フィールド: [length]。
     */
    private Integer fLength;

    /**
     * フィールド [name] の値を設定します。
     *
     * フィールドの説明: [項目名を指定します。必須項目です。]。
     *
     * @param argName フィールド[name]に設定する値。
     */
    public void setName(final String argName) {
        fName = argName;
    }

    /**
     * フィールド [name] の値を取得します。
     *
     * フィールドの説明: [項目名を指定します。必須項目です。]。
     *
     * @return フィールド[name]から取得した値。
     */
    public String getName() {
        return fName;
    }

    /**
     * フィールド [type] の値を設定します。
     *
     * フィールドの説明: [キー文字列タイプを指定染ます。必須項目です。]。
     * raw
     * hex
     * bin
     * sha1
     * sha256
     *
     * @param argType フィールド[type]に設定する値。
     */
    public void setType(final String argType) {
        fType = argType;
    }

    /**
     * フィールド [type] の値を取得します。
     *
     * フィールドの説明: [キー文字列タイプを指定染ます。必須項目です。]。
     * raw
     * hex
     * bin
     * sha1
     * sha256
     *
     * @return フィールド[type]から取得した値。
     */
    public String getType() {
        return fType;
    }

    /**
     * フィールド [length] の値を設定します。
     *
     * フィールドの説明: [この項目の桁数を指定染ます。必須項目です。]。
     *
     * @param argLength フィールド[length]に設定する値。
     */
    public void setLength(final Integer argLength) {
        fLength = argLength;
    }

    /**
     * フィールド [length] の値を取得します。
     *
     * フィールドの説明: [この項目の桁数を指定染ます。必須項目です。]。
     *
     * @return フィールド[length]から取得した値。
     */
    public Integer getLength() {
        return fLength;
    }

    /**
     * Gets the string representation of this value object.
     *
     * <P>Precautions for use</P>
     * <UL>
     * <LI>Only the shallow range of the object will be subject to the stringification process.
     * <LI>Do not use this method if the object has a circular reference.
     * </UL>
     *
     * @return String representation of a value object.
     */
    @Override
    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtKeyPhrase[");
        buf.append("name=" + fName);
        buf.append(",type=" + fType);
        buf.append(",length=" + fLength);
        buf.append("]");
        return buf.toString();
    }

    /**
     * このバリューオブジェクトを指定のターゲットに複写します。
     *
     * <P>使用上の注意</P>
     * <UL>
     * <LI>オブジェクトのシャロー範囲のみ複写処理対象となります。
     * <LI>オブジェクトが循環参照している場合には、このメソッドは使わないでください。
     * </UL>
     *
     * @param target target value object.
     */
    public void copyTo(final BlancoKeyGeneratorKtKeyPhrase target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoKeyGeneratorKtKeyPhrase#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fName
        // Type: java.lang.String
        target.fName = this.fName;
        // Name: fType
        // Type: java.lang.String
        target.fType = this.fType;
        // Name: fLength
        // Type: java.lang.Integer
        target.fLength = this.fLength;
    }
}
