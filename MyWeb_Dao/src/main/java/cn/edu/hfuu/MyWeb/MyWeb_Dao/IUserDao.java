package cn.edu.hfuu.MyWeb.MyWeb_Dao;


import cn.edu.hfuu.MyWeb.MyWeb_Dao.domain.User;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by 志鹏 on 2016/7/28.
 */
public interface IUserDao {

    User queryUser(String name);

    void insertUser(User user);

    void upDataTime(String name, Date data);
    boolean queryUsername(String name);
    List<Map<Object, Object>> selectalluser(int offset, int limit);

}
