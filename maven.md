## 官网   https://maven.apache.org/

配置环境变量
export PATH=/opt/apache-maven-3.8.5/bin:$PATH

## 简单向导  https://maven.apache.org/guides/getting-started/index.html




mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false


###  java 9 or later 需要使用的maven-compiler-plugin需要最少为3.6.0，并且设置maven.compiler.release为对应的java版本,例如:
```
	<properties>
        <maven.compiler.release>11</maven.compiler.release>
    </properties>
 
    <build>
        <pluginManagement>
            <plugins>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <version>3.8.1</version>
                </plugin>
            </plugins>
        </pluginManagement>
    </build>
```

### 资源过滤
想要maven打包时候替换resource目录下面配置文件的变量${}，需要设置filtering为true,具体的该属性可以是您的 pom.xml 中定义的值之一、用户的 settings.xml 中定义的值、外部属性文件中定义的属性或系统属性。
```
<build>
    <resources>
      <resource>
        <directory>src/main/resources</directory>
        <filtering>true</filtering>
      </resource>
    </resources>
  </build>
```

`mvn process-resources` 可以进行资源过滤检验结果

eg:

```
# application.properties

# project default properties
application.name=${project.name}
application.version=${project.version}
application.build.finalName=${project.build.finalName}

# User's settings.xml

localRepo=${settings.localRepository}

java.version=${java.version}
user.home=${user.home}
command.line.prop=${command.line.prop}

```
`mvn process-resources "-Dcommand.line.prop=hello again"`


## 私服推送jar
### pom配置
```
<distributionManagement>
    <repository>
      <id>mycompany-repository</id>
      <name>MyCompany Repository</name>
      <url>scp://repository.mycompany.com/repository/maven2</url>
    </repository>
  </distributionManagement>
```

```
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
  ...
  <servers>
    <server>
      <id>mycompany-repository</id>
      <username>jvanzyl</username>
      <!-- Default value is ~/.ssh/id_dsa -->
      <privateKey>/path/to/identity</privateKey> (default is ~/.ssh/id_dsa)
      <passphrase>my_key_passphrase</passphrase>
    </server>
  </servers>
  ...
</settings>
```

## pom
### pom中父级pom的默认路径
<parent>标签中，如果父级pom.xml在当前项目的上一级目录下面，则不用写<relativePath>否则需要手动指定

## profile
在settings中指定激活的profile
```
<settings>
  ...
  <activeProfiles>
    <activeProfile>dev</activeProfile>
  </activeProfiles>
  ...
</settings>
```

## 默认激活的profile查看  参考 https://maven.apache.org/guides/introduction/introduction-to-profiles.html
### 用如下命令查看
```
mvn help:active-profiles
```
### 配置文件和命令行同时都有激活profile，查看时候用
```
mvn help:active-profiles -P appserverConfig-dev
```

这个时候可能列出来的事两个如下:
```
The following profiles are active:

 - appserverConfig-dev (source: pom)
 - appserverConfig (source: settings.xml)
```
即使列出来激活的是两个，想看哪个真正被应用，用如下命令
```
mvn help:effective-pom -P appserverConfig-dev
```
注意settings.xml中的优先级高于POM中的，所以被应用的是appserverConfig



## 可选依赖项
比如Mybatis可以操作数据库，Mysql,PostgreSQL，Oracle等等，但是程序只使用一种数据库，所以mybatis这个项目可以将各个数据库的驱动声明为可选依赖项。这样，ProjectA依赖Mybatis的时候，只需要把特定数据库的依赖项明确依赖就行，没必要依赖别的数据库驱动。

## 依赖排除 (排除项适用于声明他们的位置下方的整个依赖项链条)
A --> B --> D --> E
现在A项目要用到B也要用到D，但是不需要用E，则可以引入B的时候，声明排除E（中间跳过了D）
```
<project>
  <modelVersion>4.0.0</modelVersion>
  <groupId>sample.ProjectA</groupId>
  <artifactId>Project-A</artifactId>
  <version>1.0-SNAPSHOT</version>
  <packaging>jar</packaging>
  ...
  <dependencies>
    <dependency>
      <groupId>sample.ProjectB</groupId>
      <artifactId>Project-B</artifactId>
      <version>1.0-SNAPSHOT</version>
      <exclusions>
        <exclusion>
          <groupId>sample.ProjectE</groupId> <!-- Exclude Project-E from Project-B -->
          <artifactId>Project-E</artifactId>
        </exclusion>
      </exclusions>
    </dependency>
  </dependencies>
</project>
```
