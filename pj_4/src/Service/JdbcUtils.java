package Service;

import java.beans.PropertyVetoException;
	import java.sql.Connection;
	import java.sql.SQLException;

	import javax.sql.DataSource;

	import com.mchange.v2.c3p0.ComboPooledDataSource;

	import static java.sql.DriverManager.getConnection;

public class JdbcUtils {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String CONNECTION = "jdbc:mysql://localhost:8787/pj?useSSL=false&useUnicode=true&amp&characterEncoding=UTF-8&amp&serverTimezone=GMT%2B8";
	private static final String USER = "root";
	private static final String PASSWORD = "zaqw1234";

	public static Connection getConnected(){
		try{
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			System.out.println("No Driver");
			e.printStackTrace();
		}
		Connection connection = null;
		try{
			connection = java.sql.DriverManager.getConnection(CONNECTION, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("No connection");
			e.printStackTrace();
		}
		return connection;
	}

		
		public static void releaseConnection(Connection connection) {
			try {
				if(connection != null) {
					connection.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

		
		private static ComboPooledDataSource dataSource = null;
		static {
			dataSource = new ComboPooledDataSource();
			try {
				dataSource.setDriverClass( "com.mysql.cj.jdbc.Driver" );
			} catch (PropertyVetoException e) {
				e.printStackTrace();
			}           
			dataSource.setJdbcUrl( "jdbc:mysql://localhost:8787/pj?useSSL=false&useUnicode=true&amp&characterEncoding=UTF-8&amp&serverTimezone=GMT%2B8" );
			dataSource.setUser("root");
			dataSource.setPassword("zaqw1234");
		}
		
		public static Connection getConnection() throws SQLException {
			return dataSource.getConnection();
		}
	}


