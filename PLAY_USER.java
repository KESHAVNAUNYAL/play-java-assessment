package model;
import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
//import java.sql.Date;


import play.data.*;
import play.data.validation.Constraints.Email;
import play.data.validation.Constraints.MinLength;
import play.data.validation.Constraints.Required;
import play.data.validation.Constraints.Pattern;
import play.data.format.*;
@Entity
@Table(name = "PLAY_USER", schema = "play_assessment_db")
public class PLAY_USER  {
@Id
@GeneratedValue(strategy=GenerationType.AUTO)
public Integer id;
@Required
@Pattern("^[a-zA-Z]+$")
private String firstName;
@Required
@Pattern("^[a-zA-Z]+$")
private String lastName;
@Required
@Email
private String emailAddress;
@Required
private String streetName;
@Required
private String houseNumber;
@Required
@Pattern("^[a-zA-Z]+$")
private String city;
@Required
@Formats.DateTime(pattern = "yyyy-MM-dd")
private Date  birthDate;
@Required
@MinLength(3)
private String userName;
@Required
@Pattern("(?=.*?[A-Z])(?=.*?[#?!@$%^&*-]).{6,}$")
private String password;


    public PLAY_USER() {
    }

    public PLAY_USER(int id,String firstName, String lastName, String emailAddress, String streetName, String houseNumber, String city, Date birthDate, String userName, String password) {
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
    }
}