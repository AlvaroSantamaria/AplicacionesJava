package zona_fit.datos;

import java.util.List;
import zona_fit.dominio.Cliente;

public interface IClienteDAO {

    List<Cliente> listarCliente();
    boolean buscarClientePorId(Cliente cliente);
    boolean agregarCliente(Cliente cliente);
    boolean modificarCliente(Cliente cliente);
    boolean eliminarCliente(Cliente cliente);

}
