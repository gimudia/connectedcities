package com.codechallenge.connectedcities.service.impl;

import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.codechallenge.connectedcities.data.ConnectedCitiesDataLoader;
import com.codechallenge.connectedcities.service.ConnectedCitiesService;

/**
 * @author GodfreyImudia
 *
 */
@Service
public class ConnectedCitiesServiceImpl implements ConnectedCitiesService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ConnectedCitiesServiceImpl.class);

	@Autowired
	private ConnectedCitiesDataLoader connectedCitiesDataLoader;

	/**
	 * @see com.codechallenge.connectedcities.service.ConnectedCitiesService#areCitiesConnected()
	 */
	@Override
	public String areCitiesConnected(String origin, String destination) {
		// check if origin and destination city are empty or null
		if (StringUtils.isEmpty(origin.trim()) || StringUtils.isEmpty(destination)) {
			return "No";
		}
		
		Map<String, String> connectedCityNames = connectedCitiesDataLoader.getConnectedCityNames();
		Map<String, String> result = connectedCityNames.entrySet().stream()
				.filter(map -> (map.getKey().equalsIgnoreCase(origin.trim()) && map.getValue().equalsIgnoreCase(destination.trim()))
						|| (map.getKey().equalsIgnoreCase(destination) && map.getValue().equalsIgnoreCase(origin)))
				.collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
		
		if (Objects.nonNull(result) && !CollectionUtils.isEmpty(result)) {
			return "Yes";
		}
		return "No";
	}
}
