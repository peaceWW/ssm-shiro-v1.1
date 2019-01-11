package com.yutons.shiro.util;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @Author: yutons
 * @Description: 汉字转化为拼音的工具类
 * @Created: 2017/9/22 15:04
 */
public class PinyinUtil {
    public static enum Type {
        UPPERCASE,              //大写
        LOWERCASE,              //小写
    }

    /**
     * 获取汉字串拼音首字母，英文字符不变
     *
     * @param chinese 汉字串
     * @return 汉语拼音首字母
     */
    public static String getFirstSpell(String chinese, Type type) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        if (type == null || type == Type.LOWERCASE) {
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        } else {
            defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        }
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat);
                    if (temp != null) {
                        pybf.append(temp[0].charAt(0));
                    }
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString().replaceAll("\\W", "").trim();
    }

    /**
     * 获取汉字串拼音，英文字符不变
     *
     * @param chinese   汉字串
     * @param uppercase
     * @return 汉语拼音
     */
    public static String getFullSpell(String chinese, Type type) {
        StringBuffer pybf = new StringBuffer();
        char[] arr = chinese.toCharArray();
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        if (type == null || type == Type.LOWERCASE) {
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        } else {
            defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        }
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > 128) {
                try {
                    pybf.append(PinyinHelper.toHanyuPinyinStringArray(arr[i], defaultFormat)[0]);
                } catch (BadHanyuPinyinOutputFormatCombination e) {
                    e.printStackTrace();
                }
            } else {
                pybf.append(arr[i]);
            }
        }
        return pybf.toString();
    }

    public static void main(String[] args) throws BadHanyuPinyinOutputFormatCombination {
        System.out.println(PinyinUtil.getFirstSpell("我爱中国",Type.UPPERCASE));
        System.out.println(PinyinUtil.getFullSpell("我爱中国",Type.UPPERCASE));
    }
}
