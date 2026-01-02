package Habits.Habits.Repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Habits.Habits.DTO.UserConfigDTO;
import Habits.Habits.Entity.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	
	boolean existsByEmail(String email);
    boolean existsByApelido(String apelido);
    
    Optional<Usuario> findByEmail(String email);
    
    
    @Query(value = "SELECT apelido,email FROM usuario WHERE email=:email_logado",nativeQuery = true)
    UserConfigDTO PegarDadoConfig(@Param("email_logado") String email);
}
