package org.r.idea.plugin.generator.core;

import com.intellij.psi.PsiClass;
import java.util.ArrayList;
import java.util.List;
import org.r.idea.plugin.generator.core.config.Config;
import org.r.idea.plugin.generator.core.exceptions.ClassNotFoundException;
import org.r.idea.plugin.generator.core.nodes.Node;

/**
 * @ClassName Generator
 * @Author Casper
 * @DATE 2019/6/21 16:51
 **/
public class Generator {


    private Config config;

    public Generator(Config config) {
        this.config = config;
    }

    public void doGenerate() {
        List<PsiClass> interfaceClass = config.getFileProbe().getAllInterfaceClass(config.getInterfaceFilesPath());

        List<Node> interfaceNode = new ArrayList<>();
        for (PsiClass target : interfaceClass) {
            try {
                Node parse = config.getInterfaceParser().parse(target);
                interfaceNode.add(parse);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        List<String> docList = config.getDocBuilder().buildDoc(interfaceNode);

        config.getFileProbe().saveDoc(docList);


    }


}
