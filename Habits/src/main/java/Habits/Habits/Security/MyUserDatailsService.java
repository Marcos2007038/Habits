package Habits.Habits.Security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Habits.Habits.Entity.Usuario;
import Habits.Habits.Repository.UsuarioRepository;

@Service
public class MyUserDatailsService implements UserDetailsService{
	 @Autowired
	 private UsuarioRepository Repository;
	 
	 @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

	        // Opção 1: se você usa o campo "username" na entidade (mais comum)
		 Usuario usuario = Repository.findByEmail(username)
			        .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));


	        // Opção 2: se você mudou o campo para "email" na entidade, use isso:
	        // Usuario usuario = repository.findByEmail(username)
	        //         .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

	        return new User(
	                usuario.getEmail(),           // ← aqui era "usuario" com minúscula!
	                usuario.getSenha(),           // ← corrigido também
	                AuthorityUtils.createAuthorityList("ROLE_" + usuario.getRole())
	        );
	    }
}
