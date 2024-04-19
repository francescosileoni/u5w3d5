package francescosileoni.u5w3d5.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name="events")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titolo;
    private String descrizione;
    private LocalDate data;
    private String location;
    private int numeroDiPersone;
    @ManyToOne
    @JoinColumn
    private User user;
}