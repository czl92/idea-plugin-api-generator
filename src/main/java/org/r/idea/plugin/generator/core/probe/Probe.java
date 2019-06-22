package org.r.idea.plugin.generator.core.probe;

import com.intellij.psi.PsiClass;
import java.util.List;

/**
 * @ClassName Probe
 * @Author Casper
 * @DATE 2019/6/21 16:54
 **/

public interface Probe {

    List<PsiClass> getAllInterfaceClass(List<String> interfaceFilePath);

    void saveDoc(List<String> docList);

}
