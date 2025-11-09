/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.arbolaritmetico;

/**
 *
 * @author Daniel
 */
import java.util.*;

/*
Marco Daniel Cruz Ixta ID: 00565494
Ricardo Jafet Vargas ID: 00515677
Ingenieria en Tecnologias de la Informacion y Negocios Digitales 
*/

/*Descargar el código y realizar lo siguiente:
1. mostrarlo en un entorno grafico utilizando ApaceNetBeans, se evaluará el uso de los componentes graficos.check
2. Describir que es la notación postfija, prefija e infija

R= La notación infija coloca el operador entre los operandos, como A+B,y es la forma tradicional de escribir operaciones.
La notacion prefija ubica el operador antes de los operandos, (+ A B).
La notacion postfija lo coloca después (A B +).
En nuestro codigo, estas notaciones se obtienen con los recorridos InOrden, PreOrden y PostOrden.

3. ¿Por qué se aplica en la solución de operaciones aritméticas?
R= Las notaciones prefija y potfija se aplican porque permiten resolver operaciones sin tener que usar 
paréntsis ni reglas de prioridad. El orden de ejecución queda definido por la posición de los operadores
y operandos, lo que facilita que un programa evalúe las expresiones de manera directa y sin ambiguedad. 

4. Relación o parecido en los recorridos Preorden, Inorden y PostOrden.
R=Los recorridos muestran distintas formas de recorrer un árbol binario.
En preorden se visita primero la raíz (notación prefija).
En inorden la raíz se recorre al centro (notación infija).
En postorden la raíz se visita al final (notación postfija).
Cada uno representa un orden diferente para leer los operadores y operandos de una misma expresión 
*/

public class ArbolAritmetico {

    // Método que verifica si el dato es un operador
    private static boolean esOperador(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/");
    }

    // Método que establece la prioridad de operadores
    private static int prioridad(String op) {
        switch (op) {
            case "+": case "-": return 1;
            case "*": case "/": return 2;
        }
        return -1;
    }

    // Convierte expresión infija a postfija
    public static List<String> infijaAPostfija(String expresion) {
        Stack<String> pila = new Stack<>();
        List<String> salida = new ArrayList<>();
        String[] tokens = expresion.split(" ");

        for (String token : tokens) {
            if (token.matches("\\d+")) {
                salida.add(token);
            } else if (esOperador(token)) {
                while (!pila.isEmpty() && prioridad(pila.peek()) >= prioridad(token)) {
                    salida.add(pila.pop());
                }
                pila.push(token);
            } else if (token.equals("(")) {
                pila.push(token);
            } else if (token.equals(")")) {
                while (!pila.isEmpty() && !pila.peek().equals("(")) {
                    salida.add(pila.pop());
                }
                pila.pop();
            }
        }
        while (!pila.isEmpty()) {
            salida.add(pila.pop());
        }
        return salida;
    }

    // Construye el árbol desde la expresión postfija
    public static Nodo construirArbol(List<String> postfija) {
        Stack<Nodo> pila = new Stack<>();
        for (String token : postfija) {
            Nodo nodo = new Nodo(token);
            if (esOperador(token)) {
                nodo.derecho = pila.pop();
                nodo.izquierdo = pila.pop();
            }
            pila.push(nodo);
        }
        return pila.peek();
    }

    // Evalúa el árbol
    public static int evaluar(Nodo raiz) {
        if (raiz == null) return 0;
        if (!esOperador(raiz.valor)) {
            return Integer.parseInt(raiz.valor);
        }
        int izq = evaluar(raiz.izquierdo);
        int der = evaluar(raiz.derecho);
        switch (raiz.valor) {
            case "+": return izq + der;
            case "-": return izq - der;
            case "*": return izq * der;
            case "/": return izq / der;
        }
        return 0;
    }

    // Recorrido en orden (para mostrar el árbol)
    public static void inOrden(Nodo raiz) {
        if (raiz != null) {
            inOrden(raiz.izquierdo);
            System.out.print(raiz.valor + " ");
            inOrden(raiz.derecho);
        }
    }

    public static void main(String[] args) {
        String expresion = "( 3 + 5 ) * ( 2 - 4 )"; // Ejemplo
        List<String> postfija = infijaAPostfija(expresion);
        System.out.println("Postfija: " + postfija);

        Nodo raiz = construirArbol(postfija);
        System.out.print("Árbol en orden: ");
        inOrden(raiz);
        System.out.println("\nResultado: " + evaluar(raiz));
    }
}
