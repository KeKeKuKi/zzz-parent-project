package per.zzz.jpaTest.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author 阿忠 2669918628@qq.com
 * @since 2022/3/2 15:31
 */
@Data
@Entity
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "username")
    private String userName;
}
