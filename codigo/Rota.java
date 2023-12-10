package codigo;

public class Rota {

    private double quilometragem;
    private Data data;

    public Rota(double quilometragem, Data data) {
        this.quilometragem = quilometragem;
        this.data = data;
    }

    public double getQuilometragem() {
        return quilometragem;
    }

    public Data getData() {
        return data;
    }

    public String relatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("Data da Rota: ").append(data.dataFormatada());
        sb.append("\nQuilometragem: ").append(quilometragem).append(" km");

        return sb.toString();
    }
}