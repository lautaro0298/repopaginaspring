
package com.pag1.repositorios;

import com.pag1.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface Usuariorepo extends JpaRepository<Usuario,String> {
    
@Query("SELECT u FROM Usuario u WHERE u.id = :id")
public Usuario buscarporid(@Param("id") String id);

    
}
