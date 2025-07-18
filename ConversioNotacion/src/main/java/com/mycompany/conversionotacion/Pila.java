package com.mycompany.conversionotacion;

import java.util.ArrayList;

/**
 * Implementación de una pila (estructura LIFO) para manejar los operadores
 * LIFO = Last In First Out (Último en entrar, primero en salir)
 */
public class Pila<T> {
    private ArrayList<T> elementos = new ArrayList<>();
    
    // Añade un elemento a la cima de la pila
    public void apilar(T elemento) {
        elementos.add(elemento);
    }
    
    // Elimina y devuelve el elemento de la cima
    public T desapilar() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        return elementos.remove(elementos.size() - 1);
    }
    
    // Observa el elemento en la cima sin sacarlo
    public T mirarCima() {
        if (estaVacia()) {
            throw new RuntimeException("La pila está vacía");
        }
        return elementos.get(elementos.size() - 1);
    }
    
    // Verifica si la pila no contiene elementos
    public boolean estaVacia() {
        return elementos.isEmpty();
    }
}