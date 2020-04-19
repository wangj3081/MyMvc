package com.mvc.annotation.scan;

import com.mvc.servlet.bean.ControllerAnnotationParse;
import com.mvc.servlet.bean.ControllerMapping;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ScanPackageUtil
 * @Description 扫描指定包下使用到对应注解的处理类
 * @Author wangjian
 * @Date 2020/4/19 4:36 下午
 * @Version 1.0
 **/
public class ScanPackageUtil {

    private static final Map<String, ControllerMapping>  MAP_PATH = new HashMap<>();

    public static Map<String, ControllerMapping> getMapPath() {
        return MAP_PATH;
    }

    /*
    *
     * @Author wangjian
     * @Description  扫描指定目录下的包，由此加载所有的Controller
     * @Date 6:07 下午 2020/4/19
     * @Param path:
     * @return void
     **/
    public static void scanPackage(Class<?> clazz, String path) {
        if (path == null || "".equals(path)) {
            return;
        }
        // 要扫描的包的根路径
        String basePath = clazz.getResource("/").getPath();
        System.out.println("【ScanPackageUtil】：" + basePath);
        // 当前项目中的需要被扫描的包,多个使用「;」分割
        String[] splitPath = path.split(";");
        for (String sp : splitPath) {
            // 读取配置的路径内容，将其转换成操作系统对应的文件路径
            String replace = sp.replace(".", File.separator);
//            System.out.println("替换后:" + replace);
            // 获得该文件目录或文件
            File file = new File(basePath, replace);
            listFile(basePath, file);
        }
    }


    /*
    *
     * @Author wangjian
     * @Description  将扫描的包路径做初始化处理
     * @Date 6:21 下午 2020/4/19
     * @Param basePath: 根路径
     * @Param file: 指定文件目录或文件
     * @return void
     **/
    private static void listFile(String basePath,File file) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                listFile(basePath, f); // 递归处理
            }
        } else if (file.isFile()){
            // 扫描其中标记了控制层对应注解的类，并存储映射关系
            String absolutePath = file.getAbsolutePath();
            if (absolutePath.indexOf(".class") != -1) { // 当前文件为class文件时
                String clazzPath = absolutePath.replace(basePath, "") // 去除掉根目录中的文件夹内容，只保留当前项目的包路径
                        .replace(File.separator, ".") // 做包路径的数据格式转换
                        .replace(".class", "");  // 去除.class后缀
                try {
                    ControllerAnnotationParse annotationParse = new ControllerAnnotationParse(Class.forName(clazzPath));
                    MAP_PATH.putAll(annotationParse.getMapParse());
                  /*  MAP_PATH.forEach((k,v)-> {
                        System.err.println(k + ":" + v.getClazz().toString() + ":" + v.getMethod().toString());
                    });*/
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }

        }
    }

}
