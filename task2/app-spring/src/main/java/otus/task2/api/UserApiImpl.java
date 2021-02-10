package otus.task2.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import otus.task2.data.UserService;
import otus.task2.data.model.UserEntity;
import otus.task2.model.User;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class UserApiImpl implements UserApi {

    private final UserService service;

    public UserApiImpl(UserService service) {
        this.service = service;
    }

    @Override
    public ResponseEntity<Void> createUser(@Valid User user) {
        UserEntity createdUser = service.createUser(
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone()
        );
        return ResponseEntity.created(getLocation(createdUser)).build();
    }

    private URI getLocation(UserEntity createdUser) {
        return ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdUser.getId())
                .toUri();
    }

    @Override
    public ResponseEntity<Void> deleteUser(Long userId) {
        if(service.deleteUser(userId)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @Override
    public ResponseEntity<User> findUserById(Long userId) {
        return service.findUser(userId).map(user ->
                ResponseEntity.ok(
                        new User()
                                .id(user.getId())
                                .username(user.getUsername())
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                .email(user.getEmail())
                                .phone(user.getPhone())
                )
        ).orElse(
                ResponseEntity.badRequest().build()
        );
    }

    @Override
    public ResponseEntity<Void> updateUser(Long userId, @Valid User user) {
        return service.updateUser(
                userId,
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhone()
        ).map((unused) ->
                ResponseEntity.noContent().<Void>build()
        ).orElse(
                ResponseEntity.badRequest().build()
        );
    }
}
