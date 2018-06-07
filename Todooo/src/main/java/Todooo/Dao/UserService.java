package Todooo.Dao;

public class UserService {

	public boolean isValidUser(User user) {
		
		if (user.getPassword().equals("servlet")) {
			user.setName("ㄱㄴㄷ");
			return true;
		}
		
		return false;
	}
	
	public Profile getProfile(String id) {
		
		return null;
	}
}
