package otus.task2.data;

import org.springframework.data.repository.CrudRepository;
import otus.task2.data.model.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
