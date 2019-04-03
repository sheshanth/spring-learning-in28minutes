package com.example.demo.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;


@RestController
@RequestMapping("/users")
public class UserResource {
	
	@Autowired
	private UserDaoService userDaoService;

	@GetMapping("")
	public List<User> getAllUsers() {
		return userDaoService.findAllUsers();
	}

	@GetMapping("/{id}")
	public EntityModel<User> getUserById(@PathVariable(name = "id") int id) {
		User user = userDaoService.findOneById(id);
		System.out.println(user);
		if (user == null)
			throw new UserNotFoundException("id- " + id);		

		
		  EntityModel<User> entityModel = new EntityModel<User>(user);
		  
		  WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getAllUsers());
		  
		  entityModel.add(linkTo.withRel("all-users"));
		  
		  return entityModel;
		 
	}

	@PostMapping("/")
	public ResponseEntity<User> saveUser(@Valid @RequestBody User user) {
		User savedUser = userDaoService.saveUser(user);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		return ResponseEntity.created(location).build();
	}

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable int id) {
		User user = userDaoService.deleteUserById(id);
		if (user == null)
			throw new UserNotFoundException("No user found");
	}

}
