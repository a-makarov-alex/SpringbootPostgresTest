package first;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "firsttable")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private int age;
}
