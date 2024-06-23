package Criptos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.Scanner;

public class Contraseñas {
    
    public static void guardarUsuarioContraseña() {
        Scanner scanner = new Scanner(System.in);

        // Solicitar el nombre de usuario
        System.out.print("Introduce tu nombre de usuario: ");
        String usuario = scanner.nextLine();
        int contadorContraseña = 0;
        while (contadorContraseña == 0) {
            System.out.print("Introduce tu contraseña (debe ser una clave AES válida en Base64): ");
            String contrasena = scanner.nextLine();
            if (rectificarContraseña(contrasena)) {
                try (PrintWriter writer = new PrintWriter(new FileWriter("usuarios.txt", true))) {
                    writer.println("Usuario: " + usuario);
                    writer.println("Contraseña: " + contrasena);
                    writer.println();  // Añadir una línea en blanco entre usuarios
                    System.out.println("Datos guardados exitosamente.");
                } catch (IOException e) {
                    System.out.println("Ocurrió un error al guardar los datos.");
                    e.printStackTrace();
                }
                contadorContraseña = 1;
            } else {
                System.out.println("La contraseña proporcionada no es válida. Por favor, inténtelo de nuevo.");
            }
        }
    }

    public static boolean rectificarContraseña(String contrasena) {
        try {
            // Decodificar la cadena Base64
            byte[] claveBytes = Base64.getDecoder().decode(contrasena);

            // Verificar la longitud de la clave (16, 24 o 32 bytes para AES)
            int longitudClave = claveBytes.length;
            if (longitudClave == 16 || longitudClave == 24 || longitudClave == 32) {
                return true;
            } else {
                System.out.println("La longitud de la clave decodificada no es válida. Debe ser de 16, 24 o 32 bytes.");
                return false;
            }
        } catch (IllegalArgumentException e) {
            System.out.println("La cadena proporcionada no es una Base64 válida.");
            return false;
        }
    }

    public static void revisarUsuarioContraseña() {
        Scanner scanner = new Scanner(System.in);
        int contador = 0;
        while (contador == 0) {
        System.out.print("Introduce tu nombre de usuario: ");
        String usuarioDado = scanner.nextLine();
        System.out.print("Introduce tu contraseña: ");
        String contraseñaDada = scanner.nextLine();
            if(verificarCredenciales(usuarioDado, contraseñaDada)){
                contador = 1;
            }else{
                contador = 0;
                System.out.println("Los datos están incorrectos, intentelo nuevamente");
            }
        }
        System.out.println("Inicio de seción exitoso");
    }

    public static boolean buscarContraseñaEspecifica(String contraseñaBuscada) {
        boolean encontrada = false;

        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if(linea.startsWith("Usuario: ")){
                    linea = reader.readLine();
                    if (linea.startsWith("Contraseña: ")) {
                        String contraseña = linea.substring(12).trim(); // Extraer la contraseña después de "Contraseña: " y eliminar espacios en blanco
                        System.out.println("Contraseña leída del archivo: '" + contraseña + "'"); // Mensaje de depuración
                        System.out.println("Contraseña buscada: '" + contraseñaBuscada + "'"); // Mensaje de depuración
                        if (contraseña.equals(contraseñaBuscada)) {
                            encontrada = true;
                            break; // Salir del bucle si se encuentra la coincidencia
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo 'usuarios.txt'. Asegúrate de que el archivo existe y es accesible.");
            e.printStackTrace();
        }

        return encontrada;
    }

    public static boolean verificarCredenciales(String usuarioDado, String contraseñaDada) {
        boolean encontrado = false;
        try (BufferedReader reader = new BufferedReader(new FileReader("usuarios.txt"))) {
            String linea;
            String usuario = null;
            String contraseña = null;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Usuario: ")) {
                    usuario = linea.substring(9).trim(); // Extraer el usuario después de "Usuario: " y eliminar espacios en blanco
                } else if (linea.startsWith("Contraseña: ")) {
                    contraseña = linea.substring(12).trim(); // Extraer la contraseña después de "Contraseña: " y eliminar espacios en blanco

                    // Verificar las credenciales
                    if (usuario != null && contraseña != null && usuario.equals(usuarioDado) && contraseña.equals(contraseñaDada)) {
                        encontrado = true;
                        break; // Salir del bucle si se encuentra la coincidencia
                    }

                    // Reiniciar variables para la siguiente búsqueda
                    usuario = null;
                    contraseña = null;
                }
            }
        } catch (IOException e) {
            System.out.println("Ocurrió un error al leer el archivo.");
            e.printStackTrace();
        }

        return encontrado;
    }
}





