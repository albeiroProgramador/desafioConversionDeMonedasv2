package Principal_conver;

import Servicios_Convert.Consulta_Convert;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Consulta_Convert objCC = new Consulta_Convert();
        int opc;

        do {
            // Mostrar el menú y obtener la opción seleccionada por el usuario
            System.out.println(objCC.menuConversion());
            opc = scan.nextInt();
            double amount;

            // Salir del programa si se selecciona la opción 8
            if (opc == 8) {
                break;
            }

            // Ejecutar la opción seleccionada
            switch (opc) {
                case 1:
                    amount = objCC.DineroAconvertir();
                    objCC.CreateLink("USD", "ARS", amount);
                    break;
                case 2:
                    amount = objCC.DineroAconvertir();
                    objCC.CreateLink("ARS", "USD", amount);
                    break;
                case 3:
                    amount = objCC.DineroAconvertir();
                    objCC.CreateLink("USD", "BRL", amount);
                    break;
                case 4:
                    amount = objCC.DineroAconvertir();
                    objCC.CreateLink("BRL", "USD", amount);
                    break;
                case 5:
                    amount = objCC.DineroAconvertir();
                    objCC.CreateLink("USD", "COP", amount);
                    break;
                case 6:
                    amount = objCC.DineroAconvertir();
                    objCC.CreateLink("COP", "USD", amount);
                    break;
                case 7:
                    try {
                        // Obtener las divisas personalizadas del usuario
                        System.out.println("Por favor, ingresa la divisa del país de origen: ");
                        String op1 = scan.next().toUpperCase().replace(" ", "");
                        System.out.println("Ahora por favor, ingresa la divisa del país al que quieres convertir: ");
                        String op2 = scan.next().toUpperCase().replace(" ", "");
                        amount = objCC.DineroAconvertir();
                        objCC.CreateLink(op1, op2, amount);
                    } catch (Exception e) {
                        throw new RuntimeException("Lo sentimos, la divisa ingresada no existe");
                    }
                    break;
                default:
                    System.out.println("""
                            æ ┬─ OPCIÓN INCORRECTA ┬─ æ
                            _________________________________________
                            """);
            }
        } while (opc != 9);

        // Mostrar el historial de conversiones si se realizaron conversiones
        if (objCC.getHistorial().isEmpty()) {
            System.out.println("No hiciste ninguna conversión");
        } else {
            System.out.println("El historial que hiciste en esta sesión fue: " + "\n" + objCC.getHistorial());
        }
    }
}