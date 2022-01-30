/*
 * blanco Framework
 * Copyright (C) 2004-2020 IGA Tosiki
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 */
package blanco.keygeneratorkt;

import blanco.keygeneratorkt.task.BlancoKeyGeneratorKtProcessImpl;
import blanco.keygeneratorkt.task.valueobject.BlancoKeyGeneratorKtProcessInput;
import blanco.valueobjectkt.task.BlancoValueObjectKtProcessImpl;
import blanco.valueobjectkt.task.valueobject.BlancoValueObjectKtProcessInput;
import org.junit.Test;

import java.io.IOException;

/**
 * Generation test for Kotlin.
 *
 * @author IGA Tosiki
 * @author tueda
 */
public class BlancoKeyGeneratorKtTest {

    @Test
    public void testBlancoKeyGeneratorKt() {
        BlancoValueObjectKtProcessInput input = new BlancoValueObjectKtProcessInput();
        input.setMetadir("meta/samples");
        input.setEncoding("UTF-8");
        input.setSheetType("php");
        input.setTmpdir("tmpTest");
        input.setTargetdir("sample/blanco");
        input.setTargetStyle("maven");
        input.setVerbose(true);
        input.setLineSeparator("LF");
        input.setPackageSuffix("blanco");

        BlancoValueObjectKtProcessImpl imple = new BlancoValueObjectKtProcessImpl();
        try {
            imple.execute(input);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BlancoKeyGeneratorKtProcessInput input2 = new BlancoKeyGeneratorKtProcessInput();
        input2.setMetadir("meta/samples");
        input2.setEncoding("UTF-8");
        input2.setSheetType("php");
        input2.setTmpdir("tmpTest");
        input2.setTargetdir("sample/blanco");
        input2.setTargetStyle("maven");
        input2.setVerbose(true);
        input2.setLineSeparator("LF");
        input2.setPackageSuffix("blanco");
        input2.setSearchTmpdir("tmpTest");

        BlancoKeyGeneratorKtProcessImpl imple2 = new BlancoKeyGeneratorKtProcessImpl();
        try {
            imple2.execute(input2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
