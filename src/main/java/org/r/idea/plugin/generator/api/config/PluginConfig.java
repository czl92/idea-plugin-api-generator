package org.r.idea.plugin.generator.api.config;

import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import javax.swing.JComponent;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.Nls.Capitalization;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.r.idea.plugin.generator.api.beans.SettingState;
import org.r.idea.plugin.generator.api.service.StorageService;
import org.r.idea.plugin.generator.api.ui.SettingPanel;

/**
 * @ClassName PluginConfig
 * @Author Casper
 * @DATE 2019/6/10 9:56
 **/
public class PluginConfig implements SearchableConfigurable {


    private SettingPanel settingPanel;

    private StorageService storageService = StorageService.getInstance();


    /**
     * Unique configurable id. Note this id should be THE SAME as the one specified in XML.
     *
     * @see ConfigurableEP#id
     */
    @NotNull
    @Override
    public String getId() {
        return "api-generator";
    }

    /**
     * Returns the visible name of the configurable component. Note, that this method must return the display name that
     * is equal to the display name declared in XML to avoid unexpected errors.
     *
     * @return the visible name of the configurable component
     */
    @Nls(capitalization = Capitalization.Title)
    @Override
    public String getDisplayName() {
        return getId();
    }

    /**
     * Creates new Swing form that enables user to configure the settings. Usually this method is called on the EDT, so
     * it should not take a long time.
     *
     * Also this place is designed to allocate resources (subscriptions/listeners etc.)
     *
     * @return new Swing form to show, or {@code null} if it cannot be created
     * @see #disposeUIResources
     */
    @Nullable
    @Override
    public JComponent createComponent() {
        if (null == settingPanel) {
            settingPanel = new SettingPanel();
        }
        return settingPanel.getPanel(storageService.getState());
    }

    /**
     * Indicates whether the Swing form was modified or not. This method is called very often, so it should not take a
     * long time.
     *
     * @return {@code true} if the settings were modified, {@code false} otherwise
     */
    @Override
    public boolean isModified() {
        SettingState state = storageService.getState();
        if (state != null && settingPanel != null) {
            if (!state.getSrcFilePaths().equals(settingPanel.getSrcFilePathText())) {
                return true;
            }
            if (!state.getInterfaceFilePaths().equals(settingPanel.getInterfaceFileText())) {
                return true;
            }
            if (!state.getOutputFilePaths().equals(settingPanel.getOutputFileText())) {
                return true;
            }
            if (!state.getBaseClass().equals(settingPanel.getBaseClassText())) {
                return true;
            }
            if (!state.getCollectionClass().equals(settingPanel.getCollectionClassText())) {
                return true;
            }
            if (!state.getTemplateFilePaths().equals(settingPanel.getTemplateText())) {
                return true;
            }
            if (!state.getNonsupportClass().equals(settingPanel.getNonsupportClassText())) {
                return true;
            }
            return false;
        }
        return true;
    }

    /**
     * Stores the settings from the Swing form to the configurable component. This method is called on EDT upon user's
     * request.
     *
     * @throws ConfigurationException if values cannot be applied
     */
    @Override
    public void apply() throws ConfigurationException {
        SettingState state = new SettingState();
        state.setSrcFilePaths(settingPanel.getSrcFilePathText());
        state.setInterfaceFilePaths(settingPanel.getInterfaceFileText());
        state.setOutputFilePaths(settingPanel.getOutputFileText());
        state.setBaseClass(settingPanel.getBaseClassText());
        state.setCollectionClass(settingPanel.getCollectionClassText());
        state.setNonsupportClass(settingPanel.getNonsupportClassText());
        state.setTemplateFilePaths(settingPanel.getTemplateText());
        storageService.loadState(state);
    }
}
