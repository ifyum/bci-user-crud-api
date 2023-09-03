package cl.bci.user.crud.test.controller;

import cl.bci.user.crud.controller.UserController;
import cl.bci.user.crud.model.UserDetailVo;
import cl.bci.user.crud.model.UserRequest;
import cl.bci.user.crud.model.UserSimpleVo;
import cl.bci.user.crud.model.UserVo;
import cl.bci.user.crud.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateUser() {
        // Configuración de prueba
        UserRequest userRequest = new UserRequest(); // Proporciona datos de prueba adecuados

        UserVo userVo = new UserVo(); // Proporciona un UserVo simulado
        when(userService.create(userRequest)).thenReturn(userVo);

        // Ejecución de la prueba
        ResponseEntity<UserVo> responseEntity = userController.create(userRequest);

        // Verificación de resultados
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(userVo, responseEntity.getBody());
    }

    @Test
    public void testGetUsers() {
        // Configuración de prueba
        List<UserSimpleVo> userSimpleVos = new ArrayList<>(); // Proporciona una lista de UserSimpleVo simulados
        when(userService.getUsers()).thenReturn(userSimpleVos);

        // Ejecución de la prueba
        ResponseEntity<List<UserSimpleVo>> responseEntity = userController.getUsers();

        // Verificación de resultados
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userSimpleVos, responseEntity.getBody());
    }

    @Test
    public void testGetUser() {
        // Configuración de prueba
        UUID userId = UUID.randomUUID(); // Proporciona un ID de usuario simulado
        UserDetailVo userDetailVo = new UserDetailVo(); // Proporciona un UserDetailVo simulado
        when(userService.getUser(userId)).thenReturn(userDetailVo);

        // Ejecución de la prueba
        ResponseEntity<UserDetailVo> responseEntity = userController.getUser(userId);

        // Verificación de resultados
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(userDetailVo, responseEntity.getBody());
    }

    @Test
    public void testUpdateUser() {
        // Configuración de prueba
        UUID userId = UUID.randomUUID(); // Proporciona un ID de usuario simulado
        UserRequest userRequest = new UserRequest(); // Proporciona datos de actualización simulados

        UserVo updatedUserVo = new UserVo(); // Proporciona un UserVo simulado actualizado
        when(userService.update(userId, userRequest)).thenReturn(updatedUserVo);

        // Ejecución de la prueba
        ResponseEntity<UserVo> responseEntity = userController.update(userId, userRequest);

        // Verificación de resultados
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        assertEquals(updatedUserVo, responseEntity.getBody());
    }

    @Test
    public void testDeleteUser() {
        // Configuración de prueba
        UUID userId = UUID.randomUUID(); // Proporciona un ID de usuario simulado

        // Ejecución de la prueba
        ResponseEntity<Void> responseEntity = userController.update(userId);

        // Verificación de resultados
        assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());
        verify(userService, times(1)).delete(userId);
    }
}
