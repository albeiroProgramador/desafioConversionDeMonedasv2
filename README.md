<h1 align="center"> Convertio, tu conversor de Divisas </h1>
## Convertio, tu conversor de Divisas

Convertio es una aplicación que permite convertir diferentes divisas utilizando la API de ExchangeRate-API. La aplicación está desarrollada en Java y permite realizar conversiones entre USD, ARS, BRL y COP, además de permitir conversiones personalizadas entre otras divisas.

## Estructura del Proyecto

- **src**
  - **main**
    - **java**
      - **Rec_Principal_convert**
        - `Convert.java`: Record que contiene el resultado de la conversión y la tasa de conversión.
      - **Servicios_Convert**
        - `ConsultaConvert.java`: Clase que maneja la lógica de conversión, el historial y las interacciones con la API.
      - `Main.java`: Clase principal que ejecuta el programa.

## Requisitos

- Java 11 o superior
- IntelliJ IDEA (opcional, pero recomendado para desarrollo)

## Instalación

1. Clona este repositorio en tu máquina local.
2.Abre el proyecto en tu IDE de preferencia (se recomienda IntelliJ IDEA).
3.Configura las variables de entorno necesarias:
API_KEY: Tu clave de API de ExchangeRate-API.

## Uso
Ejecuta la clase Main.java.

Selecciona la opción de conversión que desees:

1)Dólar a Peso Argentino
2)Peso Argentino a Dólar
3)Dólar a Real Brasileño
4)Real Brasileño a Dólar
5)Dólar a Peso Colombiano
6)Peso Colombiano a Dólar
7)Otras conversiones (Ingresar el código del país, Ej. DZD)
8)Salir
Ingresa el monto a convertir.

El resultado de la conversión y la tasa de conversión se mostrarán en pantalla.

Al finalizar, puedes consultar el historial de conversiones.

## Tecnologías Utilizadas
Java 17
HTTP Client
Gson
ExchangeRate-API
https://www.exchangerate-api.com/

##Contribución
Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:

*Haz un fork del repositorio.
*Crea una nueva rama (feature/nueva-caracteristica).
*Realiza los cambios y commitea.
*Envía tus cambios mediante un pull request.

## Licencia
Este proyecto es de código abierto, hecho para Alura Latam.

Contacto
Para cualquier duda o sugerencia, puedes contactar al autor en Jesusalbeirocastrogil@gmail.com
