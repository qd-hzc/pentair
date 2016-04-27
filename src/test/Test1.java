package test;

import java.io.*;

import jxl.*;
import jxl.write.*;


public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			WritableWorkbook book = Workbook.createWorkbook((File)null);
			
			
			WritableSheet sheet = book.createSheet("ProductNo", 1);
			
			CellView cellView = new CellView();
			cellView.setSize(200);
			sheet.setColumnView(0, cellView);
			sheet.addCell(new Label(0, 0, "产品编码"));
			sheet.addCell(new Label(1, 0, "产品名称"));
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
