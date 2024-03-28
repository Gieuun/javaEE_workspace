package com.sds.dataroom.excel;

import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//자바언어로 엑셀을 연동하려면 apache
public class ExcelRead {
		public static void main(String[] args) {
			
			//하드 디스크에 있는 파일을 대상으로 WorkBook 객체를 생성하자
			try {
				//엑셀 파일을 접근
				Workbook workbook = new XSSFWorkbook("D:/javaEE_workspace/Dataroom/src/main/webapp/data/emp2.xlsx");
				
				//Sheet에 접근
				Sheet sheet = workbook.getSheetAt(0); //첫번째 시트에 접근
				
				//시트는 row(행)들을 iterator로 반환 해준다
				Iterator<Row> rowIt =sheet.iterator();
				
				while(rowIt.hasNext()) { //row 만큼 반복문 돌ㄹ리자
					Row row = rowIt.next();; //row 1개 반환
					Iterator<Cell> cellIt = row.iterator(); //이 row를 구성하는 커럶
					
					while(cellIt.hasNext()) { //하나ㅏ의 Row를 구성하는 컬럼 수 만큼
						Cell cell = cellIt.next();
						if(cell.getCellType()==CellType.STRING) { //문자형이라면
							System.out.print(cell.getStringCellValue());
						}else if(cell.getCellType()==CellType.NUMERIC) {
							System.out.println(cell.getNumericCellValue());
						}
					}
					System.out.println("--------------------------------------");
				}
				
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			} 
		}
}
