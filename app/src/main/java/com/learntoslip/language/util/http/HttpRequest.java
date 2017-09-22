package com.learntoslip.language.util.http;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ArrayList;
import java.util.zip.GZIPInputStream;

public class HttpRequest {
	protected ArrayList<HttpParam> _params = null;
	protected ArrayList<HttpHeader> _headers = null;
	protected ArrayList<HttpParam> _dataParams = null;

	protected String _url;

	public HttpRequest() {
	}

	public HttpRequest(String url) {
		_url = url;
	}

	public HttpRequest addParam(String key, Object value) {
		if (key == null || value == null) {
			return this;
		}
		if (_params == null) {
			_params = new ArrayList<HttpParam>();
		}
		_params.add(new HttpParam(key, value));
		return this;
	}

	public HttpRequest addDataParam(String key, Object value) {
		if (key == null || value == null) {
			return this;
		}
		if (_dataParams == null) {
			_dataParams = new ArrayList<HttpParam>();
		}
		_dataParams.add(new HttpParam(key, value));
		return this;
	}

	public HttpRequest addHeader(String key, Object value) {
		if (key == null || value == null) {
			return this;
		}
		if (_headers == null) {
			_headers = new ArrayList<HttpHeader>();
		}
		_headers.add(new HttpHeader(key, value));
		return this;
	}

	public String post(int connectTimeout, int readTimeout, String reqEnc, String respEnc) throws Exception {
		StringBuffer reqParam = new StringBuffer();
		if (_params != null) {
			for (HttpParam param : _params) {
				reqParam.append(param.key()).append("=").append(param.valueString(reqEnc)).append("&");
			}
		}
		long starttime=System.currentTimeMillis();
		String response=sendRaw(_url, reqParam.toString(), "POST", connectTimeout, readTimeout, reqEnc, respEnc);
		long endtime=System.currentTimeMillis();
		System.out.println("请求信息:"+reqParam.toString()+",响应时间:"+(endtime-starttime));
		return response;
	}

	public String get(int connectTimeout, int readTimeout, String reqEnc, String respEnc) throws Exception {
		StringBuffer reqParam = new StringBuffer(_url);
		if (_params != null) {
			reqParam.append("?");
			int i=0;
			for (HttpParam param : _params) {
				if(i!=0){
					reqParam.append("&");
				}
				if(reqEnc!=null){
					reqParam.append(param.key()).append("=").append(param.valueString(reqEnc));
				}else{
					reqParam.append(param.key()).append("=").append(param.value());
				}
				i++;
			}
		}
		long starttime=System.currentTimeMillis();
		String response=sendRaw(reqParam.toString(), null, "GET", 
				connectTimeout, readTimeout, reqEnc, respEnc);
		long endtime=System.currentTimeMillis();
		System.out.println("请求信息:"+reqParam.toString()+",响应时间:"+(endtime-starttime));
		return response;


	}

	public String sendRaw(String url, String content, String method, int connectTimeout, int readTimeout, String reqEnc,
			String respEnc) throws Exception {

		if (url == null || method == null || connectTimeout < 0 || readTimeout < 0) {
			return null;
		}

		//System.out.println(url + "?" + content);

		InputStream is = null;
		OutputStream os = null;
		HttpURLConnection conn = null;

		try {
			conn = (HttpURLConnection) new URL(url).openConnection();
			// if(_url.contains("127")){
			// conn = (HttpURLConnection) new URL(url).openConnection();
			// }else{
			// Proxy proxy = new Proxy(Type.HTTP, new
			// InetSocketAddress("42.121.121.188", 60001));
			// conn = (HttpURLConnection) new URL(url).openConnection(proxy);
			// }
			conn.setConnectTimeout(connectTimeout);
			conn.setReadTimeout(readTimeout);
			conn.setDoInput(true);
			conn.setRequestMethod(method.toUpperCase());

			if (_headers != null) {
				for (HttpHeader header : _headers) {
					conn.setRequestProperty(header.key(), header.valueString());
				}
			}

			if (method.equalsIgnoreCase("post")) {
				conn.setDoOutput(true);

				os = conn.getOutputStream();
				os.write(content.getBytes(reqEnc == null ? "utf-8" : reqEnc));
				os.flush();
			}
			boolean gziped = false;
			String contentEncoding = conn.getContentEncoding();
			gziped = contentEncoding != null && contentEncoding.equalsIgnoreCase("gzip");
			int httpStatus = conn.getResponseCode();
			if (httpStatus == 200) {
				is = gziped ? new GZIPInputStream(conn.getInputStream()) : conn.getInputStream();
			} else {
				throw new Exception("http:" + httpStatus);
			}
			byte[] a = new byte[1024];
			int length = 0;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((length = is.read(a, 0, 1024)) > 0) {
				bos.write(a, 0, length);
			}
			String response = new String(bos.toByteArray(), respEnc == null ? "utf-8" : respEnc);
			// System.out.println(response);
			bos.close();
			return response;
		} catch (SocketTimeoutException se) {
			throw se;
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (os != null) {
					os.close();
					os = null;
				}
				if (is != null) {
					is.close();
					is = null;
				}
				if (conn != null) {
					conn.disconnect();
					conn = null;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public ArrayList<HttpParam> get_params() {
		return _params;
	}

	public ArrayList<HttpParam> get_dataParams() {
		return _dataParams;
	}

}
