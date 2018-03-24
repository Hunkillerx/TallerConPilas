/**
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad EAN (Bogotá - Colombia)
 * Departamento de Sistemas
 * Faculta de Ingeniería
 *
 * Proyecto EAN Kotlin Collections
 * Autor: Universidad EAN - Mar 16, 2018
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package ean.programacionavanzada.tallerpilas

import ean.collections.ArrayStack
import ean.collections.IList
import ean.collections.IStack
import kotlin.math.abs

/**
 * Obtener el fondo de la pila
 */
fun <T> IStack<T>.bottom(): T {
    require(!this.isEmpty)

    var c = this.peek()
    val p = this.copy()

    while (!p.isEmpty) {
        c = p.peek()
        p.pop()
    }

    return c
}

/**
 * Tamaño de una pila
 */
fun <T> tam(pila: IStack<T>): Int {
    var c = 0
    var copia = pila.copy()
    while (!copia.isEmpty) {
        copia.pop()
        c++
    }
    return c
}

/**
 * Función para determinar cuál es el número más grande de dos cifras que hay
 * en una pila de números. Si no existe  ningún número de dos cifras, retorne
 * null
 */
fun mayorDeDosCifras(pila: IStack<Int>): Int? {
    var copia = pila.copy()
    var mayor = Int.MIN_VALUE

    while ( !copia.isEmpty ) {
        var tope = copia.peek()
        if ( abs( tope ) in 10..99 && tope > mayor ) {
            mayor = tope
        }
        copia.pop()
    }
    return if ( mayor == Int.MIN_VALUE ) null else mayor
}

/**
 * Hacer una función externa que permita guardar un elemento en el fondo
 * de la pila. GEnérica
 */
fun <T> guardarEnElFondo(p: IStack<T>, elem: T): Unit {
    var aux: IStack<T> = ArrayStack()

    while ( !p.isEmpty ) {
        var tope = p.peek()
        aux.push( tope )
        p.pop()
    }

    aux.pop()
    aux.push( elem )

    while ( !aux.isEmpty ) {
        var tope = aux.peek()
        p.push( tope )
        aux.pop()
    }
}

/**
 * Usar una pila de letras para Determinar si una frase es palindrome o no
 */
fun palindrome(frase: String): Boolean {
    var pila1 = ArrayStack<Char>()
    for (i in frase) {
        pila1.push(i)
    }
    println(pila1)

    var copia = pila1.copy()
    var inver = ArrayStack<Char>()
    while (!copia.isEmpty) {
        var tope = copia.peek()
        inver.push(tope)
        copia.pop()
    }
    println(inver)
    copia = pila1.copy()
    while (!copia.isEmpty) {
        var a = copia.peek()
        var b = inver.peek()
        if (b != a) {
            return false
        }
        copia.pop()
        inver.pop()
    }
    return true
}

/**
 * Usar una pila para retornar una lista invertida de otra lista
 */
fun <T> invertirLista(unaLista: IList<T>): IList<T> {
    var pila = ArrayStack<T?>()
    var listaInvertida = ean.collections.emptyList<T>()

    var i = 0
    var p: T? = null
    var tam = unaLista.size

    while (i < tam) {
        p = unaLista!!.get(i)
        pila.push(p)
        i++
    }
    i = 0
    while (i < tam) {
        var tope = pila.peek()
        listaInvertida.add(tope!!)
        pila.pop()
        i++
    }
    return listaInvertida
}

/**
 * Determinar si dos pilas son iguales
 */
fun <T> igulesPilas(pila1: IStack<T>, pila2: IStack<T>): Boolean {
    var copia1 = pila1.copy()
    var copia2 = pila2.copy()

    if (tam(copia1) != tam(copia2)) {
        return false
    } else {
        while (!copia1.isEmpty && !copia2.isEmpty) {
            var tope1 = copia1.peek()
            var tope2 = copia2.peek()
            if (tope1 != tope2) {
                return false
            }
            copia1.pop()
            copia2.pop()
        }
    }
    return true
}

/**
 * Evaluar una expresión en notación postfija. Cada elemento de la expresión es un elemento de la lista
 */
fun evaluarPostfija(expresion: IList<String>): Int {
    var pila = ArrayStack<String>()
    var aux = ArrayStack<String>()

    var resultado = 0

    var i = 0
    var p = ""
    var tam = expresion.size

    var a = ""
    var b = ""

    while (i < tam) {
        p = expresion.get(i)
        pila.push(p)
        i++
    }
    // println( pila )

    var copia = pila.copy()
    while (!copia.isEmpty) {
        var tope = copia.peek()
        aux.push(tope)
        copia.pop()
    }
    // println( "$aux\ninicio de operaciones\n" )

    var aux2 = ArrayStack<String>() // pila que almacena los numeros
    while (!aux.isEmpty) {
        var tope = aux.peek()

        if (tope == "-") {
            // println("resta encontrada $aux2")
            b = aux2.peek() // segundo termino de la operacion
            aux2.pop()  // se elimina el ultimo numero de la pila
            a = aux2.peek() // primer termino de la operacion
            resultado = a.toInt() - b.toInt()
            aux2.pop()  // se elimina el penultumo numero de la pila
            aux2.push(resultado.toString()) // se agrega al resultado de la operacion convertido a String
            println("$a - $b = $resultado")
            // println("nueva pila = $aux2\n")
        } else if (tope == "+") {
            // println("suma encontrada $aux2")
            b = aux2.peek()
            aux2.pop()
            a = aux2.peek()
            resultado = a.toInt() + b.toInt()
            aux2.pop()
            aux2.push(resultado.toString())
            println("$a + $b = $resultado")
            // println("nueva pila = $aux2\n")
        } else if (tope == "/") {
            // println("division encontrada $aux2")
            b = aux2.peek()
            aux2.pop()
            a = aux2.peek()
            resultado = a.toInt() / b.toInt()
            aux2.pop()
            aux2.push(resultado.toString())
            println("$a / $b = $resultado")
            // println("nueva pila = $aux2\n")
        } else if (tope == "*") {
            // println("multiplicación encontrada $aux2")
            b = aux2.peek()
            aux2.pop()
            a = aux2.peek()
            resultado = a.toInt() * b.toInt()
            aux2.pop()
            aux2.push(resultado.toString())
            println("$a * $b = $resultado")
            // println("nueva pila = $aux2\n")
        } else if (tope == "%") {
            // println("modulo encontrado $aux2")
            b = aux2.peek()
            aux2.pop()
            a = aux2.peek()
            resultado = a.toInt() % b.toInt()
            aux2.pop()
            aux2.push(resultado.toString())
            println("$a % $b = $resultado")
            // println("nueva pila = $aux2\n")
        } else {
            // println("numero $tope agregado")
            aux2.push(tope)
        }

        aux.pop()
    }
    return resultado
}






