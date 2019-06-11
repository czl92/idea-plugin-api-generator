package org.r.idea.plugin.generator.api.service;

import com.intellij.openapi.components.ServiceManager;
import org.r.idea.plugin.generator.api.beans.SettingBean;
import org.r.idea.plugin.generator.api.beans.SettingState;

/**
 * @ClassName ConfigConvertor
 * @Author Casper
 * @DATE 2019/6/10 11:56
 **/
public interface ConfigConvertor {

    static ConfigConvertor getInstance() {
        return ServiceManager.getService(ConfigConvertor.class);
    }


    /**
     * 转化持久化的数据为程序需要使用的数据
     */
    SettingBean convertFrom(SettingState state);


}
