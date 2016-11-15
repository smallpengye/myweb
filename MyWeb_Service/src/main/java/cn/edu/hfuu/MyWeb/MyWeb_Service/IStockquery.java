package cn.edu.hfuu.MyWeb.MyWeb_Service;

import net.sf.json.JSONObject;

/**
 * Created by 志鹏 on 2016/11/4.
 */
public interface IStockquery {
   JSONObject getRequest1(String Stocknumber);
   void getRequest2(String Stocknumber);
   void getRequest3(String Stocknumber);
   //查询第几页，每页20条数据
   void getRequest4(int num);
   void getRequest5(int num );
   void getRequest6(int num);
   void getRequest7(int num);

}
