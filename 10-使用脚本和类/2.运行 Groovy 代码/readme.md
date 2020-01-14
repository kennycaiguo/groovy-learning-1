# Java 和 Groovy 的混合 

要在 Java 中使用 Groovy，需要使用 JSR223 提供的 ScriptEngine API。
如果想在 Java 中使用 Groovy 类，或者想在 Groovy 中使用 Java 类，可以使用 Groovy 的联合编译工具。

# 运行 Groovy 代码

方式一：对源代码执行 groovy 命令，groovy 会自动在内存中编译代码并执行。

```shell script
groovy Greet.groovy
```

方式二：使用 `groovyc` 显式的编译成 class 文件，然后使用 `java` 命令运行这个字节码文件：

```shell script
cd /Users/Harry/Workspace/groovy-learning/10-使用脚本和类/2.运行 Groovy 代码
# -d 参数可以指定 class 存放的目录
groovyc -d output Greet.groovy
# 在 classpath 后面加 . 号，会在当前目录的指定目录下找类。
java -classpath /Users/Harry/Workspace/groovy-learning/groovy-2.5.8.jar:./output Greet
```
