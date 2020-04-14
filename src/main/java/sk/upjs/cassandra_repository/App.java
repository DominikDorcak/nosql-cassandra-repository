package sk.upjs.cassandra_repository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import sk.upjs.cassandra_repository.simplestudent.SimpleStudentService;

public class App {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =  new AnnotationConfigApplicationContext(CassandraConfig.class);
        SimpleStudentService service = context.getBean(SimpleStudentService.class);
        service.insertAllStudents();
        System.out.println(service.findByPriezvisko("Vuko"));
        System.out.println(service.findBySkratkaakadtitul("Mgr."));
        service.deleteAllStudents();
        context.getBean(Session.class).getCluster().close();
        context.close();
    }
}
