import Others.Material;
import Others.OperatingUnit;
import lienzoarbol.Controlador;
import lienzoarbol.Lienzo;
import logica.Arbol;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class structure20241Test {

    private static Map<String, Material> materials = new HashMap<>();
    private static Map<String, OperatingUnit> operatingUnits = new HashMap<>();

    public static void main(String[] args) {
        File file = chooseFile();
        if (file != null) {
            readFile(file);
            printDetails();
            Arbol<String> arbol = construirArbol();
            mostrarArbolEnLienzo(arbol);
        } else {
            System.out.println("No file selected.");
        }
    }

    private static File chooseFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        }
        return null;
    }

    private static void readFile(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String section = "";

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                if (line.startsWith("materials:")) {
                    section = "materials";
                } else if (line.startsWith("operating_units:")) {
                    section = "operating_units";
                } else if (line.startsWith("material_to_operating_unit_flow_rates:")) {
                    section = "flow_rates";
                } else {
                    switch (section) {
                        case "materials":
                            parseMaterial(line);
                            break;
                        case "operating_units":
                            parseOperatingUnit(line);
                            break;
                        case "flow_rates":
                            parseFlowRate(line);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void parseMaterial(String line) {
        String[] parts = line.split(":");
        String name = parts[0].trim();
        String[] typeInfo = parts[1].trim().split(",");
        String type = typeInfo[0].trim();
        int flowRateLowerBound = typeInfo.length > 1 ? Integer.parseInt(typeInfo[1].split("=")[1].trim()) : 0;

        Material material = flowRateLowerBound > 0 ? new Material(name, type, flowRateLowerBound) : new Material(name, type);
        materials.put(name, material);
    }

    private static void parseOperatingUnit(String line) {
        String[] parts = line.split(":");
        String name = parts[0].trim();
        String[] unitInfo = parts[1].trim().split(",");
        int capacityUpperBound = Integer.parseInt(unitInfo[0].split("=")[1].trim());
        int fixCost = Integer.parseInt(unitInfo[1].split("=")[1].trim());
        int proportionalCost = Integer.parseInt(unitInfo[2].split("=")[1].trim());

        OperatingUnit unit = new OperatingUnit(name, capacityUpperBound, fixCost, proportionalCost);
        operatingUnits.put(name, unit);
    }

    private static void parseFlowRate(String line) {
        String[] parts = line.split(":");
        String unitName = parts[0].trim();
        String[] flowInfo = parts[1].trim().split("=>");
        String inputMaterial = flowInfo[0].trim();
        String outputMaterial = flowInfo[1].trim();

        OperatingUnit unit = operatingUnits.get(unitName);
        Material input = materials.get(inputMaterial);
        Material output = materials.get(outputMaterial);

        if (unit != null && input != null && output != null) {
            unit.addMaterialFlowRate(input, output);
        }
    }

    private static void printDetails() {
        operatingUnits.forEach((name, unit) -> {
            System.out.println(unit);
            unit.getMaterialFlowRates().forEach((input, output) -> 
                System.out.println("   " + input.getName() + " => " + output.getName()));
        });
    }

    private static Arbol<String> construirArbol() {
        Arbol<String> arbol = new Arbol<>();
        for (String material : materials.keySet()) {
            arbol.insertar(material);
        }
        for (String unit : operatingUnits.keySet()) {
            arbol.insertar(unit);
        }
        return arbol;
    }

    private static void mostrarArbolEnLienzo(Arbol<String> arbol) {
        Lienzo lienzo = new Lienzo();
        Controlador controlador = new Controlador(lienzo, arbol);
        controlador.iniciar();

        JFrame ventana = new JFrame();
        ventana.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Árbol Binario de Unidades Operativas", JLabel.CENTER);
        panel.add(titulo, BorderLayout.NORTH);
        panel.add(lienzo, BorderLayout.CENTER);

        ventana.getContentPane().add(panel);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(800, 600);
        ventana.setVisible(true);
    }
}
