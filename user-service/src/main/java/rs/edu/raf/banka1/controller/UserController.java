package rs.edu.raf.banka1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rs.edu.raf.banka1.responses.UserResponse;
import rs.edu.raf.banka1.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@Tag(name = "User", description = "User API")
//@SecurityRequirement() TODO
@SecurityRequirement(name = "basicScheme")
@CrossOrigin
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // A method that returns a JSON string with array of type UserResponse
    @GetMapping(value= "/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all users", description = "Returns all users")
    public ResponseEntity<List<UserResponse>> readAllUsers() {
        return new ResponseEntity<>(this.userService.findAll(), HttpStatus.OK);
    }

    // A method that returns a JSON string with type UserResponse
    @GetMapping(value= "/get/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get user by username", description = "Returns user by username")
    public ResponseEntity<UserResponse> readUser(@PathVariable String username) {
        return new ResponseEntity<>(this.userService.findByUsername(username), HttpStatus.OK);
    }

}