package com.suam.bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropriedadesDB {
	private String username;
	private String password;
	private String url;

	public PropriedadesDB() throws FileNotFoundException, IOException {
		Properties properties = new Properties();
		properties.load(new FileInputStream("arquivos/database.properties"));
		this.username = properties.getProperty("username");
		this.password = properties.getProperty("password");
		this.url = properties.getProperty("url");
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getUrl() {
		return url;
	}

}
