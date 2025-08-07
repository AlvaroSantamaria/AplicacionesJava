package Aplicacion.ZonaFitSwing.servicio;

import java.util.List;
import Aplicacion.ZonaFitSwing.modelo.Cliente;

public interface IClienteServicio {

    public List<Cliente> listarClientes();

    public Cliente buscarClientePorId(Integer idCliente);

    public void guardarCliente(Cliente cliente);

    public void eliminarCliente(Cliente cliente);

}
