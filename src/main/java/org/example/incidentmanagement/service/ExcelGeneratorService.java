package org.example.incidentmanagement.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ExcelGeneratorService {
    private static final Logger logger = LoggerFactory.getLogger(ExcelGeneratorService.class);

//  Excel Generate Type For Backend Default Excel Generation.
    public byte[] generateExcel(List<?> dataList, String sheetName) throws IOException {
        logger.info("Generating Excel from Backend Service for sheet: {}", sheetName);

        if (dataList == null || dataList.isEmpty()) {
            logger.warn("Empty data list provided");
            throw new IllegalArgumentException("Data list cannot be empty");
        }

//      Create Excel With No Data With One Sheet.
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName != null ? sheetName : "Data");

//      Style For Header, Data, Date Types.
        CellStyle headerStyle = createHeaderStyle(workbook);
        CellStyle dataStyle = createDataStyle(workbook);
        CellStyle dateStyle = createDateStyle(workbook);

//      Get Header Data From Object
        Object firstObject = dataList.get(0);
        List<FieldInfo> fields = extractFields(firstObject);

//      Create Header, Fill Data, Auto Size Column
        createHeaderRow(sheet, fields, headerStyle);
        fillDataRows(sheet, dataList, fields, dataStyle, dateStyle);
        autoSizeColumns(sheet, fields.size());

//      Return Byte Type Data For API to Download
        byte[] result = writeToByteArray(workbook);
        logger.info("Excel generated successfully with {} rows", dataList.size());
        return result;
    }

//  Excel Generator For Data Which is Given From Frontend
    public byte[] generateExcelFromFrontend(Object frontendData, String sheetName) throws IOException {
        logger.info("Generating Excel from Frontend data for sheet: {}", sheetName);

        if (frontendData == null) {
            throw new IllegalArgumentException("Frontend data cannot be null");
        }

//      Type For Data From Frontend
        List<Map<String, Object>> dataList;

//      Check Frontend Given Data is List Or Not If Not Cast to List.
        if (frontendData instanceof List) {
            dataList = (List<Map<String, Object>>) frontendData;
        } else if (frontendData instanceof Map) {
            dataList = new ArrayList<>();
            dataList.add((Map<String, Object>) frontendData);
        } else {
            throw new IllegalArgumentException("Unsupported data type. Expected List or Map");
        }
//      Check Data is Empty or Not
        if (dataList.isEmpty()) {
            throw new IllegalArgumentException("Data list cannot be empty");
        }

//      Return Generated Excel.
        return generateExcelFromMapList(dataList, sheetName);
    }

//  Generate Excel From Map List For Data From Frontend
    private byte[] generateExcelFromMapList(List<Map<String, Object>> dataList, String sheetName) throws IOException {
            Workbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet(sheetName != null ? sheetName : "Data");

            CellStyle headerStyle = createHeaderStyle(workbook);
            CellStyle dataStyle = createDataStyle(workbook);
            CellStyle dateStyle = createDateStyle(workbook);

            // Headers - JSON-ის keys
            Map<String, Object> firstRow = dataList.get(0);
            List<String> headers = new ArrayList<>(firstRow.keySet());

            // Header Row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(formatFieldName(headers.get(i)));
                cell.setCellStyle(headerStyle);
            }

            // Data Rows
            for (int rowIndex = 0; rowIndex < dataList.size(); rowIndex++) {
                Row row = sheet.createRow(rowIndex + 1);
                Map<String, Object> dataRow = dataList.get(rowIndex);

                for (int colIndex = 0; colIndex < headers.size(); colIndex++) {
                    Cell cell = row.createCell(colIndex);
                    Object value = dataRow.get(headers.get(colIndex));
                    setCellValue(cell, value, dataStyle, dateStyle);
                }
            }

            autoSizeColumns(sheet, headers.size());

            byte[] result = writeToByteArray(workbook);
            logger.info("Excel generated from Frontend with {} rows", dataList.size());
            return result;
        }

//  Create Headers Row Using In Backend Excel. Can User For Direct Reports With Dto.
    private void createHeaderRow(Sheet sheet, List<FieldInfo> fields, CellStyle headerStyle) {
            Row headerRow = sheet.createRow(0);

            for (int i = 0; i < fields.size(); i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(fields.get(i).displayName);
                cell.setCellStyle(headerStyle);
            }
        }

//  Fills Excel rows with object data via Reflection
    private void fillDataRows(Sheet sheet, List<?> dataList, List<FieldInfo> fields,
                                  CellStyle dataStyle, CellStyle dateStyle) {
        for (int rowIndex = 0; rowIndex < dataList.size(); rowIndex++) {
            Row row = sheet.createRow(rowIndex + 1);
            Object dataObject = dataList.get(rowIndex);

            for (int colIndex = 0; colIndex < fields.size(); colIndex++) {
                Cell cell = row.createCell(colIndex);
                Object value = getFieldValue(dataObject, fields.get(colIndex).field);
                setCellValue(cell, value, dataStyle, dateStyle);
            }
        }
    }

