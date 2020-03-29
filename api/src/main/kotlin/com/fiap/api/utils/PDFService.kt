package com.fiap.api.utils

import com.fiap.api.domain.Transaction
import com.fiap.api.domain.User
import com.itextpdf.text.Document
import com.itextpdf.text.DocumentException
import com.itextpdf.text.Element
import com.itextpdf.text.Font
import com.itextpdf.text.Phrase
import com.itextpdf.text.pdf.PdfPCell
import com.itextpdf.text.pdf.PdfPTable
import com.itextpdf.text.pdf.PdfWriter
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import org.slf4j.LoggerFactory

object PDFService {
    private val logger = LoggerFactory.getLogger(PDFService::class.java)
    fun report(user: User, transactions: List<Transaction>): ByteArrayInputStream {
        val document = Document()
        val out = ByteArrayOutputStream()
        try {
            val headFont: Font = com.itextpdf.text.FontFactory.getFont(com.itextpdf.text.FontFactory.HELVETICA_BOLD)
            var hcell: PdfPCell

            val tableHeader = PdfPTable(1)
            tableHeader.setWidthPercentage(90F)
            tableHeader.setWidths(intArrayOf(100))

            hcell = PdfPCell(Phrase("FIAP Cartões", headFont))
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER)
            tableHeader.addCell(hcell)

            val tableHeader2 = PdfPTable(2)
            tableHeader2.setWidthPercentage(90F)
            tableHeader2.setWidths(intArrayOf(50, 50))

            hcell = PdfPCell(Phrase(user.name, headFont))
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER)
            tableHeader2.addCell(hcell)

            hcell = PdfPCell(Phrase(user.doc, headFont))
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER)
            tableHeader2.addCell(hcell)

            val table = PdfPTable(3)
            table.setWidthPercentage(90F)
            table.setWidths(intArrayOf(3, 3, 3))

            hcell = PdfPCell(Phrase("Description", headFont))
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER)
            table.addCell(hcell)

            hcell = PdfPCell(Phrase("Date", headFont))
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER)
            table.addCell(hcell)

            hcell = PdfPCell(Phrase("Amount", headFont))
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER)
            table.addCell(hcell)

            for (transaction in transactions) {
                var cell: PdfPCell
                cell = PdfPCell(Phrase(transaction.description))
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE)
                cell.setHorizontalAlignment(Element.ALIGN_CENTER)
                table.addCell(cell)

                cell = PdfPCell(Phrase(transaction.dateTransaction.toString()))
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE)
                cell.setHorizontalAlignment(Element.ALIGN_CENTER)
                table.addCell(cell)

                cell = PdfPCell(Phrase(transaction.amount.toString()))
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE)
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT)
                table.addCell(cell)
            }
            PdfWriter.getInstance(document, out)
            document.open()
            document.add(tableHeader)
            document.add(tableHeader2)
            document.add(table)
            document.close()
        } catch (ex: DocumentException) {
            logger.error("Error occurred: {0}", ex)
        }
        return ByteArrayInputStream(out.toByteArray())
    }
}
