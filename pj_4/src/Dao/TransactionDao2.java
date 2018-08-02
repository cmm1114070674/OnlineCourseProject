package Dao;


import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import Service.ReflectionUtils;


/**
 * ʹ��quertRuner�ṩ�����ʵ��
 * @author qiu yongting
 *
 * @param <t>�������贫��ķ�������
 */
public class TransactionDao2<t> implements DAO2<t> {
	
		private QueryRunner queryRunner = null;
		private Class<t> type;
		
		public TransactionDao2() {
			queryRunner = new QueryRunner();
			type = ReflectionUtils.getSuperGenericType(getClass());
		}
		
		@Override
		public void batch(Connection connection, String sql, Object[]... args) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public <e> e getForValue(Connection connection, String sql, Object... args) throws SQLException {
			Object result = queryRunner.query(connection, sql, 
					new ScalarHandler(),args);
			return (e) result;
		}

		@Override
		public List<t> getForList(Connection connection, String sql, Object... args) 
				throws SQLException {
			List<t> list = queryRunner.query(connection, sql, new BeanListHandler<>(type),args);
			
			return list;
		}

		@Override
		public t get(Connection connection, String sql, Object... args) throws SQLException {
			return queryRunner.query(connection, sql,new BeanHandler<>(type),args);
		}

		@Override
		public void update(Connection connection, String sql, Object... args) throws SQLException {
			queryRunner.execute(connection, sql, args);
			
			
		}

	}


