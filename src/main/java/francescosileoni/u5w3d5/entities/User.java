package francescosileoni.u5w3d5.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import francescosileoni.u5w3d5.enums.RuoloUser;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@JsonIgnoreProperties({"eventList","password","role","authorities", "accountNonExpired", "credentialsNonExpired", "accountNonLocked", "enabled"})
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nome;
    private String cognome;
    private LocalDate dataDiNascita;
    private String avatar;
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private RuoloUser ruolo;
    @OneToMany(mappedBy = "user")
    private List<Evento> eventList;

    public User(String nome, String cognome, LocalDate dataDiNascita, String avatar, String email, String password,RuoloUser ruolo) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.avatar = avatar;
        this.email = email;
        this.password = password;
        this.ruolo=RuoloUser.USER;

    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.ruolo.name()));
    }
}
