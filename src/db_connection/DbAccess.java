package db_connection;

import db_connection.DbInterfaceUserAdmin;

public class DbAccess {
	
	private DbInterfaceUserAdmin userAdminDb = new DbInterfaceUserAdmin();
	
	public void runTest() {
		userAdminDb.initUserAdmin();
	}
}

