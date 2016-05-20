package com.synisys.armen;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author armen.mkrtchyan
 */
@RestController
@RequestMapping("/calc")
public class MainController {

	private UUID uuid = UUID.randomUUID();
	private AtomicInteger counter = new AtomicInteger(0);

	@RequestMapping(value = "/count", method = RequestMethod.GET)
	public @ResponseBody String count() {
		return uuid.toString() + " / " + counter.incrementAndGet();
	}

	@RequestMapping(value = "/sum/{first}/{second}", method = RequestMethod.GET)
	public @ResponseBody Integer sum(@PathVariable Integer first, @PathVariable Integer second) {
		return first + second;
	}

	@RequestMapping(value = "/multiply/{first}/{second}", method = RequestMethod.GET)
	public @ResponseBody Integer multiply(@PathVariable Integer first, @PathVariable Integer second) {
		return first * second;
	}

	@RequestMapping(value = "/divide/{first}/{second}", method = RequestMethod.GET)
	public @ResponseBody Double divide(@PathVariable Double first, @PathVariable Double second) {
		return first / second;
	}

	@RequestMapping(value = "/minus/{first}/{second}", method = RequestMethod.GET)
	public @ResponseBody Integer minus(@PathVariable Integer first, @PathVariable Integer second) {
		return first - second;
	}

}
