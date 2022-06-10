package com.carrosSB.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrosSB.domain.dto.CarroDTO;

@Service
public class CarroService {

	@Autowired
	private CarroRepository rep;

	public List<CarroDTO> getCarros() {
		return rep.findAll().stream().map(CarroDTO::new).collect(Collectors.toList());
		
	/*	List<Carro> carro = rep.findAll();

		List<CarroDTO> list = new ArrayList<>();
		for (Carro c : carro) {
			list.add(new CarroDTO(c));
		}*/

	}

	public List<CarroDTO> getCarroByTipo(String tipo) {
		return rep.findByTipo(tipo).stream().map(CarroDTO::new).collect(Collectors.toList());
		
		/*	List<Carro> carro = rep.findByTipo(tipo);

		List<CarroDTO> list = new ArrayList<>();
		for (Carro c : carro) {
			list.add(new CarroDTO(c));
		}

		return list; */

	}

	public Optional<CarroDTO> getcarroById(Long id) {
		return rep.findById(id).map(CarroDTO::new);
	}

	public Carro insert(Carro carro) {
		return rep.save(carro);

	}

	public Carro update(Carro carro) {

		return rep.save(carro);
	}

	public void delete(Long id) {
		
	 if(getcarroById(id).isPresent()) {
		 rep.deleteById(id);
	 }
		
	}

	/*
	 * public List<Carro> getCarrosFake() { List<Carro> carros = new ArrayList<>();
	 * 
	 * carros.add(new Carro(1L, "Fusca")); carros.add(new Carro(2L, "Brasilia"));
	 * carros.add(new Carro(3L, "Chevete"));
	 * 
	 * return carros; }
	 */

}
