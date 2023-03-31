import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {

        API api = API.NASA;

        String url = api.getUrl();
        ExtratorDeConteudo extator = api.getExtrator();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extator.extraiConteudos(json);

        var gerador = new GeradorDeFigurinhas();

        for (int i = 0; i < 3; i++) {

            Conteudo conteudo = conteudos.get(i);

            InputStream inputStream = new URL(conteudo.urlImagem()).openStream();
            String nomeArquivo = "Alura-stickers/Figurinhas/" + conteudo.titulo() + ".png";

            gerador.cria(inputStream, nomeArquivo);

            System.out.println(conteudo.titulo());
            System.out.println();
        }
    }
}
