package cn.edu.hfuu.MyWeb.MyWeb_Web.controller;


import cn.edu.hfuu.MyWeb.MyWeb_Dao.IUserDao;
import cn.edu.hfuu.MyWeb.MyWeb_Service.IStockquery;
import cn.edu.hfuu.MyWeb.MyWeb_Service.IUserService;
import cn.edu.hfuu.MyWeb.MyWeb_Web.request.InformationRequest;
import cn.edu.hfuu.MyWeb.MyWeb_Web.request.LoginRequest;
import cn.edu.hfuu.MyWeb.MyWeb_Web.request.StockRequest;
import cn.edu.hfuu.MyWeb.MyWeb_Web.response.InformationResponse;
import cn.edu.hfuu.MyWeb.MyWeb_Web.response.LoginResponse;
import cn.edu.hfuu.MyWeb.MyWeb_Web.response.Response;
import cn.edu.hfuu.MyWeb.MyWeb_Web.response.StockResponse;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

/**
 * Created by ??? on 2016/8/1.
 */
@Controller
public class MainController extends BaseController {

    @Autowired
    IUserService iUserService;
    @Autowired
    IStockquery iStockquery;
    @Autowired
    IUserDao iUserDao;


    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String login1(@ModelAttribute("loginRequest") LoginRequest loginRequest) {
        return "login";
    }

    @RequestMapping(value = "login123")
    @ResponseBody
    /*@ResponseBody该注解用于将Controller的方法返回的对象，通过适当的HttpMessageConverter转换为指定格式后，写入到Response对象的body数据区。
    使用时机：
    返回的数据不是html标签的页面，而是其他某种格式的数据时（如json、xml等）使用*/
    public Object login(HttpSession httpSession, LoginRequest req) {
        Response rsp = new Response();
        String userName = req.getUserName();
        String userPsd = req.getUserPsd();
        out.println(userName);

     /*   if(httpSession.getAttribute(userName)!=null){
            httpSession.setAttribute("userName",null);
        }
    */

        //?ж????????????
        if (StringUtils.isEmpty(userName)) {
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setSuccess(false);
            loginResponse.setInfo("user name is not allowed empty");
            rsp.setData(loginResponse);
        } else {
            String Psd = iUserService.queryUserByUserName(userName);
            if ((Psd != null) && (Psd.equalsIgnoreCase(userPsd))) {

                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setSuccess(true);
                loginResponse.setInfo("success");
                //????session
                //  request.getSession().setAttribute("sessionUser",userName);
                httpSession.setAttribute("userName", userName);

                rsp.setData(loginResponse);
            } else if ((Psd != null) && (!Psd.equalsIgnoreCase(userPsd))) {
                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setSuccess(false);
                loginResponse.setInfo("passwor发的说法d correct");
                rsp.setData(loginResponse);
            } else {

                LoginResponse loginResponse = new LoginResponse();
                loginResponse.setSuccess(false);
                loginResponse.setInfo("?????????");
                rsp.setData(loginResponse);
            }
        }
        return rsp;
    }


    @RequestMapping("index")
    public String index(HttpSession httpSession, @ModelAttribute("loginRequest") LoginRequest loginRequest) {
        Object s = httpSession.getAttribute("userName");
        // String s=getSessionUserName();
        out.println("session1" + s);
        return "index";
    }

    @RequestMapping("index1")
    @ResponseBody
    //???request???js?е?????response????js??????????
    public Object index1(HttpSession httpSession, HttpServletRequest request, LoginRequest req) {
        Object s = httpSession.getAttribute("userName");
        out.println("session" + s);

        Response rsp = new Response();
        int offset = req.getOffset();
        int limit = req.getLimit();
        String userName = req.getUserName();
        String PSd = req.getUserPsd();

        List<Map<Object, Object>> listmap = iUserDao.selectalluser(offset, limit);

        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setSuccess(true);
        loginResponse.setMap(listmap);
        rsp.setData(loginResponse);

        return rsp;
    }


    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String register(@ModelAttribute("loginRequest") LoginRequest loginRequest) {

        return "register";
    }

    @RequestMapping("register1")
    @ResponseBody
    public Object register1(LoginRequest loginRequest) {
        LoginResponse rsp = new LoginResponse();

        String userName = loginRequest.getUserName();
        String userPsd = loginRequest.getUserPsd();

        //写一个插入用户的程序返回信息为注册成功还是用户已存在

        boolean b = iUserService.checkname(userName);
        if (b) {
            //用户存在
            rsp.setSuccess(true);
        } else {
            iUserService.InsertUser(userName, userPsd);
            rsp.setSuccess(false);
        }
        //  rsp.setData();
        return rsp;
    }


