package com.kisti.edison.SuperMan.DataExtractor;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EDISON_DB_Connector {
	private static final String DBMS_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";

	private static String strUserName = "root";

	private static String strPassword = "dpel+*db";

	private static String strConnectString = "jdbc:mysql://150.183.247.20/";

	private static String strDatabaseName = "edison2_release";

	private static Connection _connection = null;

	private static Statement _statement = null;

	/**
	 * Closes the DBMS connection that was opened by the open call.
	 */
	public static void close() {
		try {
			if (_connection != null)
				_connection.commit();
			if (_statement != null)
				_statement.close();
			if (_connection != null)
				_connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		_connection = null;
	}

	/**
	 * Commits all update operations made to the dbms. This must be called for
	 * inserts statements to be seen.
	 */
	public static void commit() {
		try {
			if (_connection != null && !_connection.isClosed())
				_connection.commit();
		} catch (SQLException e) {
			System.err.println("Commit failed");
			e.printStackTrace();
		}
	}

	/**
	 * Opens the connection to the DBMS.
	 * 
	 * @throws DBMSInvalidConnectionParameterException
	 */
	public static boolean open(boolean auto_commit) {
		boolean res = false;
		try {
			Class.forName(DBMS_DRIVER_CLASS_NAME);
			_connection = DriverManager.getConnection(strConnectString+strDatabaseName,
					strUserName, strPassword);
			// turn off auto-commit. If this is turned on there will be a
			// huge performance hit for inserting tuples
			// into the DBMS.
			_connection.setAutoCommit(auto_commit);
			_statement = _connection.createStatement(
					ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
			res = true;
		} catch (SQLException sqlex) {
			System.err.println("login details: " + strConnectString + ", "
					+ strUserName + ", " + strPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(1); // programemer/dbsm error
		}
		return res;
	}

	public static ResultSet executeQuery(String query){
		 ResultSet rs = null;
		 try {
			rs = _statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return rs;
	}
}
