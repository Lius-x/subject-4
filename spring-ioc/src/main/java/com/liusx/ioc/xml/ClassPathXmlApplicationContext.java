package com.liusx.ioc.xml;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClassPathXmlApplicationContext {

    // 配置文件路径
    private String xmlPath;

    static Map<String, Object> singletonObjects = new ConcurrentHashMap<>();

    public ClassPathXmlApplicationContext(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    /**
     * 获取Bean
     *
     * @param id Bean的id
     * @return
     * @throws Exception
     */
    public Object getBean(String id) throws Exception {
        if (StringUtils.isEmpty(id)) {
            throw new IllegalAccessException("id不能为空");
        }
        if (singletonObjects.containsKey(id)) {
            return singletonObjects.get(id);
        }
        // map里面没有bean == 创建bean -- 解析xml -- 把bean 返回
        // 读取xml文件
        List<Element> readXml = readXml();
        if (readXml == null) {
            throw new Exception("配置文件为空");
        }
        // 获取class名称
        String className = findBeanElementClass(readXml, id);
        if (className == null) {
            throw new Exception("配置文件没有这个class");
        }
        // 创建对象
        return newInstance(className);
    }

    /**
     * 获取Class名称
     *
     * @param readXml 读取到的xml内容
     * @param beanId  查找的BeanID
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public String findBeanElementClass(List<Element> readXml, String beanId) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
        for (Element element : readXml) {
            String xmlBeanId = element.attributeValue("id");
            if (beanId == xmlBeanId) {
                continue; // 继续执行
            }
            String xmlClass = element.attributeValue("class");
            singletonObjects.put(xmlBeanId, newInstance(xmlClass));
            return xmlClass;
        }
        return null;
    }

    /**
     * 读取xml文件
     *
     * @return xml解析内容
     * @throws DocumentException dom4j
     */
    public List<Element> readXml() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(getResourcesAsStream("applicationContext.xml"));
        Element rootElement = document.getRootElement();
        List<Element> elements = rootElement.elements();
        return elements;

    }

    /**
     * 获取上下文路径
     *
     * @param xmlPath xml路径
     * @return InputStream
     */
    public InputStream getResourcesAsStream(String xmlPath) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xmlPath);
        return inputStream;
    }

    /**
     * 反射创建对象
     *
     * @param className class名称
     * @return
     * @throws ClassNotFoundException
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public Object newInstance(String className) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> aClass = Class.forName(className);
        return aClass.newInstance();
    }

}
