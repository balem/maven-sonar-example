package py.una.pol.mavensonarexample.entity;

import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Entity
@Table(name = "configurations")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ParamConfiguration {
    @Id
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false)
    @EqualsAndHashCode.Include
    private Integer id;
    @Column(name = "clave", nullable = false, length = 60)
    private String key;
    @Column(name = "value", nullable = false, length = 60)
    private String value;

}
