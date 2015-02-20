package gr.sperfect.djuqbox.webapp.shared.data;

import org.fusesource.restygwt.client.Attribute;

public class BaseDataClass {

	public BaseDataClass() {
		//needed by json converter
		id = "test";
		
	}
	
	public BaseDataClass(String aName) {
		name = aName;
		id = "test2";
	}

	
	
	//protected String id;
	public String id; //prepei na einai public gia na to balw san @Attribute("id") sto RestApiService.getRoomUsers()
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
