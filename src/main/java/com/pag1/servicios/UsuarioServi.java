
package com.pag1.servicios;

import com.pag1.entidades.Foto;
import com.pag1.entidades.Usuario;
import com.pag1.repositorios.Usuariorepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServi {
@Autowired
private Usuariorepo usu;
@Autowired
FotoServi foto;
@Transactional
public void registrar(String nombre ,String mail, String clave) throws Exception{
    Usuario user = new Usuario();
    user.setNombre(nombre);
    user.setMail(mail);
    user.setClave(clave);
    validar(user);
    usu.save(user);
}
@Transactional
public void modificar(String nombre , String clave, String id) throws Exception{
    Usuario user = usu.buscarporid(id);
    if(id ==null||id.isEmpty()){
        throw new Exception("error id nulo");
    }
    validar(user);
    user.setClave(clave);
    user.setNombre(nombre);
    usu.save(user);
}
@Transactional
public List<Usuario> buscartodo(){
    return usu.findAll();
}
@Transactional
public Usuario buscaruno(String id){
    return usu.buscarporid(id);
    
}
public void validar(Usuario user) throws Exception{
    if(user.getNombre() == null || user.getNombre().isEmpty()){
        throw new Exception("error falta nombre");
    }
    if(user.getClave()==null || user.getClave().isEmpty() || user.getClave().length()<6){
        throw new Exception("error clave vacia o menor que 6");
    }
}
public void cargarfoto(MultipartFile archivo,String idusuario) throws Exception{
    Usuario usuario=usu.buscarporid(idusuario);
    Foto fote=new Foto();
    if(usuario !=null){
        foto.guardar(archivo);
        usuario.setFoto(fote);
        usu.save(usuario);
    }else{throw new Exception("error usuario invalido");}
    
}
}
