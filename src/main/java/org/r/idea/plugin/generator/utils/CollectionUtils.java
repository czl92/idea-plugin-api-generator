package org.r.idea.plugin.generator.utils;

import java.util.Collection;

/**
 * @ClassName CollectionUtils
 * @Author Casper
 * @DATE 2019/6/21 17:32
 **/
public class CollectionUtils {

    public static boolean isEmpty(Collection target) {
        return target == null || target.size() == 0;
    }

    public static boolean isNotEmpty(Collection target) {
        return !isEmpty(target);
    }


}
