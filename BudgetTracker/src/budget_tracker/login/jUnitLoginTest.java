package budget_tracker.login;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class jUnitLoginTest {

	@Test
	void loginTest() {
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
