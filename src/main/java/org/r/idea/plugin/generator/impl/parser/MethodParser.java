package org.r.idea.plugin.generator.impl.parser;

import com.intellij.psi.PsiAnnotation;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiMethod;
import org.r.idea.plugin.generator.impl.Utils;
import org.r.idea.plugin.generator.impl.nodes.MethodNode;

/**
 * @ClassName MethodParser
 * @Author Casper
 * @DATE 2019/6/21 17:38
 **/
public class MethodParser {

    private String[] reqAnno = {
        "org.springframework.web.bind.annotation.RequestMapping",
        "org.springframework.web.bind.annotation.PostMapping",
        "org.springframework.web.bind.annotation.PutMapping",
        "org.springframework.web.bind.annotation.GetMapping",
        "org.springframework.web.bind.annotation.DeleteMapping"
    };

    public MethodNode parse(PsiMethod target) {

        MethodNode methodNode = new MethodNode();

        /*设置描述*/
        if (target.getDocComment() != null) {
            methodNode.setDesc(Utils.getDocCommentDesc(target.getDocComment().getDescriptionElements()));
        }
        /*处理请求方法和url*/

        return methodNode;
    }


    private boolean isAvailable(MethodNode node, PsiMethod target) {

        PsiAnnotation[] annotations = target.getAnnotations();

        boolean isAvailable = !Utils.isContainAnnotation("java.lang.Deprecated", annotations);

        if (isAvailable) {
            PsiAnnotation req = null;

        }
        return false;
    }


}
