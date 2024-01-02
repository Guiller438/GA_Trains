package com.example.trains;

import java.util.*;

public class Grafo {
    private Map<Character, Map<Character, Integer>> grafo = new HashMap<>();

    public void agregarArista(char inicio, char fin, int distancia) {
        grafo.computeIfAbsent(inicio, k -> new HashMap<>()).put(fin, distancia);
    }

    public int distanciaDeRuta(List<Character> ruta) {
        int distancia = 0;
        for (int i = 0; i < ruta.size() - 1; i++) {
            char inicio = ruta.get(i);
            char fin = ruta.get(i + 1);
            if (!grafo.containsKey(inicio) || !grafo.get(inicio).containsKey(fin)) {
                return -1; // NO EXISTE RUTA
            }
            distancia += grafo.get(inicio).get(fin);
        }
        return distancia;
    }

    public int numViajesMaxParadas(char inicio, char fin, int maxParadas, int paradasActuales, List<Character> rutaActual) {
        if (paradasActuales > maxParadas) {
            return 0;
        }
        if (inicio == fin && paradasActuales > 0) {
            return 1;
        }
        int count = 0;
        for (char vecino : grafo.getOrDefault(inicio, new HashMap<>()).keySet()) {
            List<Character> nuevaRuta = new ArrayList<>(rutaActual);
            nuevaRuta.add(inicio);
            count += numViajesMaxParadas(vecino, fin, maxParadas, paradasActuales + 1, nuevaRuta);
        }
        return count;
    }

    public int numViajesExactasParadas(char inicio, char fin, int paradasExactas, int paradasActuales, List<Character> rutaActual) {
        if (paradasActuales == paradasExactas && inicio == fin) {
            return 1;
        }
        if (paradasActuales > paradasExactas) {
            return 0;
        }
        int count = 0;
        for (char vecino : grafo.getOrDefault(inicio, new HashMap<>()).keySet()) {
            List<Character> nuevaRuta = new ArrayList<>(rutaActual);
            nuevaRuta.add(inicio);
            count += numViajesExactasParadas(vecino, fin, paradasExactas, paradasActuales + 1, nuevaRuta);
        }
        return count;
    }

    public int longitudRutaMasCorta(char inicio, char fin, int longitudActual, List<Character> rutaActual) {
        if (inicio == fin) {
            return longitudActual;
        }
        if (rutaActual.isEmpty()) {
            rutaActual = new ArrayList<>(Collections.singletonList(inicio));
        }
        int minLongitud = Integer.MAX_VALUE;
        for (char vecino : grafo.getOrDefault(inicio, new HashMap<>()).keySet()) {
            if (!rutaActual.contains(vecino)) {
                List<Character> nuevaRuta = new ArrayList<>(rutaActual);
                nuevaRuta.add(vecino);
                int nuevaLongitud = longitudRutaMasCorta(vecino, fin, longitudActual + grafo.get(inicio).get(vecino), nuevaRuta);
                minLongitud = Math.min(minLongitud, nuevaLongitud);
            }
        }
        return minLongitud;
    }

    public int numRutasMenosDistancia(char inicio, char fin, int maxDistancia, int distanciaActual, List<Character> rutaActual) {
        int count = 0;
        for (char vecino : grafo.getOrDefault(inicio, new HashMap<>()).keySet()) {
            int nuevaDistancia = distanciaActual + grafo.get(inicio).get(vecino);
            if (nuevaDistancia < maxDistancia) {
                List<Character> nuevaRuta = new ArrayList<>(rutaActual);
                nuevaRuta.add(inicio);
                if (vecino == fin && nuevaDistancia < maxDistancia) {
                    count++; // Incrementar el conteo solo cuando se llega al destino y la distancia es menor a maxDistancia
                }
                count += numRutasMenosDistancia(vecino, fin, maxDistancia, nuevaDistancia, nuevaRuta);
            }
        }
        return count;
    }
}
