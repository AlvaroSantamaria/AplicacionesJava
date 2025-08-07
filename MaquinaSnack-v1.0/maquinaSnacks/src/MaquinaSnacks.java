import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MaquinaSnacks {
    public static void main (String[] args){
        maquinaSnacks();
    }

    public static void maquinaSnacks(){
        var salir = false;
        var consola = new Scanner(System.in);
        // Creamos la lista de productos de tipo snack
        List<Snack> productos = new ArrayList<>();
        System.out.println("*** Maqunia de Snacks ***");
        Snacks.mostrarSnacks();
        while (!salir){
            try{
                var opcion = mostrarMenu(consola);
                salir = ejecutarOpciones(opcion, consola, productos);
            }catch (Exception e){
                System.out.println("Error = " + e.getMessage());
            }finally {
                System.out.println(); // Imprime un salto de linea con cada iteración
            }
        }
    }

    private static int mostrarMenu(Scanner consola){
        System.out.print("""
                Menu:
                1. Comprar snack
                2. Mostrar ticket
                3. Agregar nuevo snack
                4. Salir
                Elige una opcion:\s""");
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(int opcion, Scanner cosola,List<Snack> productos){
        var salir = false;
        switch (opcion){
            case 1 -> comprarSnack(cosola, productos);
            case 2 -> mostrarTicket(productos);
            case 3 -> agregarSnacks(cosola);
            case 4 -> {
                System.out.println("Regresa pronto!");
                salir = true;
            }
            default -> System.out.println("Opcion invalida: " + opcion);
        }
        return salir;
    }

    private static void comprarSnack(Scanner consola,List<Snack> productos){
        System.out.print("Que snack quieres comprar (id)? ");
        var idSnack = Integer.parseInt(consola.nextLine());
        // Validar que el snack exista en la lista de snacks
        var snackEncontrado = false;
        for(var snack: Snacks.getSnacks()){
            if(idSnack == snack.getIdSnack()){
                // Agregamos el snack a la lista de productos
                productos.add(snack);
                System.out.println("Snack agregado con Exito: " + snack);
                snackEncontrado = true;
                break;
            }
        }
        if(!snackEncontrado){
            System.out.println("Id de snack no encontrado: " + idSnack);
        }
    }

    private static void mostrarTicket(List<Snack> productos){
        var ticket = "*** Ticket de Venta ***";
        var total = 0.0;
        for(var producto: productos){
            ticket += "\n\t* " + producto.getNombre() + " - $" + producto.getPrecio();
            total += producto.getPrecio();
        }
        ticket += "\n\ttotal -> $" + total;
        System.out.println(ticket);
    }

    private static void agregarSnacks(Scanner consola){
        System.out.print("Nombre del Snack: ");
        var nombre = consola.nextLine();
        System.out.print("Precio del Snack: ");
        var precio = Double.parseDouble(consola.nextLine());
        Snacks.agregarSnacks(new Snack(nombre, precio));
        System.out.println("Snack agregado correctamente\n");
        Snacks.mostrarSnacks();
    }

}
