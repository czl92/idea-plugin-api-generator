package org.r.idea.plugin.generator.api.ui;

import com.intellij.icons.AllIcons;
import com.intellij.icons.AllIcons.Nodes;
import com.intellij.openapi.ui.MasterDetailsComponent.MyNode;
import com.intellij.ui.ColoredTreeCellRenderer;
import com.intellij.ui.SimpleTextAttributes;
import com.intellij.ui.components.JBScrollPane;
import com.intellij.ui.treeStructure.Tree;
import com.intellij.util.ui.UIUtil;
import com.intellij.util.ui.tree.TreeUtil;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.event.TreeExpansionEvent;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeWillExpandListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.ExpandVetoException;
import org.jetbrains.annotations.NotNull;
import org.r.idea.plugin.generator.api.StringUtils;
import org.r.idea.plugin.generator.api.ui.component.MyTreeNode;

/**
 * @ClassName FileTreeDialog
 * @Author Casper
 * @DATE 2019/6/13 9:32
 **/
public class FileTreeDialog {

    private JTextField pathText;
    private JPanel main;
    private JScrollPane treePanel;
    private Tree fileTree;

    public JPanel getMain() {

        init(null);

        return main;
    }


    public void init(String initPath) {

        File[] files = File.listRoots();
        List<File> fileList = sortFile(files);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        for (File file : fileList) {
            MyTreeNode node = new MyTreeNode(file.getPath().substring(0, 1), file);
            root.add(node);
        }
        DefaultTreeModel defaultTreeModel = new DefaultTreeModel(root);

        fileTree.setModel(defaultTreeModel);
        fileTree.setShowsRootHandles(true);
        UIUtil.setLineStyleAngled(fileTree);
        TreeUtil.installActions(fileTree);
        fileTree.setCellRenderer(new ColoredTreeCellRenderer() {
            @Override
            public void customizeCellRenderer(@NotNull JTree tree,
                Object value,
                boolean selected,
                boolean expanded,
                boolean leaf,
                int row,
                boolean hasFocus) {
                if (value instanceof MyTreeNode) {
                    final MyTreeNode node = (MyTreeNode) value;
                    setIcon(node.getIcon());
                    final Font font = UIUtil.getTreeFont();
                    setFont(font.deriveFont(Font.BOLD));
                    setFont(font.deriveFont(Font.PLAIN));
                    append(node.getTitle(),
                        SimpleTextAttributes.REGULAR_BOLD_ATTRIBUTES);
                }
            }
        });
        fileTree.addTreeWillExpandListener(new TreeWillExpandListener() {
            @Override
            public void treeWillExpand(TreeExpansionEvent event) throws ExpandVetoException {
                Object lastPathComponent = event.getPath().getLastPathComponent();
                if (lastPathComponent instanceof MyTreeNode) {
                    MyTreeNode curNode = (MyTreeNode) lastPathComponent;
                    resetChildNode(curNode, new File(curNode.getFilePath()));
                }

            }

            @Override
            public void treeWillCollapse(TreeExpansionEvent event) throws ExpandVetoException {

            }
        });
        fileTree.addTreeSelectionListener(event -> {
            Object lastPathComponent = event.getPath().getLastPathComponent();
            if (lastPathComponent instanceof MyTreeNode) {
                MyTreeNode curNode = (MyTreeNode) lastPathComponent;
                setPathText(curNode.getFilePath());
            }
        });

        if (StringUtils.isEmpty(initPath)) {
            return;
        }
    }

    private void resetChildNode(MyTreeNode node, File file) {
        if (file == null || !file.exists()) {
            return;
        }
        node.removeAllChildren();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                List<File> fileList = sortFile(files);
                for (File tmp : fileList) {
                    MyTreeNode childNode = new MyTreeNode(tmp.getName(), tmp);
                    node.add(childNode);
                }
            }
        }
    }

    private List<File> sortFile(File[] file) {
        List<File> fileList = new ArrayList<>(Arrays.asList(file));
        return fileList.stream().sorted(Comparator.comparing(File::getName)).collect(Collectors.toList());
    }


    public void setPathText(String pathText) {
        this.pathText.setText(pathText);
    }

    public String getPathText() {
        return this.pathText.getText();
    }

}
