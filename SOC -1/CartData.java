import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.io.FileWriter;

public class CartData {
    public static void main(String[] args) {
        try {
            File inputFile = new File("cart_data.xml"); // Replace with your XML file path
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

            NodeList nodeList = doc.getElementsByTagName("food");

            String[][] tableData = new String[nodeList.getLength()][4]; // Assuming four columns

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    String name = element.getElementsByTagName("name").item(0).getTextContent();
                    String price = element.getElementsByTagName("price").item(0).getTextContent();
                    String description = element.getElementsByTagName("description").item(0).getTextContent();
                    String calories = element.getElementsByTagName("calories").item(0).getTextContent();
                    tableData[i][0] = name;
                    tableData[i][1] = price;
                    tableData[i][2] = description;
                    tableData[i][3] = calories;
                }
            }

            generateHTMLTable(tableData, "table.html");
            System.out.println("HTML table generated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateHTMLTable(String[][] data, String outputFilePath) {
        try {
            FileWriter writer = new FileWriter(outputFilePath);

            writer.write("<html><body>");
            writer.write("<table border=\"1\">");

            // Generate table header
            writer.write("<tr>");
            writer.write("<th>Name</th>");
            writer.write("<th>brand</th>");
            writer.write("<th>price</th>");
            writer.write("</tr>");

            // Generate table rows
            for (String[] row : data) {
                writer.write("<tr>");
                for (String cell : row) {
                    writer.write("<td>" + cell + "</td>");
                }
                writer.write("</tr>");
            }

            writer.write("</table>");
            writer.write("</body></html>");

            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
