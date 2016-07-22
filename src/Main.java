import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/45.0.2454.101 Safari/537.36";
    private static String preUrl = "http://tieba.baidu.com";
    private static String preHomeUrl = "http://tieba.baidu.com/f?kw=%E5%B5%A9%E5%8E%BF&ie=utf-8&pn=";
    private static int pageNum = 0;
    private static List<bean> list = new ArrayList<>();
    private static List<String> urlList = new ArrayList<>();
    private static URLConnection connection;
    private static int count = 0;
    private static List<bean> result = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        readTxtFile("C:\\Users\\ymh\\Desktop\\result.txt");
        sortData();
    }

    private static void sortData() {
        Collections.sort(result, new SortByNum());
//        PraseDataUtils.getDataTime(result);
        for (bean temp : result) {
            System.out.println(temp.getTitle() + " " + temp.getUrl() + " " + temp.getViewNum());
        }
    }

    public static void readTxtFile(String filePath) {
        try {
            String encoding = "utf-8";
            File file = new File(filePath);
            if (file.isFile() && file.exists()) { //判断文件是否存在
                InputStreamReader read = new InputStreamReader(
                        new FileInputStream(file), encoding);//考虑到编码格式
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTxt = null;
                while ((lineTxt = bufferedReader.readLine()) != null) {
//                    System.out.println(lineTxt);
                    mergeData(lineTxt);
                }
                read.close();
            } else {
                System.out.println("找不到指定的文件");
            }
        } catch (Exception e) {
            System.out.println("读取文件内容出错");
            e.printStackTrace();
        }
    }

    private static void mergeData(String lineTxt) {
        int titleStart = lineTxt.indexOf("[标题]  ");
        titleStart += 6;
        int titleEnd = lineTxt.indexOf("  [链接]");
        String title = lineTxt.substring(titleStart, titleEnd);

        int urlStart = lineTxt.indexOf("[链接]  ");
        urlStart += 6;
        int urlEnd = lineTxt.indexOf("  [点击量]");
        String url = lineTxt.substring(urlStart, urlEnd);

        int numStart = lineTxt.indexOf("[点击量]&");
        numStart += 6;
        int numEnd = lineTxt.indexOf("&  第");
        int num = Integer.parseInt(lineTxt.substring(numStart, numEnd));
//        System.out.println(title + " " + url + " " + num);
        bean data = new bean(title, url, num);
        result.add(data);
    }

    private static void begin() {
        long start = System.currentTimeMillis();
        while (pageNum <= 825400) {
            list.clear();
            urlList.clear();
            String result = HttpUtils.fetchURL(preHomeUrl, pageNum);
            praseData(result);
            pageNum += 50;
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("总耗时：" + (end - start) / 1000 + "s");
    }

    private static void praseData(String result) {
        Document doc = Jsoup.parse(result);
        Elements elements = doc.select("li.j_thread_list");
        int num = 0;
        for (Element element : elements) {
            String temp = element.select("span.threadlist_rep_num").text();
            if (temp.length() > 0) {
                num = Integer.parseInt(temp);
                if (num > 100) {
                    String title = element.select("div.threadlist_title").select("a").attr("title");
                    String url = element.select("div.threadlist_title").select("a").attr("href");
                    bean data = new bean(title, preUrl + url, num);
                    if (!urlList.contains(url)) {
                        count++;
                        urlList.add(url);
                        list.add(data);
                        FileUtils.writeFileAppend(data.toString() + "  第" + count + " 条");
                        System.out.println(data.toString() + "  第" + count + " 条");
                    }
                }
            }
        }
    }


    static class SortByNum implements Comparator<bean> {


        @Override
        public int compare(bean o1, bean o2) {
            if (o1.getViewNum() < o2.getViewNum()) return -1;
            else if (o1.getViewNum() > o2.getViewNum()) return 1;
            else return 0;
        }
    }


}
