package org.r.idea.plugin.generator.api.ui;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import org.r.idea.plugin.generator.api.Constants;
import org.r.idea.plugin.generator.api.beans.SettingState;

/**
 * @ClassName SettingPanel
 * @Author Casper
 * @DATE 2019/6/12 17:33
 **/
public class SettingPanel {

    private JTabbedPane tabbedPane1;
    private JPanel main;
    private TablePathPanel srcPathPanel = new TablePathPanel();
    private TablePathPanel interfacePathPanel = new TablePathPanel();
    private OtherSettingPanel otherSettingPanel = new OtherSettingPanel();

    public JComponent getPanel(SettingState state) {

        createUIComponents(state);
        return main;
    }


    private void createUIComponents(SettingState state) {
        tabbedPane1.add("src file path", srcPathPanel.getMain());
        srcPathPanel.setTitle("选择源代码目录路径");
        tabbedPane1.add("interface file path", interfacePathPanel.getMain());
        interfacePathPanel.setTitle("选择接口文件目录路径");
        tabbedPane1.add("other setting", otherSettingPanel.getMain(null));
        if (state != null) {
            init(state);
        }


    }

    public void init(SettingState state) {
        setSrcFilePathText(state.getSrcFilePaths());
        setInterfaceFileText(state.getInterfaceFilePaths());
        setTemplateText(state.getTemplateFilePaths());
        setOutputFileText(state.getOutputFilePaths());
        setNonsupportClassText(state.getNonsupportClass());
        setCollectionClassText(state.getCollectionClass());
        setBaseClassText(state.getBaseClass());
    }

    public String getSrcFilePathText() {
        return srcPathPanel.getPath();
    }

    public void setSrcFilePathText(String srcFilePathText) {
        if (isNotVaildPath(srcFilePathText)) {
            return;
        }
        srcPathPanel.setPath(srcFilePathText.split(Constants.SPLITOR));
    }

    public String getInterfaceFileText() {
        return interfacePathPanel.getPath();
    }

    public void setInterfaceFileText(String interfaceFileText) {
        if (isNotVaildPath(interfaceFileText)) {
            return;
        }
        interfacePathPanel.setPath(interfaceFileText.split(Constants.SPLITOR));
    }

    public String getOutputFileText() {
        return otherSettingPanel.getOutputFileText();
    }

    public void setOutputFileText(String text) {
        otherSettingPanel.setOutputFileText(text);
    }

    public String getBaseClassText() {
        return otherSettingPanel.getBaseClassText();
    }

    public void setBaseClassText(String text) {
        otherSettingPanel.setBaseClassText(text);
    }

    public String getCollectionClassText() {
        return otherSettingPanel.getCollectionClassText();
    }

    public void setCollectionClassText(String text) {
        otherSettingPanel.setCollectionClassText(text);
    }

    public String getNonsupportClassText() {
        return otherSettingPanel.getNonsupportClassText();
    }

    public void setNonsupportClassText(String text) {
        otherSettingPanel.setNonsupportClassText(text);
    }

    public String getTemplateText() {
        return otherSettingPanel.getTemplateText();
    }

    public void setTemplateText(String text) {
        otherSettingPanel.setTemplateText(text);
    }


    private boolean isNotVaildPath(String path) {
        return path == null || "".equals(path);
    }


}
