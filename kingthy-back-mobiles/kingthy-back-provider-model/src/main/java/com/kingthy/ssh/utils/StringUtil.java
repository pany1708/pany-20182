package com.kingthy.ssh.utils;

import com.kingthy.ssh.constant.BaseConstant;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author:  潘勇
 * @Description:  StringUtils工具类
 * @Date:  2017/12/8 11:23
 */
public class StringUtil
{
    public static String EMPTY_STRING = "";

    public static String[] EMPTY_STRING_ARRAY = new String[0];

    public static boolean containsIgnoreCase(final String originalStr, final CharSequence targetStr)
    {

        if (null == originalStr)
        {
            return false;
        }

        String originalStrCaps = originalStr.toUpperCase();
        String targetStrCaps = targetStr.toString().toUpperCase();
        return originalStrCaps.contains(targetStrCaps);
    }

    public static String convertToCamelCaseString(String inputString, boolean firstCharacterUppercase)
    {
        if (null == inputString)
        {
            return null;
        }
        StringBuilder sb = new StringBuilder();

        boolean nextUpperCase = false;
        for (int i = 0; i < inputString.length(); i++)
        {
            char c = inputString.charAt(i);

            switch (c)
            {
                case '_':
                case '-':
                case '@':
                case '$':
                case '#':
                case ' ':
                case '/':
                case '&':
                    if (sb.length() > 0)
                    {
                        nextUpperCase = true;
                    }
                    break;

                default:
                    if (nextUpperCase)
                    {
                        sb.append(Character.toUpperCase(c));
                        nextUpperCase = false;
                    }
                    else
                    {
                        sb.append(c);
                    }
                    break;
            }
        }

        if (firstCharacterUppercase)
        {
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
        }
        else
        {
            sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
        }

        return sb.toString();
    }

    public static String defaultIfBlank(String originalStr, String defaultStr)
    {
        if (StringUtil.isBlank(originalStr))
        {
            return defaultStr;
        }
        Collections.emptyList();
        return originalStr;
    }

    public static boolean equalsIgnoreCaseAll(String targetStr, String... compareStrArray)
    {

        if (StringUtil.isBlank(targetStr) || null == compareStrArray || 0 == compareStrArray.length)
        {
            return false;
        }
        for (int i = 0; i < compareStrArray.length; i++)
        {
            if (!targetStr.equalsIgnoreCase(compareStrArray[i]))
            {
                return false;
            }
        }
        return true;
    }

    public static boolean equalsIgnoreCaseOne(String targetStr, String... compareStrArray)
    {

        if (StringUtil.isBlank(targetStr) || null == compareStrArray || 0 == compareStrArray.length)
        {
            return false;
        }
        for (int i = 0; i < compareStrArray.length; i++)
        {
            if (targetStr.equalsIgnoreCase(compareStrArray[i]))
            {
                return true;
            }
        }
        return false;
    }

    public static List<String> findAllByRegex(String originalStr, String regex)
    {

        if (StringUtil.isBlank(originalStr) || StringUtil.isBlank(regex))
            return null;

        List<String> targetStrList = new ArrayList<String>();
        final Pattern patternOfTargetStr = Pattern.compile(regex, Pattern.CANON_EQ);
        final Matcher matcherOfTargetStr = patternOfTargetStr.matcher(originalStr);
        while (matcherOfTargetStr.find())
        {
            targetStrList.add(StringUtil.trimToEmpty(matcherOfTargetStr.group()));
        }
        return targetStrList;
    }

    public static String findFirstByRegex(String originalStr, String regex)
    {

        if (StringUtil.isBlank(originalStr) || StringUtil.isBlank(regex))
            return EMPTY_STRING;

        final Pattern patternOfTargetStr = Pattern.compile(regex, Pattern.CANON_EQ);
        final Matcher matcherOfTargetStr = patternOfTargetStr.matcher(originalStr);
        if (matcherOfTargetStr.find())
        {
            return StringUtil.trimToEmpty(matcherOfTargetStr.group());
        }
        return EMPTY_STRING;
    }

    public static String generateLineBlank(int lines)
    {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < lines; i++)
        {
            sb.append("\n");
        }

