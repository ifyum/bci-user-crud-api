package cl.bci.user.crud.converters;


import cl.bci.user.crud.entity.Users;
import cl.bci.user.crud.model.UserSimpleVo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntity2UserSimpleVo implements Converter<Users, UserSimpleVo> {
    @Override
    public UserSimpleVo convert(Users user) {
        UserSimpleVo userVo = new UserSimpleVo();

        userVo.setId(user.getId());
        userVo.setName(user.getName());
        userVo.setEmail(user.getEmail());
        userVo.setEnable(user.isEnable());

        return userVo;
    }
}
