package cn.edu.hfuu.MyWeb.MyWeb_Web.request;

/**
 * Created by 志鹏 on 2016/11/4.
 */
public class StockRequest extends Request {

    String stocknumber;
    int num;
    public String getStocknumber() {
        return stocknumber;
    }

    public void setStocknumber(String stocknumber) {
        this.stocknumber = stocknumber;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }


}
