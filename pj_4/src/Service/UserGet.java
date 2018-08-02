package Service;

import Bean.UserBean;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import static Service.JdbcUtils.getConnected;


public class UserGet {
    public static ArrayList<UserBean> getUserListAll(){
        Connection connection = null;
        PreparedStatement statement = null;
        ArrayList<UserBean> userBeans = new ArrayList<>();
        try{
            connection = getConnected();
            String sql = "SELECT id, name, password, email, phone, activeCode, activeFlag, registerTime FROM user";
            statement = connection.prepareStatement(sql);
            ResultSet rs = statement.executeQuery(sql);
            while(rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String phone = rs.getString("phone");
                String activeCode = rs.getString("activeCode");
                int activeFlag = rs.getInt("activeFlag");
                Date registerTime = rs.getDate("registerTime");
                userBeans.add(new UserBean(id, name, password, email, phone, activeCode, activeFlag, registerTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return userBeans;
    }

    public static UserBean getUserByUsername(String username){
        if(username == null)
            return null;
        ArrayList<UserBean> userBeans = getUserListAll();
        UserBean userBean = new UserBean();
        for(UserBean i:userBeans)
            if(i.getName().equals(username))
                userBean = i;
        return userBean;
    }



}
