/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pag1.servicios;

import com.pag1.entidades.Editorial;
import com.pag1.entidades.Libro;
import com.pag1.repositorios.Editorialrepo;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EditorialServi {
    @Autowired
    Editorialrepo edirepo;
     @Transactional
    public void registrar(String nombre) throws Exception{
       
        Editorial edi = new Editorial();
        edi.setNombre(nombre);
        edi.setAlta(true);
        
      validar(edi);
      edirepo.save(edi);
    }
     @Transactional
    public void modificar(String id , String nombre) throws Exception{
        Editorial edi = edirepo.findById(id).orElse(null);
        if(edi != null){
            edi.setNombre(nombre);
            edirepo.save(edi);
        }else{throw new Exception("no se encontro la editorial");}
    }
     @Transactional
    public void borrar(Editorial edi) throws Exception{
        validar(edi);
        if(edirepo.findById(edi.getId()).isPresent()){
            edirepo.delete(edi);
        }else{throw new Exception("no se encontro la editorial");}
    }
    @Transactional
    public void baja(String id){
       Editorial edi=edirepo.findById(id).orElse(null);
       edi.setAlta(false);
    }
    @Transactional
     public void alta(String id){
       Editorial edi=edirepo.findById(id).orElse(null);
       edi.setAlta(true);
    }
    public void validar(Editorial edi) throws Exception{
       /* if(edi.getId()==null || edi.getId().isEmpty()){
         throw new Exception("Id vacio");   
        }*/
        if(edi.getNombre()==null||edi.getNombre().isEmpty()){
            throw new Exception("nombre  vacio");
        }
        
    }
    @Transactional
    public Editorial buscarporid(String Id){
        return edirepo.findById(Id).orElse(null);
    }
    @Transactional
    public List<Editorial> todos(){
        return (List<Editorial>) edirepo.findAll();
    }
}
