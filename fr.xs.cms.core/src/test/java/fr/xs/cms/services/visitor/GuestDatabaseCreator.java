package fr.xs.cms.services.visitor;

import fr.xs.cms.services.guest.GuestDatabase;
import fr.xs.jtk.databases.DatabaseManager;
import fr.xs.jtk.databases.beans.DatabaseAccountBean;
import fr.xs.jtk.databases.beans.DatabaseBean;
import fr.xs.jtk.helpers.MediaHelper;

public class GuestDatabaseCreator {
	
	public static void main(String[] args) {
		DatabaseAccountBean      db_root  = new DatabaseAccountBean("IP_or_Domain", "root", "rootPswd");
		DatabaseAccountBean      db_user  = new DatabaseAccountBean("IP_or_Domain", "username", "userPswd");
		DatabaseBean             database = DatabaseManager.loadModelsFromJSON(
											MediaHelper.getContent(GuestDatabaseCreator.class, "json/databases/guest_db.json")).iterator().next();

		DatabaseManager.create(db_root, db_user, database);
		
		
		GuestDatabase.initialize(db_user);
/*
		VisitorBean visitor = GuestDatabase.getVisitor(new HashTag("4202936f47000"));
		ClientBean  client  = GuestDatabase.getClient(visitor != null ? visitor.getClientId() : 0);
		UserBean    user    = GuestDatabase.getUser(0);
		ProfileBean profile = GuestDatabase.getProfile(0);
/*
		System.out.println(visitor);
//		System.out.println(client);
		System.out.println(GuestDatabase.isClientExist(client));
/*
		visitor.setVisitCount(visitor.getVisitCount() + 1);
		visitor.setLastVisit(Instant.now());
		GuestDatabase.updateVisitor(visitor);

		visitor = GuestDatabase.getVisitor(visitor.getHashtag());
		System.out.println(visitor);
		
		
		/*
		ClientBean add = new ClientBean();
		add.setPlatform("Linux x86_64")
		.setName("Netscape")
		.setCodeName("Mozilla")
		.setVersion("5.0 (X11)")
		.setUserAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:39.0) Gecko/20100101 Firefox/39.0");
		
		GuestDatabase.insertClient(add);
		long id = GuestDatabase.isClientExist(add);
		
		System.out.println(id < 0 ? "OK":"NOK");
		*/
		
		
		
		
		/*
		if(visitor == null) {
			visitor = new VisitorBean();
			if(visitor.getId() == -1) {
				visitor .setId(1)
						.setClientId(0)
						.setFirstVisit(Instant.now())
						.setLastVisit(Instant.now())
						.setVisitCount(0)
						.setHashtag(new HashTag("fff"))
						.setIPv4("1.2.3.3");
				access.insertVisitor(visitor);
			} else
				System.out.println(visitor.toString());
		} else
			System.out.println(visitor.toString());

		if(client == null) {
			client = new ClientBean();
			if(client.getId() == -1) {
				client  .setId(0)
						.setPlatform("Linux x86_64")
						.setName("Netscape")
						.setCodeName("Mozilla")
						.setVersion("5.0 (X11)")
						.setUserAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:39.0) Gecko/20100101 Firefox/39.0");
				access.insertClient(client);
			} else
				System.out.println(client.toString());
		} else
			System.out.println(client.toString());
		*/
	}

}
