package gr.sperfect.djuqbox.webapp.server.db;

import gr.sperfect.djuqbox.webapp.shared.data.BaseDataClass;

public interface IDB<T extends BaseDataClass> {

	T createObject(T r);

	T getObject(T o);

	T getObjectById(Long id);

	T updateObject(T r);

	void deleteObject(T r);

}
