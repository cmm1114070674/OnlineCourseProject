package Dao.DaoImpl;

import Dao.DAO;

import Dao.RegisterDao;
import Bean.RegisterBean;

public class RegisterDaoImpl extends DAO<RegisterBean> implements RegisterDao {

	@Override
	public boolean checkUsername(String username) {
		String sql = "select count(id) from user where name = ?";
		long count = getForValue(sql, username);
		boolean flag = (count == 0) ? true : false;
		return flag;
	}

	@Override
	public void save(RegisterBean registerBean) {
		String sql = "insert into user(name,password,email,phone,activeCode,activeFlag,registerTime) values(?,?,?,?,?,?,?)";
		
		update(sql, registerBean.getUsername(),registerBean.getPassword(),
				registerBean.getEmail(),registerBean.getPhone(),registerBean.getActiveCode(),registerBean.getActiveFlag()
				,registerBean.getRegisterTime());

	}
	
	public boolean checkActiveCode(String activeCode) {
		String sql = "select count(id) from user where activeCode = ?";
		long count = getForValue(sql, activeCode);
		boolean flag = (count == 0) ? false : true;
		return flag;
	}
	
	public void changeActiveFlagByActiveCode(String activeCode) {
		String sql = "update user set activeFlag = ? where activeCode = ?";
		update(sql, 1,activeCode);
	}

}
