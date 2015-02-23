package gr.sperfect.djuqbox.webapp.server.db;

import gr.sperfect.djuqbox.webapp.shared.data.BaseDataClass;
import gr.sperfect.djuqbox.webapp.shared.data.Room;

public interface IDB/*<T>*/ {

	
	Room CreateRoom(Room r);
	
	Room GetRoom(Long id);

	Room UpdateRoom(Room r);	

	void DeleteRoom(Room r);

	BaseDataClass GetObject(BaseDataClass o);
	
	//<T> BaseDataClass  GetObjectGeneric(T o);

//	T GetObjectGeneric(T o);
 

}
