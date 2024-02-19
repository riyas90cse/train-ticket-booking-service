package com.cloud.bees.challenge.controller;

import com.cloud.bees.challenge.model.User;
import com.cloud.bees.challenge.service.impl.UserService;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Hidden
@Slf4j
@RestController
@RequestMapping(value = "/api")
@Tag(name = "User Controller", description = "Testing Purpose")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users")
    @Operation(summary = "Get Users", description = "Get All Users",
            responses = {@ApiResponse(responseCode = "200", description = "The Users",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = User.class))))
            }
    )
    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(userService.getUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/users/{id}")
    @Operation(summary = "Get User", description = "Get User Details",
            responses = {@ApiResponse(responseCode = "200", description = "The User",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)))})
    public ResponseEntity<User> get(@PathVariable(value = "id") Long id) {
        return new ResponseEntity<>(userService.getUser(id), HttpStatus.OK);
    }

    @PostMapping(value = "/users")
    @Operation(summary = "Create User", description = "Create User",
            responses = {@ApiResponse(responseCode = "201", description = "Create User",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)))})
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        User savedUser = userService.saveUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @PutMapping(value = "/users")
    @Operation(summary = "Update User", description = "Update User",
            responses = {@ApiResponse(responseCode = "200", description = "Update User",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = User.class)))
            })
    public ResponseEntity<User> update(@RequestBody @Valid User user) {
        User savedUser = userService.updateUser(user);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @DeleteMapping(value = "/users/{id}")
    @Operation(summary = "Delete User", description = "Delete User",
            responses = {@ApiResponse(responseCode = "200", description = "Delete The User")})
    public ResponseEntity<String> delete(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
        String message = "User Deleted Successfully";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

}
