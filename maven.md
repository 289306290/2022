## 官网   https://maven.apache.org/

配置环境变量
`export PATH=/opt/apache-maven-3.8.5/bin:$PATH`

## 简单向导  https://maven.apache.org/guides/getting-started/index.html




```
mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
```


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

## 远程仓库，一般替换为国内的镜像，设置在settings.xml中

```
<settings>
  ...
  <mirrors>
    <mirror>
      <id>other-mirror</id>
      <name>Other Mirror Repository</name>
      <url>https://other-mirror.repo.other-company.com/maven2</url>
      <mirrorOf>central</mirrorOf>
    </mirror>
  </mirrors>
  ...
</settings>
```
例如:用阿里云替换central

```
<mirror>
       <id>nexus-aliyun</id>
       <mirrorOf>central</mirrorOf>
       <name>Nexus aliyun</name>
       <url>http://maven.aliyun.com/nexus/content/groups/public</url>
</mirror>
```
### 也可以在项目pom中配置，并且项目优先级较高(先用项目pom中配置的仓库去找，找不到才用settings.xml中配置的仓库）
```
<project>
...
  <repositories>
    <repository>
      <id>my-repo1</id>
      <name>your custom repo</name>
      <url>http://jarsm2.dyndns.dk</url>
    </repository>
    <repository>
      <id>my-repo2</id>
      <name>your custom repo</name>
      <url>http://jarsm2.dyndns.dk</url>
    </repository>
  </repositories>
...
</project>
```
### 查看起作用的maven setting 配置可以用如下命令 (mvn主目录conf下面的配置以及~/.m2/settings.xml中合并的配置)
 `mvn help:effective-settings` 
 
### 查看生效的pom （这里好像不包含镜像仓库）
`mvn help:effective-pom -Dverbose`

### 一般配置
## 在用户settings.xml中配置阿里镜像仓库，另外配置一个profile,让其默认为激活状态(这里配置公司的maven私服地址）
```
</profiles>
	<profile>
	 	<id>thirdparty</id>
	  <repositories>
        <repository>
            <id>thirdparty</id>
            <url>http://xxx.bbb.com/nexus/content/repositories/thirdparty</url>
        </repository>
		<repository>
			<id>puhuifinance</id>
			<name>Maven of Puhuifinance.com</name>
			<url>http://xxx.bbb.com/nexus/content/groups/public/</url>
			<releases>
				<enabled>true</enabled>
				<updatePolicy>always</updatePolicy>
			</releases>
			<snapshots>
				<enabled>true</enabled>
				<updatePolicy>always</updatePolicy>
			</snapshots>
			<layout>default</layout>
		</repository>
		<repository>
			<id>oschina</id>
			<name>Maven of oschina.net</name>
			<url>http://maven.oschina.net/content/groups/public/</url>
			<releases>
				<enabled>true</enabled>
				<updatePolicy>always</updatePolicy>
			</releases>
			<snapshots>
				<enabled>true</enabled>
				<updatePolicy>always</updatePolicy>
			</snapshots>
			<layout>default</layout>
		</repository>
	</repositories>
 </profile>
 
</profiles>

<activeProfiles>
  <activeProfile>thirdparty</activeProfile>
</activeProfiles>
```

## 如果要临时离线构建，可以使用 -o
` mvn -o package`

## 完整的pom.xml元素以及解释如下: 
https://maven.apache.org/ref/3.8.5/maven-model/maven.html#class_extension

## 依赖机制参考
https://maven.apache.org/guides/introduction/introduction-to-dependency-mechanism.html#introduction-to-the-dependency-mechanism

