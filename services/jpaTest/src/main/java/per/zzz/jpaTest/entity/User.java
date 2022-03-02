package per.zzz.jpaTest.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/3/2 15:31
 */
@Data
@Entity
@Table(name = "t_user")
public class User {

    @Id
    private Integer id;

    @Column(name = "username")
    private String userName;
}
