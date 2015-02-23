package gr.sperfect.djuqbox.webapp.server.db.objectify;

import static gr.sperfect.djuqbox.webapp.server.db.objectify.OfyService.ofy;
import gr.sperfect.djuqbox.webapp.server.db.IDB;
import gr.sperfect.djuqbox.webapp.shared.data.BaseDataClass;
import gr.sperfect.djuqbox.webapp.shared.data.Room;

import java.util.Date;

import com.googlecode.objectify.Key;
import com.googlecode.objectify.Result;


public class DbOjectify/*<T extends BaseDataClass>*/ implements IDB {
 
	static DbOjectify inst = new  DbOjectify();
	//static Ofy()
	
	public static DbOjectify getInstance()
	{
		ofy();
		return inst;
	}

	@Override
	public Room CreateRoom(Room r) {
		
		r.setDateInsert(new Date());
		ofy().save().entity(r).now();
				
		return r;
	}

	@Override
	public Room GetRoom(Long id) {
	
		Result<Room> result = ofy().load().key(Key.create(Room.class,id));  // Result is async
		Room fetched1 = result.now();    // Materialize the async value
		
		return fetched1;
	}

	@Override
	public Room UpdateRoom(Room r) {
	
		r.setDateUpdat(new Date());
		ofy().save().entity(r).now();
		return r;
	}

	@Override
	public void DeleteRoom(Room r) {

		ofy().delete().entity(r).now();
	}
	
	public BaseDataClass GetObject(BaseDataClass o) {
		
		Result<BaseDataClass> result = ofy().load().key(Key.create(o.getClass(),o.id));  // Result is async
		BaseDataClass fetched1 = result.now();    // Materialize the async value
		
		return fetched1; 
	}

//	@Override
//	public Object GetObjectGeneric(Object o) {
//		// TODO Auto-generated method stub
//		return null;
//	}


	
//	public T GetObjectGeneric(T o) {
//		
//		Result<BaseDataClass> result = ofy().load().key(Key.create(o.getClass(),o.id));  // Result is async
//		BaseDataClass fetched1 = result.now();    // Materialize the async value
//		
//		return (T) fetched1; 
//	}

//	@Override
//	public T GetObjectGeneric(T o) {
//		
//		Result<BaseDataClass> result = ofy().load().key(Key.create(o.getClass(),o.id));  // Result is async
//		BaseDataClass fetched1 = result.now();    // Materialize the async value
//		
//		return (BaseDataClass) fetched1; 
//	}

	
	
}
