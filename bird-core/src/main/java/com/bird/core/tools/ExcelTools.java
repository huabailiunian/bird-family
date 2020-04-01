package com.bird.core.tools;

import com.bird.core.consts.GlobalConst;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * excel 操作工具类
 *
 * @author youly
 * 2018/12/24 11:02
 */
public class ExcelTools {

    private static Logger logger = LoggerFactory.getLogger(ExcelTools.class);
    private static final SimpleDateFormat sdf = new SimpleDateFormat(GlobalConst.FORMAT_DATE);


    /**
     * 读取一行,返回数组
     *
     * @param row 行
     * @return 字符串数组, 如果row为null, 返回空数组
     */
    public static String[] readRow(Row row) {
        if (row == null) {
            return new String[0];
        }
        //用于标识该行是否有数据
        int dataNum = 0;
        String[] data = new String[row.getLastCellNum()];
        for (Cell cell : row) {
            String cellVal = getCellVal(cell);
            if (StringUtils.isNotBlank(cellVal)) {
                data[cell.getColumnIndex()] = cellVal;
                dataNum++;
            }
        }
        if (logger.isDebugEnabled()) {
            int rowNum = row.getRowNum() + 1;
            logger.debug("Read Row[rowNum:{}]-Data[{}]", rowNum, String.join(GlobalConst.DELIMITER_DEFAULT, data));
        }
        return dataNum > 0 ? data : new String[0];
    }

    /**
     * 读取一行,返回键-值对
     * 如果row为null或keys为空, 返回空Map;
     * 如果键个数小于一行的列个数,以键为基准读取;
     * 如果键个数大于一行的列个数,以行为基准读取.
     *
     * @param row  行
     * @param keys 键数组
     * @return 键值对Map
     */
    public static Map<String, String> readRow(Row row, String[] keys) {
        if (row == null || ArrayUtils.isEmpty(keys)) {
            return Collections.emptyMap();
        }
        Map<String, String> map = new HashMap<>(row.getLastCellNum());
        for (Cell cell : row) {
            String cellVal = getCellVal(cell);
            if (StringUtils.isNotBlank(cellVal)) {
                map.put(keys[cell.getColumnIndex()], cellVal);
            }
        }
        if (logger.isDebugEnabled()) {
            int rowNum = row.getRowNum() + 1;
            logger.debug("Read Row[rowNum:{}]-Data[{}]", rowNum, map);
        }
        return map;
    }

    /**
     * 读取整个sheet
     *
     * @param rowOffset 跳过行数
     * @return 数组列表
     */
    public static List<String[]> readSheet(Sheet sheet, final int rowOffset) {
        if (sheet == null) {
            return Collections.emptyList();
        }
        int total = sheet.getLastRowNum() + 2 - rowOffset;
        List<String[]> data = new ArrayList<>(total);
        sheet.forEach(row -> {
            if (row.getRowNum() >= rowOffset) {
                String[] strings = readRow(row);
                if (ArrayUtils.isNotEmpty(strings)) {
                    data.add(strings);
                }
            }
        });
        return data;
    }

    /**
     * 读取整个sheet
     *
     * @param hasHeadRow   是否有表头
     * @param headRowIndex 表头行序号
     * @param rowOffset    跳过的行数
     * @param keys         键数组
     * @return Map列表
     */
    public static List<Map<String, String>> readSheet(Sheet sheet, boolean hasHeadRow, final int headRowIndex, final int rowOffset, String[] keys) {
        if (sheet == null || (ArrayUtils.isEmpty(keys) && !hasHeadRow)) {
            return Collections.emptyList();
        }
        int total = sheet.getLastRowNum() + 2 - rowOffset;
        List<Map<String, String>> data = new ArrayList<>(total);
        if (ArrayUtils.isEmpty(keys) && hasHeadRow) {
            keys = readRow(sheet.getRow(headRowIndex));
        }
        final String[] key = keys;
        sheet.forEach(row -> {
            if (row.getRowNum() >= rowOffset) {
                Map<String, String> map = readRow(row, key);
                if (CollectionTools.isNotEmpty(map)) {
                    data.add(map);
                }
            }
        });
        return data;
    }


