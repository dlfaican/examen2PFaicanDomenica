package ec.edu.espe.segundoparcial.examenfaican.domain;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Document(collection = "empresas")

public class Empresa {
    @Id
    private String id;

    @Field("ruc")
    private String ruc;

    @Field("razon_social")
    private String razonSocial;

    @Field("fecha_creacion")
    private Date fechaCreacion;

    @Version
    private Long version;

    public Empresa(String id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Empresa other = (Empresa) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "empresa [id=" + id + ", ruc=" + ruc + ", razonSocial=" + razonSocial + ", fechaCreacion="
                + fechaCreacion + ", version=" + version + "]";
    }

    
    
    

   
}

