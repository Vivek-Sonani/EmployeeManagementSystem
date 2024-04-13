package com.springboot.employee.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.events.IEventDispatcher;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.IBlockElement;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPHeaderCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.springboot.employee.model.Employee;
import com.springboot.employee.repository.EmployeeRepository;
import com.itextpdf.layout.Document;

@Service
public class PdfGenerator {

	@Autowired
	private EmployeeRepository employeeRepository;
    private static final Logger logger = LoggerFactory.getLogger(PdfGenerator.class);

	public byte[] createPDF() {
		 String pdfFilename = "example.pdf";
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfWriter writer = new PdfWriter(new File(pdfFilename));
		
          PdfDocument pdfDoc = new PdfDocument(writer);

          // Create an instance of PageNoAndNewFooterHandler
          PageNoAndNewFooterHandler pageEvent = new PageNoAndNewFooterHandler();

          // Register the event handler with the PdfDocument
          pdfDoc.addEventHandler(com.itextpdf.kernel.events.PdfDocumentEvent.END_PAGE, pageEvent);
          HeaderFooterPageEvent headerFooterPageEvent = new HeaderFooterPageEvent();
          pdfDoc.addEventHandler(com.itextpdf.kernel.events.PdfDocumentEvent.START_PAGE, headerFooterPageEvent);

          // Create a Document
          Document document = new Document(pdfDoc);

			List<Employee> emp = employeeRepository.findAll();
			Comparator<Employee> cmp = Comparator.comparing(Employee::getPosition)
					.thenComparing(Employee::getFirstname);
			Collections.sort(emp, cmp);
			System.out.println(emp);

			Rectangle rect = new Rectangle(30, 30, 300, 820);
			Rectangle rectangle = new Rectangle(45, 18, 550, 790);
			rectangle.setBackgroundColor(new BaseColor(128, 128, 128));
			//writer.setBoxSize("art1", rectangle);
			//HeaderFooterPageEvent event = new HeaderFooterPageEvent();
			//pdfDoc.addEventHandler(pdfFilename, );
			   Table billTable = new Table(new float[] { 1, 2, 2.2f, 2, 3, 2, 2, 2 });
			 //  billTable.setFixedLayout();
	            billTable.setSpacingRatio(5.0f);
	            billTable.setPaddingTop(10);
	        	billTable.addCell((Cell) getBillHeaderCell(new Paragraph("ID")));
				billTable.addCell((Cell) getBillHeaderCell(new Paragraph("Firstname")));
				billTable.addCell((Cell) getBillHeaderCell(new Paragraph("Middlename")));
				billTable.addCell((Cell) getBillHeaderCell(new Paragraph("Lastname")));
				billTable.addCell((Cell) getBillHeaderCell(new Paragraph("Email")));
				billTable.addCell((Cell) getBillHeaderCell(new Paragraph("Position")));
				billTable.addCell((Cell) getBillHeaderCell(new Paragraph("Contact")));
				billTable.addCell((Cell) getBillHeaderCell(new Paragraph("Salary")));

	            for (Employee employee : emp) {
	                billTable.addCell((Cell) getBillRowCell(new Paragraph(String.valueOf(employee.getEmployId()))));
	                billTable.addCell((Cell) getBillRowCell(new Paragraph(employee.getFirstname())));
	                billTable.addCell((Cell) getBillRowCell(new Paragraph(employee.getMiddlename())));
	                billTable.addCell((Cell) getBillRowCell(new Paragraph(employee.getLastname())));
	                billTable.addCell((Cell) getBillRowCell(new Paragraph(employee.getEmail())));
	                billTable.addCell((Cell) getBillRowCell(new Paragraph(employee.getPosition())));
	                billTable.addCell((Cell) getBillRowCell(new Paragraph(String.valueOf(employee.getContactNo()))));
	                billTable.addCell((Cell) getBillRowCell(new Paragraph(String.valueOf(employee.getSalary()))));
	            }
	        
			document.add(billTable);

			document.close();

			System.out.println("Pdf created successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}


	public static Cell getBillHeaderCell(Paragraph text) {
		   Color fontColor = new DeviceRgb(0, 255, 255);
		    float fontSize = 9f;
		  // PdfFont font = null;

		    Cell cell = new Cell()
		           // .setFont(fo)
		            .setFontSize(fontSize)
		            .setFontColor(fontColor)
		            .setBackgroundColor(new DeviceRgb(255, 255, 255)) // White background color
		            .setTextAlignment(TextAlignment.CENTER)
		            .setPadding(5f)
		           // .setBorder(null)
		            .add(text); 
		return cell;
	}
	public static Object getBillHeaderCellColor(String text,Table billTable) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.COURIER_OBLIQUE, 10, Font.BOLD);
		font.setColor(BaseColor.BLACK);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setBorderColor(new BaseColor(255, 0, 0));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		cell.disableBorderSide(0);
	    cell.setBorder(PdfPCell.NO_BORDER);  // remove border from the cell of table
	    for (int i = 0; i < billTable.getNumberOfRows(); i++) {
            // cell = billTable.getRow(i).getCells()[5];
            cell.setBackgroundColor(BaseColor.GREEN);
        }
		// cell.setBackgroundColor(new BaseColor(128, 128, 128));
	      //List<PdfPHeaderCell> list =  cell.getHeaders();
	    
	    if (text.equalsIgnoreCase("Position")) {
            cell.setBackgroundColor(BaseColor.LIGHT_GRAY); // Set background color for the column
        }	   
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(5.0f);
		return (Object) cell;
	}

	public static Object getBillRowCell1(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.COURIER_OBLIQUE, 9);
		fs.addFont(font);

		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setBorderColor(new BaseColor(255, 0, 0));
		// cell.setBackgroundColor(new BaseColor(0, 255, 255));
		if (text.equalsIgnoreCase("scientist")) {
			  for (Chunk chunk : phrase.getChunks()) {
		          //  chunk.setBackground(BaseColor.GREEN); //Set background color of the chunk , this is used to set the color around only text, not whole cell
		        }
		}
	    logger.info(""+text);
	   // cell.setBorder(PdfPCell.NO_BORDER);
	    cell.setBorder(PdfPCell.BOTTOM);  // remove border from the cell of table
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(5.0f);
		cell.disableBorderSide(0);
		// cell.setBorderWidthBottom(1.0f);
		// cell.setBorderWidthTop(1.0f);
		return (Object) cell;
	}
	public static Cell getBillRowCell(Paragraph text) {
	    // Define font properties
	    Color fontColor = new DeviceRgb(0, 0, 0); // Black color
	    float fontSize = 9f;
	  // PdfFont font = null;

	    Cell cell = new Cell()
	          //  .setFont(font)
	            .setFontSize(fontSize)
	            .setFontColor(fontColor)
	            .setBackgroundColor(new DeviceRgb(255, 255, 255)) // White background color
	            .setTextAlignment(TextAlignment.CENTER)
	            .setPadding(5f)
	           // .setBorder(null)
	            .add(text); 


	    return cell;
}
}
