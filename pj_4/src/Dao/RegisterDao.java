package Dao;

import Bean.RegisterBean;

public interface RegisterDao {
public boolean checkUsername(String username);
public void save(RegisterBean registerBean);
}
