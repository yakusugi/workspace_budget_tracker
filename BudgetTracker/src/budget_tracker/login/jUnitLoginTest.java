package budget_tracker.login;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.mysql.cj.jdbc.MysqlDataSource;

class jUnitLoginTest {

	@BeforeAll
	@Test
	void loginTest() throws NamingException, SQLException {
		InitialContext ic = new InitialContext();
		
		String inputId = "testuser";
		String inputPassword = "testpass";

		LoginServletLogic loginServletLogic = new LoginServletLogic();

		try {
			ic.createSubcontext("java:");
			ic.createSubcontext("java:comp");
			ic.createSubcontext("java:comp/env");
			ic.createSubcontext("java:comp/env/jdbc");
			
			MysqlDataSource ds = new MysqlDataSource();
			ds.setUser("db_admin");
			ds.setPassword("dbpass");
			ds.setDatabaseName("new_budget_tracker_db");
			ds.setServerName("localhost");
			ds.setPortNumber(3306);
			
			ds = (MysqlDataSource) ic.lookup("java:comp/env/jdbc/searchman");

			ic.bind("java:comp/env/jdbc/searchman", ds);

			if (loginServletLogic.login(inputId, inputPassword)) {
				System.out.println("login success");
			} else {
				System.out.println("login failed");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
