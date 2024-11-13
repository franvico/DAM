package logica;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class GestionPedidos {
    private static final String ARCHIVO_XML = "pedidos.xml";

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Insertar pedido");
            System.out.println("2. Eliminar pedidos por NIF");
            System.out.println("3. Mostrar pedidos por NIF");
            System.out.println("4. Mostrar gasto total por NIF");
            System.out.println("5. Salir");
            System.out.print("Elija una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer de entrada

            switch (opcion) {
                case 1:
                    insertarPedido(scanner);
                    break;
                case 2:
                    System.out.print("Ingrese el NIF del cliente: ");
                    String nifEliminar = scanner.nextLine();
                    eliminarPedidosPorNIF(nifEliminar);
                    break;
                case 3:
                    System.out.print("Ingrese el NIF del cliente: ");
                    String nifMostrar = scanner.nextLine();
                    mostrarPedidosPorNIF(nifMostrar);
                    break;
                case 4:
                    System.out.print("Ingrese el NIF del cliente: ");
                    String nifGasto = scanner.nextLine();
                    mostrarGastoPorNIF(nifGasto);
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    return;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    // Función para insertar un pedido
    private static void insertarPedido(Scanner scanner) throws Exception {
        Document doc = obtenerDocumentoXML();

        Node rootElement = doc.getFirstChild();

        // Crear nuevo pedido
        Element pedido = doc.createElement("pedido");

        // Información del cliente
        Element cliente = doc.createElement("cliente");
        Element nombre = doc.createElement("nombre");
        System.out.print("Nombre del cliente: ");
        
        nombre.setTextContent(scanner.nextLine());
        
        //LO MISMO DE ARRIBA:
        Text textoNombre = doc.createTextNode(scanner.nextLine());
        nombre.appendChild(textoNombre);

        Element nif = doc.createElement("nif");
        System.out.print("NIF del cliente: ");
        nif.setTextContent(scanner.nextLine());

        cliente.appendChild(nombre);
        cliente.appendChild(nif);

        // Información del producto
        Element producto = doc.createElement("producto");
        Element descripcion = doc.createElement("descripcion");
        System.out.print("Descripción del producto: ");
        descripcion.setTextContent(scanner.nextLine());

        Element precio = doc.createElement("precio");
        System.out.print("Precio del producto: ");
        precio.setTextContent(scanner.nextLine());

        Element cantidad = doc.createElement("cantidad");
        System.out.print("Cantidad: ");
        cantidad.setTextContent(scanner.nextLine());

        producto.appendChild(descripcion);
        producto.appendChild(precio);
        producto.appendChild(cantidad);

        // Agregar cliente y producto al pedido
        pedido.appendChild(cliente);
        pedido.appendChild(producto);

        // Agregar el nuevo pedido al root
        rootElement.appendChild(pedido);

        // Guardar los cambios en el archivo
        guardarDocumentoXML(doc);

        System.out.println("Pedido insertado con éxito.");
    }

    // Función para eliminar pedidos de un cliente dado su NIF
    private static void eliminarPedidosPorNIF(String nifCliente) throws Exception {
        Document doc = obtenerDocumentoXML();

        NodeList pedidos = doc.getElementsByTagName("pedido");
        
        //NodeList pedidos = doc.getFirstChild().getChildNodes();
        
        List<Node> pedidosEliminar = new LinkedList<>();

        for (int i = 0; i < pedidos.getLength(); i++) {
            Node pedido = pedidos.item(i);
            Element cliente = (Element) ((Element) pedido).getElementsByTagName("cliente").item(0);
            String nif = cliente.getElementsByTagName("nif").item(0).getTextContent();
            //String nif = cliente.getElementsByTagName("nif").item(0).getFirstChild().getNodeValue();
            if (nif.equals(nifCliente)) {
                pedidosEliminar.add(pedido);
               
            }
        }
        
        for(Node pedido : pedidosEliminar)
        	doc.getFirstChild().removeChild(pedido);
        
        guardarDocumentoXML(doc);
        
        System.out.println("Pedidos eliminados con éxito.");
        
    }

    // Función para mostrar pedidos de un cliente dado su NIF
    private static void mostrarPedidosPorNIF(String nifCliente) throws Exception {
        Document doc = obtenerDocumentoXML();

        NodeList pedidos = doc.getElementsByTagName("pedido");
        boolean encontrado = false;

        for (int i = 0; i < pedidos.getLength(); i++) {
            Node pedido = pedidos.item(i);
            Element cliente = (Element) ((Element) pedido).getElementsByTagName("cliente").item(0);
            String nif = cliente.getElementsByTagName("nif").item(0).getTextContent();

            if (nif.equals(nifCliente)) {
                encontrado = true;
                Element producto = (Element) ((Element) pedido).getElementsByTagName("producto").item(0);
                System.out.println("Descripción: " + producto.getElementsByTagName("descripcion").item(0).getTextContent());
                System.out.println("Precio: " + producto.getElementsByTagName("precio").item(0).getTextContent());
                System.out.println("Cantidad: " + producto.getElementsByTagName("cantidad").item(0).getTextContent());
                System.out.println("----------------------------");
            }
        }

        if (!encontrado) {
            System.out.println("No se encontraron pedidos para el NIF proporcionado.");
        }
    }

    // Función para mostrar el gasto total de un cliente dado su NIF
    private static void mostrarGastoPorNIF(String nifCliente) throws Exception {
        Document doc = obtenerDocumentoXML();

        NodeList pedidos = doc.getElementsByTagName("pedido");
        double gastoTotal = 0;
        boolean encontrado = false;

        for (int i = 0; i < pedidos.getLength(); i++) {
            Node pedido = pedidos.item(i);
            Element cliente = (Element) ((Element) pedido).getElementsByTagName("cliente").item(0);
            String nif = cliente.getElementsByTagName("nif").item(0).getTextContent();

            if (nif.equals(nifCliente)) {
                encontrado = true;
                Element producto = (Element) ((Element) pedido).getElementsByTagName("producto").item(0);
                double precio = Double.parseDouble(producto.getElementsByTagName("precio").item(0).getTextContent());
                int cantidad = Integer.parseInt(producto.getElementsByTagName("cantidad").item(0).getTextContent());
                gastoTotal += precio * cantidad;
            }
        }

        if (encontrado) {
            System.out.println("El gasto total del cliente con NIF " + nifCliente + " es: " + gastoTotal);
        } else {
            System.out.println("No se encontraron pedidos para el NIF proporcionado.");
        }
    }

    // Obtener el documento XML
    private static Document obtenerDocumentoXML() throws ParserConfigurationException, SAXException, IOException {
        File archivo = new File(ARCHIVO_XML);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        if (archivo.exists()) {
            return builder.parse(archivo);
        } else {
            // Si no existe, crear un nuevo archivo XML
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("pedidos");
            doc.appendChild(rootElement);
            return doc;
        }
    }

    // Guardar los cambios en el archivo XML
    private static void guardarDocumentoXML(Document doc) throws TransformerException {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        StreamResult result = new StreamResult(new File(ARCHIVO_XML));
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(source, result);
    }
}
