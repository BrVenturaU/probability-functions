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
            validarValorProbabilidad(p)
            val q = 1 - p
            return q
        }

        /**
         * Cálcula el factorial de un numero [n].
         * @param [n] Es el número al que se desea calcular el factorial.
         * @return El valor del factorial de [n]. Si [n] es cero devuelve 1.
         */
        fun factorial(n: Int=0): Long{
            validarFactorial(n)
            var factorial: Long = 1
            for (i in 1..n) {
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
            validarValoresNegativos(intArrayOf(n, x), "Los valores de n y x deben ser mayores o iguales a cero.")
            validarValoresInicioFin(x, n, "El valor de x debe ser menor o igual a n.")
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
            validarValorProbabilidad(p)
            validarValoresNegativos(intArrayOf(n, x), "Los valores de n y x deben ser mayores o iguales a cero.")
            validarValoresInicioFin(x, n, "El valor de x debe ser menor o igual a n.")
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

            validarValorProbabilidad(p)
            validarValoresNegativos(intArrayOf(n), "El valor de n no puede ser negativo.")
            validarValoresNegativos(intArrayOf(inicio, fin), "Los valores de inicio (a) y fin (b) deben ser mayores o iguales a cero.")
            validarValoresInicioFin(fin, n, "El valor de fin debe ser menor o igual a n.")
            validarValoresInicioFin(inicio, fin, "El valor de inicio debe ser menor o igual al valor de fin.")

            val fin = if (incluirFin) fin else fin-1
            val inicio = if (incluirInicio) inicio else inicio+1
            var probabilidadAcumulada: Double = 0.0
            for (x in inicio..fin){
                probabilidadAcumulada += probabilidadBinomial(n, x, p)
            }

            return probabilidadAcumulada
        }

        fun validarValorProbabilidad(p:Float){
            if(p<0 || p>1)
                throw NumberFormatException("El valor de p debe estar entre cero y uno.")
        }

        fun validarFactorial(n:Int){
            if(n < 0)
                throw NumberFormatException("El valor de n debe ser mayor o igual a cero.")
        }

        fun validarValoresInicioFin(inicio: Int, fin: Int, message: String="Valores inválidos. Verifique."){
            if (fin < inicio)
                throw NumberFormatException(message)
        }

        fun validarValoresNegativos(numbers: IntArray, message: String="El valor no puede ser negativo."){
            for (number in numbers){
                if (number<0)
                    throw NumberFormatException(message)
            }
        }
    }
}