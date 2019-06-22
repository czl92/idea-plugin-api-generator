package org.r.idea.plugin.generator.gui.ui;

import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import org.r.idea.plugin.generator.impl.Constants;
import org.r.idea.plugin.generator.utils.StringUtils;
import org.r.idea.plugin.generator.gui.util.FilePathUtil;

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
            DefaultTableModel tmp = (DefaultTableModel) pathTable.getModel();
            int[] selectedRows = pathTable.getSelectedRows();
            for(int i = selectedRows.length-1;i>=0;i--){
                tmp.removeRow(selectedRows[i]);
            }
            pathTable.setModel(tmp);
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
        setPath(new ArrayList<>(Arrays.asList(srcPath)));
    }

    public void setPath(List<String> srcPath) {
        if (srcPath == null) {
            return;
        }
        /*获取现有的路径*/
        String path = getPath();
        /*如果现有的路径存在，则先过滤重复的*/
        if (StringUtils.isNotEmpty(path)) {
            List<String> tmp = new ArrayList<>(Arrays.asList(path.split(Constants.SPLITOR)));
            srcPath.removeAll(tmp);
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
        Project[] openProjects = ProjectManager.getInstance().getOpenProjects();
        Project curProject = null;
        if (openProjects.length > 0) {
            curProject = openProjects[0];
        }
        FileChooser.chooseFiles(
            new FileChooserDescriptor(false, true, false, false, false, true),
            curProject,
            null,
            t -> {
                List<String> result = new ArrayList<>();
                for (int i = 0; i < t.size(); i++) {
                    result.add(FilePathUtil.formatPath(t.get(i).getPath()));
                }
                setPath(result);
            });

    }

    public String getTitle() {
        return title.getText();
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }
}
