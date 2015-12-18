package fr.xs.cms.helpers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.markdown4j.Markdown4jProcessor;

public class HtmlContentHelper extends ServletResourceHelper {

	public static String loadFromHtml(String _url) {
		byte[] data = ServletResourceHelper.getAsByteArrays(_url);
		if(data == null)
			return "Failed to load ressource " + _url;

		return new String(data);
	}
	public static String loadFromMarkdown(String _url) {
		byte[] data = ServletResourceHelper.getAsByteArrays(_url);
		if(data == null)
			return "Failed to load ressource " + _url;

        String markdown2html = null;
        try { 
        	markdown2html = new Markdown4jProcessor().process(new String(data, StandardCharsets.UTF_8));
		} catch(IOException e) { markdown2html = "error"; e.printStackTrace(); }

        return markdown2html;
	}
	public static String loadFromLatex(String _url) {
		byte[] data = ServletResourceHelper.getAsByteArrays(_url);
		if(data == null)
			return "Failed to load ressource " + _url;

        String latex2html = null;
        try { 
        	latex2html = new String(data, StandardCharsets.UTF_8);

//        	if(latex2html == null)
//        		throw new Exception();
		} catch(Exception e) { latex2html = "Failed to load ressource " + _url; }

		return latex2html;
	}

	public static String loadFromHtml(Class<?> _cls, String _url) {
		byte[] data = ServletResourceHelper.getAsByteArrays(_cls, _url);
		if(data == null)
			return "Failed to load ressource " + _url;

		return new String(data);
	}
	public static String loadFromMarkdown(Class<?> _cls, String _url) {
		byte[] data = ServletResourceHelper.getAsByteArrays(_cls, _url);
		if(data == null)
			return "Failed to load ressource " + _url;

        String markdown2html = null;
        try { 
        	markdown2html = new Markdown4jProcessor().process(new String(data, StandardCharsets.UTF_8));
		} catch(IOException e) { markdown2html = "error"; e.printStackTrace(); }

        return markdown2html;
	}
	public static String loadFromLatex(Class<?> _cls, String _url) {
		byte[] data = ServletResourceHelper.getAsByteArrays(_cls, _url);
		if(data == null)
			return "Failed to load ressource " + _url;

        String latex2html = null;
        try { 
        	latex2html = new String(data, StandardCharsets.UTF_8);

//        	if(latex2html == null)
//        		throw new Exception();
		} catch(Exception e) { latex2html = "Failed to load ressource " + _url; }

		return latex2html;
	}

}
