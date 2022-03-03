/*
 * blanco Framework
 * Copyright (C) 2004-2008 IGA Tosiki
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.keygeneratorkt;

import blanco.cg.BlancoCgSupportedLang;
import blanco.commons.util.BlancoNameUtil;
import blanco.commons.util.BlancoStringUtil;
import blanco.keygeneratorkt.message.BlancoKeyGeneratorKtMessage;
import blanco.keygeneratorkt.resourcebundle.BlancoKeyGeneratorKtResourceBundle;
import blanco.keygeneratorkt.valueobject.*;
import blanco.xml.bind.BlancoXmlBindingUtil;
import blanco.xml.bind.BlancoXmlUnmarshaller;
import blanco.xml.bind.valueobject.BlancoXmlAttribute;
import blanco.xml.bind.valueobject.BlancoXmlDocument;
import blanco.xml.bind.valueobject.BlancoXmlElement;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * A class that parses (reads and writes) the intermediate XML file format of a blancoValueObject.
 *
 * @author IGA Tosiki
 * @author tueda
 */
public class BlancoKeyGeneratorKtTableXmlParser {

    /** Item separtor of key definition. */
    private final String PHRASE_SEPARATOR = "\\+";
    /** キー定義の項目属性部開始 */
    private final String PHRASE_ATTR_START = "[";
    /** キー定義の項目属性部終了 */
    private final String PHRASE_ATTR_END = "]";
    private final List<String> keyTypes = new ArrayList<>(
            Arrays.asList(
                    "raw",
                    "b64",
                    "hex",
                    "bin",
                    "sha1",
                    "sha256",
                    "json"
            )
    );

    /**
     * messages.
     */
    private final BlancoKeyGeneratorKtMessage fMsg = new BlancoKeyGeneratorKtMessage();

    private final BlancoKeyGeneratorKtResourceBundle fBundle = new BlancoKeyGeneratorKtResourceBundle();

    private boolean fVerbose = false;
    public void setVerbose(boolean argVerbose) {
        this.fVerbose = argVerbose;
    }
    public boolean isVerbose() {
        return fVerbose;
    }

    /*
     * Settings for overriding package names.
     */
    private String fPackageSuffix = "";
    public void setPackageSuffix(String suffix) {
        this.fPackageSuffix = suffix;
    }
    public String getPackageSuffix() {
        return this.fPackageSuffix;
    }
    private String fOverridePackage = "";
    public void setOverridePackage(String overridePackage) {
        this.fOverridePackage = overridePackage;
    }
    public String getOverridePackage() {
        return this.fOverridePackage;
    }

    /**
     * Parses an XML document in an intermediate XML file to get an array of value object information.
     *
     * @param argMetaXmlSourceFile
     *            An intermediate XML file.
     * @return An array of information obtained as a result of parsing.
     */
    public List<BlancoKeyGeneratorKtTableStructure> parse(
            final File argMetaXmlSourceFile,
            final BlancoKeyGeneratorKtBucketListStructure argBucketListStructure
    ) {
        final BlancoXmlDocument documentMeta = new BlancoXmlUnmarshaller()
                .unmarshal(argMetaXmlSourceFile);
        if (documentMeta == null) {
            return null;
        }

        System.out.println("[blancoKeyGeneratorKt: Processes " + argMetaXmlSourceFile.getName() + ".]");

        return parse(documentMeta, argBucketListStructure);

    }

    /**
     * Parses an XML document in an intermediate XML file to get an array of value object information.
     *
     * @param argXmlDocument
     *            XML document of an intermediate XML file.
     * @return An array of value object information obtained as a result of parsing.
     */
    public List<BlancoKeyGeneratorKtTableStructure> parse(
            final BlancoXmlDocument argXmlDocument,
            final BlancoKeyGeneratorKtBucketListStructure argBucketListStructure
    ) {
        final List<BlancoKeyGeneratorKtTableStructure> listStructure = new ArrayList<BlancoKeyGeneratorKtTableStructure>();

        // Gets the root element.
        final BlancoXmlElement elementRoot = BlancoXmlBindingUtil
                .getDocumentElement(argXmlDocument);
        if (elementRoot == null) {
            // The process is aborted if there is no root element.
            return null;
        }

        // Gets a list of sheets (Excel sheets).
        final List<BlancoXmlElement> listSheet = BlancoXmlBindingUtil
                .getElementsByTagName(elementRoot, "sheet");

        final int sizeListSheet = listSheet.size();
        for (int index = 0; index < sizeListSheet; index++) {
            final BlancoXmlElement elementSheet = listSheet.get(index);

            /*
             * BlancoKeyGenerator is just support PHP style sheet.
             */
            List<BlancoXmlElement> listCommon = BlancoXmlBindingUtil
                    .getElementsByTagName(elementSheet,
                            fBundle.getMeta2xmlElementCommon());
            int sheetLang = BlancoCgSupportedLang.PHP;
            if (listCommon.size() != 0) {
                BlancoXmlAttribute attr = new BlancoXmlAttribute();
                attr.setType("CDATA");
                attr.setQName("style");
                attr.setLocalName("style");

                attr.setValue(new BlancoCgSupportedLang().convertToString(sheetLang));

                elementSheet.getAtts().add(attr);

                /* tueda DEBUG */
//                    if (this.isVerbose()) {
//                        System.out.println("/* tueda */ style = " + BlancoXmlBindingUtil.getAttribute(elementSheet, "style"));
//                    }
            } else {
                // Skips if there is no common.
                continue;
            }

            // Processes only the first item.
            final BlancoXmlElement elementCommon = listCommon.get(0);
            final String name = BlancoXmlBindingUtil.getTextContent(
                    elementCommon, "name");
            if (BlancoStringUtil.null2Blank(name).trim().length() == 0) {
                continue;
            }

            BlancoKeyGeneratorKtTableStructure objTableStructure = parseElementSheetPhp(elementSheet, argBucketListStructure, BlancoKeyGeneratorKtUtil.packageMap);

            if (objTableStructure != null) {
                // Saves the obtained information.
                listStructure.add(objTableStructure);
            }
        }

        return listStructure;
    }

