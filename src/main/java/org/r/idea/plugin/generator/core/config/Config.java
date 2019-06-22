package org.r.idea.plugin.generator.core.config;

import java.util.List;
import org.r.idea.plugin.generator.core.builder.DocBuilder;
import org.r.idea.plugin.generator.core.parser.Parser;
import org.r.idea.plugin.generator.core.probe.Probe;

/**
 * @ClassName Config
 * @Author Casper
 * @DATE 2019/6/21 16:52
 **/

public interface Config {


    Parser getInterfaceParser();

    Probe getFileProbe();

    DocBuilder getDocBuilder();

    List<String> getInterfaceFilesPath();

}
