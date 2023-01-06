package sg.edu.nus.iss.app.workshop13.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import sg.edu.nus.iss.app.workshop13.models.Contact;
import sg.edu.nus.iss.app.workshop13.models.Contacts;

public class AddressbookController {
    @Autowired
    Contacts ctcz;

    @Autowired
    ApplicationArguments appArgs;

    @GetMapping
    public String showAddrBookForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "addressbook";
    }

    @GetMapping(path="{contactId")
    public String retrieveContactBy(@PathVariable String contactId, Model model) {
        ctcz.getContactById(model, contactId, appArgs, contactId);
        return "result";
    }

    @PostMapping
    public String saveContact(@Valid Contact contact,
                BindingResult bindResult, Model model) throws IOException {
            if(bindResult.hasErrors())
                    return "addressbook";
            if(!checkAgeInBetween(contact.getDateOfBirth())){
                ObjectError err = new ObjectError("dateOfBirthAge", "Age must be between 10 to 100 years old");
                bindResult.addError(err);
                return "addressbook";
            }

            return "result";
        }

    private boolean checkAgeInBetween(LocalDate dob){
        Integer calculatedAge = 0;
        if(dob != null){
            calculatedAge = Period.between(dob, LocalDate.now()).getYears();
        }

        if(calculatedAge >= 10 && calculatedAge <=100){
            return true;
        }
    }
}
