package fr.xs.cms.core.html.properties;

import java.util.HashMap;
import java.util.Map;

public class HtmlRequestParameters extends HashMap<String, String> {
	private static final long serialVersionUID = 1L;

	public HtmlRequestParameters() {
		super(); 
	}
	
	public HtmlRequestParameters add(String _key, String _value) {
		put(_key,  _value);
		return this;
	}
	public HtmlRequestParameters remove(String _key) {
		remove(_key);
		return this;
	}
	
	public boolean isEmpty() {
		return size() == 0;
	}

//	@Override
	public String toUrlArgv() {
		boolean first = true;
		String result = "?";
		for(Map.Entry<String, String> param : entrySet())
			if(first) {
				result += param.getKey() + "=" + param.getValue();
				first =false;
			} else
				result += "&" + param.getKey() + "=" + param.getValue();
		
		return result;
	}

}
