package com.example.kursach6sem.service;

import com.example.kursach6sem.domain.Client;
import com.example.kursach6sem.domain.Credit;
import com.example.kursach6sem.domain.User;
import com.example.kursach6sem.repos.CreditRepo;
import com.example.kursach6sem.repos.CreditTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CreditService {
    @Autowired
    private CreditRepo creditRepo;

    @Autowired
    private CreditTypeRepo creditTypeRepo;

    public Credit findById(Long id){return creditRepo.findById(id).orElse(new Credit());}

    public List<Credit> findAll() {
        return creditRepo.findAll();
    }

    public void saveCredit(
            Client client,
            User user,
            Credit credit,
            Double sum,
            String type,
            String date
    ) throws ParseException {
        credit.setId(user.getId());
        credit.setSum(sum);
        credit.setType(creditTypeRepo.findByName(type));
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.applyPattern("dd/MM/yyyy");
        Date docDate= format.parse(date);
        credit.setDate(docDate);
        credit.setClient(client);
        creditRepo.save(credit);
    }

}
