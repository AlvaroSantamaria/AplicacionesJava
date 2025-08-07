package servicio;

import dominio.Snack;
import java.util.List;

public interface IServicioSnacks {
    void agregarSnacks(Snack snack);
    void mostrarSnacks();
    List<Snack> getSnacks();
}
