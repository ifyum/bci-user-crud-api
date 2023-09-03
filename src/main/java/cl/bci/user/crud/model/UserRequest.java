package cl.bci.user.crud.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserRequest implements Serializable {
    @Schema(description = "nombre del usuario")
    private String name;
    @Schema(description = "correo del usuario")
    private String email;
    @Schema(description = "contraseña del usuario")
    private String password;

    private List<PhoneVo> phones;
}
