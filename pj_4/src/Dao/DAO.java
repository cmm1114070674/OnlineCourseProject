package Dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import Service.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;;

/**
 * ��װ�˻�����CRUD�������Թ�����̳�ʹ��
 * 
 * ��ǰDAOֱ���ڷ����л�ȡ���ݿ�����
 * 
 * ����DAO��ȡDBUtils����
 * @author qiu yongting
 *
 * @param <t>:��ǰDAO�����ʵ�����������ʲô
 */
public class DAO<t> {
	
	private Class<t> clazz;
	
	private QueryRunner queryRunner = new QueryRunner();
	
	public DAO(){
		Type superClass = getClass().getGenericSuperclass();
		
		if(superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType)superClass;
			
			Type[] typeArgs = parameterizedType.getActualTypeArguments();
			
			if(typeArgs != null && typeArgs.length != 0) {
				if(typeArgs[0] instanceof Class) {
					clazz = (Class<t>) typeArgs[0];
				}
			}
		}
	}
	
	/**
	 * ����ĳһ���ֶε�ֵ�����緵��ĳһ����¼��customerName���򷵻ص����ݱ����ж�������¼
	 * @param sql
	 * @param args
	 * @return
	 */
	public <e> e getForValue(String sql,Object ...args) {

		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			
			return (e)queryRunner.query(connection, sql,new ScalarHandler(), args);
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.releaseConnection(connection);
		}
		
		return null;
	}
	
	/**
	 * ����t��Ӧ��list
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<t> getForList(String sql,Object ...args){

		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			
			return queryRunner.query(connection, sql,new BeanListHandler<t>(clazz), args);
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.releaseConnection(connection);
		}
		return null;
		
	}
	
	/**
	 * ���ض�Ӧ��t��ʵ����Ķ���
	 * @param sql
	 * @param args
	 * @return
	 */
	public t get(String sql,Object ...args) {

		Connection connection = null;
		
		try {
			connection = JdbcUtils.getConnection();
			
			return queryRunner.query(connection, sql,new BeanHandler<t>(clazz), args);
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.releaseConnection(connection);
		}
		
		return null;
	}

	/**
	 * �÷�����װ��delete��update��insert����
	 * @param sql
	 * @param args
	 */
	public void update(String sql, Object ...args) {
		Connection connection = null;
		try {
			connection = JdbcUtils.getConnection();
			queryRunner.update(connection, sql, args);
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtils.releaseConnection(connection);
		}
	}
	
	public int updateWithKeyReturned(String sql , Object ...args) {
		Connection connection = null;
		PreparedStatement prepareStatement = null;
		ResultSet resultSet = null;
		int key = 0;
		
		try {
			connection = JdbcUtils.getConnection();
			prepareStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
			for(int i = 0;i < args.length ; i++) {
				prepareStatement.setObject(i+1, args[i]);
			}
			
			prepareStatement.executeUpdate();
			
			resultSet = prepareStatement.getGeneratedKeys();
			if(resultSet.next()) {
				key = resultSet.getInt(1);
				System.out.println(resultSet.getInt(1));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(resultSet != null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(prepareStatement != null) {
				try {
					prepareStatement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return key;
	}
	
	
	
}
