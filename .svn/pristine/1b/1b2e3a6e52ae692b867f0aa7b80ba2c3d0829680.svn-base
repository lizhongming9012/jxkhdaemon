package org.jxkh.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.jxkh.util.StringUtil;

public class ExcelViewBuilder extends AbstractExcelView {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook wb,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			// 从Map获取数据集
			List<Object> data = (List<Object>) map.get("data");
			String title = (String) map.get("title");
			Map<String, String> colInfo = (Map<String, String>) map
					.get("colInfo");

			HSSFCellStyle cellStyle = wb.createCellStyle();
			cellStyle
					.setDataFormat(HSSFDataFormat.getBuiltinFormat("#,##0.00"));
			// 获取sheet表单
			Sheet sheet = wb.createSheet("Sheet1");
			// 设置列的宽度
			this.setColumnWidth(sheet);
			// 写表头
			this.fillTableHeader(wb, sheet, colInfo, title);
			// 将数据集写入到excel标题
			int i = 2;
			Row row = null;
			for (Object record : data) {
				row = sheet.createRow(i);
				this.writeToRowFromRecord(row, sheet, record, cellStyle,
						colInfo);
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void writeToRowFromRecord(Row row, Sheet sheet, Object record,
			CellStyle cellStyle, Map<String, String> colInfo)
			throws IllegalArgumentException, IllegalAccessException,
			InvocationTargetException, SecurityException, NoSuchMethodException {
		int i = 0;
		for (String key : colInfo.keySet()) {
			Cell cell = row.createCell(i);
			Class<?> service = record.getClass();
			key = key.substring(0, 1).toUpperCase() + key.substring(1);
			String methodName = "get" + key;
			Method im = (Method) service.getMethod(methodName);
			Class<?> returnType = im.getReturnType();
			Object resultObject = im.invoke(record);
			String result = "";
			if (resultObject != null) {
				if ("java.util.Date".equals(returnType.getName())) {
					SimpleDateFormat sdf = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					result = sdf.format(resultObject);
				} else {
					result = resultObject.toString();
				}
				if ("java.lang.Float".equals(returnType.getName())
						|| StringUtil.isScienticNotation(result)) {
					cell.setCellStyle(cellStyle);
					cell.setCellValue(Float.parseFloat(result));
				} else {
					cell.setCellValue(result);
				}
			}
			i++;
		}
	}

	public void fillTableHeader(HSSFWorkbook wb, Sheet sheet,
			Map<String, String> colInfo, String title) {
		Cell cell = null;
		int cols = colInfo.size();
		Row row0 = sheet.createRow(0);
		cell = row0.createCell(0);
		CellStyle style1 = this.getStyle(1, wb);
		// 标题
		cell.setCellValue(title);
		cell.setCellStyle(style1);
		// 合并单元格
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, cols - 1));
		Row row1 = sheet.createRow(1);
		int i = 0;
		for (String key : colInfo.keySet()) {
			cell = row1.createCell(i);
			cell.setCellValue(colInfo.get(key));
			i++;
		}
	}

	public void setColumnWidth(Sheet sheet) {
		final int[] width = new int[] { 4096,
				8448 
		};
		for (int i = 0; i < width.length; i++) {
			sheet.setColumnWidth(i, width[i]);
		}
	}

	public CellStyle getStyle(int columNumber, HSSFWorkbook wb) {
		// 设置字体
		CellStyle style = null;
		Font font = wb.createFont();
		// 标题
		if (columNumber == 1) {
			font.setFontHeightInPoints((short) 20);
			font.setFontName("宋体");
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style = wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setFont(font);
		}
		// 列名
		if (columNumber == 2) {
			font.setFontHeightInPoints((short) 10);
			font.setFontName("宋体");
			font.setBoldweight(Font.BOLDWEIGHT_BOLD);
			style = wb.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			style.setFont(font);
		}
		return style;
	}

}
