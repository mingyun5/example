package Todooo.Dao;

public class Profile {

	String id;
	String name;
	String desc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Profile [id=" + id + ", name=" + name + ", desc=" + desc + "]";
	}

}