    //通过url跳到我的主页
    @RequestMapping(value = "myinformation", method = RequestMethod.GET)
    public String register(HttpSession httpSession) {

        return "myinformation";
    }


    //跳到我的
    @RequestMapping("myinformation1")
    @ResponseBody
    public Object myinformation1(HttpSession httpSession, InformationRequest informationRequest) {
        //session的值
        String userName = (String) httpSession.getAttribute("userName");
        InformationResponse informationResponse = new InformationResponse();
        informationResponse.setUserName(userName);
        Response rsp = new Response();

        rsp.setData(informationResponse);
        return rsp;
    }

    @RequestMapping(value = "upload")
    public String updata(@RequestParam("name") String name, @RequestParam("file") MultipartFile file, HttpSession httpSession) throws Exception {
        if (!file.isEmpty()) {
            String Name = (String) httpSession.getAttribute("userName");
            File f = new File("D:/MyWeb/" + Name + "/");
            if (!f.exists() && !f.isDirectory()) {
                f.mkdir();
                file.transferTo(new File("D:/MyWeb/" + Name + "/" + file.getOriginalFilename()));
            } else {
                // file.transferTo(new File("D:/" + httpSession.getAttribute("userName") + file.getOriginalFilename()));
                file.transferTo(new File("D:/MyWeb/" + Name + "/" + file.getOriginalFilename()));
            }

            // out.print("<script language=\"javascript\">alert('恭喜你成功了！')");
            return "success";
        } else {
            return "fail";
        }
    }

    @RequestMapping(value = "fail")
    public String fail() {
        return "fail";
    }

    @RequestMapping(value = "success")
    public String success() {
        return "success";
    }


    @RequestMapping(value = "querystock")
    public String querystock() {
        return "QueryStock";
    }

    //查询股票信息
    @RequestMapping(value = "querystock1")
    @ResponseBody
    public Object querystock(HttpSession httpSession, StockRequest stockRequest) {
        Response rsp = new Response();
        StockResponse stockResponse = new StockResponse();

        //String APPKEY ="fb9c12dbb725e1f530f194cb2b08ba9a";
        JSONObject o = iStockquery.getRequest1(stockRequest.getStocknumber());
        System.out.println("返回的jsondata=========" + o);
        String error_code = o.getString("error_code");
         if(error_code.equals("0")) {

             JSONArray jsonArray = o.getJSONArray("result");
             System.out.println("jsonArray========" + jsonArray);
             JSONObject jsonObjects = jsonArray.getJSONObject(0);
             System.out.println("result=========" + jsonObjects);
             //提取到data中的数据
             JSONObject jsonObject = jsonObjects.getJSONObject("data");
             //将所需要的json数据存放进去
             System.out.println("data=========" + jsonObject);
             String gid = jsonObject.getString("gid");
             String name = jsonObject.getString("name");
             String nowPri = jsonObject.getString("nowPri");
             String yestodEndPri = jsonObject.getString("yestodEndPri");
             String todayMax = jsonObject.getString("todayMax");
             String todayMin = jsonObject.getString("todayMin");
             Object traNumber = jsonObject.get("traNumber");
             String date = jsonObject.getString("date");
             stockResponse.setGid(gid);
             stockResponse.setName(name);
             stockResponse.setNowPri(nowPri);
             stockResponse.setTodayMax(todayMax);
             stockResponse.setTodayMin(todayMin);
             stockResponse.setYestodEndPri(yestodEndPri);
             stockResponse.setTraNumber(traNumber);
             stockResponse.setDate(date);

             //***********************System.out.println("gid=========" + gid);

             JSONObject jsonObject1 = jsonObjects.getJSONObject("gopicture");
             String minurl = jsonObject1.getString("minurl");
             String dayurl = jsonObject1.getString("dayurl");
             String weekurl = jsonObject1.getString("weekurl");
             String monthurl = jsonObject1.getString("monthurl");
             stockResponse.setMinurl(minurl);
             stockResponse.setDayurl(dayurl);
             stockResponse.setWeekurl(weekurl);
             stockResponse.setMonthulr(monthurl);
             System.out.println("minurl"+minurl);
             System.out.println("dayurl"+dayurl);
             System.out.println("weekurl"+weekurl);
             System.out.println("monthurl"+monthurl);
             rsp.setMsg(error_code);

             rsp.setData(stockResponse);
         }
        else {
             System.out.println("errorcode="+error_code);
             rsp.setMsg(error_code);
         }

        return rsp;
    }
}
