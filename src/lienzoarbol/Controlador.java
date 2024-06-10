/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lienzoarbol;

import logica.Arbol;

/*
*        
*
*/
public class Controlador {
    private final Lienzo objLienzo; //VISTA
    private final Arbol objArbol; //MODELO

    public Controlador(Lienzo objLienzo, Arbol objArbol) {
        this.objLienzo = objLienzo;
        this.objArbol = objArbol;
    }
    
    public void iniciar() {
        objLienzo.setObjArbol(objArbol);
    }
}
