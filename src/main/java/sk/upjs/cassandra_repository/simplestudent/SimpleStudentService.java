package sk.upjs.cassandra_repository.simplestudent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sk.upjs.nosql_data_source.entity.SimpleStudent;
import sk.upjs.nosql_data_source.persist.DaoFactory;
import sk.upjs.nosql_data_source.persist.StudentDao;

import java.util.List;
import java.util.stream.Stream;

@Service
public class SimpleStudentService {

    @Autowired
    private SimpleStudentRepository repository;

    private StudentDao studentDao = DaoFactory.INSTANCE.getStudentDao();


    public void insertStudent(){
        List<SimpleStudent> students = studentDao.getSimpleStudents();
        CassandraSimpleStudent cStudent = new CassandraSimpleStudent(students.get(0));
        repository.save(cStudent);
    }

    public void insertAllStudents(){
        studentDao.getSimpleStudents().forEach(simpleStudent -> {
            CassandraSimpleStudent cStudent = new CassandraSimpleStudent(simpleStudent);
            repository.save(cStudent);
        });

    }

    public void printStudents(){
        repository.findAll().forEach(System.out::println);
    }

    public void deleteAllStudents(){
        repository.deleteAll();
    }

    public List<CassandraSimpleStudent> findBySkratkaakadtitul(String titul){
        return repository.findBySkratkaakadtitul(titul);
    }

    public List<CassandraSimpleStudent> findByPriezvisko(String priezvisko){
        return repository.findByPriezvisko(priezvisko);
    }

    public Stream<CassandraSimpleStudent> findAllByStream(){
        return repository.findAllBy();
    }
}
