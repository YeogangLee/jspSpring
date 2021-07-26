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
		//Connection Pool이 내부에서 사용할 JDBC Drvier를 Loading함.
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("JDBC Driver 로딩 실패", ex);
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
			poolConfig.setTestWhileIdle(true); //할 일이 없으면 반환이 된다.
			poolConfig.setMinIdle(4);	//최소 4개는 커넥션 객체를 가진다.
			poolConfig.setMaxIdle(50); //50명이 대기, 당직
			
			//커넥션 풀의 설정 정보 생성
			GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnFactory, poolConfig);
			
			poolableConnFactory.setPool(connectionPool); // (부서에 작업장 생성)
			
			//커넥션 풀을 제공하는 JDBC 드라이버 등록
			Class.forName("org.apache.commons.dbcp2.PoolingDriver");
			PoolingDriver driver = (PoolingDriver)DriverManager.getDriver("jdbc:apache:commons:dbcp:");
			
			//커넥션 풀 드라이버에 생성된 커넥션 풀을 등록
			driver.registerPool("board", connectionPool);
		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			
		} catch (Exception ex) {
			throw new RuntimeException();
		}
	}
	
	
}
