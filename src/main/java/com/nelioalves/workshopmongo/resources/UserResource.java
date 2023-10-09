package com.nelioalves.workshopmongo.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nelioalves.workshopmongo.domain.User;
import com.nelioalves.workshopmongo.dto.UserDTO;
import com.nelioalves.workshopmongo.services.UserService;

@RestController //recurso rest
@RequestMapping(value = "/users") //caminho do end point
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> findAll(){ // responseEntity do spring
		
		List<User> list = service.findAll();
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList()); //expressao lambda
		return ResponseEntity.ok().body(listDto);	
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<UserDTO> findById(@PathVariable String id){ // responseEntity do spring
		
		User obj = service.findById(id);
		return ResponseEntity.ok().body(new UserDTO(obj));	
	}
	
	@PostMapping
	public ResponseEntity<Void> insert(@RequestBody UserDTO objDto){ // responseEntity do spring
		User obj = service.fromDTO(objDto); //dto convertido para user
		obj = service.insert(obj);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri(); // ele pega o endereco do novo objeto que inserimos
		return ResponseEntity.created(uri).build(); //created retorna o codigo 201, que e o codigo de resposta http de quando se cria um recurso
	}
}
