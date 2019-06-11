package org.r.idea.plugin.generator.api.service.impl;

import org.r.idea.plugin.generator.api.beans.SettingBean;
import org.r.idea.plugin.generator.api.beans.SettingState;
import org.r.idea.plugin.generator.api.service.ConfigConvertor;

/**
 * @ClassName ConfigConvertorImpl
 * @Author Casper
 * @DATE 2019/6/10 11:56
 **/
public class ConfigConvertorImpl implements ConfigConvertor {

    private String SEPARATOR = ",";

    /**
     * 转化持久化的数据为程序需要使用的数据
     */
    @Override
    public SettingBean convertFrom(SettingState state) {

        SettingBean settingBean = new SettingBean();

        return settingBean;
    }
}
