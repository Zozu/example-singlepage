package selekh;

public class User {
	public String name;
	public User(String name, String password, int iD) {
		this.name = name;
		this.password = password;
		ID = iD;
	}
	public String password;
	public int ID;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
}
