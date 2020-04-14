package sk.upjs.cassandra_repository;

import com.datastax.driver.core.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sk.upjs.cassandra_repository.student.StudentRepositoryService;

public class App2 {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(CassandraConfig.class);
        StudentRepositoryService service = context.getBean(StudentRepositoryService.class);
        service.insertAllStudents();
        System.out.println(service.getByTitul("RNDr."));
        service.deleteStudents();
        context.getBean(Session.class).getCluster().close();
        context.close();
    }
}
