package com.sds.dataroom.excel;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//자바에서 엑셀파일을 접근하여, 데이터를 입력해보자
public class ExcelWrite {
	
	public static void main(String[] args) {
		
			
			Workbook workbook = new XSSFWorkbook();
			
			//시트 생성
			Sheet sheet = workbook.createSheet("info");
			
			//기존에 없었던 새로운 Row 생성
			Row row =sheet.createRow(0);
			
			//생성된 Row에 들어갈 Cell 2개 만들기
			Cell nameCell = row.createCell(0); // 이름 column
			Cell salCell = row.createCell(1);
			
			//생성된 cell에 값부여하기
			nameCell.setCellValue("Batman");
			salCell.setCellValue(7000);
			
		//새로운 파일을 생성하고 엑셀내용을 적용하자
		String path="D:/javaEE_workspace/Dataroom/src/main/webapp/data/emp2.xlsx";
		try {
			FileOutputStream fos = new FileOutputStream(path);
			workbook.write(fos); //가상의 엑셀정보를, 실제 파일에 파일에 반영하기
			workbook.close();
			
			System.out.println("엑셀 생성 성공");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
