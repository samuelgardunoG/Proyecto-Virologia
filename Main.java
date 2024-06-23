import javax.crypto.SecretKey;

import Criptos.Contraseñas;
import Criptos.Criptos;
import Criptos.Encryptor;
import java.util.Base64;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            int choice = -1;
            while (choice != 1 && choice != 2 && choice != 0) {
                // Limpia la consola para un mejor aspecto visual
                System.out.print("\033[H\033[2J");
                System.out.flush();

                // Menú mejorado con aspectos visuales
                System.out.println("╔═══════════════════════════════════╗");
                System.out.println("║          MENÚ PRINCIPAL           ║");
                System.out.println("╠═══════════════════════════════════╣");
                System.out.println("║  1. Ingresar con usuario existente║");
                System.out.println("║  2. Agregar nuevo usuario         ║");
                System.out.println("║  0. Salir del programa            ║");
                System.out.println("╚═══════════════════════════════════╝");
                System.out.print("Ingrese su opción (0, 1 o 2): ");

                choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        Contraseñas.revisarUsuarioContraseña();
                        scanner.nextLine();
                        menu2();
                        break;
                    case 2:
                        Contraseñas.guardarUsuarioContraseña();
                        scanner.nextLine();
                        menu2();
                        break;
                    case 0:
                        break;    
                    default:
                        System.out.println("Los datos no son correctos, intentelo de nuevo");
                        scanner.nextLine();
                        break;
                }

            }
            
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
        System.out.println("Adios...");
        scanner.nextLine();
    }

    public static void menu2(){
        try{
            Scanner scanner = new Scanner(System.in);
        int choice = -1;
            while (choice != 0) {
                // Limpia la consola para un mejor aspecto visual
                System.out.print("\033[H\033[2J");
                System.out.flush();

                // Menú mejorado para encriptar y desencriptar documentos
                System.out.println("╔════════════════════════════════════════════════════════╗");
                System.out.println("║           MENÚ DE ENCRIPTACIÓN/DESENCRIPTACIÓN         ║");
                System.out.println("╠════════════════════════════════════════════════════════╣");
                System.out.println("║  1. Encriptar un documento                             ║");
                System.out.println("║  2. Desencriptar un documento                          ║");
                System.out.println("║  0. Salir del programa                                 ║");
                System.out.println("╚════════════════════════════════════════════════════════╝");
                System.out.print("Ingrese su opción (0, 1 o 2): ");

                choice = scanner.nextInt();
                scanner.nextLine(); 

                switch (choice) {
                    case 1:
                        SeleccionarEncriptar();
                        break;
                    case 2:
                        SeleccionarDesencriptar();
                        break;
                    case 0:
                        //Salir de el programa totalmente
                        break;       
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                        break;
                }
            }
        }catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static void SeleccionarEncriptar() throws Exception{
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
        while (choice != 0) {
            // Generar una clave AES
            SecretKey secretKey = Encryptor.generateAESKey();
            // Convertir la clave generada a una cadena Base64
            String aesKeyBase64 = Base64.getEncoder().encodeToString(secretKey.getEncoded());

            if(choice != -1){
                System.out.println("Que otro archivo deseas encriptar?:");
            }else{
                System.out.println("Selecciona el tipo de archivo que deseas encriptar:");
            }
            System.out.println("1. Archivo de texto");
            System.out.println("2. Archivo de imagen");
            System.out.println("3. Archivo de audio");
            System.out.println("0. Salir");
            System.out.print("Tu elección: ");
            choice = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (choice) {
                case 1:
                    Criptos.encryptTextFile(secretKey);
                    System.out.println("Clave AES generada: ");
                    System.out.println(aesKeyBase64);
                    break;
                case 2:
                    Criptos.encryptTextFile(secretKey);
                    System.out.println("Clave AES generada: ");
                    System.out.println(aesKeyBase64);
                    break;
                case 3:
                    Criptos.encryptTextFile(secretKey);
                    System.out.println("Clave AES generada: ");
                    System.out.println(aesKeyBase64);
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    System.out.print("\033[H\033[2J");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    public static void SeleccionarDesencriptar() throws Exception{
        Scanner scanner = new Scanner(System.in);
        int choice = -1;
            while (choice != 0) {
                System.out.println("Selecciona el tipo de archivo que deseas desencriptar:");
                System.out.println("1. Archivo de texto");
                System.out.println("2. Archivo de imagen");
                System.out.println("3. Archivo de audio");
                System.out.println("0. Salir");
                System.out.print("Tu elección: ");
                choice = scanner.nextInt();
                scanner.nextLine(); // Limpiar el buffer

                switch (choice) {
                    case 1:
                        Criptos.decryptTextFile();
                        break;
                    case 2:
                        Criptos.decryptTextFile();
                        break;
                    case 3:
                        Criptos.decryptTextFile();
                        break;
                    case 0:
                        System.out.println("Saliendo del programa.");
                        System.out.print("\033[H\033[2J");
                        break;
                    default:
                        System.out.println("Opción no válida. Inténtalo de nuevo.");
                }
            }
    }
   
}

