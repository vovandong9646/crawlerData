package crapy.web.every;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class GetCouponLearnViral {
	public static void main(String[] args) throws Exception {
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
	    
	    WebClient webClient = new WebClient();
	    webClient.getOptions().setCssEnabled(false);
	    webClient.getOptions().setJavaScriptEnabled(false);
	    Workbook workbook = new XSSFWorkbook();
	    Sheet sheet = workbook.createSheet("CouponUdemy");
	    int rowNum = 0;
	    long startTime = new Date().getTime();
	    for (int i = start; i <= end; i++) {
	      String url = "https://udemycoupon.learnviral.com/coupon-category/free100-discount/page/" + i;
	      HtmlPage currentPage = (HtmlPage)webClient.getPage(url);
	      DomNode domNode = (DomNode)currentPage.getByXPath("//*[@id=\"content\"]/div[2]/div/div").get(0);
	      String page = domNode.asXml();
	      Pattern pattern = Pattern.compile("<a href=\"(.*)\" id=\"(.*)\">");
	      Matcher matcher = pattern.matcher(page);
	      while (matcher.find()) {
	        Row row = sheet.createRow(rowNum);
	        Cell cell = row.createCell(0, CellType.STRING);
	        cell.setCellValue(matcher.group(1).toString());
	        System.out.println(matcher.group(1).toString());
	        rowNum++;
	      }
	    }
	    
	    FileOutputStream fileOut = new FileOutputStream("C:\\Users\\DongVV2\\Desktop\\udemycoupon.xlsx");
	    workbook.write(fileOut);
	    fileOut.close();
	    long endTime = new Date().getTime();
	    System.out.println("Done : " + ((float)(endTime - startTime) / 60000) + " minute");
	}
}

