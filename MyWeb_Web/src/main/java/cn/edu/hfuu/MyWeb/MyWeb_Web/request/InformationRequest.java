package cn.edu.hfuu.MyWeb.MyWeb_Web.request;

/**
 * Created by 志鹏 on 2016/10/21.
 */
public class InformationRequest extends Request {
    private String userName;

    private String userPsd;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPsd() {
        return userPsd;
    }

    public void setUserPsd(String userPsd) {
        this.userPsd = userPsd;
    }
}
