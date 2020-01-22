# MOP 技术回顾
## 方法拦截
12 章节探讨了方法拦截，可以使用 GroovyInterceptable、ExpandoMetaClass 或 Category。

- 如果有权修改源代码，可以在想要拦截的方法调用的类上实现 GroovyInterceptable 接口，实现 invokeMethod() 即可。
- 如果无法修改类，或者那是一个 Java 类，那就使用 ExpandoMetaClass 或 Category。ExpandoMetaClass 的一个 invokeMethod() 就能拦截所有
方法。Category 则需要为每一个需要拦截方法提供一个单独的方法，还需要使用 use 代码块。

## 方法注入
13 章节探讨了方法注入。方法注入方面，分类完全可以与 ExpandoMetaClass 相媲美。

如果使用分类，可以控制注入方法的位置。通过使用不同的分类，可以轻松实现不同版本的方法注入。
还可以轻松嵌入和混用多个分类。分类所提供的控制 —— 即方法注入仅在 use 块内起作用，而且被限制在执行线程上，可以认为是一个限制。

如果想在任意位置上使用注入的方法，或者想注入静态方法或构造器，ExpandoMetaClass 是更好的选择。注意：ExpandoMetaClass 不是 Groovy 中的默认 MetaClass。
借助 ExpandoMetaClass，可以将某个具体的实例注入方法，而不影响整个类。

## 方法合成
14 章节探讨了方法合成，我们可以在 Groovy 对象或 ExpandoMetaClass 上使用 methodMissing()。

如果有权修改源代码，能够在类上为想要合成的方法实现 methodMissing()，可以通过在第一次调用时注入方法来改进性能。如果同时需要拦截方法，使用 GroovyInterceptable 接口即可。

如果无法修改类，可以将 methodMissing() 添加到类的 ExpandoMetaClass 中。如果同时想要拦截方法，可以在 ExpandoMetaClass 上实现 invokeMethod()。

借助 ExpandoMetaClass，可以将方法合成到某个具体的实例，而不影响整个类。
