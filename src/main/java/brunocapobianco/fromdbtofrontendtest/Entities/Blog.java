package brunocapobianco.fromdbtofrontendtest.Entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;


@Entity
@Table(name="Blog")
@Getter
@Setter
@ToString

public class Blog
{
   @Id
    @GeneratedValue
    private UUID id_blog;
    private String categoria;
    private String titolo;
    private String cover;
    private String contenuto;
    private int tempodilettura;
    @ManyToOne
    @JoinColumn(name = "id_user",nullable = false)
    private User user;
}
