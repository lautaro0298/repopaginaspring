
package com.pag1.controladores;

import com.pag1.servicios.AutorServi;
import com.pag1.servicios.EditorialServi;
import com.pag1.servicios.LibroServi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/alta")
public class baja {
    @Autowired
    LibroServi lib ;
    @Autowired
    EditorialServi edi;
    @Autowired
    AutorServi aut;
    @GetMapping("/librobaja")
    public String bajalibro(@RequestParam String id){
        lib.baja(id);
        return"redirect:/listado/libro";
       }
     @GetMapping("/libroalta")
    public String altalibro(@RequestParam String id){
        lib.alta(id);
        return"redirect:/listado/libro";
       }
     @GetMapping("/autoralta")
    public String bajaautor(@RequestParam String id){
        aut.alta(id);
        return"redirect:/listado/autor";
       }
     @GetMapping("/autorbaja")
    public String altaautor(@RequestParam(name="id") String id){
        aut.baja(id);
        return"redirect:/listado/autor";
       }
     @GetMapping("/editorialalta")
    public String altaeditorial(@RequestParam String id){
        lib.alta(id);
        return"redirect:/listado/editorial";
       }
     @GetMapping("/editorialbaja")
    public String bajaeditorial(@RequestParam String id){
        lib.baja(id);
        return"redirect:/listado/editorial";

    }
}
