package org.r.idea.plugin.generator.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @ClassName FileUtils
 * @Author Casper
 * @DATE 2019/6/24 10:41
 **/
public class FileUtils {


    public static void copy(OutputStream out, InputStream in) throws IOException {
        byte[] buf = new byte[4096];
        int byteReaded = 0;
        while ((byteReaded = in.read(buf)) != -1) {
            out.write(buf, 0, byteReaded);
        }
    }


}
