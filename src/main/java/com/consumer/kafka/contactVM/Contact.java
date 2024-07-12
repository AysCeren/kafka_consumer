package com.consumer.kafka.contactVM;
//imports
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity //it will be an entity
@Table(name = "contacts") // VERY IMPORTANT

public class Contact {

    @Id //determines the primary key for the contacts table
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //very important! this code saved the program from duplicate key error
    private int id; //which is id
    @Setter
    @Getter
    private String firstname;
    @Setter
    @Getter
    private String lastname;
    @Setter
    @Getter
    private String address;
    @Setter
    @Getter
    private String phone;
    @Setter
    @Getter
    private String birthDay;
    @Setter
    @Getter
    private String loginDate;
    @Setter
    @Getter
    private int age;
    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id + '\''+
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", age=" + age + '\'' +
                ", login='" + loginDate + '\'' +
                '}';
    }
}

