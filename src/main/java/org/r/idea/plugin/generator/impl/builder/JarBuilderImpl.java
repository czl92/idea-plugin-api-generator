package org.r.idea.plugin.generator.impl.builder;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;
import java.util.jar.JarOutputStream;
import java.util.stream.Collectors;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import org.r.idea.plugin.generator.core.ConfigHolder;
import org.r.idea.plugin.generator.core.builder.JarBuilder;
import org.r.idea.plugin.generator.impl.Constants;
import org.r.idea.plugin.generator.utils.CollectionUtils;
import org.r.idea.plugin.generator.utils.FileUtils;

/**
 * @Author Casper
 * @DATE 2019/6/23 21:09
 **/
public class JarBuilderImpl implements JarBuilder {

    /**
     * 容器jar在本jar包中的位置
     */
    private String contarinerJar = "/container.jar";

    /**
     * 目标可运行jar包
     */
    private String productJar = "api-doc.jar";


    @Override
    public void buildJar(String srcDir, String workSpace) {

        /*查询所有的源文件*/
        List<File> fileList = ConfigHolder.getConfig().getFileProbe()
            .searchFile(srcDir, pathname -> pathname.getName().endsWith(".java"));
        List<String> srcJava = fileList.stream().map(File::getAbsolutePath).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(srcJava)) {
            System.out.println("源文件不存在");
            return;
        }
        /*编译源文件,并储存为临时文件*/
        List<File> clazz = compile(srcJava, workSpace);
        /*copy容器*/
        generateContainer(clazz, workSpace);

    }

    /**
     * 编译源文件并输入到指定的临时目录
     *
     * @param javaSrc 源文件路径信息
     * @param workSpace 工作空间
     */
    private List<File> compile(List<String> javaSrc, String workSpace) {

        /*构建编译参数*/
        int ext = 4;
        String[] filenames = new String[javaSrc.size() + ext];
        String classOutputPath = workSpace + Constants.TMP_CLASS_DIR;
        int i = 0;
        filenames[i++] = "-d";
        filenames[i++] = classOutputPath;
        filenames[i++] = "-encoding";
        filenames[i++] = "UTF-8";
        for (String path : javaSrc) {
            filenames[i++] = path;
        }
        /*获取编译器*/
        JavaCompiler javac = getJavaCompiler();
        if (javac == null) {
            throw new RuntimeException("无法获取编译器");
        }
        javac.run(null, null, null, filenames);
        /*获取class文件路径信息*/
        List<File> clazz = ConfigHolder.getConfig().getFileProbe()
            .searchFile(classOutputPath, pathname -> pathname.getName().endsWith(".class"));
        if (CollectionUtils.isEmpty(clazz)) {
            throw new RuntimeException("编译失败");
        }
        return clazz;
    }

    /**
     * 获取java编译器
     */
    private JavaCompiler getJavaCompiler() {
        JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
        if (javac == null) {
            try {
                Class<?> aClass = Class.forName("com.sun.tools.javac.api.JavacTool");
                Method create = aClass.getMethod("create");
                javac = (JavaCompiler) create.invoke(null);
            } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        return javac;
    }


    private void generateContainer(List<File> clazzs, String workSpace) {
        /*获取容器*/
        File targetJar = new File(workSpace + productJar);
        InputStream in = null;
        try (
            JarOutputStream out = new JarOutputStream(new FileOutputStream(targetJar));
            JarInputStream jarInput = new JarInputStream(this.getClass().getResourceAsStream(contarinerJar));
        ) {
            JarEntry tmpEntry = null;
            while ((tmpEntry = jarInput.getNextJarEntry()) != null) {
                out.putNextEntry(tmpEntry);
                FileUtils.copy(out, jarInput);
            }
            for (File file : clazzs) {
                JarEntry entry = new JarEntry(Constants.JAR_FILE_PATH + file.getName());
                out.putNextEntry(entry);
                in = new FileInputStream(file);
                FileUtils.copy(out, in);
                in.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
