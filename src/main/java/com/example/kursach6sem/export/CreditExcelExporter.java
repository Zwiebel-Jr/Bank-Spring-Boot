package com.example.kursach6sem.export;

import com.example.kursach6sem.domain.Credit;
import com.example.kursach6sem.domain.User;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class CreditExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    //private List<User> listUsers;
    private List<Credit> listCredit;

    public CreditExcelExporter(List<Credit> listCredit) {
        this.listCredit = listCredit;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "User ID", style);
        createCell(row, 1, "Date", style);
        createCell(row, 2, "Sum", style);
        createCell(row, 3, "Client_id", style);
        createCell(row, 4, "Credit_type_id", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else if(value instanceof String){
            cell.setCellValue((String) value);
        }else if(value instanceof Long){
            cell.setCellValue((Long) value);
        } else if(value instanceof Double){
            cell.setCellValue((Double) value);
        }else {
            cell.setCellValue((String) value);
        }

        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (Credit credit : listCredit) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, credit.getId(), style);
            createCell(row, columnCount++, credit.getDate().toString(), style);
            createCell(row, columnCount++, credit.getSum(), style);
            createCell(row, columnCount++, credit.getClient().getId(), style);
            createCell(row, columnCount++, credit.getType().getId(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        // workbook.close();

        outputStream.close();

    }
}
