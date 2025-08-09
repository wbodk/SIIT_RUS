package unittesting.mock;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertTrue;

import org.mockito.Mockito;
import org.testng.annotations.Test;

public class LoginServiceTest {

	@Test
	public void loginBasic() {

		//instantiate login service. We test its functionalities in isolation
		LoginService service = new LoginService();

		//we mock DAO layer because we do not test its correctness in this test
		UserDAO userDao = Mockito.mock(UserDAO.class);
		//mock object successfully logins user "admin"
		when(userDao.loadByUsername("admin")).thenReturn(new User("admin", "admin"));

		//service will use mocked DAO object
		service.setUserDao(userDao);

		//test service login method
		assertTrue(service.login("admin", "admin"));
		
		// verify that login method has called UserDao::loadByUsername
		verify(userDao, times(1)).loadByUsername("admin");
	}
}