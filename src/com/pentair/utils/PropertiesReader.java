package com.pentair.utils;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/****
 * 读取properties文件
 *
 * @author Administrator
 */
public class PropertiesReader {

    private static PropertiesReader instance = null;

    private Properties properties = null;


    private PropertiesReader(String path) {
        properties = this.createProperties(path);
        if (properties == null)
            properties = new Properties();
    }

    /**
     * 默认读取struts.propertis文件
     *
     * @return 返回PropertiesReader的实例
     */
    public static PropertiesReader getIntance() {
        if (instance == null)
            instance = new PropertiesReader("/application.properties");
        return instance;
    }

    /**
     * 读取指定路径的properties文件
     *
     * @param path properties文件路径
     * @return 返回PropertiesReader的实例
     */
    public static PropertiesReader getIntance(String path) {
        if (path == null || "".equals(path)) {
            return getIntance();
        }
        instance = new PropertiesReader(path);
        return instance;
    }

    /**
     * 根据资源名获取资源内容
     *
     * @param {String} key 资源文件内key
     * @param {Stirng} defaultValue 默认值
     * @reaurn String key对应的资源内容
     */
    public String getProperty(String key, String defaultValue) {
        if (key == null || "".equals(key)) {
            return "";
        }
        return properties.getProperty(key, defaultValue);
    }

    private Properties createProperties(String path) {
        Properties p = null;
        Object obj = null;
        try {
            obj = getClass().getResourceAsStream(path);
            if (obj == null)
                obj = new FileInputStream(path);
        } catch (IOException e) {
            System.err.println("System can not find the property!");
        }
        if (obj != null) {
            try {
                p = new Properties();
                p.load((InputStream) obj);
                ((InputStream) obj).close();
            } catch (IOException e) {
                p = null;
            }
        }
        return p;
    }

    /**
     * 通过输入的properties文件的key得到value
     *
     * @param key
     * @return value
     */
    public String getProperty(String key) {
        if (key == null || "".equals(key))
            return "";
        return properties.getProperty(key);
    }
}
