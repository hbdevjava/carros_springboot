package com.carrosSB.api;



import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carrosSB.domain.Carro;
import com.carrosSB.domain.CarroService;

@RestController
@RequestMapping("/api/v1/carros")
public class CarrosController {
	private CarroService service = new CarroService ();
	
	@GetMapping
	public List<Carro> get() {
	     return service.getCarros();
	}
}
