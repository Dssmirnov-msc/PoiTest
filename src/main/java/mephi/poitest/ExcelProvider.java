/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mephi.poitest;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 *
 * @author smirn
 */
public class ExcelProvider {

    private File file;
    private  XSSFWorkbook wb;
    private Journal journal;
    
    public ExcelProvider() {   
        init();
    }
    
    private void init(){
        setFile(new File("Статистика_свод_2022.xlsx"));
    }
    
    public void run(){
        loadFile();
        readSheets();
        close();
    }
    
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
    
    public void loadFile(){
        try {
            wb = new XSSFWorkbook(file);
        } catch (IOException ex) {
            Logger.getLogger(ExcelProvider.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidFormatException ex) {
            Logger.getLogger(ExcelProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        // javax.swing.JDialog diag = new javax.swing.JDialog();
    }

    private void readSheets() {
        int N = wb.getNumberOfSheets();
        journal = new Journal();
        for (int i = 0; i < N; i++) {
            XSSFSheet sheet = wb.getSheetAt(i);
            String name = sheet.getSheetName();
            journal.addGroup(name);
            getHeader(sheet);
            getTable(sheet);
        }
    }
//    private ArrayList<String> getSheetNames(){
//        ArrayList<String> names = new ArrayList();
//        int N = wb.getNumberOfSheets();
//        for (int i = 0; i < N; i++) {
//            XSSFSheet sheet = wb.getSheetAt(i);
//            String name = sheet.getSheetName();
//            names.add(name);
//        }
//        return names;
//    }
    
    
    
    private void close() {
        try {
            wb.close();
        } catch (IOException ex) {
            Logger.getLogger(ExcelProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getHeader(XSSFSheet sheet) {
        XSSFRow header = sheet.getRow(0);
        
        int C = header.getLastCellNum();
        ArrayList<String> tasknames = new ArrayList();
        for (int i = 2; i < C; i++) {
            XSSFCell cell = header.getCell(i);
            tasknames.add( cell.getStringCellValue());
        }
        journal.setTasks(tasknames);
    }

    private void getTable(XSSFSheet sheet) {
        int R = sheet.getLastRowNum();
        
        for (int j = 1; j < R; j++) {
            XSSFRow row = sheet.getRow(j);
            getObservation(row);
            getValues(row);
        }
    }

    private void getObservation(XSSFRow row) {
        int number = (int)row.getCell(0).getNumericCellValue();
        String FIO = row.getCell(1).getStringCellValue();
        journal.addStudent(number, FIO);
    }

    private void getValues(XSSFRow row) {
        int C = row.getLastCellNum();
        for (int i = 2; i < (C-1); i++) {
            XSSFCell cell = row.getCell(i);
            double mark = cell.getNumericCellValue();
            journal.writeMark(mark);
        }
        XSSFCell cell = row.getCell(C-1);
        String fmark = cell.getStringCellValue();
        journal.setFinalMark(fmark);
    }

    public Journal getJournal() {
        return journal;
    }
    
}
