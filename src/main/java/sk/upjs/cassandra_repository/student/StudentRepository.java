package sk.upjs.cassandra_repository.student;

import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface StudentRepository extends CrudRepository<CassandraStudent, MapId> {
    @AllowFiltering
    Collection<NamesOnly> findBySkratkaakadtitul(String titul);
}
