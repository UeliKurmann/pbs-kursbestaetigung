package ch.pbs.benevole.renderer.pdf;

import ch.pbs.benevole.renderer.core.ListElement;
import ch.pbs.benevole.renderer.core.NameValue;
import ch.pbs.benevole.renderer.core.PdfText;
import ch.pbs.benevole.renderer.core.PdfText.Style;
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

import java.awt.*;
import java.net.URL;
import java.util.Arrays;
import java.util.Objects;

public class ElementFactory {

	private static final String NEW_LINE = "\n";

	public static Table createNameValueTable(final NameValue... pairs) throws BadElementException {
		final Table table = new Table(2);
		table.setHorizontalAlignment(HorizontalAlignment.CENTER);
		table.setWidth(75);
		table.setWidths(new float[] { 1, 3 });
		table.setBorderWidth(0);
		table.setPadding(0);
		table.setSpacing(1);
		for (final NameValue pair : pairs) {
			createTableRow(table, pair.getName(), pair.getValue());
		}

		return table;
	}

	private static void createTableRow(final Table table, final String name, final String value) {

		final Cell cell = new Cell(new Paragraph(name, createAbsatzFont()));
		cell.setUseBorderPadding(false);
		cell.setLeading(14);
		cell.setBorder(0);
		cell.setWidth(50);
		table.addCell(cell);

		final Cell cell2 = new Cell(new Paragraph(cleanupMultilineValues(value), createAbsatzFont()));
		cell2.setLeading(14);
		cell2.setBorder(0);
		cell2.setWidth(120);
		table.addCell(cell2);
	}

	private static String cleanupMultilineValues(final String value) {
		final StringBuilder sb = new StringBuilder();
		if (value != null) {
			Arrays.stream(value.split(NEW_LINE)).forEach(line -> sb.append(line.trim()).append(NEW_LINE));
		}
		return sb.toString();
	}

	public static List createList(final ListElement... elements) {
		final List list = new List(false, 0);
		list.setListSymbol("");
		for (final ListElement element : elements) {
			final ListItem item = new ListItem(toParagraph(element.getValue()));
			item.setSpacingAfter(2);
			list.add(item);
			if (element.hasSubListElements()) {
				final List subList = new List(false, 10);
				subList.setListSymbol("•");
				subList.setIndentationLeft(15);
				for (final ListElement subElement : element.getSubListElements()) {
					final ListItem o = new ListItem(toParagraph(subElement.getValue()));
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

	public static Image createLogo(final PdfWriter pdfWriter) throws Exception {
		var pbsLogo = ElementFactory.class.getClassLoader().getResourceAsStream("pbsassets/pbslogo.pdf");
		Objects.requireNonNull(pbsLogo, "logo not found.");
		final PdfReader reader = new PdfReader(pbsLogo);
		final PdfImportedPage importedPage = pdfWriter.getImportedPage(reader, 1);

		final Image image = Image.getInstance(importedPage);
		image.setAlignment(Image.LEFT);

		image.setAbsolutePosition(30, 30);

		return image;
	}

	public static Image createSignature(final PdfText.Alignment alignment) throws Exception {

		final URL resource = ElementFactory.class.getClassLoader().getResource("pbsassets/signature.jpg");
		Objects.requireNonNull(resource, "signature not found.");
		final Image image = Image.getInstance(resource);
		if (alignment == PdfText.Alignment.left) {
			image.setAlignment(Image.LEFT);
		} else {
			image.setAlignment(Image.RIGHT);
		}
		image.scaleToFit(130, 100);
		return image;
	}

	public static Paragraph createH1Paragraph(final String title) {
		final Paragraph p = new Paragraph(title, createH1Font());

		p.setSpacingAfter(15);
		return p;
	}

	public static Paragraph createH2Paragraph(final String title) {
		final Paragraph p = new Paragraph(title, createH2Font());
		p.setSpacingAfter(10);
		p.setSpacingBefore(10);
		return p;
	}

	public static Font createH1Font() {
		return FontFactory.getFont("Arial", 36, Font.BOLD, Color.decode("#632949"));
	}

	public static Font createH2Font() {
		return FontFactory.getFont("Arial", 14, Font.BOLD, Color.decode("#8b426b"));
	}

	public static Font createAbsatzBoldFont() {
		return FontFactory.getFont("Arial", 10, Font.BOLD, Color.BLACK);
	}

	public static Font createAbsatzFont() {
		return FontFactory.getFont("Arial", 10, Font.NORMAL, Color.BLACK);
	}

	public static Paragraph toParagraph(final java.util.List<PdfText> text) {
		final Paragraph p = new Paragraph();
		p.setLeading(14);
		for (int i = 0; i < text.size(); i++) {
			final PdfText t = text.get(i);
			if (t.getStyle() == Style.bold) {
				p.add(new Phrase(t.getValue(), createAbsatzBoldFont()));
			} else {
				p.add(new Phrase(t.getValue(), createAbsatzFont()));
			}
			if (t.getAlignment() == PdfText.Alignment.left) {
				p.setAlignment(Paragraph.ALIGN_LEFT);
			} else {
				p.setAlignment(Paragraph.ALIGN_RIGHT);
			}

			if (t.isNewline()) {
				p.add(new Phrase("\n"));
			} else if (text.size() - 1 != i) {
				p.add(new Phrase(" "));
			}

		}
		return p;
	}

}
