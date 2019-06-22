package org.r.idea.plugin.generator.core.probe;

import com.intellij.psi.PsiClass;
import java.util.List;

/**
 * @ClassName FileProbe
 * @Author Casper
 * @DATE 2019/6/21 16:54
 **/

public interface FileProbe {

    List<PsiClass> getAllInterfaceClass();

    void saveDoc(List<String> docList);

}
