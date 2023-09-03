package cl.bci.user.crud.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserVo implements Serializable {
    @Schema(description = "identificador interno")
    private UUID id;

    @Schema(description = "nombre del usuario")
    private String name;

    @Schema(description = "correo del usuario")
    private String email;

    @Schema(description = "contraseña del usuario")
    private String password;

    @Schema(description = "token de sesion")
    private String token;

    @Schema(description = "fecha en que se creo el usuario")
    @JsonProperty(value = "created")
    private Instant creationDate;

    @Schema(description = "ultima fecha en que se modifico el usuario")
    @JsonProperty(value = "modified")
    private Instant lastModifiedDate;

    @Schema(description = "ultima fecha en que se inicio sesion")
    @JsonProperty(value = "last_login")
    private Instant lastLogin;

    @Schema(description = "para determinar si el usuario está activo")
    @JsonProperty(value = "isactive")
    private boolean enable;
}
