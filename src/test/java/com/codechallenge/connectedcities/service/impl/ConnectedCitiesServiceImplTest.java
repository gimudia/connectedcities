/**
 * 
 */
package com.codechallenge.connectedcities.service.impl;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.codechallenge.connectedcities.data.ConnectedCitiesDataLoader;

/**
 * @author GodfreyImudia
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ConnectedCitiesServiceImplTest {
	
	@InjectMocks
	private ConnectedCitiesServiceImpl connectedCitiesService; 
	
	@Mock
	private ConnectedCitiesDataLoader connectedCitiesDataLoader;

	/**
	 * Test method for {@link com.codechallenge.connectedcities.service.impl.ConnectedCitiesServiceImpl#areCitiesConnected(java.lang.String, java.lang.String)}.
	 */
	@Test
	public void Given_ServiceUp_When_OriginAndDestinationCitiesAreConnected_Then_ReturnYES() {
		when(connectedCitiesDataLoader.IsConnected("boston", "new york")).thenReturn("Yes");
		String isConnected = connectedCitiesService.areCitiesConnected("Boston", "New York");
		assertEquals("Yes",isConnected);
	}
	
	@Test
	public void Given_ServiceUp_When_OriginAndDestinationCitiesAreConnected_Then_ReturnNo() {
		when(connectedCitiesDataLoader.IsConnected("boston", "new york")).thenReturn("No");
		String isConnected = connectedCitiesService.areCitiesConnected("Boston", "New York");
		assertEquals("No",isConnected);
	}

}
