package Habits.Habits.Controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import Habits.Habits.DTO.CadastrarUserDTO;
import Habits.Habits.Service.ValidarUsuarioService;

@RestController
public class LoginController {
	
	@Autowired
	private ValidarUsuarioService validarUsuarioService;
	
	@PostMapping("/cadastrar")
	public Map<String, Object> CadastrarNovoUser(@RequestBody CadastrarUserDTO CadastrarDTO) {
	    return validarUsuarioService.CadastrarUsuario(CadastrarDTO);
	    
	}
	
}
