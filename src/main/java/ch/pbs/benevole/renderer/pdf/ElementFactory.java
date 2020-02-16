package ch.pbs.benevole.renderer.pdf;

import java.awt.Color;
import java.io.OutputStream;
import java.net.URL;
import java.util.Arrays;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Cell;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.List;
import com.lowagie.text.ListItem;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.alignment.HorizontalAlignment;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfWriter;

import ch.pbs.benevole.renderer.core.ListElement;
import ch.pbs.benevole.renderer.core.NameValue;
import ch.pbs.benevole.renderer.core.PdfText;
import ch.pbs.benevole.renderer.core.PdfText.Alignement;
import ch.pbs.benevole.renderer.core.PdfText.Style;

public class ElementFactory {

	private static final String NEW_LINE = "\n";

	public static Table createNameValueTable(NameValue... pairs) throws BadElementException {
		Table table = new Table(2);
		table.setHorizontalAlignment(HorizontalAlignment.CENTER);
		table.setWidth(75);
		table.setWidths(new float[] { 1, 3 });
		table.setBorderWidth(0);
		table.setPadding(0);
		table.setSpacing(1);
		for (NameValue pair : pairs) {
			createTableRow(table, pair.getName(), pair.getValue());
		}

		return table;
	}

	private static void createTableRow(Table table, String name, String value) {

		Cell cell = new Cell(new Paragraph(name, createAbsatzFont()));
		cell.setUseBorderPadding(false);
		cell.setLeading(14);
		cell.setBorder(0);
		cell.setWidth(50);
		table.addCell(cell);

		Cell cell2 = new Cell(new Paragraph(cleanupMultilineValues(value), createAbsatzFont()));
		cell2.setLeading(14);
		cell2.setBorder(0);
		cell2.setWidth(120);
		table.addCell(cell2);
	}

	private static String cleanupMultilineValues(String value) {
		StringBuilder sb = new StringBuilder();
		if (value != null) {
			Arrays.stream(value.split(NEW_LINE)).forEach(line -> sb.append(line.trim()).append(NEW_LINE));
		}
		return sb.toString();
	}

	public static List createList(ListElement... elements) {
		List list = new List(false, 10);
		for (ListElement element : elements) {
			ListItem item = new ListItem(toParagraph(element.getValue()));
			item.setSpacingAfter(2);
			list.add(item);
			if (element.hasSubListElements()) {
				List subList = new List(false, 10);
				subList.setListSymbol("•");
				subList.setIndentationLeft(15);
				for (ListElement subElement : element.getSubListElements()) {
					ListItem o = new ListItem(toParagraph(subElement.getValue()));
					o.setSpacingAfter(2);
					subList.add(o);
					if (subElement.hasSubListElements()) {
						throw new IllegalStateException("Only two list levels supported.");
					}
				}
				list.add(subList);
			}
		}
		return list;
	}

	public static Image createLogo(OutputStream os, PdfWriter pdfWriter) throws Exception {

		PdfReader reader = new PdfReader(ElementFactory.class.getClassLoader().getResourceAsStream("pbsassets/pbslogo.pdf"));
		PdfImportedPage importedPage = pdfWriter.getImportedPage(reader, 1);

		Image image = Image.getInstance(importedPage);
		image.setAlignment(Image.RIGHT);
		image.scaleToFit(270, 200);
		return image;
	}

	public static Image createSignature(Alignement alignement, OutputStream os, PdfWriter pdfWriter) throws Exception {

		URL resource = ElementFactory.class.getClassLoader().getResource("pbsassets/signature.png");
		Image image = Image.getInstance(resource);
		if(alignement == Alignement.left) {
			image.setAlignment(Image.LEFT);
		}else {
			image.setAlignment(Image.RIGHT);
		}
		image.scaleToFit(130, 100);
		return image;
	}

	public static Paragraph createH1Paragraph(String title) {
		Paragraph p = new Paragraph(title, createH1Font());
		p.setSpacingAfter(15);
		return p;
	}

	public static Paragraph createParagraph(String text) {
		Paragraph p = new Paragraph(text, createAbsatzFont());
		// p.setSpacingBefore(0);
		return p;
	}

	public static Paragraph createH2Paragraph(String title) {
		Paragraph p = new Paragraph(title, createH2Font());
		p.setSpacingAfter(10);
		p.setSpacingBefore(10);
		return p;
	}

	public static Font createH1Font() {
		return FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLD, Color.BLACK);
	}

	public static Font createH2Font() {
		return FontFactory.getFont(FontFactory.HELVETICA, 14, Font.BOLD, Color.BLACK);
	}

	public static Font createAbsatzBoldFont() {
		return FontFactory.getFont(FontFactory.HELVETICA, 10, Font.BOLD, Color.BLACK);
	}

	public static Font createAbsatzFont() {
		return FontFactory.getFont(FontFactory.HELVETICA, 10, Font.NORMAL, Color.BLACK);
	}

	public static Paragraph toParagraph(java.util.List<PdfText> text) {
		Paragraph p = new Paragraph();
		p.setLeading(14);
		for (PdfText t : text) {
			if (t.getStyle() == Style.bold) {
				p.add(new Phrase(t.getValue(), createAbsatzBoldFont()));
			} else {
				p.add(new Phrase(t.getValue(), createAbsatzFont()));
			}
			if(t.getAlignement() == Alignement.left) {
				p.setAlignment(Paragraph.ALIGN_LEFT);
			}else {
				p.setAlignment(Paragraph.ALIGN_RIGHT);
			}
			
			if (t.isNewline()) {
				p.add(new Phrase("\n"));
			} else {
				p.add(new Phrase(" "));
			}

		}
		return p;
	}

}
