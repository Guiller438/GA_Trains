package com.example.trains;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.agregarArista('A', 'B', 5);
        grafo.agregarArista('B', 'C', 4);
        grafo.agregarArista('C', 'D', 8);
        grafo.agregarArista('D', 'C', 8);
        grafo.agregarArista('D', 'E', 6);
        grafo.agregarArista('A', 'D', 5);
        grafo.agregarArista('C', 'E', 2);
        grafo.agregarArista('E', 'B', 3);
        grafo.agregarArista('A', 'E', 7);

        // Pruebas de salida esperada
        System.out.println(grafo.distanciaDeRuta(Arrays.asList('A', 'B', 'C')));           // Salida #1: 9
        System.out.println(grafo.distanciaDeRuta(Arrays.asList('A', 'D')));                // Salida #2: 5
        System.out.println(grafo.distanciaDeRuta(Arrays.asList('A', 'D', 'C')));           // Salida #3: 13
        System.out.println(grafo.distanciaDeRuta(Arrays.asList('A', 'E', 'B', 'C', 'D'))); // Salida #4: 22
        int distancia5 = grafo.distanciaDeRuta(Arrays.asList('A', 'E', 'D'));
        System.out.println(distancia5 == -1 ? "NO EXISTE RUTA" : distancia5);               // Salida #5: NO EXISTE RUTA
        System.out.println(grafo.numViajesMaxParadas('C', 'C', 3, 0, new ArrayList<>()));  // Salida #6: 2
        System.out.println(grafo.numViajesExactasParadas('A', 'C', 4, 0, new ArrayList<>()));  // Salida #7: 3
        int longitud8 = grafo.longitudRutaMasCorta('A', 'C', 0, new ArrayList<>());
        System.out.println(longitud8 == Integer.MAX_VALUE ? "NO EXISTE RUTA" : longitud8); // Salida #8: 9
        int longitud9 = grafo.longitudRutaMasCorta('B', 'B', 0, new ArrayList<>());
        System.out.println(longitud9 == Integer.MAX_VALUE ? "NO EXISTE RUTA" : longitud9); // Salida #9: 9
        System.out.println(grafo.numRutasMenosDistancia('C', 'C', 30, 0, new ArrayList<>()));  // Salida #10: 7
    }
}

