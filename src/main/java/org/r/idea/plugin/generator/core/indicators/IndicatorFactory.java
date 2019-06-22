package org.r.idea.plugin.generator.core.indicators;

import org.r.idea.plugin.generator.impl.indicators.GenericityIndicatorImpl;

/**
 * @ClassName IndicatorFactory
 * @Author Casper
 * @DATE 2019/6/21 15:10
 **/
public class IndicatorFactory {

    private static GenericityIndicator genericityIndicator = new GenericityIndicatorImpl();

    public static GenericityIndicator getGenericityIndicator() {
        return genericityIndicator;
    }


}
