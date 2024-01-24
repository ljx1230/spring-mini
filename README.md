# Spring简易版实现 开发手册
### 实现BeanDefinition 和 BeanDefinitionRegistry

首先，我们可以创建一个最简单的容器——BeanFactory。这个BeanFactory中定义了一个Map，我们可以根据Bean的名字，取出Bean对象。
```java
    public class BeanFactory {
    private Map<String, Object> beanMap = new HashMap<>();
    public void registerBean(String name, Object bean) {
        beanMap.put(name, bean);
    }
    public Object getBean(String name) {
        return beanMap.get(name);
    }
}
```
但是这样的话，不易于我们进行扩展，在spring中，早期的时候，
我们会通过写xml配置文件的方式去定义一些Bean的信息 ，
而这些Bean的信息，
我们会将其存放在一个叫BeanDefinition的类中，
因此我们会创建一个BeanDefinition类存放Bean的信息,
这个类中目前存储了Bean的Class类别，
并且提供了get和set方法，
```java
public class BeanDefinition {
    private Class beanClass;
    public BeanDefinition(Class beanClass) {
        this.beanClass = beanClass;
    }
    public Class getBeanClass() {
        return beanClass;
    }
    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }
}
```
另外，这些BeanDefinition的对象，
也就是每个Bean的信息我们也会将他保存到一个容器中，
因此我们创建一个接口BeanDefinitionRegistry,用于注册Bean的信息
```java
public interface BeanDefinitionRegistry {
    // 向注册表中加入BeanDefinition
    void registerBeanDefinitionRegistry(String name, BeanDefinition beanDefinition);
}
```

为了扩展性，我们将BeanFactory设置成一个接口，
这个接口提供一个方法：getBean
```java
public interface BeanFactory {
    Object getBean(String name);
}
```
在BeanFactory我们提供一个方法可以获得Bean，这个Bean既可以获得单例,
，也可以夺得prototype类型的Bean,因此我们还需要一个接口，
可以通过这个接口去得到单例Bean，
所以我们定义接口SingletonBeanRegistry，这个接口中提供一个方法，
获取单例的Bean，之后我们BeanFactory想要获取单例的Bean，
我们就可以用这个接口的方法，
```java
public interface SingletonBeanRegistry {
    Object getSingleton(String beanName);
}
```
创建了这个接口后，我们给它一个实现类吧，我们把这个实现类叫做DefaultSingletonBeanRegistry，
我们添加一个新功能，就是让我们的Bean注册器，可以往IOC容器中新加入一个Bean，
，并且我们将Map对象定义在这里。
```java
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    Map<String,Object> singletonObjects = new HashMap<>();

    @Override
    public Object getSingleton(String beanName) {
        return singletonObjects.get(beanName);
    }

    protected void addSingleton(String beanName,Object singletonObject) {
        singletonObjects.put(beanName,singletonObject);
    }

}
```
然后我们定义一个抽象类，这个抽象类AbstractBeanFactory继承自DefaultSingletonBeanRegistry，
并且实现了BeanFactory接口，因此它具有前面两者的功能，
并且我们在这里实现BeanFactory接口的方法，我们还在这个类中提供两个抽象方法，
一个是更加方便的创建Bean的方法createBean，还有一个是根据Bean的名字
去获取Bean的信息的方法。
```java
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {
    @Override
    public Object getBean(String name) throws BeansException {
        Object bean = getSingleton(name);
        if(bean != null) { // 如果容器中已经存在,直接返回bean
            return bean;
        }
        // 容器中不存在,创建Bean
        BeanDefinition beanDefinition = getBeanDefinition(name);
        return createBean(name,beanDefinition);
    }
    protected abstract Object createBean(String beanName,BeanDefinition beanDefinition) throws BeansException;
    protected abstract BeanDefinition getBeanDefinition(String beanName) throws BeansException;
}
```
好的，接下来我们继续往下扩展功能，我们在spring项目中，定义了Bean之后，
根据Bean定义的信息，我们是可以往这个Bean中自动注入属性的，因此我们创建一个抽象类AbstractAutowireCapableBeanFactory
，这个抽象类继承自我们刚刚创建的AbstractBeanFactory，并且实现了createBean方法，这个方法中我们调用doCreateBean方法。
之后为bean注入属性的过程，我们会写在doCreateBean中，目前只是先简单创建一下对象
```java
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName,beanDefinition);
    }
    protected Object doCreateBean(String beanName,BeanDefinition beanDefinition) {
        Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            bean = beanClass.newInstance();
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed",e);
        }
        addSingleton(beanName,bean); // 加入容器中
        return bean;
    }
}
```
好的，完成了以上工作之后，我们要来实现spring中十分重要的一个类：
DefaultListableBeanFactory，这个类继承自AbstractAutowireCapableBeanFactory
，并且它实现了BeanDefinitionRegistry，在这个类中，我们会创建一个Map，
这个Map存放每个Bean对应的Bean信息，也就是BeanDefinition。
然后实现了根据Bean名称获取Bean定义信息的方法，并且我们可以将Bean的信息，放入到Map容器中。
```java
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{
    private Map<String,BeanDefinition> beanDefinitionMap = new HashMap<>();
    @Override
    protected BeanDefinition getBeanDefinition(String beanName) throws BeansException {
        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if(beanDefinition == null) {
            throw new BeansException("No bean named '" + beanName + "' is defined");
        }
        return beanDefinition;
    }

    @Override
    public void registerBeanDefinitionRegistry(String name, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(name, beanDefinition);
    }
}
```

