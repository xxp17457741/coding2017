package com.coderising.download;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.List;

import com.coderising.download.api.Connection;
import com.coderising.download.api.ConnectionException;
import com.coderising.download.api.ConnectionManager;
import com.coderising.download.api.DownloadListener;
import com.coding.basic.ArrayList;

public class FileDownloader {

	String url;

	DownloadListener listener;

	ConnectionManager cm;

	public FileDownloader(String _url) {
		this.url = _url;

	}

	public void execute() {
		// ������ʵ����Ĵ��룬 ע�⣺ ��Ҫ�ö��߳�ʵ������
		// ��������������������ӿ�, ����Ҫд�⼸���ӿڵ�ʵ�ִ���
		// (1) ConnectionManager , ���Դ�һ�����ӣ�ͨ��Connection���Զ�ȡ���е�һ�Σ���startPos,
		// endPos��ָ����
		// (2) DownloadListener, �����Ƕ��߳����أ� ���������Ŀͻ��˲�֪��ʲôʱ���������������Ҫʵ�ֵ�����
		// �̶߳�ִ�����Ժ� ����listener��notifiedFinished������ �����ͻ��˾����յ�֪ͨ��
		// �����ʵ��˼·��
		// 1. ��Ҫ����ConnectionManager��open���������ӣ�
		// Ȼ��ͨ��Connection.getContentLength��������ļ��ĳ���
		// 2. ��������3���߳����أ� ע��ÿ���߳���Ҫ�ȵ���ConnectionManager��open����
		// Ȼ�����read������ read�������ж�ȡ�ļ��Ŀ�ʼλ�úͽ���λ�õĲ����� ����ֵ��byte[]����
		// 3. ��byte����д�뵽�ļ���
		// 4. ���е��̶߳���������Ժ� ��Ҫ����listener��notifiedFinished����

		// ����Ĵ�����ʾ�����룬 Ҳ����˵ֻ��һ���̣߳� ����Ҫ����ɶ��̵߳ġ�
		Connection conn = null;
		try {
			Integer threadNum = 3;
			//Integer threadDone = 0;
			ArrayList threadDone=new ArrayList();
			conn = cm.open(this.url);
			if (conn.getConn().getResponseCode() == 200) {
				int length = conn.getContentLength();
				int size = (length % threadNum == 0 ? length / threadNum : length / threadNum + 1);

				String filename = url.substring(url.lastIndexOf('/'));
				String filePath = "C:\\Users\\Administrator\\Desktop\\" + filename;
				File file = new File(filePath);
				RandomAccessFile raf = new RandomAccessFile(file, "rw");
				raf.setLength(length);
				raf.close();

				for (int i = 0; i < threadNum; i++) {
					Connection connThread = cm.open(this.url);
					new DownloadThread(connThread, i * size, (i + 1) * size - 1, listener, file, threadNum,
							threadDone).start();
				}
			}
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (conn != null) {
				conn.close();
			}
		}

	}

	public void setListener(DownloadListener listener) {
		this.listener = listener;
	}

	public void setConnectionManager(ConnectionManager ucm) {
		this.cm = ucm;
	}

	public DownloadListener getListener() {
		return this.listener;
	}

}
