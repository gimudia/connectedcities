package com.codechallenge.connectedcities.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.codechallenge.connectedcities.data.ConnectedCitiesDataLoader;
import com.codechallenge.connectedcities.service.ConnectedCitiesService;

/**
 * @author GodfreyImudia
 *
 */
@Service
public class ConnectedCitiesServiceImpl implements ConnectedCitiesService {
	
	@Autowired
	private ConnectedCitiesDataLoader connectedCitiesDataLoader;

	/**
	 * @see com.codechallenge.connectedcities.service.ConnectedCitiesService#areCitiesConnected()
	 */
	@Override
	public String areCitiesConnected(String origin, String destination) {
		return connectedCitiesDataLoader.IsConnected(origin.trim().toLowerCase(), destination.trim().toLowerCase());
	}
}
