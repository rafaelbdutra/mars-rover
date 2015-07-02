package br.com.rbdutra.mars.rover.resource;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import br.com.rbdutra.mars.rover.domain.Command;
import br.com.rbdutra.mars.rover.domain.Position;
import br.com.rbdutra.mars.rover.domain.Rover;
import br.com.rbdutra.mars.rover.domain.Rover.FaceDirection;
import br.com.rbdutra.mars.rover.domain.Surface;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = MarsRoverResource.class)
@WebIntegrationTest("server.port=9000")
public class SurfaceResourceTest {

	String resourceHost = "http://localhost:9000/mars-rover";

	RestTemplate restTemplate = new RestTemplate();

	MarsRoverParam sucessParam;

	MarsRoverParam colisionParam;

	@Before
	public void setUp() {

		// sucess
		Surface sucessSurface = new Surface(10, 10);
		Rover sucessRover = new Rover(new Position(3, 1), FaceDirection.W);
		sucessRover.setCommands(Arrays.asList(Command.M, Command.R, Command.M,
				Command.M));

		sucessParam = new MarsRoverParam();
		sucessParam.setSurface(sucessSurface);
		sucessParam.setRovers(Arrays.asList(sucessRover));

		// colision
		Surface colisionSurface = new Surface(10, 10);
		Rover parkedRover = new Rover(new Position(3, 1), FaceDirection.W);

		Rover coliderRover = new Rover(new Position(4, 1), FaceDirection.W);
		coliderRover.setCommands(Arrays.asList(Command.M));

		colisionParam = new MarsRoverParam();
		colisionParam.setSurface(colisionSurface);
		colisionParam.setRovers(Arrays.asList(parkedRover, coliderRover));
	}

	@Test
	public void testSucessCall() {

		try {

			ResponseEntity<Rover[]> response = restTemplate.postForEntity(
					resourceHost, getHttpEntity(sucessParam), Rover[].class);

			Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
			Assert.assertEquals(new Position(2, 3),
					response.getBody()[0].getPosition());
			Assert.assertEquals(FaceDirection.N,
					response.getBody()[0].getFaceDirection());

		} catch (RestClientException e) {
			Assert.fail(e.toString());
		}
	}

	@Test
	public void testColisionErrorCall() {

		try {

			restTemplate.postForEntity(resourceHost,
					getHttpEntity(colisionParam), Rover[].class);

			Assert.fail();
		} catch (RestClientException e) {
			Assert.assertTrue(e instanceof HttpServerErrorException);
		}
	}

	private HttpEntity<MarsRoverParam> getHttpEntity(MarsRoverParam param) {

		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setContentType(MediaType.APPLICATION_JSON);
		httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

		return new HttpEntity<MarsRoverParam>(param, httpHeaders);
	}
}
