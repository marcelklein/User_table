package db_connection;

import db_connection.DbAccess;

public class Main {

	public static void main(String[] args) {
		
		boolean test2 = true;
		
		if(test2) {
			DbAccess testDb = new DbAccess();
			testDb.runTest();
		}
	}

}
