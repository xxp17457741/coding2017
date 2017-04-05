package thirdwork.impl;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.URLConnection;

import thirdwork.api.Connection;


public class ConnectionImpl implements Connection{
	

	public URLConnection urlConnection;
	
	public ConnectionImpl(URLConnection urlConnection) {
		this.urlConnection = urlConnection;
	}

	@Override
	public byte[] read(int startPos, int endPos) throws IOException {
		
		urlConnection.setReadTimeout(5000);
		
		urlConnection.setRequestProperty("Range", "bytes=" + startPos + "-" + endPos);
		
		BufferedInputStream inputStream= new BufferedInputStream(urlConnection.getInputStream());
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		
		byte[] buff = new byte[1024];
		int len = 0;
		
		while ((len = inputStream.read(buff)) != -1) {
			outputStream.write(buff,0,len);
		}
		byte[] temp = outputStream.toByteArray();
		
		inputStream.close();
		outputStream.close();
 
		return temp;
	}

	@Override
	public int getContentLength() {
		return urlConnection.getContentLength();
	}

	@Override
	public void close() {
		urlConnection = null;
	}

}