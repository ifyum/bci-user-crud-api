package cl.bci.user.crud.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class PhoneVo implements Serializable {
    @Schema(description = "numero de telefono")
    private String number;

    @Schema(description = "codigo pais")
    private String countryCode;

    @Schema(description = "codigo ciudad")
    private String cityCode;
}
