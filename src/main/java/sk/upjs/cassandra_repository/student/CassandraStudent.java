package sk.upjs.cassandra_repository.student;

import com.datastax.driver.core.DataType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.core.cql.Ordering;
import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import sk.upjs.nosql_data_source.entity.Student;
import sk.upjs.nosql_data_source.entity.Studium;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Table("student")

/*
CREATE TABLE IF NOT EXISTS student (
   id bigint,
   meno text,
   priezvisko text,
   titul text,
   studia list<FROZEN<studium>>,
   PRIMARY KEY(id,priezvisko)
);

CREATE FUNCTION skratky (studia list<FROZEN<studium>>)
    RETURNS NULL ON NULL INPUT
    RETURNS list<text>
    LANGUAGE java AS
    '
    List<String> result = new ArrayList<String>();
    for(UDTValue st: studia){
        result.add(st.getString("skratka"));
    }
    return result;
    '
;
* */
public class CassandraStudent {
    @PrimaryKeyColumn(ordinal = 0,type = PrimaryKeyType.PARTITIONED)
    private Long id;
    @Column
    private String meno;
    @PrimaryKeyColumn(ordinal = 1,type = PrimaryKeyType.CLUSTERED,ordering = Ordering.ASCENDING)
    private String priezvisko;
    @Transient
    private char kodpohlavie;
    @Column("titul")
    private String skratkaakadtitul;
    @Column("studia")
    @CassandraType(type = DataType.Name.LIST, userTypeName = "studium", typeArguments = {DataType.Name.UDT})
    private List<CassandraStudium> studium = new ArrayList<>();

    public CassandraStudent() {
    }

    public CassandraStudent(Student student) {
        id = student.getId();
        meno = student.getMeno();
        priezvisko = student.getPriezvisko();
        kodpohlavie = student.getKodpohlavie();
        skratkaakadtitul = student.getSkratkaakadtitul();
        studium = student.getStudium().stream()
                .map(st -> new CassandraStudium(st)).collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "CassandraStudent{" +
                "id=" + id +
                ", meno='" + meno + '\'' +
                ", priezvisko='" + priezvisko + '\'' +
                ", kodpohlavie=" + kodpohlavie +
                ", skratkaakadtitul='" + skratkaakadtitul + '\'' +
                ", studium=" + studium +
                '}' + "\n";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMeno() {
        return meno;
    }

    public void setMeno(String meno) {
        this.meno = meno;
    }

    public String getPriezvisko() {
        return priezvisko;
    }

    public void setPriezvisko(String priezvisko) {
        this.priezvisko = priezvisko;
    }

    public char getKodpohlavie() {
        return kodpohlavie;
    }

    public void setKodpohlavie(char kodpohlavie) {
        this.kodpohlavie = kodpohlavie;
    }

    public String getSkratkaakadtitul() {
        return skratkaakadtitul;
    }

    public void setSkratkaakadtitul(String skratkaakadtitul) {
        this.skratkaakadtitul = skratkaakadtitul;
    }

    public List<CassandraStudium> getStudium() {
        return studium;
    }

    public void setStudium(List<CassandraStudium> studium) {
        this.studium = studium;
    }
}
