package com.nelioalves.workshopmongo.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nelioalves.workshopmongo.domain.User;

@RestController //recurso rest
@RequestMapping(value = "/users") //caminho do end point
public class UserResource {

	@GetMapping
	public ResponseEntity<List<User>> findAll(){ // responseEntity do spring
		User maria = new User("1", "Maria brown", "maria@gmail.com");
		User alex = new User("2", "Alex green", "alex@gmail.com");
		
		List<User> list = new ArrayList<>();
		list.addAll(Arrays.asList(maria, alex));
		return ResponseEntity.ok().body(list);
		
		
		
	}
	
	
}
