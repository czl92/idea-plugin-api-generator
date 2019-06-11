package org.r.idea.plugin.generator.api.ui;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTextField;
import org.r.idea.plugin.generator.api.beans.SettingState;

/**
 * @ClassName SettingPanel
 * @Author Casper
 * @DATE 2019/6/10 9:37
 **/
public class SettingPanel {

    private JTextField srcFileText;
    private JTextField intrFileText;
    private JTextField outputFileText;
    private JTextField baseClassText;
    private JTextField collClassText;
    private JTextField nonsupClassText;
    private JButton srcFileSelectBut;
    private JButton intrFileBut;
    private JButton outputFileBut;
    private JButton baseClassBut;
    private JButton collClassBut;
    private JButton nonsupClassBut;
    private JPanel mainPanel;


    public JComponent getPanel(SettingState state) {
        if (state != null) {
            setSrcFilePathText(state.getSrcFilePaths());
            setInterfaceFileText(state.getInterfaceFilePaths());
            setOutputFileText(state.getOutputFilePaths());
            setBaseClassText(state.getBaseClass());
            setCollectionClassText(state.getCollectionClass());
            setNonsupportClassText(state.getNonsupportClass());
        }

        return mainPanel;
    }

    public String getSrcFilePathText() {
        return srcFileText.getText();
    }

    public String getInterfaceFileText() {
        return intrFileText.getText();
    }

    public String getOutputFileText() {
        return outputFileText.getText();
    }

    public String getBaseClassText() {
        return baseClassText.getText();
    }

    public String getCollectionClassText() {
        return collClassText.getText();
    }

    public String getNonsupportClassText() {
        return nonsupClassText.getText();
    }

    public void setSrcFilePathText(String text) {
        srcFileText.setText(text);
    }

    public void setInterfaceFileText(String text) {
        intrFileText.setText(text);
    }

    public void setOutputFileText(String text) {
        outputFileText.setText(text);
    }

    public void setBaseClassText(String text) {
        baseClassText.setText(text);
    }

    public void setCollectionClassText(String text) {
        collClassText.setText(text);
    }

    public void setNonsupportClassText(String text) {
        nonsupClassText.setText(text);
    }


}
