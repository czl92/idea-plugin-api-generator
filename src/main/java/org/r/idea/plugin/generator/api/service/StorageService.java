package org.r.idea.plugin.generator.api.service;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import org.r.idea.plugin.generator.api.beans.SettingState;

/**
 * @ClassName StorageService
 * @Author Casper
 * @DATE 2019/6/10 9:44
 **/
public interface StorageService extends PersistentStateComponent<SettingState> {

    static StorageService getInstance() {
        return ServiceManager.getService(StorageService.class);
    }

}
