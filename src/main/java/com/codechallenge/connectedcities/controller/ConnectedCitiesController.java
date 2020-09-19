/**
 * 
 */
package com.codechallenge.connectedcities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.codechallenge.connectedcities.service.ConnectedCitiesService;

import io.swagger.annotations.ApiOperation;

/**
 * @author GodfreyImudia
 */
@RestController
@RequestMapping("/")
public class ConnectedCitiesController {
	
	@Autowired
	private ConnectedCitiesService connectedCitiesService; 
	
	@ApiOperation(value = "Check cities are connected or not", notes = "Returns YES if they are connected else NO ", response = String.class)
	@GetMapping("connected")
	@ResponseBody
	public String isRouteAvailable(@RequestParam(name = "origin", required = true) String origin,
			@RequestParam(name = "destination", required = true) String destination) {
		return connectedCitiesService.areCitiesConnected(origin, destination);
	}
}
