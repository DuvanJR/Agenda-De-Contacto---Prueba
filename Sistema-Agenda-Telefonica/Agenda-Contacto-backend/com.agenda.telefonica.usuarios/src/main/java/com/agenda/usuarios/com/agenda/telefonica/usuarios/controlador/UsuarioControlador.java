
package com.agenda.usuarios.com.agenda.telefonica.usuarios.controlador;

import com.agenda.usuarios.com.agenda.telefonica.usuarios.excepciones.ResourceNotFoundException;
import com.agenda.usuarios.com.agenda.telefonica.usuarios.modelo.Usuario;
import com.agenda.usuarios.com.agenda.telefonica.usuarios.repositorio.UsuarioRepositorio;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author DUVAN
 */

@RestController
@RequestMapping("/api/v1/")
@CrossOrigin(origins = "http://localhost:4200/")
public class UsuarioControlador {
    
    @Autowired
    private UsuarioRepositorio repositorio;
    
    //Este metodo sirve para listar todos los usuarios/contactos
    @GetMapping("/usuarios")
    public List<Usuario> listarTodosLosUsuarios(){
        return repositorio.findAll();
    }
    
    //Este metodo sirve para guardar el usuario / contacto
    @PostMapping("/usuarios")
    public Usuario guardarUsuario(@RequestBody Usuario usuario){
        return repositorio.save(usuario);
    }
    
    //Este metodo sirve para buscar un Usuario / Contacto
    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable Long id){
            Usuario usuario = repositorio.findById(id)
                              .orElseThrow(() -> new ResourceNotFoundException("No existe el Contacto con el ID: " + id));
            return ResponseEntity.ok(usuario);
    }
    
    
    @PutMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Long id, @RequestBody Usuario detallesUsuario){
            Usuario usuario = repositorio.findById(id)
                              .orElseThrow(() -> new ResourceNotFoundException("No existe el Contacto con el ID: " + id));
            
            usuario.setNombre(detallesUsuario.getNombre());
            usuario.setApellido(detallesUsuario.getApellido());
            usuario.setEmail(detallesUsuario.getEmail());
            usuario.setEmail(detallesUsuario.getEmail());
            usuario.setNumero(detallesUsuario.getNumero());
            usuario.setFecha(detallesUsuario.getFecha());
            usuario.setDireccion(detallesUsuario.getDireccion());
            usuario.setTipoContacto(detallesUsuario.getTipoContacto());
            usuario.setOrigen(detallesUsuario.getOrigen());
            
            Usuario usuarioActualizado = repositorio.save(usuario);
            return ResponseEntity.ok(usuarioActualizado);
    }
    
    
    
    //Este metodo sirve para eliminar un usuario / contacto
    @DeleteMapping("/usuarios/{id}")
    public ResponseEntity<Map<String,Boolean>> eliminarUsuario(@PathVariable Long id){
            Usuario usuario = repositorio.findById(id)
                                       .orElseThrow(() -> new ResourceNotFoundException("No existe el Usuario / Contacto con el ID: " + id));
            repositorio.delete(usuario);
            Map<String, Boolean> respuesta = new HashMap<>();
            respuesta.put("eliminar",Boolean.TRUE);
            return ResponseEntity.ok(respuesta);
    }
}
