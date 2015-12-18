package fr.xs.cms.services.guest.beans;

import java.io.Serializable;

public class ProfileBean implements Serializable {
	private static final long serialVersionUID = 1938669966699566139L;

	private long   id;
	private String fullname;
	private String nickname;
	private String param3;
	private String param4;
	private String param5;
	
	public ProfileBean() {
		super();
		id = -1;
		fullname = null;
		nickname = null;
		param3 = null;
		param4 = null;
		param5 = null;
	}

	public long getId() { return id; }
	public ProfileBean setId(long _id) { id = _id; return this; }

	public String getFullName() { return fullname; }
	public ProfileBean setFullName(String _fullname) { fullname = _fullname; return this; }

	public String getNickName() { return nickname; }
	public ProfileBean setNickName(String _nickname) { nickname = _nickname; return this; }

	public String getParam3() { return param3; }
	public ProfileBean setParam3(String _param3) { param3 = _param3; return this; }

	public String getParam4() { return param4; }
	public ProfileBean setParam4(String _param4) { param4 = _param4; return this; }

	public String getParam5() { return param5; }
	public ProfileBean setParam5(String _param5) { param5 = _param5; return this; }

}
