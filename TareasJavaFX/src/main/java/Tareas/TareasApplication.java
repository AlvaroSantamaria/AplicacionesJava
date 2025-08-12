package Tareas;

import Tareas.presentacion.SistemaTareasFx;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TareasApplication {

	public static void main(String[] args) {
        Application.launch(SistemaTareasFx.class, args);
	}

}
