package gr.sperfect.djuqbox.webapp.shared.data;

public class BaseDataClass {

	public BaseDataClass() {
		//needed by json converter
		id = "test";
		
	}
	
	public BaseDataClass(String aName) {
		name = aName;
	}

	
	
	protected String id;
	protected String name;

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
}
