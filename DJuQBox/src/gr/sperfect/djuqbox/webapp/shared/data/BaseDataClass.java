package gr.sperfect.djuqbox.webapp.shared.data;

import java.util.Date;

import org.fusesource.restygwt.client.Json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

public class BaseDataClass {

	
	@Id
	public Long id;
	//prepei na einai public gia na to balw san @Attribute("id") den ginetai me getter se klhsh tou Resty
	
	@Index
	private String name;
	
	@Index
	private String code;
	
	
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

	
	

	 
	
//	public Long getID() {
//		return id;
//	}
//
//	
//	public void setID(Long id) {
//		this.id = id;
//	}
	
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



	public HATEOASLink getLink() {
		return link;
	}



	public void setLink(HATEOASLink link) {
		this.link = link;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}
	
}
