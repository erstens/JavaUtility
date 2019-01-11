package com.erstens.utility;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ErrorPropertiesUtils {
    private static final Properties errorProp = new Properties() ;
    static {
        InputStream input = ErrorPropertiesUtils.class.getClassLoader().getResourceAsStream("error.properties");
        InputStream in = null;
        try {
            in = new BufferedInputStream(input);
            errorProp.load(in);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != in) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (null != input) {
                    input.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static String getValue(String key) {
        return errorProp.getProperty(key);
    }
}
