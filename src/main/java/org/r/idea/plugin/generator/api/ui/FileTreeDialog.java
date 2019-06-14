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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Enumeration;
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
import org.r.idea.plugin.generator.api.util.NodeUtil;

/**
 * @ClassName FileTreeDialog
 * @Author Casper
 * @DATE 2019/6/13 9:32
 **/
public class FileTreeDialog{

    private JTextField pathText;
    private JPanel main;
    private JScrollPane treePanel;
    private Tree fileTree;

    public FileTreeDialog() {
        pathText.addKeyListener(new KeyAdapter() {


            @Override
            public void keyReleased(KeyEvent keyEvent) {
                fileTree.repaint();
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) fileTree.getModel().getRoot();
                MyTreeNode child = (MyTreeNode) node.getFirstChild();
                System.out.println(child.getTitle());
                System.out.println(child.getChildCount());
                child.remove(1);
            }
        });
    }

    public JPanel getMain() {

        init(null);

        return main;
    }


    public void init(String initPath) {

        File[] files = File.listRoots();

        DefaultMutableTreeNode root = new DefaultMutableTreeNode();
        List<MyTreeNode> myTreeNodes = new ArrayList<>();
        for (File file : files) {
            MyTreeNode node = new MyTreeNode(file.getPath().substring(0, 1), file);
            System.out.println(node.getFilePath() + "  " + node.getTitle());
            myTreeNodes.add(node);
        }
        myTreeNodes = myTreeNodes.stream().sorted((Comparator.comparing(MyTreeNode::getFilePath))).collect(Collectors.toList());
        myTreeNodes.forEach(root::add);
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
        List<MyTreeNode> myTreeNodeList = new ArrayList<>();
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null) {
                for (File tmp : files) {
                    if (tmp.isFile()) {
                        continue;
                    }
                    MyTreeNode childNode = new MyTreeNode(tmp.getName(), tmp);
                    myTreeNodeList.add(childNode);
                }
                myTreeNodeList = myTreeNodeList.stream().sorted((Comparator.comparing(MyTreeNode::getFilePath))).collect(Collectors.toList());
                myTreeNodeList.forEach(node::add);
            }
        }
    }


    public void setPathText(String pathText) {
        this.pathText.setText(pathText);
    }

    public String getPathText() {
        return this.pathText.getText();
    }

}
