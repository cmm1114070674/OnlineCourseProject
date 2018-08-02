package Dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DAO2<t> {


	/**
	 * �������ݵ�dao�ӿڣ����涨��÷������ݱ�ĸ��ַ���
	 * @author qiu yongting
	 *@param t:dao�����ʵ���������
	 */


		/**
		 * ��������ķ���
		 * @param connection
		 * @param sql
		 * @param args�����ռλ����Object[]���͵Ŀɱ����
		 */
		void batch(Connection connection, String sql, Object[]... args);
		
		/**
		 * ���ؾ����һ��ֵ����������ƽ�����ʣ�ĳ���˵�email��
		 * @param connection
		 * @param sql
		 * @param args
		 * @return
		 * @throws SQLException 
		 */
		<e> e getForValue(Connection connection, String sql, Object... args) throws SQLException;
		
		/**
		 * ����t��һ������
		 * @param connection
		 * @param sql
		 * @param args
		 * @return
		 * @throws SQLException 
		 */
		List<t> getForList(Connection connection, String sql, Object... args) throws SQLException;
		
		/**
		 * ����һ��t�Ķ���
		 * @param connection
		 * @param sql
		 * @param args
		 * @return
		 * @throws SQLException 
		 */
		t get(Connection connection, String sql, Object... args) throws SQLException;
		
		/**
		 * insert,delete,update
		 * @param connection:���ݿ�����
		 * @param sql��sql���
		 * @param args�����ռλ���Ŀɱ����
		 * @throws SQLException 
		 */
		void update(Connection connection, String sql, Object... args) throws SQLException;
	}


