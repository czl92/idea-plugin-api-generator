package org.r.idea.plugin.generator.api.ui;

import com.intellij.openapi.ui.DialogBuilder;
import com.intellij.openapi.ui.DialogWrapper;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * @ClassName TablePathPanel
 * @Author Casper
 * @DATE 2019/6/12 18:38
 **/
public class TablePathPanel implements ActionListener {

    private JLabel title;
    private JButton addBut;
    private JButton delBut;
    private JTable pathTable;
    private JPanel main;

    private FileTreeDialog fileTreeDialog = new FileTreeDialog();


    public JPanel getMain() {

        DefaultTableModel model = (DefaultTableModel) pathTable.getModel();
        Vector<String> header = new Vector<>();
        header.add("路径");
        model.setColumnIdentifiers(header);
        pathTable.setModel(model);
        addBut.addActionListener(this);

        delBut.addActionListener(e -> {
            DefaultTableModel model1 = (DefaultTableModel) pathTable.getModel();
            pathTable.getSelectedRow();
            model1.removeRow(pathTable.getSelectedRow());
            pathTable.setModel(model1);
        });
        return main;
    }

    public String getPath() {
        TableModel model = pathTable.getModel();
        int rowCount = model.getRowCount();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowCount; i++) {
            sb.append(model.getValueAt(i, 0)).append(",");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


    public void setPath(String[] srcPath) {
        if (srcPath == null) {
            return;
        }
        DefaultTableModel model = (DefaultTableModel) pathTable.getModel();
        for (String s : srcPath) {
            Vector<String> tmp = new Vector<>();
            tmp.add(s);
            model.addRow(tmp);
        }
        pathTable.setModel(model);
    }

    /**
     * Invoked when an action occurs.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        DialogBuilder builder = new DialogBuilder(main);
        builder.setCenterPanel(fileTreeDialog.getMain());
        builder.setTitle("选择目录");
        builder.removeAllActions();
        builder.addOkAction();
        builder.addCancelAction();
        boolean isOk = builder.show() == DialogWrapper.OK_EXIT_CODE;
        if (isOk) {
            String pathText = fileTreeDialog.getPathText();

            String[] tmp = {pathText.replace('\\', '/')};
            setPath(tmp);
        }

    }

    public String getTitle() {
        return title.getText();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }
}
