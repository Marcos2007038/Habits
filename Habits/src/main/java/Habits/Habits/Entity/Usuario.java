package Habits.Habits.Entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


@Entity
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="apelido",nullable=true,unique=true,length=100)
	private String apelido;
	@Column(name="email",nullable=true,unique=true,length=150)
	private String email;
	@Column(name="senha",nullable=true,length=150)
	private String senha;
	@Column(name="role",nullable=true)
	private String role="_USER"; 
	
	@JsonManagedReference
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Tarefas> tarefas;
	
	
	

	public Usuario() {
		
	}
	
	

	
	
	
	public Usuario(Long id, String apelido, String email, String senha, String role, List<Tarefas> tarefas) {
		this.id = id;
		this.apelido = apelido;
		this.email = email;
		this.senha = senha;
		this.role = role;
		this.tarefas = tarefas;
	}






	public List<Tarefas> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefas> tarefas) {
		this.tarefas = tarefas;
	}
	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getApelido() {
		return apelido;
	}


	public void setApelido(String apelido) {
		this.apelido = apelido;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}
	
	
	
	
}
