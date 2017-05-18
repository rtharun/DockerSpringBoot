package com.tharun.docker.springboot;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class EchoController {

	@RequestMapping(value = "/echo/{text}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public String pollCommandResult(@PathVariable String text) {
		return "Echo: " + text;
	}
}
