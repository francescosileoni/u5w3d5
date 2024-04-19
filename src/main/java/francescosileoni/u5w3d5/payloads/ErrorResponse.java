package francescosileoni.u5w3d5.payloads;

import java.time.LocalDateTime;

public record ErrorResponse(String msg, LocalDateTime timestamp) {
}
