package jdbc;

import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

//DBCP : DataBase Connection Pool
public class DBCPInit extends HttpServlet {
	
	@Override
	public void init() throws ServletException {
		loadJDBCDriver();
		initConnectionPool();
	}
	
	private void loadJDBCDriver() {
		//Connection Pool�� ���ο��� ����� JDBC Drvier�� Loading��.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("JDBC Driver �ε� ����", ex);
		}
	}
	
	private void initConnectionPool() {
		try {
			String jdbcUrl = "jdbc:oracle:thin:@localhost:1521/xe";
			String userId = "board";
			String pw = "java";
			
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, userId, pw);
	
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			
			poolableConnFactory.setValidationQuery("select 1");
			
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L*60L*5L);
			poolConfig.setTestWhileIdle(true); //�� ���� ������ ��ȯ�� �ȴ�.
			poolConfig.setMinIdle(4);	//�ּ� 4���� Ŀ�ؼ� ��ü�� ������.
			poolConfig.setMaxIdle(50); //50���� ���, ����
			
			//Ŀ�ؼ� Ǯ�� ���� ���� ����
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
			
			poolableConnFactory.setPool(connectionPool); // (�μ��� �۾��� ����)
			
			//Ŀ�ؼ� Ǯ�� �����ϴ� JDBC ����̹� ���
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			
			//Ŀ�ؼ� Ǯ ����̹��� ������ Ŀ�ؼ� Ǯ�� ���
			driver.registerPool("board", connectionPool);
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (Exception ex) {
			throw new RuntimeException();
		}
	}
	
	
}
