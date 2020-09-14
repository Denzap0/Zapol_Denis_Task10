package Parsers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class DOMParser {

    public static File XMLFile = new File("src/Files/XMLFiles/books.xml");
    public static final String author = "Энтони Берджесс";

    public static void parse() throws ParserConfigurationException, IOException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = factory.newDocumentBuilder();
        long beginTime = System.nanoTime();
        Document document = documentBuilder.parse(XMLFile);
        long endTime = System.nanoTime();
        System.out.println("DOM parser time: " + (endTime - beginTime));
        writeAuthors(document);
        writeBookOfAuthor(document);
        numOfBooks(document);
        nameOfBookByNum(document, 4);
        writeAllBooks(document);
    }

    public static void writeAuthors(Document document) {
        NodeList books = document.getElementsByTagName("book");
        try (FileWriter fileWriter = new FileWriter("src/Files/DOM/Authors.txt")) {
            for (int i = 0; i < books.getLength(); i++) {
                Node node = books.item(i);
                Element element = (Element) node;

                fileWriter.write(element.getElementsByTagName("author").item(0).getTextContent() + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeBookOfAuthor(Document document) {
        NodeList books = document.getElementsByTagName("book");

        try (FileWriter fileWriter = new FileWriter("src/Files/DOM/BookOfWrittenAuthor")) {
            for (int i = 0; i < books.getLength(); i++) {
                Node node = books.item(i);
                Element element = (Element) node;
                if (element.getElementsByTagName("author").item(0).getTextContent().equals(author)) {
                    fileWriter.write(element.getElementsByTagName("title").item(0).getTextContent() + "\n");
                }


            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void numOfBooks(Document document) {
        NodeList books = document.getElementsByTagName("book");
        try (FileWriter fileWriter = new FileWriter("src/Files/DOM/NumOfBooks.txt")) {
            fileWriter.write(books.getLength() + "");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void nameOfBookByNum(Document document, int numOfBook) {
        NodeList books = document.getElementsByTagName("book");

        try (FileWriter fileWriter = new FileWriter("src/Files/DOM/NameOfBookByNum")) {
            Node node = books.item(numOfBook);
            Element element = (Element) node;
            fileWriter.write(element.getElementsByTagName("title").item(0).getTextContent());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeAllBooks(Document document) {
        NodeList books = document.getElementsByTagName("book");

        try (FileWriter fileWriter = new FileWriter("src/Files/DOM/AllBooks.txt")) {
            for (int i = 0; i < books.getLength(); i++) {
                Node node = books.item(i);
                Element element = (Element) node;
                fileWriter.write(element.getElementsByTagName("title").item(0).getTextContent() + "\n");

            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
