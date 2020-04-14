package sk.upjs.cassandra_repository.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.mapping.BasicMapId;
import org.springframework.data.cassandra.core.mapping.MapId;
import org.springframework.stereotype.Service;
import sk.upjs.nosql_data_source.entity.Student;
import sk.upjs.nosql_data_source.persist.DaoFactory;
import sk.upjs.nosql_data_source.persist.StudentDao;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
@Service
public class StudentRepositoryService {
    @Autowired
    StudentRepository repository;
    private StudentDao studentDao = DaoFactory.INSTANCE.getStudentDao();

    public void insertAllStudents(){
        List<Student> students = studentDao.getAll();
        List<CassandraStudent> cStudents = students.stream()
                .map(student -> new CassandraStudent(student)).collect(Collectors.toList());
        repository.saveAll(cStudents);
    }

    public void printStudents(){
        repository.findAll().forEach(System.out::print);
    }

    public void deleteStudents(){
        repository.deleteAll();
    }

    public Collection<NamesOnly> getByTitul(String titul){
        return repository.findBySkratkaakadtitul(titul);
    }

    public CassandraStudent findByIdAndPriezvisko(Long id, String priezvisko){
        MapId mapId = BasicMapId.id("id",id).with("priezvisko",priezvisko);
        return repository.findById(mapId).orElse(null);
    }
}
