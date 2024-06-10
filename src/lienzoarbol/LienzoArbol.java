/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lienzoarbol;
import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Iterator;
import logica.Arbol;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import javax.swing.JFrame;
import logica.Arbol;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author 
 */
public class LienzoArbol {
   public static <T> void mostrarElementosInsertados(Arbol<T> arbol) {
        List<T> elementos = arbol.getElementosInsertados();
        System.out.println("Elementos insertados:");
        for (T elemento : elementos) {
            System.out.println(elemento);
        }
    } 
 public static List<String> generarCombinaciones(int n) {
        List<String> resultado = new ArrayList<>();
        int numeroDeCombinaciones = (int) Math.pow(2, n);

        for (int i = 0; i < numeroDeCombinaciones; i++) {
            StringBuilder combinacion = new StringBuilder();
            for (int j = n - 1; j >= 0; j--) {
                // Verificar si el j-ésimo bit en i es 1 o 0
                combinacion.append((i & (1 << j)) != 0 ? '1' : '0');
            }
            resultado.add(combinacion.toString());
        }

        return resultado;
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
      
      try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            String linea;
            int lineaNumero = 1;

            while ((linea = br.readLine()) != null) {
                System.out.println("----------------------------------------");
                System.out.println("Procesando línea " + lineaNumero + ": " + linea);
                lineaNumero++;

                // Crear un nuevo árbol para cada línea
                Arbol<String> objArbol = new Arbol<>();
                objArbol.insertar("R");

                // Dividir la línea en unidades operativas y insertarlas en el árbol
                String[] unidadesOperativas = linea.split(",");
                for (String unidad : unidadesOperativas) {
                    objArbol.insertar(unidad.trim());
                }

                // Generar combinaciones
                int n = unidadesOperativas.length;
                List<String> combinaciones = generarCombinaciones(n);

                System.out.println("\nN° Unidades Operativas: " + n);
                System.out.println("Estados:\n");
                int c = 0;
                for (String combinacion : combinaciones) {
                    String[] numeros = combinacion.split("");
                    String resultado = "{" + String.join(",", numeros) + "}";
                    System.out.println(resultado);
                    c++;
                }
                System.out.println("\nEstados Totales: " + c);
                System.out.println("\n---------------------------------------\n");
                System.out.println("Linea ejecutada: "+ linea +"\n" );
                // Mostrar el árbol gráfico (opcional, puede comentarse si no se necesita)
                Lienzo objLienzo = new Lienzo();
                Controlador objControlador = new Controlador(objLienzo, objArbol);
                objControlador.iniciar();
                
                JFrame ventana = new JFrame();
                ventana.setLayout(new BorderLayout());
                
                JPanel panel = new JPanel(new BorderLayout());
                JLabel titulo = new JLabel("Unidades Operativas: " + linea, JLabel.CENTER);
                panel.add(titulo, BorderLayout.NORTH);
                panel.add(objLienzo, BorderLayout.NORTH);
                
                
                ventana.getContentPane().add(objLienzo);
                ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                ventana.setSize(800, 600);
                ventana.setVisible(true);

              
                try {
                    Thread.sleep(10000); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                
                ventana.setVisible(false);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }
    
}
    