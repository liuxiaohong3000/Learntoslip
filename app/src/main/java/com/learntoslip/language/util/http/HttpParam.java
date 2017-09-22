package com.learntoslip.language.util.http;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class HttpParam {

	private String _key;
	private Object _value;
	
	public String key() { return _key; }
	
	public Object value() { return _value; }
	
	public String valueString(String enc) {
		if (_value != null) {
			try {
				return URLEncoder.encode(_value.toString(), (enc == null? "utf-8": enc));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public HttpParam(String key, Object value) {
		_key = key;
		_value = value;
	}
	
	public String toString() {
		return key() + "=" + value();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}

}
