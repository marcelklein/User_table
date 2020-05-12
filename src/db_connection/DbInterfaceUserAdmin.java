package db_connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import db_connection.model.UserStruct;

public class DbInterfaceUserAdmin {

	static Connection userAdminDb = null;

	public DbInterfaceUserAdmin() {

		try {
			// here user_admin is database name, root is username and password
			Class.forName("com.mysql.jdbc.Driver");
			if (userAdminDb == null) {
				userAdminDb = DriverManager.getConnection("jdbc:mysql://localhost:3306/user_admin", "root",
						"05722285878l");
				System.out.println("Databaseconnection opened to user_admin!");
			}
		} catch (Exception e) {
			System.out.println("Databaseconnection failed!");
			e.printStackTrace();
		}
	}

	public void initUserAdmin() {
		Statement stmt = null;
		
		try {
			String table = "user_Base";
			List<String> columns = new Vector();
			stmt = userAdminDb.createStatement();
			String sql = "describe " + table;
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				columns.add(rs.getString(1));
			}
			System.out.println("table: " + table + "; columns: " + columns);

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public UserStruct getUserByUserId(int userId) {
		UserStruct user = new UserStruct();
		Statement stmt = null;
		
		try {

			stmt = userAdminDb.createStatement();
			String sql = "SELECT * FROM user_admin.user_base where user_id = " + userId;
			System.out.println(sql);
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				user.userId = resultSet.getInt(1);
				user.surname = resultSet.getString(2);
				user.firstname = resultSet.getString(3);
				user.streetAddress = resultSet.getString(4);
				user.city = resultSet.getString(5);
				user.zipCode = resultSet.getInt(6);
			}

		} catch (Exception e) {
			System.out.println(e);
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return user;
	}

	public Vector<UserStruct> getAllUser() {
		Vector<UserStruct> allUser = new Vector<UserStruct>();
		Statement stmt = null;
		
		try {
			stmt = userAdminDb.createStatement();
			String sql = "SELECT * FROM user_admin.user_base";
			System.out.println(sql);
			ResultSet resultSet = stmt.executeQuery(sql);
			while (resultSet.next()) {
				UserStruct user = new UserStruct();
				user.userId = resultSet.getInt(1);
				user.surname = resultSet.getString(2);
				user.firstname = resultSet.getString(3);
				user.streetAddress = resultSet.getString(4);
				user.city = resultSet.getString(5);
				user.zipCode = resultSet.getInt(6);
				allUser.add(user);
			}
		} catch (Exception e) {
			System.out.println(e);
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return allUser;
	}
	
	public void deleteUserFromDb(int userId) {

		Statement stmt = null;
		
		try {
			String sql = "DELETE FROM `user_admin`.`user_base` WHERE (`user_id` = '" + userId + "')";
			stmt = userAdminDb.createStatement();
			System.out.println(sql);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	public void saveUserToDb(UserStruct newUser) {

		Statement stmt = null;
		System.out.println(newUser);

		try {
			
			String sql = "INSERT INTO `user_admin`.`user_base` "
					+ "(`user_id`, `surname`, `firstname`, `street`, `city`, `zip_code`) " + "VALUES (" + getUserIdPk()
					+ ", '" + newUser.surname + "', '" + newUser.firstname + "', '" + newUser.streetAddress + "', '"
					+ newUser.city + "', " + newUser.zipCode + ")";
			System.out.println(sql);
			stmt = userAdminDb.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private int getUserIdPk() {

		int newUserId = -1;
		Statement stmt = null;
		try {
			stmt = userAdminDb.createStatement();
			String sql = "select max(user_id) from user_admin.user_base";
			System.out.println(sql);

			ResultSet result = stmt.executeQuery(sql);
			result.next();

			newUserId = result.getInt(1);
			newUserId++;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (stmt != null) {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

		return newUserId; // return newUserId to sql stmt above as userId as callback
	}



}
