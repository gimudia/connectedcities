package com.codechallenge.connectedcities.service;

import org.springframework.stereotype.Service;

/**
 * @author GodfreyImudia
 */
@Service
public interface ConnectedCitiesService {
	/**
	 * @param origin
	 * @param destination
	 * @return String
	 * @implNote Method is responsible check route is available or Not between cities.
	 */
	public String areCitiesConnected(String origin, String destination);
}
