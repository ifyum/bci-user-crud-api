package cl.bci.user.crud.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class UserSimpleVo implements Serializable {
    @Schema(description = "identificador interno")
    private UUID id;

    @Schema(description = "nombre del usuario")
    private String name;

    @Schema(description = "correo del usuario")
    private String email;

    @Schema(description = "para determinar si el usuario está activo")
    @JsonProperty(value = "isactive")
    private boolean enable;
}
