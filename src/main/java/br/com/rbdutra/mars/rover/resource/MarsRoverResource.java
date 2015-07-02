package br.com.rbdutra.mars.rover.resource;

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
public class MarsRoverResource {

	@RequestMapping(value = "/mars-rover", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<Rover> create(@RequestBody MarsRoverParam params) {

		Surface surface = params.getSurface();
		List<Rover> rovers = params.getRovers();
		
		surface.addRovers(rovers);
		applyCommandsToRovers(rovers);

		return rovers;
	}

	private void applyCommandsToRovers(List<Rover> rovers) {

		for (Rover rover : rovers)
			rover.applyCommands();
	}

	public static void main(String[] args) {
		SpringApplication.run(MarsRoverResource.class, args);
	}
}
