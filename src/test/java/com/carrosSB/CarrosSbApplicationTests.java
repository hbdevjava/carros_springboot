package com.carrosSB;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.carrosSB.domain.Carro;
import com.carrosSB.domain.CarroService;
import com.carrosSB.domain.dto.CarroDTO;

@RunWith(SpringRunner.class)
@SpringBootTest
class CarrosSbApplicationTests {
	
	@Autowired
	private CarroService service;

	@Test
	public void teste1() {
		Carro carro = new Carro();
		carro.setNome("Ferrari");
		carro.setTipo("esportivos");
		
		CarroDTO c = service.insert(carro);
		assertNotNull(c);
		
		Long id = c.getId();
		assertNotNull(id);
		
		//Busca o objeto
		Optional<CarroDTO> op = service.getcarroById(id);
		assertTrue(op.isPresent());
		
		c = op.get(); 
		
		
		
		
		
		
		
		
		
		
		
	}

	@Test
	public void test2() {
	}

}
