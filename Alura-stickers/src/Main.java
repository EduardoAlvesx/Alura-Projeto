import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;
public class Main {
    public static void main(String[] args) throws Exception {

        String url = "https://raw.githubusercontent.com/alura-cursos/imersao-java-2-api/main/TopMovies.json";
        URI endereco = URI.create(url);
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        String body = response.body();
        JsonParser jsonParser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = jsonParser.parse(body);

        for (Map<String,String> filme: listaDeFilmes) {
            System.out.println("\u001b[32mTitulo:\u001b[m "+filme.get("title"));
            System.out.println("\u001b[32mPoster:\u001b[m "+filme.get("image"));
            System.out.println("\u001b[32mAvaliaçâo:\u001b[m "+filme.get("imDbRating"));
            double avaliacao = Double.parseDouble(filme.get("imDbRating"));
            int estrela = (int) avaliacao;
            for (int i = 1; i <= estrela; i++) {
                System.out.print("⭐");
            }
            System.out.println("\n");
        }
    }
}