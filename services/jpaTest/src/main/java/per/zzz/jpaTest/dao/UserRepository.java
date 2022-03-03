package per.zzz.jpaTest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import per.zzz.jpaTest.entity.User;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/3/2 15:29
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

}
