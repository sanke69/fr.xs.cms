package fr.xs.cms.services.guest;

import java.sql.ResultSet;
import java.sql.SQLException;

import fr.xs.cms.services.guest.beans.ClientBean;
import fr.xs.cms.services.guest.beans.ProfileBean;
import fr.xs.cms.services.guest.beans.UserBean;
import fr.xs.cms.services.guest.beans.VisitorBean;
import fr.xs.jtk.databases.DatabaseConnector;
import fr.xs.jtk.databases.beans.DatabaseAccountBean;
import fr.xs.jtk.helpers.TimeHelper;
import fr.xs.jtk.tools.HashTag;

public class GuestDatabase {
	private static final String guestDatabase = "guest_spweb";
	private static final String visitorTable  = "visitor";
	private static final String clientTable   = "client";
	private static final String userTable     = "user";
	private static final String profileTable  = "profile";

	private static DatabaseConnector conn     = null;

	public static boolean initialize(DatabaseAccountBean _user) {
		if(conn == null) {
			conn = new DatabaseConnector();
			if( !conn.connectTo(_user) )
				System.err.println("Failed to connect to mySQL server");
			return true;
		}
		return false;
	}
	public static boolean isConnected() {
		return conn != null;
	}

	public static VisitorBean getVisitor(long _id) {
		String query = "SELECT " + "hashtag, client_uuid, ipv4, ipv6, count, first, last" 
					 + " FROM  " + guestDatabase + "." + visitorTable
					 + " WHERE " + "uuid='" + _id + "';";

		if(conn == null) {
			System.out.println(query);
			return null;
		}

		ResultSet result = conn.query(query);

		VisitorBean bean = null;
		try {
//			if(result != null && result.next()) {
			if(result != null && result.next()) {
				bean = new VisitorBean();
				bean.setId(_id)
//					.setHashtag(new Hashtag(result.getLong(1)))
					.setClientId(result.getLong(2))
					.setIPv4(result.getString(3))
					.setIPv6(result.getString(4))
					.setVisitCount(result.getInt(5))
					.setFirstVisit(TimeHelper.fromSQL(result.getTimestamp(6)))
					.setLastVisit(TimeHelper.fromSQL(result.getTimestamp(7)));
			}
		} catch(SQLException e) {
			bean = null;
		}

		return bean;
	}
	public static VisitorBean getVisitor(HashTag _hashtag) {
		String attr   = "uuid, client_uuid, ipv4, ipv6, count, first, last";
		String table  = guestDatabase + "." + visitorTable;
		String clause = "hashtag='" + _hashtag.toString() + "'";

		String query  = "SELECT " + attr + " FROM " + table +  " WHERE " + clause + ";";

		if(conn == null) {
			System.out.println(query);
			return null;
		}

		ResultSet result = conn.query(query);

		VisitorBean bean = null;
		try {
			if(result != null && result.next()) {
				bean = new VisitorBean();
				bean.setId(result.getLong(1))
					.setHashtag(_hashtag)
					.setClientId(result.getLong(2))
					.setIPv4(result.getString(3))
					.setIPv6(result.getString(4))
					.setVisitCount(result.getInt(5))
					.setFirstVisit(TimeHelper.fromSQL(result.getTimestamp(6)))
					.setLastVisit(TimeHelper.fromSQL(result.getTimestamp(7)));
			}
		} catch(SQLException e) {
			bean = null;
		}

		return bean;
	}
	public static boolean insertVisitor(VisitorBean _bean) {
		String table  = guestDatabase + "." + visitorTable;
		String attr   = "hashtag, client_uuid, ipv4, ipv6, count, first, last";
		String value  = "'" + _bean.getHashtag() + "', '" + _bean.getClientId() + "', '" + _bean.getIPv4() + "', '" + _bean.getIPv6() + "', '" + _bean.getVisitCount() + "', '" + TimeHelper.toSQL(_bean.getFirstVisit()) + "', '" + TimeHelper.toSQL(_bean.getLastVisit()) + "'";

		String request = "INSERT INTO " + table + " (" + attr + ") VALUES (" + value + ");";
	
		if(conn == null) {
			System.out.println(request);
			return false;
		}

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}
	public static boolean updateVisitor(VisitorBean _bean) {
		String request = "UPDATE " + guestDatabase + "." + visitorTable
					   + " SET " + 
					   		"count='" + _bean.getVisitCount() + "', " +
					   		"last='"  + TimeHelper.toSQL(_bean.getLastVisit()) + "'"
					   + " WHERE " + "hashtag='"  + _bean.getHashtag() + "';"; 

		if(conn == null) {
			System.out.println(request);
			return false;
		}

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}

