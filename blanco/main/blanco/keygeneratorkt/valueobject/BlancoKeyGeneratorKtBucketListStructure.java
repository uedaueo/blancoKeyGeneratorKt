package blanco.keygeneratorkt.valueobject;

import java.util.List;

/**
 * バケットリストのクラスをあらわすバリューオブジェクトクラス。このクラスの設定情報をもとにクラスが自動生成されます。
 */
public class BlancoKeyGeneratorKtBucketListStructure {
    /**
     * バケットリスト定義書IDです。必須項目です。
     *
     * フィールド: [name]。
     */
    private String fName;

    /**
     * パッケージ名を指定します。必須項目です。
     *
     * フィールド: [package]。
     */
    private String fPackage;

    /**
     * クラスの説明です。
     *
     * フィールド: [description]。
     */
    private String fDescription;

    /**
     * クラスの補助説明です。文字参照エンコード済みの値を格納してください。
     *
     * フィールド: [descriptionList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<String> fDescriptionList = new java.util.ArrayList<java.lang.String>();

    /**
     * バケットを記憶するリストを指定します。
     *
     * フィールド: [bucketList]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtBucketStructure&gt;()]。
     */
    private List<BlancoKeyGeneratorKtBucketStructure> fBucketList = new java.util.ArrayList<blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtBucketStructure>();

    /**
     * パッケージ名の後ろに付加する文字列をしていします。
     *
     * フィールド: [packageSuffix]。
     */
    private String fPackageSuffix;

    /**
     * 定義書で指定されたパッケージ名を上書きします。
     *
     * フィールド: [overridePackage]。
     */
    private String fOverridePackage;

    /**
     * フィールド [name] の値を設定します。
     *
     * フィールドの説明: [バケットリスト定義書IDです。必須項目です。]。
     *
     * @param argName フィールド[name]に設定する値。
     */
    public void setName(final String argName) {
        fName = argName;
    }

    /**
     * フィールド [name] の値を取得します。
     *
     * フィールドの説明: [バケットリスト定義書IDです。必須項目です。]。
     *
     * @return フィールド[name]から取得した値。
     */
    public String getName() {
        return fName;
    }

    /**
     * フィールド [package] の値を設定します。
     *
     * フィールドの説明: [パッケージ名を指定します。必須項目です。]。
     *
     * @param argPackage フィールド[package]に設定する値。
     */
    public void setPackage(final String argPackage) {
        fPackage = argPackage;
    }

    /**
     * フィールド [package] の値を取得します。
     *
     * フィールドの説明: [パッケージ名を指定します。必須項目です。]。
     *
     * @return フィールド[package]から取得した値。
     */
    public String getPackage() {
        return fPackage;
    }

    /**
     * フィールド [description] の値を設定します。
     *
     * フィールドの説明: [クラスの説明です。]。
     *
     * @param argDescription フィールド[description]に設定する値。
     */
    public void setDescription(final String argDescription) {
        fDescription = argDescription;
    }

    /**
     * フィールド [description] の値を取得します。
     *
     * フィールドの説明: [クラスの説明です。]。
     *
     * @return フィールド[description]から取得した値。
     */
    public String getDescription() {
        return fDescription;
    }

    /**
     * フィールド [descriptionList] の値を設定します。
     *
     * フィールドの説明: [クラスの補助説明です。文字参照エンコード済みの値を格納してください。]。
     *
     * @param argDescriptionList フィールド[descriptionList]に設定する値。
     */
    public void setDescriptionList(final List<String> argDescriptionList) {
        fDescriptionList = argDescriptionList;
    }

    /**
     * フィールド [descriptionList] の値を取得します。
     *
     * フィールドの説明: [クラスの補助説明です。文字参照エンコード済みの値を格納してください。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[descriptionList]から取得した値。
     */
    public List<String> getDescriptionList() {
        return fDescriptionList;
    }

    /**
     * フィールド [bucketList] の値を設定します。
     *
     * フィールドの説明: [バケットを記憶するリストを指定します。]。
     *
     * @param argBucketList フィールド[bucketList]に設定する値。
     */
    public void setBucketList(final List<BlancoKeyGeneratorKtBucketStructure> argBucketList) {
        fBucketList = argBucketList;
    }

    /**
     * フィールド [bucketList] の値を取得します。
     *
     * フィールドの説明: [バケットを記憶するリストを指定します。]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtBucketStructure&gt;()]。
     *
     * @return フィールド[bucketList]から取得した値。
     */
    public List<BlancoKeyGeneratorKtBucketStructure> getBucketList() {
        return fBucketList;
    }

    /**
     * フィールド [packageSuffix] の値を設定します。
     *
     * フィールドの説明: [パッケージ名の後ろに付加する文字列をしていします。]。
     *
     * @param argPackageSuffix フィールド[packageSuffix]に設定する値。
     */
    public void setPackageSuffix(final String argPackageSuffix) {
        fPackageSuffix = argPackageSuffix;
    }

    /**
     * フィールド [packageSuffix] の値を取得します。
     *
     * フィールドの説明: [パッケージ名の後ろに付加する文字列をしていします。]。
     *
     * @return フィールド[packageSuffix]から取得した値。
     */
    public String getPackageSuffix() {
        return fPackageSuffix;
    }

    /**
     * フィールド [overridePackage] の値を設定します。
     *
     * フィールドの説明: [定義書で指定されたパッケージ名を上書きします。]。
     *
     * @param argOverridePackage フィールド[overridePackage]に設定する値。
     */
    public void setOverridePackage(final String argOverridePackage) {
        fOverridePackage = argOverridePackage;
    }

    /**
     * フィールド [overridePackage] の値を取得します。
     *
     * フィールドの説明: [定義書で指定されたパッケージ名を上書きします。]。
     *
     * @return フィールド[overridePackage]から取得した値。
     */
    public String getOverridePackage() {
        return fOverridePackage;
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
        buf.append("blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtBucketListStructure[");
        buf.append("name=" + fName);
        buf.append(",package=" + fPackage);
        buf.append(",description=" + fDescription);
        buf.append(",descriptionList=" + fDescriptionList);
        buf.append(",bucketList=" + fBucketList);
        buf.append(",packageSuffix=" + fPackageSuffix);
        buf.append(",overridePackage=" + fOverridePackage);
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
    public void copyTo(final BlancoKeyGeneratorKtBucketListStructure target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoKeyGeneratorKtBucketListStructure#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fName
        // Type: java.lang.String
        target.fName = this.fName;
        // Name: fPackage
        // Type: java.lang.String
        target.fPackage = this.fPackage;
        // Name: fDescription
        // Type: java.lang.String
        target.fDescription = this.fDescription;
        // Name: fDescriptionList
        // Type: java.util.List
        // フィールド[fDescriptionList]はサポート外の型[java.util.Listjava.lang.String]です。
        // Name: fBucketList
        // Type: java.util.List
        // フィールド[fBucketList]はサポート外の型[java.util.Listblanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtBucketStructure]です。
        // Name: fPackageSuffix
        // Type: java.lang.String
        target.fPackageSuffix = this.fPackageSuffix;
        // Name: fOverridePackage
        // Type: java.lang.String
        target.fOverridePackage = this.fOverridePackage;
    }
}
