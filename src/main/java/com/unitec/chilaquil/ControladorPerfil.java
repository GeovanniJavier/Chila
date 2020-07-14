package com.unitec.chilaquil;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//representational state transfer Controller
//Los estados mas comunes son: guardar, buscar, actualizar y borrar
@RestController
//API - Application Programing Interface
@RequestMapping("/api")
public class ControladorPerfil {

    //esta es la inversion de control o inyeccion de dependencias
    @Autowired
    RepoPerfil repoPerfil;

    //En los servicos REST se teiene una urlBase que consiste de la ip
    //o host seguida del puerto, despues /api/hola
    //Es decir , para este caso mi api REST es:
    //http://localhost:8080/api/hola
    @GetMapping("/hola")
    public Saludo saludar() {
        Saludo s = new Saludo();
        s.setNombre("Javi");
        s.setMensaje("Mi primer mensaje en spring rest");
        return s;
    }

    //El siguiente metodo va a servir para guardar en back end nuestros datos
    //del perfil
    //Para guardar SIEMPRE debes usar el metodo POST
    @PostMapping("/perfil")
    public Estatus guardar(@RequestBody String json) throws Exception {
        //Paso 1 para recibir ese objeto json es leerlo y convertirlo
        //en objeto JAVA a esto se le llama des-serializacion
        ObjectMapper maper = new ObjectMapper();
        Perfil perfil = maper.readValue(json, Perfil.class);
        //Por experiencia antes de guardar tenemois qyue checar que llego bien
        //todo el objeto y se leyo bien
        System.out.println("Perfil leido " + perfil);
        //este objeto perfil despues se guarda con una sola linea en mongodb
        //Aqu[i va ir la linea para guardar
        repoPerfil.save(perfil);

        //Despues enviamos un mensaje de status al cliente para que se informe
        //si se guardo o no su perfil
        Estatus e = new Estatus();
        e.setSucces(true);
        e.setMensaje("Perfil guardado con exito!!! :D");
        return e;
    }

    //Vamos a generar nuestro servicio para actualizar un perfil
    @PutMapping("/perfil")
    public Estatus actualizar(@RequestBody String json) throws Exception {

        ObjectMapper maper = new ObjectMapper();
        Perfil perfil = maper.readValue(json, Perfil.class);
        System.out.println("Perfil leido " + perfil);

        repoPerfil.save(perfil);

        Estatus e = new Estatus();
        e.setSucces(true);
        e.setMensaje("Perfil actualizado con exito!!! :D");
        return e;
    }

    //El metodo para borrar un perfil
    @DeleteMapping("/perfil/{id}")
    public Estatus borrar(@PathVariable String id) {
        //Invocamos el repositorio 
        repoPerfil.deleteById(id);
        //Generamos el mensaje de estatus para que este informado el cliente
        Estatus e = new Estatus();
        e.setMensaje("Perfil borrado con exito");
        e.setSucces(true);
        return e;
    }

    //El metodo para buscar todos
    @GetMapping("/perfil")
    public List<Perfil> buscarTodos() {
        return repoPerfil.findAll();
    }

    //El metodo para buscar por ID
    @GetMapping("/perfil/{id}")
    public Perfil buscarPorId(@PathVariable String id) {
        return repoPerfil.findById(id).get();
    }
}

//Este tipo de controlador estilo REST es muy poderoso y se usa en todas
//las arquitecturas estilo REST, y se le denomida CONSTRUCCION DE API'S
//API - Application Programming Interface.
//Interfas conexion de dos caracteristicas o dos interfaces 
//como cliente y servidor (java)
//intento