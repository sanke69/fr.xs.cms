package fr.xs.cms;

import java.util.List;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;

import fr.xs.cms.core.mail.MailBoxManager;
import fr.xs.cms.core.mail.MailBoxManager.Type;

public class MailBoxManagerTest {

	public static void main(String[] _argv) {
		MailBoxManager mailbox = new MailBoxManager();
	
		mailbox.openSession(Type.IMAP, "IMAP_Server", "username", "password");
		
		List<Folder>  l = mailbox.getBoxFolders();
		List<Message> msg = null;
	
		for(Folder f : l) {
			try {
				System.out.println(" -->  " + String.format("%1$"+32+ "s", f.getName()) + "\t\tTotal=" + f.getMessageCount() + "\tUnread=" + f.getNewMessageCount());
	
				if(f.getName().compareToIgnoreCase("INBOX") == 0) {
					msg = mailbox.getBoxFolderMessages(f);
					for(Message m : msg)
						System.out.println(mailbox.getMessageContent(m));
				}
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
		
		mailbox.closeSession();
	
	}
	
}

