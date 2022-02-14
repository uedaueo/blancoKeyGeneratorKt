package blanco.keygeneratorkt.valueobject;

import java.util.List;
import java.util.Map;

/**
 * キージェネレータのバケットをあらわすバリューオブジェクトクラス。このクラスの設定情報をもとにフィールドとセッター・ゲッターが自動生成されます。
 */
public class BlancoKeyGeneratorKtBucketStructure {
    /**
     * 項目番号。省略可能です。
     *
     * フィールド: [no]。
     */
    private String fNo;

    /**
     * フィールド名を指定します。必須項目です。
     *
     * フィールド: [name]。
     */
    private String fName;

    /**
     * キーバリューストアのタイプを指定します。必須項目です。
     *
     * フィールド: [kvsType]。
     */
    private String fKvsType;

    /**
     * table識別子の桁数を指定します。1桁1バイトです。
     *
     * フィールド: [tableIdLength]。
     */
    private String fTableIdLength;

    /**
     * bucket構成のバージョンです。
     *
     * フィールド: [version]。
     */
    private String fVersion;

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
     * このバケットに配置するテーブルです。table番号をキーにMapに保管することで、テーブル番号の重複を防ぎます。
     *
     * フィールド: [tableMap]。
     * デフォルト: [new java.util.HashMap&lt;java.lang.String, blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtTableStructure&gt;()]。
     */
    private Map<String, BlancoKeyGeneratorKtTableStructure> fTableMap = new java.util.HashMap<java.lang.String, blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtTableStructure>();

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
     * フィールドの説明: [フィールド名を指定します。必須項目です。]。
     *
     * @param argName フィールド[name]に設定する値。
     */
    public void setName(final String argName) {
        fName = argName;
    }

    /**
     * フィールド [name] の値を取得します。
     *
     * フィールドの説明: [フィールド名を指定します。必須項目です。]。
     *
     * @return フィールド[name]から取得した値。
     */
    public String getName() {
        return fName;
    }

    /**
     * フィールド [kvsType] の値を設定します。
     *
     * フィールドの説明: [キーバリューストアのタイプを指定します。必須項目です。]。
     *
     * @param argKvsType フィールド[kvsType]に設定する値。
     */
    public void setKvsType(final String argKvsType) {
        fKvsType = argKvsType;
    }

    /**
     * フィールド [kvsType] の値を取得します。
     *
     * フィールドの説明: [キーバリューストアのタイプを指定します。必須項目です。]。
     *
     * @return フィールド[kvsType]から取得した値。
     */
    public String getKvsType() {
        return fKvsType;
    }

    /**
     * フィールド [tableIdLength] の値を設定します。
     *
     * フィールドの説明: [table識別子の桁数を指定します。1桁1バイトです。]。
     *
     * @param argTableIdLength フィールド[tableIdLength]に設定する値。
     */
    public void setTableIdLength(final String argTableIdLength) {
        fTableIdLength = argTableIdLength;
    }

    /**
     * フィールド [tableIdLength] の値を取得します。
     *
     * フィールドの説明: [table識別子の桁数を指定します。1桁1バイトです。]。
     *
     * @return フィールド[tableIdLength]から取得した値。
     */
    public String getTableIdLength() {
        return fTableIdLength;
    }

    /**
     * フィールド [version] の値を設定します。
     *
     * フィールドの説明: [bucket構成のバージョンです。]。
     *
     * @param argVersion フィールド[version]に設定する値。
     */
    public void setVersion(final String argVersion) {
        fVersion = argVersion;
    }

    /**
     * フィールド [version] の値を取得します。
     *
     * フィールドの説明: [bucket構成のバージョンです。]。
     *
     * @return フィールド[version]から取得した値。
     */
    public String getVersion() {
        return fVersion;
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
     * フィールド [tableMap] の値を設定します。
     *
     * フィールドの説明: [このバケットに配置するテーブルです。table番号をキーにMapに保管することで、テーブル番号の重複を防ぎます。]。
     *
     * @param argTableMap フィールド[tableMap]に設定する値。
     */
    public void setTableMap(final Map<String, BlancoKeyGeneratorKtTableStructure> argTableMap) {
        fTableMap = argTableMap;
    }

    /**
     * フィールド [tableMap] の値を取得します。
     *
     * フィールドの説明: [このバケットに配置するテーブルです。table番号をキーにMapに保管することで、テーブル番号の重複を防ぎます。]。
     * デフォルト: [new java.util.HashMap&lt;java.lang.String, blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtTableStructure&gt;()]。
     *
     * @return フィールド[tableMap]から取得した値。
     */
    public Map<String, BlancoKeyGeneratorKtTableStructure> getTableMap() {
        return fTableMap;
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
        buf.append("blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtBucketStructure[");
        buf.append("no=" + fNo);
        buf.append(",name=" + fName);
        buf.append(",kvsType=" + fKvsType);
        buf.append(",tableIdLength=" + fTableIdLength);
        buf.append(",version=" + fVersion);
        buf.append(",description=" + fDescription);
        buf.append(",descriptionList=" + fDescriptionList);
        buf.append(",tableMap=" + fTableMap);
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
    public void copyTo(final BlancoKeyGeneratorKtBucketStructure target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoKeyGeneratorKtBucketStructure#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fNo
        // Type: java.lang.String
        target.fNo = this.fNo;
        // Name: fName
        // Type: java.lang.String
        target.fName = this.fName;
        // Name: fKvsType
        // Type: java.lang.String
        target.fKvsType = this.fKvsType;
        // Name: fTableIdLength
        // Type: java.lang.String
        target.fTableIdLength = this.fTableIdLength;
        // Name: fVersion
        // Type: java.lang.String
        target.fVersion = this.fVersion;
        // Name: fDescription
        // Type: java.lang.String
        target.fDescription = this.fDescription;
        // Name: fDescriptionList
        // Type: java.util.List
        // フィールド[fDescriptionList]はサポート外の型[java.util.Listjava.lang.String]です。
        // Name: fTableMap
        // Type: java.util.Map
        // フィールド[fTableMap]はサポート外の型[java.util.Mapjava.lang.String, blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtTableStructure]です。
    }
}
