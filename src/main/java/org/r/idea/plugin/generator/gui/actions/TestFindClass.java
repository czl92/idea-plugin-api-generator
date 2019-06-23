package org.r.idea.plugin.generator.gui.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.local.CoreLocalFileSystem;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import gherkin.lexer.No;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.r.idea.plugin.generator.core.nodes.Node;
import org.r.idea.plugin.generator.impl.nodes.InterfaceNode;
import org.r.idea.plugin.generator.impl.nodes.ParamNode;
import org.r.idea.plugin.generator.impl.parser.InterfaceParser;
import org.r.idea.plugin.generator.impl.parser.ParamParser;
import org.r.idea.plugin.generator.impl.probe.FileProbe;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * @ClassName TestFindClass
 * @Author Casper
 * @DATE 2019/6/20 16:51
 **/
public class TestFindClass extends AnAction {


    @Override
    public void actionPerformed(AnActionEvent e) {

//        FileProbe fileProbe = new FileProbe();
//        List<String> paths = new ArrayList<>();
//        paths.add("F:/project/project/api-doc/src/main/java/testfile");
//        List<PsiClass> allInterfaceClass = fileProbe.getAllInterfaceClass(paths);
//        allInterfaceClass.size();
//
//        InterfaceParser interfaceParser = new InterfaceParser();
//        List<Node> in = new ArrayList<>();
//        for (PsiClass p : allInterfaceClass) {
//            try {
//                Node parse = interfaceParser.parse(p);
//                in.add(parse);
//            } catch (ClassNotFoundException ex) {
//                ex.printStackTrace();
//            }
//        }
//        in.size();


        URL resource = this.getClass().getResource("/container.jar");
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        try {
            Class<?> aClass = Class.forName("com.sun.tools.javac.api.JavacTool");
            Method create = aClass.getMethod("create");
            javaCompiler = (JavaCompiler) create.invoke(null);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e1) {
            e1.printStackTrace();
        }
        if(javaCompiler == null){
            Messages.showInfoMessage("找不到编译器","提示");
        }
    }


}