    /**
     * 读取单元格的值,统一转字符串
     *
     * @param cell 单元格
     * @return 单元格的值
     */
    private static String getCellVal(Cell cell) {
        String cellVal = "";
        if (cell == null) {
            return cellVal;
        }
        switch (cell.getCellTypeEnum()) {
            case STRING:
                cellVal = cell.getStringCellValue();
                break;
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    Date date = cell.getDateCellValue();
                    cellVal = sdf.format(date);
                    break;
                }
                cell.setCellType(CellType.STRING);
                cellVal = cell.getStringCellValue();
                break;
            case BOOLEAN:
                cellVal = String.valueOf(cell.getBooleanCellValue());
                break;
            case FORMULA:
                cellVal = cell.getCellFormula();
                break;
            case BLANK:
                break;
            default:
                cellVal = cell.getStringCellValue();
                break;
        }
        return cellVal;
    }

    public static void writeCell(Row row, int column, CellStyle cellStyle, String value) {
        Cell cell = row.getCell(column);
        if (cell == null) {
            cell = row.createCell(column);
        }
        if (cellStyle != null) {
            cell.setCellStyle(cellStyle);
        }
        cell.setCellValue(value);
    }

    public static void writeCell(Sheet sheet, int row, int column, CellStyle cellStyle, String value) {
        Row tmp = sheet.getRow(row);
        if (tmp == null) {
            tmp = sheet.createRow(row);
        }
        writeCell(tmp, column, cellStyle, value);
    }

    public static void writeCell(Workbook workbook, String sheetName, int row, int column, CellStyle cellStyle, String value) {
        Sheet sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }
        writeCell(sheet, row, column, cellStyle, value);
    }

    public static void writeRow(Sheet sheet, int rowIndex, CellStyle[] styles, String[] values) {
        Row row = sheet.getRow(rowIndex);
        if (row == null) {
            row = sheet.createRow(rowIndex);
        }
        if (!ArrayUtils.isEmpty(values)) {
            for (int i = 0; i < values.length; i++) {
                CellStyle style = styles.length > i ? styles[i] : null;
                writeCell(row, i, style, values[i]);
            }
        }
    }

    public static void writeSheet(Sheet sheet, Collection<String[]> values) {
        int rowIndex = sheet.getLastRowNum() + 1;
        if (CollectionTools.isNotEmpty(values)) {
            for (String[] strings : values) {
                writeRow(sheet, rowIndex, new CellStyle[0], strings);
                rowIndex++;
            }
        }
    }

    public static void writeSheet(Sheet template, Sheet target, Collection<String[]> values) {
        copySheet(template, target, true);
        writeSheet(target, values);
    }

    /**
     * 复制sheet页,覆盖复制
     *
     * @param source 原sheet页
     * @param target 目标sheet页
     */
    public static void copySheet(Sheet source, Sheet target, boolean copyCellStyle) {
        if (!ObjectUtils.allNotNull(source, target)) {
            throw new NullPointerException("参数[source,target]不能为NUll");
        }
        //合并单元格
        List<CellRangeAddress> mergedRegions = source.getMergedRegions();
        if (CollectionTools.isNotEmpty(mergedRegions)) {
            mergedRegions.forEach(target::addMergedRegion);
        }
        int maxColumns = 0;
        //复制所有行
        if (source.iterator().hasNext()) {
            for (Row row : source) {
                if (row.getLastCellNum() > maxColumns) {
                    maxColumns = row.getLastCellNum();
                }
                Row targetRow = target.getRow(row.getRowNum());
                if (targetRow == null) {
                    targetRow = target.createRow(row.getRowNum());
                }
                copyRow(row, targetRow, copyCellStyle);
            }
        }
        //设置列宽
        for (int i = 0; i < maxColumns; i++) {
            target.setColumnWidth(i, source.getColumnWidth(i));
        }
    }

    /**
     * 复制一行
     */
    public static void copyRow(Row source, Row target, boolean copyCellStyle) {
        if (!ObjectUtils.allNotNull(source, target)) {
            throw new NullPointerException("参数[source,target]不能为NUll");
        }
        //设置行高
        target.setHeight(source.getHeight());
        //复制所有列
        if (source.iterator().hasNext()) {
            for (Cell sourceCell : source) {
                Cell targetCell = target.getCell(sourceCell.getColumnIndex());
                if (targetCell == null) {
                    targetCell = target.createCell(sourceCell.getColumnIndex());
                }
                copyCell(sourceCell, targetCell, copyCellStyle);
            }
        }
    }

    /**
     * 复制单元格
     *
     * @param source 原单元格
     * @param target 目标单元格
     */
    public static void copyCell(Cell source, Cell target, boolean copyCellStyle) {
        if (!ObjectUtils.allNotNull(source, target)) {
            throw new NullPointerException("参数[source,target]不能为NUll");
        }
        //复制单元格样式
        if (copyCellStyle) {
            CellStyle cellStyle = source.getCellStyle();
            if (source.getSheet().getWorkbook() == target.getSheet().getWorkbook()) {
                target.setCellStyle(cellStyle);
            } else {
                CellStyle style = target.getSheet().getWorkbook().createCellStyle();
                style.cloneStyleFrom(cellStyle);
                target.setCellStyle(style);
            }
        }
        copyValue(source, target);
    }

    /**
     * 复制单元格的值
     */
    private static void copyValue(Cell source, Cell target) {
        switch (source.getCellTypeEnum()) {
            case STRING:
                target.setCellValue(source.getStringCellValue());
                break;
            case NUMERIC:
                target.setCellValue(source.getNumericCellValue());
                break;
            case BOOLEAN:
                target.setCellValue(source.getBooleanCellValue());
                break;
            case FORMULA:
                target.setCellValue(source.getCellFormula());
                break;
            case BLANK:
                target.setCellType(CellType.BLANK);
                break;
            case ERROR:
                target.setCellValue(source.getErrorCellValue());
                break;
            default:
                break;
        }
    }

    /**
     * 删除sheet页
     *
     * @param sheetNames 需要删除的sheet页名字
     */
    public static void removeSheetInclude(Workbook workbook, String... sheetNames) {
        int count = workbook.getNumberOfSheets();
        List<Integer> integers = new ArrayList<>(count);
        if (!ArrayUtils.isEmpty(sheetNames)) {
            List<String> strings = Arrays.asList(sheetNames);
            for (int i = 0; i < count; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if (strings.contains(sheet.getSheetName())) {
                    integers.add(i);
                }
            }
        }
        integers.forEach(workbook::removeSheetAt);
    }

    /**
     * 删除sheet页
     *
     * @param ignoreSheetNames 需要排除的sheet页名字
     */
    public static void removeSheetExclude(Workbook workbook, String... ignoreSheetNames) {
        int count = workbook.getNumberOfSheets();
        List<Integer> integers = new ArrayList<>(count);
        if (!ArrayUtils.isEmpty(ignoreSheetNames)) {
            List<String> strings = Arrays.asList(ignoreSheetNames);
            for (int i = 0; i < count; i++) {
                Sheet sheet = workbook.getSheetAt(i);
                if (!strings.contains(sheet.getSheetName())) {
                    integers.add(i);
                }
            }
        } else {
            for (int i = 0; i < count; i++) {
                integers.add(i);
            }
        }
        integers.forEach(workbook::removeSheetAt);
    }
}
