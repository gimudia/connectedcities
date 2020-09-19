/**
 * 
 */
package com.codechallenge.road.city.connect.service;

/**
 * @author Godfrey
 *
 */
public interface ICityService {
	
  /**
	* Checking if origin and destination cities are connected each other.
	* @param origin
	* @param destination
	* @return 'Yes' or 'No'
	* @throws Exception
  */
	public String areCitiesConnected(String origin, String destination) throws Exception;

}
