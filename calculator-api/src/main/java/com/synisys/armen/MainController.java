package com.synisys.armen;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author armen.mkrtchyan
 */
@RestController
@RequestMapping("/calc")
public class MainController {

	private final RestTemplate restTemplate;
	private final Object lock = new Object();

	@Value("${server.url}")
	private String serverUrl;

	public MainController() {
		restTemplate = new RestTemplate();
	}

	@RequestMapping(value = "/sum/{first}/{second}", method = RequestMethod.GET)
	public @ResponseBody Integer sum(@PathVariable Integer first, @PathVariable Integer second) {
		return restTemplate.getForObject(serverUrl + "/sum/{first}/{second}", Integer.class, first, second);
	}

	@RequestMapping(value = "/multiply/{first}/{second}", method = RequestMethod.GET)
	public @ResponseBody Integer multiply(@PathVariable Integer first, @PathVariable Integer second) {
		return restTemplate.getForObject(serverUrl + "/multiply/{first}/{second}", Integer.class, first, second);
	}

	@RequestMapping(value = "/divide/{first}/{second}", method = RequestMethod.GET)
	public @ResponseBody Double divide(@PathVariable Double first, @PathVariable Double second) {
		return restTemplate.getForObject(serverUrl + "/divide/{first}/{second}", Double.class, first, second);
	}

	@RequestMapping(value = "/minus/{first}/{second}", method = RequestMethod.GET)
	public @ResponseBody Integer minus(@PathVariable Integer first, @PathVariable Integer second) {
		return restTemplate.getForObject(serverUrl + "/minus/{first}/{second}", Integer.class, first, second);
	}

	@RequestMapping(value = "/wait/{seconds}", method = RequestMethod.GET)
	public @ResponseBody String waitSeconds(@PathVariable Integer seconds)
			throws InterruptedException {
		synchronized (lock) {
			TimeUnit.SECONDS.sleep(seconds);
		}
		return UUID.randomUUID().toString();
	}

}
