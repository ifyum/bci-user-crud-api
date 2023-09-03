package cl.bci.user.crud.controller;

import cl.bci.user.crud.model.UserDetailVo;
import cl.bci.user.crud.model.UserRequest;
import cl.bci.user.crud.model.UserSimpleVo;
import cl.bci.user.crud.model.UserVo;
import cl.bci.user.crud.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Tag(name = "Usuario", description = "Microservicios para administrar usuarios")
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;

    @Operation(summary = "Crear un usuario",
    responses = {
            @ApiResponse(responseCode = "201", description = "Creacion exitosa"),
            @ApiResponse(responseCode = "400", description = "Error en los datos de ingreso"),
            @ApiResponse(responseCode = "409", description = "Email repetido")
    })
    @PostMapping
    public ResponseEntity<UserVo> create(@RequestBody UserRequest userRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.create(userRequest));
    }

    @Operation(summary = "buscar usuarios",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Consulta exitosa")
            })
    @GetMapping
    public ResponseEntity<List<UserSimpleVo>> getUsers() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @Operation(summary = "buscar un usuario",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Consulta exitosa"),
                    @ApiResponse(responseCode = "404", description = "Usuario no existe")
            })
    @GetMapping("/{id}")
    public ResponseEntity<UserDetailVo> getUser(@PathVariable final UUID id) {
        return ResponseEntity.ok().body(userService.getUser(id));
    }

    @Operation(summary = "Actualizar un usuario",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Actulizacion exitosa"),
                    @ApiResponse(responseCode = "400", description = "Error en los datos de ingreso"),
                    @ApiResponse(responseCode = "409", description = "Email repetido")
            })
    @PutMapping("/{id}")
    public ResponseEntity<UserVo> update(@PathVariable final UUID id,
                                         @RequestBody UserRequest userRequest) {
        return ResponseEntity.accepted().body(userService.update(id, userRequest));
    }

    @Operation(summary = "Desactivar usuario",
            responses = {
                    @ApiResponse(responseCode = "202", description = "Eliminacion exitosa"),
                    @ApiResponse(responseCode = "404", description = "Usuario no existe")
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable final UUID id) {
        userService.delete(id);
        return ResponseEntity.accepted().build();
    }
}
