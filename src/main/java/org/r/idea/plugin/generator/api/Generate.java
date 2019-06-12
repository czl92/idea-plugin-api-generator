package org.r.idea.plugin.generator.api;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import java.util.ArrayList;
import java.util.Arrays;
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

        Project project = e.getProject();
        SettingState state = storageService.getState();
        String splitChar = ",";
        if (state == null || project == null) {
            throw new RuntimeException("程序异常");
        }
        ConfigBean configBean = new ConfigBean();
        configBean.setProjectPath(project.getBasePath());
        configBean.setSrcFilePath(new ArrayList<>(Arrays.asList(state.getSrcFilePaths().split(splitChar))));
        configBean.setInterfacePath(new ArrayList<>(Arrays.asList(state.getInterfaceFilePaths().split(splitChar))));
        configBean.setCodeTemplatePath(state.getTemplateFilePaths());
        configBean.setDocPath(state.getOutputFilePaths());
        configBean.setBaseClassType(state.getBaseClass().split(splitChar));
        configBean.setCollectionClassType(state.getCollectionClass().split(splitChar));
        configBean.setNonsupportCollectionClassType(state.getNonsupportClass().split(splitChar));

        Generator generator = new Generator();
        generator.generate(configBean);
    }


}
