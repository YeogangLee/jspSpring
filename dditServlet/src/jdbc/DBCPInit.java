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
			String userId = "jspexam";
			String pw = "java";
			
			//Connection Pool�� ���ο� Connection�� ���� �� ����� Connection Factory�� ����
			ConnectionFactory connFactory = new DriverManagerConnectionFactory(jdbcUrl, userId, pw);
	
			//PoolableConnection�� �����ϴ� Factory�� ����.
			//Connection Pool�� Connection ��ü�� ���� �� PoolableConnection�� �����.
			PoolableConnectionFactory poolableConnFactory = new PoolableConnectionFactory(connFactory, null);
			
			//Connection�� ��� ���� �˻�� ����� query�� ������.
			poolableConnFactory.setValidationQuery("select 1");
			
			//Connection Pool�� ���� ���� ����
			GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
			//��� �ִ� connection ��ü�� pool���� �����ϴ� �ð� ����(1��/1000��)
			poolConfig.setTimeBetweenEvictionRunsMillis(1000L*60L*5L);
			//Ǯ(�۾���)�� ���������� Ŀ�ؼ�(����)�� ��ȿ���� �˻����� ����
			poolConfig.setTestWhileIdle(true); //�� ���� ������ ��ȯ�� �ȴ�.
			//Ŀ�ؼ�(����) �ּ� ���� (�ο���)
			poolConfig.setMinIdle(4);	//�ּ� 4���� Ŀ�ؼ� ��ü�� ������.
			//Ŀ�ؼ�(����) �ִ� ���� (�ο���)
			poolConfig.setMaxIdle(50); //50���� ���, ����
			
			//Ŀ�ؼ� Ǯ�� ���� ���� ����
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
			
			//PoolableConnectionFactory���� ������ Ŀ�ؼ� Ǯ�� ������
			poolableConnFactory.setPool(connectionPool); // (�μ��� �۾��� ����)
	//		�μ�, �۾���(�۾��� �����)
			//Ŀ�ؼ� Ǯ�� �����ϴ� JDBC ����̹� ���
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			
			//Ŀ�ؼ� Ǯ ����̹��� ������ Ŀ�ؼ� Ǯ�� ���
			driver.registerPool("chapter14", connectionPool);
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception ex) {
			throw new RuntimeException();
		}
	}
	
	
}
