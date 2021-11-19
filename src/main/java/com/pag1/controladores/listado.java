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
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author lautaro
 */
@Controller
@RequestMapping("/listado")

public class listado {

    @Autowired
    LibroServi libroS;
    @Autowired
    EditorialServi ediS;
    @Autowired
    AutorServi autorS;
    @GetMapping("/todos")

    String listadodetodos() {
       
        return "listadotodos";
    }
    @GetMapping("/libro")

    String listadodelibros(ModelMap model) {
        List<Libro> libros;
        libros=libroS.todos();
        List<Autor> autor;
        autor=autorS.todos();
        model.addAttribute("lista", libros);
        model.addAttribute("list", autor);
        return "listadolibro";
    }
    @GetMapping("/autores")

    String listadodeautor(ModelMap model) {
        List<Autor> autores;
        autores=autorS.todos();
        model.addAttribute("lista", autores);
        return "listadoautor";
    }
    @GetMapping("/editorial")

    String listadodeeditorial(ModelMap model) {
        List<Editorial> editoriales;
        editoriales=ediS.todos();
        
        model.addAttribute("lista", editoriales);
        return "listadoeditorial";
    }
  
}
