package Contactos.controlador;

import Contactos.modelo.Contacto;
import Contactos.servicio.ContactoServicio;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class IndexControlador {
    private static final Logger logger =
            LoggerFactory.getLogger(IndexControlador.class);

    @Autowired
    private ContactoServicio contactoServicio;

    @GetMapping("/")
    public String iniciar(ModelMap modelo){
        List<Contacto> contactos = contactoServicio.listarContactos();
        contactos.forEach((contacto) -> logger.info(contacto.toString()));
        modelo.put("contactos", contactos);
        return "index"; //index.html
    }

    @GetMapping("/agregar")
    public String mostrarAgregar(){
        return "agregar"; //agregar.hmtl
    }

    @PostMapping("/agregar")
    public String agregar(@ModelAttribute("contactoForma")Contacto contacto){
        logger.info("Contacto a agregar: " + contacto);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/"; //redirige al path "/"
    }

    @GetMapping("/editar/{id}")
    public String mostarEditar(@PathVariable(value = "id") int idContacto, ModelMap modelo){
        Contacto contacto = contactoServicio.buscarContactoPorId(idContacto);
        logger.info("Concato a editar: " + contacto);
        modelo.put("contacto", contacto);
        return "editar"; //mostar editar.html

    }

    @PostMapping("/editar")
    public String editar(@ModelAttribute("contacto")Contacto contacto){
        logger.info("Contacto a editar(guardar): " + contacto);
        contactoServicio.guardarContacto(contacto);
        return "redirect:/"; //redirige al path "/"
    }

    @RequestMapping("/eliminar/{id}")
    public String eliminar(@PathVariable(value = "id") int idContacto){
        Contacto contacto = new Contacto();
        contacto.setIdContacto(idContacto);
        contactoServicio.eliminarContacto(contacto);
        logger.info("Contacto a eliminar: " + contacto);
        return "redirect:/"; //redirige al path "/"
    }
}
