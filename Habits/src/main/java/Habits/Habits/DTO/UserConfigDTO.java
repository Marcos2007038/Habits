package Habits.Habits.DTO;

public class UserConfigDTO {
	
	private String apelido;
	private String email;
	
	
	
	public UserConfigDTO(String apelido, String email) {
		this.apelido = apelido;
		this.email = email;
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
	
	
	

}
