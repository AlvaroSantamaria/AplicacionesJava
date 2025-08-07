package Aplicacion.ZonaFitSwing.repositorio;

import Aplicacion.ZonaFitSwing.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
}
