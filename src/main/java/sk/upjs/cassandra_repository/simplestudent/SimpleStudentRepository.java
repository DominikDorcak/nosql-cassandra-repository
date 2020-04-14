package sk.upjs.cassandra_repository.simplestudent;

import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.stream.Stream;

public interface SimpleStudentRepository extends CrudRepository<CassandraSimpleStudent,Long> {

    List<CassandraSimpleStudent> findByPriezvisko(String priezvisko);
    Stream<CassandraSimpleStudent> findAllBy();
    @AllowFiltering
    List<CassandraSimpleStudent> findBySkratkaakadtitul(String titul);

}
