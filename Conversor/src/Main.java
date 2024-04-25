import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
    private static final HttpClient httpClient = HttpClient.newBuilder().build();
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/cc9968c2546aae2899de73ba/pair/";

    public static class CurrencyConverter {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Selecione a opção:");
                System.out.println("1. Conversão de moedas");
                System.out.println("2. Sair");

                int choice = scanner.nextInt();

                if (choice == 1) {
                    System.out.println("Escolha o país de origem:");
                    displayCountryList();
                    int fromChoice = scanner.nextInt();
                    String fromCurrency = getCurrencyCode(fromChoice);

                    System.out.println("Escolha o país de destino:");
                    displayCountryList();
                    int toChoice = scanner.nextInt();
                    String toCurrency = getCurrencyCode(toChoice);

                    System.out.println("Digite a quantidade a ser convertida:");
                    double amount = scanner.nextDouble();

                    double convertedAmount = convertCurrency(fromCurrency, toCurrency, amount);
                    System.out.printf("%.2f %s equivale a %.2f %s\n", amount, fromCurrency, convertedAmount, toCurrency);
                } else if (choice == 2) {
                    break;
                } else {
                    System.out.println("Opção inválida!");
                }
            }

            scanner.close();
        }

        private static double convertCurrency(String fromCurrency, String toCurrency, double amount) {
            String url = API_URL + fromCurrency + "/" + toCurrency + "/" + amount;
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .build();

            try {
                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() == 200) {
                    JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
                    if (jsonObject.get("result").getAsString().equals("success")) {
                        return jsonObject.get("conversion_result").getAsDouble();
                    } else {
                        System.out.println("Erro na conversão: " + jsonObject.get("error-type").getAsString());
                    }
                } else {
                    System.out.println("Erro na requisição HTTP. Código de status: " + response.statusCode());
                }
            } catch (Exception e) {
                System.out.println("Erro na requisição HTTP: " + e.getMessage());
            }

            return -1; // Retorna -1 se houver erro na conversão
        }

        private static void displayCountryList() {
            System.out.println("Escolha o país:");
            System.out.println("1. Brasil");
            System.out.println("2. Argentina");
            System.out.println("3. Chile");
            // Adicione mais países conforme necessário
        }

        private static String getCurrencyCode(int choice) {
            return switch (choice) {
                case 1 -> "BRL";
                case 2 -> "ARS";
                case 3 -> "CLP";
                // Adicione mais casos conforme necessário
                default -> ""; // Retorno vazio para caso de escolha inválida
            };
        }
    }
}
