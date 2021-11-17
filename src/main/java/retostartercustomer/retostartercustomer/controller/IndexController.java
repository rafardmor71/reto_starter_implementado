package retostartercustomer.retostartercustomer.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import retostarter.retostarter.configuration.Status;

@RestController
public class IndexController {

	@Autowired
	private Status status;

	private RestTemplate template = new RestTemplate();
	private String url = "http://localhost:8080/";

	@GetMapping(path = "/status")
	public List<String> nextStatus() {
	
			String urlTemplate = url + "actuator/estados";
			List<String> estadosRespuesta = new ArrayList<>();
			ResponseEntity<String[]> responseEntity = template.getForEntity(urlTemplate, String[].class);
			List<String> statusList = Arrays.asList(responseEntity.getBody());
			String status_open = statusList.get(0);
			System.out.println(statusList.get(1));
			for (int i = 0; i < statusList.size(); i++) {
				 estadosRespuesta = status.nextStatus(statusList.get(i));
			}
			
	
		return estadosRespuesta;

	}
}
