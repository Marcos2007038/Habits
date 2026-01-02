package Habits.Habits.DTO;

public class TarefaGraficoDTO {

    private String tag;
    private String cor;
    private Long count;

    public TarefaGraficoDTO(String tag, String cor, Long count) {
        this.tag = tag;
        this.cor = cor;
        this.count = count;
    }

    // Getters and Setters
    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TarefaGraficoDTO{" +
               "tag='" + tag + '\'' +
               ", cor='" + cor + '\'' +
               ", count=" + count +
               '}';
    }
}
