package org.r.idea.plugin.generator.api;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import org.r.generator.api.core.Generator;
import org.r.generator.api.core.beans.ConfigBean;
import org.r.idea.plugin.generator.api.beans.SettingState;
import org.r.idea.plugin.generator.api.service.StorageService;

/**
 * @ClassName Generate
 * @Author Casper
 * @DATE 2019/6/10 9:37
 **/
public class Generate extends AnAction {

    private StorageService storageService = StorageService.getInstance();


    @Override
    public void actionPerformed(AnActionEvent e) {

        SettingState state = storageService.getState();
        String msg = state.getSrcFilePaths();
        ConfigBean configBean = new ConfigBean();



        //Generator.generate(null,null);
        Messages.showInfoMessage(msg, "src file path");
    }
}
