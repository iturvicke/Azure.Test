package com.eon.azure.bo.impl;

import com.eon.azure.bo.boPrueba;
import com.eon.azure.common.Cuadrado;

public class boPruebaImpl implements boPrueba {

    @Override
    public Cuadrado calculoArea(float Lado) {
        
        Cuadrado cuadrado = new Cuadrado();

         
        cuadrado.setLado(Lado);
      
        cuadrado.setArea(Math.pow(cuadrado.getLado(), 2));

      
        
        
        return cuadrado;
    }


    
}
