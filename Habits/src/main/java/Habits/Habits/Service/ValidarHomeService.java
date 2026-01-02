package Habits.Habits.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import Habits.Habits.DTO.MarcarHabitoDTO;
import Habits.Habits.DTO.TarefaGraficoDTO;
import Habits.Habits.DTO.TarefasDTO;
import Habits.Habits.DTO.UserConfigDTO;
import Habits.Habits.Entity.Tarefas;
import Habits.Habits.Entity.Usuario;
import Habits.Habits.Repository.TarefasRepository;
import Habits.Habits.Repository.UsuarioRepository;
import jakarta.transaction.Transactional;

@Service
public class ValidarHomeService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	@Autowired
	private TarefasRepository tarefasRepository;

	
	public Map<String, Object> CadastrarHabito(TarefasDTO tarefasDTO,UserDetails userDetails){
		
		try {
			
			
			System.out.println(!tarefasDTO.ValidarDados());
			if(!tarefasDTO.ValidarDados()) {
				System.out.println(tarefasDTO.toString());
				return Map.of(
					"status", false,
		        		"message", "Erro ao tentar cadastrar um novo habito!!!"
				);
			}
			
			 
			 Usuario userLogado = usuarioRepository.findByEmail(userDetails.getUsername())
		                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
			
			
			Tarefas tarefaUser=new Tarefas();
			tarefaUser.setTitulo(tarefasDTO.getTitulo());
			tarefaUser.setTag(tarefasDTO.getTag());
			tarefaUser.setDescricao(tarefasDTO.getDescricao());
			tarefaUser.setData(LocalDate.parse(tarefasDTO.getData()));
			tarefaUser.setConcluido(tarefasDTO.isConcluido());
			tarefaUser.setCor(tarefasDTO.getCor());
			tarefaUser.setRepetir(tarefasDTO.isRepetir());
			tarefaUser.setUsuario(userLogado);
			
			tarefasRepository.save(tarefaUser);
			
			return Map.of(
				"status", true,
	        		"message", "Cadastrado com sucesso!!!"
			);
					
			}catch (Exception e) {
			return Map.of(
				"status", false,
	        		"message", "Erro ao tentar cadastrar um novo habito no sistema!!!"
			);
		}
			
		
			
			
			
		
		
		
		
		 
	}
	
	
	
	
	public List<Tarefas> PegarHabito(UserDetails userDetails) {

	    List<Tarefas> habitoDiario = tarefasRepository.pegarHabito(LocalDate.now(), userDetails.getUsername());
	    

	    List<Tarefas> habitoDiarioRepetido = tarefasRepository.pegarHabitoRepetido(LocalDate.now(), userDetails.getUsername());
	    

	    List<Tarefas> JoinHabits = new ArrayList<>();
	    
	    JoinHabits.addAll(habitoDiario);
	    JoinHabits.addAll(habitoDiarioRepetido);
	    
	    return JoinHabits;
	}

	
	 
	@Transactional 
	public boolean MarcarComoConcluido(MarcarHabitoDTO marcarHabitoDTO){
		int res = tarefasRepository.marcarConcluido(marcarHabitoDTO.isConcluido(), marcarHabitoDTO.getId());
	    if (res == 0) {
	        // Nenhum registro foi atualizado, podemos lançar uma exceção ou retornar um erro
	        throw new RuntimeException("Nenhum registro foi atualizado. Verifique se o ID está correto.");
	    }
	    return true;
	
	}
	
	
	@Transactional 
	public boolean ExcluirHabito(Long id) {
		int rest = tarefasRepository.excluirTarefa(id);
		if(rest==0) {
			 throw new RuntimeException("Nenhum registro foi excluido. Verifique se o ID está correto.");
		}
		return true;
	}
	
	
	
	
	
	public Map<String, Object> PegarDadosPizza(String userDetails,String filtro){
			

		List<String> dataFiltro=new ArrayList<String>();
		dataFiltro.add("todos");dataFiltro.add("dia");
		dataFiltro.add("semana");dataFiltro.add("mes");
		dataFiltro.add("ano");
		
		LocalDate dataAtual=LocalDate.now();
		
		Object[] plusDate= {
				LocalDate.of(2000, 1, 1),
				dataAtual.minusDays(1),
				dataAtual.minusDays(7),
				dataAtual.minusMonths(1),
				dataAtual.minusYears(1)
				};
		
		int index=dataFiltro.indexOf(filtro);
	
		System.out.println(LocalDate.parse(plusDate[index].toString()));
		
		LocalDate DataPrevista=LocalDate.parse(plusDate[index].toString());
		
		List<TarefaGraficoDTO> obj=tarefasRepository.PegarDadosGrafico(DataPrevista,LocalDate.now(),userDetails);
		

		if(obj.size()==0) {
			return Map.of(
					 
				"labels",false,
				"status","nenhum dado encontrado"
					
			);
		}
		
		
		List<String> tags =new ArrayList<String>();
		List<Long> quantidade =new ArrayList<Long>();
		List<String> cores =new ArrayList<String>();
		
		for (TarefaGraficoDTO t : obj) {
			tags.add(t.getTag());
			quantidade.add(t.getCount());
			cores.add(t.getCor());
		}
		
		
	
		return Map.of(
				 
				"labels",tags,
				"data",quantidade,
				"backgroundColor",cores
				
				);
	
		
	}
	
	
	
	
	
	
	
	
	
	
	public Map<String,Object> PegarDadosUser(UserDetails userDetails){
		try {
			
		UserConfigDTO userTarefaDTO= usuarioRepository.PegarDadoConfig(userDetails.getUsername());
		

			System.out.println(userTarefaDTO.toString());
		
			return Map.of(
				"Apelido",userTarefaDTO.getApelido(),
				"Email",userTarefaDTO.getEmail()
			);
		
		} catch (Exception e) {
			return Map.of(
				"Apelido","Erro",
				"Email","Erro"
			);
		}
	}
	
	
	
	
}
	
	
	
	
	
	
