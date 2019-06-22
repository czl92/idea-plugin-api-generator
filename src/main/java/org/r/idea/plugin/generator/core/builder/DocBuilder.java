package org.r.idea.plugin.generator.core.builder;

import java.util.List;
import org.r.idea.plugin.generator.core.nodes.Node;

/**
 * @ClassName DocBuilder
 * @Author Casper
 * @DATE 2019/6/21 17:02
 **/

public interface DocBuilder {


    String buildDoc(Node node);

    List<String> buildDoc(List<Node> nodes);

}
