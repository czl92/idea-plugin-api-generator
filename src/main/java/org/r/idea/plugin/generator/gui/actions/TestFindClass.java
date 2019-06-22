package org.r.idea.plugin.generator.gui.actions;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.psi.search.PsiShortNamesCache;
import java.util.List;
import org.r.idea.plugin.generator.impl.nodes.ParamNode;
import org.r.idea.plugin.generator.impl.parser.ParamParser;

/**
 * @ClassName TestFindClass
 * @Author Casper
 * @DATE 2019/6/20 16:51
 **/
public class TestFindClass extends AnAction {


    private String[] arrayTypes = {"[]", "List", "ArrayList"};


    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        assert project != null;
        PsiClass testController = PsiShortNamesCache.getInstance(project)
            .getClassesByName("TestController", GlobalSearchScope.allScope(project))[0];

        testController.getAnnotations();
        PsiMethod[] methods = testController.getMethods();
        PsiMethod method = methods[0];

        ParamParser paramParser = new ParamParser();
        List<ParamNode> parse = paramParser.parse(method);
        System.out.println(parse);

    }


}
