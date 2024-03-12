package com.example.demo.Utility;
import com.example.demo.model.Book;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvUtil {

    public static List<Book> readBooksFromCSV(String filePath) {
        List<Book> books = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                try {
                    // Assuming the CSV format is: BookName,Author,PublicationYear
                    String bookName = line[0].trim();
                    String author = line[1].trim();
                    int publicationYear = Integer.parseInt(line[2].trim());

                    Book book = new Book(bookName, author, publicationYear);
                    books.add(book);
                } catch (NumberFormatException e) {
                    // Handle invalid number format if needed
                    e.printStackTrace();
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            // Handle exceptions accordingly
        }

        return books;
    }

    public static void addBookToCSV(String filePath, Book book) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath, true))) {
            // Assuming the CSV format is: BookName,Author,PublicationYear
            String[] record = {book.getBookName(), book.getAuthor(), String.valueOf(book.getPublicationYear())};
            writer.writeNext(record);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions accordingly
        }
    }

    public static void deleteBookFromCSV(String filePath, String bookName) {
        List<String[]> newRecords = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] line;
            while ((line = reader.readNext()) != null) {
                // Assuming the CSV format is: BookName,Author,PublicationYear
                String existingBookName = line[0].trim().toLowerCase();

                if (!existingBookName.equalsIgnoreCase(bookName.trim().toLowerCase())) {
                    newRecords.add(line);
                }
            }
        } catch (IOException | CsvValidationException e) {
            e.printStackTrace();
            // Handle exceptions accordingly
        }

        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(newRecords);
        } catch (IOException e) {
            e.printStackTrace();
            // Handle exceptions accordingly
        }
    }
}
