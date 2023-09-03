package cl.bci.user.crud.converters;


import cl.bci.user.crud.entity.Users;
import cl.bci.user.crud.model.UserDetailVo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntity2UserDetailVo implements Converter<Users, UserDetailVo> {
    @Override
    public UserDetailVo convert(Users user) {
        UserDetailVo userVo = new UserDetailVo();

        userVo.setId(user.getId());
        userVo.setName(user.getName());
        userVo.setEmail(user.getEmail());
        userVo.setPassword(user.getPassword());
        userVo.setToken(user.getToken());
        userVo.setCreationDate(user.getCreationDate());
        userVo.setLastModifiedDate(user.getLastModifiedDate());
        userVo.setLastLogin(user.getLastLogin());
        userVo.setEnable(user.isEnable());
        return userVo;
    }
}
