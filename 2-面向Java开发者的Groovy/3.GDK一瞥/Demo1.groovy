// Java 的方式使用 Process 类与系统级进程交互
import java.io.*
public class ExecuteProcess {
    public static void main(String[] args){
        try {
            Process proc = Runtime.getRuntime().exec("svn help");
            BufferedReader result = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line;
            while ((line = result.readLine()) != null){
                System.out.println(line);
            }
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}


/*
    Groovy 的方式
    通过向 JDK 的各种类添加便捷方法，GDK 拓展了 JDK。
*/
println "svn help".execute().text

/*
 "svn help" 是一个 String 实例，该实例调用 execute() 方法时，Groovy 拓展了 Process 类的实例，就像 Java 的Runtime类调用 exec() 方法一样。
 验证：
 
*/
println "svn help".execute().getClass().name

