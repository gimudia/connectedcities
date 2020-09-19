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
	 * @return
	 */
	public String areCitiesConnected(String origin, String destination);
}
