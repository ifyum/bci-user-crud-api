package cl.bci.user.crud.service;



import cl.bci.user.crud.model.UserDetailVo;
import cl.bci.user.crud.model.UserRequest;
import cl.bci.user.crud.model.UserSimpleVo;
import cl.bci.user.crud.model.UserVo;

import java.util.List;
import java.util.UUID;

public interface UserService {
    UserVo create(final UserRequest userRequest);

    List<UserSimpleVo> getUsers();
    UserDetailVo getUser(final UUID id);

    UserVo update(final UUID id, final UserRequest userRequest);

    void delete(final UUID id);
}
