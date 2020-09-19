/**
 * 
 */
package com.codechallenge.road.city.connect.utils;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * @author Godfrey
 *
 */
public class CityConnectUtils {
	private static final Logger logger = LoggerFactory.getLogger(CityConnectUtils.class);
	
	private Map<String, LinkedList<String>> cityRoutesData = new ConcurrentHashMap<String, LinkedList<String>>();
	
	/**
	 * This method will creating the connected city data into service cache
	 * @param origin
	 * @param destination
	 */
	public void addCityData(String origin, String destination) {
		logger.info("Start processing to insert data into cache {0} {1}", origin, destination);
		//adding origin and destination city data into cached into Lower case
		String originCity = origin.trim().toLowerCase();
		String destinationCity = destination.trim().toLowerCase();
		//check if both the cities are same and not empty
		if (!StringUtils.isEmpty(originCity) && !StringUtils.isEmpty(destinationCity) && !originCity.equals(destinationCity)) {
			// add data into link list for Origin and destination cities data
			LinkedList<String> llOriginCity = cityRoutesData.getOrDefault(originCity, new LinkedList<String>());
			LinkedList<String> llDestinationCity = cityRoutesData.getOrDefault(destinationCity, new LinkedList<String>());
			llOriginCity.add(destinationCity);
			llDestinationCity.add(originCity);
			// added data into main cache
			cityRoutesData.put(originCity, llOriginCity);
			cityRoutesData.put(destinationCity, llDestinationCity);
			logger.info("added data into the cache {0} {1}", origin, destination);
		}
	}
	
	/**
	 * This method will check weather origin and destination city are connected or not
	 * @param origin
	 * @param destination
	 * @return 'Yes' or 'No'
	 */
	public String IsConnected(String origin, String destination) {
		// check cached data empty and null
		if(CollectionUtils.isEmpty(cityRoutesData)) {
			logger.error("No cached data found for city connected");
			return "no";
		}
		//check if origin and destination city are empty or null 
		if(StringUtils.isEmpty(origin) || StringUtils.isEmpty(destination)) {
			return "no";
		}
		// check if the given city are not exist into the cached data
		if (!cityRoutesData.containsKey(origin) && !cityRoutesData.containsKey(destination)) {
			logger.error("Origin and destination cities are not present into current cache {0} {1}",origin,destination);
			return "no";
		}
		Set<String> searchedNode = new HashSet<String>();
		Queue<String> queue = new LinkedList<String>();
		queue.offer(origin); 
		while (!queue.isEmpty()) {
			String queueEntry = queue.poll();
			searchedNode.add(queueEntry);
			if (queueEntry.equals(destination)) {
				return "yes";
			}
			LinkedList<String> connectedCities = cityRoutesData.get(queueEntry);
			if (connectedCities != null) {
				for (String connectedCity : connectedCities) {
					if (!searchedNode.contains(connectedCity)) {
						queue.offer(connectedCity);
					}
				}
			}
		}
		return "no";
	}

}
