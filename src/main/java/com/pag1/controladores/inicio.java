/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pag1.controladores;

import com.pag1.entidades.Libro;
import com.pag1.servicios.LibroServi;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class inicio {

    @Autowired
    LibroServi libS;
    //@PreAuthorize("hasAnyRole('ROLE_USUARIO_REGISTRADO')")

    @GetMapping("/")

    public String hola(ModelMap modelo) {
         List<Libro> libros;
        libros=libS.todos();
        modelo.addAttribute("lista", libros);
        
        return "index";
    }

    @GetMapping("/login")
    public String log() {
        return "login";
    }
    @PostMapping("/login")
   public String secion(@RequestParam String email, @RequestParam String password){
       return("index"); 
   }
}
