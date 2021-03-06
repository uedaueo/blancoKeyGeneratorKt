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
import blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtClassStructure;
import blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtDelegateStructure;
import blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtExtendsStructure;
import blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtFieldStructure;
import blanco.xml.bind.BlancoXmlBindingUtil;
import blanco.xml.bind.BlancoXmlUnmarshaller;
import blanco.xml.bind.valueobject.BlancoXmlAttribute;
import blanco.xml.bind.valueobject.BlancoXmlDocument;
import blanco.xml.bind.valueobject.BlancoXmlElement;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A class that parses (reads and writes) the intermediate XML file format of a blancoValueObject.
 *
 * @author IGA Tosiki
 */
public class BlancoKeyGeneratorKtXmlParser {
    /**
     * A message.
     */
    private final BlancoKeyGeneratorKtMessage fMsg = new BlancoKeyGeneratorKtMessage();

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
    public BlancoKeyGeneratorKtClassStructure[] parse(
            final File argMetaXmlSourceFile) {
        final BlancoXmlDocument documentMeta = new BlancoXmlUnmarshaller()
                .unmarshal(argMetaXmlSourceFile);
        if (documentMeta == null) {
            return null;
        }

        System.out.println("[blancoKeyGeneratorKt: Processes " + argMetaXmlSourceFile.getName() + ".]");

        return parse(documentMeta);

    }

    /**
     * Parses an XML document in an intermediate XML file to get an array of value object information.
     *
     * @param argXmlDocument
     *            XML document of an intermediate XML file.
     * @return An array of value object information obtained as a result of parsing.
     */
    public BlancoKeyGeneratorKtClassStructure[] parse(
            final BlancoXmlDocument argXmlDocument) {
        final List<BlancoKeyGeneratorKtClassStructure> listStructure = new ArrayList<BlancoKeyGeneratorKtClassStructure>();

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
             * Supports sheets written for languages other than Java.
             */
            List<BlancoXmlElement> listCommon = null;
            int sheetLang = BlancoCgSupportedLang.PHP;
            for (String common : BlancoKeyGeneratorKtUtil.mapCommons.keySet()) {
                listCommon = BlancoXmlBindingUtil
                        .getElementsByTagName(elementSheet,
                                common);
                if (listCommon.size() != 0) {
                    BlancoXmlAttribute attr = new BlancoXmlAttribute();
                    attr.setType("CDATA");
                    attr.setQName("style");
                    attr.setLocalName("style");

                    sheetLang = BlancoKeyGeneratorKtUtil.mapCommons.get(common);
                    attr.setValue(new BlancoCgSupportedLang().convertToString(sheetLang));

                    elementSheet.getAtts().add(attr);

                    /* tueda DEBUG */
//                    if (this.isVerbose()) {
//                        System.out.println("/* tueda */ style = " + BlancoXmlBindingUtil.getAttribute(elementSheet, "style"));
//                    }

                    break;
                }
            }

            if (listCommon == null || listCommon.size() == 0) {
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

            BlancoKeyGeneratorKtClassStructure objClassStructure = null;
            switch (sheetLang) {
                case BlancoCgSupportedLang.PHP:
                    objClassStructure = parseElementSheetPhp(elementSheet, BlancoKeyGeneratorKtUtil.packageMap);
                    /* NOT YET SUPPORT ANOTHER LANGUAGES */
            }

            if (objClassStructure != null) {
                // Saves the obtained information.
                listStructure.add(objClassStructure);
            }
        }

        final BlancoKeyGeneratorKtClassStructure[] result = new BlancoKeyGeneratorKtClassStructure[listStructure
                .size()];
        listStructure.toArray(result);
        return result;
    }

    /**
     * Parses the "sheet" XML element in the intermediate XML file to get the value object information.
     *
     * @param argElementSheet
     *            "sheet" XML element in the intermediate XML file.
     * @return Value object information obtained as a result of parsing. Null is returned if "name" is not found.
     */
    public BlancoKeyGeneratorKtClassStructure parseElementSheet(
            final BlancoXmlElement argElementSheet) {
        final BlancoKeyGeneratorKtClassStructure objClassStructure = new BlancoKeyGeneratorKtClassStructure();
        final List<BlancoXmlElement> listCommon = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        "blancokeygenerator-common");
        if (listCommon == null || listCommon.size() == 0) {
            // Skips if there is no common.
            return null;
        }
        final BlancoXmlElement elementCommon = listCommon.get(0);
        objClassStructure.setName(BlancoXmlBindingUtil.getTextContent(
                elementCommon, "name"));
        objClassStructure.setPackage(BlancoXmlBindingUtil.getTextContent(
                elementCommon, "package"));

