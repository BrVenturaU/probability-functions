package com.example.probabilityfunctions.utils

import kotlin.math.pow

class BinomialHelper {
    companion object{
        /**
         * Función que cálcula la probabilidad contraria de un evento.
         * @param [p] Es el valor de probabilidad de éxito de un evento.
         * @return El valor de probabilidad contraria de un evento.
         */
        fun probabilidadContraria(p: Float = 0f): Float{
            val q = 1 - p
            return q
        }

        /**
         * Cálcula el factorial de un numero [n].
         * @param [n] Es el número al que se desea calcular el factorial.
         * @return El valor del factorial de [n]. Si [n] es cero devuelve 1.
         */
        fun factorial(n: Int=0): Long{
            var factorial: Long = 1
            for (i in 1..n) {
                // factorial = factorial * i;
                factorial *= i.toLong()
            }
            return factorial
        }

        /**
         * Realiza una combianción sin repetición de valores [n] y [x].
         * @param [n] Es el número total de elementos.
         * @param [x] Es el número de elementos seleccionados.
         * @return El valor de la combinación entre [n] y [x].
         */
        fun combinacionSinRepeticion(n: Int, x: Int): Long{

            val factorialN = factorial(n)
            val factorialK = factorial(x)
            val factorialNMenosX = factorial(n-x)

            val resultadoCombinatorio = factorialN / (factorialK * factorialNMenosX)
            return resultadoCombinatorio
        }

        /**
         * Resuelve el cálculo de probabilidad binomial.
         * @param [n] Es el número total de elementos.
         * @param [x] Es el número de elementos seleccionados.
         * @param [p] Es el valor de probabilidad de éxito del evento.
         * @return El valor de la probabilidad de que ocurra el evento [x].
         */
        fun probabilidadBinomial(n: Int, x: Int, p: Float): Double{
            val q = probabilidadContraria(p)
            val resultadoBinomial = combinacionSinRepeticion(n, x) * p.pow(x) * q.pow(n-x)
            return resultadoBinomial.toDouble()
        }

        /**
         * Resuelve cálculo de probabilidad binomial acumulada.
         * @param [n] Es el número total de elementos.
         * @param [inicio] Es el número de inicio de elementos seleccionados. Si [inicio] no es
         * definido su valor es cero.
         * @param [fin] Es el número de fin de los elementos seleccionados. Si [fin] no es
         * definido su valor es el valor de [n].
         * @param [incluirInicio] Valor booleano que indica si el valor de inicio debe ser
         * tomado en cuenta para el calculo. Su valor inicial es true.
         * @param [incluirFin] Valor booleano que indica si el valor de fin debe ser
         * tomado en cuenta para el calculo. Su valor inicial es true.
         * @return El valor de la probabilidad acumulada de que ocurra el evento en el rando de
         * [inicio] a [fin].
         */
        fun probabilidadBinomialAcumulada(
                n: Int, p: Float, inicio: Int=0,
                fin: Int=n, incluirInicio: Boolean=true,
                incluirFin: Boolean=true): Double{
            val fin = if (incluirFin) fin else fin-1
            val inicio = if (incluirInicio) inicio else inicio+1
            var probabilidadAcumulada: Double = 0.0
            for (x in inicio..fin){
                probabilidadAcumulada += probabilidadBinomial(n, x, p)
            }

            return probabilidadAcumulada
        }

    }
}