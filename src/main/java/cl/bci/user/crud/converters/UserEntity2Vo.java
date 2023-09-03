package cl.bci.user.crud.converters;


import cl.bci.user.crud.entity.Users;
import cl.bci.user.crud.model.UserVo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserEntity2Vo implements Converter<Users, UserVo> {
    @Override
    public UserVo convert(Users user) {
        UserVo userVo = new UserVo();

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
