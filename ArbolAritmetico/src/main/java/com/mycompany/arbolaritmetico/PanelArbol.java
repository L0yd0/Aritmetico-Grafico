/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.arbolaritmetico;

/**
 *
 * @author Daniel
 */

/*
Marco Daniel Cruz Ixta ID: 00565494
Ricardo Jafet Vargas ID: 00515677
Ingenieria en Tecnologias de la Informacion y Negocios Digitales 
*/


import javax.swing.*;
import java.awt.*;

public class PanelArbol extends JPanel{
    private Nodo raiz;
     
    // Constructor
    public PanelArbol(){
        setBackground(Color.WHITE);
        this.raiz = null;
    }
    
    public void setRaiz(Nodo raiz){
        this.raiz = raiz;
    }
    
    public Nodo getRaiz(){
        return this.raiz;
    }
    
    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        if(raiz != null){
            dibujarNodo(g2d, raiz, getWidth()/2, 30, getWidth()/4);
        }
        
    }
    
    void dibujarNodo(Graphics2D g, Nodo nodo, int x, int y, int separacion){
        if(nodo == null) return;
        
        // DIBUJA LAS LÍNEAS PRIMERO (para que queden detrás de los nodos)
        if(nodo.izquierdo != null){
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(2));
            g.drawLine(x, y, x - separacion, y + 60);
        }
        if(nodo.derecho != null){
            g.setColor(Color.BLACK);
            g.setStroke(new BasicStroke(2));
            g.drawLine(x, y, x + separacion, y + 60); // CORREGIDO: x + separacion
        }
        
        // DIBUJA EL NODO CON SU VALOR
        // Si es operador, usa un color, si es número usa otro
        if(esOperador(nodo.valor)){
            g.setColor(new Color(255, 100, 100)); // Rojo para operadores
        } else {
            g.setColor(new Color(100, 200, 255)); // Azul para números
        }
        g.fillOval(x - 20, y - 20, 40, 40);
        
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(2));
        g.drawOval(x - 20, y - 20, 40, 40);
        
        // DIBUJA EL TEXTO CENTRADO
        g.setFont(new Font("Arial", Font.BOLD, 16));
        FontMetrics fm = g.getFontMetrics();
        int textWidth = fm.stringWidth(nodo.valor);
        int textHeight = fm.getAscent();
        g.drawString(nodo.valor, x - textWidth/2, y + textHeight/4);
        
        // DIBUJA LOS HIJOS RECURSIVAMENTE
        if(nodo.izquierdo != null){
            dibujarNodo(g, nodo.izquierdo, x - separacion, y + 60, separacion/2);
        }
        if(nodo.derecho != null){
            dibujarNodo(g, nodo.derecho, x + separacion, y + 60, separacion/2); // CORREGIDO: x + separacion
        }
    }
    
    private boolean esOperador(String valor){
        return valor.equals("+") || valor.equals("-") || valor.equals("*") || valor.equals("/");
    }
}

   
