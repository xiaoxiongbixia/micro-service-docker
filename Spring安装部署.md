# Spring Cloud + Kubernetes + Docker #

我们一起来学习

## 1 Spring Cloud 篇 ##

### 1.1 Windows下开发环境搭建 ###

**1.1.1 JDK 安装**
<hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />

> **描述**：根据Spring Cloud项目要求，JAVA版本最好在1.8以上
>
> **下载地址**：[http://www.oracle.com/technetwork/java/javase/downloads/index.html](http://www.oracle.com/technetwork/java/javase/downloads/index.html) 
> 
> **当前版本**：Java Platform (JDK) 8u131
> 
> **环境变量配置**：计算机>右键：属性>高级系统设置>高级>环境变量>用户变量
> 
> - `JAVA_HOME=D:\tools\java` *(D:\tools\java为我本地安装目录)*
> 
> - `Path=%JAVA_HOME%\bin;`
> 
> - 如果没有环境变量的Key就自己新建1个
> 
> **验证**：通过Windows+R键, 运行`cmd`命令, 并在命令行中输入`java -version`显示如下提示表示安装成功
>
>     $ java -version
>     java version "1.8.0_131"
>     Java(TM) SE Runtime Environment (build 1.8.0_131-b13)
>     Java HotSpot(TM) 64-Bit Server VM (build 25.121-b13, mixed mode)

**1.1.2 项目管理工具之Maven安装**

> **描述**：Ant、Maven、Gradle是JAVA世界的三大构建工具，目前Ant已经销声匿迹，Maven已经日渐黄昏，Gradle正如日中天，所以有机会建议大家学习Gradle。
> 
> **下载地址**：[http://maven.apache.org/download.cgi](http://maven.apache.org/download.cgi)

> **当前版本**：Apache Maven 3.5.0
>
> **安装**：解压到指定文件夹，例如D:\tools\apache-maven-3.5.0
>
> **环境变量配置**：计算机>右键：属性>高级系统设置>高级>环境变量>用户变量
> 
> - `M2_HOME=D:\tools\apache-maven-3.5.0` *(D:\tools\apache-maven-3.5.0为我本地安装目录)*
> 
> - `Path=%JAVA_HOME%\bin;%M2_HOME%\bin;`
> 
> - 如果没有环境变量的Key就自己新建1个
> 
> **验证**：通过Windows+R键, 运行`cmd`命令, 并在命令行中输入`mvn -v`显示如下提示表示安装成功
>
>     $ mvn -v
>     Apache Maven 3.5.0 (ff8f5e7444045639af65f6095c62210b5713f426; 2017-04-04...)
>     Maven home: D:\tools\apache-maven-3.5.0\bin\..
>     Java version: 1.8.0_131, vendor: Oracle Corporation
>     Java home: D:\tools\Java\jre
>     Default locale: zh_CN, platform encoding: GBK
>     OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
>
> **相关配置**：编辑*D:\tools\apache-maven-3.5.0*\conf\settings.xml
>
> - 本地仓库路径配置
>     <pre><code>在&lt;settings&gt;节点下增加
>     &lt;localRepository&gt;D:\tools\maven-repository(该路径为自定义路径)&lt;/localRepository&gt;</code></pre>
> - 国内镜像仓库地址配置
>     <pre><code>在&lt;settings&gt;的&lt;mirrors&gt;节点下增加
&lt;mirror&gt;
>        &lt;id&gt;CN&lt;/id&gt;
>        &lt;mirrorOf&gt;central&lt;/mirrorOf&gt;
>        &lt;name&gt;OSChina Central&lt;/name&gt;
>        &lt;url&gt;http://maven.aliyun.com/nexus/content/groups/public/&lt;/url&gt;
>     &lt;/mirror&gt;
</code></pre>
>
> - 网络代理配置(如果公司需要代理上网，其中用户名、密码如果不需要可以置空)
>     <pre><code>在&lt;settings&gt;的&lt;proxies&gt;节点中增加
&lt;proxy&gt;
>       &lt;id&gt;取名唯一&lt;/id&gt;
>       &lt;active&gt;true&lt;/active&gt;
>       &lt;protocol&gt;http&lt;/protocol&gt;
>       &lt;username&gt;用户名&lt;/username&gt;
>       &lt;password&gt;密码&lt;/password&gt;
>       &lt;host&gt;代理域名或者IP&lt;/host&gt;
>       &lt;port&gt;代理端口&lt;/port&gt;
>       &lt;nonProxyHosts&gt;local.net|some.host.com&lt;/nonProxyHosts&gt;
>     &lt;/proxy&gt;
</code></pre>

**1.1.3 IDE之Spring Tool Suite安装**

> **描述**：STS和Eclipse非常相似，只不过是对Spring的集成做了深度优化，比较好用
> 
> **下载地址**：[https://spring.io/tools/sts/](https://spring.io/tools/sts/)
>
> **当前版本**：3.8.4 RELEASE For Windows
>
> **安装**：直接解压就可以使用，因为内部文件层次较深，容易超出Windows文件名长度上限，建议选择磁盘根目录解压
>
> **配置**：比如JDK、Maven、字体、字符集等配置
>
> - 代理配置(如果公司需要代理上网)：顶部导航>Window>Preferences>General>Network Connections
    <pre>
    1) Active Provider：选择 Manual
    2) Proxy entries：在HTTP和HTTPS中输入域名、端口、用户、密码，并勾选前边的复选框
    3) 点击Apply应用
    </pre>
