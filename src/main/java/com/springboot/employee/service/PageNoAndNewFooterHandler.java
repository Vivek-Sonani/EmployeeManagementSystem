package com.springboot.employee.service;

import java.text.SimpleDateFormat;


import java.util.Date;
import java.util.TimeZone;


import com.itextpdf.kernel.events.Event;
import com.itextpdf.kernel.events.IEventHandler;
import com.itextpdf.kernel.events.PdfDocumentEvent;
import com.itextpdf.kernel.geom.Rectangle;
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

/**
 * Handle page related event for PDF document.
 *
 */
public class PageNoAndNewFooterHandler implements IEventHandler {

  /**
   * Hook for handling events. Implementations can access the PdfDocument instance associated to the
   * specified Event or, if available, the PdfPage instance. Specified by: handleEvent(...) in
   * IEventHandler Parameters: event the Event that needs to be processed
   * 
   * @param event the Event that needs to be processed
   * 
   */
  public void handleEvent(Event event) {
//    PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
//    PdfDocument pdfDoc = docEvent.getDocument();
//    PdfPage page = docEvent.getPage();
//    Rectangle pageSize = page.getPageSize();
//
//    PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);
//
//    Rectangle rectangle = new Rectangle(18, 36, pageSize.getWidth() - 35, 30);
//  //  Canvas canvas = ReportStyleUtils.getCanvas(pdfCanvas, pdfDoc, rectangle);
//    Canvas canvas = new Canvas(pdfCanvas, rectangle);
// //   canvas.setFontProvider(FontFactory.getFontProvider());
//    // Default font
//    canvas.setProperty(Property.FONT, Font.BOLD);
 //   Table table = new Table(UnitValue.createPercentArray(new float[] {33.33f, 33.33f, 33.33f}));
//    table.setWidth(100).setMarginTop(18);
//
//    canvas.add(table);
//    canvas.close();
	  PdfDocumentEvent docEvent = (PdfDocumentEvent) event;
	    PdfDocument pdfDoc = docEvent.getDocument();
	    PdfPage page = docEvent.getPage();
	    Rectangle pageSize = page.getPageSize();

	    PdfCanvas pdfCanvas = new PdfCanvas(page.newContentStreamBefore(), page.getResources(), pdfDoc);

	    Rectangle rectangle = new Rectangle(18, 0, pageSize.getWidth() - 35, 30);
	    Canvas canvas = new Canvas(pdfCanvas, rectangle);

	    Table table = new Table(UnitValue.createPercentArray(new float[] {50.00f, 50.00f}));
	    table.setWidth(550);

	    SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM, yyyy");
	    dateFormat.setTimeZone(TimeZone.getTimeZone("PST"));
	   Paragraph p  = new Paragraph("Generated as of this" + dateFormat.format(new Date()));
	       
	    table.addCell(p);

//	    Cell rightCell = BeanUtils.only().getCellFactory()
//	        .create(getPageNumberDetails(pdfDoc, page), ReportCellStyles.TABLE_CELL_WITHOUT_BORDER)
//	        .setTextAlignment(TextAlignment.RIGHT).setFontSize(ReportsConfig.FONT_SIZE_FOOTER);
//	    table.addCell(rightCell);
	    Paragraph rightCell = new Paragraph(getPageNumberDetails(pdfDoc, page));
	    rightCell.setTextAlignment(TextAlignment.RIGHT);
	    
	    table.addCell(rightCell);
	    table.setBorder(null);

	    canvas.add(table);
	  }

	  private String getPageNumberDetails(PdfDocument pdfDoc, PdfPage page) {
	    return "Page " + pdfDoc.getPageNumber(page) + "of " + pdfDoc.getNumberOfPages();
}
}
