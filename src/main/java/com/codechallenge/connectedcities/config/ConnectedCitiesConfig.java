package com.codechallenge.connectedcities.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @author GodfreyImudia
 */
@Configuration
public class ConnectedCitiesConfig {
	
	@Value("${path.connectedcities.data}")
	private String filePathConnectedCitiesData;

	/**
	 * @return the filePathConnectedCitiesData
	 */
	public String getFilePathConnectedCitiesData() {
		return filePathConnectedCitiesData;
	}

	/**
	 * @param filePathConnectedCitiesData the filePathConnectedCitiesData to set
	 */
	public void setFilePathConnectedCitiesData(String filePathConnectedCitiesData) {
		this.filePathConnectedCitiesData = filePathConnectedCitiesData;
	}
	
	
}
