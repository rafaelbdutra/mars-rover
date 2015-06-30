package br.com.rbdutra.mars.rover;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.rbdutra.mars.rover.domain.Rover;
import br.com.rbdutra.mars.rover.domain.Surface;

@RestController
@EnableAutoConfiguration
public class SampleController {

	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Rover> create(@RequestBody Surface surface) {
		
		return new ArrayList<Rover>();
	}

	public static void main(String[] args) {
		SpringApplication.run(SampleController.class, args);
	}
}
