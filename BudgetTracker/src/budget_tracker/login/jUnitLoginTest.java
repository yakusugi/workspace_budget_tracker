package budget_tracker.login;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.junit.jupiter.api.Test;

class jUnitLoginTest {

	@Test
	void loginTest() throws NamingException, SQLException {
		InitialContext ic = new InitialContext();
		DataSource ds = null;
		
		ic.createSubcontext("java:");
		ic.createSubcontext("java:comp");
		ic.createSubcontext("java:comp/env");
		ic.createSubcontext("java:comp/env/jdbc");
		
		Context envContext  = (Context)ic.lookup("java:/comp/env");
		DataSource dataSource;
		dataSource = (DataSource)envContext.lookup("java:comp/env/jdbc/searchman");
		Connection conn = null;
		conn = ds.getConnection();
		
		String inputId = "testuser";
		String inputPassword = "testpass";

		LoginServletLogic loginServletLogic = new LoginServletLogic();

		try {

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
