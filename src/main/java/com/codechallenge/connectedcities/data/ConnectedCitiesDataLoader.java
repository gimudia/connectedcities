package com.codechallenge.connectedcities.data;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author GodfreyImudia
 */
@Component
public class ConnectedCitiesDataLoader {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectedCitiesDataLoader.class);
	private Map<String, String> connectedCityNames;

	/**
	 * @return the connectedCityNames
	 */
	public Map<String, String> getConnectedCityNames() {
		return connectedCityNames;
	}

	/**
	 * @param connectedCityNames the connectedCityNames to set
	 */
	public void setConnectedCityNames(Map<String, String> connectedCityNames) {
		this.connectedCityNames = connectedCityNames;
	}

	/**
	 * @throws URISyntaxException
	 */
	@PostConstruct
	public void init() throws URISyntaxException {
		connectedCityNames = new HashMap<>();
		Path filePath = Paths.get(ClassLoader.getSystemResource("city.txt").toURI());
		try (Stream<String> lines = Files.lines(filePath)) {
			LOGGER.info("Start reading the city route data file from path");
			lines.forEach(line -> {
				String[] cities = line.split(",");
				connectedCityNames.put(cities[0], cities[1]);
			});
		} catch (IOException e) {
			LOGGER.error("City Connected Data file not found on path");
			e.printStackTrace();
			
		}
	}

}
