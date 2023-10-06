package edu.ifam.dra.chatfront.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.ifam.dra.chatfront.model.Contato;
import edu.ifam.dra.chatfront.service.ContatoService;

@RequestMapping("/contato")
@RestController
public class ContatoController {
	@Autowired
	ContatoService contatoService;
	
	@GetMapping
	ResponseEntity<Contato[]> getContatos() {
		Contato[] contatos = contatoService.getContatos();
		
		return ResponseEntity.ok(contatos);
	}
	
	@GetMapping("/{id}")
	ResponseEntity<Contato> getContato(@PathVariable Long id) {
		try {
			return ResponseEntity.ok(contatoService.getContato(id));
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@PostMapping
	ResponseEntity<Contato> setContato(@RequestBody Contato contato) {
		contatoService.setContato(contato);
		return ResponseEntity.created(null).body(null);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<Contato> setContato(@RequestBody Contato contato, @PathVariable Long id) {
		try {			
			contatoService.setContato(id, contato);
			return ResponseEntity.accepted().body(null);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<Contato> deleteContato(@PathVariable Long id) {
		try {
			contatoService.deleteContato(id);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
		} catch(Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
