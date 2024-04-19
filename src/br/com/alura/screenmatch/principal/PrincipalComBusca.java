package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.excecao.ErroDeConversaoDeAnoException;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmbd;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {

        HttpClient client = HttpClient.newHttpClient();

        // É utilizado o padrão de projeto Builder para a criação de objetos complexos.
        // Ou seja, várias partes complexas para formar algo maior.


        // Repetindo para uma lista de filmes:

        String busca = "";
        List<Titulo> titulos = new ArrayList<>();
//        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();
        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).setPrettyPrinting().create();



        while (!busca.equalsIgnoreCase("sair")) {
            Scanner leitura = new Scanner(System.in);
            System.out.println("Digite o nome do filme: ");
            busca = leitura.nextLine();


            if (busca.equalsIgnoreCase("sair")) {
                break;
            }


            String apikey = "&apikey=e8e878d6";
            String endereco = "http://www.omdbapi.com/?t=" + busca.replace(" ", "+") + apikey;
//        String endereco = "http://www.omdbapi.com/?t=" + busca + apikey;
//        Esta linha mesmo do jeito que é digitado, ainda é necessário substituir o espaço - " " pelo sinal de +
//        De qualquer forma, essa string maior não seria apresentada desta forma - a apikey seria colocar em uma varíavel de ambiente,
//        assim como o site da API.

            try {
                HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();

// HttpRequest requisicao = new HttpRequest();
// Essa linha de cima, vai apresentar um erro de compilação, já que se trata de uma classe abstrata.
// Logo ela não pode ser instanciada.

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());


                System.out.println("Endereço pesquisado: " + endereco);
                System.out.println(response.body());
                System.out.println(response.statusCode());

                String json = response.body();
//        Gson gson = new Gson();


//        Linha comentada para usar o Record ao invés do classe.
//        Titulo meuTitulo = gson.fromJson(json, Titulo.class);


                TituloOmbd meuTitulooOmbd = gson.fromJson(json, TituloOmbd.class);
                // Esse objeto acima foi criado apenas para ser depois transferido para outro lugar.

//        Ele será transferido no momento de criação de um objeto do tipo MeuTitulo.
//        try {
                Titulo meuTitulo = new Titulo(meuTitulooOmbd);
                System.out.println(meuTitulo);

                titulos.add(meuTitulo);


                // Gravando num arquivo
                FileWriter escritaNoArquivo = new FileWriter("filmes.txt");
                escritaNoArquivo.write(meuTitulo.toString());
                escritaNoArquivo.close();
            }

// Mas esse bloco try ainda não é suficiente, uma vez que é possível ser lançada uma exceção de argumento inválido.
// Por isso, será colocado o bloco try no início da classe.
//
            catch (NumberFormatException e) {
                System.out.println("Aconteceu um erro:");
                System.out.println(e.getMessage());
            }

            //Mesmo contendo esse catch. precisaremos de outra captura de exceção para argumentos inválidos.
            catch (IllegalArgumentException e) {
                System.out.println("Erro: Argumento inválido. - verifique o endereço");
                System.out.println(e.getMessage());
            }

            // Tratamento mais genérico - não recomendado.
//        catch (Exception e) {
//            System.out.println("Exceção genérica.");
//            System.out.println(e.getMessage());
//        }//
//
//        Colocando a nossa exceção persolizada.
            catch (ErroDeConversaoDeAnoException e) {
                System.out.println(e.getMessage());
            }

            // Como o ano ou duração podem lançar uma exceção, essa linha ficará comentada e será deixada no bloco try-catch
//        Titulo meuTitulo = new Titulo(meuTitulooOmbd);


        }

//        System.out.println(titulos);

//        Agora já possível salvar uma lista de Filmes, vou salvar num arquivo.

        FileWriter arquivo = new FileWriter("filmes.json");
        // Mas nesta linha de cima não é suficiente, uma vez que desejo pegar o conteúdo de um objeto e converter em JSON.
        // Para isso, vou usar a classe Gson.

//        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

        arquivo.write(gson.toJson(titulos));
        arquivo.close();


        // Tentando obter o nome do título. Porém o resultado desta linha será "null".
        System.out.println("Programa terminou corretamente.");
        // Para resolver essa situação, precisamos informar que o campo "nome" em título é serializado.
    }
}
