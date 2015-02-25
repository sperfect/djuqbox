package gr.sperfect.djuqbox.webapp.shared.data;

import java.util.Date;

import org.fusesource.restygwt.client.Attribute;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

public class BaseDataClass {

	
	@Id
	public Long id;
	@Index
	public String name;
	
	private Date date_insert;
	private Date date_update;
	
	private HATEOASLink link;
	
	public BaseDataClass() {
		//needed by json converter
		//id = "test";
		
		
	}
	
	
	
	public BaseDataClass(String aName) {
		name = aName;
		//id = "test2";
	}

	
	
	//protected String id;
	//public String id; //prepei na einai public gia na to balw san @Attribute("id") sto RestApiService.getRoomUsers()
	//protected String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public Date getDateInsert() {
		return date_insert;
	}
	
	public void setDateInsert(Date date) {
		date_insert = date;		
	}
	
	public Date getDateUpdate() {
		return date_update;
	}
	
	public void setDateUpdate(Date date) {
		date_update = date;
		
	}



	private HATEOASLink getLink() {
		return link;
	}



	private void setLink(HATEOASLink link) {
		this.link = link;
	}
	
}
