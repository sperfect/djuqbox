package gr.sperfect.djuqbox.webapp.server.db;

import gr.sperfect.djuqbox.webapp.server.db.objectify.DbOjectify;
import gr.sperfect.djuqbox.webapp.shared.data.BaseDataClass;

public class DBHelper<T extends BaseDataClass> {

	

	public static <T extends BaseDataClass> IDB<T> getDB(Class<T> type) {

		// edw h parametropoihsh ths db
		//if..
		//
		DbOjectify<T> db = new DbOjectify<T>();
		
		
		return db;
	}

}