        objClassStructure.setDescription(BlancoXmlBindingUtil.getTextContent(
                elementCommon, "description"));
        if (BlancoStringUtil.null2Blank(objClassStructure.getDescription())
                .length() > 0) {
            final String[] lines = BlancoNameUtil.splitString(objClassStructure
                    .getDescription(), '\n');
            for (int index = 0; index < lines.length; index++) {
                if (index == 0) {
                    objClassStructure.setDescription(lines[index]);
                } else {
                    // For a multi-line description, it will be split and stored.
                    // From the second line, assumes that character reference encoding has been properly implemented.
                    objClassStructure.getDescriptionList().add(lines[index]);
                }
            }
        }

        objClassStructure.setAccess(BlancoXmlBindingUtil.getTextContent(
                elementCommon, "access"));
        objClassStructure.setAbstract("true".equals(BlancoXmlBindingUtil
                .getTextContent(elementCommon, "abstract")));
        objClassStructure.setData("true".equals(BlancoXmlBindingUtil
                .getTextContent(elementCommon, "data")));
        objClassStructure.setGenerateToString("true"
                .equals(BlancoXmlBindingUtil.getTextContent(elementCommon,
                        "generateToString")));
        objClassStructure.setAdjustFieldName("true".equals(BlancoXmlBindingUtil
                .getTextContent(elementCommon, "adjustFieldName")));
        objClassStructure.setAdjustDefaultValue("true"
                .equals(BlancoXmlBindingUtil.getTextContent(elementCommon,
                        "adjustDefaultValue")));
        objClassStructure
                .setFieldList(new ArrayList<blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtFieldStructure>());

        if (BlancoStringUtil.null2Blank(objClassStructure.getName()).trim()
                .length() == 0) {
            // Skips if name is empty.
            return null;
        }

        if (objClassStructure.getPackage() == null) {
            throw new IllegalArgumentException(fMsg
                    .getMbvoji01(objClassStructure.getName()));
        }