        return sb.toString();
    }

    public static String makeFirstLetterLowerCase(String str)
    {
        String firstLetter = str.substring(0, 1);
        return firstLetter.toLowerCase() + str.substring(1, str.length());
    }

    public static boolean isBlank(String originalStr)
    {
        if (null == originalStr)
        {
            return true;
        }
        if (originalStr.contains(BaseConstant.WORD_SEPARATOR))
        {
            return false;
        }
        return trimToEmpty(originalStr).isEmpty();
    }

    public static boolean isBlank(String... originalStrArray)
    {

        if (null == originalStrArray || 0 == originalStrArray.length)
            return true;
        for (int i = 0; i < originalStrArray.length; i++)
        {
            if (isBlank(originalStrArray[i]))
                return true;
        }
        return false;
    }

    public static boolean isContainWhitespace(String originalStr)
    {

        if (StringUtil.isBlank(originalStr))
        {
            return true;
        }
        int strLen = originalStr.length();
        for (int i = 0; i < strLen; i++)
        {
            char ch = originalStr.charAt(i);
            if (Character.isWhitespace(ch))
            {
                return true;
            }
        }
        return false;
    }

    /**
     * 字符串连接，使用指定分隔符
     *
     * @return
     */
    public static String join(String... subStrs)
    {

        if (null == subStrs || 0 == subStrs.length)
        {
            return EMPTY_STRING;
        }
        StringBuilder sb = new StringBuilder();
        for (String subStr : subStrs)
        {
            sb.append(subStr).append(BaseConstant.WORD_SEPARATOR);
        }
        String sbStr = sb.toString();
        if (sbStr.endsWith(BaseConstant.WORD_SEPARATOR))
        {
            sbStr = StringUtil.replaceLast(sbStr, BaseConstant.WORD_SEPARATOR, "");
        }
        return sbStr;
    }

    public static String replaceAll(String originalStr, String replacement, String regex)
    {
        return StringUtil.trimToEmpty(originalStr).replaceAll(regex, replacement);
    }

    public static String replaceAll(String originalStr, String replacement, String... regexArray)
    {

        if (0 == regexArray.length)
            return originalStr;

        for (String regex : regexArray)
        {
            originalStr = StringUtil.replaceAll(originalStr, replacement, regex);
        }

        return originalStr;
    }

    public static String replaceLast(String originalStr, String regex, String replacement)
    {

        if (StringUtil.isBlank(originalStr))
            return EMPTY_STRING;

        int index = originalStr.lastIndexOf(regex);
        if (-1 == index)
            return originalStr;

        // 鍏堝瓨鍌ㄨ繖涓猧ndex涔嬪墠鐨勬墍鏈塻tr
        String temp = originalStr.substring(0, index);
        String temp2 = originalStr.substring(index, originalStr.length());

        temp2 = temp2.replaceFirst(regex, replacement);

        originalStr = temp + temp2;

        return originalStr;
    }

    public static String replaceSequenced(String originalStr, Object... replacementParams)
    {

        if (StringUtil.isBlank(originalStr))
            return EMPTY_STRING;
        if (null == replacementParams || 0 == replacementParams.length)
            return originalStr;

        for (int i = 0; i < replacementParams.length; i++)
        {
            String elementOfParams = replacementParams[i] + EmptyObjectConstant.EMPTY_STRING;
            if (StringUtil.trimToEmpty(elementOfParams).equalsIgnoreCase("null"))
                elementOfParams = EmptyObjectConstant.EMPTY_STRING;
            originalStr = originalStr.replace("{" + i + "}", StringUtil.trimToEmpty(elementOfParams));
        }

        return originalStr;
    }

    public static String setPrefix(String originalStr, String prefix)
    {
        originalStr = StringUtil.trimToEmpty(originalStr);
        prefix = StringUtil.trimToEmpty(prefix);
        if (!originalStr.startsWith(prefix))
        {
            originalStr = prefix + originalStr;
        }
        return originalStr;
    }

    /**
     * /**
     * 鍒ゆ柇瀛楃涓叉槸鍚﹁秴杩囨寚瀹氶暱搴︼紝濡備綍瓒呰繃锛屾坊鍔犳寚瀹氬悗缂�
     *
     * @param originalStr "閾舵椂鐨�
     * @param maxLength   2
     * @param suffix      ...
     * @return "閾舵椂..."
     */
    public static String subStringIfTooLong(String originalStr, int maxLength, String suffix)
    {
        if (StringUtil.isBlank(originalStr))
            return EmptyObjectConstant.EMPTY_STRING;
        if (maxLength < 0)
            maxLength = 0;
        if (originalStr.length() > maxLength)
            return originalStr.substring(0, maxLength) + StringUtil.trimToEmpty(suffix);
        return originalStr;
    }

    /**
     * Returns a copy of the string, with leading and trailing whitespace
     * omitted. Don't worry the NullPointerException. Will never return Null.
     *
     * @param originalStr
     * @return "" or String without empty str.
     */
    public static String trimToEmpty(String originalStr)
    {
        if (null == originalStr || originalStr.isEmpty())
            return EMPTY_STRING;
        if (originalStr.equals(BaseConstant.WORD_SEPARATOR))
            return originalStr;
        return originalStr.trim();
    }

    /**
     * URL编码
     *
     * @param s   String to be translated.
     * @param enc The name of a supported character encoding.
     * @return
     */
    public static String urlEncode(String s, String enc)
    {
        if (StringUtil.isBlank(s))
            return StringUtil.trimToEmpty(s);
        try
        {
            return java.net.URLEncoder.encode(trimToEmpty(s), enc);
        }
        catch (UnsupportedEncodingException e)
        {
            return s;
        }
    }

    /**
     * URL编码,使用UTF-8编码
     *
     * @param s String to be translated.
     * @return
     */
    public static String urlEncode(String s)
    {
        if (StringUtil.isBlank(s))
            return StringUtil.trimToEmpty(s);
        return urlEncode(trimToEmpty(s), "UTF-8");
    }

}
