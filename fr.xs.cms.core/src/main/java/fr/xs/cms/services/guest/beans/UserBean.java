package fr.xs.cms.services.guest.beans;

import java.io.Serializable;
import java.time.Instant;

public class UserBean implements Serializable {
	private static final long serialVersionUID = 1938669966699566139L;

	private long  	id;
	private String  login;
	private String  password;
	private String  email;
	private String  hash_validation;
	private Instant date_inscription;
	private Instant last_connection;
	
	public UserBean() {
		super();
		id = -1;
		login = null;
		password = null;
		email = null;
		hash_validation = null;
		date_inscription = null;
		last_connection = null;
	}

	public long getId() { return id; }
	public UserBean setId(long _id) { id = _id; return this; }

	public String getLogin() { return login; }
	public UserBean setLogin(String _name) { login = _name; return this; }

	public String getPassword() { return password; }
	public UserBean setPassword(String _password) { password = _password; return this; }

	public String getEmail() { return email; }
	public UserBean setEmail(String _email) { email = _email; return this; }

	public String getHashValidation() { return hash_validation; }
	public UserBean setHashValidation(String _hash_validation) { hash_validation = _hash_validation; return this; }

	public Instant getInscription() { return date_inscription; }
	public UserBean setInscription(Instant _date_inscription) { date_inscription = _date_inscription; return this; }

	public Instant getLastConnection() { return last_connection; }
	public UserBean setLastConnection(Instant _last_connection) { last_connection = _last_connection; return this; }

}
