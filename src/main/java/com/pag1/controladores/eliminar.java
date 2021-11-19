/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pag1.controladores;

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
@RequestMapping("/borrar")
public class eliminar {
    @Autowired
    LibroServi lib;
    @Autowired
    EditorialServi edi;
    @Autowired
    AutorServi aut;
    @PostMapping("/libro")
    public String borrarlib(ModelMap modelo , @RequestParam String id) throws Exception{
        lib.borrar(id);
        List<Libro> libros;
        libros=lib.todos();
        modelo.addAttribute("lista", libros);
        return"listadoLibro";
    }
    @PostMapping("/editorial")
    public String borraredi(ModelMap modelo, @RequestParam String id) throws Exception{
        edi.borrar(edi.buscarporid(id));
        modelo.addAttribute("lista", edi.todos());
        return"listadoeditorial";
        
    }
     @PostMapping("/autor")
    public String borrareaut(ModelMap modelo, @RequestParam String id) throws Exception{
        aut.borrar(aut.buscarporid(id));
        modelo.addAttribute("lista", aut.todos());
        return"listadoautor";}
        
}
