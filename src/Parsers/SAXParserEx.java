package Parsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


public class SAXParserEx extends DefaultHandler {
    public static File XMLFile = new File("src/Files/XMLFiles/books.xml");
    public String thisElement = "";
    public String curBook = "";
    public static ArrayList<String> authors = new ArrayList<>();
    public static String author = "Владимир Набоков";
    public static int numOfNeedBook = 0;
    public static int numOfBooks = 0;
    public static ArrayList<String> allBooks = new ArrayList<>();

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equals("author") && (!new String(ch, start, length).contains("  "))) {

            authors.add(new String(ch, start, length));
        }


        if (thisElement.equals("title") && (!new String(ch, start, length).contains("  "))) {
            curBook = new String(ch, start, length);
            allBooks.add(curBook);

        }
        if (thisElement.equals("author") && new String(ch, start, length).equals(author)) {
            try (FileWriter fileWriter = new FileWriter("src/Files/SAX/BookOfWrittenAuthor")) {
                fileWriter.write(curBook);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (thisElement.equals("title") && (!new String(ch, start, length).contains("  "))) {
            numOfNeedBook++;
            if (numOfNeedBook == 5) {
                try (FileWriter fileWriter = new FileWriter("src/Files/SAX/NameOfBookByNum")) {
                    fileWriter.write(new String(ch, start, length));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


    }

    @Override
    public void startDocument() throws SAXException {
        super.startDocument();

    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        thisElement = qName;
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        super.endElement(uri, localName, qName);
    }

    public static void parse() throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();
        SAXParserEx saxParserEx = new SAXParserEx();
        long beginTime = System.nanoTime();
        parser.parse(XMLFile, saxParserEx);
        long endTime = System.nanoTime();
        System.out.println("Sax parser time: " + (endTime - beginTime));
        try (FileWriter fileWriter = new FileWriter("src/Files/SAX/Authors.txt")) {
            for (String author : authors) {
                fileWriter.write(author + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter fileWriter = new FileWriter("src/Files/SAX/NumOfBooks.txt")) {
            fileWriter.write(numOfNeedBook + "");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileWriter fileWriter = new FileWriter("src/Files/SAX/AllBooks.txt")) {
            for (int i = 0; i < allBooks.size(); i++) {
                fileWriter.write(allBooks.get(i) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