好的，接下来，我们来测试一下十分能够正常运行，我们可以编写以下测试代码
```java
 public class BeanFactoryTest {
    @Test
    public void testBeanFactory() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        BeanDefinition beanDefinition = new BeanDefinition(HelloService.class);
        beanFactory.registerBeanDefinitionRegistry("helloService",beanDefinition);
        HelloService helloService = (HelloService) beanFactory.getBean("helloService");
        helloService.sayHello();
    }
}
```
首先第一步，我们创建一个DefaultListableBeanFactory, 接着我们开始定义Bean信息，
我们创建一个BeanDefinition，传入一个HelloService的Class，
DefaultListableBeanFactory实现了BeanDefinitionRegistry接口，
因此我们可以在此处注册BeanDefinition，接着调用getBean方法，尝试获取Bean实例，
调用getBean之后，我们会走到AbstractBeanFactory类中的getBean方法，这个类继承自DefaultSingletonBeanRegistry，因此我们会调用DefaultSingletonBeanRegistry的getSingleton方法，可惜容器中此时还没有这个对象，
因此我们会创建这个对象，首先根据bean的名称获取BeanDefinition，
然后调用createBean方法，此时会调用了AbstractAutowireCapableBeanFactory中实现的doCreateBean方法，
在这个方法中获取Bean的Class，然后通过反射实例化Bean，接着调用 addSingleton，将它添加到容器中，
并且将实例化后的bean返回。最后一步步返回给了测试方法中的getBean的返回值。
然后我们就可以正常使用这个bean了。

### 定义Bean的实例化策略
目前我们实现了创建一个不含有属性的Bean，接着来定义一下Bean的实例化策略，
首先，依然是创建一个接口，在这个接口中我们定义一个实例化bean的方法，我们可以创建不同的实现类，
来定义不同的实例化策略。
```java
public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition) throws BeansException;
}
```
我们来简单的创建两个实现类吧，首先创建一个最简单的实例化策略，也就是根据类的类型，直接反射实例化
```java
public class SimpleInstantiationStrategy implements InstantiationStrategy{
    // 简单的bean实例化策略，根据bean的无参构造函数实例化对象
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        Class beanClass = beanDefinition.getBeanClass();
        try {
            Constructor constructor = beanClass.getDeclaredConstructor();
            return constructor.newInstance();

        } catch (Exception e) {
        }
        return null;
    }
}
```
我们也可以通过CGLIB动态创建子类
```java
public class CglibSubclassingInstantiationStrategy implements InstantiationStrategy{
    @Override
    public Object instantiate(BeanDefinition beanDefinition) throws BeansException {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(beanDefinition.getBeanClass());
        enhancer.setCallback((MethodInterceptor) (obj, method, argsTemp, proxy) -> proxy.invokeSuper(obj,argsTemp));
        return enhancer.create();
    }
}
```

