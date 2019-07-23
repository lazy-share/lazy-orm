package com.lazy.orm.scaner;

import com.lazy.orm.annotation.Mapper;
import com.lazy.orm.exception.InitException;
import com.lazy.orm.session.Configuration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;

/**
 * <p>
 * Mapper扫描器
 * </p>
 *
 * @author laizhiyuan
 * @since 2019/7/23.
 */
public class MapperScan {

    private Configuration config;

    public MapperScan(Configuration config) {
        this.config = config;
    }


    public void scan() {

        String[] packages = config.getMapperScanPackage().split(",");

        for (String pkg : packages){
            try {
                Enumeration<URL> dirs = Thread.currentThread().getContextClassLoader().getResources(pkg);
                while (dirs.hasMoreElements()) {
                    URL url = dirs.nextElement();
                    String filePath = URLDecoder.decode(url.getFile(), "UTF-8").substring(1);
                    doScan(new File(filePath), filePath.replaceAll("\\\\", "/").split(pkg)[0]);
                }
            } catch (Exception ex) {
                throw new InitException("加载Mapper异常", ex);
            }
        }

    }

    /**
     * 扫描Class文件
     *
     * @param classDir
     */
    @SuppressWarnings({"all"})
    private void doScan(File classDir, String rootPath) throws IOException {
        if (classDir == null) {
            return;
        }
        File[] classFiles = classDir.listFiles();
        for (File f : classFiles) {
            if (f.isDirectory()) {
                doScan(f, rootPath);
            } else {
                //不是所有的文件都会被接收
                if (!f.getName().endsWith(".class")) {
                    continue;
                }
                String className = f.getPath().replaceAll("\\\\", "/")
                        .replaceAll(rootPath.replaceAll("\\\\", "/"), "")
                        .replaceAll("/", "\\.")
                        .replaceAll(".class", "");

                Class clazz = null;
                try {
                    if (className.startsWith(config.getMapperScanPackage().replaceAll("/", "\\."))) {
                        clazz = Thread.currentThread().getContextClassLoader().loadClass(className);
                        if (!clazz.isAnnotationPresent(Mapper.class)) {
                            continue;
                        }

                        config.addMapper(clazz);
                    }
                } catch (Exception ex) {
                    if (clazz != null) {
                        config.addMapper(clazz);
                    }
                }
            }
        }
    }
}
