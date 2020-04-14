package sk.upjs.cassandra_repository;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.core.cql.CqlTemplate;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

@Configuration
@EnableCassandraRepositories
@ComponentScan(basePackages = "sk.upjs.cassandra_repository")
public class CassandraConfig extends AbstractCassandraConfiguration {

    static String  HOST = "localhost";
    static String KEYSPACE = "ks_dorcak";

//    @Bean
//    public Session getSession(){
//        Cluster cluster = Cluster.builder().addContactPoint(HOST).build();
//        return cluster.connect(KEYSPACE);
//    }
//    @Bean
//    public CqlTemplate cqlTemplate(Session session){
//        return new CqlTemplate(session);
//    }
//    @Bean
//    public CassandraTemplate cassandraTemplate(Session session){
//        return new CassandraTemplate(session);
//    }

    @Override
    protected String getKeyspaceName() {
        return KEYSPACE;
    }

    @Override
    protected String getContactPoints() {
        return HOST;
    }
}

