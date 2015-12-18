package fr.xs.cms.services.guest.beans;

import java.io.Serializable;
import java.time.Instant;

import fr.xs.jtk.helpers.TimeHelper;
import fr.xs.jtk.tools.HashTag;

public class VisitorBean implements Serializable {
	private static final long serialVersionUID = 1938681039899566139L;

	// Status
	private boolean 				valid;

	private long	 				id, client_id;
	private HashTag  				hashtag;
	private String  				IPv4, IPv6;
	private int     				visit_count;
	private Instant                 first, last;

	public VisitorBean() {
		super();
		valid = false;
		id = client_id = -1;

		hashtag = null;
		IPv4 = null;
		IPv6 = null;

		visit_count = 0;
		first = null;
		last = null;
	}
	
	public boolean isValid() { return valid; }
	public VisitorBean setValid(boolean _valid) { valid = _valid; return this; }

	public long getId() { return id; }
	public VisitorBean setId(long _id) { id = _id; return this; }

	public HashTag getHashtag() { return hashtag; }
	public VisitorBean setHashtag(HashTag _hashtag) { hashtag = _hashtag; return this; }

	public long getClientId() { return client_id; }
	public VisitorBean setClientId(long _client_id) { client_id = _client_id; return this; }

	public String getIPv4() { return IPv4; }
	public VisitorBean setIPv4(String _ip) { IPv4 = _ip; return this; }

	public String getIPv6() { return IPv6; }
	public VisitorBean setIPv6(String _ip) { IPv6 = _ip; return this; }

	public String getIP() { return (IPv4 != null && IPv4.compareToIgnoreCase("IP") != 0) ? IPv4 : IPv6 != null ? IPv6 : "undef"; }

	public int getVisitCount() { return visit_count; }
	public VisitorBean setVisitCount(int _visit_count) { visit_count = _visit_count; return this; }

	public Instant getFirstVisit() { return first; }
	public VisitorBean setFirstVisit(Instant _first) { first = _first; return this; }

	public Instant getLastVisit() { return last; }
	public VisitorBean setLastVisit(Instant _last) { last = _last; return this; }

	@Override
	public String toString() {
		return  "<<Visitor>>" +
				getHashtag() + "|" + getIP() + "(" + getIPv4() + "|" + getIPv6() + ") | " + 
				TimeHelper.format(getFirstVisit(), TimeHelper.sqlFormat) + ", " +
				TimeHelper.format(getLastVisit(), TimeHelper.sqlFormat) + ", " +
				"<</Visitor>>";
	}
	public String toConstantString() {
		return  "<<Visitor>>" +
				getHashtag() + "|" + getIP() + "(" + getIPv4() + "|" + getIPv6() + ")" +
				"<</Visitor>>";
	}

}
