package ean.programacionavanzada.tallerpilas

import ean.collections.ArrayStack
import ean.collections.IStack
import ean.collections.emptyList
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TallerPilasKtTest {
    @Test
    fun pruebaMayor() {
        val pila: IStack<Int> = ArrayStack()

        pila.push(13)
        pila.push(89)
        pila.push(1000)
        pila.push(670)
        pila.push(1)


        var mayor2 = mayorDeDosCifras(pila)
        assertEquals(89, mayor2)

    }

    @Test
    fun pruebaGuardarEnFondo() {
        var pila: IStack<Int> = ArrayStack()

        pila.push(13)
        pila.push(89)
        pila.push(1000)
        pila.push(670)
        pila.push(1)

        println("original: $pila")

        var fondo = pila.bottom()

        assertEquals(13, fondo)

        guardarEnElFondo(pila, 780)

        println("nueva: $pila")

        fondo = pila.bottom()

        assertEquals(780, fondo)
    }

    @Test
    fun palindrome() {
        var test = palindrome("sobreverbos")

        assertTrue(test)

        test = palindrome("esto")

        assertFalse(test)
    }

    @Test
    fun invertirLista() {

        val lista = emptyList<Int>()


        lista.add(67)
        lista.add(866)
        lista.add(3345)
        lista.add(12)
        lista.add(174)
        lista.add(90)

        println(lista)

        var inversa = ean.programacionavanzada.tallerpilas.invertirLista(lista)
        print(inversa)
    }

    @Test
    fun pilasIguales() {

        var primera: IStack<Int> = ArrayStack()

        primera.push(13)
        primera.push(44)
        primera.push(5600)
        primera.push(679)
        primera.push(43)


        var segunda: IStack<Int> = ArrayStack()
        segunda.push(2)
        segunda.push(4)
        segunda.push(1997)
        segunda.push(7)
        segunda.push(1)
        segunda.push(1973)

        var test = igulesPilas(primera, segunda)

        assertFalse(test)

        primera.clear()

        primera.push(2)
        primera.push(4)
        primera.push(1997)
        primera.push(7)
        primera.push(1)
        primera.push(1973)

        segunda.clear()

        segunda.push(2)
        segunda.push(4)
        segunda.push(1997)
        segunda.push(7)
        segunda.push(1)
        segunda.push(1973)

        test = igulesPilas(primera, segunda)

        assertTrue(test)
    }

    @Test
    fun tamaño() {
        var pila: IStack<Int> = ArrayStack()

        pila.push(2)
        pila.push(4)
        pila.push(1997)
        pila.push(7)
        pila.push(1)
        pila.push(1973)

        var tam = tam(pila)

        assertEquals(6, tam)

        pila.push(412)

        tam = tam(pila)

        assertEquals(7, tam)
    }

    @Test
    fun evaluarPostfija() {
        // Creamos una lista
        val lista = emptyList<String>()

        // Llenamos la lista de una serie de datos
        lista.add("10")
        lista.add("8")
        lista.add("7")
        lista.add("-")
        lista.add("4")
        lista.add("+")
        lista.add("/")

        var test = evaluarPostfija(lista)

        println("El resultado de la operación es: \n$test")

        assertEquals(2, test)

        // Llenamos la lista de una serie de datos
        lista.clear()
        lista.add("12")
        lista.add("10")
        lista.add("%")
        lista.add("4")
        lista.add("7")
        lista.add("-")
        lista.add("*")

        test = evaluarPostfija(lista)

        println("El resultado de la operación es: \n$test")

        assertEquals(-6, test)
    }
}