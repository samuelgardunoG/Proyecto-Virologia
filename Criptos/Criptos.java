package Criptos;
import javax.crypto.SecretKey;
import java.util.Scanner;

public class Criptos {

    
    public static SecretKey ingresoClave(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingresa la clave AES en formato Base64 para desencriptar los archivos: ");
            String userKeyInput = scanner.nextLine().trim(); // Trim para eliminar espacios adicionales
            // Convertir la clave ingresada por el usuario de Base64 a SecretKey
            SecretKey userSecretKey = Encryptor.convertStringToAESKey(userKeyInput);
        return userSecretKey;    
    }

    // Métodos para encriptar y desencriptar archivos de texto, imagen y audio
    public static void encryptTextFile(SecretKey secretKey) throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean contraseñaCorrecta = false;

        while (!contraseñaCorrecta) {
            System.out.println("Ingrese su contraseña: ");
            String contrasena = scanner.nextLine().trim();
            if (Contraseñas.rectificarContraseña(contrasena)) {
                if (Contraseñas.buscarContraseñaEspecifica(contrasena)) {
                    System.out.println("Ponga el nombre del archivo que desea encriptar");
                    String textFileOriginal = scanner.nextLine().trim();
                    String textFileEncrypted = "Encripted_" + textFileOriginal;
                    Encryptor.encryptFile(textFileOriginal, textFileEncrypted, Encryptor.convertStringToAESKey(contrasena));
                    Encryptor.encryptFile(textFileEncrypted, textFileEncrypted, secretKey);
                    System.out.println("Archivo de texto encriptado: " + textFileEncrypted);
                    contraseñaCorrecta = true;
                } else {
                    System.out.println("Contraseña no encontrada. Intente de nuevo.");
                }
            } else {
                System.out.println("Contraseña incorrecta. Intente de nuevo.");
            }
        }
    }

    public static void decryptTextFile() throws Exception {
        Scanner scanner = new Scanner(System.in);
        boolean contraseñaCorrecta = false;
    
        while (!contraseñaCorrecta) {
            System.out.println("Ingrese su contraseña: ");
            String contrasena = scanner.nextLine().trim();
            if (Contraseñas.rectificarContraseña(contrasena)) {
                if (Contraseñas.buscarContraseñaEspecifica(contrasena)) {
                    SecretKey secretKey = ingresoClave();
                    System.out.println("Ponga el nombre del archivo que desea desencriptar");
                    String textFileEncrypted = scanner.nextLine();
                    String textFileDecrypted = "Desencripted_" + textFileEncrypted;
                    Encryptor.decryptFile(textFileEncrypted, textFileDecrypted, secretKey);
                    Encryptor.decryptFile(textFileDecrypted, textFileDecrypted, Encryptor.convertStringToAESKey(contrasena));
                    System.out.println("Archivo de texto desencriptado: " + textFileDecrypted);
                    contraseñaCorrecta = true;
                } else {
                    System.out.println("Contraseña no encontrada. Intente de nuevo.");
                }
            } else {
                System.out.println("Contraseña incorrecta. Intente de nuevo.");
            }
        }
    }
    
}
