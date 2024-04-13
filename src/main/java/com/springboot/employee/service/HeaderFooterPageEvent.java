package com.springboot.employee.service;

import com.itextpdf.kernel.events.Event;

import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.text.BaseColor;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.text.Font;
import com.itextpdf.kernel.geom.Rectangle;


public class HeaderFooterPageEvent extends PdfPageEventHelper implements IEventHandler {
	int i = 0;

//	public void onStartPage(PdfWriter writer, Document document) {
//		Rectangle rect = writer.getBoxSize("art");
//		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);
//		font.setColor(BaseColor.BLUE);
//
//		// PdfContentByte pdfContentByte = writer.getDirectContent();
//		PdfContentByte canvas = writer.getDirectContentUnder();
//		canvas.setColorFill(new BaseColor(128, 128, 128)); // Set the color for the footer
//		canvas.rectangle(00, 809, 600, 30); // Adjust the height as needed
//		canvas.fill();
//
//		String waterMarkText = "CodeTailor";
//
//		Phrase phrase = new Phrase(waterMarkText, new Font(FontFamily.TIMES_ROMAN, 90, // Select the Font type of
//				Font.ITALIC, // Select the Font style of waterMark Text
//				BaseColor.LIGHT_GRAY)); // Select the Font colour of waterMark Text
//
//		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, // Keep waterMark center aligned
//				phrase, 300f, 400f, 45f);
//
//		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
//				new Phrase("Employee Management System", font), rect.getRight(), rect.getTop(), 0);
//	}
//
//	public void onEndPage(PdfWriter writer, Document document) {
//		Rectangle rect = writer.getBoxSize("art1");
//
//		Font font = new Font(Font.FontFamily.HELVETICA, 8);
//		PdfContentByte canvas = writer.getDirectContentUnder();
//		canvas.setColorFill(new BaseColor(128, 128, 128)); // Set the color for the footer
//		canvas.rectangle(00, 5, 600, 30); // Adjust the height as needed
//		canvas.fill();
//		
//		int page = document.getPageNumber();
//
//		Phrase linkText = new Phrase("abc@gmail.com", font);
//		int pageNumber = writer.getPageNumber();
//		int n = document.getPageNumber();
//		System.out.println(pageNumber);
//		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
//				new Phrase("page" + ++i + "of: " + String.valueOf(n), font), rect.getLeft(), rect.getBottom(), 0);
//		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, linkText, rect.getRight(),
//				rect.getBottom(), 0);
//	}

	@Override
	public void handleEvent(Event event) {
		// TODO Auto-generated method stub
		 PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
		    PdfDocument pdfDoc = docEvent.getDocument();
		    PdfPage page = docEvent.getPage();
		    Rectangle pageSize = page.getPageSize();

		    PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

		    Rectangle rectangle = new Rectangle(150, 150, pageSize.getWidth() - 35, 30);
		    Canvas canvas = new Canvas(pdfCanvas, rectangle);

		    Table table = new Table(UnitValue.createPercentArray(new float[] {50.00f, 50.00f}));
		    
		   // canvas.setBackgroundColor(new BaseColor(128, 128, 128)); // Set the color for the footer
			//canvas.rectangle(00, 809, 600, 30); // Adjust the height as needed
			//canvas.fill();

			String waterMarkText = "CodeTailor";
			Paragraph p = new Paragraph(waterMarkText);
			p.setFontSize(Font.ITALIC);
            p.setRotationAngle(45);

            table.addCell(p);
    	    table.setBorder(null);

    	    canvas.add(table);

	}		
	
}
