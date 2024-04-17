import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Principal {
    public static void main(String[] args) {
//        Filme meuFilme = new Filme("O poderoso chefão");
//        meuFilme.setAnoDeLancamento(1970);
//        meuFilme.setDuracaoEmMinutos(180);
//        System.out.println("Duração do filme: " + meuFilme.getDuracaoEmMinutos());
//
//        meuFilme.exibeFichaTecnica();
//        meuFilme.avalia(8);
//        meuFilme.avalia(5);
//        meuFilme.avalia(10);
//        System.out.println("Total de avaliações: " + meuFilme.getTotalDeAvaliacoes());
//        System.out.println(meuFilme.pegaMedia());
//        //meuFilme.somaDasAvaliacoes = 10;
//        //meuFilme.totalDeAvaliacoes = 1;
//        //System.out.println(meuFilme.pegaMedia());
//
//        Serie serieAlexsandro = new Serie("Prison Break");
//
//
//        Serie lost = new Serie("Lost");
//        lost.setAnoDeLancamento(2000);
//        lost.exibeFichaTecnica();
//        lost.setTemporadas(10);
//        lost.setEpisodiosPorTemporada(10);
//        lost.setMinutosPorEpisodio(50);
//        System.out.println("Duração para maratonar Lost: " + lost.getDuracaoEmMinutos());
//
//        Filme outroFilme = new Filme("Avatar");
//        outroFilme.setAnoDeLancamento(2023);
//        outroFilme.setDuracaoEmMinutos(200);
//
//        CalculadoraDeTempo calculadora = new CalculadoraDeTempo();
//        calculadora.inclui(meuFilme);
//        calculadora.inclui(outroFilme);
//        calculadora.inclui(lost);
//        System.out.println(calculadora.getTempoTotal());
//
//        FiltroRecomendacao filtro = new FiltroRecomendacao();
//        filtro.filtra(meuFilme);
//
//        Episodio episodio = new Episodio();
//        episodio.setNumero(1);
//        episodio.setSerie(lost);
//        episodio.setTotalVisualizacoes(300);
//        filtro.filtra(episodio);

        ArrayList<String> lista = new ArrayList<>();
        lista.add("Alexsandro");
        lista.add("Matias");
        lista.add("Almeida");

        Collections.sort(lista);

        System.out.println(lista);
    }
}