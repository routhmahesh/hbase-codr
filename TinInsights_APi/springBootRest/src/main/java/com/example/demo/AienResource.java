package com.example.demo;

import java.util.List;
import java.util.Optional;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AienResource {
	@Autowired
	AlienRepository repo;
	
	@GetMapping("/aliens")
	public List<Alien> getAliens(){
	
		
		List<Alien> a = (List<Alien>) repo.findAll();
				
		
		return a ;
		
	}
	
	@GetMapping(value = "/alien/{id}")
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	public Alien getAlien(@PathVariable int id){
	     
		Optional<Alien>  a =   repo.findById(id);
		
		Alien b = a.get();
				
		return b ;
		
	}
	
	@PostMapping(value = "/aliens")
	public List<Alien> postAlien(@RequestBody List<Alien> alienList ){
		
		List<Alien> a = (List<Alien>) repo.saveAll(alienList);
	
		return a;
	
	
	}
	
	@PutMapping(value = "/alien/{id}")
    public Alien puttAlien(@RequestBody Alien alien,@PathVariable int id){
		
		Alien existAlien = getAlien(id);
	              
		Alien saved = (Alien) repo.save(alien);
		
		return saved;	
	}
	
	@DeleteMapping("/alien/{id}")
	public void delete(@PathVariable Integer id) {
		Alien existAlien = getAlien(id);
	    repo.delete(existAlien);
	}
	
	
	
}
