package selekh;

public class User {
	public String name;
	public User(String name, int iD) {
		this.name = name;
		ID = iD;
	}
	public int ID;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
}
