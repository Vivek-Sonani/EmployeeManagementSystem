package com.springboot.employee.service;

import java.io.ByteArrayInputStream;

import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.FontSelector;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.springboot.employee.model.Employee;
import com.springboot.employee.repository.EmployeeRepository;

@Service
public class PdfGenerator {

	@Autowired
	private EmployeeRepository employeeRepository;

	public byte[] createPDF() {
		String pdfFilename = "example.pdf";
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		try {
			List<Employee> emp = employeeRepository.findAll();

		//	OutputStream file = new FileOutputStream(new File(pdfFilename));
			Document document = new Document();
			PdfWriter writer = PdfWriter.getInstance(document, out);

			Rectangle rect = new Rectangle(30, 30, 300, 820);
			writer.setBoxSize("art", rect);
			Rectangle rectangle = new Rectangle(45, 18, 550, 790);
			rectangle.setBackgroundColor(new BaseColor(128, 128, 128));
			writer.setBoxSize("art1", rectangle);
			HeaderFooterPageEvent event = new HeaderFooterPageEvent();
			writer.setPageEvent(event);

			PdfPTable billTable = new PdfPTable(8);
			billTable.setWidthPercentage(100);
			billTable.setWidths(new float[] { 1, 2, (float) 2.2, 2, 3, 2, 2, 2 });
			billTable.setSpacingBefore(5.0f);
			billTable.setPaddingTop(10);
			billTable.addCell(getBillHeaderCell("ID"));
			billTable.addCell(getBillHeaderCell("Firstname"));
			billTable.addCell(getBillHeaderCell("Middlename"));
			billTable.addCell(getBillHeaderCell("Lastname"));
			billTable.addCell(getBillHeaderCell("Email"));
			billTable.addCell(getBillHeaderCell("Position"));
			billTable.addCell(getBillHeaderCell("Contact"));
			billTable.addCell(getBillHeaderCell("Salary"));

			for (Employee employee : emp) {
				billTable.addCell(getBillRowCell(String.valueOf(employee.getEmployId())));
				billTable.addCell(getBillRowCell(employee.getFirstname()));
				billTable.addCell(getBillRowCell(employee.getMiddlename()));
				billTable.addCell(getBillRowCell(employee.getLastname()));
				billTable.addCell(getBillRowCell(employee.getEmail()));
				billTable.addCell(getBillRowCell(employee.getPosition()));
				billTable.addCell(getBillRowCell(String.valueOf(employee.getContactNo())));
				billTable.addCell(getBillRowCell(String.valueOf(employee.getSalary())));
			}

//			billTable.addCell(getBillRowCell("2"));
//			billTable.addCell(getBillRowCell("Accessories"));
//			billTable.addCell(getBillRowCell("Nokia Lumia 610 Panel Serial: TIN3720 "));
//			billTable.addCell(getBillRowCell("200.0"));
//			billTable.addCell(getBillRowCell("1"));
//			billTable.addCell(getBillRowCell("200.0"));
//

			document.open();

			document.add(billTable);

			document.close();

			//file.close();

			System.out.println("Pdf created successfully..");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	public static void setHeader() {

	}

	public static PdfPCell getBillHeaderCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.COURIER_OBLIQUE, 10, Font.BOLD);
		font.setColor(BaseColor.BLACK);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setBorderColor(new BaseColor(255, 0, 0));
		cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
		// cell.setBackgroundColor(new BaseColor(128, 128, 128));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(5.0f);
		return cell;
	}

	public static PdfPCell getBillRowCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.COURIER_OBLIQUE, 9);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setBorderColor(new BaseColor(255, 0, 0));
		// cell.setBackgroundColor(new BaseColor(0, 255, 255));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(5.0f);
		// cell.setBorderWidthBottom(1.0f);
		// cell.setBorderWidthTop(1.0f);
		return cell;
	}

	public static PdfPCell getIRHCell(String text, int alignment) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 16);
		/* font.setColor(BaseColor.GRAY); */
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setPadding(5);
		cell.setHorizontalAlignment(alignment);
		cell.setBorder(PdfPCell.NO_BORDER);
		return cell;
	}

	public static PdfPCell getIRDCell(String text) {
		PdfPCell cell = new PdfPCell(new Paragraph(text));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(5.0f);
		cell.setBorderColor(BaseColor.LIGHT_GRAY);
		return cell;
	}

	public static PdfPCell getBillFooterCell(String text) {
		PdfPCell cell = new PdfPCell(new Paragraph(text));
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setPadding(5.0f);
		cell.setBorderWidthBottom(0);
		cell.setBorderWidthTop(0);
		return cell;
	}

	public static PdfPCell getValidityCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setBorder(0);
		return cell;
	}

	public static PdfPCell getAccountsCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setBorderWidthRight(0);
		cell.setBorderWidthTop(0);
		cell.setPadding(5.0f);
		return cell;
	}

	public static PdfPCell getAccountsCellR(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setBorderWidthLeft(0);
		cell.setBorderWidthTop(0);
		cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		cell.setPadding(5.0f);
		cell.setPaddingRight(20.0f);
		return cell;
	}

	public static PdfPCell getdescCell(String text) {
		FontSelector fs = new FontSelector();
		Font font = FontFactory.getFont(FontFactory.HELVETICA, 10);
		font.setColor(BaseColor.GRAY);
		fs.addFont(font);
		Phrase phrase = fs.process(text);
		PdfPCell cell = new PdfPCell(phrase);
		cell.setHorizontalAlignment(Element.ALIGN_CENTER);
		cell.setBorder(0);
		return cell;
	}

}
