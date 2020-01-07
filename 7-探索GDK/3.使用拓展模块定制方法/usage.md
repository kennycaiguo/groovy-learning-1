
## 如何实现一个自己的拓展模块？

1. 创建拓展模块类，编辑拓展方法，拓展方法需要注意第一个参数是需要被拓展的类型；
2. 在根目录下创建文件：`/manifest/META-INF/services/org.codehaus.groovy.runtime.ExtensionModule`，声明拓展信息；
3. 编译拓展模块类，将创建的拓展模块类和配置文件打成一个 jar 包；

    ```sh
    # -d: 指定生成的类文件的位置（创建一个叫做 classes 的文件夹）
    groovyc -d classes 7-探索GDK/3.使用拓展模块定制方法/xyz/yuzh/extensions/*.groovy  
    # -cf：以指定文件名创建归档文件；-C 进入到 classes 目录，将其中的所有文件放入到归档文件，进入到 manifest 目录，将其中的所有文件放入到归档文件。
    jar -cf priceExtensions.jar -C classes . -C manifest . 
    # 可以使用以下命令查看 jar 包中的文件
    jar -tf priceExtensions.jar
    ```

4. 接下来就可以使用自己的拓展类了：
    
   - 4.1. 通过命令行运行：groovy -classpath priceExtensions.jar 7-探索GDK/3.使用拓展模块定制方法/UsingMyExtensions.groovy；
   - 4.2. 通过 IDEA 运行：在 Project Structure 中添加 priceExtensions.jar 包，删除掉源文件（因为会冲突），直接运行 UsingMyExtensions 即可。
