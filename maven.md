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