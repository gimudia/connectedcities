/**
 * 
 */
package com.codechallenge.connectedcities.data;

import static org.junit.Assert.assertEquals;

import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author GodfreyImudia
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ConnectedCitiesDataLoaderTest {
	@InjectMocks
	private ConnectedCitiesDataLoader connectedCitiesDataLoader;
	
	@Before
	public void init() throws URISyntaxException {
		connectedCitiesDataLoader.init();
	}
	
	@Test
	public void Given_ServiceUp_When_InvalidOriginAndDestinationCities_Then_ReturnNo() {
		String isConnected = connectedCitiesDataLoader.IsConnected("test", "test");
		assertEquals("No",isConnected);
	}
	
	@Test
	public void Given_CityCacheLoaded_When_OriginAndDestinationCitiesCachedNotLoaded_Then_ReturnNo() {
		String isConnected = connectedCitiesDataLoader.IsConnected("boston", "new york");
		assertEquals("Yes",isConnected);
	}
	
	@Test
	public void Given_CityCacheNotLoaded_When_ValidOriginAndDestinationCities_Then_ReturnNo() {
		connectedCitiesDataLoader.setConnectedCityNames(null);
		String isConnected = connectedCitiesDataLoader.IsConnected("boston", "new york");
		assertEquals("No",isConnected);
	}
	
	@Test
	public void Given_CityCacheLoaded_When_ValidOriginAndInvaidDestinationCities_Then_ReturnNo() {
		String isConnected = connectedCitiesDataLoader.IsConnected("boston", "test");
		assertEquals("No",isConnected);
	}
	
	@Test
	public void Given_CityCacheLoaded_When_InValidOriginAndInvaidDestinationCities_Then_ReturnNo() {
		String isConnected = connectedCitiesDataLoader.IsConnected("test", "test");
		assertEquals("No",isConnected);
	}
	
	@Test
	public void Given_CityCacheLoaded_When_NullOriginAndNullDestinationCities_Then_ReturnNo() {
		String isConnected = connectedCitiesDataLoader.IsConnected(null, null);
		assertEquals("No",isConnected);
	}
	

}
