package Aplicacion.ZonaFitWeb.repositorio;

import Aplicacion.ZonaFitWeb.modelo.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer> {
}
