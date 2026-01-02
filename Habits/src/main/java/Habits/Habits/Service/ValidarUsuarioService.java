package Habits.Habits.Service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Habits.Habits.DTO.CadastrarUserDTO;
import Habits.Habits.Entity.Usuario;
import Habits.Habits.Repository.UsuarioRepository;



@Service
public class ValidarUsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	public Map<String, Object> CadastrarUsuario(CadastrarUserDTO cadastroDTO){
		
		try {
			
			System.out.println(cadastroDTO.toString());
			
			/*Validando os dados para cadasatro do userDTO*/
			if(!cadastroDTO.ValidarDados()){
		        return Map.of(
		        	"status", false,
		        	"message", "Formato invalido dos dados para cadastrar!"
			    );
			}
			
			/*Verfiando se existe no banco de dados*/
			String VerificarExist=VerificarUserDB(cadastroDTO);
			if(!VerificarExist.equals("OK")) {
				
				 return Map.of(
				      "status", false,
				      "message", VerificarExist
				 );
				
			}
			
			
			/*Crindo um novo usuário*/
			Usuario user =new Usuario();
			System.out.println("dnfjdnjfnjk");
			user.setApelido(cadastroDTO.getApelido());
			user.setEmail(cadastroDTO.getEmail());
			user.setSenha(passwordEncoder.encode(cadastroDTO.getSenha()));
			usuarioRepository.save(user);
			
			
			return Map.of(
	        	"status", true,
	        	"message", "Usuario Cadastrado com sucesso!"
			 );
			
			
		} catch (Exception e) {
			return Map.of(
	        	"status", false,
	        	"message", "Erro no sistema, por favor aguarde"
			 );
		}
		
	}
	
	
	public String VerificarUserDB(CadastrarUserDTO cadastroDTO) {
	    if(usuarioRepository.existsByApelido(cadastroDTO.getApelido())) {
	        return "Já existe usuário com esse apelido";
	    }

	    if(usuarioRepository.existsByEmail(cadastroDTO.getEmail())) {
	        return "Esse email já foi cadastrado";
	    }
	    return "OK";
	}

		
	
	
	
	
}
