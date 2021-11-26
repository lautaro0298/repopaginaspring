
package com.pag1.entidades;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
public class Usuario {
    @Id
    @GeneratedValue(generator="uuid")
    @GenericGenerator(name="uuid" , strategy="uuid2")
    private String Id;
    private String clave;
    private String nombre;
    private String mail;
    @OneToOne
    private Foto foto; 
}
