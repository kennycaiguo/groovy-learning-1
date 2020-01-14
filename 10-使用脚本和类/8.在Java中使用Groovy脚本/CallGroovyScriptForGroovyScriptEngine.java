import groovy.lang.Binding;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.IOException;

/**
 * 使用 GroovyScriptEngine 在 Java 中调用 Groovy 脚本，与 ScriptEngineManager 不同的是：
 * GroovyScriptEngine 的 run() 可传递脚本名和一个 Bindings 对象，当脚本文件改变时会重新加载并运行脚本，这使得其非常合适用于 Java 服务器应用中。
 *
 * @author Harry Zhang
 * @since 2020/1/14 2:05 PM
 */
public class CallGroovyScriptForGroovyScriptEngine {
    public static void main(String[] args) throws IOException, ResourceException, ScriptException {
        // 指定脚本所在目录
        GroovyScriptEngine groovyScriptEngine = new GroovyScriptEngine(new String[]{
                "/Users/Harry/Workspace/groovy-learning/10-使用脚本和类/8.在Java中使用Groovy脚本",
                "/Users/Harry/Workspace/groovy-learning/10-使用脚本和类/7.在Groovy中使用Groovy脚本"});

        Binding binding = new Binding();
        binding.setProperty("key", "key's value...");
        System.out.println(groovyScriptEngine.run("GroovyScript.groovy", binding));
        System.out.println(groovyScriptEngine.run("Script1.groovy", "null"));
    }
}
