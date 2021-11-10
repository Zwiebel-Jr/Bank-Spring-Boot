package com.example.kursach6sem.controller;


import com.example.kursach6sem.domain.Credit;
import com.example.kursach6sem.domain.Debit;
import com.example.kursach6sem.domain.User;
import com.example.kursach6sem.export.DebitExcelExporter;
import com.example.kursach6sem.export.UserExcelExporter;
import com.example.kursach6sem.export.CreditExcelExporter;
import com.example.kursach6sem.service.CreditService;
import com.example.kursach6sem.service.DebitService;
import com.example.kursach6sem.service.UserSevice;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ReportController {
    @Autowired
    private UserSevice service;

    @Autowired
    private CreditService creditService;

    @Autowired
    private DebitService debitService;

    //@GetMapping("/userRepo")
    @GetMapping("/users/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<User> listUsers = service.findAll();

        UserExcelExporter excelExporter = new UserExcelExporter(listUsers);

        excelExporter.export(response);
    }

    @GetMapping("/users/export/excelCred")
    public void exportToExcelCred(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=credits_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Credit> listCredit = creditService.findAll();

        CreditExcelExporter excelExporter = new CreditExcelExporter(listCredit);

        excelExporter.export(response);
    }

    @GetMapping("/users/export/excelDeb")
    public void exportToExcelDeb(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=debits_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);

        List<Debit> listCredit = debitService.findAll();

        DebitExcelExporter excelExporter = new DebitExcelExporter(listCredit);

        excelExporter.export(response);
    }

    /*
    public ResponseEntity<StreamingResponseBody> excel() {
        Workbook workBook = new XSSFWorkbook();
        Sheet sheet = workBook.createSheet("My Sheet");
        sheet.setColumnWidth(0, 2560);
        sheet.setColumnWidth(1, 2560);
        Row row = sheet.createRow(0);
        row.createCell(0).setCellValue("Hello World");

        return ResponseEntity
                .ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline;filename=\"myfilename.xlsx\"")
                .body(workBook::write);
    }

    */
}
