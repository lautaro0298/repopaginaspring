/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pag1.controladores;

import com.pag1.entidades.Autor;
import com.pag1.entidades.Editorial;
import com.pag1.entidades.Libro;
import com.pag1.servicios.AutorServi;
import com.pag1.servicios.EditorialServi;
import com.pag1.servicios.LibroServi;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author lautaro
 */
@Controller
@RequestMapping("/modificar")
public class modificar {
    @Autowired
    LibroServi libroS;
    @Autowired
    AutorServi autorS;
    @Autowired
    EditorialServi ediS  ;      
    @GetMapping("/libro")
    public String modificarL(ModelMap modelo , @RequestParam String id){
        List<Autor> autor;
        autor=autorS.todos();
        modelo.addAttribute("list", autor);
        modelo.put("libro",libroS.buscarporid(id) );
        return "modificarLibro";
        
    }
    @PostMapping("/libro")
    public String ModificarL(ModelMap modelo,@RequestParam String id , @RequestParam String titulo , @RequestParam int anio ,@RequestParam int ejemplares,@RequestParam long isbn ) throws Exception{
        
        libroS.modificar(id, titulo, anio, ejemplares, isbn);
        modelo.put("exito", "modificado exitoso");
        List<Libro> libros;
        libros=libroS.todos();
        modelo.addAttribute("lista", libros);
        return "listadolibro";
    }
        @GetMapping("/autor")
    public String modificarA(ModelMap modelo , @RequestParam String id){
        
        modelo.put("autor",autorS.buscarporid(id) );
        return "modificarautor";
        
    }
    @PostMapping("/autor")
    public String ModificarA(ModelMap modelo,@RequestParam String id , @RequestParam String nombre  ) throws Exception{
        
        autorS.modificar(id, nombre);
        modelo.put("exito", "modificado exitoso");
        List<Autor> autor;
        autor=autorS.todos();
        modelo.addAttribute("lista", autor);
        return "listadoautor";
    }
         @GetMapping("/editorial")
    public String modificarE(ModelMap modelo , @RequestParam String id){
        
        modelo.put("editorial",ediS.buscarporid(id) );
        return "modificareditorial";
        
    }
    @PostMapping("/editorial")
    public String ModificarE(ModelMap modelo,@RequestParam String id , @RequestParam String nombre  ) throws Exception{
         
        ediS.modificar(id , nombre);
        modelo.put("exito", "modificado exitoso");
        List<Editorial> editoriales;
        editoriales=ediS.todos();
        modelo.addAttribute("lista", editoriales);
        return "listadoeditorial";
    }
}
