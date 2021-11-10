package com.example.kursach6sem.controller;

import com.example.kursach6sem.domain.Client;
import com.example.kursach6sem.domain.Role;
import com.example.kursach6sem.domain.User;
import com.example.kursach6sem.export.UserExcelExporter;
import com.example.kursach6sem.repos.ClientRepo;
import com.example.kursach6sem.service.ClientService;
import com.example.kursach6sem.service.CreditService;
import com.example.kursach6sem.service.UserSevice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserSevice userSevice;

    @Autowired
    private ClientService clientService;

    @Autowired
    private CreditService creditService;

    @Autowired
    private ClientRepo clientRepo;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping
    public String userList(Model model) {
        model.addAttribute("users", userSevice.findAll());

        return "userList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "userEdit";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userSevice.saveUser(user, username, form);

        return "redirect:/user";
    }

    @GetMapping("profile")
    public String getProfile(Model model, @AuthenticationPrincipal User user) {
        model.addAttribute("users",userSevice.findById(user.getId()));
        model.addAttribute("user", user);
        model.addAttribute("username", user.getUsername());
        model.addAttribute("email", user.getEmail());
        model.addAttribute("client", userSevice.findById(user.getId()));
        return "profile";
    }

    @PostMapping("profile")
    public String updateProfile(
            @AuthenticationPrincipal User user,
           // @RequestParam Client client,
            @RequestParam String password,
            @RequestParam String email,
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String middleName,
            @RequestParam String dateOfBirth,
            @RequestParam String gender,
            @RequestParam String passportSerialNumber,
            @RequestParam String passportIssuer,
            @RequestParam String passportNumber,
            @RequestParam String passportIssueDate,
            @RequestParam String passportIdentificationNumber,
            @RequestParam String placeOfBirth,
          //  @RequestParam String cityOfResidence,
            @RequestParam String addressOfResidence,
            @RequestParam String addressOfRegistry
         //   @RequestParam String maritalStatus,
          //  @RequestParam String citizenship,
         //   @RequestParam String disability,
          //  @RequestParam String pensioner,
          //  @RequestParam String dutyBound

    ) throws ParseException {
        userSevice.updateProfile(user, password, email);

        clientService.saveClient(
                 user,  firstName, userSevice.findById(user.getId()),
                 lastName,
                 middleName,
                 dateOfBirth,
                 gender,
                 passportSerialNumber,
                 passportNumber,
                 passportIssueDate,
                 passportIdentificationNumber,
                 placeOfBirth,
                //String cityOfResidence,
                 addressOfResidence,
                 addressOfRegistry,
                 passportIssuer
                // String maritalStatus,
                // String citizenship,
                // String disability,
                //String pensioner,
                //     String dutyBound

        );
        return "redirect:/user/profile";
    }

    @GetMapping("/users/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<User> listUsers = userSevice.findAll();

        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);

        excelExporter.export(response);
    }

}
