package otus.task3.data;

import org.springframework.data.repository.CrudRepository;
import otus.task3.data.model.UserEntity;

public interface UserRepository extends CrudRepository<UserEntity, Long> {
}
