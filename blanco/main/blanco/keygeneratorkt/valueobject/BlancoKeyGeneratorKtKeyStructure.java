package blanco.keygeneratorkt.valueobject;

import java.util.List;

/**
 * キージェネレータのキー定義をあらわすバリューオブジェクトクラス。このクラスの設定情報をもとにフィールドとセッター・ゲッターが自動生成されます。
 */
public class BlancoKeyGeneratorKtKeyStructure {
    /**
     * 項目番号。省略可能です。
     *
     * フィールド: [no]。
     */
    private String fNo;

    /**
     * キー名を指定します。必須項目です。
     *
     * フィールド: [name]。
     */
    private String fName;

    /**
     * 型名をパッケージ名のフル修飾付で指定します。必須項目です。
     *
     * フィールド: [keyPart]。
     * デフォルト: [new java.util.ArrayList&lt;&gt;()]。
     */
    private List<BlancoKeyGeneratorKtKeyPhrase> fKeyPart = new java.util.ArrayList<>();

    /**
     * 型が期待する総称型の具体的な型名を指定します．
     *
     * フィールド: [valuePart]。
     * デフォルト: [new java.util.ArrayList&lt;&gt;()]。
     */
    private List<BlancoKeyGeneratorKtKeyPhrase> fValuePart = new java.util.ArrayList<>();

    /**
     * フィールドの説明です。
     *
     * フィールド: [description]。
     */
    private String fDescription;

    /**
     * フィールドの補助説明です。文字参照エンコード済みの値を格納してください。
     *
     * フィールド: [descriptionList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<String> fDescriptionList = new java.util.ArrayList<java.lang.String>();

    /**
     * フィールド [no] の値を設定します。
     *
     * フィールドの説明: [項目番号。省略可能です。]。
     *
     * @param argNo フィールド[no]に設定する値。
     */
    public void setNo(final String argNo) {
        fNo = argNo;
    }

    /**
     * フィールド [no] の値を取得します。
     *
     * フィールドの説明: [項目番号。省略可能です。]。
     *
     * @return フィールド[no]から取得した値。
     */
    public String getNo() {
        return fNo;
    }

    /**
     * フィールド [name] の値を設定します。
     *
     * フィールドの説明: [キー名を指定します。必須項目です。]。
     *
     * @param argName フィールド[name]に設定する値。
     */
    public void setName(final String argName) {
        fName = argName;
    }

    /**
     * フィールド [name] の値を取得します。
     *
     * フィールドの説明: [キー名を指定します。必須項目です。]。
     *
     * @return フィールド[name]から取得した値。
     */
    public String getName() {
        return fName;
    }

    /**
     * フィールド [keyPart] の値を設定します。
     *
     * フィールドの説明: [型名をパッケージ名のフル修飾付で指定します。必須項目です。]。
     *
     * @param argKeyPart フィールド[keyPart]に設定する値。
     */
    public void setKeyPart(final List<BlancoKeyGeneratorKtKeyPhrase> argKeyPart) {
        fKeyPart = argKeyPart;
    }

    /**
     * フィールド [keyPart] の値を取得します。
     *
     * フィールドの説明: [型名をパッケージ名のフル修飾付で指定します。必須項目です。]。
     * デフォルト: [new java.util.ArrayList&lt;&gt;()]。
     *
     * @return フィールド[keyPart]から取得した値。
     */
    public List<BlancoKeyGeneratorKtKeyPhrase> getKeyPart() {
        return fKeyPart;
    }

    /**
     * フィールド [valuePart] の値を設定します。
     *
     * フィールドの説明: [型が期待する総称型の具体的な型名を指定します．]。
     *
     * @param argValuePart フィールド[valuePart]に設定する値。
     */
    public void setValuePart(final List<BlancoKeyGeneratorKtKeyPhrase> argValuePart) {
        fValuePart = argValuePart;
    }

    /**
     * フィールド [valuePart] の値を取得します。
     *
     * フィールドの説明: [型が期待する総称型の具体的な型名を指定します．]。
     * デフォルト: [new java.util.ArrayList&lt;&gt;()]。
     *
     * @return フィールド[valuePart]から取得した値。
     */
    public List<BlancoKeyGeneratorKtKeyPhrase> getValuePart() {
        return fValuePart;
    }

    /**
     * フィールド [description] の値を設定します。
     *
     * フィールドの説明: [フィールドの説明です。]。
     *
     * @param argDescription フィールド[description]に設定する値。
     */
    public void setDescription(final String argDescription) {
        fDescription = argDescription;
    }

    /**
     * フィールド [description] の値を取得します。
     *
     * フィールドの説明: [フィールドの説明です。]。
     *
     * @return フィールド[description]から取得した値。
     */
    public String getDescription() {
        return fDescription;
    }

    /**
     * フィールド [descriptionList] の値を設定します。
     *
     * フィールドの説明: [フィールドの補助説明です。文字参照エンコード済みの値を格納してください。]。
     *
     * @param argDescriptionList フィールド[descriptionList]に設定する値。
     */
    public void setDescriptionList(final List<String> argDescriptionList) {
        fDescriptionList = argDescriptionList;
    }

    /**
     * フィールド [descriptionList] の値を取得します。
     *
     * フィールドの説明: [フィールドの補助説明です。文字参照エンコード済みの値を格納してください。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[descriptionList]から取得した値。
     */
    public List<String> getDescriptionList() {
        return fDescriptionList;
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
        buf.append("blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtKeyStructure[");
        buf.append("no=" + fNo);
        buf.append(",name=" + fName);
        buf.append(",keyPart=" + fKeyPart);
        buf.append(",valuePart=" + fValuePart);
        buf.append(",description=" + fDescription);
        buf.append(",descriptionList=" + fDescriptionList);
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
    public void copyTo(final BlancoKeyGeneratorKtKeyStructure target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoKeyGeneratorKtKeyStructure#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fNo
        // Type: java.lang.String
        target.fNo = this.fNo;
        // Name: fName
        // Type: java.lang.String
        target.fName = this.fName;
        // Name: fKeyPart
        // Type: List
        // フィールド[fKeyPart]はサポート外の型[Listblanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtKeyPhrase]です。
        // Name: fValuePart
        // Type: List
        // フィールド[fValuePart]はサポート外の型[Listblanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtKeyPhrase]です。
        // Name: fDescription
        // Type: java.lang.String
        target.fDescription = this.fDescription;
        // Name: fDescriptionList
        // Type: java.util.List
        // フィールド[fDescriptionList]はサポート外の型[java.util.Listjava.lang.String]です。
    }
}
