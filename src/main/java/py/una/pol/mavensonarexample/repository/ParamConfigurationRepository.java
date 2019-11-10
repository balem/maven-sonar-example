package py.una.pol.mavensonarexample.repository;

import org.springframework.data.repository.CrudRepository;
import py.una.pol.mavensonarexample.entity.ParamConfiguration;

public interface ParamConfigurationRepository extends CrudRepository<ParamConfiguration, Integer> {

    ParamConfiguration findByKeyAndValue(String key, String value);

}
