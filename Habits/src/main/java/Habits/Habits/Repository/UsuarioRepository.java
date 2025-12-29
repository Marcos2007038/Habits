package Habits.Habits.Repository;


import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import Habits.Habits.Entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario,Long>{
	
	boolean existsByEmail(String email);
    boolean existsByApelido(String apelido);
    
    Optional<Usuario> findByEmail(String email);
	
}
