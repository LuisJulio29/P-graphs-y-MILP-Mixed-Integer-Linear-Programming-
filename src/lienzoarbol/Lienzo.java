/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lienzoarbol;

import java.awt.Graphics;
import javax.swing.JPanel;
import logica.Arbol;
import logica.Nodo;

/*
*         
*
*
*/
public class Lienzo extends JPanel {
    private Arbol objArbol;
    public static final int DIAMETRO = 30;
    public static final int RADIO = DIAMETRO / 2;
    public static final int ANCHO = 40;

    public void setObjArbol(Arbol objArbol) {
        this.objArbol = objArbol;
        repaint();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); //To change body of generated methods, choose Tools | Templates.
        pintar(g, getWidth() / 2, 50, objArbol.getRaiz());
    }
    
     private void pintar(Graphics g, int x, int y, Nodo n) {
        if (n != null) {
        int EXTRA = n.nodosCompletos(n) * (ANCHO / 2);
        
        // Dibuja el nodo actual
        g.drawOval(x, y, DIAMETRO, DIAMETRO);
        g.drawString(n.getElemento().toString(), x + 12, y + 20);

        // Dibuja el nodo izquierdo
        if (n.getIzquierda() != null) {
            int nuevaXIzquierda = x - ANCHO / 2 - EXTRA;
            int nuevaYIzquierda = y + ANCHO;
            g.drawLine(x + RADIO, y + RADIO, nuevaXIzquierda + RADIO, nuevaYIzquierda + RADIO);
            pintar(g, nuevaXIzquierda, nuevaYIzquierda, n.getIzquierda());
        }

        // Dibuja el nodo derecho
        if (n.getDerecha() != null) {
            int nuevaXDerecha = x + ANCHO / 2 + EXTRA;
            int nuevaYDerecha = y + ANCHO;
            g.drawLine(x + RADIO, y + RADIO, nuevaXDerecha + RADIO, nuevaYDerecha + RADIO);
            pintar(g, nuevaXDerecha, nuevaYDerecha, n.getDerecha());
        }
    }
}
}