package sg.edu.nus.iss.app.workshop13.models;

import java.time.LocalDate;
import java.util.Random;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Contact {
    @NotNull(message="Name cannot be null")
    @Size(min=3, max=64, message="Name must be between 3 and 64 chars")
    private String name;
    
    private String email;
    private Integer phoneNumber;

    private String id;

    private LocalDate dateOfBirth;

    public Contact() {

    }

    public Contact(String name, String email, Integer phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }



    private synchronized String generateId(Integer numOfChar) {
        Random r = new Random();
        StringBuilder strBuilder = new StringBuilder();
        while(strBuilder.length() < numOfChar) {
            strBuilder.append(Integer.toHexString(r.nextInt()));
        }
        return strBuilder
            .toString().substring(0, numOfChar);
    }



    public String getName() {
        return name;
    }



    public void setName(String name) {
        this.name = name;
    }



    public String getEmail() {
        return email;
    }



    public void setEmail(String email) {
        this.email = email;
    }



    public Integer getPhoneNumber() {
        return phoneNumber;
    }



    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



    public String getId() {
        return id;
    }



    public void setId(String id) {
        this.id = id;
    }



    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }



    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }



    

}






