package warsztaty.spring.ailleron.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import warsztaty.spring.ailleron.exceptions.UserExistsException;
import warsztaty.spring.ailleron.exceptions.UserNotFoundException;
import warsztaty.spring.ailleron.model.User;
import warsztaty.spring.ailleron.services.UserService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class UserController {

    @Autowired
    UserService userService;
//
//    @GetMapping("/users/{name}")
//    public Optional<User> getUserByName(@PathVariable String name) throws UserNotFoundException {
//        return userService.getUserByName(name);
//    }


    @GetMapping("/users/{id}")
    public User getUserById(@PathVariable Long id) throws UserNotFoundException {
        return userService.getUserById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public ResponseEntity<Resource<Long>> addUser(@RequestBody @Valid User user) throws UserExistsException, UserNotFoundException {
        long userId = userService.addUser(user);
        Resource<Long> resource = new Resource<>(userId);
        ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).getUserById(userId));
        resource.add(linkTo.withRel("get-user"));

        return ResponseEntity.status(HttpStatus.CREATED).body(resource);
    }


    @PutMapping("/users")
    public User modifyUser(@RequestBody User user) throws UserNotFoundException {
        return userService.modifyUser(user);
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable long id) throws UserNotFoundException {
        userService.deleteUser(id);
    }
}