package crapy.web.every;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Scanner;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class DisUdemy {

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

		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.createSheet("KhoaHoc");
		
		WebClient webClient = new WebClient();
		webClient.getOptions().setJavaScriptEnabled(false);
		webClient.getOptions().setCssEnabled(false);
		long startTime = new Date().getTime();
		int rowNum = 0;
		while (start <= end) {
			
			HtmlPage page = (HtmlPage) webClient.getPage("https://www.discudemy.com/all/" + start);

			for (Object o1 : page.getByXPath("//section[@class='card']/div[1]/div[2]/span[1]/div")) {
				HtmlElement h1 = (HtmlElement) o1;
				
				String oldUrl = ((DomElement) h1.getParentNode().getParentNode()).getPreviousElementSibling()
						.getFirstElementChild().getAttribute("href");
				
				String newUrl = "https://www.discudemy.com/go"
						+ oldUrl.trim().substring(oldUrl.trim().lastIndexOf("/"), oldUrl.trim().length());
				HtmlPage page2 = (HtmlPage) webClient.getPage(newUrl);
				for (Object ha2 : page2.getByXPath("//div[@class='ui segment']/a")) {
					HtmlAnchor h2 = (HtmlAnchor) ha2;
					System.out.println(h2.getHrefAttribute());
					sheet.createRow(rowNum++).createCell(0).setCellValue(h2.getHrefAttribute().trim());
				}
			}
			start++;
		}
		FileOutputStream outputStream = new FileOutputStream("C:\\Users\\DongVV2\\Desktop\\Discudemy.xlsx");
		wb.write(outputStream);
		wb.close();
		long endTime = new Date().getTime();
		System.out.println("Done : " + ((float)(endTime - startTime) / 60000) + " minute");
	}

}