## 上传jar包到私服相关   https://maven.apache.org/guides/mini/guide-large-scale-centralized-deployments.html
注意maven deploy plugin版本
```
<settings>
  ...
  <profiles>
    <profile>
 
      <id>corp-repository-manager</id>
 
      <properties>
        <!--
          For Maven Deploy Plugin >= 2.8, deploy snapshots to this repository instead of the
          distributionManagement snapshotRepository from project pom.xml files.
        -->
        <altSnapshotDeploymentRepository>corp::default::https://corp-repository-manager-host/maven-snapshots</altSnapshotDeploymentRepository>
 
        <!--
          For Maven Deploy Plugin >= 2.8, deploy releases to this repository instead of the
          distributionManagement repository from project pom.xml files.
        -->
        <altReleaseDeploymentRepository>corp::default::https://corp-repository-manager-host/maven-releases</altReleaseDeploymentRepository>
 
        <!--
          Only needed if some projects are still using Maven Deploy Plugin >=2.3 and < 2.8,
          which is the case if projects are using the default version of Maven Deploy Plugin in maven 3.x.
          For Maven Deploy Plugin >=2.3 and < 2.8, deploy both releases and snapshots to this repository
          instead of the repositories mentioned in distributionManagement from project pom.xml files.
        -->
        <altDeploymentRepository>corp::default::https://corp-repository-manager-host/maven-combined</altDeploymentRepository>
      </properties>
 
      <repositories>
        <repository>
          <id>corp</id>
          <!--
            This URL is overridden by the corp-repository-manager mirror above.
            Some misbehaving tools might complain if they can't resolve the host specified here.
            If you encounter this problem, use the same URL as the corp-repository-manager mirror.
          -->
          <url>https://ignored</url>
          <releases>
            <enabled>true</enabled>
            <updatePolicy>never</updatePolicy>
          </releases>
          <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
          </snapshots>
        </repository>
      </repositories>
 
      <pluginRepositories>
        <pluginRepository>
          <id>corp</id>
          <!--
            This URL is overridden by the corp-repository-manager mirror above.
            Some misbehaving tools might complain if they can't resolve the host specified here.
            If you encounter this problem, use the same URL as the corp-repository-manager mirror.
          -->
          <url>https://ignored</url>
          <releases>
            <enabled>true</enabled>
            <updatePolicy>never</updatePolicy>
          </releases>
          <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
          </snapshots>
        </pluginRepository>
      </pluginRepositories>
 
    </profile>
  </profiles>
  <activeProfiles>
    <activeProfile>corp-repository-manager</activeProfile>
  </activeProfiles>
  ...
</settings>
```

## maven-assembly-plugin 自定义打包
配置文档 https://maven.apache.org/plugins/maven-assembly-plugin/assembly.html
预定义的示例  https://maven.apache.org/plugins/maven-assembly-plugin/descriptor-refs.html#bin
1. 首先pom中配置此插件
```
<build>
    <plugins>
      <plugin>
        <artifactId>maven-assembly-plugin</artifactId>
        <version>3.3.0</version>
        <configuration>
          <descriptors>
            <descriptor>src/assembly/dep.xml</descriptor>
          </descriptors>
        </configuration>
        <executions>
          <execution>
            <id>create-archive</id>
            <phase>package</phase>
            <goals>
              <goal>single</goal>
            </goals>
          </execution>
        </executions>
      </plugin>
    </plugins>
  </build>
```
2. 示例dep.xml
```
<assembly>
    <id>assembly</id>
    <formats>
        <format>zip</format>
    </formats>
    <dependencySets>
        <dependencySet>
            <outputDirectory>lib</outputDirectory>
            <unpack>false</unpack>
        </dependencySet>
    </dependencySets>
    <fileSets>
        <fileSet>
            <directory>${project.basedir}/src/main/resources</directory>
            <outputDirectory>/conf</outputDirectory>

            <includes>
                <include>**/*</include>
            </includes>
            <fileMode>0644</fileMode>
            <filtered>true</filtered>
        </fileSet>
        <fileSet>
            <directory>${project.basedir}/src/main/assembly</directory>
            <outputDirectory>/bin</outputDirectory>
            <filtered>true</filtered>
            <fileMode>0755</fileMode>
            <includes>
                <include>*.sh</include>
            </includes>
            <lineEnding>unix</lineEnding>
        </fileSet>
    </fileSets>
</assembly>
```
## settings.xml配置文档 
https://maven.apache.org/ref/3.8.5/maven-settings/settings.html

## maven环境变量
### 下载构建的时候,maven默认允许同时下载5个插件，可以命令行调整 `-Dmaven.artifact.threads`
例如：
`mvn -Dmaven.artifact.threads=1 verify`

如果想永久设置需要配置MAVEN_OPTS  
`export MAVEN_OPTS=-Dmaven.artifact.threads=3`


### mvn 命令参数，主要多模块项目时候 参考https://maven.apache.org/guides/mini/guide-multiple-modules-4.html
```
1. -pl 相当于 --projects 指定打包某个项目
mvn clean package -pl puhui-cc-cloud-server -Dmaven.test.skip=true
2. -am 相当于 --also-make  表示同时处理选定模块所依赖的模块
mvn clean package -am puhui-cc-cloud-server -Dmaven.test.skip=true  打包puhui-cc-cloud-server同时打包依赖的puhui-cc-common
3. --amd  相当于 --also-make-dependents  表示同时处理依赖选定模块的模块
mvn clean package -am puhui-cc-common -Dmaven.test.skip=true  打包puhui-cc-common同时打包依赖于这个的puhui-cc-cloud-server
```