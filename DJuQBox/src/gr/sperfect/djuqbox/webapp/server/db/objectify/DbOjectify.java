package gr.sperfect.djuqbox.webapp.server.db.objectify;

import static gr.sperfect.djuqbox.webapp.server.db.objectify.OfyService.ofy;
import gr.sperfect.djuqbox.webapp.server.db.IDB;
import gr.sperfect.djuqbox.webapp.shared.data.BaseDataClass;
import gr.sperfect.djuqbox.webapp.shared.data.Room;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;


public class DbOjectify<T extends BaseDataClass> implements IDB<T> {
 
	//static DbOjectify<?> inst ;//= new  DbOjectify<?>();
	

//	
//	
//	public static DbOjectify<T> getInstance()
//	{
//		//ofy();
//		inst = new DbOjectify<T>();
//		return inst;
//	}

	

	//@Override
	public T getObjectById(Long id) {
	
		return null; //???pws 8a dwsw ton typo?
		
//		Result<T> result = (Result<T>) ofy().load().key(Key.create(????,id));  // Result is async
//		T fetched1 = result.now();    // Materialize the async value
//		
//		return fetched1;
	}

	


	//@Override
	public T getObject(T o) {
		
		ofy().save().entity(o).now();
		
		if (o.id == null){
			 LogError("id is null. Type " + o.getClass());
			 return null;
		}
		
		@SuppressWarnings("unchecked")
		Result<T> result = (Result<T>) ofy().load().key(Key.create(o.getClass(),o.id));  // Result is async
		
		T fetched1 = result.now();
		return fetched1;
	}

	private void LogError(String err) {
		System.out.println("ERROR: "+err);
		
	}

	@Override
	public T createObject(T o) {
		
		o.setDateInsert(new Date());
		ofy().save().entity(o).now();
				
		return o;
	}

	@Override
	public T updateObject(T o) {

		o.setDateUpdat(new Date());
		ofy().save().entity(o).now();
		return o;
	}

	@Override
	public void deleteObject(T o) {
		ofy().delete().entity(o).now();
		
	}

	



	
	
}
