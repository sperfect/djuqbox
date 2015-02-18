package gr.sperfect.djuqbox.webapp.shared.data;

public class Room extends BaseDataClass{

	private String name;

	public Room(String aName) {
		name = aName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
