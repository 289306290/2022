Mac下面jdk安装

1、MAC默认安装目录为/Library/Java/JavaVirtualMachines/

2、终端打开环境配置文件：open ~/.bash_profile


3、添加内容如下：

　　　　JAVA_HOME=/Library/Java/JavaVirtualMachines/jdk1.8.0_101.jdk/Contents/Home/

　　　　CLASSPAHT=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar

　　　　PATH=$JAVA_HOME/bin:$PATH:

　　　　export JAVA_HOME

　　　　export CLASSPATH

　　　　export PATH

4、保存文本后关闭

5、终端输入内容使配置生效：source ~/.bash_profile

6、如果有多个jdk版本，都会在默认目录/Library/Java/JavaVirtualMachines/下面，修改JAVA_HOME就行。