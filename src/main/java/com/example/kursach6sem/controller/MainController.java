package com.example.kursach6sem.controller;

import com.example.kursach6sem.domain.*;
import com.example.kursach6sem.repos.CreditRepo;
import com.example.kursach6sem.repos.CreditTypeRepo;
import com.example.kursach6sem.repos.DebitRepo;
import com.example.kursach6sem.repos.MessageRepo;
import com.example.kursach6sem.service.ClientService;
import com.example.kursach6sem.service.CreditService;
import com.example.kursach6sem.service.DebitService;
import com.example.kursach6sem.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

@Controller
public class MainController {
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
    private DebitRepo debitRepo;

    @Autowired
    private CreditRepo creditRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String greeting(Map<String, Object> model) {
        return "greeting";
    }

    @GetMapping("/main")
    public String main(@RequestParam(required = false, defaultValue = "") String filter, Model model) {
        Iterable<Message> messages;

        if (filter != null && !filter.isEmpty()) {
            messages = messageRepo.findByTag(filter);
        } else {
            messages = messageRepo.findAll();
        }

        model.addAttribute("messages", messages);
        model.addAttribute("filter", filter);

        return "main";
    }

    @PostMapping("/main")
    public String add(
            @AuthenticationPrincipal User user,
            @Valid Message message,
            BindingResult bindingResult,
            Model model,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        message.setAuthor(user);

        if (bindingResult.hasErrors()) {
            Map<String, String> errorsMap = ControllerUtils.getErrors(bindingResult);

            model.mergeAttributes(errorsMap);
            model.addAttribute("message", message);
        } else {
            saveFile(message, file);

            model.addAttribute("message", null);

            messageRepo.save(message);
        }

        Iterable<Message> messages = messageRepo.findAll();

        model.addAttribute("messages", messages);

        return "main";
    }

    private void saveFile(@Valid Message message, @RequestParam("file") MultipartFile file) throws IOException {
        if (file != null && !file.getOriginalFilename().isEmpty()) {
            File uploadDir = new File(uploadPath);

            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFilename = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath + "/" + resultFilename));

            message.setFilename(resultFilename);
        }
    }

    @GetMapping("/user-messages/{user}")
    public String userMessges(
            @AuthenticationPrincipal User currentUser,
            @PathVariable User user,
            Model model,
            @RequestParam(required = false) Message message
    ) {
        Set<Message> messages = user.getMessages();

        model.addAttribute("messages", messages);
        model.addAttribute("message", message);
        model.addAttribute("isCurrentUser", currentUser.equals(user));

        return "userMessages";
    }

    @PostMapping("/user-messages/{user}")
    public String updateMessage(
            @AuthenticationPrincipal User currentUser,
            @PathVariable Long user,
            @RequestParam("id") Message message,
            @RequestParam("text") String text,
            @RequestParam("tag") String tag,
            @RequestParam("file") MultipartFile file
    ) throws IOException {
        if (message.getAuthor().equals(currentUser)) {
            if (!StringUtils.isEmpty(text)) {
                message.setText(text);
            }

            if (!StringUtils.isEmpty(tag)) {
                message.setTag(tag);
            }

            saveFile(message, file);

            messageRepo.save(message);
        }

        return "redirect:/user-messages/" + user;
    }

    @GetMapping("/credit")
    public String userCredit(
            @AuthenticationPrincipal User user,
            Model model
    ){
        //Credit credit = new Credit();
        //credit.setClient(userSevice.findById(user.getId()));
        //creditRepo.save(credit);
        model.addAttribute("credit", creditRepo.findByClient(userSevice.findById(user.getId())));
        //model.addAttribute("credit", creditService.findById(user.getId()));
        return "credit";
    }

    @GetMapping("/credits")
    public String userCredits(
            @AuthenticationPrincipal User user,
            Model model
    ){

       // List<Credit> credits = creditService.findAll();
        Credit credit = creditService.findById(5L);
        //System.out.println(credit.getClient().getFirstName());
        //System.out.println(credit.getType().getName());
        model.addAttribute("id", user.getId());
        model.addAttribute("credit", creditService.findById(user.getId()));
       // model.addAttribute("client", userSevice.findById(user.getId()));
        model.addAttribute("sum",credit.getSum());
        //System.out.println(credit.getSum());
        model.addAttribute("date",credit.getDate());
        model.addAttribute("credits", creditService.findAll());
        return "credits";
    }

    @GetMapping("/deposits")
    public String userDebits(
            @AuthenticationPrincipal User user,
            Model model
    ){

        // List<Credit> credits = creditService.findAll();
        Debit debit = debitService.findById(user.getId());
        //System.out.println(credit.getClient().getFirstName());
        //System.out.println(credit.getType().getName());
        model.addAttribute("id", user.getId());
        //model.addAttribute("credit", creditService.findById(user.getId()));
        // model.addAttribute("client", userSevice.findById(user.getId()));
        model.addAttribute("sum",debit.getSum());
        //System.out.println(credit.getSum());
        model.addAttribute("date",debit.getDate());
        model.addAttribute("debits", debitService.findAll());
        return "deposits";
    }
    @GetMapping("/debit")
    public String userDebit(
            @AuthenticationPrincipal User user,
            Model model
    ){
        model.addAttribute("debit", debitRepo.findByClient(userSevice.findById(user.getId())));
       // model.addAttribute("debit", debitService.findById(user.getId()));
        return "deposit";
    }

    @PostMapping("/debit")
    public String edDeb(){
        return "depositEd";
    }
}
