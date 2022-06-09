package com.carrosSB.api;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrosSB.domain.Carro;
import com.carrosSB.domain.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	@Autowired 
	private CarroService service;
	
	@GetMapping
	public Iterable<Carro> get() {
	     return service.getCarros();
	}
	
	@GetMapping("/{id}")
	public Optional<Carro> get(@PathVariable Long id){
		return service.getcarroById(id);
	}
}
