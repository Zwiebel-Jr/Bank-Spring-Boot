package com.example.kursach6sem.service;

import com.example.kursach6sem.domain.Client;
import com.example.kursach6sem.domain.Role;
import com.example.kursach6sem.domain.User;
import com.example.kursach6sem.repos.ClientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ClientService {
    @Autowired
    private ClientRepo clientRepo;

    public void saveClient(User user, String firstName, Client client,
                            String lastName,
                            String middleName,
                            String dateOfBirth,
                            String gender,
                            String passportSerialNumber,
                            String passportNumber,
                            String passportIssueDate,
                            String passportIdentificationNumber,
                            String placeOfBirth,
                            //String cityOfResidence,
                            String addressOfResidence,
                            String addressOfRegistry,
                            String passsportIssuer
                           // String maritalStatus,
                           // String citizenship,
                           // String disability,
                            //String pensioner,
                       //     String dutyBound
                                ) throws ParseException {
        //Client client = new Client();

        client.setFirstName(firstName);
        client.setLastName(lastName);
        client.setMiddleName(middleName);
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.applyPattern("dd/MM/yyyy");
        Date docDate= format.parse(dateOfBirth);
        client.setDateOfBirth(docDate);
        client.setGender(gender);
        client.setPassportSerialNumber(passportSerialNumber);
        client.setPassportNumber(passportNumber);
        docDate= format.parse(passportIssueDate);
        client.setPassportIssueDate(docDate);
        client.setPassportIssuer(passsportIssuer);
        client.setPassportIdentificationNumber(passportIdentificationNumber);
        client.setPlaceOfBirth(placeOfBirth);
       // client.setCityOfResidence(cityOfResidence);
        client.setAddressOfResidence(addressOfResidence);
        client.setAddressOfRegistry(addressOfRegistry);
        client.setId(user.getId());
        //client.setMaritalStatus(maritalStatus);
      //  client.setCitizenship();
      //  client.setDisability();
        //client.setPensioner();
      //  client.setDutyBound();



        clientRepo.save(client);
    }

    public List<Client> findAll(){return clientRepo.findAll();}
}
