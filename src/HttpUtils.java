import java.io.*;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ymh on 2016/7/22.
 */
public class HttpUtils {
    private static URLConnection connection;

    public static String fetchURL(String preHomeUrl, int strUrl) {
        StringBuilder sb = new StringBuilder();
        URL url = null;
        try {
            url = new URL(preHomeUrl + strUrl);
            connection = url.openConnection();
//            connection.setRequestProperty("User-Agent", USER_AGENT);
            InputStream is = connection.getInputStream();
            Reader r = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(r);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            return "";
        }
        return sb.toString();
    }

    public static String fetchURL1(String strUrl) {
        StringBuilder sb = new StringBuilder();
        URL url = null;
        try {
            url = new URL(strUrl);
            connection = url.openConnection();
//            connection.setRequestProperty("User-Agent", USER_AGENT);
            InputStream is = connection.getInputStream();
            Reader r = new InputStreamReader(is);
            BufferedReader bufferedReader = new BufferedReader(r);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            return "";
        }
        return sb.toString();
    }
}
