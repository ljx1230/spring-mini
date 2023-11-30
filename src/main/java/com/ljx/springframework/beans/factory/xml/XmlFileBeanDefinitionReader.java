package com.ljx.springframework.beans.factory.xml;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.ljx.springframework.beans.BeansException;
import com.ljx.springframework.beans.PropertyValue;
import com.ljx.springframework.beans.PropertyValues;
import com.ljx.springframework.beans.factory.config.BeanDefinition;
import com.ljx.springframework.beans.factory.config.BeanReference;
import com.ljx.springframework.beans.factory.support.AbstractBeanDefinitionReader;
import com.ljx.springframework.beans.factory.support.BeanDefinitionRegistry;
import com.ljx.springframework.core.io.Resource;
import com.ljx.springframework.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.InputStream;

/**
 * @Author: ljx
 * @Date: 2023/11/30 19:30
 * 读取配置在xml文件中的bean定义信息
 */
public class XmlFileBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";

    public XmlFileBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    @Override
    public void loadBeanDefinitions(Resource resource) throws BeansException {
        try {
            InputStream inputStream = resource.getInputStream();
            try {
                doLoadBeanDefinitions(inputStream);
            } finally {
                inputStream.close();
            }
        } catch (Exception e) {
            throw new BeansException("IOException 解析xml文档失败:" + resource, e);
        }
    }

    /**
     * @param inputStream
     * @throws Exception
     * 做具体xml文档解析
     */
    protected void doLoadBeanDefinitions(InputStream inputStream) throws Exception{
        Document document = XmlUtil.readXML(inputStream);

        Element root = document.getDocumentElement(); //

        NodeList childNodes = root.getChildNodes();

        for(int i = 0;i < childNodes.getLength();++i) {
            if(childNodes.item(i) instanceof Element) {
                // 子结点如果是一个标签
                if(BEAN_ELEMENT.equals(((Element) childNodes.item(i)).getNodeName())) {
                    // 子标签为bean，解析
                    Element bean = (Element) childNodes.item(i);
                    // 获取id、name、class属性
                    String id = bean.getAttribute(ID_ATTRIBUTE);
                    String name = bean.getAttribute(NAME_ATTRIBUTE);
                    String className = bean.getAttribute(CLASS_ATTRIBUTE);

                    Class<?> clazz = Class.forName(className);

                    // id属性的优先级高于name属性
                    String beanName = StrUtil.isNotEmpty(id) ? id : name;

                    if (StrUtil.isEmpty(beanName)) {
                        //如果id和name都为空，将类名的第一个字母转为小写后作为bean的名称
                        beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                    }

                    BeanDefinition beanDefinition = new BeanDefinition(clazz);

                    // 注入属性
                    for(int j = 0;j < bean.getChildNodes().getLength();++j) {
                        if (bean.getChildNodes().item(j) instanceof Element) {
                            if(PROPERTY_ELEMENT.equals(((Element)bean.getChildNodes().item(j)).getNodeName())) {
                                // 解析property标签
                                Element property = (Element) bean.getChildNodes().item(j);
                                String nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
                                String valueAttribute = property.getAttribute(VALUE_ATTRIBUTE);
                                String refAttribute = property.getAttribute(REF_ATTRIBUTE);

                                Object value = valueAttribute;

                                if(StrUtil.isNotEmpty(refAttribute)) {
                                    value = new BeanReference(refAttribute);
                                }
                                PropertyValue propertyValue = new PropertyValue(nameAttribute,value);
                                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                            }
                        }
                    }
                    getRegistry().registerBeanDefinitionRegistry(beanName,beanDefinition);
                }
            }
        }

    }

    @Override
    public void loadBeanDefinitions(String location) throws BeansException {
        ResourceLoader resourceLoader = getResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        loadBeanDefinitions(resource);
    }
}
