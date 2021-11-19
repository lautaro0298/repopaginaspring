/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pag1.servicios;


import com.pag1.entidades.Autor;
import com.pag1.repositorios.Autorrepo;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AutorServi {
   @Autowired
    private Autorrepo aut ;
    @Transactional
    public void registrar(String nombre) throws Exception{
         Autor autor=new Autor();
         autor.setNombre(nombre);
         autor.setAlta(true);
         autor.setId("hola");
         validar(autor);
        /*if(aut.findById(autor.getId()).isPresent()){
            throw new Exception("el id ya fue registradro");
        }*/
        
        aut.save(autor);
    }
     @Transactional
    public void borrar(Autor autor) throws Exception{
        if(aut.findById(autor.getId()).orElse(null)!=null){
            aut.delete(autor);
            
        }else throw new Exception("no se encontro el usuario");
    }
     @Transactional
    public void modificar(String id,String nombre) throws Exception{
        Autor autor = aut.findById(id).orElse(null);
        autor.setNombre(nombre);
        if(aut.findById(autor.getId()).isPresent()){
            aut.save(autor);
            
        }else throw new Exception("no se enconro el usuario");
    }
    @Transactional
    public Autor buscarporid(String id){
        return aut.findById(id).orElse(null);
    }
    private void validar(Autor autor) throws Exception {
      /*  if(autor.getId()==null || autor.getId().isEmpty()){
            throw new Exception("error id vacio");
        }*/
        if(autor.getNombre()==null||autor.getNombre().isEmpty()){
            throw new Exception("error nombre vacio");
        }
        
    }
     @Transactional
    public List<Autor> todos(){
        return (List<Autor>) aut.findAll();
    }
    
    
}
