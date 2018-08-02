package Dao.DaoImpl;

import Dao.DAO;
import Dao.LoginDao;
import Bean.LoginBean;

public class LoginDaoImpl extends DAO<LoginBean> implements LoginDao {

	@Override
	public boolean checkLogin(LoginBean loginBean) {
		String sql = "select count(id) from user where name = ? and password = ? and activeFlag = ?";
		
		long count = getForValue(sql, loginBean.getUsername(),loginBean.getPassword(),1);
		boolean flag = (count == 0) ? false : true;
		return flag;
	}

}
