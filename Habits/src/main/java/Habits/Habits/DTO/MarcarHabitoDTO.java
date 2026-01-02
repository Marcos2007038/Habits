package Habits.Habits.DTO;

public class MarcarHabitoDTO {
	
	private Long id;
	private boolean concluido;
	
	
	
	public MarcarHabitoDTO(Long id, boolean concluido) {
		this.id = id;
		this.concluido = concluido;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public boolean isConcluido() {
		return concluido;
	}



	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}
	
	
	
	
}
