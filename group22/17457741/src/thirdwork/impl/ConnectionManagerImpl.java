package thirdwork.impl;

import java.net.URL;

import thirdwork.api.Connection;
import thirdwork.api.ConnectionException;
import thirdwork.api.ConnectionManager;

public class ConnectionManagerImpl implements ConnectionManager {

	@Override
	public Connection open(String url) throws ConnectionException {
		Connection connection = null;
		try {
			
			connection = new ConnectionImpl(new URL(url).openConnection());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}

}