/**
 * 
 */
package com.codechallenge.road.city.connect.service.impl;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import com.codechallenge.road.city.connect.service.ICityService;
import com.codechallenge.road.city.connect.utils.CityConnectUtils;

/**
 * @author Godfrey
 *
 */
@Service
public class CityServiceImpl implements ICityService {
	private static final Logger LOGGER = LoggerFactory.getLogger(CityServiceImpl.class);
	
	@Value("${connectedCityFilePath}")
	private String filePath;
	
	static final String COMMA_DELIMITED = ",";
	
	/**
	 * This method will get the city data from given file path and loading the data into the memory
	 */
	@PostConstruct
	private void processCitydatafromFile() {
		List<String> fileData = new ArrayList<>();
		try {
			LOGGER.info("Start reading the city route data file from path");
			fileData = Files
					.readAllLines(ResourceUtils.getFile("classpath:" + filePath).toPath());
		} catch (IOException ex) {
			LOGGER.error("City Connected Data file not found on path: {} ",
					this.filePath);
			LOGGER.error(ex.toString());
		}
		CityConnectUtils cityConnectUtils = new CityConnectUtils();
		fileData.stream().forEach(data -> {
			String[] cities = data.split(COMMA_DELIMITED);
			cityConnectUtils.addCityData(cities[0], cities[1]);
			});
	}

	/**
	 * This Method will check if Origin and destination cities are connected 
	 * @param origin
	 * @param destination
	 * @return 'Yes' if connected 'No' if not connected
	 */
	@Override
	public String areCitiesConnected(String origin, String destination) throws Exception {
		try {
			LOGGER.info("check both city is connetced or not {0},{1}", origin,destination);
			CityConnectUtils cityConnectUtils = new CityConnectUtils();
			return cityConnectUtils.IsConnected(origin, destination);
		} catch(Exception ex) {
			LOGGER.error("exception while check cities are connected {0}", ex.getMessage());
		}
		return null;
	}

}
