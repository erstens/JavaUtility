package com.erstens.utility;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 *     compile group: 'com.belerweb', name: 'pinyin4j', version: '2.5.0'
 */
public class PinYinUtils {
    /**
     * 得到中文首字母
     *
     * @param str
     * @return
     */
    public static String getPinYinHeadChar(String str) {

        String convert = "";
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert += pinyinArray[0].charAt(0);
            } else {
                convert += word;
            }
        }
        return convert;
    }
}