我们来修改一下AbstractAutowireCapableBeanFactory类，我们添加一个InstantiationStrategy类型成员变量，并且将他默认实例化为SimpleInstantiationStrategy的对象。
然后在doCreateBean方法中不直接通过类名放射调用，而是直接调用实例化策略类的实例化方法。
具体代码如下：
```java
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();
    @Override
    protected Object createBean(String beanName, BeanDefinition beanDefinition) throws BeansException {
        return doCreateBean(beanName,beanDefinition);
    }
    protected Object doCreateBean(String beanName,BeanDefinition beanDefinition) {
        // Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
            bean = createBeanInstance(beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed",e);
        }
        addSingleton(beanName,bean); // 加入容器中
        return bean;
    }

    protected Object createBeanInstance(BeanDefinition beanDefinition) {
        return instantiationStrategy.instantiate(beanDefinition);
    }

    public void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }

    public InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
}
```

### 给Bean注入属性

我们在xml文件中写spring配置文件时候，定义了一个有属性的bean之后，我们还要在bean标签下定义property标签，里面描述了这个bean中各个属性的值，因此这些值我们也要记录下来，我们定义一个类表示单个property的信息，
其中包括属性名和属性的值，该类的全部信息如下：
```java
public class PropertyValue {
    private final String name;
    private final String value;
    public PropertyValue(String name, String value) {
        this.name = name;
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public String getValue() {
        return value;
    }
}
```
每个bean会有若干属性，因此我们还要定义一个类，用数组保存某个bean的全部属性：
```java
public class PropertyValues {
    private final List<PropertyValue> propertyValueList = new ArrayList<>();
    public void addPropertyValue(PropertyValue propertyValue) {
        propertyValueList.add(propertyValue);
    }
    public PropertyValue[] getPropertyValues() {
        return this.propertyValueList.toArray(new PropertyValue[0]);
    }
    public PropertyValue getPropertyValue(String propertyName) {
        for(var propertyValue : propertyValueList) {
            if(propertyValue.getName().equals(propertyName)) {
                return propertyValue;
            }
        }
        return null;
    }
}
```
接着我们去修改BeanDefinition，在这里面我们既要保存bean的class类型，还要保存这个bean的所有属性的信息，
因此我们要添加一个PropertyValues类型的成员变量，修改如下：
```java
public class BeanDefinition {
    private Class beanClass;
    private PropertyValues propertyValues;
    public BeanDefinition(Class beanClass) {
        this(beanClass,null);
    }
    public BeanDefinition(Class beanClass, PropertyValues propertyValues) {
        this.beanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }
    public Class getBeanClass() {
        return beanClass;
    }
    public void setBeanClass(Class beanClass) {
        this.beanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
```
添加该信息后，在AbstractAutowireCapableBeanFactory类中，我们要在创建bean的时候为其注入属性，首先选择给定的实例化策略实例化bean，
接着再添加一个方法为其注入属性，该方法接收三个参数：beanName、bean实例以及bean的相关信息。
在这个方法中，我们首先获取bean的class类型，以便后续通过反射调用其set方法，接着我们遍历bean信息中的属性信息
，获取属性名和值，通过反射获取属性名对应的属性类型，使用拼接字符串的方式获取set方法名，反射获取该set方法，
最后反射调用该方法，为bean实例注入属性。
```java
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    protected Object doCreateBean(String beanName,BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            // 实例化bean
            bean = createBeanInstance(beanDefinition);
            // 为bean注入属性
            applyPropertyValues(beanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("Instantiation of bean failed",e);
        }
        addSingleton(beanName,bean); // 加入容器中
        return bean;
    }
    // 为bean注入属性
    protected void applyPropertyValues(String beanName,Object bean,BeanDefinition beanDefinition) {
        try {
            Class beanClass = beanDefinition.getBeanClass();
            for(PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
                String name = propertyValue.getName();
                String value = propertyValue.getValue();
                Class<?> type = beanClass.getDeclaredField(name).getType();
                String methodName = "set" + name.substring(0,1).toUpperCase() + name.substring(1); // 获取set方法名
                Method method = beanClass.getDeclaredMethod(methodName, new Class[]{type});
                method.invoke(bean,new Object[]{value});
            }
        } catch (Exception e) {
            throw new BeansException("Error setting property values for bean: " + beanName, e);
        }
    }
}
```
### 为bean注入其他bean
上一步已经完成了为bean注入基本属性，但是如果一个bean的属性是其他bean呢？上面的方法就没法完成了，
因此我们再定义一个BeanReference类，这个类表示一种特殊的属性值，因此我们要修改之前创建的PropertyValue类，
将其成员变量value的类型改为Object，我们还可以利用hutool工具类更简单的完成为bean注入属性这件事情，
BeanReference类如下：
```java
public class BeanReference {
    private final String beanName;
    public BeanReference(String beanName) {
        this.beanName = beanName;
    }
    public String getBeanName() {
        return beanName;
    }
}
```
接着我们会在注入属性的方法中判断，当前遍历到的属性是不是BeanReference类型，如果是的话，
我们会根据BeanReference中获取beanName，然后获取bean，接着将value设置为bean，以完成属性的注入，
此时applyPropertyValues的for循环中的代码如下：
```java
for(PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()) {
        String name = propertyValue.getName();
        Object value = propertyValue.getValue();
        if(value instanceof BeanReference) {
            // 如果注入的属性是一个Bean，则获取这个bean
            BeanReference beanReference = (BeanReference) value;
            value = getBean(beanReference.getBeanName());
        }
        BeanUtil.setFieldValue(bean,name,value);
}
```

