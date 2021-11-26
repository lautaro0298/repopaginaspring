
package com.pag1.servicios;

import com.pag1.entidades.Foto;
import com.pag1.repositorios.Fotorepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


 @Service
public class FotoServi {
    @Autowired
    private Fotorepo fot;
    public Foto guardar(MultipartFile archivo) throws Exception{
        if(archivo != null){
            Foto foto = new Foto();
            foto.setMime(archivo.getContentType());
            foto.setNombre(archivo.getName());
            foto.setContenido(archivo.getBytes());
            return fot.save(foto);
        }else{return null;}
    }
    public Foto actualizar(String idFoto,MultipartFile archivo) throws Exception{
         if(archivo != null){
            Foto foto = new Foto();
            if(idFoto != null){
                Optional<Foto> respuesta=fot.findById(idFoto);
                if(respuesta.isPresent()){
                    foto= respuesta.get();
                }
            }
            foto.setMime(archivo.getContentType());
            foto.setNombre(archivo.getName());
            foto.setContenido(archivo.getBytes());
            return fot.save(foto);
        }else{return null;}
    }
}
