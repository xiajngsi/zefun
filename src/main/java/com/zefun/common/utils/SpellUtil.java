package com.zefun.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 拼写工具类
* @author 洪秋霞
* @date 2015年8月11日 下午2:21:59
 */
public class SpellUtil {

    /** 
     * 将汉字转换为全拼 
     * @param str 
     * @return String 
     */
    public static String toSpell(String str) {
        if (StringUtil.isEmpty(str)) {
            return "";
        }
        char[] cs = str.toCharArray();
        // 设置汉字拼音输出的格式
        HanyuPinyinOutputFormat hpof = new HanyuPinyinOutputFormat();
        hpof.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        hpof.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        hpof.setVCharType(HanyuPinyinVCharType.WITH_V);
        StringBuffer spell = new StringBuffer();
        String s = null;
        try {
            for (char c : cs) {
                // 判断是否为汉字字符
                String letter = Character.toString(c);
                if (letter.matches("[\\u4E00-\\u9FA5]+")) {
                    s = PinyinHelper.toHanyuPinyinStringArray(c, hpof)[0];
                    spell.append(s.substring(0, 1).toUpperCase());
                    spell.append(s.substring(1));
                }
                else {
                    if (letter.matches("[^a-zA-Z]")) {
                        spell.append("#");
                    }
                    else {
                        spell.append(letter);
                    }
                }
            }
        }
        catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return spell.toString();
    }

    /** 
     * 提取每个汉字的首字母 
     * @param str 
     * @return String 
     */
    public static String toSpellHead(String str) {
        StringBuffer convert = new StringBuffer();
        for (int j = 0; j < str.length(); j++) {
            char word = str.charAt(j);
            // 提取汉字的首字母
            String[] pinyinArray = PinyinHelper.toHanyuPinyinStringArray(word);
            if (pinyinArray != null) {
                convert.append(pinyinArray[0].charAt(0));
            }
            else {
                convert.append(word);
            }
        }
        return convert.toString();
    }

}