此时bean的属性注入已经完成，编写测试代码,通过测试。
```java
public class PopulateBeanWithPropertyValuesTest {
    @Test
    public void testPopulateBeanWithPropertyValues() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","ljx"));
        propertyValues.addPropertyValue(new PropertyValue("age",21));
        BeanDefinition beanDefinition = new BeanDefinition(Person.class,propertyValues);
        beanFactory.registerBeanDefinitionRegistry("person",beanDefinition);
        Person person = (Person) beanFactory.getBean("person");
        System.out.println(person);
    }
    @Test
    public void testPopulateBeanWithBean() throws Exception {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 注册Car示例
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("brand","byd"));
        BeanDefinition beanDefinition = new BeanDefinition(Car.class,propertyValues);
        beanFactory.registerBeanDefinitionRegistry("car",beanDefinition);
        // 注册Person对象，Person对象中组合了Car对象
        propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name","ljx"));
        propertyValues.addPropertyValue(new PropertyValue("age",21));
        propertyValues.addPropertyValue(new PropertyValue("car",new BeanReference("car")));
        BeanDefinition personBeanDefinition = new BeanDefinition(Person.class,propertyValues);
        beanFactory.registerBeanDefinitionRegistry("person",personBeanDefinition);
        System.out.println(beanFactory.getBean("car"));
        System.out.println(beanFactory.getBean("person"));

    }
}
```
### 实现资源类和资源加载器
创建接口Resource,这是资源的抽象和访问的接口，我们可以有不同的实现类。
```java
public interface Resource {
    InputStream getInputStream() throws IOException;
}
```
接着我们可以创建三个Resource接口的实现类，分别是对java.net.URL进行资源定位的实现类、
对文件系统资源定位的实现类以及对类路径下资源定位的实现类。
```java
public class UrlResource implements Resource{
    private final URL url;
    public UrlResource(URL url) {
        this.url = url;
    }
    @Override
    public InputStream getInputStream() throws IOException {
        URLConnection con = this.url.openConnection();
        try {
            return con.getInputStream();
        } catch (IOException e) {
            throw e;
        }
    }
}
```
```java
public class FileSystemResource implements Resource{
    private final String filePath;
    public FileSystemResource(String filePath) {
        this.filePath = filePath;
    }
    @Override
    public InputStream getInputStream() throws IOException {
        try {
            Path path = new File(this.filePath).toPath();
            return Files.newInputStream(path);
        } catch (NoSuchFileException e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }
}
```
```java
public class ClassPathResource implements Resource{
    private final String path;
    public ClassPathResource(String path) {
        this.path = path;
    }
    @Override
    public InputStream getInputStream() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(this.path);
        if(is == null) {
            throw new FileNotFoundException(this.path + " 不存在,打开失败");
        }
        return is;
    }
    public String getPath() {
        return path;
    }
}
```
为了加载这些资源，我们首先创建一个资源加载器接口，接口提供一个获取资源的方法，资源加载器可以有不同的实现类。
```java
public interface ResourceLoader {
    Resource getResource(String location);
}
```
创建一个默认的资源加载器的实现类，如果location以"classpath:"开头，那么加载classpath资源，否则当成url资源处理，如果处理发生异常，捕获该异常，当成系统文件资源处理即可。
```java
public class DefaultResourceLoader implements ResourceLoader{
    public static final String CLASSPATH_URL_PREFIX = "classpath:";
    @Override
    public Resource getResource(String location) {
        if(location.startsWith(CLASSPATH_URL_PREFIX)) {
            // classpath下的资源
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        } else {
            try {
                // 当成UrlResource处理
                URL url = new URL(location);
                return new UrlResource(url);
            } catch (MalformedURLException e) {
                // 当成文件系统下的资源来处理
                String path = location;
                if(location.startsWith("/")) {
                    path = location.substring(1);
                }
                return new FileSystemResource(path);
            }
        }
    }
}
```
编写测试类,测试资源加载是否成功:
```java
public class ResourceLoaderTest {
    @Test
    public void testResourceLoader() throws Exception {
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();

        // 测试类路径下的加载
        Resource resource = defaultResourceLoader.getResource("classpath:ljx.txt");
        InputStream inputStream = resource.getInputStream();
        String s = IoUtil.readUtf8(inputStream);
        System.out.println(s);

        // 加载文件系统中的资源
        resource = defaultResourceLoader.getResource("D:\\code\\java-workplace\\spring-mini\\src\\test\\resources\\xxx.txt");
        inputStream = resource.getInputStream();
        s = IoUtil.readUtf8(inputStream);
        System.out.println(s);

        // 加载Url资源
        resource = defaultResourceLoader.getResource("https://www.baidu.com");
        if(resource instanceof UrlResource) {
            System.out.println("是网络资源");
            inputStream = resource.getInputStream();
            s = IoUtil.readUtf8(inputStream);
            System.out.println(s);
        } else {
            System.out.println("加载Url资源有问题");
        }

    }
}
```

