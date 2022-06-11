package com.carrosSB.domain;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carrosSB.domain.dto.CarroDTO;

import junit.framework.Assert;

@Service
public class CarroService {

	@Autowired
	private CarroRepository rep;

	public List<CarroDTO> getCarros() {
		return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());

		/*
		 * List<Carro> carro = rep.findAll();
		 * 
		 * List<CarroDTO> list = new ArrayList<>(); for (Carro c : carro) { list.add(new
		 * CarroDTO(c)); }
		 */

	}
	
	public Optional<CarroDTO> getcarroById(Long id) {
		return rep.findById(id).map(CarroDTO::create);
	}
	
	
	

	public List<CarroDTO> getCarroByTipo(String tipo) {
		return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());

		/*
		 * List<Carro> carro = rep.findByTipo(tipo);
		 * 
		 * List<CarroDTO> list = new ArrayList<>(); for (Carro c : carro) { list.add(new
		 * CarroDTO(c)); }
		 * 
		 * return list;
		 */

	}


	public CarroDTO insert(Carro carro) {
		
		return CarroDTO.create(rep.save(carro));

	}

	public CarroDTO update(Carro carro, Long id) {
		
		Optional<Carro> optional = rep.findById(id);
		if(optional.isPresent()) {
			Carro db = optional.get();
			db.setNome(carro.getNome());
			db.setTipo(carro.getTipo());
			System.out.println("Carro id" + db.getId());
			
			rep.save(db);
			
			return CarroDTO.create(db);
		}else {
			throw new RuntimeException("Nao é possivel atualizar o registro");
		}
		
	}

	
	public boolean delete(Long id) {

		if (getcarroById(id).isPresent()) {
			rep.deleteById(id);
			return true;
		}
		return false;
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
