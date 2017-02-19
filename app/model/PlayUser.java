package model;
import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
//import java.sql.Date;


import play.data.*;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import org.mindrot.jbcrypt.BCrypt;

import play.data.validation.Constraints.Pattern;
import play.data.format.*;
@Entity
@Table(name = "PlayUser", schema = "play_assessment_db")
public class PlayUser  {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
@Column(name="id")
public Integer id;
@Required
@Pattern("^[a-zA-Z]+$")
@Column(name="firstName")
public String firstName;
@Required
@Pattern("^[a-zA-Z]+$")
@Column(name="lastName")
public String lastName;
@Required
@Email
@Column(name="emailAddress")
public String emailAddress;
@Required
@Column(name="streetName")
public String streetName;
@Required
@Column(name="houseNumber")
public String houseNumber;
@Required
@Pattern("^[a-zA-Z]+$")
@Column(name="city")
public String city;
@Required
@Formats.DateTime(pattern = "yyyy-MM-dd")
@Column(name="birthDate")
public Date  birthDate;
@Required
@MinLength(3)
@Column(name="userName")
public String userName;
@Required
@Pattern("(?=.*?[A-Z])(?=.*?[#?!@$%^&*-]).{6,}$")
@Column(name="password")
public String password;


    public PlayUser() {
    	this.id = 0;
        this.firstName = "";
        this.lastName = "";
        this.emailAddress = "";
        this.streetName = "";
        this.houseNumber = "";
        this.city = "";
        this.birthDate = new Date();
        this.userName = "";
        this.password = "";
    }

    public PlayUser(int id,String firstName, String lastName, String emailAddress, String streetName, String houseNumber, String city, Date birthDate, String userName, String password) {
    	this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.emailAddress = emailAddress;
        this.streetName = streetName;
        this.houseNumber = houseNumber;
        this.city = city;
        this.birthDate = birthDate;
        this.userName = userName;
        this.password = password;
    }
    
    public Integer getId() {
    	return id;
    }
    public void setId(Integer id) {
    	this.id =  id;
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
        //this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        //this.password = BCrypt.hashpw(password, BCrypt.gensalt());
        
    }
}
