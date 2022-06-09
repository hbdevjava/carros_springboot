package com.carrosSB.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service 
public class CarroService {
	
	@Autowired
	private CarroRepository rep;
	
	public Iterable<Carro> getCarros(){
		return rep.findAll();
	}
	
	public Iterable<Carro> getCarroByTipo(String tipo) {
		
		return rep.findByTipo(tipo);
	}
	
	public Optional<Carro> getcarroById(Long id){
		return rep.findById(id);
	}
	
	
	public List<Carro> getCarrosFake(){
		List<Carro> carros = new ArrayList<>();
		
		carros.add(new Carro (1L, "Fusca"));
		carros.add(new Carro (2L, "Brasilia"));
		carros.add(new Carro (3L, "Chevete"));
		
		
		
		return carros;
	}




	
	
	
	
	





















}
