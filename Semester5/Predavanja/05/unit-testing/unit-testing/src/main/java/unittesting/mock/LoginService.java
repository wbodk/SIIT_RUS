package unittesting.mock;

public class LoginService {

	UserDAO userDao;
	
	public boolean login(String username, String password) {
		User user = this.userDao.loadByUsername(username);
		
		// success login if DAO object has found user
		if (user != null) {
			return true;
		}
		
		return false;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
}
