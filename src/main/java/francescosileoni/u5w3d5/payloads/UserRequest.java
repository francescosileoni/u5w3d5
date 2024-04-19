package francescosileoni.u5w3d5.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record UserRequest(
        @NotEmpty(message = "Campo obbligatorio")
        @Size(min = 5,max = 50, message = "Nome incorretto: min5 max 40 caratteri")
        String nome,
        @NotEmpty(message = "Campo obbligatorio")
        @Size(min = 5,max = 50, message = "Cognome errato: min5 max 40 caratteri")
        String cognome,
        @NotNull(message = "data di nascita obbligatoria")
        LocalDate dataDiNascita,
        @NotEmpty(message = "Campo obbligatorio")
        @Email(message = "email non valida")
        String email,
        @NotEmpty(message = "Campo obbligario")
        @Size(min = 8, message = "la password deve avere un minimo di 8 caratteri")
        String password

) {
}
