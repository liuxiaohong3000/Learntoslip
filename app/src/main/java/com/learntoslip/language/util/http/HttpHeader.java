package com.learntoslip.language.util.http;

public class HttpHeader {

	private String _key;
	private Object _value;
	
	public String key() { return _key; }
	public Object value() { return _value; }
	public String valueString() { return _value != null? _value.toString(): ""; }

	public HttpHeader(String key, Object value) {
		_key = key;
		_value = value;
	}

	public String toString() {
		return key() + " = " + value();
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {

	}
}
