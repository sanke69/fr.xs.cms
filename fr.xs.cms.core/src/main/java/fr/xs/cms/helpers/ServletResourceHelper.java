package fr.xs.cms.helpers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.servlet.ServletContext;

import fr.xs.jtk.bytes.ByteStreams;
import fr.xs.jtk.helpers.MediaHelper;

public class ServletResourceHelper extends MediaHelper {

	static ServletContext context;

	public static void initialize(ServletContext _context) {
		context = _context;
	}

	public static InputStream get(String _url) {
		InputStream is = MediaHelper.getContent( _url);

		if(is == null) {
			try {
				String path = context.getRealPath(_url);
				is = new FileInputStream(new File(path));
			} catch(IOException e) {
				is = null;
			}
		}
		if(is == null) {
			try {
				URL resourceUrl = context.getResource(_url);
				is = resourceUrl.openStream();
			} catch(IOException e) {
				is = null;
			}
		}

		return is;
	}
	public static byte[] getAsByteArrays(String _url) {
		InputStream is = get(_url);
		if(is != null)
			return ByteStreams.toByteArray(is);
		return null;
	}
	public static String getAsString(String _url) {
		byte[] data = getAsByteArrays(_url);
		if(data != null)
			return new String(data);
		return null;
	}
	
	public static InputStream get(Class<?> _class, String _url) {
		InputStream is = MediaHelper.getContent(_class, _url);

		if(is == null) {
			try {
				String path = context.getRealPath(_url);
				is = new FileInputStream(new File(path));
			} catch(IOException e) {
				is = null;
			}
		}
		if(is == null) {
			try {
				URL resourceUrl = context.getResource(_url);
				is = resourceUrl.openStream();
			} catch(IOException e) {
				is = null;
			}
		}

		return is;
	}
	public static byte[] getAsByteArrays(Class<?> _class, String _url) {
		InputStream is = get(_class, _url);
		if(is != null)
			return ByteStreams.toByteArray(is);
		return null;
	}
	public static String getAsString(Class<?> _class, String _url) {
		byte[] data = getAsByteArrays(_class, _url);
		if(data != null)
			return new String(data);
		return null;
	}


}