    /**
     * Parses the "sheet" XML element (PHP format) in the intermediate XML file to get the key information.
     *
     * @param argElementSheet
     *            "sheet" XML element in the intermediate XML file.
     * @param argBucketListStructure
     * @return Value object information obtained as a result of parsing. Null is returned if "name" is not found.
     */
    public BlancoKeyGeneratorKtTableStructure parseElementSheetPhp(
            final BlancoXmlElement argElementSheet,
            BlancoKeyGeneratorKtBucketListStructure argBucketListStructure, final Map<String, String> argClassList
    ) {
        final BlancoKeyGeneratorKtTableStructure objTableStructure = new BlancoKeyGeneratorKtTableStructure();
        final List<BlancoXmlElement> listCommon = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        fBundle.getMeta2xmlElementCommon());
        if (listCommon == null || listCommon.size() == 0) {
            // Skips if there is no common.
            return null;
        }

        if (argClassList == null) {
            // Also skips if there is no classList.
            System.out.println("### ERROR ### NO CLASS LIST DEFINED.");
            return null;
        }

        // Sets the option to replace the package name if available.
        objTableStructure.setPackageSuffix(this.fPackageSuffix);
        objTableStructure.setOverridePackage(this.fOverridePackage);

        // keyGenerator definition (PHP) common, just use first one.
        final BlancoXmlElement elementCommon = listCommon.get(0);
        parseCommonPhp(elementCommon, objTableStructure, argBucketListStructure);
        if (BlancoStringUtil.null2Blank(objTableStructure.getName()).trim()
                .length() == 0) {
            // Skips if name is empty.
            return null;
        }

