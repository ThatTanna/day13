package sg.edu.nus.iss.app.workshop13.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Formatter;
import java.util.List;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.server.ResponseStatusException;

@Component("contacts")
public class Contacts {
    public void saveContact(Contact ctc, Model model,
            ApplicationArguments appArgs, String defaultDataDir){
        String dataFileName = ctc.getId();
        PrintWriter printWriter = null;
        try {
            FileWriter fileWriter = new FileWriter(getDataDir(appArgs, defaultDataDir)
                + "/" + (dataFileName));
            printWriter = new PrintWriter(fileWriter);
            printWriter.println(ctc.getName());
            printWriter.println(ctc.getEmail());
            printWriter.println(ctc.getPhoneNumber());
            printWriter.println(ctc.getDateOfBirth().toString());
            printWriter.close();
            
        } catch (IOException e) {
            System.err.println(e);
        } finally {
            printWriter.close();
        }
        model.addAttribute("contact", ctc());


    }

    private String getDataDir(ApplicationArguments appArgs
            , String defaultDataDir) {
            String dataDirResult = null;
            List<String> optValues = null;
            String[] optValuesArr = null;
            Set<String> opsNames = appArgs.getOptionNames();
            String[] optNamesArr = opsNames.toArray(new String[opsNames.size()]);
            if(optNamesArr.length > 0) {
                appArgs.getOptionValues(optNamesArr[0]);
            } else {
                dataDirResult = defaultDataDir;
            }
            return null;
            }


    public void getContactById(Model model, String contactId,
            ApplicationArguments appArgs, String defaultDataDir) {
        Contact c = new Contact();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Path filePath = new File(getDataDir(appArgs, defaultDataDir)
            + "/" + contactId).toPath();
        Charset charset = Charset.forName("UTF-8");
        try {
            List<String> stringListDat = Files
                        .readAllLines(filePath, charset);
                c.setId(contactId);
                c.setName(stringListDat.get(0));
                c.setEmail(stringListDat.get(1));
                c.setPhoneNumber(Integer.parseInt(stringListDat.get(2)));
                LocalDate dob = LocalDate.parse(stringListDat.get(3), formatter);
        } catch (Exception e) {
            System.err.println(e);
            throw new ResponseStatusException(HttpStatus.NOT_FOUND
                        , "Contact info not found");
        }
        model.addAttribute("contact", c);
    }
}
