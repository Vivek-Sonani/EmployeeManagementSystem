package com.springboot.employee.service;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfWriter;

public class HeaderFooterPageEvent extends PdfPageEventHelper {
	public void onStartPage(PdfWriter writer, Document document) {
		Rectangle rect = writer.getBoxSize("art");
		Font font = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);
		font.setColor(BaseColor.BLUE);

		// PdfContentByte pdfContentByte = writer.getDirectContent();
		PdfContentByte canvas = writer.getDirectContentUnder();
		canvas.setColorFill(new BaseColor(128, 128, 128)); // Set the color for the footer
		canvas.rectangle(00, 809, 600, 30); // Adjust the height as needed
		canvas.fill();

		String waterMarkText = "CodeTailor";

		Phrase phrase = new Phrase(waterMarkText, new Font(FontFamily.TIMES_ROMAN, 90, // Select the Font type of
				Font.ITALIC, // Select the Font style of waterMark Text
				BaseColor.LIGHT_GRAY)); // Select the Font colour of waterMark Text

		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, // Keep waterMark center aligned
				phrase, 300f, 400f, 45f);

		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
				new Phrase("Employee Management System", font), rect.getRight(), rect.getTop(), 0);
	}

	public void onEndPage(PdfWriter writer, Document document) {
		Rectangle rect = writer.getBoxSize("art1");

		Font font = new Font(Font.FontFamily.HELVETICA, 8);
		PdfContentByte canvas = writer.getDirectContentUnder();
		canvas.setColorFill(new BaseColor(128, 128, 128)); // Set the color for the footer
		canvas.rectangle(00, 5, 600, 30); // Adjust the height as needed
		canvas.fill();

		Phrase linkText = new Phrase("abc@gmail.com", font);
		int pageNumber = writer.getPageNumber();

		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER,
				new Phrase(String.valueOf(pageNumber), font), rect.getLeft(), rect.getBottom(), 0);
		ColumnText.showTextAligned(writer.getDirectContent(), Element.ALIGN_CENTER, linkText, rect.getRight(),
				rect.getBottom(), 0);
	}
}
