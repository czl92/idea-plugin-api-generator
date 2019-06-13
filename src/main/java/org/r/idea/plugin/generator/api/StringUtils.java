package org.r.idea.plugin.generator.api;

/**
 * @ClassName StringUtils
 * @Author Casper
 * @DATE 2019/6/13 9:41
 **/
public class StringUtils {


    public static boolean isEmpty(String src) {
        return src == null || "".equals(src);
    }

    public static boolean isNotEmpty(String src) {
        return !isEmpty(src);
    }

}
