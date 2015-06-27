package br.com.rbdutra.mars.rover;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class SampleController {
	
	@RequestMapping("/")
	@ResponseBody
	public String home() {
		return "Hello SpringBootson";
	}
	
	public static void main(String[] args) {
		SpringApplication.run(SampleController.class, args);
	}
}
