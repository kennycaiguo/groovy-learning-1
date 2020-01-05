import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Java 向文件写内容
 *
 * @author Harry Zhang
 * @since 2020/1/5 5:18 PM
 */
public class OutputStreamDemo {
    public static void main(String[] args) throws IOException {
        File file = new File("/Users/Harry/Workspace/groovy-learning/7-探索GDK/2.其他拓展/demo-output.txt");
        OutputStream outputStream = new FileOutputStream(file);
        outputStream.write("TEST".getBytes());
        outputStream.flush();
        outputStream.close();
    }
}
