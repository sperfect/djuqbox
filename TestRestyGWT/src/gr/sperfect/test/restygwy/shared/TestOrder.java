package gr.sperfect.test.restygwy.shared;

public class TestOrder {

	public TestOrder() {
		// TODO Auto-generated constructor stub
	}
	public TestOrder(String aName) {

		setName(aName);
	}

	public String getName() {
		return name;
	}
	void setName(String name) {
		this.name = name; 
	}

	private String name;
}
