package com.agenda.usuarios.com.agenda.telefonica.usuarios.repositorio;

import com.agenda.usuarios.com.agenda.telefonica.usuarios.modelo.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author DUVAN
 */
@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

}
