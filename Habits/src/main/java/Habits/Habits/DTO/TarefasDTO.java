package Habits.Habits.DTO;


public class TarefasDTO {


	private String titulo;
	private String tag;
	private String descricao;
	private String data;
	private boolean concluido;
	private String cor;
	private boolean repetir;

	public TarefasDTO() {
		
	}
	
	public TarefasDTO(String titulo, String tag, String descricao, String data, boolean concluido,String cor,boolean repetir) {
		this.titulo = titulo;
		this.tag = tag;
		this.descricao = descricao;
		this.data = data;
		this.concluido = concluido;
		this.cor=cor;
		this.repetir=repetir;
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


	public String getData() {
		return data;
	}


	public void setData(String data) {
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

	@Override
	public String toString() {
		return "TarefasDTO [titulo=" + titulo + ", tag=" + tag + ", descricao=" + descricao + ", data=" + data
				+ ", concluido=" + concluido + ", cor=" + cor + ", repetir=" + repetir + "]";
	}
	
	
	
	public  boolean ValidarDados() {
		if(this.titulo.isEmpty() || this.tag.isEmpty()) {
			return false;
		}
		
		if(this.descricao.isEmpty() || this.data.isEmpty()) {
			return false;
		}
		if(this.cor.isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	
}
