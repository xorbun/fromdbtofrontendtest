package brunocapobianco.fromdbtofrontendtest.Entities;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name="utenti")
@Getter
@Setter
@ToString
public class User
{
    @Id
    @GeneratedValue
    private UUID id_user;
    private String nome;
    private String cognome;
    private String email;
    private String datanascita;
    private String avatar;
}
