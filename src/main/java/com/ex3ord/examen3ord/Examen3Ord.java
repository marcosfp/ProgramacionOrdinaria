/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package com.ex3ord.examen3ord;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.apache.commons.lang3.ArrayUtils;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 *
 * @author madrid
 */
public class Examen3Ord {

    private static final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public static String[][] leerCsv(File f) throws IOException {

        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        List<String[]> lines = new ArrayList<String[]>();
        String thisLine;

        while ((thisLine = br.readLine()) != null) {
            lines.add(thisLine.split(";"));
        }

        // convert our list to a String array.
        String[][] array = new String[lines.size()][0];
        lines.toArray(array);
        return array;

    }

    public static void obntenerMayorSalida(String[][] fich) {
        int cSalidas = 8;
        Integer salida = Integer.MIN_VALUE;
        Integer fila = 0;
        for (int i = 1; i < fich.length; i++) {
            if (pattern.matcher(fich[i][cSalidas]).matches() && salida < Integer.parseInt(fich[i][cSalidas])) {
                salida = Integer.parseInt(fich[i][cSalidas]);
                fila = i;
            }

        }

        System.out.println("La mayor salida ha sido " + salida + " en el " + fich[fila][1] + " " + fich[fila][0]);

    }

    public static void obntenerMenorSalida(String[][] fich) {
        int cSalidas = 8;
        Integer salida = Integer.MAX_VALUE;
        Integer fila = 0;
        for (int i = 1; i < fich.length; i++) {
            if (pattern.matcher(fich[i][cSalidas]).matches() && salida > Integer.parseInt(fich[i][cSalidas])) {
                salida = Integer.parseInt(fich[i][cSalidas]);
                fila = i;
            }

        }

        System.out.println("La menor salida ha sido " + salida + " en el " + fich[fila][1] + " " + fich[fila][0]);

    }

    public static HashMap<String, Integer> mapearDatosFecha(String[][] datos) {

        HashMap<String, Integer> mapa = new HashMap<String, Integer>();
        String fecha;
        for (int i = 0; i < datos.length; i++) {
            fecha = datos[i][0] + " " + datos[i][1];
            if (pattern.matcher(datos[i][10]).matches()) {
                if (!mapa.containsKey(fecha)) {
                    mapa.put(fecha, Integer.parseInt(datos[i][10]));
                } else {
                    int valor = mapa.get(fecha);
                    mapa.put(fecha, valor + Integer.parseInt(datos[i][10]));
                }

            }
        }
        return mapa;
    }
    
        public static HashMap<String, Integer> mapearDatosDistrito(String[][] datos) {

        HashMap<String, Integer> mapa = new HashMap<String, Integer>();
        String distrito;
        for (int i = 0; i < datos.length; i++) {
            distrito = datos[i][2];
            if (pattern.matcher(datos[i][10]).matches()) {
                if (!mapa.containsKey(distrito)) {
                    mapa.put(distrito, Integer.parseInt(datos[i][10]));
                } else {
                    int valor = mapa.get(distrito);
                    mapa.put(distrito, valor + Integer.parseInt(datos[i][10]));
                }

            }
        }
        return mapa;
    } 
    

    public static void main(String[] args) {

        final File carpeta = new File("Bomberos");
        Map<String, String[][]> hBomberos = new HashMap<String, String[][]>();

        try {
            for (final File fileEntry : carpeta.listFiles()) {
                hBomberos.put(fileEntry.getName(), leerCsv(fileEntry));

            }
        } catch (IOException e) {
            System.out.println("Error ficheros");
        }

        String[][] total = new String[0][0];

        Set<String> entradas = hBomberos.keySet();
        for (String entrada : entradas) {

            total = ArrayUtils.addAll(total, hBomberos.get(entrada));

        }

        obntenerMayorSalida(total);
        obntenerMenorSalida(total);

        HashMap<String, Integer> mapaFecha = mapearDatosFecha(total);
        
        
        Set<String> entries = mapaFecha.keySet();
        
        for (String entry: entries){
            System.out.println("Para "+entry + " hubo "+mapaFecha.get(entry)+" salidas");
            
        }
        
        
        HashMap<String, Integer> mapaFistrito = mapearDatosDistrito(total);
        
        String dis= "SAN BLAS";
        
        System.out.println("En el distrito de "+dis +  " ha habido " + mapaFistrito.get(dis));
        
        
        
    }

}