> - JDK配置：顶部导航>Window>Preferences>java>Installed JREs，将JDK路径指定到JAVA安装路径*(D:\tools\java)*
>
> - Maven配置：顶部导航>Window>Preferences>Maven>User Settings
    <pre>
	1) Global Settings: 选择Maven安装目录下的conf\settings.xml (如D:\tools\apache-maven-3.5.0\conf\settings.xml)
	2) User Settings: 选择Maven安装目录下的conf\settings.xml (如D:\tools\apache-maven-3.5.0\conf\settings.xml)
	3) 点击Apply应用
	</pre>
>
> - 字符集配置：顶部导航>Window>Preferences>General>Workspace 在左下角Text file encoding勾选Other并选择UTF-8，点击Apply应用
> 
> 关于IDE进行Maven项目的导入导出，这里不做赘述，请自行度娘之。

**1.1.4 版本控制之GIT安装**

> **描述**：SVN、GIT都是比较好用的版本控制软件、不过目前GIT正在逐步取代SVN，所以建议大家有时间学习GIT
> 
> **下载地址**：[https://git-scm.com/downloads](https://git-scm.com/downloads "https://git-scm.com/downloads")
>
> **当前版本**：2.13.1
>
> **图形界面及汉化包地址**：[https://tortoisegit.org/download/](https://tortoisegit.org/download/)
>
> **当前版本**：2.4.0 *(如果不需要图形界面可以不安装)*
>
> **相关配置**：凡是GIT的配置都可以通过Bash界面(右键->Git Bash Here)操作，以下所有代码块中以//开头的都是注释说明
>
> <pre><code>//配置全局用户名，随便取名，在提交代码的时候回显示到记录中
> $ git config --global user.name 'Joshua'
> //配置全局邮箱，自己的邮箱就好，在提交代码的时候回显示到记录中
> $ git config --global user.email 'email@qq.com'
> //如果公司需要代理上网，则配置代理，格式为，协议名://用户名:密码@域名或IP:端口
> $ git config --global http.proxy 'http://user:pwd@proxy.neusoft.com:8080'
> //配置Git提交时过滤规则，即哪些文件可以提交，哪些文件自动忽略
> //编辑~/.gitignore_global，内容如下
> $ cat ~/.gitignore_global
> #所有的编译文件
> *.class
> #目标目录
> target
> #设置文件
> .settings
> #以.开头的文件
> .*
> //配置全局过滤规则
> $ git config --global core.excludesfile ~/.gitignore_global
> //用以显示全部配置信息，或者可以再指定--global参数查看global的配置信息
> $ git config -l 
> core.symlinks=false
> core.autocrlf=true
> core.fscache=true
> color.diff=auto
> color.status=auto
> color.branch=auto
> color.interactive=true
> help.format=html
> http.sslcainfo=C:/Program Files/Git/mingw64/ssl/certs/ca-bundle.crt
> diff.astextplain.textconv=astextplain
> rebase.autosquash=true
> credential.helper=manager
> user.name=Joshua
> user.email=email@qq.com
> credential.helper=manager
> core.quotepath=false
> core.excludesfile=C:/Users/Administrator/.gitignore_global
> http.proxy=http://user:pwd@proxy.neusoft.com:8080 
> </code></pre>

> **SSH配置**：该功能用于免账户密码，通过SSH的模式与git仓库交互
> 
> <pre><code>//生成证书，执行过程中密码可以置空
> $ ssh-keygen -t rsa -b 4096 -C "你的邮箱或者其他字符串啥的"
> //证书的路径默认在~/.ssh，拷贝公钥
> $ clip < ~/.ssh/id_rsa.pub
> //接下来将拷贝的结果黏贴到GIT仓库的SSH公钥列表中
> //并通过SSH协议与GIT仓库通信，并且提交的时候不再需要输入GIT仓库的用户密码
> $ git clone git@git.oschina.net:xiaoxiongbixia/config-center.git
> //如果使用Https协议通信话，需要输入用户密码，这也是常规方式，容易掌握
> $ git clone https://git.oschina.net/xiaoxiongbixia/config-center.git
> </code></pre>
>
> SSH参考文档：[https://help.github.com/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/](https://help.github.com/articles/generating-a-new-ssh-key-and-adding-it-to-the-ssh-agent/)
> 

### 1.2 Linux下部署环境搭建 ###
<hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />
我这里的系统用的是CentOs7虚拟机系统，自己的电脑安装虚拟机可以使用VMware Workstation Pro软件

**1.2.1 系统配置**

> 因为我的电脑需要代理上网，所以我在这里讲下代理的相关配置。

> **全局代理配置**：如果有需要的情况下，可以配置全局代理，但是一旦配置了全局代理，后续的kubernetes等集群就无法正常调用了
> 
> <pre><code>//编辑全局配置文件，增加代理描述
> # cat /etc/profile
> ...
> http_proxy="http://账户:密码@proxy.neusoft.com:8080"
> https_proxy="http://账户:密码@proxy.neusoft.com:8080"
> export http_proxy
> export https_proxy
> //导入配置信息，重启后依然生效
> # source /etc/profile
> </code></pre>
>
> **临时代理配置**：这种方式比较灵活，我个人比较喜欢这种
> 
> <pre><code>//命令行 启用代理
> # export http_proxy="http://账户:密码@proxy.neusoft.com:8080" https_proxy="http://账户:密码@proxy.neusoft.com:8080"
> //命令行 取消代理
> # export http_proxy="" https_proxy=""
> //通过自定义快捷命令实现，编辑~/.bashrc文件，增加内容如下
> # cat ~/.bashrc
> ...
> alias onproxy='export http_proxy="http://账户:密码@proxy.neusoft.com:8080" https_proxy="http://账户:密码@proxy.neusoft.com:8080"'
alias offproxy='export http_proxy="" https_proxy=""'
> //导入配置
> # source ~/.bashrc
> //启用代理
> # onproxy
> //测试启用代理是否成功，执行如下命令会有数据返回
> # curl -X GET www.baidu.com
> //取消代理
> # offproxy
> //测试取消代理是否成功，执行如下命令不会有数据返回
> # curl -X GET www.baidu.com
> </code></pre>
> 
> **wget工具代理配置**：配置到这里，表明使用wget的时候回通过代理
> 
> <pre><code>//编辑wgetrc文件，增加内容如下，保存即可
> # cat /etc/wgetrc
> ...
> http_proxy = http://账户:密码@proxy.neusoft.com:8080
https_proxy = http://账户:密码@proxy.neusoft.com:8080
> </code></pre>
>
> **yum工具代理配置**：配置到这里，表明使用yum的时候回通过代理
> 
> <pre><code>//编辑yum.conf文件，增加内容如下，保存即可
> # cat /etc/yum.conf
> ...
> proxy=http://账户:密码@proxy.neusoft.com:8080
> </code></pre>
>
> 以上是代理的几种用法，可供参考

**1.2.2 JAVA安装**

> 因为后续Spring Cloud的功能都依赖Java1.8以上版本，所以我们需要安装新版本JAVA
>
> **查看旧版本以及删除旧版本**
> 
> <pre><code>//查看旧版本的方法，如果满足条件则不需要再次安装
> //显示java版本，一般新安装的CentOS7系统，java都不满足条件
> # java -version
> //查看javac版本，新安装的CentOS7系统，没有javac命令
> # javac -version
> 
> # 如果前者两个命令发现JAVA版本不满足条件，则通过rpm -qa|grep java获取当前包信息并通过rpm -e --nodeps删除包
> # rpm -qa|grep java
> //以下是默认安装的相关软件包可以删除的部分
> java-1.7.0-openjdk-1.7.0.111-2.6.7.8.el7.x86_64
> tzdata-java-2016g-2.el7.noarch
> java-1.8.0-openjdk-1.8.0.102-4.b14.el7.x86_64
> java-1.8.0-openjdk-headless-1.8.0.102-4.b14.el7.x86_64
> java-1.7.0-openjdk-headless-1.7.0.111-2.6.7.8.el7.x86_64
> //删除软件包
> # rpm -e --nodeps java-1.7.0-openjdk-1.7.0.111-2.6.7.8.el7.x86_64
> # rpm -e --nodeps tzdata-java-2016g-2.el7.noarch
> # rpm -e --nodeps java-1.8.0-openjdk-1.8.0.102-4.b14.el7.x86_64
> # rpm -e --nodeps java-1.8.0-openjdk-headless-1.8.0.102-4.b14.el7.x86_64
> # rpm -e --nodeps java-1.7.0-openjdk-headless-1.7.0.111-2.6.7.8.el7.x86_64
> </code></pre>
> 
> **安装新版本**
> 
> <pre><code># mkdir /usr/java
> # cd /usr/java
> //自己下载JAVA的LINUX安装包jdk-8u131-linux-x64.rpm并上传到此目录
> # ls
> jdk-8u131-linux-x64.rpm
> //安装
> # rpm -ivh jdk-8u131-linux-x64.rpm
> //配置全局JAVA的环境变量，编辑/etc/profile，增加内容如下
> # cat /etc/profile
> ...
> export JAVA_HOME=/usr/java/jdk1.8.0_131 # 根据情况修改
> export PATH=$JAVA_HOME/bin:$PATH
> export CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar
> //导入变量信息
> # source /etc/profile
> //通过 javac -version和 java -version命令验证安装是否成功
> </code></pre>

**1.2.3 Maven安装**

> Linux Maven的安装很简单
> 
> <pre><code># mkdir /usr/maven
> # cd /usr/maven
> //自己下载apache-maven-3.5.0-bin.tar.gz并上传到此目录
> # ls
> apache-maven-3.5.0-bin.tar.gz
> //解压
> # tar zxvf apache-maven-3.5.0-bin.tar.gz
> //配置全局Maven的环境变量，编辑/etc/profile，增加内容如下
> # cat /etc/profile
> ...
> export MAVEN_HOME=/user/maven/apache-maven-3.5.0
> export PATH=$MAVEN_HOME/bin:$PATH
> //导入变量信息
> # source /etc/profile
> //通过 mvn -v命令验证安装是否成功
> //Linux Maven的配置和Windows下基本一样，不做赘述，请自行编辑/user/maven/apache-maven-3.5.0/conf/settings.xml
> </code></pre>

**1.2.4 GIT安装**

> 非常之简单，通过`yum install git`即可，并按照Windows下命令行的方法进行适当配置

### 1.3 Spring Cloud 组件学习 ###
<hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />

**1.3.1 服务发现Eureka**

- 单机模式

- 集群模式

**1.3.2 客户端负载均衡Ribbon**

- 简单用法

- 代买级别配置

- Yaml文件配置

**1.3.3 客户端负载均衡Fegin**

- 基于Spring的Feign Client

- 基于Feign原生的Feign Client

**1.3.4 断路器Hystrix**

- Ribbon断路器配置

- Feign断路器配置

- 断路器看板Dashboard

- 断路器看板之Turbine

**1.3.5 API Gateway Zull**

- zuul功能

- sidecar

**1.3.6 配置中心Config Center**

- 加密

- BUS

### 1.4 与同类框架比较 ###

## 2 Docker篇 ##

## 3 Kubernetes 篇 ##

