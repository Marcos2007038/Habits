package Habits.Habits.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Tarefas {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name="titulo",nullable=true,length=100)
	private String titulo;
	@Column(name="tag",nullable=true,length=100)
	private String tag;
	@Column(name="descricao",nullable=true,length=255)
	private String descricao;
	@Column(name="data",nullable=true)
	private LocalDate data;
	@Column(name="concluido",nullable=true)
	private boolean concluido;
	@Column(name="cor",nullable=true,length=50)
	private String cor;
	@Column(name="repetir",nullable=true)
	private boolean repetir;
	
	@ManyToOne
	@JsonBackReference
    @JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	public Tarefas() {
		
	}
	
	
	
	

	public Tarefas(String titulo, String tag, String descricao, LocalDate data, boolean concluido, String cor,
			boolean repetir, Usuario usuario) {
		this.titulo = titulo;
		this.tag = tag;
		this.descricao = descricao;
		this.data = data;
		this.concluido = concluido;
		this.cor = cor;
		this.repetir = repetir;
		this.usuario = usuario;
	}





	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getTag() {
		return tag;
	}


	public void setTag(String tag) {
		this.tag = tag;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	public boolean isConcluido() {
		return concluido;
	}


	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public boolean isRepetir() {
		return repetir;
	}

	public void setRepetir(boolean repetir) {
		this.repetir = repetir;
	}
	
	
	
	
}
