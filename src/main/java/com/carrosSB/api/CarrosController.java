package com.carrosSB.api;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.servlet.Servlet;

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
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

		/*
		 * return carro.isPresent() ? ResponseEntity.ok(carro.get()) :
		 * ResponseEntity.notFound().build(); /* if(carro.isPresent()) { return
		 * ResponseEntity.ok(carro.get()); }else { return
		 * ResponseEntity.notFound().build(); }
		 */
		return carro.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());

	}

	@GetMapping("/tipo/{tipo}")
	public ResponseEntity getcarrosBytipo(@PathVariable("tipo") String tipo) {
		List<CarroDTO> carros = service.getCarroByTipo(tipo);

		return carros.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(carros);
	}

	// @RequestBody: pega o objeto completo, ele pega o Json do carro e converte
	// para um objeto desde que eles tenham os mesmos atributos;
	@PostMapping
	public ResponseEntity post(@RequestBody Carro carro) {

		try {
			CarroDTO c = service.insert(carro);

			URI location = getUri(c.getId());
			return ResponseEntity.created(null).build();
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}

	private URI getUri(Long id) {
		return ServletUriComponentsBuilder.fromCurrentRequest().path("/(id)").buildAndExpand(id).toUri();
	}

	@PutMapping("/{id}")
	public ResponseEntity  put(@PathVariable("id") Long id, @RequestBody Carro carro) {
		CarroDTO c = service.update(carro, id);

		return c != null?
				ResponseEntity.ok(c) : 
					ResponseEntity.notFound().build();	
	}

	@DeleteMapping("/{id}")
	public ResponseEntity delete(@PathVariable("id") Long id) {
		boolean ok = service.delete(id);

		return ok ?
				ResponseEntity.ok().build() : 
					ResponseEntity.notFound().build();
	}

}
