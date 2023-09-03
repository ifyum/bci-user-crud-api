package cl.bci.user.crud.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserDetailVo extends UserVo implements Serializable {

    private List<PhoneVo> phones;
}
