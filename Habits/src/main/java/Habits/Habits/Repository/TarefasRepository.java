package Habits.Habits.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Habits.Habits.DTO.TarefaGraficoDTO;
import Habits.Habits.Entity.Tarefas;

public interface TarefasRepository  extends JpaRepository<Tarefas, Long>{
	


	@Query(value = "SELECT * FROM tarefas p WHERE  p.data = :dataAtual AND p.repetir=false  AND p.usuario_id = (SELECT use.id FROM usuario use WHERE use.email = :email_logado)ORDER BY p.id ASC;  ", nativeQuery = true)
	  List<Tarefas> pegarHabito(@Param("dataAtual") LocalDate dataAtual,@Param("email_logado") String email_logado);
	
	
	@Query(value = "SELECT * FROM tarefas p WHERE  p.data = :dataAtual AND p.repetir=true  AND p.usuario_id = (SELECT use.id FROM usuario use WHERE use.email = :email_logado)ORDER BY p.id ASC;  ", nativeQuery = true)
	  List<Tarefas> pegarHabitoRepetido(@Param("dataAtual") LocalDate dataAtual,@Param("email_logado") String email_logado);
	
	
	@Modifying
	@Query(value = "UPDATE tarefas SET concluido=:status WHERE id=:id_tarefa ", nativeQuery = true)
	int marcarConcluido(@Param("status") boolean status,@Param("id_tarefa") Long tarefaAtualizar);	
	
	@Modifying
	@Query(value = "DELETE FROM tarefas WHERE id=:id_tarefa", nativeQuery = true)
	int excluirTarefa(@Param("id_tarefa") Long tarefaExcluir);	
	
	
	
	
	
	@Query(value = "SELECT tag, cor, COUNT(id) " +
            "FROM tarefas " +
            "WHERE data >= :dataFiltro AND data <= :dataAtual AND concluido=true " +
            "AND usuario_id = (SELECT use.id FROM usuario use WHERE use.email = :email_logado) " +
            "GROUP BY tag, cor", nativeQuery = true)
List<TarefaGraficoDTO> PegarDadosGrafico(@Param("dataFiltro") LocalDate dataFiltro,
                                      @Param("dataAtual") LocalDate dataAtual,
                                      @Param("email_logado") String email_logado);
	
	
	

}















