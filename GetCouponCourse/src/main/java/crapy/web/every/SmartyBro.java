package crapy.web.every;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class SmartyBro {

	public static void main(String[] args) {

		try {
			Scanner sn = new Scanner(System.in);
		    System.out.println("Ban muon get data from page ... to ...? (example: get page 1 -> 6)");
		    System.out.println("Page Start: ");
		    System.out.println("Page End: ");
		    System.out.println("Enter continue :)");
		    String startStr = sn.nextLine();
		    String endStr = sn.nextLine();
		    
		    int start = Integer.parseInt(startStr);
		    int end = Integer.parseInt(endStr);
		    
		    System.out.println("=========================== Dang get data from page " + start + " to page " + end + " ====================================");

			XSSFWorkbook wb = new XSSFWorkbook();
			XSSFSheet sheet = wb.createSheet("KhoaHoc");
			
			WebClient webClient = new WebClient();
			webClient.getOptions().setJavaScriptEnabled(false);
			webClient.getOptions().setCssEnabled(false);
			HtmlAnchor ha;
			long startTime = new Date().getTime();
			int rowNum = 0;
			while (start <= end) {
				
				HtmlPage page = (HtmlPage) webClient
						.getPage("https://smartybro.com/category/udemy-free-courses/page/" + start);

				List<HtmlAnchor> listHtml = (List<HtmlAnchor>) page.getByXPath("//h2[@class='grid-tit']/a");
				for(int i=0;i<listHtml.size();i++) {
					HtmlAnchor html = (HtmlAnchor) listHtml.get(i);
					HtmlPage next = (HtmlPage) html.click();
					List localIterator2 = next.getByXPath("//a[@class='fasc-button fasc-size-xlarge fasc-type-flat external']");
					for(int j = 0;j<localIterator2.size();j++) {
						ha = (HtmlAnchor) localIterator2.get(j);
						System.out.println(ha.getHrefAttribute().trim());
						sheet.createRow(rowNum++).createCell(0).setCellValue(ha.getHrefAttribute().trim());
					}
				}
				start++;
			}
			
			FileOutputStream outputStream = new FileOutputStream("C:\\Users\\DongVV2\\Desktop\\khoahoc.xlsx");
			wb.write(outputStream);
			wb.close();
			long endTime = new Date().getTime();
			System.out.println("Done : " + ((float)(endTime - startTime) / 60000) + " minute");

		} catch (FailingHttpStatusCodeException e) {

			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		}

	}
}
