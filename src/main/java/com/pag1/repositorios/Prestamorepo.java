/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pag1.repositorios;

import com.pag1.entidades.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author lautaro
 */
public interface Prestamorepo extends JpaRepository<Prestamo,String>  {
    
}
