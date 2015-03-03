package gr.sperfect.djuqbox.webapp.server.db;

import gr.sperfect.djuqbox.webapp.shared.data.BaseDataClass;

import java.util.List;


public interface IDB<T extends BaseDataClass> {

	Class<T> getGenType();
	
	T createObject(T r);

	T getObject(T o);

	T getObjectById(Long id);

	T updateObject(T r);

	void deleteObject(T r);

	List<T> getAllObjects(String filters);

	T findObjectWithValue(String aField, Object aValue);

}
