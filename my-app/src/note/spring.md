# BeanFactory和 ApplicationContext 的区别
BeanFacotry延迟加载，而ApplicationContext默认是非延迟加载,也可以指定延迟加载。
        ApplicationContext是BeanFactory的子接口。

　　BeanFactory：bean工厂接口；负责创建bean实例；容器里面保存的单例bean其实是一个map，它是Spring最底层的接口，只提供了简单的容器功能（只有实例化和拿对象（手动拿）的功能，如AOP功能、Web应用等都没有），BeanFactory在启动的时候不会实例化Bean，只有从容器中拿Bean的时候才会去实例化

　　ApplicationContext：是容器的接口；更多的负责容器接口的实现；（可以基于beanFactory创建好的对象之上完成更强大的容器）。

　　　　容器可以从map中获取到这个bean，并且aop、di。在ApplicationContext接口下的这些类中。

　　BeanFactory最底层的接口，ApplicationContext是留给程序员使用的ioc容器接口，ApplicationContext是BeanFactory的接口，提供了比BeanFactory更强大的功能，与BeanFactory最大的不同就是它在应用（服务）启动的时候就把Bean实例化完了（可以在Bean配置中使用lazy-init=true来让Bean延迟实例化），BeanFactory只有从容器中拿Bean的时候才会去实例化。

　　BeanFactory的优点（延迟实例化）：启动时资源占用较少。缺点：后期每次使用某个功能前都需要将与该功能有关的所有bean实例化，响应时间可能会比较长

　　ApplicationContext的缺点（不延迟实例化）：启动时资源占用多，所有的bean都被实例化，优点：后期使用这些bean的时候就不需要再次实例化，直接调用就可以。

　　使用场景：对于启动时需要资源较多的项目可以用BeanFactory，反之用ApplicationContext，不过用的最多的应该还是ApplicationContext，毕竟ApplicationContext可能只会导致启动的时候慢一些，而且启动时将这些Bean都实例化了，如果有错误至少可以及时解决，但是如果使用BeanFactory，那用户使用使用时，实例化某个Bean出错了，那岂不是凉凉，而且用户还要等你实例化完（个人理解）。

　　Spring中最大的模式是工厂模式