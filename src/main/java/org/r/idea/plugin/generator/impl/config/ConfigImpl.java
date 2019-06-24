package org.r.idea.plugin.generator.impl.config;

import java.util.List;
import org.r.idea.plugin.generator.core.builder.DocBuilder;
import org.r.idea.plugin.generator.core.builder.JarBuilder;
import org.r.idea.plugin.generator.core.config.Config;
import org.r.idea.plugin.generator.core.parser.Parser;
import org.r.idea.plugin.generator.core.probe.Probe;
import org.r.idea.plugin.generator.impl.builder.DocBuilderImpl;
import org.r.idea.plugin.generator.impl.builder.JarBuilderImpl;
import org.r.idea.plugin.generator.impl.parser.InterfaceParser;
import org.r.idea.plugin.generator.impl.probe.FileProbe;

/**
 * @ClassName ConfigImpl
 * @Author Casper
 * @DATE 2019/6/24 13:49
 **/
public class ConfigImpl implements Config {


    private Parser parser;

    private Probe probe;

    private DocBuilder docBuilder;

    private JarBuilder jarBuilder;

    private List<String> interfaceFilePaths;

    private String workSpace;


    public ConfigImpl(List<String> interfaceFilePaths, String workSpace) {
        parser = new InterfaceParser();
        probe = new FileProbe();
        docBuilder = new DocBuilderImpl();
        jarBuilder = new JarBuilderImpl();
        this.interfaceFilePaths = interfaceFilePaths;
        this.workSpace = workSpace;
    }

    public ConfigImpl(Parser parser, Probe probe, DocBuilder docBuilder,
        JarBuilder jarBuilder, List<String> interfaceFilePaths, String workSpace) {
        this.parser = parser;
        this.probe = probe;
        this.docBuilder = docBuilder;
        this.jarBuilder = jarBuilder;
        this.interfaceFilePaths = interfaceFilePaths;
        this.workSpace = workSpace;
    }

    @Override
    public Parser getInterfaceParser() {
        return parser;
    }

    @Override
    public Probe getFileProbe() {
        return probe;
    }

    @Override
    public DocBuilder getDocBuilder() {
        return docBuilder;
    }

    @Override
    public JarBuilder getJarBuilder() {
        return jarBuilder;
    }

    @Override
    public List<String> getInterfaceFilesPath() {
        return interfaceFilePaths;
    }

    @Override
    public String getWorkSpace() {
        return workSpace;
    }
}
