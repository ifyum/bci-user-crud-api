package cl.bci.user.crud.test.service.Impl;

import java.util.List;
import java.util.Optional;

import cl.bci.user.crud.model.PhoneVo;
import cl.bci.user.crud.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;

import cl.bci.user.crud.converters.PhoneEntity2Vo;
import cl.bci.user.crud.converters.UserEntity2UserDetailVo;
import cl.bci.user.crud.converters.UserEntity2UserSimpleVo;
import cl.bci.user.crud.converters.UserEntity2Vo;
import cl.bci.user.crud.entity.Users;
import cl.bci.user.crud.exceptions.ValueInUseException;
import cl.bci.user.crud.model.UserRequest;
import cl.bci.user.crud.model.UserVo;
import cl.bci.user.crud.repository.PhoneRepository;
import cl.bci.user.crud.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
class UserServiceImplTest {

    @InjectMocks
    UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @Mock
    PhoneRepository phoneRepository;

    @Mock
    UserEntity2Vo userEntity2Vo;

    @Mock
    UserEntity2UserSimpleVo userEntity2SimpleVo;

    @Mock
    UserEntity2UserDetailVo userEntity2UserDetailVo;

    @Mock
    PhoneEntity2Vo phoneEntity2Vo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(userService, "emailRegex", "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
        ReflectionTestUtils.setField(userService, "passwordRegex", "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    @Test
    void createRequestNull() {
        //Arrange
        //Act
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.create(null));
    }

    @Test
    void createEmailRegexFail() {
        //Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("foo.cl");
        //Act
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.create(userRequest));
    }

    @Test
    void createPswRegexFail() {
        //Arrange
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("foo@bar.cl");
        userRequest.setPassword("1234");
        //Act
        //Assert
        Assertions.assertThrows(IllegalArgumentException.class, () -> userService.create(userRequest));
    }

    @Test
    void createEmailExiste() {
        //Arrange
        Users userMail = new Users();
        userMail.setEmail("foo@bar.cl");
        Optional<Users> userMailOp = Optional.of(userMail);

        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("foo@bar.cl");
        userRequest.setPassword("1234pepito");

        Mockito.when(userRepository.findByEmail("foo@bar.cl")).thenReturn(userMailOp);
        //Act
        //Assert
        Assertions.assertThrows(ValueInUseException.class, () -> userService.create(userRequest));
    }

    @Test
    void createZeroPhones() {
        //Arrange
        UserVo userVo = new UserVo();

        UserRequest userRequest = new UserRequest();
        userRequest.setName("Foo Bar");
        userRequest.setEmail("foo@bar.cl");
        userRequest.setPassword("1234pepito");

        Mockito.when(userRepository.findByEmail("foo@bar.cl")).thenReturn(Optional.empty());
        Mockito.when(userEntity2Vo.convert(Mockito.any())).thenReturn(userVo);
        //Act
        UserVo result = userService.create(userRequest);
        //Assert
        assertThat(result).isNotNull();
        verify(userRepository, times(1)).save(any());
        verify(phoneRepository, never()).save(any());
    }

    @Test
    void createTwoPhones() {
        //Arrange
        UserVo userVo = new UserVo();

        PhoneVo phone1 = new PhoneVo();
        phone1.setNumber("12345");
        phone1.setCityCode("12345");
        phone1.setCountryCode("12345");
        PhoneVo phone2 = new PhoneVo();
        phone2.setNumber("12345");
        phone2.setCityCode("12345");
        phone2.setCountryCode("12345");

        UserRequest userRequest = new UserRequest();
        userRequest.setName("Foo Bar");
        userRequest.setEmail("foo@bar.cl");
        userRequest.setPassword("1234pepito");
        userRequest.setPhones(List.of(phone1, phone2));

        Mockito.when(userRepository.findByEmail("foo@bar.cl")).thenReturn(Optional.empty());
        Mockito.when(userEntity2Vo.convert(Mockito.any())).thenReturn(userVo);
        //Act
        UserVo result = userService.create(userRequest);
        //Assert
        assertThat(result).isNotNull();
        verify(userRepository, times(1)).save(any());
        verify(phoneRepository, times(2)).save(any());
    }
}
