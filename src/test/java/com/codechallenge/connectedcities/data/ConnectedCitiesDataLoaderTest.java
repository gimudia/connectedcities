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
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codechallenge.connectedcities.config.ConnectedCitiesConfig;
import com.codechallenge.connectedcities.constants.ConnectedCitiesConstant;

/**
 * @author GodfreyImudia
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ConnectedCitiesDataLoaderTest {
	@Mock
	private ConnectedCitiesConfig connectedCitiesConfig;
	
	@InjectMocks
	private ConnectedCitiesDataLoader connectedCitiesDataLoader;
	
	@Before
	public void init() throws URISyntaxException {
		Mockito.when(connectedCitiesConfig.getFilePathConnectedCitiesData()).thenReturn("data//city.txt");
		connectedCitiesDataLoader.init();
	}
	
	@Test
	public void Given_ServiceUp_When_InvalidOriginAndDestinationCities_Then_ReturnNo() {
		String isConnected = connectedCitiesDataLoader.isRouteConnected("test", "test");
		assertEquals(ConnectedCitiesConstant.NO,isConnected);
	}
	
	@Test
	public void Given_CityCacheLoaded_When_OriginAndDestinationBothAreConnected_Then_ReturnNo() {
		String isConnected = connectedCitiesDataLoader.isRouteConnected("boston", "new york");
		assertEquals(ConnectedCitiesConstant.YES,isConnected);
	}
	
	@Test
	public void Given_CityCacheLoaded_When_OriginAndDestinationCitiesViceVersa_Then_ReturnNo() {
		String isConnected = connectedCitiesDataLoader.isRouteConnected("new york","boston");
		assertEquals(ConnectedCitiesConstant.YES,isConnected);
	}
	
	@Test
	public void Given_CityCacheNotLoaded_When_ValidOriginAndDestinationCities_Then_ReturnNo() {
		connectedCitiesDataLoader.setConnectedCityNames(null);
		String isConnected = connectedCitiesDataLoader.isRouteConnected("boston", "new york");
		assertEquals(ConnectedCitiesConstant.NO,isConnected);
	}
	
	@Test
	public void Given_CityCacheLoaded_When_ValidOriginAndInvaidDestinationCities_Then_ReturnNo() {
		String isConnected = connectedCitiesDataLoader.isRouteConnected("boston", "test");
		assertEquals(ConnectedCitiesConstant.NO,isConnected);
	}
	
	@Test
	public void Given_CityCacheLoaded_When_InValidOriginAndInvaidDestinationCities_Then_ReturnNo() {
		String isConnected = connectedCitiesDataLoader.isRouteConnected("test", "test");
		assertEquals(ConnectedCitiesConstant.NO,isConnected);
	}
	
	@Test
	public void Given_CityCacheLoaded_When_NullOriginAndNullDestinationCities_Then_ReturnNo() {
		String isConnected = connectedCitiesDataLoader.isRouteConnected(null, null);
		assertEquals(ConnectedCitiesConstant.NO,isConnected);
	}
	

}
