package py.una.pol.mavensonarexample.entity;

import javax.persistence.*;

@Entity
@Table(name = "configurations")
public class ParamConfiguration {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;
    @Column(name = "clave", nullable = false, length = 60)
    private String key;
    @Column(name = "value", nullable = false, length = 60)
    private String value;

}
