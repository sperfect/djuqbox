package gr.sperfect.djuqbox.webapp.shared.data;

public class BaseDataClass {

	public BaseDataClass() {
		//needed by json converter
		id = "test";
	}
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