### 读取xml文件来配置bean
有了资源加载器，就可以在xml格式配置文件中声明式地定义bean的信息，
资源加载器读取xml文件，解析出bean的信息，自动往容器中注册BeanDefinition。

BeanDefinitionReader是读取bean定义信息的抽象接口，
XmlBeanDefinitionReader是从xml文件中读取的实现类。
BeanDefinitionReader需要有获取资源的能力，
且读取bean定义信息后需要往容器中注册BeanDefinition
，因此BeanDefinitionReader的抽象实现类AbstractBeanDefinitionReader拥有ResourceLoader和BeanDefinitionRegistry两个属性。

首先创建BeanDefinitionReader接口:
```java
public interface BeanDefinitionReader {
    BeanDefinitionRegistry getRegistry();

    ResourceLoader getResourceLoader();

    void loadBeanDefinitions(Resource resource) throws BeansException;

    void loadBeanDefinitions(String location) throws BeansException;

    void loadBeanDefinitions(String[] locations) throws  BeansException;
}
```
因为我们可能不只是需要从xml配置文件中读取bean信息，所以我们定义一个抽象类AbstractBeanDefinitionReader实现该接口，
该类中有资源加载器和Bean信息的注册器两个成员变量。
我们在这个抽象类中未实现这两个方法：因为这两个方法的实现和我们从哪里读取信息相关，我们不能放在抽象类中实现。
```
loadBeanDefinitions(Resource resource);
loadBeanDefinitions(String location);
```
AbstractBeanDefinitionReader内容如下：
```java
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private final BeanDefinitionRegistry registry;
    private ResourceLoader resourceLoader;
    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
        this.resourceLoader = new DefaultResourceLoader();
    }
    @Override
    public BeanDefinitionRegistry getRegistry() {
        return registry;
    }
    @Override
    public void loadBeanDefinitions(String[] locations) throws BeansException {
        for(var location : locations) {
            loadBeanDefinitions(location);
        }
    }
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
```
接着创建具体的实现类XlmFileBeanDefinitionReader,该类继承自上述抽象类。首先，我们要定义标签的常量，例如bean标签，和property标签。
还要定义属性的常量：比如id、name、class、value等等。接着从资源中获取InputStream，最后解析xml文档，将bean信息定义好后使用bean信息注册器注册bean信息。
该类的内容如下：
```java
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
```
