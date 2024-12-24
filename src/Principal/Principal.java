package Principal;

import Models.Moneda;
import Models.MonedaOmdb;
import com.google.gson.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class Principal {




    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner lector = new Scanner(System.in);



        var busqueda = 0;
        while(busqueda!=7) {
            System.out.println("************************************");
            System.out.println("Sea bienvenido/a al Conversor de Monedas :)");
            System.out.println("1) Dólar Estadounidense =>> Sol Peruano\n" +
                    "2) Sol Peruano =>> Dólar Estadounidense\n" +
                    "3) Dólar Estadounidense =>> Peso Cubano\n" +
                    "4) Peso Cubano =>> Dólar Estadounidense\n" +
                    "5) Dólar Estadounidense =>> Peso Mexicano\n" +
                    "6) Peso Mexicano =>> Dólar Estadounidense\n" +
                    "7) Salir");
            busqueda = lector.nextInt();
            System.out.println(busqueda);
            switch (busqueda) {
                case 1:
                    calcularConversion("USD", "PEN");
                    break;
                case 2:
                    calcularConversion("PEN", "USD");
                    break;
                case 3:
                    calcularConversion("USD", "CUP");
                    break;
                case 4:
                    calcularConversion("CUP", "USD");
                    break;
                case 5:
                    calcularConversion("USD", "MXN");
                    break;
                case 6:
                    calcularConversion("MXN", "USD");
                    break;
                case 7:
                    System.out.println("Saliendo....");
                    break;
                default:
                    System.out.println("Por favor, elija una opción válida");
                    break;
            }
        }


    }
    private static void calcularConversion(String convertir, String conversion) throws IOException, InterruptedException {
        // Setting URL
        String url_str = "https://v6.exchangerate-api.com/v6/bda5d88bca4a9b73db879180/latest/"+convertir;
        Scanner lector = new Scanner(System.in);
        System.out.println("Ingrese el valor que deseas convertir:");
        var lectura = lector.nextDouble();
        HttpRequest request = (HttpRequest) HttpRequest.newBuilder()
                .uri(URI.create(url_str))
                .build();
        HttpClient client = HttpClient.newHttpClient();

        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());


        String json = response.body();

        Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE).create();

        Moneda moneda = gson.fromJson(json, Moneda.class);
        //MonedaOmdb monedaOmdb = gson.fromJson(json, MonedaOmdb.class);
        //Moneda moneda = new Moneda(monedaOmdb.nombre(), monedaOmdb.conversion());
        //Titulo miTitulo = gson.fromJson(json, Titulo.class);
        //System.out.println(json);

        Double convertido = moneda.getConversion().get(conversion);
        if(convertir=="USD"&&conversion=="CUP"){
            convertido = 312.00;
        } else if (convertir=="CUP"&&conversion=="USD") {
            convertido = 0.0032;
        }
        System.out.println("El valor de "+lectura+" ["+convertir+"] corresponde al valor final de =>>> "+convertido*lectura + " ["+conversion+"]");
        //System.out.println(moneda.getNombre());
        //System.out.println(moneda.getConversion().get(conversion));
        //System.out.println(monedaOmdb);
    }

}
