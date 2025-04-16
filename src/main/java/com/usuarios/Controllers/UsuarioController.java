package com.usuarios.Controllers;

import com.usuarios.model.Usuarios;
import com.usuarios.repositorio.UsuarioRepository;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
	

    @Autowired
    private UsuarioRepository usuarioRepository;
    
 // Este método es para mostrar el index (solo si lo necesitas)
    @GetMapping("/")
    public String index() {
        return "index"; // Este es el nombre del archivo HTML que contiene el index
    }

 // Este método es para mostrar el formulario (solo si lo necesitas)
    @GetMapping("/eliminar")
    public String mostrarFormularioEliminar() {
        return "eliminarUser"; // Este es el nombre del archivo HTML que contiene el formulario
    }
    
    
 // Procesar la eliminación
    @PostMapping("/eliminar")
    public String eliminarUsuario(@RequestParam("id") Integer id, Model model) {
        Optional<Usuarios> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()) {
            usuarioRepository.delete(usuario.get());
            model.addAttribute("mensaje", "Usuario eliminado con éxito");
        } else {
            model.addAttribute("mensaje", "Usuario no encontrado");
        }
        return "eliminarUser"; // misma vista, ahora con mensaje
    }
    
    @GetMapping("/listar")
    public String listarUsuarios(Model model) {
        List<Usuarios> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "listarUsuarios"; // nombre del archivo HTML en src/main/resources/templates
    }
    
 // Mostrar formulario de inserción de usuario
    @GetMapping("/insertar")
    public String mostrarFormularioInsertar() {
        return "formInsertUser";  // Este es el nombre del archivo HTML
    }
    
    
 // Procesar la inserción de usuario
    @PostMapping("/insertar")
    public String insertarUsuario(@RequestParam("nombre") String nombre, 
                                  @RequestParam("edad") int edad, 
                                  @RequestParam("sexo") String sexo, 
                                  Model model) {
        // Crear un nuevo usuario
        Usuarios nuevoUsuario = new Usuarios();
        nuevoUsuario.setNombre(nombre);
        nuevoUsuario.setEdad(edad);
        nuevoUsuario.setSexo(sexo);

        // Guardar el usuario en la base de datos
        usuarioRepository.save(nuevoUsuario);

        // Agregar un mensaje de éxito
        model.addAttribute("mensaje", "Usuario agregado exitosamente");

        // Redirigir o mostrar un mensaje
        return "formInsertUser";  // O puedes redirigir a alguna otra vista
    }
 // Mostrar formulario de inserción de usuario
    @GetMapping("/buscarUser")
    public String buscarUsuario() {
        return "busquedaUserAjax";  // Este es el nombre del archivo HTML
    }
    
    public static String quitarAcentos(String texto) {
        return Normalizer.normalize(texto, Normalizer.Form.NFD)
                         .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
    }
    
    
 // Procesar la inserción de usuario
    
    @PostMapping("/validarNombre")
    public ResponseEntity<?> validarNombre(@RequestParam String nombreBuscado) {
        try {
            List<Usuarios> users = usuarioRepository.findAll();
            List<Usuarios> userPNombres = new ArrayList<>();

            if (nombreBuscado != null && !nombreBuscado.trim().isEmpty()) {
                for (Usuarios usuario : users) {
                    int size = nombreBuscado.length();
                    int realUserSize = usuario.getNombre().length();
                    if (realUserSize >= size) {
                        if (quitarAcentos(usuario.getNombre().substring(0, size).toLowerCase())
                            .equals(quitarAcentos(nombreBuscado.toLowerCase()))) {
                            userPNombres.add(usuario);
                        }
                    }
                }
            }

            if (userPNombres.isEmpty()) {
                return ResponseEntity.ok("No hay personas con ese nombre");
            }

            return ResponseEntity.ok(userPNombres); // Esto devolverá JSON con usuarios

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno");
        }
    }


}
