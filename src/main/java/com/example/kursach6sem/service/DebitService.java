package com.example.kursach6sem.service;

import com.example.kursach6sem.domain.Client;
import com.example.kursach6sem.domain.Credit;
import com.example.kursach6sem.domain.Debit;
import com.example.kursach6sem.domain.User;
import com.example.kursach6sem.repos.DebitRepo;
import com.example.kursach6sem.repos.DebitTypeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DebitService {
    @Autowired
    private DebitRepo debitRepo;

    @Autowired
    private DebitTypeRepo debitTypeRepo;

    public Debit findById(Long id){return debitRepo.findById(id).orElse(new Debit());}

    public List<Debit> findAll() {
        return debitRepo.findAll();
    }

    public void saveDebit(
            Client client,
            User user,
            Debit debit,
            Double sum,
            String type,
            String date
    ) throws ParseException {
        debit.setId(user.getId());
        debit.setSum(sum);
        debit.setType(debitTypeRepo.findByName(type));
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        format.applyPattern("dd/MM/yyyy");
        Date docDate= format.parse(date);
        debit.setDate(docDate);
        debit.setClient(client);
        debitRepo.save(debit);
    }

}
