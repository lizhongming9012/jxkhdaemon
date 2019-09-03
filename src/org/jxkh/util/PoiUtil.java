package org.jxkh.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.DateUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

/** excel读写工具类 */
public class PoiUtil {
	private static Logger logger = Logger.getLogger(PoiUtil.class);
	private final static String xls = "xls";
	private final static String xlsx = "xlsx";

	/**
	 * 读入excel文件，根据传入的表格索引，解析后返回
	 * 
	 * @return 把每行中的值作为一个数组，所有行作为一个集合返回
	 * @param file
	 * @throws IOException
	 */
	public static List<String[]> readExcelBySheetNum(MultipartFile file,
			int sheetNum) throws IOException {
		// 检查文件
		checkFile(file);
		// 获得Workbook工作薄对象
		Workbook workbook = getWorkBook(file);
		// 创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
		List<String[]> list = new ArrayList<String[]>();
		if (workbook != null && sheetNum <= workbook.getNumberOfSheets()) {
			// 获得当前sheet工作表
			Sheet sheet = workbook.getSheetAt(sheetNum);
			// 获得当前sheet的开始行
			int firstRowNum = sheet.getFirstRowNum();
			// 获得当前sheet的结束行
			int lastRowNum = sheet.getLastRowNum();
			// 循环除了第一行的所有行
			for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
				// 获得当前行
				Row row = sheet.getRow(rowNum);
				if (row == null) {// 跳过空行
					continue;
				}
				// 获得当前行的开始列
				int firstCellNum = row.getFirstCellNum();
				// 获得当前行的列数
				int lastCellNum = row.getPhysicalNumberOfCells();
				String[] cells = new String[row.getPhysicalNumberOfCells()];
				// 循环当前行
				for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
					Cell cell = row.getCell(cellNum);
					if (cell != null) {// 跳过空单元格
						cells[cellNum] = getCellValue(cell);
					}
				}
				list.add(cells);
			}
			// ((InputStream) workbook).close();
		}
		return list;
	}

	/**
	 * 判断文件
	 * 
	 * @param file
	 * @throws IOException
	 */
	public static void checkFile(MultipartFile file) throws IOException {
		// 判断文件是否存在
		if (null == file) {
			logger.error("文件不存在！");
			throw new FileNotFoundException("文件不存在！");
		}
		// 获得文件名
		String fileName = file.getOriginalFilename();
		// 判断文件是否是excel文件
		if (!fileName.endsWith(xls) && !fileName.endsWith(xlsx)) {
			logger.error(fileName + "不是excel文件");
			throw new IOException(fileName + "不是excel文件");
		}
	}

	/**
	 * 读入excel文件，解析所有Sheet后返回
	 * 
	 * @return 把每行中的值作为一个数组，所有行作为一个集合返回
	 * @param file
	 * @throws IOException
	 */
	public static List<String[]> readExcel(MultipartFile file)
			throws IOException {
		// 检查文件
		checkFile(file);
		// 获得Workbook工作薄对象
		Workbook workbook = getWorkBook(file);
		// 创建返回对象，把每行中的值作为一个数组，所有行作为一个集合返回
		List<String[]> list = new ArrayList<String[]>();
		if (workbook != null) {
			for (int sheetNum = 0; sheetNum < workbook.getNumberOfSheets(); sheetNum++) {
				// 获得当前sheet工作表
				Sheet sheet = workbook.getSheetAt(sheetNum);
				if (sheet == null) {
					continue;
				}
				// 获得当前sheet的开始行
				int firstRowNum = sheet.getFirstRowNum();
				// 获得当前sheet的结束行
				int lastRowNum = sheet.getLastRowNum();
				// 循环除了第一行的所有行
				for (int rowNum = firstRowNum + 1; rowNum <= lastRowNum; rowNum++) {
					// 获得当前行
					Row row = sheet.getRow(rowNum);
					if (row == null) {// 跳过空行
						continue;
					}
					// 获得当前行的开始列
					int firstCellNum = row.getFirstCellNum();
					// 获得当前行的列数
					int lastCellNum = row.getPhysicalNumberOfCells();
					String[] cells = new String[row.getPhysicalNumberOfCells()];
					// 循环当前行
					for (int cellNum = firstCellNum; cellNum < lastCellNum; cellNum++) {
						Cell cell = row.getCell(cellNum);
						if (cell != null) {// 跳过空单元格
							cells[cellNum] = getCellValue(cell);
						}
					}
					list.add(cells);
				}
			}
			// ((InputStream) workbook).close();
		}
		return list;
	}

	/**
	 * 获取文件内容
	 * 
	 * @param file
	 * @return
	 */
	public static Workbook getWorkBook(MultipartFile file) {
		// 获得文件名
		String fileName = file.getOriginalFilename();
		// 创建Workbook工作薄对象，表示整个excel
		Workbook workbook = null;
		try {
			// 获取excel文件的io流
			InputStream is = file.getInputStream();
			// 根据文件后缀名不同(xls和xlsx)获得不同的Workbook实现类对象
			if (fileName.endsWith(xls)) {
				// 2003
				workbook = new HSSFWorkbook(is);
			} else if (fileName.endsWith(xlsx)) {
				// 2007
				workbook = new XSSFWorkbook(is);
			}
		} catch (IOException e) {
			logger.info(e.getMessage());
		}
		return workbook;
	}

	/**
	 * 根据单元格类型转换成String输出
	 */
	public static String getCellValue(Cell cell) {
		String value = "";
		try {// 判断数据的类型
			if (cell.getCellType() == Cell.CELL_TYPE_BLANK) { // 空值
				value = "";
			} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {// Boolean
				value = String.valueOf(cell.getBooleanCellValue());
			} else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {// 故障
				value = "非法字符";
			} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {// 公式
				// value = cell.getCellFormula();
				// 处理公式格式的单元格
				try {
					value = cell.getStringCellValue();
				} catch (IllegalStateException e) {
					value = String.valueOf(cell.getNumericCellValue());
				}
			} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) { // 数字
				// 处理数据格式的单元格时，进行判断是否为时间，若是时间，则以指定格式处理
				if (DateUtil.isCellDateFormatted(cell)) { // 日期型数字
					Date tempValue = cell.getDateCellValue();
					SimpleDateFormat simpleFormat = new SimpleDateFormat(
							"yyyy-MM-dd");
					value = simpleFormat.format(tempValue);
				} else {
					value = String.valueOf(cell.getNumericCellValue());
				}
			} else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {// 字符串
				value = cell.getStringCellValue();
			} else {
				value = String.valueOf(cell.getDateCellValue());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return value;
	}
}