package com.carrosSB.api;




import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrosSB.domain.Carro;
import com.carrosSB.domain.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
//ex:http://localhost:8080/api/v1/carros/23 
//ex: localhost:8080/api/v1/carros/tipo/luxo 
public class CarrosController {
	@Autowired 
	private CarroService service;
	
	@GetMapping
	public Iterable<Carro> get() {
	     return service.getCarros();
	}
	
	@GetMapping("/{id}")
	public Optional<Carro> get(@PathVariable ("id") Long id){
		return service.getcarroById(id);
	}
	
	@GetMapping("/tipo/{tipo}")
	public Iterable<Carro> getcarrosBytipo(@PathVariable ("tipo") String tipo){
		return service.getCarroByTipo(tipo);
	}
	
	
	//@RequestBody: pega o objeto completo, ele pega o Json do carro e converte 
	//para um objeto desde que eles tenham os mesmos atributos;
	@PostMapping
	public String post(@RequestBody Carro carro) {
		Carro c = service.insert(carro);
		
		
		return "Carro salvo com sucesso: " + c.getId();
	}
	
	@PutMapping("/{id}")
	public String put(@PathVariable ("id") Long id, @RequestBody Carro carro ){
		Carro c = service.update(carro);
		
		return "Carro Atualizado com sucesso: " + c.getId();
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable ("id") Long id) {
		service.delete(id);
		
		return "Carro Deletado com sucesso";
	}
	
	
	
	
	
	
}
