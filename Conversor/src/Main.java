import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {
    private static final HttpClient httpClient = HttpClient.newBuilder().build();
    private static final String API_URL = "https://v6.exchangerate-api.com/v6/cc9968c2546aae2899de73ba/pair/";
    private static final List<Conversion> conversionHistory = new ArrayList<>();

    public static class CurrencyConverter {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Selecione a opção:");
                System.out.println("1. Conversão de moedas");
                System.out.println("2. Histórico de conversões");
                System.out.println("3. Sair");

                int choice = scanner.nextInt();

                if (choice == 1) {
                    performConversion(scanner);
                } else if (choice == 2) {
                    displayConversionHistory();
                } else if (choice == 3) {
                    System.out.println("Saindo...");
                    break;
                } else {
                    System.out.println("Opção inválida!");
                }
            }

            scanner.close();
        }

        private static void performConversion(Scanner scanner) {
            System.out.println("Escolha a moeda do país de origem:");
            displayCountryList();
            int fromChoice = getValidChoice(scanner);
            String fromCurrency = getCurrencyCode(fromChoice);

            System.out.println("Escolha a moeda do país de destino:");
            int toChoice = getValidChoice(scanner);
            String toCurrency = getCurrencyCode(toChoice);

            System.out.println("Digite a quantia a ser convertida:");
            double amount = scanner.nextDouble();

            double convertedAmount = convertCurrency(fromCurrency, toCurrency, amount);
            System.out.printf("%.2f %s equivale a %.2f %s\n", amount, fromCurrency, convertedAmount, toCurrency);

            // Adiciona a conversão ao histórico
            Conversion conversion = new Conversion(fromCurrency, toCurrency, amount, convertedAmount, LocalDateTime.now());
            conversionHistory.add(conversion);
        }

        private static void displayConversionHistory() {
            if (conversionHistory.isEmpty()) {
                System.out.println("Não há histórico de conversões disponível.");
            } else {
                System.out.println("Histórico de Conversões:");
                for (Conversion conversion : conversionHistory) {
                    System.out.println(conversion);
                }
            }
        }

        private static void displayCountryList() {
            System.out.println("**********************************");
            System.out.println("1. Real Brasileiro");
            System.out.println("2. Dólar Americano");
            System.out.println("3. Peso Chileno");
            System.out.println("4. Peso Argentino");
            System.out.println("5. Boliviano da Bolívia");
            System.out.println("6. Peso Colombiano");
            System.out.println("**********************************");
        }

        private static int getValidChoice(Scanner scanner) {
            int choice;
            while (true) {
                System.out.print("Digite o número correspondente ao país: ");
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= 6) {
                    break;
                } else {
                    System.out.println("Escolha inválida. Por favor, escolha um número entre 1 e 10.");
                }
            }
            return choice;
        }

        private static String getCurrencyCode(int choice) {
            return switch (choice) {
                case 1 -> "BRL";
                case 2 -> "USD";
                case 3 -> "CLP";
                case 4 -> "ARS";
                case 5 -> "BOB";
                case 6 -> "COP";
                default -> "";
            };
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

            return -1;
        }
    }

    static class Conversion {
        private final String fromCurrency;
        private final String toCurrency;
        private final double amount;
        private final double convertedAmount;
        private final LocalDateTime dateTime;
        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        public Conversion(String fromCurrency, String toCurrency, double amount, double convertedAmount, LocalDateTime dateTime) {
            this.fromCurrency = fromCurrency;
            this.toCurrency = toCurrency;
            this.amount = amount;
            this.convertedAmount = convertedAmount;
            this.dateTime = dateTime;
        }

        @Override
        public String toString() {
            return String.format("[%s -> %s] Quantia: %.2f %s, Convertida: %.2f %s, Horário: %s",
                    fromCurrency, toCurrency, amount, fromCurrency, convertedAmount, toCurrency, dateTime.format(formatter));
        }
    }
}
