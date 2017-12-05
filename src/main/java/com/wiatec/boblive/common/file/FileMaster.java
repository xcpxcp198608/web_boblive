package com.wiatec.boblive.common.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class FileMaster {

    public static FileInputStream getInputStreamFrom(String filePath) throws FileNotFoundException {
        return new FileInputStream(new File(filePath));
    }
}
