package org.r.idea.plugin.generator.api.config;


import com.intellij.openapi.components.ServiceManager;
import org.r.idea.plugin.generator.api.beans.SettingBean;

/**
 * @ClassName PathConfig
 * @Author Casper
 * @DATE 2019/6/10 11:48
 **/
public class PathConfig {

    static PathConfig getInstance(){
        return ServiceManager.getService(PathConfig.class);
    }

    private SettingBean settingBean;




}
