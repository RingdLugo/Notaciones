package com.mycompany.conversionotacion;

/**
 * Clase que realiza las conversiones entre notaciones matemáticas
 */
public class ConversorNotacion {
    
    /**
     * Convierte notación infija a postfija (Polaca Inversa)
     * Ejemplo: (A+B)*C → AB+C*
     */
    public String infijoAPostfija(String expresion) throws Exception {
        validarExpresion(expresion);
        
        StringBuilder resultado = new StringBuilder();
        Pila<Character> pila = new Pila<>();
        
        expresion = expresion.replaceAll("\\s+", ""); // Eliminar espacios
        
        for (char c : expresion.toCharArray()) {
            if (esOperando(c)) {
                resultado.append(c);
            } else if (c == '(') {
                pila.apilar(c);
            } else if (c == ')') {
                while (!pila.estaVacia() && pila.mirarCima() != '(') {
                    resultado.append(pila.desapilar());
                }
                pila.desapilar(); // Eliminar '('
            } else if (esOperador(c)) {
                while (!pila.estaVacia() && precedencia(c) <= precedencia(pila.mirarCima())) {
                    resultado.append(pila.desapilar());
                }
                pila.apilar(c);
            }
        }
        
        while (!pila.estaVacia()) {
            resultado.append(pila.desapilar());
        }
        
        return resultado.toString();
    }
    
    /**
     * Convierte notación infija a prefija (Polaca)
     * Ejemplo: (A+B)*C → *+ABC
     */
    public String infijoAPrefija(String expresion) throws Exception {
        validarExpresion(expresion);
        
        // 1. Invertir la expresión original
        String invertida = new StringBuilder(expresion.replaceAll("\\s+", ""))
            .reverse()
            .toString()
            .replace('(', '#').replace(')', '(').replace('#', ')');
        
        // 2. Convertir a postfija la expresión invertida
        String postfija = infijoAPostfija(invertida);
        
        // 3. Invertir el resultado para obtener prefija
        return new StringBuilder(postfija).reverse().toString();
    }
    
    // Métodos auxiliares
    private void validarExpresion(String expresion) throws Exception {
        if (expresion == null || expresion.trim().isEmpty()) {
            throw new Exception("La expresión no puede estar vacía");
        }
    }
    
    private boolean esOperando(char c) {
        return Character.isLetterOrDigit(c);
    }
    
    private boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }
    
    private int precedencia(char operador) {
        switch (operador) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
            default: return -1;
        }
    }
}