//  Set Cell Value Type
    private void setCellValue(Cell cell, Object value, CellStyle dataStyle, CellStyle dateStyle) {
            if (value == null) {
                cell.setCellValue("-");
                cell.setCellStyle(dataStyle);
            } else if (value instanceof Number) {
                cell.setCellValue(((Number) value).doubleValue());
                cell.setCellStyle(dataStyle);
            } else if (value instanceof Date) {
                cell.setCellValue((Date) value);
                cell.setCellStyle(dateStyle);
            } else if (value instanceof LocalDate) {
                cell.setCellValue(value.toString());
                cell.setCellStyle(dataStyle);
            } else if (value instanceof LocalDateTime) {
                cell.setCellValue(((LocalDateTime) value).format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                cell.setCellStyle(dataStyle);
            } else if (value instanceof Boolean) {
                cell.setCellValue((Boolean) value ? "Yes" : "No");
                cell.setCellStyle(dataStyle);
            } else if (value instanceof Enum) {
                cell.setCellValue(((Enum<?>) value).name());
                cell.setCellStyle(dataStyle);
            } else {
                cell.setCellValue(value.toString());
                cell.setCellStyle(dataStyle);
            }
        }

//  Create Cell Style For Header
    private CellStyle createHeaderStyle(Workbook workbook) {
            CellStyle style = workbook.createCellStyle();

            Font font = workbook.createFont();
            font.setBold(true);
            font.setFontHeightInPoints((short) 12);
            font.setColor(IndexedColors.WHITE.getIndex());
            style.setFont(font);

            style.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);

            return style;
        }

//  Create Cell Style For Data
    private CellStyle createDataStyle(Workbook workbook) {
            CellStyle style = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.LEFT);
            style.setVerticalAlignment(VerticalAlignment.CENTER);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            return style;
        }

//  Create Cell Stype For Date
    private CellStyle createDateStyle(Workbook workbook) {
            CellStyle style = createDataStyle(workbook);
            CreationHelper createHelper = workbook.getCreationHelper();
            style.setDataFormat(createHelper.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
            return style;
        }

//  Extract Fields Names For Headers From Object(DTO)
    private List<FieldInfo> extractFields(Object obj) {
            List<FieldInfo> fieldInfos = new ArrayList<>();
            Class<?> clazz = obj.getClass();

            for (Field field : clazz.getDeclaredFields()) {
                if (shouldIncludeField(field)) {
                    field.setAccessible(true);
                    String displayName = formatFieldName(field.getName());
                    fieldInfos.add(new FieldInfo(field, displayName));
                }
            }

            return fieldInfos;
        }

//  Filter Header Names For Generation
    private boolean shouldIncludeField(Field field) {
            String name = field.getName();
            int modifiers = field.getModifiers();

            return !name.startsWith("$")
                    && !java.lang.reflect.Modifier.isStatic(modifiers)
                    && !name.equals("serialVersionUID");
        }

//  Get Specific Field Value From Object
    private Object getFieldValue(Object obj, Field field) {
            try {
                return field.get(obj);
            } catch (IllegalAccessException e) {
                logger.info("Could not access field: {}", field.getName());
                return null;
            }
        }

//  Format Header Names For Excel (firstName -> First Name)
    private String formatFieldName(String fieldName) {
            StringBuilder result = new StringBuilder();
            result.append(Character.toUpperCase(fieldName.charAt(0)));

            for (int i = 1; i < fieldName.length(); i++) {
                char c = fieldName.charAt(i);
                if (Character.isUpperCase(c)) {
                    result.append(" ");
                }
                result.append(c);
            }

            return result.toString();
        }

//  Auto-sizes all columns with additional padding and maximum width limit
    private void autoSizeColumns(Sheet sheet, int columnCount) {
        for (int i = 0; i < columnCount; i++) {
            sheet.autoSizeColumn(i);
            int currentWidth = sheet.getColumnWidth(i);
            sheet.setColumnWidth(i, Math.min(currentWidth + 500, 15000));
        }
    }

//  Converts Workbook to byte[] for download
    private byte[] writeToByteArray(Workbook workbook) throws IOException {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                workbook.close();
                return outputStream.toByteArray();
            }
        }

//  Field Info Helper Class.
    private static class FieldInfo {
            Field field;
            String displayName;

            FieldInfo(Field field, String displayName) {
                this.field = field;
                this.displayName = displayName;
            }
        }
}


