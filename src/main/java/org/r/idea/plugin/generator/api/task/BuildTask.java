package org.r.idea.plugin.generator.api.task;

import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import com.intellij.openapi.ui.popup.Balloon.Position;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import com.intellij.ui.awt.RelativePoint;
import java.util.ArrayList;
import java.util.Arrays;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nls.Capitalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.r.generator.api.core.Generator;
import org.r.generator.api.core.beans.ConfigBean;
import org.r.idea.plugin.generator.api.beans.SettingState;
import org.r.idea.plugin.generator.api.service.StorageService;

/**
 * @ClassName BuildTask
 * @Author Casper
 * @DATE 2019/6/12 12:21
 **/
public class BuildTask extends Task.Backgroundable {

    private String title;
    private Project project;


    public BuildTask(@Nullable Project project,
        @Nls(capitalization = Capitalization.Title) @NotNull String title) {
        super(project, title);
        this.project = project;
        this.title = title;
    }

    @Override
    public void run(@NotNull ProgressIndicator indicator) {
        indicator.setIndeterminate(true);
        StorageService storageService = StorageService.getInstance();

        SettingState state = storageService.getState();
        if (state == null || project == null) {
            throw new RuntimeException("程序异常");
        }
        indicator.setFraction(0.1);
        ConfigBean configBean = getConfig(state);
        indicator.setFraction(0.3);
        Generator generator = new Generator();
        generator.generate(configBean);
        indicator.setFraction(1.0);
        indicator.setText("finish");

        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
        JBPopupFactory.getInstance()
            .createHtmlTextBalloonBuilder("building finish", MessageType.INFO, null)
            .setFadeoutTime(7500)
            .createBalloon()
            .show(RelativePoint.getCenterOf(statusBar.getComponent()), Position.atRight);

    }

    private ConfigBean getConfig(SettingState state) {
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
        return configBean;
    }


}
