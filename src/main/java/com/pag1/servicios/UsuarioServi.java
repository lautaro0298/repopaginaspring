
package com.pag1.servicios;

import com.pag1.entidades.Foto;
import com.pag1.entidades.Usuario;
import com.pag1.repositorios.Usuariorepo;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UsuarioServi implements UserDetailsService {
@Autowired
private Usuariorepo usu;
@Autowired
FotoServi foto;
@Transactional
public void registrar(String nombre ,String mail, String clave)  throws Exception{
    Usuario user = new Usuario();
    
    user.setNombre(nombre);
    user.setMail(mail);
    String encriptada = new BCryptPasswordEncoder().encode(clave);
    user.setClave(encriptada);
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

    @Override
    public UserDetails loadUserByUsername(String mail) throws UsernameNotFoundException {
   Usuario usuario = usu.buscarpormail(mail);
        if (usuario != null) {
            List<GrantedAuthority> permisos = new ArrayList<>();
                        
            GrantedAuthority p1 = new SimpleGrantedAuthority("ROLE_USUARIO_REGISTRADO");
            permisos.add(p1);
         
            //Esto me permite guardar el OBJETO USUARIO LOG, para luego ser utilizado
            ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
            HttpSession session = attr.getRequest().getSession(true);
            session.setAttribute("usuariosession", usuario);

            User user = new User(usuario.getMail(), usuario.getClave(), permisos);
            return user;

        } else {
            return null;
        }
    }
    public Usuario buscarpormail(String mail){
        Usuario user=usu.buscarpormail(mail);
        return user;
    }
}
