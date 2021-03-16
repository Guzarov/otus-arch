package otus.task3.data;

import org.springframework.stereotype.Service;
import otus.task3.data.model.UserEntity;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public UserEntity createUser(String username, String firstName, String lastName, String email, String phone) {
        return repository.save(new UserEntity(username, firstName, lastName, email, phone));
    }

    public boolean deleteUser(Long userId) {
        return repository.findById(userId).map(user -> {
                    repository.delete(user);
                    return user;
        }).isPresent();
    }

    public Optional<UserEntity> findUser(Long userId) {
        return repository.findById(userId);
    }

    public Optional<UserEntity> updateUser(Long userId, String username, String firstName, String lastName, String email, String phone) {
        return repository.findById(userId).map(entity -> {
            entity.setUsername(username);
            entity.setFirstName(firstName);
            entity.setLastName(lastName);
            entity.setEmail(email);
            entity.setPhone(phone);
            return Optional.of(repository.save(entity));
        }).orElse(Optional.empty());
    }
}
