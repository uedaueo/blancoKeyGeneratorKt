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
import java.util.List;
import java.util.Map;

/**
 * A class that parses (reads and writes) the intermediate XML file format of a bucketList of blancoKeyGenertor.
 *
 * @author tueda
 */
public class BlancoKeyGeneratorKtBucketXmlParser {
    /**
     * A message.
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
     * Parses an XML document in an intermediate XML file to get an array
     *
     * @param argMetaXmlSourceFile
     * @return
     */
    public BlancoKeyGeneratorKtBucketListStructure parse(
            final File argMetaXmlSourceFile
    ) {
        final BlancoXmlDocument documentMeta = new BlancoXmlUnmarshaller()
                .unmarshal(argMetaXmlSourceFile);
        if (documentMeta == null) {
            return null;
        }

        System.out.println("[blancoKeyGeneratorKt: Phase Bucket List Processes " + argMetaXmlSourceFile.getName() + ".]");

        return parse(documentMeta);
    }

    /**
     * Parses an XML document in an intermediate XML file to get an array
     *
     * @param argXmlDocument
     * @return
     */
    public BlancoKeyGeneratorKtBucketListStructure parse(
            BlancoXmlDocument argXmlDocument
    ) {
        BlancoKeyGeneratorKtBucketListStructure bucketListStructure = null;

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
            List<BlancoXmlElement> listCommon = BlancoXmlBindingUtil
                    .getElementsByTagName(elementSheet, fBundle.getMeta2xmlElementBucketcommon());
            /* sheetLang is always PHP in BlancoKeyGenerator */
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

            bucketListStructure = parseElementSheetPhp(elementSheet);
            if (bucketListStructure != null) {
                /* bucketList found. */
                break;
            }
        }
        return bucketListStructure;

    }

    /**
     * Parses the "sheet" XML element (PHP format) in the intermediate XML file to get the buckets information.
     *
     * @param argElementSheet
     *            "sheet" XML element in the intermediate XML file.
     * @return buckets information obtained as a result of parsing. Null is returned if "name" is not found.
     */
    public BlancoKeyGeneratorKtBucketListStructure parseElementSheetPhp(
            final BlancoXmlElement argElementSheet) {
        final BlancoKeyGeneratorKtBucketListStructure bucketListStructure = new BlancoKeyGeneratorKtBucketListStructure();
        final List<BlancoXmlElement> listCommon = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet,
                        fBundle.getMeta2xmlElementBucketcommon());
        if (listCommon == null || listCommon.size() == 0) {
            // Skips if there is no common.
            return null;
        }

        // Key Generator BucketList common
        final BlancoXmlElement elementCommon = listCommon.get(0);
        parseCommonPhp(elementCommon, bucketListStructure);
        if (BlancoStringUtil.null2Blank(bucketListStructure.getName()).trim()
                .length() == 0) {
            // Skips if name is empty.
            return null;
        }

        bucketListStructure.setPackageSuffix(this.fPackageSuffix);
        bucketListStructure.setOverridePackage(this.fOverridePackage);

        // Key Generator BucketList list
        final List<BlancoXmlElement> listList = BlancoXmlBindingUtil
                .getElementsByTagName(argElementSheet, fBundle.getMeta2xmlElementBucketlist());
        if (listList != null && listList.size() != 0) {
            final BlancoXmlElement elementListRoot = listList.get(0);

            parseBucketList(elementListRoot, bucketListStructure);
        }

        return bucketListStructure;
    }

    /**
     * Key Generator BucketList common
     * @param argElementCommon
     * @param argBucketListStructure
     */
    private void parseCommonPhp(
            final BlancoXmlElement argElementCommon,
            final BlancoKeyGeneratorKtBucketListStructure argBucketListStructure) {

        argBucketListStructure.setName(BlancoXmlBindingUtil.getTextContent(argElementCommon, "name"));
        argBucketListStructure.setPackage(BlancoXmlBindingUtil.getTextContent(argElementCommon, "package"));

        argBucketListStructure.setDescription(BlancoXmlBindingUtil.getTextContent(
                argElementCommon, "description"));
        if (BlancoStringUtil.null2Blank(argBucketListStructure.getDescription())
                .length() > 0) {
            final String[] lines = BlancoNameUtil.splitString(argBucketListStructure
                    .getDescription(), '\n');
            for (int index = 0; index < lines.length; index++) {
                if (index == 0) {
                    argBucketListStructure.setDescription(lines[index]);
                } else {
                    // For a multi-line description, it will be split and stored.
                    // From the second line, assumes that character reference encoding has been properly implemented.
                    argBucketListStructure.getDescriptionList().add(lines[index]);
                }
            }
        }

        argBucketListStructure
                .setBucketList(new ArrayList<BlancoKeyGeneratorKtBucketStructure>());
    }

    /**
     * Key Generator BucketList list
     * @param argElementListRoot
     * @param argBucketListStructure
     */
    private void parseBucketList(
            final BlancoXmlElement argElementListRoot,
            final BlancoKeyGeneratorKtBucketListStructure argBucketListStructure
    ) {

        final List<BlancoXmlElement> listChildNodes = BlancoXmlBindingUtil
                .getElementsByTagName(argElementListRoot, "bucket");
        for (int index = 0; index < listChildNodes.size(); index++) {
            final BlancoXmlElement elementList = listChildNodes.get(index);
            final BlancoKeyGeneratorKtBucketStructure bucketStructure = new BlancoKeyGeneratorKtBucketStructure();

            bucketStructure.setNo(BlancoXmlBindingUtil.getTextContent(
                    elementList, "no"));
            bucketStructure.setName(BlancoXmlBindingUtil.getTextContent(
                    elementList, "name"));
            if (bucketStructure.getName() == null
                    || bucketStructure.getName().trim().length() == 0) {
                continue;
            }
            bucketStructure.setKvsType(BlancoXmlBindingUtil.getTextContent(elementList, "kvsType"));
            if (BlancoStringUtil.null2Blank(bucketStructure.getKvsType()).trim().length() == 0) {
                continue;
            }
            bucketStructure.setTableIdLength(BlancoXmlBindingUtil.getTextContent(elementList, "tableIdLength"));
            if (BlancoStringUtil.null2Blank(bucketStructure.getTableIdLength()).trim().length() == 0) {
                continue;
            }
            bucketStructure.setVersion(BlancoXmlBindingUtil.getTextContent(elementList, "version"));
            if (BlancoStringUtil.null2Blank(bucketStructure.getVersion()).trim().length() == 0) {
                continue;
            }

            bucketStructure.setDescription(BlancoXmlBindingUtil
                    .getTextContent(elementList, "description"));
            final String[] lines = BlancoNameUtil.splitString(
                    bucketStructure.getDescription(), '\n');
            for (int indexLine = 0; indexLine < lines.length; indexLine++) {
                if (indexLine == 0) {
                    bucketStructure.setDescription(lines[indexLine]);
                } else {
                    // For a multi-line description, it will be split and stored.
                    // From the second line, assumes that character reference encoding has been properly implemented.
                    bucketStructure.getDescriptionList().add(
                            lines[indexLine]);
                }
            }

            argBucketListStructure.getBucketList().add(bucketStructure);
        }
    }
}
