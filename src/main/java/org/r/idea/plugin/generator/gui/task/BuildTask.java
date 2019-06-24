package org.r.idea.plugin.generator.gui.task;

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
import java.util.List;

import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nls.Capitalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.r.generator.api.core.beans.ConfigBean;
import org.r.idea.plugin.generator.core.Generator;
import org.r.idea.plugin.generator.core.config.Config;
import org.r.idea.plugin.generator.gui.beans.SettingState;
import org.r.idea.plugin.generator.gui.service.StorageService;
import org.r.idea.plugin.generator.impl.Constants;
import org.r.idea.plugin.generator.impl.config.ConfigImpl;

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
        Config config = getConfig(state);
        indicator.setFraction(0.3);
        Generator generator = new Generator(config);
        generator.doGenerate();
        indicator.setFraction(1.0);
        indicator.setText("finish");

        StatusBar statusBar = WindowManager.getInstance().getStatusBar(project);
        JBPopupFactory.getInstance()
                .createHtmlTextBalloonBuilder("building finish", MessageType.INFO, null)
                .setFadeoutTime(7500)
                .createBalloon()
                .show(RelativePoint.getCenterOf(statusBar.getComponent()), Position.atRight);

    }

    private Config getConfig(SettingState state) {

        List<String> interfacePath = new ArrayList<>(Arrays.asList(state.getInterfaceFilePaths().split(Constants.SPLITOR)));

        Config config = new ConfigImpl(interfacePath, state.getOutputFilePaths(), state.getBaseClass());


        return config;
    }


}
