package com.coderising.download.api;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.HttpURLConnection  ;

public interface Connection {
	URL fileurl = null;
	HttpURLConnection   conn = null;
	InputStream inStream = null;

	/**
	 * ������ʼ�ͽ���λ�ã� ��ȡ���ݣ� ����ֵ���ֽ�����
	 * 
	 * @param startPos
	 *            ��ʼλ�ã� ��0��ʼ
	 * @param endPos
	 *            ����λ��
	 * @return
	 */
	public byte[] read(int startPos, int endPos,File file) throws IOException;

	/**
	 * �õ��������ݵĳ���
	 * 
	 * @return
	 */
	public int getContentLength();

	/**
	 * �ر�����
	 */
	public void close();

	public void setConn(HttpURLConnection  conn);
	public void setFileurl(URL fileurl);
	public HttpURLConnection  getConn();
	public URL getFileurl(URL fileurl) ;
	public void setinStream(InputStream inStream);
	public InputStream getinStream();
}
