/**
 * Created by ymh on 2016/7/21.
 */
public class bean {
    private String title;
    private String url;
    private int viewNum;

    public bean(String title, String url, int viewNum) {
        this.title = title;
        this.url = url;
        this.viewNum = viewNum;
    }

    @Override
    public String toString() {
        return " [标题]  " + title + "  [链接]  " + url + "  [点击量]&" + viewNum+"&";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViewNum() {
        return viewNum;
    }

    public void setViewNum(int viewNum) {
        this.viewNum = viewNum;
    }
}
