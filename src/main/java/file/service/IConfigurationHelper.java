package file.service;

import file.datastructure.IConfiguration;

import java.io.File;
import java.net.URL;
import java.util.HashSet;

/**
 * 读取配置文件接口
 * 
 * 从jar包及文件目录
 * 读取配置文件转化为bean
 * @author: chenyun 
 * @since: 2014年12月26日 上午12:15:23 
 * @history:
 */
public interface IConfigurationHelper {

    /**
     * 得到 class文件运行的相对目录,在web项目即为web-inf
     */
    public static final String path_relative = "classpath:";

    /**
     * 
     * @param path
     * @param encoding
     * @return 
     * @create: 2014年12月26日 上午12:18:42 chenyun
     * @history:
     */
    File loadFile(String path, String encoding);

    /**
     * 
     * @param namespace
     * @param path
     * @param encoding
     * @param traversalSet --xml中有指向其它xml的url,递归解析
     * @return 
     * @create: 2014年12月26日 上午12:21:39 chenyun
     * @history:
     */
    IConfiguration loadFileToConfiguration(String namespace, String path,
                                           String encoding, HashSet<String> traversalSet);

    /**
     * 
     * @param url
     * @param domContent
     * @return 
     * @create: 2014年12月26日 上午12:26:25 chenyun
     * @history:
     */
    IConfiguration loadDomToConfiguration(URL url, String domContent);
}
