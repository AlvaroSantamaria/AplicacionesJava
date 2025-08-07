package servicio;

import dominio.Snack;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ServicioSnacksArchivos implements IServicioSnacks{

    private final String NOMBRE_ARCHIVO = "snacks.txt";
    // crear lista de snacks
    private List<Snack> snacks = new ArrayList<>();

    // constructor clase
    public ServicioSnacksArchivos (){
        // Creamos el archivo si no existe
        var archivo = new File(NOMBRE_ARCHIVO);
        var existe = false;
        try {
            existe = archivo.exists();
            if(existe){
                this.snacks = obtenerSnacks();
            }else { // Creamos el Archivo
                var salir = new PrintWriter(new FileWriter(archivo));
                System.out.println("Se ha creado el archivo");
            }
        }catch (Exception e){
            System.out.println("Error al crear el archivo: " + e.getMessage());
        }
        // si no existe, caragmos algunos snacks iniciales
        if(!existe){
            cargarSnacksIniciales();
        }
    }

    private void cargarSnacksIniciales(){
        this.agregarSnacks(new Snack("Papas", 70.00));
        this.agregarSnacks(new Snack("Refresco", 50.00));
        this.agregarSnacks(new Snack("Sandwich", 120.00 ));
    }

    private List<Snack> obtenerSnacks() {
        var snacks = new ArrayList<Snack>();
        try{
            List<String> lineas = Files.readAllLines(Paths.get(NOMBRE_ARCHIVO));
            for(String linea: lineas){
                String[] lineaSnack = linea.split(","); // Parseo para optener los valores
                var idSnack = lineaSnack[0]; // no se usa
                var nombre = lineaSnack[1];
                var precio = Double.parseDouble(lineaSnack[2]);
                var snack = new Snack(nombre, precio);
                snacks.add(snack); // agregamos el snack leido a la lista
            }
        }catch (Exception e){
            System.out.println("Error al leer archivo de snacks: " + e.getMessage());
        }
        return snacks;
    }

    @Override
    public void agregarSnacks(Snack snack) {
        // Agreagmos el nuevo snacks, 1. a la lista en memoria
        this.snacks.add(snack);
        // 2. Guardamos el nuevo snack en el archivo
        this.agregarSnacksArchivo(snack);
    }

    private void agregarSnacksArchivo(Snack snack){
        boolean anexar = false;
        var archivo = new File(NOMBRE_ARCHIVO);
        try {
            anexar = archivo.exists();
            var salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(snack.escribirSnack());
            salida.close(); // se escribe la información en el archivo
        }catch (Exception e){
            System.out.println("Error al agregar snack: " + e.getMessage());
        }
    }

    @Override
    public void mostrarSnacks() {
        System.out.println("--- Snacks en el invetario ---");
        // Mostramos la lista de snacks en el archivo
        var invetarioSnacks = "";
        for(var snack: this.snacks){
            invetarioSnacks += snack.toString() + "\n";
        }
        System.out.println(invetarioSnacks);
    }

    @Override
    public List<Snack> getSnacks() {
        return this.snacks;
    }
}
