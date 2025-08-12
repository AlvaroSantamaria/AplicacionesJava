package Tareas.servicio;

import Tareas.modelo.Tarea;
import java.util.List;

public interface ITareaSevicio  {

    public List<Tarea> listarTareas();

    public Tarea buscarTareaPorId(Integer idTarea);

    public void guardarTarea(Tarea tarea);

    public void eliminarTarea(Tarea tarea);
}
