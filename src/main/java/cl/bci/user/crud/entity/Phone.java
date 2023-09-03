package cl.bci.user.crud.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "phone")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Phone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "number", nullable = false)
    private String number;

    @Column(name = "country_code", nullable = false)
    private String countryCode;

    @Column(name = "city_code", nullable = false)
    private String cityCode;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;
}
