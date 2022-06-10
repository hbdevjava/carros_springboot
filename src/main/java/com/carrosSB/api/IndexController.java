package com.carrosSB.api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import org.springframework.web.bind.annotation.RequestMapping;


import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class IndexController {
	@GetMapping
	public String hello() {
	     return "API dos Carros";
	}
	//Para estudo
	/*@PostMapping("/login")//@RequestParam: le os parametro da query atraves da ?
	public String login(@RequestParam("login") String login,@RequestParam("senha") String senha) {
		return " login "  + login  + " senha " + senha;
	}
	
	@GetMapping("/login/{login}/{senha}")//@PathVariable: le os parametro da query atraves da /
	public String loginn(@PathVariable("login") String login,@PathVariable("senha") String senha) {
		return " login "  + login  + " senha " + senha;
	}
	
	@GetMapping("/carro/id/{id}")//http://localhost:8080/carro/id/1...
	public String getCarroById(@PathVariable("id") Long id) {
	     return "Carro by id " + id;
	}
	
	@GetMapping("/carro/tipo/{tipo}")//ex:http://localhost:8080/carro/tipo/1...
	public String getCarroByTipo(@PathVariable("tipo") String tipo) {
		return "lista by tipo " + tipo;
	} */
	
	
}
