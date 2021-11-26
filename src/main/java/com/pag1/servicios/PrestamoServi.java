/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pag1.servicios;

import com.pag1.entidades.Libro;
import com.pag1.entidades.Prestamo;
import com.pag1.repositorios.Librorepo;
import com.pag1.repositorios.Prestamorepo;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lautaro
 */
public class PrestamoServi {
    @Autowired 
    Prestamorepo presrep;
    @Autowired
    Librorepo librep;
    @Autowired
    UsuarioServi usu;
    @Transactional
    public void nuevopresta(@RequestParam String idlib,@RequestParam String idcli)throws Exception{
        Libro lib = librep.findById(idlib).orElse(null);
        if(lib !=null || idcli !=null){
        Prestamo presta=new Prestamo();
        lib.setEjemplaresPrestados(lib.getEjemplaresRestantes()-1);
        presta.setLibro(lib);
        presta.setUsuario(usu.buscaruno(idcli));
        presta.setFechaprestamo(new Date());
       presrep.save(presta);}
    else{  throw new Exception("error") ;}
}}