	public static long isClientExist(ClientBean _bean) {
		if(_bean == null)
			return -1;

		String query = "SELECT " + "uuid" 
					 + " FROM " + guestDatabase + "." + clientTable
					 + " WHERE " + 
					 	"platform='" + _bean.getPlatform() + "'" + " AND " +
					 	"name='" + _bean.getName() + "'" + " AND " +
					 	"codename='" + _bean.getCodeName() + "'" + " AND " +
					 	"version='" + _bean.getVersion() + "'" + " AND " +
					 	"useragent='" + _bean.getUserAgent() + "';";

		if(conn == null) {
			System.out.println(query);
			return -1;
		}

		ResultSet result = conn.query(query);

		try {
			if(result != null && result.next())
				return result.getInt(1);
		} catch(SQLException e) {}

		return -1;
	}
	public static ClientBean  getClient(long _uuid) {
		String query = "SELECT " + "platform, name, codename, version, useragent" 
					 + " FROM " + guestDatabase + "." + clientTable
					 + " WHERE " + "uuid='" + _uuid + "';";

		if(conn == null) {
			System.out.println(query);
			return null;
		}

		ResultSet result = conn.query(query);

		ClientBean bean = null;
		try {
			if(result != null && result.next()) {
				bean = new ClientBean();
				bean.setId(_uuid)
					.setPlatform(result.getString(1))
					.setName(result.getString(2))
					.setCodeName(result.getString(3))
					.setVersion(result.getString(4))
					.setUserAgent(result.getString(5));
			}
		} catch(SQLException e) {
			bean = null;
		}

		return bean;
	}
	public static boolean insertClient(ClientBean _bean) {
		String request = "INSERT INTO " + guestDatabase + "." + clientTable + "(platform, name, codename, version, useragent)"
					   + " VALUES ('" + _bean.getPlatform() + "', '" + _bean.getName() + "', '" + _bean.getCodeName() + "', '" + _bean.getVersion() + "', '" + _bean.getUserAgent() + "');"; 

		if(conn == null) {
			System.out.println(request);
			return false;
		}

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}
	@Deprecated
	public static boolean updateClient(ClientBean _bean) {
		/*
		String request = "UPDATE " + visitorTable
					   + "SET " + 
					   		"count=" + _bean.getVisitCount() + ", " +
					   		"last="  + _bean.getLastVisit()
					   + "WHERE " + "hashtag="  + _bean.getHashtag() + ";"; 

		if(conn == null) {
			System.out.println(request);
			return false;
		}

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
		*/
		return false;
	}
	
	public static UserBean    getUser(long _uuid) {
		String query = "SELECT " + "login, password, email, hash_validation, date_inscription, last_connection" 
					 + " FROM " + guestDatabase + "." + userTable
					 + " WHERE " + "uuid='" + _uuid + "';";

		if(conn == null) {
			System.out.println(query);
			return null;
		}

		ResultSet result = conn.query(query);

		UserBean bean = null;
		try {
			if(result != null && result.next()) {
				bean = new UserBean();
				bean.setId(_uuid)
					.setLogin(result.getString(1))
					.setPassword(result.getString(2))
					.setEmail(result.getString(3))
					.setHashValidation(result.getString(4))
					.setInscription(result.getDate(5).toInstant())
					.setLastConnection(result.getDate(6).toInstant());
			}
		} catch(SQLException e) {
			bean = null;
		}

		return bean;
	}
	public static boolean insertUser(UserBean _bean) {
		String request = "INSERT INTO " + guestDatabase + "." + userTable + "(login, password, email, hash_validation, date_inscription, last_connection)"
					   + " VALUES ('" + _bean.getLogin() + "', '" + _bean.getPassword() + "', '" + _bean.getEmail() + "', '" + _bean.getHashValidation() + "', '" + _bean.getInscription() + "', '" + _bean.getLastConnection() + "');"; 

		if(conn == null) {
			System.out.println(request);
			return false;
		}

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}
	public static boolean updateUser(UserBean _bean) {
		String request = "UPDATE " + guestDatabase + "." + userTable
					   + " SET " + 
					   		"password=" + _bean.getPassword() + ", " +
					   		"email=" + _bean.getEmail() + ", " +
					   		"hash_validation=" + _bean.getHashValidation() + ", " +
					   		"last_connection=" + _bean.getLastConnection()
					   + " WHERE " + "uuid="  + _bean.getId() + ";"; 

		if(conn == null) {
			System.out.println(request);
			return false;
		}

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}

	public static ProfileBean getProfile(long _uuid) {
		String query = "SELECT " + "fullname, nickname, param3, param4, param5" 
					 + " FROM " + guestDatabase + "." + profileTable
					 + " WHERE " + "uuid='" + _uuid + "';";

		if(conn == null) {
			System.out.println(query);
			return null;
		}

		ResultSet result = conn.query(query);

		ProfileBean bean = null;
		try {
			if(result != null && result.next()) {
				bean = new ProfileBean();
				bean.setId(_uuid)
					.setFullName(result.getString(1))
					.setNickName(result.getString(2))
					.setParam3(result.getString(3))
					.setParam4(result.getString(4))
					.setParam5(result.getString(5));
			}
		} catch(SQLException e) {
			bean = null;
		}

		return bean;
	}
	public static boolean insertProfile(ProfileBean _bean) {
		String request = "INSERT INTO " + guestDatabase + "." + profileTable + "(fullname, nickname, param3, param4, param5)"
					   + " VALUES ('" + _bean.getFullName() + "', '" + _bean.getNickName() + "', '" + _bean.getParam3() + "', '" + _bean.getParam4() + "', '" + _bean.getParam5() + "', '" + "');"; 

		if(conn == null) {
			System.out.println(request);
			return false;
		}

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}
	public static boolean updateProfile(ProfileBean _bean) {
		String request = "UPDATE " + guestDatabase + "." + profileTable
					   + " SET " + 
					   		"fullname=" + _bean.getFullName() + ", " +
					   		"nickname=" + _bean.getNickName() + ", " +
					   		"param3=" + _bean.getParam3() + ", " +
					   		"param4=" + _bean.getParam4() + ", " +
					   		"param5=" + _bean.getParam5()
					   + " WHERE " + "uuid="  + _bean.getId() + ";"; 

		if(conn == null) {
			System.out.println(request);
			return false;
		}

		try {
			return conn.update(request);
		} catch(Exception e) {
			return false;
		}
	}

}
