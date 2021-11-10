package com.example.kursach6sem.controller;

import com.example.kursach6sem.domain.*;
import com.example.kursach6sem.repos.CreditTypeRepo;
import com.example.kursach6sem.repos.DebitTypeRepo;
import com.example.kursach6sem.repos.MessageRepo;
import com.example.kursach6sem.service.ClientService;
import com.example.kursach6sem.service.CreditService;
import com.example.kursach6sem.service.DebitService;
import com.example.kursach6sem.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;

@Controller
public class EditController {
    @Autowired
    private MessageRepo messageRepo;

    @Autowired
    private UserSevice userSevice;

    @Autowired
    private CreditTypeRepo creditTypeRepo;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CreditService creditService;

    @Autowired
    private DebitService debitService;

    @Autowired
    private DebitTypeRepo debitTypeRepo;

    @GetMapping("/creditEd")
    public String creditEdit(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("credit", creditService.findById(user.getId()));
        model.addAttribute("creditTypes",creditTypeRepo.findAll());
        return "creditEd";
    }

    @PostMapping("/creditEd")
    public String creditEd(
            @AuthenticationPrincipal User user,
         //   @RequestParam("credit") Credit credit,
            @RequestParam("sum") Double sum,
       //     @RequestParam("currency") String currency,
            @RequestParam("type") String type,
            @RequestParam("date") String date
    ) throws ParseException {
       Credit credit = creditService.findById(user.getId());
        CreditType creditType = creditTypeRepo.findByName(type);
        System.out.println(creditType.getName());
        System.out.println("123");
        creditService.saveCredit(userSevice.findById(user.getId()),user,credit,sum,type,date);
        return "creditEd";
    }

    @GetMapping("/debitEd")
    public String depositEdit(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute("clients", clientService.findAll());
        model.addAttribute("debit", debitService.findById(user.getId()));
        model.addAttribute("debitTypes",debitTypeRepo.findAll());
        return "depositEd";
    }

    @PostMapping("/debitEd")
    public String depositEd(
            @AuthenticationPrincipal User user,
            //   @RequestParam("credit") Credit credit,
            @RequestParam("sum") Double sum,
            //     @RequestParam("currency") String currency,
            @RequestParam("type") String type,
            @RequestParam("date") String date
    ) throws ParseException {
        Debit debit = debitService.findById(user.getId());
//        DebitType debitType = debitTypeRepo.findByName(type);
  //      System.out.println(debitType.getName());
        System.out.println("123");
        debitService.saveDebit(userSevice.findById(user.getId()),user,debit,sum,type,date);
        return "depositEd";
    }
}
