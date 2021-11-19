/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pag1.repositorios;

import com.pag1.entidades.Autor;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface Autorrepo extends CrudRepository<Autor,String> {

    
    
}
