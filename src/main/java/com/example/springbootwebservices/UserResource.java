package com.example.springbootwebservices;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
	@Autowired
	MessageSource resource;
	
	
	@Autowired
	UserDaoService service;
	
	
	@GetMapping("/user/{id}")
	public EntityModel<User> getUser(@PathVariable int id) {
		User user=service.getUser(id);
		if(null==user.getId())
			throw new UserNotFoundException("Id= "+id);
		
		EntityModel<User> model=EntityModel.of(user);
		
		
		WebMvcLinkBuilder builder=linkTo(methodOn(this.getClass()).findAll());
		model.add(builder.withRel("all-users"));
		
		
		return model;
	}
	
	@GetMapping(path = "/internationalized")
	public String hello(@RequestHeader(name = "Accept-Language",required = false) Locale local) {
		return resource.getMessage("key", null,LocaleContextHolder.getLocale());
	}
	
	
	@GetMapping("/user")
	public List<User> findAll(){
		
		return service.findAll();
		
	}
	
	@PostMapping("/user")
	public ResponseEntity<Object> saveuser(@Valid @RequestBody User data){
		
		User ss=service.save(data);
		
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(ss.getId()).toUri() ;
		
		return ResponseEntity.created(location).build();
		 
	}

}
