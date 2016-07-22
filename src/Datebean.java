/**
 * Created by ymh on 2016/7/22.
 */
public class Datebean {
    private bean data;
    private int pubTime;

    public Datebean(bean data, int pubTime) {
        this.data = data;
        this.pubTime = pubTime;
    }

    public bean getData() {
        return data;
    }

    public void setData(bean data) {
        this.data = data;
    }

    public int getPubTime() {
        return pubTime;
    }

    public void setPubTime(int pubTime) {
        this.pubTime = pubTime;
    }

    @Override
    public String toString() {
        return " [标题]  " + data.getTitle() + "  [链接]  " + data.getUrl() + "  [点击量]  " + data.getViewNum();
    }
}
