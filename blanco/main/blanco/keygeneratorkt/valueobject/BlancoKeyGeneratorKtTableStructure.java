package blanco.keygeneratorkt.valueobject;

import java.util.List;

import blanco.cg.valueobject.BlancoCgField;

/**
 * キージェネレータのクラスをあらわすバリューオブジェクトクラス。このクラスの設定情報をもとにクラスが自動生成されます。
 */
public class BlancoKeyGeneratorKtTableStructure {
    /**
     * フィールド名を指定します。必須項目です。
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
     * クラスの総称型を指定します。
     *
     * フィールド: [generic]。
     */
    private String fGeneric;

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
     * クラスのアノテーションを指定します。
     *
     * フィールド: [annotationList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<String> fAnnotationList = new java.util.ArrayList<java.lang.String>();

    /**
     * importを指定します。
     *
     * フィールド: [importList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<String> fImportList = new java.util.ArrayList<java.lang.String>();

    /**
     * クラスのアクセス。通常は public。
     *
     * フィールド: [access]。
     * デフォルト: [&quot;public&quot;]。
     */
    private String fAccess = "public";

    /**
     * dataクラスかどうか。
     *
     * フィールド: [data]。
     * デフォルト: [false]。
     */
    private Boolean fData = false;

    /**
     * クラスが拡張可能かどうか。kotlin では通常は true。
     *
     * フィールド: [final]。
     * デフォルト: [true]。
     */
    private Boolean fFinal = true;

    /**
     * 抽象クラスかどうか。通常は false。
     *
     * フィールド: [abstract]。
     * デフォルト: [false]。
     */
    private Boolean fAbstract = false;

    /**
     * toStringメソッドを生成するかどうか。
     *
     * フィールド: [generateToString]。
     * デフォルト: [false]。
     */
    private Boolean fGenerateToString = false;

    /**
     * フィールド名の名前変形をおこなうかどうか。
     *
     * フィールド: [adjustFieldName]。
     * デフォルト: [true]。
     */
    private Boolean fAdjustFieldName = true;

    /**
     * デフォルト値の変形をおこなうかどうか。※なるべく変形を利用しないことを推奨したい。※プログラムAPIとして生成する際には、このフィールドを明示的に設定してください。
     *
     * フィールド: [adjustDefaultValue]。
     * デフォルト: [false]。
     */
    private Boolean fAdjustDefaultValue = false;

    /**
     * 継承するクラスを指定します。
     *
     * フィールド: [extends]。
     * デフォルト: [new blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtExtendsStructure()]。
     */
    private BlancoKeyGeneratorKtExtendsStructure fExtends = new blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtExtendsStructure();

    /**
     * 実装するインタフェース(java.lang.String)の一覧。
     *
     * フィールド: [implementsList]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     */
    private List<String> fImplementsList = new java.util.ArrayList<java.lang.String>();

    /**
     * フィールドを記憶するリストを指定します。
     *
     * フィールド: [fieldList]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtFieldStructure&gt;()]。
     */
    private List<BlancoKeyGeneratorKtFieldStructure> fFieldList = new java.util.ArrayList<blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtFieldStructure>();

    /**
     * キーを記憶するリストを指定します。
     *
     * フィールド: [keyList]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtKeyStructure&gt;()]。
     */
    private List<BlancoKeyGeneratorKtKeyStructure> fKeyList = new java.util.ArrayList<blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtKeyStructure>();

    /**
     * 委譲を記憶するリストを指定します。
     *
     * フィールド: [delegateList]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtDelegateStructure&gt;()]。
     */
    private List<BlancoKeyGeneratorKtDelegateStructure> fDelegateList = new java.util.ArrayList<blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtDelegateStructure>();

    /**
     * ファイル説明
     *
     * フィールド: [fileDescription]。
     */
    private String fFileDescription;

    /**
     * クラス定義に含めるコンストラクタの引数マップです。&lt;引数名, 型&gt; となります。
     *
     * フィールド: [constructorArgList]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.cg.valueobject.BlancoCgField&gt;()]。
     */
    private List<BlancoCgField> fConstructorArgList = new java.util.ArrayList<blanco.cg.valueobject.BlancoCgField>();

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
     * このテーブルが配置されるバケットの情報を格納します。
     *
     * フィールド: [bucket]。
     * デフォルト: [new blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtBucketStructure()]。
     */
    private BlancoKeyGeneratorKtBucketStructure fBucket = new blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtBucketStructure();

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
     * フィールド [generic] の値を設定します。
     *
     * フィールドの説明: [クラスの総称型を指定します。]。
     *
     * @param argGeneric フィールド[generic]に設定する値。
     */
    public void setGeneric(final String argGeneric) {
        fGeneric = argGeneric;
    }

    /**
     * フィールド [generic] の値を取得します。
     *
     * フィールドの説明: [クラスの総称型を指定します。]。
     *
     * @return フィールド[generic]から取得した値。
     */
    public String getGeneric() {
        return fGeneric;
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
     * フィールド [annotationList] の値を設定します。
     *
     * フィールドの説明: [クラスのアノテーションを指定します。]。
     *
     * @param argAnnotationList フィールド[annotationList]に設定する値。
     */
    public void setAnnotationList(final List<String> argAnnotationList) {
        fAnnotationList = argAnnotationList;
    }

    /**
     * フィールド [annotationList] の値を取得します。
     *
     * フィールドの説明: [クラスのアノテーションを指定します。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[annotationList]から取得した値。
     */
    public List<String> getAnnotationList() {
        return fAnnotationList;
    }

    /**
     * フィールド [importList] の値を設定します。
     *
     * フィールドの説明: [importを指定します。]。
     *
     * @param argImportList フィールド[importList]に設定する値。
     */
    public void setImportList(final List<String> argImportList) {
        fImportList = argImportList;
    }

    /**
     * フィールド [importList] の値を取得します。
     *
     * フィールドの説明: [importを指定します。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[importList]から取得した値。
     */
    public List<String> getImportList() {
        return fImportList;
    }

    /**
     * フィールド [access] の値を設定します。
     *
     * フィールドの説明: [クラスのアクセス。通常は public。]。
     *
     * @param argAccess フィールド[access]に設定する値。
     */
    public void setAccess(final String argAccess) {
        fAccess = argAccess;
    }

    /**
     * フィールド [access] の値を取得します。
     *
     * フィールドの説明: [クラスのアクセス。通常は public。]。
     * デフォルト: [&quot;public&quot;]。
     *
     * @return フィールド[access]から取得した値。
     */
    public String getAccess() {
        return fAccess;
    }

    /**
     * フィールド [data] の値を設定します。
     *
     * フィールドの説明: [dataクラスかどうか。]。
     *
     * @param argData フィールド[data]に設定する値。
     */
    public void setData(final Boolean argData) {
        fData = argData;
    }

    /**
     * フィールド [data] の値を取得します。
     *
     * フィールドの説明: [dataクラスかどうか。]。
     * デフォルト: [false]。
     *
     * @return フィールド[data]から取得した値。
     */
    public Boolean getData() {
        return fData;
    }

    /**
     * フィールド [final] の値を設定します。
     *
     * フィールドの説明: [クラスが拡張可能かどうか。kotlin では通常は true。]。
     *
     * @param argFinal フィールド[final]に設定する値。
     */
    public void setFinal(final Boolean argFinal) {
        fFinal = argFinal;
    }

    /**
     * フィールド [final] の値を取得します。
     *
     * フィールドの説明: [クラスが拡張可能かどうか。kotlin では通常は true。]。
     * デフォルト: [true]。
     *
     * @return フィールド[final]から取得した値。
     */
    public Boolean getFinal() {
        return fFinal;
    }

    /**
     * フィールド [abstract] の値を設定します。
     *
     * フィールドの説明: [抽象クラスかどうか。通常は false。]。
     *
     * @param argAbstract フィールド[abstract]に設定する値。
     */
    public void setAbstract(final Boolean argAbstract) {
        fAbstract = argAbstract;
    }

    /**
     * フィールド [abstract] の値を取得します。
     *
     * フィールドの説明: [抽象クラスかどうか。通常は false。]。
     * デフォルト: [false]。
     *
     * @return フィールド[abstract]から取得した値。
     */
    public Boolean getAbstract() {
        return fAbstract;
    }

    /**
     * フィールド [generateToString] の値を設定します。
     *
     * フィールドの説明: [toStringメソッドを生成するかどうか。]。
     *
     * @param argGenerateToString フィールド[generateToString]に設定する値。
     */
    public void setGenerateToString(final Boolean argGenerateToString) {
        fGenerateToString = argGenerateToString;
    }

    /**
     * フィールド [generateToString] の値を取得します。
     *
     * フィールドの説明: [toStringメソッドを生成するかどうか。]。
     * デフォルト: [false]。
     *
     * @return フィールド[generateToString]から取得した値。
     */
    public Boolean getGenerateToString() {
        return fGenerateToString;
    }

    /**
     * フィールド [adjustFieldName] の値を設定します。
     *
     * フィールドの説明: [フィールド名の名前変形をおこなうかどうか。]。
     *
     * @param argAdjustFieldName フィールド[adjustFieldName]に設定する値。
     */
    public void setAdjustFieldName(final Boolean argAdjustFieldName) {
        fAdjustFieldName = argAdjustFieldName;
    }

    /**
     * フィールド [adjustFieldName] の値を取得します。
     *
     * フィールドの説明: [フィールド名の名前変形をおこなうかどうか。]。
     * デフォルト: [true]。
     *
     * @return フィールド[adjustFieldName]から取得した値。
     */
    public Boolean getAdjustFieldName() {
        return fAdjustFieldName;
    }

    /**
     * フィールド [adjustDefaultValue] の値を設定します。
     *
     * フィールドの説明: [デフォルト値の変形をおこなうかどうか。※なるべく変形を利用しないことを推奨したい。※プログラムAPIとして生成する際には、このフィールドを明示的に設定してください。]。
     *
     * @param argAdjustDefaultValue フィールド[adjustDefaultValue]に設定する値。
     */
    public void setAdjustDefaultValue(final Boolean argAdjustDefaultValue) {
        fAdjustDefaultValue = argAdjustDefaultValue;
    }

    /**
     * フィールド [adjustDefaultValue] の値を取得します。
     *
     * フィールドの説明: [デフォルト値の変形をおこなうかどうか。※なるべく変形を利用しないことを推奨したい。※プログラムAPIとして生成する際には、このフィールドを明示的に設定してください。]。
     * デフォルト: [false]。
     *
     * @return フィールド[adjustDefaultValue]から取得した値。
     */
    public Boolean getAdjustDefaultValue() {
        return fAdjustDefaultValue;
    }

    /**
     * フィールド [extends] の値を設定します。
     *
     * フィールドの説明: [継承するクラスを指定します。]。
     *
     * @param argExtends フィールド[extends]に設定する値。
     */
    public void setExtends(final BlancoKeyGeneratorKtExtendsStructure argExtends) {
        fExtends = argExtends;
    }

    /**
     * フィールド [extends] の値を取得します。
     *
     * フィールドの説明: [継承するクラスを指定します。]。
     * デフォルト: [new blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtExtendsStructure()]。
     *
     * @return フィールド[extends]から取得した値。
     */
    public BlancoKeyGeneratorKtExtendsStructure getExtends() {
        return fExtends;
    }

    /**
     * フィールド [implementsList] の値を設定します。
     *
     * フィールドの説明: [実装するインタフェース(java.lang.String)の一覧。]。
     *
     * @param argImplementsList フィールド[implementsList]に設定する値。
     */
    public void setImplementsList(final List<String> argImplementsList) {
        fImplementsList = argImplementsList;
    }

    /**
     * フィールド [implementsList] の値を取得します。
     *
     * フィールドの説明: [実装するインタフェース(java.lang.String)の一覧。]。
     * デフォルト: [new java.util.ArrayList&lt;java.lang.String&gt;()]。
     *
     * @return フィールド[implementsList]から取得した値。
     */
    public List<String> getImplementsList() {
        return fImplementsList;
    }

    /**
     * フィールド [fieldList] の値を設定します。
     *
     * フィールドの説明: [フィールドを記憶するリストを指定します。]。
     *
     * @param argFieldList フィールド[fieldList]に設定する値。
     */
    public void setFieldList(final List<BlancoKeyGeneratorKtFieldStructure> argFieldList) {
        fFieldList = argFieldList;
    }

    /**
     * フィールド [fieldList] の値を取得します。
     *
     * フィールドの説明: [フィールドを記憶するリストを指定します。]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtFieldStructure&gt;()]。
     *
     * @return フィールド[fieldList]から取得した値。
     */
    public List<BlancoKeyGeneratorKtFieldStructure> getFieldList() {
        return fFieldList;
    }

    /**
     * フィールド [keyList] の値を設定します。
     *
     * フィールドの説明: [キーを記憶するリストを指定します。]。
     *
     * @param argKeyList フィールド[keyList]に設定する値。
     */
    public void setKeyList(final List<BlancoKeyGeneratorKtKeyStructure> argKeyList) {
        fKeyList = argKeyList;
    }

    /**
     * フィールド [keyList] の値を取得します。
     *
     * フィールドの説明: [キーを記憶するリストを指定します。]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtKeyStructure&gt;()]。
     *
     * @return フィールド[keyList]から取得した値。
     */
    public List<BlancoKeyGeneratorKtKeyStructure> getKeyList() {
        return fKeyList;
    }

    /**
     * フィールド [delegateList] の値を設定します。
     *
     * フィールドの説明: [委譲を記憶するリストを指定します。]。
     *
     * @param argDelegateList フィールド[delegateList]に設定する値。
     */
    public void setDelegateList(final List<BlancoKeyGeneratorKtDelegateStructure> argDelegateList) {
        fDelegateList = argDelegateList;
    }

    /**
     * フィールド [delegateList] の値を取得します。
     *
     * フィールドの説明: [委譲を記憶するリストを指定します。]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtDelegateStructure&gt;()]。
     *
     * @return フィールド[delegateList]から取得した値。
     */
    public List<BlancoKeyGeneratorKtDelegateStructure> getDelegateList() {
        return fDelegateList;
    }

    /**
     * フィールド [fileDescription] の値を設定します。
     *
     * フィールドの説明: [ファイル説明]。
     *
     * @param argFileDescription フィールド[fileDescription]に設定する値。
     */
    public void setFileDescription(final String argFileDescription) {
        fFileDescription = argFileDescription;
    }

    /**
     * フィールド [fileDescription] の値を取得します。
     *
     * フィールドの説明: [ファイル説明]。
     *
     * @return フィールド[fileDescription]から取得した値。
     */
    public String getFileDescription() {
        return fFileDescription;
    }

    /**
     * フィールド [constructorArgList] の値を設定します。
     *
     * フィールドの説明: [クラス定義に含めるコンストラクタの引数マップです。<引数名, 型> となります。]。
     *
     * @param argConstructorArgList フィールド[constructorArgList]に設定する値。
     */
    public void setConstructorArgList(final List<BlancoCgField> argConstructorArgList) {
        fConstructorArgList = argConstructorArgList;
    }

    /**
     * フィールド [constructorArgList] の値を取得します。
     *
     * フィールドの説明: [クラス定義に含めるコンストラクタの引数マップです。<引数名, 型> となります。]。
     * デフォルト: [new java.util.ArrayList&lt;blanco.cg.valueobject.BlancoCgField&gt;()]。
     *
     * @return フィールド[constructorArgList]から取得した値。
     */
    public List<BlancoCgField> getConstructorArgList() {
        return fConstructorArgList;
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
     * フィールド [bucket] の値を設定します。
     *
     * フィールドの説明: [このテーブルが配置されるバケットの情報を格納します。]。
     *
     * @param argBucket フィールド[bucket]に設定する値。
     */
    public void setBucket(final BlancoKeyGeneratorKtBucketStructure argBucket) {
        fBucket = argBucket;
    }

    /**
     * フィールド [bucket] の値を取得します。
     *
     * フィールドの説明: [このテーブルが配置されるバケットの情報を格納します。]。
     * デフォルト: [new blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtBucketStructure()]。
     *
     * @return フィールド[bucket]から取得した値。
     */
    public BlancoKeyGeneratorKtBucketStructure getBucket() {
        return fBucket;
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
        buf.append("blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtTableStructure[");
        buf.append("name=" + fName);
        buf.append(",package=" + fPackage);
        buf.append(",generic=" + fGeneric);
        buf.append(",description=" + fDescription);
        buf.append(",descriptionList=" + fDescriptionList);
        buf.append(",annotationList=" + fAnnotationList);
        buf.append(",importList=" + fImportList);
        buf.append(",access=" + fAccess);
        buf.append(",data=" + fData);
        buf.append(",final=" + fFinal);
        buf.append(",abstract=" + fAbstract);
        buf.append(",generateToString=" + fGenerateToString);
        buf.append(",adjustFieldName=" + fAdjustFieldName);
        buf.append(",adjustDefaultValue=" + fAdjustDefaultValue);
        buf.append(",extends=" + fExtends);
        buf.append(",implementsList=" + fImplementsList);
        buf.append(",fieldList=" + fFieldList);
        buf.append(",keyList=" + fKeyList);
        buf.append(",delegateList=" + fDelegateList);
        buf.append(",fileDescription=" + fFileDescription);
        buf.append(",constructorArgList=" + fConstructorArgList);
        buf.append(",packageSuffix=" + fPackageSuffix);
        buf.append(",overridePackage=" + fOverridePackage);
        buf.append(",bucket=" + fBucket);
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
    public void copyTo(final BlancoKeyGeneratorKtTableStructure target) {
        if (target == null) {
            throw new IllegalArgumentException("Bug: BlancoKeyGeneratorKtTableStructure#copyTo(target): argument 'target' is null");
        }

        // No needs to copy parent class.

        // Name: fName
        // Type: java.lang.String
        target.fName = this.fName;
        // Name: fPackage
        // Type: java.lang.String
        target.fPackage = this.fPackage;
        // Name: fGeneric
        // Type: java.lang.String
        target.fGeneric = this.fGeneric;
        // Name: fDescription
        // Type: java.lang.String
        target.fDescription = this.fDescription;
        // Name: fDescriptionList
        // Type: java.util.List
        // フィールド[fDescriptionList]はサポート外の型[java.util.Listjava.lang.String]です。
        // Name: fAnnotationList
        // Type: java.util.List
        // フィールド[fAnnotationList]はサポート外の型[java.util.Listjava.lang.String]です。
        // Name: fImportList
        // Type: java.util.List
        // フィールド[fImportList]はサポート外の型[java.util.Listjava.lang.String]です。
        // Name: fAccess
        // Type: java.lang.String
        target.fAccess = this.fAccess;
        // Name: fData
        // Type: java.lang.Boolean
        target.fData = this.fData;
        // Name: fFinal
        // Type: java.lang.Boolean
        target.fFinal = this.fFinal;
        // Name: fAbstract
        // Type: java.lang.Boolean
        target.fAbstract = this.fAbstract;
        // Name: fGenerateToString
        // Type: java.lang.Boolean
        target.fGenerateToString = this.fGenerateToString;
        // Name: fAdjustFieldName
        // Type: java.lang.Boolean
        target.fAdjustFieldName = this.fAdjustFieldName;
        // Name: fAdjustDefaultValue
        // Type: java.lang.Boolean
        target.fAdjustDefaultValue = this.fAdjustDefaultValue;
        // Name: fExtends
        // Type: blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtExtendsStructure
        // フィールド[fExtends]はサポート外の型[blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtExtendsStructure]です。
        // Name: fImplementsList
        // Type: java.util.List
        // フィールド[fImplementsList]はサポート外の型[java.util.Listjava.lang.String]です。
        // Name: fFieldList
        // Type: java.util.List
        // フィールド[fFieldList]はサポート外の型[java.util.Listblanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtFieldStructure]です。
        // Name: fKeyList
        // Type: java.util.List
        // フィールド[fKeyList]はサポート外の型[java.util.Listblanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtKeyStructure]です。
        // Name: fDelegateList
        // Type: java.util.List
        // フィールド[fDelegateList]はサポート外の型[java.util.Listblanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtDelegateStructure]です。
        // Name: fFileDescription
        // Type: java.lang.String
        target.fFileDescription = this.fFileDescription;
        // Name: fConstructorArgList
        // Type: java.util.List
        // フィールド[fConstructorArgList]はサポート外の型[java.util.Listblanco.cg.valueobject.BlancoCgField]です。
        // Name: fPackageSuffix
        // Type: java.lang.String
        target.fPackageSuffix = this.fPackageSuffix;
        // Name: fOverridePackage
        // Type: java.lang.String
        target.fOverridePackage = this.fOverridePackage;
        // Name: fBucket
        // Type: blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtBucketStructure
        // フィールド[fBucket]はサポート外の型[blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtBucketStructure]です。
    }
}
