package com.carrosSB.api;

import java.util.List;
import java.util.Optional;

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

import com.carrosSB.domain.Carro;
import com.carrosSB.domain.CarroService;
import com.carrosSB.domain.dto.CarroDTO;

@RestController
@RequestMapping("/api/v1/carros")
//ex:http://localhost:8080/api/v1/carros/23 
//ex:http://localhost:8080/api/v1/carros/tipo/luxo 
public class CarrosController {
	@Autowired
	private CarroService service;

	@GetMapping
	public ResponseEntity<List<CarroDTO>> get() {
		return ResponseEntity.ok(service.getCarros());
		// return new ResponseEntity<>(service.getCarros(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity get(@PathVariable("id") Long id) {
		Optional<CarroDTO> carro = service.getcarroById(id);

		/*	return carro.isPresent() ? 
				ResponseEntity.ok(carro.get()) : 
				ResponseEntity.notFound().build();
		/*
		 * if(carro.isPresent()) { return ResponseEntity.ok(carro.get()); }else { return
		 * ResponseEntity.notFound().build(); }
		 */
		 return carro.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity <List<CarroDTO>> getcarrosBytipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = service.getCarroByTipo(tipo);
		
		return carros.isEmpty() ? ResponseEntity.noContent().build() : 
			ResponseEntity.ok(carros);
	}

	// @RequestBody: pega o objeto completo, ele pega o Json do carro e converte
	// para um objeto desde que eles tenham os mesmos atributos;
	@PostMapping
	public String post(@RequestBody Carro carro) {
		Carro c = service.insert(carro);

		return "Carro salvo com sucesso: " + c.getId();
	}

	@PutMapping("/{id}")
	public String put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		Carro c = service.update(carro);

		return "Carro Atualizado com sucesso: " + c.getId();
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);

		return "Carro Deletado com sucesso";
	}

	
	

}
