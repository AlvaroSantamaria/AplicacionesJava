package Aplicacion.ZonaFitSpring.repositorio;

import Aplicacion.ZonaFitSpring.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
}
