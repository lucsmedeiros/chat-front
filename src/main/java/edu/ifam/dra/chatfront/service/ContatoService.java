package edu.ifam.dra.chatfront.service;


import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import edu.ifam.dra.chatfront.model.Contato;

@Service
public class ContatoService {
	
	RestTemplateBuilder builder = new RestTemplateBuilder();
	RestTemplate restTemplate = builder.build();
	
	public Contato[] getContatos() {
		return restTemplate.getForObject("http://localhost:8080/contato", Contato[].class);
	}
	
	public Contato getContato(Long id) {
		return restTemplate.getForObject("http://localhost:8080/contato/" + id, Contato.class);
	}
	
	public void setContato(Contato contato) {
		HttpEntity<Contato> request = new HttpEntity<Contato>(contato);
		
		restTemplate.postForObject("http://localhost:8080/contato", request, Contato.class);
	}
	
	public void setContato(long id, Contato contato) {
		HttpEntity<Contato> request = new HttpEntity<Contato>(contato);
		
		restTemplate.exchange("http://localhost:8080/contato/" + id, HttpMethod.PUT, request, Void.class);
		
	}
	
	public void deleteContato(long id) {
		HttpEntity<Contato> request = new HttpEntity<Contato>(getContato(id));
		
		restTemplate.exchange("http://localhost:8080/contato/" + id, HttpMethod.DELETE, request, Void.class);
	}
}
