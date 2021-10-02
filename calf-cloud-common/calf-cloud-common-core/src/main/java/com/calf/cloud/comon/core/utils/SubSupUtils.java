/*
 *   All rights Reserved, Designed By ZTE-ITS
 *   Copyright:    Copyright(C) 2021-2025
 *   Company       FENGZIJK LTD.
 *   @Author:    fengzijk
 *   @Email: guozhifengvip@163.com
 *   @Version    V1.0
 *   @Date:   2021年10月03日 00时50分
 *   Modification       History:
 *   ------------------------------------------------------------------------------------
 *   Date                  Author        Version        Discription
 *   -----------------------------------------------------------------------------------
 *  2021-10-03 00:50:28    fengzijk         1.0         Why & What is modified: 改原因描述>
 *
 *
 */

package com.calf.cloud.comon.core.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * -------------------------------------------------
 * <pre>角标处理工具类</pre>
 *
 * @author : guozhifeng
 * @date : 2021/10/3 0:50
 * --------------------------------------------------
 */
public class SubSupUtils {

    private static final String THE_TEXT = "0 1 2 3 4 5 6 7 8 9 + - = ( ) l s t h";
    private static final String SUB_TEXT = "₀ ₁ ₂ ₃ ₄ ₅ ₆ ₇ ₈ ₉ ₊ ₋ ₌ ₍ ₎ ₗ ₛ ₜ ₕ";
    private static final String SUP_TEXT = "⁰ ¹ ² ³ ⁴ ⁵ ⁶ ⁷ ⁸ ⁹ ⁺ ⁻ ⁼ ⁽ ⁾ ˡ ˢ ᵗ ʰ";

    public static final Map<Character, Character> SUB_MAP = new HashMap<>();
    public static final Map<Character, Character> SUP_MAP = new HashMap<>();

    static {
        for (int i = 0; i < THE_TEXT.length(); i += 2) {
            SUB_MAP.put(THE_TEXT.charAt(i), SUB_TEXT.charAt(i));
            SUP_MAP.put(THE_TEXT.charAt(i), SUP_TEXT.charAt(i));
        }
    }

    public static String toSub(String text) {
        return text.chars().mapToObj(a -> (char) a).map(a -> SUB_MAP.getOrDefault(a, a)).map(Object::toString).collect(Collectors.joining());
    }

    public static String toSup(String text) {
        return text.chars().mapToObj(a -> (char) a).map(a -> SUP_MAP.getOrDefault(a, a)).map(Object::toString).collect(Collectors.joining());
    }

    public static String toSupString(String str) {
        String rex = "(?<=<sup>).*?(?=</sup>)";
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(str);
        matcher.reset();
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, toSup(matcher.group()));
        }
        matcher.appendTail(sb);
        return sb.toString().replaceAll("<sup>", "").replaceAll("</sup>", "");
    }

    public static String toSubString(String str) {
        String rex = "(?<=<sub>).*?(?=</sub>)";
        Pattern pattern = Pattern.compile(rex);
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        matcher.reset();
        while (matcher.find()) {
            matcher.appendReplacement(sb, toSub(matcher.group()));
        }
        matcher.appendTail(sb);
        return sb.toString().replaceAll("<sub>", "").replaceAll("</sub>", "");
    }

    public static String toSubSupString(String text) {
        return toSubString(toSupString(text));
    }

    public static void main(String[] args) {
        System.out.println("下标: " + toSub("last hello 0123456789 world"));
        System.out.println("上标: " + toSup("last hello 0123456789 world"));
        System.out.println(toSubSupString("一种F<sup>-</sup>、Zn<sup>2+</sup>、B<sup>3+</sup>离子协同掺杂电解质,H<sub>2</sub>O是水"));
    }
}
