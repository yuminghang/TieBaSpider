import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileUtils {

    private static File file = new File("C:\\Users\\ymh\\Desktop\\result.txt");
    private static String fileName = "C:\\Users\\ymh\\Desktop\\result.txt";
    public static String fileName2016 = "C:\\Users\\ymh\\Desktop\\result2016.txt";
    public static String fileName2015 = "C:\\Users\\ymh\\Desktop\\result2015.txt";
    public static String fileName2014 = "C:\\Users\\ymh\\Desktop\\result2014.txt";
    public static String fileName2013 = "C:\\Users\\ymh\\Desktop\\result2013.txt";
    public static String fileName2012 = "C:\\Users\\ymh\\Desktop\\result2012.txt";
    public static String fileNameother = "C:\\Users\\ymh\\Desktop\\resultothers.txt";

    /**
     * 向文件追加内容
     *
     * @param content 要追加的内容
     */
    public static void writeFileAppend(String content) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(fileName, true);
            fw.write(content);
            fw.write("\r\n");
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFileAppendDetail(String name, String content) {
        FileWriter fw = null;
        try {
            fw = new FileWriter(name, true);
            fw.write(content);
            fw.write("\r\n");
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
            try {
                fw.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

}
