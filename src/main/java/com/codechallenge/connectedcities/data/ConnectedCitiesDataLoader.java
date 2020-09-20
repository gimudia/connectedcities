package com.codechallenge.connectedcities.data;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author GodfreyImudia
 */
@Component
public class ConnectedCitiesDataLoader {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectedCitiesDataLoader.class);
	private Map<String, LinkedList<String>> connectedCityNames;
	private static final String COMMA_DELIMITED = ",";

	/**
	 * @return the connectedCityNames
	 */
	public Map<String, LinkedList<String>> getConnectedCityNames() {
		return connectedCityNames;
	}

	/**
	 * @param connectedCityNames the connectedCityNames to set
	 */
	public void setConnectedCityNames(Map<String, LinkedList<String>> connectedCityNames) {
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
			LOGGER.info("Start reading the city route data file from path {}",filePath);
			lines.forEach(line -> {
				String[] cities = line.split(COMMA_DELIMITED);
				String origin = cities[0].trim().toLowerCase();
				String destination = cities[1].trim().toLowerCase();
				LinkedList<String> llOriginCity = connectedCityNames.getOrDefault(origin, new LinkedList<String>());
				LinkedList<String> llDestinationCity = connectedCityNames.getOrDefault(destination, new LinkedList<String>());
				llOriginCity.add(destination);
				llDestinationCity.add(origin);
				// added data into main cache
				connectedCityNames.put(origin, llOriginCity);
				connectedCityNames.put(destination, llDestinationCity);
			});
		} catch (IOException e) {
			LOGGER.error("City Connected Data file not found on path");
			e.printStackTrace();
		}
	}
	
	/**
	 * This method will check weather origin and destination city are connected or not
	 * @param origin
	 * @param destination
	 * @return 'Yes' or 'No'
	 */
	public String IsConnected(String origin, String destination) {
		LOGGER.info("**Start cheking if origin {} and destination {} cities are connected or not",origin,destination);
		// check cached data empty and null
		if(CollectionUtils.isEmpty(connectedCityNames)) {
			LOGGER.error("**No cached data found for city connected");
			return "No";
		}
		//check if origin and destination city are empty or null 
		if(StringUtils.isEmpty(origin) || StringUtils.isEmpty(destination)) {
			LOGGER.error("**Origin {} OR Destination {} cities are blank or empty",origin,destination);
			return "No";
		}
		// check if the given city are not exist into the cached data
		if (!connectedCityNames.containsKey(origin) && !connectedCityNames.containsKey(destination)) {
			LOGGER.error("**Origin and destination cities are not present into current cache {} to {}",origin,destination);
			return "No";
		}
		Set<String> searchedNode = new HashSet<String>();
		Queue<String> queue = new LinkedList<String>();
		queue.offer(origin); 
		while (!queue.isEmpty()) {
			String queueEntry = queue.poll();
			searchedNode.add(queueEntry);
			if (queueEntry.equals(destination)) {
				LOGGER.error("**Origin {} AND Destination {} cities are connected!!",origin,destination);
				return "Yes";
			}
			LinkedList<String> connectedCities = connectedCityNames.get(queueEntry);
			if (connectedCities != null) {
				for (String connectedCity : connectedCities) {
					if (!searchedNode.contains(connectedCity)) {
						queue.offer(connectedCity);
					}
				}
			}
		}
		return "No";
	}
}
