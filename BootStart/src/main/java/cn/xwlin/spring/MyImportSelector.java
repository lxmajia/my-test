package cn.xwlin.spring;

import cn.xwlin.server.MyServerTest;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;

/**
 * 定义注册Bean的方法，通过Register
 *
 * @author MaJiaXueYuan.lx
 * @ClassName MyImportSelector.java
 * @createTime 2023-2-7-0007
 */
public class MyImportSelector implements ImportBeanDefinitionRegistrar, ResourceLoaderAware {
    private ResourceLoader resourceLoader;

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        BeanDefinition myServerTestBeanDef = new RootBeanDefinition(MyServerTest.class);
        registry.registerBeanDefinition("myServerTest", myServerTestBeanDef);
    }

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }
}
