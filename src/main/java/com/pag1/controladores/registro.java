/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.pag1.controladores;

import com.pag1.entidades.Libro;
import com.pag1.servicios.AutorServi;
import com.pag1.servicios.EditorialServi;
import com.pag1.servicios.LibroServi;
import com.pag1.servicios.UsuarioServi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author lautaro
 */
@Controller
@RequestMapping("/crud")
public class registro {
    @Autowired
    LibroServi libroS;
    @Autowired
    AutorServi autorS;
    @Autowired
    EditorialServi editorialS;
    @Autowired
    UsuarioServi usuS;
    
            
@GetMapping("/todos")
public String todos(){
    return "registrotodos";
}
@GetMapping("/libro")
public String registrolibro(ModelMap modelo){
    modelo.addAttribute("list", autorS.todos());
    modelo.addAttribute("lists", editorialS.todos());
    return "registroLibro";
}
  @PostMapping("/libro")
  public String crearlibro(ModelMap modelo ,MultipartFile archivo, @RequestParam String titulo ,@RequestParam String descripcion, @RequestParam int anio ,@RequestParam int ejemplares,@RequestParam long isbn,@RequestParam String idautor, @RequestParam String ideditorial ) throws Exception {
      try{
          
      libroS.registrar(archivo,titulo ,descripcion, anio,ejemplares ,isbn ,autorS.buscarporid(idautor),editorialS.buscarporid(ideditorial));
      modelo.put("exito", "registro exitoso");
      return "registroLibro"; 
   
      }catch(Exception e){
          modelo.put("error", "falto un dato");
          return "registroLibro";        
      }
      
}
  @GetMapping("/autor")
public String registroautor(){
    return "registroautor";
}
  @PostMapping("/autor")
  public String crearautor(ModelMap modelo , @RequestParam String nombre  ) throws Exception {
      try{
      autorS.registrar(nombre);
      modelo.put("exito", "registro exitoso");
      return "registroautor";
      }catch(Exception e){
          modelo.put("error", "falto un dato");
          return "registroautor";        
      }
  }
  @GetMapping("/editorial")
public String registroeditorial(){
    return "registroeditorial";
}
  @PostMapping("/editorial")
  public String creareditorial(ModelMap modelo , @RequestParam String nombre ) throws Exception {
      try{
      editorialS.registrar(nombre);
      modelo.put("exito", "registro exitoso");
      return "listadoeditorial";
      }catch(Exception e){
          modelo.put("error", "falto un dato");
          return "listadoeditorial";        
      }
}
  @GetMapping("/usuario")
  public String registrarusuario(ModelMap modelo){
      return "registrousuario";
  }
  @PostMapping("/usuario")
  public String registrousuari(ModelMap modelo ,@RequestParam String email, @RequestParam String password) throws Exception{
     try{ 
         usuS.registrar(email, email, password);
         return"index";
     }
     catch(Exception e ){throw new Exception("Error");}
      
    
  }
}
