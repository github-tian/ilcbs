package cn.tzs.test;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class POITest {
    public static void main(String[] args) throws IOException {
        Workbook book = new HSSFWorkbook();
        Sheet sheet = book.createSheet();
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("aaaaa");
        book.write(new FileOutputStream("d:/abc.xls"));
        HashMap<String, String> stringStringHashMap = new HashMap<String, String>();
        Set<Map.Entry<String, String>> entries = stringStringHashMap.entrySet();
    }
}
