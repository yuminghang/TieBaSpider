import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ymh on 2016/7/22.
 */
public class PraseDataUtils {
    public static String preUrl = "http://tieba.baidu.com/p/";
    public static List<Datebean> list2016 = new ArrayList<>();
    public static List<Datebean> list2015 = new ArrayList<>();
    public static List<Datebean> list2014 = new ArrayList<>();
    public static List<Datebean> list2013 = new ArrayList<>();
    public static List<Datebean> list2012 = new ArrayList<>();
    public static List<Datebean> listother = new ArrayList<>();
    private static int count2016, count2015, count2014, count2013, count2012, countother;

    public static void getDataTime(List<bean> result) {
        for (bean temp : result) {
            int totalCount = count2016 + count2015 + count2014 + count2013 + count2012 + countother;
            if (totalCount <= 120) {
                String res = HttpUtils.fetchURL1(temp.getUrl());
                praseData(res, temp);
            }
        }
        for (Datebean temp : list2016) {
            FileUtils.writeFileAppendDetail(FileUtils.fileName2016, temp.toString());
        }
        for (Datebean temp : list2015) {
            FileUtils.writeFileAppendDetail(FileUtils.fileName2015, temp.toString());
        }
        for (Datebean temp : list2014) {
            FileUtils.writeFileAppendDetail(FileUtils.fileName2014, temp.toString());
        }
        for (Datebean temp : list2013) {
            FileUtils.writeFileAppendDetail(FileUtils.fileName2013, temp.toString());
        }
        for (Datebean temp : list2012) {
            FileUtils.writeFileAppendDetail(FileUtils.fileName2012, temp.toString());
        }
        for (Datebean temp : listother) {
            FileUtils.writeFileAppendDetail(FileUtils.fileNameother, temp.toString());
        }
    }

    private static void praseData(String result, bean temp) {
        Document doc = Jsoup.parse(result);
        String attr = doc.select("div.l_post").get(1).attr("data-field");
        int timeIndex = attr.indexOf("\"date\":\"");
        timeIndex += 8;
        int time = Integer.parseInt(attr.substring(timeIndex, timeIndex + 4));
        Datebean databean = new Datebean(temp, time);
        System.out.println(databean);
        addData(time, databean);
    }

    private static void addData(int time, Datebean databean) {

        switch (time) {
            case 2016:
                if (list2016.size() <= 20) {
                    list2016.add(databean);
                    count2016++;
                }
                break;
            case 2015:
                if (list2015.size() <= 20) {
                    list2015.add(databean);
                    count2015++;
                }
                break;
            case 2014:
                if (list2014.size() <= 20) {
                    list2014.add(databean);
                    count2014++;
                }
                break;
            case 2013:
                if (list2013.size() <= 20) {
                    list2013.add(databean);
                    count2013++;
                }
                break;
            case 2012:
                if (list2012.size() <= 20) {
                    list2012.add(databean);
                    count2012++;
                }
                break;
            default:
                if (listother.size() <= 20) {
                    listother.add(databean);
                    countother++;
                }
        }
    }
}
