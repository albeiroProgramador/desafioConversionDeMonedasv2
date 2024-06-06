package Servicios_Convert;

import com.google.gson.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.Clock;
import Rec_Principal_convert.Convert;

// Clase para gestionar las conversiones de divisas

public class Consulta_Convert {

    // Clave API (debe ser reemplazada por la tuya)

    private static final String API_KEY = "Ingrese su  API KEY";

    // URL base de la API

    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";

    // Reloj del sistema para obtener la hora y fecha actual

    Clock clock = Clock.systemDefaultZone();
    Instant instant = clock.instant();

    // Contador de conversiones

    int cont = 1;
    // Historial de conversiones

    List<String> historial = new ArrayList<>();

    // Constructor
    public Consulta_Convert() {}

    // Método para obtener el historial de conversiones
    public List<String> getHistorial() {
        return historial;
    }

    // Método para crear el enlace de consulta a la API

    public void CreateLink(Object currency, Object currency2, Object amount) {

        // Construir la URI de la solicitud
        URI direccion = buildUri(currency, currency2, amount);

        // Crear un cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Crear la solicitud HTTP
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .build();

        try {
            // Enviar la solicitud y obtener la respuesta
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            // Procesar la respuesta
            Convert convert = processResponse(response.body(), currency, currency2, amount);

            if (convert != null) {
                // Mostrar el resultado de la conversión
                displayConversionResult(amount, currency, convert.conversion_result(), currency2, convert.conversion_rate());
                // Guardar la conversión en el historial
                saveToHistory(amount, currency, convert.conversion_result(), currency2, convert.conversion_rate());
            }
        } catch (Exception e) {
            // Manejar cualquier error durante la consulta
            System.err.println("Error al realizar la consulta: " + e.getMessage());
        }
    }

    // Método para construir la URI de la solicitud
    private URI buildUri(Object currency, Object currency2, Object amount) {
        String url = BASE_URL + API_KEY + "/pair/" + currency + "/" + currency2 + "/" + amount;
        return URI.create(url);
    }

    // Método para procesar la respuesta de la API
    private Convert processResponse(String responseBody, Object currency, Object currency2, Object amount) {
        // Verificar si la respuesta está vacía o es nula
        if (responseBody == null || responseBody.trim().isEmpty()) {
            System.err.println("Error: La respuesta de la API está vacía o es nula.");
            return null;
        }

        try {
            // Parsear la respuesta JSON "ya casi"
            JsonObject jsonObject = JsonParser.parseString(responseBody).getAsJsonObject();
            double conversion_rate = jsonObject.get("conversion_rate").getAsDouble();
            double conversion_result = jsonObject.get("conversion_result").getAsDouble();
            return new Convert(conversion_result, conversion_rate);
        } catch (JsonSyntaxException e) {
            // Manejar error de JSON malformado
            System.err.println("Error: La respuesta de la API no es un JSON válido.");
        } catch (Exception e) {
            // Manejar cualquier otro error durante el procesamiento de la respuesta
            System.err.println("Error al procesar la respuesta: " + e.getMessage());
        }
        return null;
    }

    // Método para mostrar el resultado de la conversión
    private void displayConversionResult(Object amount, Object currency, double conversion_result, Object currency2, double conversion_rate) {
        System.out.printf("La conversión de %s [%s] es equivalente a: %.2f [%s]%n", amount, currency, conversion_result, currency2);
        if (conversion_rate < 1) {
            System.out.printf("La tasa actual de cambio %s a %s fue: %.5f [%s]%n", currency, currency2, conversion_rate, currency2);
        } else {
            System.out.printf("La tasa de conversión utilizada para esta conversión fue: %.2f [%s]%n", conversion_rate, currency2);
        }
    }

    // Método para guardar la conversión en el historial
    private void saveToHistory(Object amount, Object currency, double conversion_result, Object currency2, double conversion_rate) {
        historial.add(String.format(
                "\nConversión #%d\nUsted ingresó %s en [%s]\nRadio de conversión: %.5f ----> Valor de [%s] en [%s]\nResultado de la conversión: %.2f [%s]\nHora de ejecución: %s\nFecha de ejecución: %s\n",
                cont++, amount, currency, conversion_rate, currency, currency2, conversion_result, currency2,
                instant.atZone(ZoneId.systemDefault()).toLocalTime().toString().substring(0, 8),
                instant.atZone(ZoneId.systemDefault()).toLocalDate()
        ));
    }

    // Método para mostrar el menú de opciones
    public String menuConversion() {
        return """
                ***********************************************************
                TE DOY LA BIENVENIDA A "CONVERTIO", EL CONVERSOR DE DIVISAS :)
                1) Dólar  A  Peso Argentino.
                2) Peso Argentino A Dólar.
                3) Dólar A Real Brasileño.
                4) Real Brasileño A Dólar.
                5) Dólar A Peso Colombiano.
                6) Peso Colombiano A Dólar.
                7) Otras conversiones (Ingresa el código del país Ej. DZD).
                8) Salir.
                SELECCIONA UNA OPCIÓN:
                ***********************************************************
               """;
    }

    // Método para obtener la cantidad de dinero a convertir
    public double DineroAconvertir() {
        Scanner scan = new Scanner(System.in);
        System.out.println("\nIngresa el monto que deseas convertir\n");
        return scan.nextDouble();
    }
}