package com.learn2code.Startup.controller;

import com.learn2code.Startup.entity.Contacts;
import com.learn2code.Startup.service.ContactsService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

@Controller
public class ExportExcelController {

    @Autowired
    private ContactsService contactsService;

    @GetMapping("/export/excel")
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=contacts.xlsx";
        response.setHeader(headerKey, headerValue);

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Contacts");

        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Name");
        header.createCell(2).setCellValue("Email");
        header.createCell(3).setCellValue("Phone");
        header.createCell(4).setCellValue("Title");

        List<Contacts> contacts = contactsService.findAllContacts();
        int rowCount = 1;
        for (Contacts contact : contacts) {
            Row row = sheet.createRow(rowCount++);
            row.createCell(0).setCellValue(contact.getId());
            row.createCell(1).setCellValue(contact.getName());
            row.createCell(2).setCellValue(contact.getEmail());
            row.createCell(3).setCellValue(contact.getPhone());
            row.createCell(4).setCellValue(contact.getTitle());
        }

        OutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();
    }
}
