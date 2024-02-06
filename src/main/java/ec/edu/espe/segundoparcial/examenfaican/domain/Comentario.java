package ec.edu.espe.segundoparcial.examenfaican.domain;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Comentario {
    private int calificacion;
    private String comentario;
    private String usuario;

    @Field("fecha_comentario")
    private Date fechaComentario;
}
