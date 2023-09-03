package cl.bci.user.crud.service.Impl;


import cl.bci.user.crud.converters.PhoneEntity2Vo;
import cl.bci.user.crud.converters.UserEntity2UserDetailVo;
import cl.bci.user.crud.converters.UserEntity2UserSimpleVo;
import cl.bci.user.crud.converters.UserEntity2Vo;
import cl.bci.user.crud.exceptions.ApplicationException;
import cl.bci.user.crud.exceptions.ValueInUseException;
import cl.bci.user.crud.entity.Phone;
import cl.bci.user.crud.entity.Users;
import cl.bci.user.crud.model.UserDetailVo;
import cl.bci.user.crud.model.UserRequest;
import cl.bci.user.crud.model.UserSimpleVo;
import cl.bci.user.crud.model.UserVo;
import cl.bci.user.crud.repository.PhoneRepository;
import cl.bci.user.crud.repository.UserRepository;
import cl.bci.user.crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Value("${email.regex.regexp}")
    private String emailRegex;

    @Value("${password.regex.regexp}")
    private String passwordRegex;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PhoneRepository phoneRepository;

    @Autowired
    UserEntity2Vo userEntity2Vo;

    @Autowired
    UserEntity2UserSimpleVo userEntity2SimpleVo;

    @Autowired
    UserEntity2UserDetailVo userEntity2UserDetailVo;

    @Autowired
    PhoneEntity2Vo phoneEntity2Vo;

    @Override
    @Transactional
    public UserVo create(final UserRequest userRequest) {
        if (userRequest == null) {
            throw new IllegalArgumentException("Request its required");
        }

        if(!Pattern.compile(emailRegex).matcher(userRequest.getEmail()).find()) {
            throw new IllegalArgumentException("Correo no cumple formato");
        }

        if(!Pattern.compile(passwordRegex).matcher(userRequest.getPassword()).find()) {
            throw new IllegalArgumentException("Contraseña no cumple formato");
        }

        if (userRepository.findByEmail(userRequest.getEmail()).isPresent()) {
            throw new ValueInUseException("El correo ya exite");
        }

        Users user = new Users();
        user.setId(UUID.randomUUID());
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setToken(UUID.randomUUID().toString());
        user.setCreationDate(Instant.now());
        user.setLastLogin(Instant.now());
        user.setEnable(true);

        userRepository.save(user);

        phoneSave(userRequest,user);

        return userEntity2Vo.convert(user);
    }
   @Transactional
    public void phoneSave(UserRequest userRequest, Users user) {
        if (userRequest.getPhones() != null) {
            userRequest.getPhones().forEach(phoneVo -> {
                Phone phone = new Phone();
                phone.setNumber(phoneVo.getNumber());
                phone.setCityCode(phoneVo.getCityCode());
                phone.setCountryCode(phoneVo.getCountryCode());
                phone.setUser(user);
                phoneRepository.save(phone);
            });
        }

    }


    @Override
    public List<UserSimpleVo> getUsers() {
        return userRepository.findAll().stream().map(userEntity2SimpleVo::convert).collect(Collectors.toList());
    }

    @Override
    public UserDetailVo getUser(final UUID id) {
        UserDetailVo user = userRepository.findById(id).map(userEntity2UserDetailVo::convert).orElseThrow(() -> new ApplicationException("Usuario no existe", HttpStatus.NOT_FOUND));
        user.setPhones(phoneRepository.findByUserId(user.getId()).stream().map(phoneEntity2Vo::convert).collect(Collectors.toList()));

        return user;
    }

    @Override
    public UserVo update(final UUID id, final UserRequest userRequest) {
        if (userRequest == null) {
            throw new IllegalArgumentException("Request its required");
        }

        Optional<Users> userEmail = userRepository.findByEmail(userRequest.getEmail());
        if (userEmail.isPresent() && !userEmail.get().getId().equals(id)) {
            throw new ValueInUseException("El correo ya exite");
        }

        Optional<Users> userBd = userRepository.findById(id);

        if (userBd.isEmpty()) {
            throw new ApplicationException("usuario no existe", HttpStatus.NOT_FOUND);
        }

        Users user = userBd.get();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPassword(userRequest.getPassword());
        user.setLastModifiedDate(Instant.now());

        userRepository.save(user);

        phoneRepository.deleteAll(phoneRepository.findByUserId(id));
        phoneSave(userRequest,user);

        return userEntity2Vo.convert(user);
    }

    @Override
    public void delete(final UUID id) {
        Users user = userRepository.findById(id).orElseThrow(() -> new ApplicationException("Usuario no existe", HttpStatus.NOT_FOUND));

        user.setEnable(false);

        userRepository.save(user);
    }
}
