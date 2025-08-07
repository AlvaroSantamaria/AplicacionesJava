package zona_fit.presentacion;

import zona_fit.datos.ClienteDAO;
import zona_fit.dominio.Cliente;
import java.util.Scanner;

public class ZonaFitApp {

    public static void main(String[] args) {
        zonaFitApp();
    }

    private static void zonaFitApp(){
        var salir = false;
        var consola = new Scanner(System.in);
        var clienteDao = new ClienteDAO();
        while (!salir){
            try {
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(consola, opcion, clienteDao);
            }catch (Exception e){
                System.out.println("Error al ejecutar opciones:" + e.getMessage());
            }finally {
                System.out.println();
            }
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                *** Zona Fit (GYM) ***
                Menu:
                1. Listar clientes
                2. Buscar cliente
                3. Agregar cliente
                4. Modificar cliente
                5. Eliminar cliente
                6. Salir
                Elige una opcion:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion, ClienteDAO clienteDAO){
        var salir = false;
        switch (opcion) {
            case 1 -> { // 1. Listar Clientes
                System.out.println("--- Listado de Clientes ---");
                var clientes = clienteDAO.listarCliente();
                clientes.forEach(System.out::println);
            }
            case 2 -> { // 2. Buscar Cliente por Id
                System.out.println("--- Buscar Cliente por Id ---");
                System.out.print("Id cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var buscar = clienteDAO.buscarClientePorId(cliente);
                if (buscar)
                    System.out.println("Cliente encontrado: " + cliente);
                else
                    System.out.println("Cliente no encontrado: " + cliente);
            }
            case 3 -> { // 3. Agregar Cliente
                System.out.println("--- Agregar Cliente ---");
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(nombre, apellido, membresia);
                var agregado = clienteDAO.agregarCliente(cliente);
                if(agregado)
                    System.out.println("Cliente agregado: " + cliente);
                else
                    System.out.println("Cliente no agregado: " + cliente);
            }
            case 4 -> { // 4. Modificar Cliente
                System.out.println("--- Modificar Cliente ---");
                System.out.print("Id cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                System.out.print("Nombre: ");
                var nombre = consola.nextLine();
                System.out.print("Apellido: ");
                var apellido = consola.nextLine();
                System.out.print("Membresia: ");
                var membresia = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente, nombre, apellido, membresia);
                var modificado = clienteDAO.modificarCliente(cliente);
                if(modificado)
                    System.out.println("Cliente modificado: " + cliente);
                else
                    System.out.println("Cliente no modificado: " + cliente);
            }
            case 5 -> { // 5. Eliminar Cliente
                System.out.println("--- Eliminar Cliente ---");
                System.out.print("Id cliente: ");
                var idCliente = Integer.parseInt(consola.nextLine());
                var cliente = new Cliente(idCliente);
                var eliminado = clienteDAO.eliminarCliente(cliente);
                if(eliminado)
                    System.out.println("Cliente eliminado: " + cliente);
                else
                    System.out.println("Cliente no eliminado: " + cliente);
            }
            case 6 -> {
                System.out.println("Hasta pronto!");
                salir = true;
            }
            default -> System.out.println("Opcion invalida: " + opcion);
        }
        return salir;
    }
}
