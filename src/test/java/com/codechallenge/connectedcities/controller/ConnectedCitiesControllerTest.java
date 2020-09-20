/**
 * 
 */
package com.codechallenge.connectedcities.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.codechallenge.connectedcities.data.ConnectedCitiesDataLoader;
import com.codechallenge.connectedcities.service.impl.ConnectedCitiesServiceImpl;

/**
 * @author GodfreyImudia
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ConnectedCitiesControllerTest {
	
	@InjectMocks
	private ConnectedCitiesController connectedCitiesController;
	
	@Mock
	private ConnectedCitiesServiceImpl connectedCitiesService; 
	
	@Mock
	private ConnectedCitiesDataLoader connectedCitiesDataLoader;
	
	@Test
	public void Given_ServiceUp_When_OriginAndDestinationCitiesAreConnected_Then_ReturnYES() throws Exception {
		when(connectedCitiesService.areCitiesConnected("Boston", "New York")).thenReturn("Yes");
		String isConencted = connectedCitiesController.isRouteAvailable("Boston", "New York");
		assertEquals("Yes",isConencted);
	}
	
	@Test
	public void Given_ServiceUp_When_OriginAndDestinationCitiesAreConnected_Then_ReturnNo() throws Exception {
		when(connectedCitiesService.areCitiesConnected("Boston", "test")).thenReturn("No");
		String isConencted = connectedCitiesController.isRouteAvailable("Boston", "test");
		assertEquals("No",isConencted);
	}

}
