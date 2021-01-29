package meesho.mohsin;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import java.util.Random;

public class App {
    public static void main(String[] args) {

        Laptop laptop = new Laptop();
        laptop.setLid(101);
        laptop.setLname("Dell");

        Student student = new Student();
        student.setRollno(1);
        student.setName("Navin");
        student.setMarks(45);
        student.getLaptop().add(laptop);

        laptop.getStudent().add(student);

        Configuration con = new Configuration().configure().addAnnotatedClass(Student.class).addAnnotatedClass(Laptop.class);

        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();

        SessionFactory sf = con.buildSessionFactory(reg);

        Session session = sf.openSession();
        session.beginTransaction();

        Random r = new Random();

        for(int i=1;i<=50;++i){
            Student student1 = new Student();
            student1.setRollno(i);
            student1.setName("Name_no_"+i);
            student1.setMarks(r.nextInt(100));
            session.save(student1);
        }

        session.getTransaction().commit();
        session.close();
    }
}
