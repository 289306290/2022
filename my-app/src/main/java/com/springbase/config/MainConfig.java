package com.springbase.config;

import com.springbase.config.condition.MyCondition;
import com.springbase.vo.Animal;
import com.springbase.vo.PersonVo;
import com.springbase.vo.ProfileBean;
import com.springbase.vo.Student;
import com.springbase.vo.importbean.ImportBean;
import com.springbase.vo.importbean.TestBeanDefinitionRegistrar;
import com.springbase.vo.importbean.TestImportSelector;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Controller;

@Configuration
@ComponentScan(basePackages = {"com.springbase", "com.xxx这里可以指定多个包"},
        excludeFilters = {
//        @ComponentScan.Filter(type = FilterType.ANNOTATION,value = {Service.class}),
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class}),
                @ComponentScan.Filter(type = FilterType.CUSTOM, value = {ExcludePackageScan.class})
        })
@Import(value = {ImportBean.class, TestImportSelector.class, TestBeanDefinitionRegistrar.class})
@PropertySource(value = {"classpath:a.properties"})
public class MainConfig {

    @Bean
    public PersonVo personVo() {
        return new PersonVo();
    }

    @Bean
    @DependsOn({"student"})
    /**
     * 如果没有DependsOn,则会按照bean定义顺序,从上往下加载，先定义Animal,后Student
     * 如果加入DependsOn("student"),则会先加载Student,后加载Animal,从构造方法可以看出来。
     */
    public Animal animal() {
        return new Animal();
    }

    @Bean("student")
    //MyCondition中描述了，有了animal,才会加载student,但是也可能只有animal的bean定义,还没有实例化animal,
    @Conditional(MyCondition.class)
    public Student student() {
        return new Student(19);
    }

    @Bean
    /**
     * 当 -DSpring.profiles.active=dev时候生效
     * -DSpring.profiles.active=dev,beta 时候 dev 和 beta都生效
     */
    @Profile("dev")
    public ProfileBean devBean(){
        System.out.println("这里是spring.profiles.active=dev");
        return new ProfileBean();
    }

    @Bean
    /**
     * 当 -DSpring.profiles.active=dev时候生效
     */
    @Profile("beta")
    public ProfileBean betaBean(){
        System.out.println("这里是spring.profiles.active=beta");
        return new ProfileBean();
    }
}
