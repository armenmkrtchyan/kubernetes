package com.synisys.armen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author armen.mkrtchyan
 */
@RestController
@RequestMapping("/project")
public class ProjectController {

	@Autowired
	private VitessRepository vitessRepository;

	@RequestMapping(value = "/add/{name}", method = RequestMethod.GET)
	public void addProject(@PathVariable String name) {
		vitessRepository.insertProject(name);
	}

	@RequestMapping(value = "/delete/", method = RequestMethod.GET)
	public void addProject() {
		vitessRepository.deleteAllProject();
	}
}
