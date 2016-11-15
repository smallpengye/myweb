package cn.edu.hfuu.MyWeb.MyWeb_Web.response;

/**
 * Created by 志鹏 on 2016/11/4.
 */
public class StockResponse extends Response {
    String gid;
    String name;
    String nowPri;
    String yestodEndPri;
    String todayMax;
    String todayMin;
    Object traNumber;
    String date;
    String minurl;
    String dayurl;
    String weekurl;
    String monthurl;


    public String getMinurl() {
        return minurl;
    }

    public void setMinurl(String minurl) {
        this.minurl = minurl;
    }

    public String getDayurl() {
        return dayurl;
    }

    public void setDayurl(String dayurl) {
        this.dayurl = dayurl;
    }

    public String getWeekurl() {
        return weekurl;
    }

    public void setWeekurl(String weekurl) {
        this.weekurl = weekurl;
    }

    public String getMonthulr() {
        return monthurl;
    }

    public void setMonthulr(String monthulr) {
        this.monthurl = monthulr;
    }


    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Object getTraNumber() {
        return traNumber;
    }

    public void setTraNumber(Object traNumber) {
        this.traNumber = traNumber;
    }

    public String getTodayMax() {
        return todayMax;
    }

    public void setTodayMax(String todayMax) {
        this.todayMax = todayMax;
    }

    public String getTodayMin() {
        return todayMin;
    }

    public void setTodayMin(String todayMin) {
        this.todayMin = todayMin;
    }

    public String getYestodEndPri() {
        return yestodEndPri;
    }

    public void setYestodEndPri(String yestodEndPri) {
        this.yestodEndPri = yestodEndPri;
    }

    public String getNowPri() {
        return nowPri;
    }

    public void setNowPri(String nowPri) {
        this.nowPri = nowPri;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}