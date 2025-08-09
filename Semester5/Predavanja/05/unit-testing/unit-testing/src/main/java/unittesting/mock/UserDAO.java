package unittesting.mock;

import java.util.HashMap;

public class UserDAO {
	
	private HashMap<String, User> userStore;
	
	public UserDAO() {
		super();
		this.userStore.put("user", new User("user", "user"));
	}

	public UserDAO(HashMap<String, User> userStore) {
		super();
		this.userStore = userStore;
	}

	public User loadByUsername(String userName) {
		User retUser = this.userStore.get(userName);
		
		return retUser;
	}

}
