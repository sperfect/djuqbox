package gr.sperfect.djuqbox.webapp.shared.data;

public class BaseDataClass {

	public BaseDataClass() {
		//needed by json converter
	}
	private String id;

	String getId() {
		return id;
	}

	void setId(String id) {
		this.id = id;
	}
}
