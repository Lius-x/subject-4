package com.liusx.ioc.test;

import com.google.common.base.Strings;
import org.apache.commons.lang3.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

public class Dom4jTest {

    public static void main(String[] args) throws DocumentException {
        new Dom4jTest().test();
    }

    public void test() throws DocumentException {
        SAXReader saxReader = new SAXReader();
        Document document = saxReader.read(getResourcesAsStream("applicationContext.xml"));
        Element rootElement = document.getRootElement();

        // 递归
        getNodes(rootElement);
    }

    public void getNodes(Element root) {
        System.out.println("获取根节点:" + root);
        List<Attribute> attributes = root.attributes();
        for (Attribute attribute : attributes) {
            System.out.println(attribute.getName() + "=========" + attribute.getText());
        }
        String textTrim = root.getTextTrim();
        if (!StringUtils.isEmpty(textTrim)) {
            System.out.println(textTrim);
        }
        Iterator<Element> elementIterator = root.elementIterator();
        while (elementIterator.hasNext()) {
            Element element = elementIterator.next();
            getNodes(element);
        }
    }

    public InputStream getResourcesAsStream(String xmlPath) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(xmlPath);
        return inputStream;
    }
}
