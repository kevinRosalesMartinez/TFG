package com.gestionclubdeportivo.entidad;

import java.awt.Color;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class EquipoPDFExporter {
	private List<Equipo> ListaEquipos;

	public EquipoPDFExporter(List<Equipo> listaEquipos) {
		super();
		ListaEquipos = listaEquipos;
	}

	private void writeTableHeader(PdfPTable table) {
		PdfPCell cell = new PdfPCell();
		cell.setBackgroundColor(Color.BLUE);
		cell.setPadding(5);

		Font font = FontFactory.getFont(FontFactory.HELVETICA);
		font.setColor(Color.WHITE);

		cell.setPhrase(new Phrase("Nombre", font));
		table.addCell(cell);

		cell.setPhrase(new Phrase("Categoria", font));
		table.addCell(cell);

	}

	private void writeTableData(PdfPTable table) {
		for (Equipo equipo : ListaEquipos) {
			table.addCell(equipo.getNombre());
			table.addCell(equipo.getCategoria().getNombre());
		}
	}

	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize.A4);

		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();

		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setColor(Color.BLUE);
		font.setSize(18);

		Paragraph title = new Paragraph("Lista de Equipos");
		title.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(title);

		PdfPTable table = new PdfPTable(2);
		table.setWidthPercentage(100);
		table.setSpacingBefore(15);
		table.setWidths(new float[] { 3.5f, 1.5f});

		writeTableHeader(table);
		writeTableData(table);

		document.add(table);

		document.close();

	}

}
