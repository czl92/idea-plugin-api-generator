package org.r.idea.plugin.generator.impl.probe;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectManager;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.local.CoreLocalFileSystem;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiDirectory;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.PsiManager;
import java.util.ArrayList;
import java.util.List;
import org.r.idea.plugin.generator.core.indicators.InterfaceIndicator;
import org.r.idea.plugin.generator.core.probe.Probe;
import org.r.idea.plugin.generator.impl.Utils;
import org.r.idea.plugin.generator.impl.indicators.InterfaceIndicatorImpl;
import org.r.idea.plugin.generator.utils.CollectionUtils;

/**
 * @ClassName Probe
 * @Author Casper
 * @DATE 2019/6/22 12:39
 **/
public class FileProbe implements Probe {


    private InterfaceIndicator interfaceIndicator = new InterfaceIndicatorImpl();


    @Override
    public List<PsiClass> getAllInterfaceClass(List<String> interfaceFilePath) {
        CoreLocalFileSystem coreLocalFileSystem = new CoreLocalFileSystem();
        Project project = ProjectManager.getInstance().getOpenProjects()[0];
        List<PsiClass> result = new ArrayList<>();
        for (String path : interfaceFilePath) {
            VirtualFile pathFile = coreLocalFileSystem.findFileByPath(path);
            if (pathFile == null) {
                continue;
            }
            PsiDirectory directory = PsiManager.getInstance(project).findDirectory(pathFile);
            List<PsiClass> classes = getPsiClassRecur(directory);
            if (CollectionUtils.isNotEmpty(classes)) {
                result.addAll(classes);
            }
        }
        return result;
    }

    @Override
    public void saveDoc(List<String> docList) {

    }

    private List<PsiClass> getPsiClassRecur(PsiDirectory directory) {
        if (directory == null) {
            return null;
        }
        List<PsiClass> result = new ArrayList<>();
        PsiElement[] children = directory.getChildren();

        for (PsiElement e : children) {
            if (e instanceof PsiJavaFile) {
                PsiClass psiClass = ((PsiJavaFile) e).getClasses()[0];
                if (interfaceIndicator.isInterface(psiClass)) {
                    result.add(psiClass);
                }
            } else if (e instanceof PsiDirectory) {
                List<PsiClass> subClass = getPsiClassRecur((PsiDirectory) e);
                if (CollectionUtils.isNotEmpty(subClass)) {
                    result.addAll(subClass);
                }
            }

        }
        return result;
    }


}
