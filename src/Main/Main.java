package Main;

import Parsers.DOMParser;
import Parsers.SAXParserEx;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;


public class Main {


    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        DOMParser.parse();
        SAXParserEx.parse();
    }
}
