package academy.mindswap.finalproject.repository;

import academy.mindswap.finalproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
