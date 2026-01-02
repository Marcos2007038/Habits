package Habits.Habits.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Habits.Habits.DTO.CadastrarUserDTO;
import Habits.Habits.DTO.MarcarHabitoDTO;
import Habits.Habits.DTO.TarefasDTO;
import Habits.Habits.Entity.Tarefas;
import Habits.Habits.Service.ValidarHomeService;
import Habits.Habits.Service.ValidarUsuarioService;

@RestController
public class LoginController {
	
	@Autowired
	private ValidarUsuarioService validarUsuarioService;
	@Autowired
	private ValidarHomeService validarHomeService;
	
	@PostMapping("/cadastrar")
	public Map<String, Object> CadastrarNovoUser(@RequestBody CadastrarUserDTO CadastrarDTO) {
	    return validarUsuarioService.CadastrarUsuario(CadastrarDTO);
	    
	} 
	
	
	@PostMapping("/cadastrarHabito")
	public Map<String, Object> CadastrarNovoHabito(@RequestBody TarefasDTO TarefaDTO,@AuthenticationPrincipal UserDetails userDetails){
		return validarHomeService.CadastrarHabito(TarefaDTO,userDetails);
	}
	
	
	
	@GetMapping("/ListarHabitos")
	public List<Tarefas> PegarHabitos(@AuthenticationPrincipal UserDetails userDetails){
		return validarHomeService.PegarHabito(userDetails);
	}
	
	
	@PostMapping("/Atualizar")
	public boolean MarcarConcluido(@RequestBody MarcarHabitoDTO marcarHabitoDTO){
		return validarHomeService.MarcarComoConcluido(marcarHabitoDTO);
	}
	
	
	@DeleteMapping("/Deletar/{id}")
	public boolean DeletarHabito(@PathVariable Long id) {
		return validarHomeService.ExcluirHabito(id);
	}
	
	
	
	
	@PostMapping("/GraficoPizza")
	public Map<String, Object> PegarDadosPizza(@AuthenticationPrincipal UserDetails userDetails,@RequestBody Map<String, String> requestBody){
		String filtroPizza = requestBody.get("filtroPizza");	
		return validarHomeService.PegarDadosPizza(userDetails.getUsername(), filtroPizza);
	}
	
	
	
	
	
	@GetMapping("/PegarDadoConfig")
	public Map<String,Object> PegarDadoConfig(@AuthenticationPrincipal UserDetails userDetails){
		return validarHomeService.PegarDadosUser(userDetails);
	}
	
	
	
	
	
	
}
