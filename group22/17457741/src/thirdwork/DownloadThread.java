package thirdwork;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import thirdwork.api.Connection;

public class DownloadThread extends Thread{

	Connection conn;
	int startPos;
	int endPos;

	public DownloadThread( Connection conn, int startPos, int endPos){
		
		this.conn = conn;		
		this.startPos = startPos;
		this.endPos = endPos;
	}
	public void run(){	
		System.out.println("start download");
		try {
			byte[] buff = conn.read(startPos, endPos);
		
			RandomAccessFile randomAccessFile = new RandomAccessFile(new File("G:/"), "rwd");
			randomAccessFile.seek(startPos);
			randomAccessFile.write(buff, 0, buff.length);
			randomAccessFile.close();
			
			System.out.println("download is over");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}