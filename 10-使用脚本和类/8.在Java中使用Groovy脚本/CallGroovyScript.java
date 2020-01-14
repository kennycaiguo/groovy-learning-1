import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * 【Java 中调用 Groovy 脚本】
 * - JSR223:
 * JSR223 规范在 JVM 与脚本语言之间架起了一座桥梁，它为 Java 和一些实现了 JSR223 脚本引擎API的脚本引擎语言提供了一种标准的交互方式。
 * JDK1.6 开始默认包含了 JSR223。
 *
 * <p>
 * Java 中要使用一个脚本，需要使用脚本引擎，脚本引擎可以通过 ScriptEngineManager 的 getEngineByName 获得。
 * 要执行一个脚本，可以通过调用 eval()，这里使用 Groovy 脚本要确保 groovy-2.5.8.jar 在 classpath 下。
 *
 * @author Harry Zhang
 * @since 2020/1/14 10:55 AM
 */
public class CallGroovyScript {
    public static void main(String[] args) {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        // 这是一个 org.codehaus.groovy.jsr223.GroovyScriptEngineImpl@4b9af9a9 实现
        ScriptEngine groovy = scriptEngineManager.getEngineByName("groovy");
        System.out.println("Calling Script From Java...");
        try {
            // 文本形式传入脚本
            groovy.eval("println 'Running Groovy Script...'.dump()");

            /*
             * 文件形式传入脚本，eval 方法有一个返回值可以用于接收脚本的返回参数。
             *
             * 「类结构」
             * ScriptEngine（接口）
             *      是一个基本接口，提供一个脚本引擎的基本功能。
             *
             * AbstractScriptEngine（接口）
             *      继承于 ScriptEngine，为一些变体的 eval() 方法提供标准实现。其通过 ScriptContext 接口提供对于 Bindings 的操作。
             *
             * GroovyScriptEngineImpl（类）
             *      实现于 AbstractScriptEngine，是 JSR-223 引擎的实现。
             *
             * ScriptContext（接口）
             *      该接口的实现类允许脚本引擎连接 Java 宿主应用中的对象（比如 Bindings）
             *
             * Bindings（接口）
             *      继承于 Map，是一个键值对结构。用于存储指定作用域的值。
             */
            groovy.put("key", "1111");
            System.out.println(groovy.get("key"));

            Object name = groovy.eval(new FileReader("/Users/Harry/Workspace/groovy-learning/10-使用脚本和类/8.在Java中使用Groovy脚本/GroovyScript.groovy"));
            System.out.println("name: " + name);

            System.out.println(groovy.get("key"));
        } catch (ScriptException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
