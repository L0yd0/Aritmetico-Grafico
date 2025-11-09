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

class Nodo {
    String valor;
    Nodo izquierdo, derecho;

    Nodo(String valor) {
        this.valor = valor;
        izquierdo = derecho = null;
    }
}
