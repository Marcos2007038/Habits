package Habits.Habits.DTO;


public class CadastrarUserDTO {
	
	private String apelido;
	private String email;
	private String senha;
	private String senhaConfirm;
	
	public CadastrarUserDTO() {
		
	}
	
	public CadastrarUserDTO(String apelido, String email, String senha, String senhaConfirm) {
		this.apelido = apelido;
		this.email = email;
		this.senha = senha;
		this.senhaConfirm = senhaConfirm;
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

	public String getSenhaConfirm() {
		return senhaConfirm;
	}

	public void setSenhaConfirm(String senhaConfirm) {
		this.senhaConfirm = senhaConfirm;
	}
	
	
	
	
	
	public boolean ValidarDados() {
		
		if(this.apelido.length() <8) {
			return false;
		}
		
		if(!this.email.matches("^[A-Za-z0-9._%+-]+@gmail\\.com$")) {
			return false;
		}
		
		if(!this.senha.equals(this.senhaConfirm) || this.senha.length() < 8) {
			return false;
		}
		
		
		return true;
	}
	
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "apelodo:"+this.apelido+"\nemail:"+this.email+"\nsenha:"+this.senha+"\nsenhaConfirm:"+this.senhaConfirm;
	}
}	