        // keyGenerator definition (PHP) inheritance, MAY NOT BE USED
        final List<BlancoXmlElement> extendsList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        "blancokeygenerator-extends");
        if (extendsList != null && extendsList.size() != 0) {
            final BlancoXmlElement elementExtendsRoot = extendsList.get(0);
            parseExtendsPhp(elementExtendsRoot, objTableStructure, argClassList);
        }

        // keyGenerator definition (Kt) delegation, MAY NOT BE USED
        final List<BlancoXmlElement> delegateList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        "blancokeygenerator-delegate");
        if (delegateList != null && delegateList.size() != 0) {
            final BlancoXmlElement elementDelegateRoot = delegateList.get(0);
            parseDelegateList(elementDelegateRoot, objTableStructure);
        }

        // keyGenerator definition (PHP) implementation, MAY NOT BE USED
        final List<BlancoXmlElement> interfaceList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        "blancokeygenerator-implements");
        if (interfaceList != null && interfaceList.size() != 0) {
            final BlancoXmlElement elementInterfaceRoot = interfaceList.get(0);

            parseInterfacePhp(elementInterfaceRoot, objTableStructure, argClassList);
        }

        // Creates import list.
        final List<BlancoXmlElement> importList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet, "blancokeygenerator-import");
        if (importList != null && importList.size() != 0) {
            final BlancoXmlElement elementImportRoot = importList.get(0);
            parseImportListPhp(elementImportRoot, objTableStructure);
        }

        // keyGenerator definition (PHP) item list
        final List<BlancoXmlElement> listList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet, "blancokeygenerator-itemlist");
        if (listList != null && listList.size() != 0) {
            final BlancoXmlElement elementListRoot = listList.get(0);
            parseItemList(elementListRoot, objTableStructure, argClassList);
        }

        // keyGenerator definition (PHP)
        final List<BlancoXmlElement> keyList = BlancoXmlBindingUtil.getElementsByTagName(argElementSheet, "blancokeygenerator-keylist");
        if (keyList != null && keyList.size() != 0) {
            final BlancoXmlElement elementListRoot = keyList.get(0);
            parseKeyList(elementListRoot, objTableStructure, argClassList);
        } else {
            throw new IllegalArgumentException(fMsg.getMbkgji14(objTableStructure.getName()));
        }

        return objTableStructure;
    }

    private List<String> createAnnotaionList(String annotations) {
        List<String> annotationList = new ArrayList<>();
        final String[] lines = BlancoNameUtil.splitString(annotations, '\n');
        StringBuffer sb = new StringBuffer();
        for (String line : lines) {
            if (line.startsWith("@")) {
                if (sb.length() > 0) {
                    annotationList.add(sb.toString());
                    sb = new StringBuffer();
                }
                line = line.substring(1);
            }
            sb.append(line + System.getProperty("line.separator", "\n"));
        }
        if (sb.length() > 0) {
            annotationList.add(sb.toString());
        }
//        if (this.isVerbose()) {
//            for (String ann : annotationList) {
//                System.out.println("Ann: " + ann);
//            }
//        }
        return annotationList;
    }

    private String parsePhpTypes(String phpType, final Map<String, String> argClassList, boolean isGeneric) {
        String kotlinType = phpType;
        if (BlancoStringUtil.null2Blank(phpType).length() != 0) {
            if ("boolean".equalsIgnoreCase(phpType)) {
                kotlinType = "kotlin.Boolean";
            } else
            if ("integer".equalsIgnoreCase(phpType)) {
                // Converts integer types to 64 bit
                kotlinType = "kotlin.Long";
            } else
            if ("double".equalsIgnoreCase(phpType)) {
                kotlinType = "kotlin.Double";
            } else
            if ("float".equalsIgnoreCase(phpType)) {
                kotlinType = "kotlin.Double";
            } else
            if ("string".equalsIgnoreCase(phpType)) {
                kotlinType = "kotlin.String";
            } else
            if ("datetime".equalsIgnoreCase(phpType)) {
                kotlinType = "java.util.Date";
            } else
            if ("array".equalsIgnoreCase(phpType)) {
                if (isGeneric) {
                    throw new IllegalArgumentException("Cannot use array for Generics.");
                } else {
                    kotlinType = "kotlin.collections.ArrayList";
                }
            } else
            if ("object".equalsIgnoreCase(phpType)) {
                kotlinType = "kotlin.Any";
            } else {
                /* Searches for a package with this name. */
                String packageName = argClassList.get(phpType);
                if (packageName != null) {
                    kotlinType = packageName + "." + phpType;
                }

                /* Others are written as is. */
                System.out.println("Unknown php type: " + kotlinType);
            }
        }
        return kotlinType;
    }

    /**
     * keyGenerator definition (PHP) common
     * @param argElementCommon
     * @param argTableStructure
     * @param argBucketListStructure
     */
    private void parseCommonPhp(
            final BlancoXmlElement argElementCommon,
            final BlancoKeyGeneratorKtTableStructure argTableStructure,
            BlancoKeyGeneratorKtBucketListStructure argBucketListStructure
    ) {
        argTableStructure.setName(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "name"));
        argTableStructure.setPackage(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "package"));
        argTableStructure.setDisplayName(BlancoXmlBindingUtil.getTextContent(argElementCommon, "displayName"));

        String bucketId = BlancoXmlBindingUtil.getTextContent(argElementCommon, "bucket");
        if (BlancoStringUtil.null2Blank(bucketId).length() == 0) {
            throw new IllegalArgumentException(fMsg.getMbkgji20(argTableStructure.getName()));
        }
        BlancoKeyGeneratorKtBucketStructure bucketStructure = getBucket(bucketId, argBucketListStructure);
        if (bucketStructure == null) {
            throw new IllegalArgumentException(fMsg.getMbkgji21(argTableStructure.getName(), bucketId));
        }
        argTableStructure.setBucket(bucketStructure);

        /*
         * calculate table number range.
         */
        Integer figureNum = bucketStructure.getTableIdLength();


        String strTableNumber = BlancoXmlBindingUtil.getTextContent(argElementCommon, "tableNumber");
        if (BlancoStringUtil.null2Blank(strTableNumber).length() == 0) {
            throw new IllegalArgumentException(fMsg.getMbkgji22(argTableStructure.getName()));
        }

        Integer maxTableNumber = ((Double)Math.pow(2, 6 * bucketStructure.getTableIdLength())).intValue();
        Integer tableNumber = 0;
        try {
            tableNumber = Integer.parseInt(strTableNumber);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(fMsg.getMbkgji25(argTableStructure.getName(), "" + maxTableNumber));
        }
        if (tableNumber >= maxTableNumber) {
            throw new IllegalArgumentException(fMsg.getMbkgji25(argTableStructure.getName(), "" + maxTableNumber));
        }
        argTableStructure.setTableNumber(tableNumber);

        BlancoKeyGeneratorKtTableStructure dupTable = bucketStructure.getTableMap().get(argTableStructure.getTableNumber());
        if (dupTable != null) {
            throw new IllegalArgumentException(fMsg.getMbkgji23(argTableStructure.getName(), "" + argTableStructure.getTableNumber(), bucketStructure.getName(), dupTable.getName()));
        }
        bucketStructure.getTableMap().put(argTableStructure.getTableNumber(), argTableStructure);

        argTableStructure.setDescription(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "description"));
        if (BlancoStringUtil.null2Blank(argTableStructure.getDescription())
                .length() > 0) {
            final String[] lines = BlancoNameUtil.splitString(argTableStructure
                    .getDescription(), '\n');
            for (int index = 0; index < lines.length; index++) {
                if (index == 0) {
                    argTableStructure.setDescription(lines[index]);
                } else {
                    // For a multi-line description, it will be split and stored.
                    // From the second line, assumes that character reference encoding has been properly implemented.
                    argTableStructure.getDescriptionList().add(lines[index]);
                }
            }
        }

        /* Supports generic types of the class. */
        String classGenerics = BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "generic");
        if (BlancoStringUtil.null2Blank(classGenerics).length() > 0) {
            argTableStructure.setGeneric(classGenerics);
        }


        /* Supports annotation of class. (Kt), if any, takes precedence. */
        String classAnnotation = BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "annotationKt");
        if (BlancoStringUtil.null2Blank(classAnnotation).length() == 0) {
            classAnnotation = BlancoXmlBindingUtil.getTextContent(
                    argElementCommon, "annotation");
        }
        if (BlancoStringUtil.null2Blank(classAnnotation).length() > 0) {
            argTableStructure.setAnnotationList(createAnnotaionList(classAnnotation));
        }

        argTableStructure.setAccess("public");
        argTableStructure.setFinal(true);
        argTableStructure.setAbstract(false);
        argTableStructure.setData(false);
        argTableStructure.setGenerateToString(false);
        argTableStructure.setAdjustFieldName(true);
        argTableStructure.setAdjustDefaultValue(false);
        argTableStructure.setFieldList(new ArrayList<>());
        argTableStructure.setKeyList(new ArrayList<>());
        argTableStructure.setDelegateList(new ArrayList<>());
        argTableStructure.setFileDescription(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "fileDescription"));

        String strKeyIdLength = BlancoXmlBindingUtil.getTextContent(argElementCommon, "keyIdLength");
        Integer keyIdLength = 2;
        if (BlancoStringUtil.null2Blank(strKeyIdLength).length() != 0) {
            try {
                keyIdLength = Integer.parseInt(strKeyIdLength);
                argTableStructure.setKeyIdLength(keyIdLength);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(fMsg.getMbkgji10(argTableStructure.getName()));
            }
        }

        String strMaxKeyLength = BlancoXmlBindingUtil.getTextContent(argElementCommon, "maxKeyLength");
        Integer maxKeyLength = 900;
        if (BlancoStringUtil.null2Blank(strMaxKeyLength).length() != 0) {
            try {
                maxKeyLength = Integer.parseInt(strMaxKeyLength);
                argTableStructure.setMaxKeyLength(maxKeyLength);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(fMsg.getMbkgji11(argTableStructure.getName()));
            }
        }

        String strRecordSequenceLength = BlancoXmlBindingUtil.getTextContent(argElementCommon, "recordSequenceLength");
        Integer recordSequenceLength = 2;
        if (BlancoStringUtil.null2Blank(strRecordSequenceLength).length() != 0) {
            try {
                recordSequenceLength = Integer.parseInt(strRecordSequenceLength);
                argTableStructure.setRecordSequenceLength(recordSequenceLength);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(fMsg.getMbkgji12(argTableStructure.getName()));
            }
        }

        String strTableVersion = BlancoXmlBindingUtil.getTextContent(argElementCommon, "tableVersion");
        Integer tableVersion = 0;
        if (BlancoStringUtil.null2Blank(strTableVersion).length() != 0) {
            try {
                tableVersion = Integer.parseInt(strTableVersion);
                argTableStructure.setTableVersion(tableVersion);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException(fMsg.getMbkgji13(argTableStructure.getName()));
            }
        }

        if (argTableStructure.getPackage() == null) {
            throw new IllegalArgumentException(fMsg
                    .getMbkgji01(argTableStructure.getName()));
        }
    }

    /**
     * Value object definition (PHP) inheritance<br>
     * <br>
     * If packageSuffix or overridePackage is specified, searches for tmp and give priority to it if found.
     * @param argElementExtendsRoot
     * @param argClassStructure
     * @param argClassList
     */
    private void parseExtendsPhp(
            final BlancoXmlElement argElementExtendsRoot,
            final BlancoKeyGeneratorKtTableStructure argClassStructure,
            final Map<String, String> argClassList
    ) {
        String className = BlancoXmlBindingUtil.getTextContent(argElementExtendsRoot, "name");
        if (BlancoStringUtil.null2Blank(className).length() > 0) {
            String packageName = BlancoXmlBindingUtil.getTextContent(argElementExtendsRoot, "package");
            String generics = BlancoXmlBindingUtil.getTextContent(argElementExtendsRoot, "generic");
            if (packageName == null ||
                    (this.fPackageSuffix != null && this.fPackageSuffix.length() > 0) ||
                    (this.fOverridePackage != null && this.fOverridePackage.length() > 0)) {
                /*
                 * Searches for the package name of this class
                 */
                packageName = argClassList.get(className);
            }
            if (packageName != null) {
                className = packageName + "." + className;
                if (isVerbose()) {
                    System.out.println("Extends = " + className);
                }
            }
            BlancoKeyGeneratorKtExtendsStructure extendsStructure = new BlancoKeyGeneratorKtExtendsStructure();
            argClassStructure.setExtends(extendsStructure);
            extendsStructure.setType(className);
            if (BlancoStringUtil.null2Blank(generics).length() > 0) {
                extendsStructure.setGenerics(generics);
            }
        } else if (isVerbose()) {
            System.out.println("parseExtendsPhp: extends type is not specified. SKIPPED.");
        }
    }

    /**
     * Value object definition (PHP) implementation<br>
     * <br>
     * If packageSuffix or overridePackage is specified, searches for tmp and give priority to it if found.
     * @param argElementInterfaceRoot
     * @param argClassStructure
     * @param argClassList
     */
    private void parseInterfacePhp(
            final BlancoXmlElement argElementInterfaceRoot,
            final BlancoKeyGeneratorKtTableStructure argClassStructure,
            final Map<String, String> argClassList) {
        final List<BlancoXmlElement> listInterfaceChildNodes = BlancoXmlBindingUtil
                .getElementsByTagName(argElementInterfaceRoot, "import");
        for (int index = 0; index < listInterfaceChildNodes.size(); index++) {
            final BlancoXmlElement elementList = listInterfaceChildNodes
                    .get(index);

            String interfaceName = BlancoXmlBindingUtil
                    .getTextContent(elementList, "name");
            if (interfaceName == null || interfaceName.trim().length() == 0) {
                continue;
            }
            String interfacePackage = BlancoKeyGeneratorKtUtil.getPackageName(interfaceName);
            String interfaceSimple = BlancoKeyGeneratorKtUtil.getSimpleClassName(interfaceName);
            if (interfacePackage.length() == 0 ||
                    (this.fPackageSuffix != null && this.fPackageSuffix.length() > 0) ||
                    (this.fOverridePackage != null && this.fOverridePackage.length() > 0)) {
                // If this interface is auto-generated, gives priority to it.
                interfacePackage = argClassList.get(interfaceSimple);
                if (interfacePackage != null && interfacePackage.length() >0) {
                    interfaceName = interfacePackage + "." + interfaceSimple;
                }
            }
            if (isVerbose()) {
                System.out.println("Implements = " + interfaceName);
            }
            argClassStructure.getImplementsList().add(interfaceName);
        }
    }

    /**
     * Creates a list of import.
     * @param argElementImportRoot
     * @param argClassStructure
     */
    private void parseImportListPhp(
            final BlancoXmlElement argElementImportRoot,
            final BlancoKeyGeneratorKtTableStructure argClassStructure
    ) {
        final List<BlancoXmlElement> listImportChildNodes = BlancoXmlBindingUtil
                .getElementsByTagName(argElementImportRoot, "import");
        for (int index = 0; index < listImportChildNodes.size(); index++) {
            final BlancoXmlElement elementList = listImportChildNodes
                    .get(index);

            final String importName = BlancoXmlBindingUtil
                    .getTextContent(elementList, "name");
//            System.out.println("/* tueda */ import = " + importName);
            if (importName == null || importName.trim().length() == 0) {
                continue;
            }
            argClassStructure.getImportList().add(
                    BlancoXmlBindingUtil
                            .getTextContent(elementList, "name"));
        }
    }

    /**
     * Value object definition (Kt) delegation
     * @param argElementListRoot
     * @param argClassStructure
     */
    private void parseDelegateList(
            final BlancoXmlElement argElementListRoot,
            final BlancoKeyGeneratorKtTableStructure argClassStructure
    ) {

        final List<BlancoXmlElement> listChildNodes = BlancoXmlBindingUtil
                .getElementsByTagName(argElementListRoot, "delegate");
        for (int index = 0; index < listChildNodes.size(); index++) {
            final BlancoXmlElement elementList = listChildNodes.get(index);
            final BlancoKeyGeneratorKtDelegateStructure delegateStructure = new BlancoKeyGeneratorKtDelegateStructure();

            delegateStructure.setNo(BlancoXmlBindingUtil.getTextContent(
                    elementList, "no"));
            delegateStructure.setName(BlancoXmlBindingUtil.getTextContent(
                    elementList, "name"));
            if (delegateStructure.getName() == null
                    || delegateStructure.getName().trim().length() == 0) {
                continue;
            }

            /*
             * Delegate is only supported in Kotlin, so the type name is assumed to be defined in Kotlin style.
             */
            delegateStructure.setType(BlancoXmlBindingUtil.getTextContent(elementList, "type"));

            if (delegateStructure.getType() == null || delegateStructure.getType().length() == 0) {
                throw new IllegalArgumentException(BlancoKeyGeneratorKtUtil.fBundle.getXml2sourceFileErr007(
                        argClassStructure.getName(),
                        delegateStructure.getName()
                ));
            }
            /* Supports Kotlin Generic. */
            delegateStructure.setGeneric(BlancoXmlBindingUtil.getTextContent(elementList, "generic"));

            // Description
            delegateStructure.setDescription(BlancoXmlBindingUtil
                    .getTextContent(elementList, "description"));
            final String[] lines = BlancoNameUtil.splitString(
                    delegateStructure.getDescription(), '\n');
            for (int indexLine = 0; indexLine < lines.length; indexLine++) {
                if (indexLine == 0) {
                    delegateStructure.setDescription(lines[indexLine]);
                } else {
                    // For a multi-line description, it will be split and stored.
                    // From the second line, assumes that character reference encoding has been properly implemented.
                    delegateStructure.getDescriptionList().add(
                            lines[indexLine]);
                }
            }
            argClassStructure.getDelegateList().add(delegateStructure);
        }
    }

    /**
     * keyGenerator definition (PHP) item list
     * @param argElementListRoot
     * @param argClassStructure
     * @param argClassList
     */
    private void parseItemList(
            final BlancoXmlElement argElementListRoot,
            final BlancoKeyGeneratorKtTableStructure argClassStructure,
            final Map<String, String> argClassList
    ) {

        final List<BlancoXmlElement> listChildNodes = BlancoXmlBindingUtil
                .getElementsByTagName(argElementListRoot, "field");
        for (int index = 0; index < listChildNodes.size(); index++) {
            final BlancoXmlElement elementList = listChildNodes.get(index);
            final BlancoKeyGeneratorKtFieldStructure fieldStructure = new BlancoKeyGeneratorKtFieldStructure();

            fieldStructure.setNo(BlancoXmlBindingUtil.getTextContent(
                    elementList, "no"));
            fieldStructure.setName(BlancoXmlBindingUtil.getTextContent(
                    elementList, "name"));
            if (fieldStructure.getName() == null
                    || fieldStructure.getName().trim().length() == 0) {
                continue;
            }

            /*
             * Gets the type. Changes the type name to Kotlin style here.
             */
            String phpType = BlancoXmlBindingUtil.getTextContent(elementList, "type");
            if (BlancoStringUtil.null2Blank(phpType).length() == 0) {
                // Type is required.
                throw new IllegalArgumentException(fMsg.getMbkgji04(
                        argClassStructure.getName(),
                        fieldStructure.getName()
                ));

            }
            String kotlinType = parsePhpTypes(phpType, argClassList, false);
            fieldStructure.setType(kotlinType);

            /* Supports Generic. */
            String phpGeneric = BlancoXmlBindingUtil.getTextContent(elementList, "generic");
            if (BlancoStringUtil.null2Blank(phpGeneric).length() != 0) {
                String kotlinGeneric = parsePhpTypes(phpGeneric, argClassList, true);
                fieldStructure.setGeneric(kotlinGeneric);
            }

            /* Supports method annnotation. */
            String methodAnnotation = BlancoXmlBindingUtil.getTextContent(elementList, "annotation");
            if (BlancoStringUtil.null2Blank(methodAnnotation).length() != 0) {
                fieldStructure.setAnnotationList(createAnnotaionList(methodAnnotation));
            }

            /*
             * Obtains types in Kotlin. The type name is assumed to be defined in Kotlin style.
             */
            fieldStructure.setTypeKt(BlancoXmlBindingUtil.getTextContent(elementList, "typeKt"));

            /* Supports Kotlin Generic. */
            fieldStructure.setGenericKt(BlancoXmlBindingUtil.getTextContent(elementList, "genericKt"));

            /* Supports Kotlin annnotation. */
            String methodAnnotationKt = BlancoXmlBindingUtil.getTextContent(elementList, "annotationKt");
            if (BlancoStringUtil.null2Blank(methodAnnotationKt).length() != 0) {
                fieldStructure.setAnnotationList(createAnnotaionList(methodAnnotationKt));
            }

            // Supports required. (Giving NotNull annotation)
            String requiredKt = BlancoXmlBindingUtil
                    .getTextContent(elementList, "requiredKt");
            String required = BlancoXmlBindingUtil
                    .getTextContent(elementList, "required");
            if (BlancoStringUtil.null2Blank(requiredKt).length() > 0) {
                if ("true".equals(requiredKt)) {
                    required = requiredKt;
                } else if ("not".equals(requiredKt) &&
                        BlancoStringUtil.null2Blank(required).length() > 0) {
                    required = ""; // Ignores required if requiredKt is "not".
                }
            }
            fieldStructure.setRequired("true".equals(required));
            if (fieldStructure.getRequired()) {
                fieldStructure.getAnnotationList().add("field:NotNull");
                argClassStructure.getImportList().add("javax.validation.constraints.NotNull");
            }

            // Supports NotNullable.
            String notNullable = BlancoXmlBindingUtil
                    .getTextContent(elementList, "notNullable");
            if (BlancoStringUtil.null2Blank(notNullable).length() > 0) {
                fieldStructure.setNotNullable("true".equals(notNullable));
            }

            // Supports constructorArg.
            fieldStructure.setConstArg("true".equals(BlancoXmlBindingUtil
                    .getTextContent(elementList, "constructorArg")));

            fieldStructure.setDescription(BlancoXmlBindingUtil
                    .getTextContent(elementList, "description"));
            final String[] lines = BlancoNameUtil.splitString(
                    fieldStructure.getDescription(), '\n');
            for (int indexLine = 0; indexLine < lines.length; indexLine++) {
                if (indexLine == 0) {
                    fieldStructure.setDescription(lines[indexLine]);
                } else {
                    // For a multi-line description, it will be split and stored.
                    // From the second line, assumes that character reference encoding has been properly implemented.
                    fieldStructure.getDescriptionList().add(
                            lines[indexLine]);
                }
            }

            fieldStructure.setDefault(BlancoXmlBindingUtil.getTextContent(
                    elementList, "default"));
            fieldStructure.setDefaultKt(BlancoXmlBindingUtil.getTextContent(
                    elementList, "defaultKt"));

            fieldStructure.setMinLength(BlancoXmlBindingUtil
                    .getTextContent(elementList, "minLength"));
            fieldStructure.setMaxLength(BlancoXmlBindingUtil
                    .getTextContent(elementList, "maxLength"));
            fieldStructure.setLength(BlancoXmlBindingUtil.getTextContent(
                    elementList, "length"));
            fieldStructure.setMinInclusive(BlancoXmlBindingUtil
                    .getTextContent(elementList, "minInclusive"));
            fieldStructure.setMaxInclusive(BlancoXmlBindingUtil
                    .getTextContent(elementList, "maxInclusive"));
            fieldStructure.setPattern(BlancoXmlBindingUtil.getTextContent(
                    elementList, "pattern"));

            if (fieldStructure.getType() == null
                    || fieldStructure.getType().trim().length() == 0) {
                throw new IllegalArgumentException(fMsg.getMbkgji02(
                        argClassStructure.getName(), fieldStructure
                                .getName()));
            }

            /* Supports non-final (default final in Kotlin). */
            fieldStructure.setOpen("true".equals(BlancoXmlBindingUtil
                    .getTextContent(elementList, "open")));

            argClassStructure.getFieldList().add(fieldStructure);
        }
    }

    /**
     * keyGenerator definition (PHP) key list
     * @param argElementListRoot
     * @param argTableStructure
     * @param argClassList
     */
    private void parseKeyList(
            final BlancoXmlElement argElementListRoot,
            final BlancoKeyGeneratorKtTableStructure argTableStructure,
            final Map<String, String> argClassList
    ) {

        final List<BlancoXmlElement> listChildNodes = BlancoXmlBindingUtil
                .getElementsByTagName(argElementListRoot, "key");
        for (int index = 0; index < listChildNodes.size(); index++) {
            final BlancoXmlElement elementList = listChildNodes.get(index);
            final BlancoKeyGeneratorKtKeyStructure keyStructure = new BlancoKeyGeneratorKtKeyStructure();

            keyStructure.setNo(BlancoXmlBindingUtil.getTextContent(
                    elementList, "no"));
            keyStructure.setName(BlancoXmlBindingUtil.getTextContent(
                    elementList, "name"));
            if (keyStructure.getName() == null
                    || keyStructure.getName().trim().length() == 0) {
                continue;
            }

            /*
             * Get keyPart.
             */
            String keyPart = BlancoXmlBindingUtil.getTextContent(elementList, "keyPart");
            if (BlancoStringUtil.null2Blank(keyPart).length() == 0) {
                throw new IllegalArgumentException(fMsg.getMbkgji15(argTableStructure.getName(), keyStructure.getName()));
            }
            List<BlancoKeyGeneratorKtKeyPhrase> keyPartList = parseKeyPhrase(keyPart, argTableStructure, keyStructure);
            if (keyPartList.size() == 0) {
                throw new IllegalArgumentException(fMsg.getMbkgji15(argTableStructure.getName(), keyStructure.getName()));
            }
            keyStructure.setKeyPart(keyPartList);

            /*
             * Get valuePart
             */
            String valuePart = BlancoXmlBindingUtil.getTextContent(elementList, "valuePart");
            List<BlancoKeyGeneratorKtKeyPhrase> valuePartList = parseKeyPhrase(valuePart, argTableStructure, keyStructure);
            if (valuePartList.size() != 0) {
                keyStructure.setValuePart(valuePartList);
            }

            /*
             * Check key legth
             */
            int keyLength = this.calcKeyLength(keyPartList) + this.calcKeyLength(valuePartList);
            if (keyLength > argTableStructure.getMaxKeyLength()) {
                throw new IllegalArgumentException(fMsg.getMbkgji24(argTableStructure.getName(), keyStructure.getName(), "" + argTableStructure.getMaxKeyLength()));
            }

            /*
             * Get description
             */
            keyStructure.setDescription(BlancoXmlBindingUtil
                    .getTextContent(elementList, "description"));
            final String[] lines = BlancoNameUtil.splitString(
                    keyStructure.getDescription(), '\n');
            for (int indexLine = 0; indexLine < lines.length; indexLine++) {
                if (indexLine == 0) {
                    keyStructure.setDescription(lines[indexLine]);
                } else {
                    // For a multi-line description, it will be split and stored.
                    // From the second line, assumes that character reference encoding has been properly implemented.
                    keyStructure.getDescriptionList().add(
                            lines[indexLine]);
                }
            }

            argTableStructure.getKeyList().add(keyStructure);
        }
    }

    /**
     * Parse key phrases.
     *
     * @param keyDef
     * @param argTableStructure
     * @param argKeyStructure
     * @return
     */
    private List<BlancoKeyGeneratorKtKeyPhrase> parseKeyPhrase(
            final String keyDef,
            final BlancoKeyGeneratorKtTableStructure argTableStructure,
            final BlancoKeyGeneratorKtKeyStructure argKeyStructure
    ) {
        List<BlancoKeyGeneratorKtKeyPhrase> list = new ArrayList<>();

        String [] items = keyDef.split(PHRASE_SEPARATOR);
        for (int i = 0; i < items.length; i++) {
            String itemInfo = items[i].trim();
            if (BlancoStringUtil.null2Blank(itemInfo).length() > 0) {
                int start = itemInfo.indexOf(PHRASE_ATTR_START);
                int end = itemInfo.indexOf(PHRASE_ATTR_END);
                if (start == -1 || end == -1 || start >= end) {
                    throw new IllegalArgumentException(fMsg.getMbkgji17(argTableStructure.getName(), argKeyStructure.getName(), itemInfo));
                }
                String itemName = itemInfo.substring(0, start);
                Boolean itemFound = false;
                for (BlancoKeyGeneratorKtFieldStructure field : argTableStructure.getFieldList()) {
                    if (field.getName().equals(itemName)) {
                        itemFound = true;
                        break;
                    }
                }
                if (!itemFound) {
                    throw new IllegalArgumentException(fMsg.getMbkgji16(argTableStructure.getName(), argKeyStructure.getName(), itemName));
                }
                String itemAttr = itemInfo.substring(start + 1, end);
                String [] attrs = itemAttr.split(",");
                if (attrs.length != 2) {
                    throw new IllegalArgumentException(fMsg.getMbkgji17(argTableStructure.getName(), argKeyStructure.getName(), itemInfo));
                }
                String type = attrs[0].trim();
                if (!keyTypes.contains(type)) {
                    throw new IllegalArgumentException(fMsg.getMbkgji18(argTableStructure.getName(), argKeyStructure.getName(), itemName, type));
                }
                String strLength = attrs[1].trim();
                Integer length = 0;
                try {
                    length = Integer.parseInt(strLength);
                } catch (NumberFormatException e) {
                    throw new IllegalArgumentException(fMsg.getMbkgji19(argTableStructure.getName(), argKeyStructure.getName(), itemName, strLength), e);
                }

                /*
                 * base64's one character means 6 bits data,
                 * so base64 length should be a multiple of 4
                 * to bind 8 bits boundary.
                 */
                if ("b64".equals(type) && length % 4 != 0) {
                    throw new IllegalArgumentException(fMsg.getMbkgji26(argTableStructure.getName(), argKeyStructure.getName(), itemName));
                }

                BlancoKeyGeneratorKtKeyPhrase keyPhrase = new BlancoKeyGeneratorKtKeyPhrase();
                list.add(keyPhrase);
                keyPhrase.setName(itemName);
                keyPhrase.setType(type);
                keyPhrase.setLength(length);
            }
        }

        return list;
    }

    /**
     * retrieve bucketStructure from bucketList.
     * if bucketId never matched, return null;
     *
     * @param argBucketId
     * @param bucketListStructure
     * @return
     */
    private BlancoKeyGeneratorKtBucketStructure getBucket(final String argBucketId, BlancoKeyGeneratorKtBucketListStructure bucketListStructure) {
        BlancoKeyGeneratorKtBucketStructure bucketStructure = null;
        for (BlancoKeyGeneratorKtBucketStructure bucket : bucketListStructure
                .getBucketList()) {
            if (argBucketId.equals(bucket.getName())) {
                bucketStructure = bucket;
                break;
            }
        }
        return bucketStructure;
    }

    /**
     * Get key length (in bytes) from keyPhraseList.
     * @param argKeyPhraseList
     * @return
     */
    private int calcKeyLength(final List<BlancoKeyGeneratorKtKeyPhrase> argKeyPhraseList) {
        int length = 0;
        for (BlancoKeyGeneratorKtKeyPhrase keyPhrase : argKeyPhraseList) {
            length += keyPhrase.getLength();
        }
        length += argKeyPhraseList.size() - 1;
        return length;
    }
}
