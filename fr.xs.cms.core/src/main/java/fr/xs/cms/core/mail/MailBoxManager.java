package fr.xs.cms.core.mail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.URLName;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;
import javax.mail.internet.MimeMultipart;

// http://atatorus.developpez.com/tutoriels/java/envoyer-recevoir-des-mails-avec-javamail/

public class MailBoxManager {

	public enum Type { SMTP, POP3, IMAP, IMAPS };

	Session current;

	Type   type;
	String server, user, email_account, email_password;
	
	Store  store = null;
	Folder currentFolder = null;

	public MailBoxManager() {

	}

	/**
	 * SESSION MANAGEMENT
	 */
	public boolean openSMTPSession(String _server, String _user, String _email_account, String _email_password, boolean _starttls) {
		Properties properties = new Properties();
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.host", _server);
		properties.setProperty("mail.smtp.user", _user);
		properties.setProperty("mail.from", _email_account);

		if(_starttls)
			properties.setProperty("mail.smtp.starttls.enable", "true");

		current = Session.getInstance(properties);
		type    = Type.SMTP;
		return current != null;
	}
	public boolean openSession(Type _type, String _server, String _email_account, String _email_password) {
		if(current != null)
			System.err.println("A session is already opened");

		Properties properties = new Properties();

		switch(_type) {
		case POP3: 	properties.setProperty("mail.store.protocol", "pop3");
					properties.setProperty("mail.pop3.host", _server);
					properties.setProperty("mail.pop3.user", _email_account);
					break;
		case IMAP:	properties.setProperty("mail.store.protocol", "imaps");
					break;
		case IMAPS: properties.setProperty("mail.store.protocol", "imaps");
					break;
		default:	break;
		}

		current        = Session.getInstance(properties);
		type           = _type;
		server         = _server;
		email_account  = _email_account;
		email_password = _email_password;

		try {
			switch(type) {
			case POP3: 	store = current.getStore(new URLName("pop3://" + server));
						store.connect(email_account, email_password);
						break;
			case IMAP:	store = current.getStore("imaps");
	          			store.connect(server, email_account, email_password);
						break;
			case IMAPS:
						break;
			default:	break;
			}
			currentFolder = store.getDefaultFolder();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return current != null;
	}
	public boolean closeSession() {
		if(current == null)
			return false;

		if(currentFolder != null)
			try {
				currentFolder.close(false);  // false -> On ne met pas a jour, ie on n'efface pas les messages marqués DELETED
			} catch (MessagingException e) {
				e.printStackTrace();
			}

		if(store != null)
			try {
				store.close();
			} catch (MessagingException e) {
				e.printStackTrace();
			}

		current = null;
		return true;
	}

	/**
	 * READING MANAGEMENT
	 */
	public List<Folder>  getBoxFolders() {
		if(current == null || store == null || currentFolder == null)
			return null;
		
		List<Folder> list = new ArrayList<Folder>();

		try {
			currentFolder = store.getDefaultFolder();

			list.add(currentFolder);
			for(Folder folder : currentFolder.list())
				list.add(folder);

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}

	public List<Message> getBoxFolderMessages(Folder _folder) {
		List<Message> list = new ArrayList<Message>();

		try {
			_folder.open(Folder.READ_ONLY);

			int count = _folder.getMessageCount();
			for(int i = 1; i <= count; i++) 
				list.add( _folder.getMessage(i) );

		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

    public static List<String> inputStreamToStrings(final InputStream stream) {
        List<String> list = new ArrayList<String>();

    	try {
	        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
	        String line = null;
	        while ((line = br.readLine()) != null) {
	            list.add(line);
	        }
	        br.close();
    	} catch(IOException e) {
    		e.printStackTrace();
    	}

        return list;
    }

    public List<String> getMessageContent(final Message _msg) {
        List<String> list = new ArrayList<String>();

    	try {
    		InputStream stream = _msg.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(stream));
	        String line = null;
	        while ((line = br.readLine()) != null) {
	            list.add(line);
	        }
	        br.close();
    	} catch(IOException e) {
    		e.printStackTrace();
    	} catch (MessagingException e) {
			e.printStackTrace();
		}

        return list;
    }

    private void processBodyPart(BodyPart bp) { 
        try { 
            System.out.println("Type : " + bp.getContentType()); 
            String fileName = bp.getFileName(); 
            System.out.println("FileName : " + fileName); 

            if (bp.isMimeType("text/plain")) { 
                System.out.println("Texte du message :"); 
                List<String> strings = inputStreamToStrings(bp.getInputStream()); 
                for (String string : strings) { 
                    System.out.println(string); 
                } 

            } else { 
            	DataHandler dh = bp.getDataHandler();

            	
                File file = new File("/tmp/" + "/received_" + fileName); 
                FileOutputStream fos = new FileOutputStream(file); 

                //bp.writeTo(fos);
                dh.writeTo(fos);		//=> Base64
            } 

        } catch (MessagingException | IOException e) { 
            e.printStackTrace(); 
        } 

    } 
	
    public void processAttachments(Message message) {
    	/*
    	if (message.isMimeType("multipart/mixed")) {
    	    printMixedMessage(message);
    	} else {
    	    printTextMessage(message);
    	}
*/
        try { 
            DataSource dataSource = message.getDataHandler().getDataSource(); 
            MimeMultipart mimeMultipart = new MimeMultipart(dataSource); 
            int multiPartCount = mimeMultipart.getCount(); 

            System.out.println("Il y a " + multiPartCount + " partie(s) dans ce message."); 

            for (int i = 0; i < multiPartCount; i++ ) { 
                BodyPart bp = mimeMultipart.getBodyPart(i); 
                processBodyPart(bp); 
            } 

        } catch (MessagingException e) { 
            e.printStackTrace(); 

        } 

    }
   
	public void printMessage(Message _msg, boolean _header_only) {
		Address[] addresses = null;
		try {
			System.out.println("Sujet : " + _msg.getSubject());
			System.out.println("Exp�diteur : ");
				addresses = _msg.getFrom();
				for(Address address : _msg.getFrom())
					System.out.println("\t" + address);
	
			System.out.println("Destinataires : ");
				addresses = _msg.getRecipients(RecipientType.TO);
				if(addresses != null)
					for(Address address : addresses)
						System.out.println("\tTo : " + address);
	
				addresses = _msg.getRecipients(RecipientType.CC);
				if(addresses != null)
					for(Address address : addresses)
						System.out.println("\tCc : " + address);

			if(!_header_only) {
				System.out.println("Content : ");
					for(String line : inputStreamToStrings(_msg.getInputStream()))
						System.out.println(line);
					
				processAttachments(_msg);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * WRITING MANAGEMENT
	 */
	public MimeBodyPart createAttachedFile(String _path) {
		File           file        = new File(_path);
		FileDataSource datasource1 = new FileDataSource(file);
		DataHandler    handler1    = new DataHandler(datasource1);
		MimeBodyPart   mime        = new MimeBodyPart();

		try {
			mime.setDataHandler(handler1);
			mime.setFileName(datasource1.getName());

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return mime;
	}

	public MimeBodyPart createAttachedText(String _text) {
		MimeBodyPart mime = new MimeBodyPart();

		try {
			mime.setContent(_text, "text/plain");

		} catch (MessagingException e) {
			e.printStackTrace();
		}

		return mime;
	}

	public void sendMessage(String subject, String text, List<MimeBodyPart> _attached, String destinataire, String copyDest) {
		MimeMessage   message       = new MimeMessage(current);
		MimeMultipart mimeMultipart = null;

		if (_attached != null && _attached.size() > 0) {
			mimeMultipart = new MimeMultipart();

			try {
				for (MimeBodyPart mime : _attached)
					mimeMultipart.addBodyPart(mime);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}

		try {
			if (mimeMultipart != null)
				message.setContent(mimeMultipart);

			message.setText(text);
			message.setSubject(subject);
			message.addRecipients(Message.RecipientType.TO, destinataire);
			message.addRecipients(Message.RecipientType.CC, copyDest);

		} catch (MessagingException e) {
			e.printStackTrace();

		}

		Transport transport = null;
		try {
			transport = current.getTransport("smtp");
			transport.connect(email_account, email_password);
			transport.sendMessage(message, new Address[] { new InternetAddress(destinataire), new InternetAddress(copyDest) });

		} catch (MessagingException e) {
			e.printStackTrace();

		} finally {
			try {
				if (transport != null) {
					transport.close();
				}

			} catch (MessagingException e) {
				e.printStackTrace();

			}

		}

	}

}