        final List<BlancoXmlElement> extendsList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        "blancokeygenerator-extends");
        if (extendsList != null && extendsList.size() != 0) {
            final BlancoXmlElement elementExtendsRoot = extendsList.get(0);
            BlancoKeyGeneratorKtExtendsStructure extendsStructure = new BlancoKeyGeneratorKtExtendsStructure();
            extendsStructure.setType(BlancoXmlBindingUtil.getTextContent(elementExtendsRoot, "name"));
            objClassStructure.setExtends(extendsStructure);
        }

        final List<BlancoXmlElement> interfaceList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        "blancokeygenerator-implements");
        if (interfaceList != null && interfaceList.size() != 0) {
            final BlancoXmlElement elementInterfaceRoot = interfaceList.get(0);
            final List<BlancoXmlElement> listInterfaceChildNodes = BlancoXmlBindingUtil
                    .getElementsByTagName(elementInterfaceRoot, "interface");
            for (int index = 0; index < listInterfaceChildNodes.size(); index++) {
                final BlancoXmlElement elementList = listInterfaceChildNodes
                        .get(index);

                final String interfaceName = BlancoXmlBindingUtil
                        .getTextContent(elementList, "name");
                if (interfaceName == null || interfaceName.trim().length() == 0) {
                    continue;
                }
                objClassStructure.getImplementsList().add(
                        BlancoXmlBindingUtil
                                .getTextContent(elementList, "name"));
            }
        }

        final List<BlancoXmlElement> listList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet, "blancokeygenerator-itemlist");
        if (listList != null && listList.size() != 0) {
            final BlancoXmlElement elementListRoot = listList.get(0);
            final List<BlancoXmlElement> listChildNodes = BlancoXmlBindingUtil
                    .getElementsByTagName(elementListRoot, "field");
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

                fieldStructure.setType(BlancoXmlBindingUtil.getTextContent(
                        elementList, "type"));

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
                    throw new IllegalArgumentException(fMsg.getMbvoji02(
                            objClassStructure.getName(), fieldStructure
                                    .getName()));
                }

                objClassStructure.getFieldList().add(fieldStructure);
            }
        }

        return objClassStructure;
    }

    /**
     * Parses the "sheet" XML element (PHP format) in the intermediate XML file to get the value object information.
     *
     * @param argElementSheet
     *            "sheet" XML element in the intermediate XML file.
     * @return Value object information obtained as a result of parsing. Null is returned if "name" is not found.
     */
    public BlancoKeyGeneratorKtClassStructure parseElementSheetPhp(
            final BlancoXmlElement argElementSheet,
            final Map<String, String> argClassList) {
        final BlancoKeyGeneratorKtClassStructure objClassStructure = new BlancoKeyGeneratorKtClassStructure();
        final List<BlancoXmlElement> listCommon = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        "blancokeygenerator-common");
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
        objClassStructure.setPackageSuffix(this.fPackageSuffix);
        objClassStructure.setOverridePackage(this.fOverridePackage);

        // Value object definition (PHP) common
        final BlancoXmlElement elementCommon = listCommon.get(0);
        parseCommonPhp(elementCommon, objClassStructure);
        if (BlancoStringUtil.null2Blank(objClassStructure.getName()).trim()
                .length() == 0) {
            // Skips if name is empty.
            return null;
        }

        // Value object definition (PHP) inheritance
        final List<BlancoXmlElement> extendsList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        "blancokeygenerator-extends");
        if (extendsList != null && extendsList.size() != 0) {
            final BlancoXmlElement elementExtendsRoot = extendsList.get(0);
            parseExtendsPhp(elementExtendsRoot, objClassStructure, argClassList);
        }

        //         // Value object definition (Kt) delegation
        final List<BlancoXmlElement> delegateList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        "blancovalueobjectkt-delegate");
        if (delegateList != null && delegateList.size() != 0) {
            final BlancoXmlElement elementDelegateRoot = delegateList.get(0);
            parseDelegateList(elementDelegateRoot, objClassStructure);
        }

        // Value object definition (PHP) implementation
        final List<BlancoXmlElement> interfaceList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        "blancokeygenerator-implements");
        if (interfaceList != null && interfaceList.size() != 0) {
            final BlancoXmlElement elementInterfaceRoot = interfaceList.get(0);

            parseInterfacePhp(elementInterfaceRoot, objClassStructure, argClassList);
        }

        // Creates import list.
        final List<BlancoXmlElement> importList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet, "blancokeygenerator-import");
        if (importList != null && importList.size() != 0) {
            final BlancoXmlElement elementImportRoot = importList.get(0);
            parseImportListPhp(elementImportRoot, objClassStructure);
        }

        // Value object definition (PHP) list
        final List<BlancoXmlElement> listList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet, "blancokeygenerator-itemlist");
        if (listList != null && listList.size() != 0) {
            final BlancoXmlElement elementListRoot = listList.get(0);

            parseFieldList(elementListRoot, objClassStructure, argClassList);
        }

        return objClassStructure;
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
     * Value object definition (PHP) common
     * @param argElementCommon
     * @param argClassStructure
     */
    private void parseCommonPhp(
            final BlancoXmlElement argElementCommon,
            final BlancoKeyGeneratorKtClassStructure argClassStructure
    ) {
        argClassStructure.setName(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "name"));
        argClassStructure.setPackage(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "package"));

        argClassStructure.setDescription(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "description"));
        if (BlancoStringUtil.null2Blank(argClassStructure.getDescription())
                .length() > 0) {
            final String[] lines = BlancoNameUtil.splitString(argClassStructure
                    .getDescription(), '\n');
            for (int index = 0; index < lines.length; index++) {
                if (index == 0) {
                    argClassStructure.setDescription(lines[index]);
                } else {
                    // For a multi-line description, it will be split and stored.
                    // From the second line, assumes that character reference encoding has been properly implemented.
                    argClassStructure.getDescriptionList().add(lines[index]);
                }
            }
        }

        /* Supports generic types of the class. */
        String classGenerics = BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "generic");
        if (BlancoStringUtil.null2Blank(classGenerics).length() > 0) {
            argClassStructure.setGeneric(classGenerics);
        }


        /* Supports annotation of class. (Kt), if any, takes precedence. */
        String classAnnotation = BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "annotationKt");
        if (BlancoStringUtil.null2Blank(classAnnotation).length() == 0) {
            classAnnotation = BlancoXmlBindingUtil.getTextContent(
                    argElementCommon, "annotation");
        }
        if (BlancoStringUtil.null2Blank(classAnnotation).length() > 0) {
            argClassStructure.setAnnotationList(createAnnotaionList(classAnnotation));
        }

        argClassStructure.setAccess(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "access"));
        argClassStructure.setFinal("true".equals(BlancoXmlBindingUtil
                .getTextContent(argElementCommon, "final")));
        argClassStructure.setAbstract("true".equals(BlancoXmlBindingUtil
                .getTextContent(argElementCommon, "abstract")));
        argClassStructure.setData("true".equals(BlancoXmlBindingUtil
                .getTextContent(argElementCommon, "data")));
        argClassStructure.setGenerateToString("true"
                .equals(BlancoXmlBindingUtil.getTextContent(argElementCommon,
                        "generateToString")));
        argClassStructure.setAdjustFieldName("true".equals(BlancoXmlBindingUtil
                .getTextContent(argElementCommon, "adjustFieldName")));
        argClassStructure.setAdjustDefaultValue("true"
                .equals(BlancoXmlBindingUtil.getTextContent(argElementCommon,
                        "adjustDefaultValue")));
        argClassStructure
                .setFieldList(new ArrayList<blanco.keygeneratorkt.valueobject.BlancoKeyGeneratorKtFieldStructure>());

        if (argClassStructure.getPackage() == null) {
            throw new IllegalArgumentException(fMsg
                    .getMbvoji01(argClassStructure.getName()));
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
            final BlancoKeyGeneratorKtClassStructure argClassStructure,
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
            final BlancoKeyGeneratorKtClassStructure argClassStructure,
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
            final BlancoKeyGeneratorKtClassStructure argClassStructure
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
            final BlancoKeyGeneratorKtClassStructure argClassStructure
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
     * Value object definition (PHP) list
     * @param argElementListRoot
     * @param argClassStructure
     * @param argClassList
     */
    private void parseFieldList(
            final BlancoXmlElement argElementListRoot,
            final BlancoKeyGeneratorKtClassStructure argClassStructure,
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
                throw new IllegalArgumentException(fMsg.getMbvoji04(
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

            // Supports abstract.
            fieldStructure.setAbstract("true".equals(BlancoXmlBindingUtil
                    .getTextContent(elementList, "abstract")));

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

            // Supports Nullable.
            String nullableKt = BlancoXmlBindingUtil
                    .getTextContent(elementList, "nullableKt");
            String nullable = BlancoXmlBindingUtil
                    .getTextContent(elementList, "nullable");
            if (BlancoStringUtil.null2Blank(nullableKt).length() > 0) {
                if ("true".equals(nullableKt)) {
                    nullable = nullableKt;
                } else if ("not".equals(nullableKt) &&
                BlancoStringUtil.null2Blank(nullable).length() > 0) {
                    nullable = ""; // Ignores nullable if nullableKt is "not".
                }
            }
            fieldStructure.setNullable("true".equals(nullable));

            // Supports value.
            String valueKt = BlancoXmlBindingUtil
                    .getTextContent(elementList, "fixedValueKt");
            String value = BlancoXmlBindingUtil
                    .getTextContent(elementList, "fixedValue");
            if (BlancoStringUtil.null2Blank(valueKt).length() > 0) {
                if ("true".equals(valueKt)) {
                    value = valueKt;
                } else if ("not".equals(valueKt) &&
                        BlancoStringUtil.null2Blank(value).length() > 0) {
                    value = ""; // Ignores value if valueKt is "not".
                }
            }
            fieldStructure.setValue("true".equals(value));

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
                throw new IllegalArgumentException(fMsg.getMbvoji02(
                        argClassStructure.getName(), fieldStructure
                                .getName()));
            }

            /* Supports non-final (final by default in Kotlin). */
            fieldStructure.setOpen("true".equals(BlancoXmlBindingUtil
                    .getTextContent(elementList, "open")));

            argClassStructure.getFieldList().add(fieldStructure);
        }
    }
}
