/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pag1.servicios;

import com.pag1.entidades.Autor;
import com.pag1.entidades.Editorial;
import com.pag1.entidades.Foto;
import com.pag1.entidades.Libro;
import com.pag1.repositorios.Autorrepo;
import com.pag1.repositorios.Editorialrepo;
import com.pag1.repositorios.Fotorepo;
import com.pag1.repositorios.Librorepo;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class LibroServi {
    @Autowired
    Librorepo librorepo;
    @Autowired
    Autorrepo auto;
    @Autowired
    Editorialrepo adi;
    @Autowired 
    FotoServi foto;
    
    @Transactional
    public void registrar (String titulo , int anio,int ejemplares , long isbn , Autor aut,Editorial e) throws Exception{
    Libro libro = new Libro();
    
    libro.setTitulo(titulo);
    libro.setAnio(anio);
    libro.setEjemplares(ejemplares);
    libro.setEjemplaresRestantes(ejemplares);
    libro.setIsbn(isbn);
    libro.setEjemplaresPrestados(0);
    libro.setAlta(true);
    
    
    libro.setAutor(aut);
    libro.setEditorial(e);
    validar(libro);
    
    librorepo.save(libro);
    
    }
    @Transactional
    public Libro buscarporid(String id){
        return librorepo.findById(id).orElse(null);
    }
    @Transactional
    public List<Libro> todos(){
        return librorepo.findAll();
    }
    @Transactional
    public void borrar (String id) throws Exception{
        Libro lib=librorepo.findById(id).orElse(null);
        if(lib !=null){
            librorepo.delete(lib);
        }else{throw new Exception("Error no se encontro el libro");}
    
    }
    @Transactional
    public void modificar(String id ,String titulo,int anio,int ejemplares,long isbn) throws Exception{
        Libro lib=this.buscarporid(id);
        if(lib!=null){
            lib.setAnio(anio);
            lib.setEjemplares(ejemplares);
            lib.setIsbn(isbn);
            lib.setTitulo(titulo);
            librorepo.save(lib);
        }else{throw new Exception("Error no se encontro el libro");}
    }
    public void cargarfoto(MultipartFile archivo,String idlib) throws Exception{
    Libro libro = librorepo.getById(idlib);
    Foto fote=new Foto();
    if( libro!=null){
        foto.guardar(archivo);
       libro.setFoto(fote);
       librorepo.save(libro) ;
    }else{throw new Exception("error usuario invalido");}
    
}
    private void validar(Libro libro) throws Exception {
        if(libro.getTitulo()==null||libro.getTitulo().isEmpty()){
        throw new Exception("el titulo esta vacio");
        }
        if(libro.getIsbn()==null||libro.getIsbn()==0){
            throw new Exception("el isbn es invalido");
        }
        if(libro.getAnio()==null||libro.getAnio()==0){
            throw new Exception("el anio es invalido");
            
        }
         if(libro.getEjemplares()==null||libro.getEjemplares()==0){
            throw new Exception("el numero de ejemplares es invalido");
        }
        /* if(libro.getAutor()==null){
            throw new Exception("el autor no fue ingresado");
            
        }
         if(libro.getEditorial()==null){
            throw new Exception("la editorial no fue ingresada");
        }*/
    }
     @Transactional
    public void baja(String id){
       Libro lib=librorepo.findById(id).orElse(null);
       lib.setAlta(false);
    }
    @Transactional
     public void alta(String id){
       Libro lib=librorepo.findById(id).orElse(null);
       lib.setAlta(true);
} }
