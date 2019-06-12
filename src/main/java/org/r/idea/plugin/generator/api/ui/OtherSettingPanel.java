package org.r.idea.plugin.generator.api.ui;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.r.idea.plugin.generator.api.beans.SettingState;

/**
 * @ClassName OtherSettingPanel
 * @Author Casper
 * @DATE 2019/6/10 9:37
 **/
public class OtherSettingPanel {

    private JTextField outputFileText;
    private JTextField baseClassText;
    private JTextField collClassText;
    private JTextField nonsupClassText;
    private JTextField templatePathText;
    private JButton outputFileBut;
    private JPanel mainPanel;
    private JButton templateBut;
    private JLabel templatePath;


    public JPanel getMain(SettingState state) {

        initText(state);

        return mainPanel;
    }

    private void initText(SettingState state) {
        if (state == null) {
            return;
        }
        setOutputFileText(state.getOutputFilePaths());
        setBaseClassText(state.getBaseClass());
        setCollectionClassText(state.getCollectionClass());
        setNonsupportClassText(state.getNonsupportClass());
        setTemplateText(state.getTemplateFilePaths());
    }


    public String getOutputFileText() {
        return outputFileText.getText();
    }

    public void setOutputFileText(String text) {
        outputFileText.setText(text);
    }

    public String getBaseClassText() {
        return baseClassText.getText();
    }

    public void setBaseClassText(String text) {
        baseClassText.setText(text);
    }

    public String getCollectionClassText() {
        return collClassText.getText();
    }

    public void setCollectionClassText(String text) {
        collClassText.setText(text);
    }

    public String getNonsupportClassText() {
        return nonsupClassText.getText();
    }

    public void setNonsupportClassText(String text) {
        nonsupClassText.setText(text);
    }

    public String getTemplateText() {
        return templatePathText.getText();
    }

    public void setTemplateText(String text) {
        templatePathText.setText(text);
    }


}
