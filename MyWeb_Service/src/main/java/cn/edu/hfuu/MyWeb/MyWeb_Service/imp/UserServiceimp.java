package cn.edu.hfuu.MyWeb.MyWeb_Service.imp;

import cn.edu.hfuu.MyWeb.MyWeb_Dao.IUserDao;
import cn.edu.hfuu.MyWeb.MyWeb_Dao.domain.User;
import cn.edu.hfuu.MyWeb.MyWeb_Service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * Created by ??? on 2016/7/28.
 */
@Component
public class UserServiceimp implements IUserService {

    @Autowired
    IUserDao iUser;
    //??????????????
    public boolean  checkname(String name) {
        System.out.print("false2");


        if( iUser.queryUsername(name)){
             return true;
        }
        else {
            return false;
        }
    }
    //????????????????????????????
    public String queryUserByUserName(String name){

        //????????????????????????????userPsd????????????
       if( iUser.queryUsername(name)){
          User user= iUser.queryUser(name);
           //?????????????????????queryUsername?????????????????????????????
           return(user.getUserPsd());
       }else{
           return null;
       }

    }

    public void UpDataTime(String name,Date date){
          iUser.upDataTime(name,date);
    }

    public void InsertUser(String  name,String psd){
        User user=new User();
        user.setUserName(name);
        user.setUserPsd(psd);
        iUser.insertUser(user);
    }
}
