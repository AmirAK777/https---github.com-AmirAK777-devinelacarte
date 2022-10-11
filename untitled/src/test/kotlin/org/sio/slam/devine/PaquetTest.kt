package org.sio.slam.devine

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.fail
import org.sio.slam.devine.core.Carte
import org.sio.slam.devine.core.Paquet
import org.sio.slam.devine.enum.CouleurCarte
import org.sio.slam.devine.enum.NomCarte
import org.sio.slam.devine.enum.getNomCarteFromString
import org.sio.slam.devine.fabrique.createJeu32Cartes
import org.sio.slam.devine.fabrique.createJeu52Cartes

internal class PaquetTest {

    @Test
    fun cardinal2Cartes() {
        val paquet2Cartes = Paquet(listOf(
            Carte(NomCarte.VALET, CouleurCarte.COEUR),
            Carte(NomCarte.DIX, CouleurCarte.TREFLE),
        ))
        assertEquals(2, paquet2Cartes.cardinal())
    }

    @Test
    fun testToString2Cartes() {
        val paquet2Cartes = Paquet(listOf(
            Carte(NomCarte.VALET, CouleurCarte.COEUR),
            Carte(NomCarte.DIX, CouleurCarte.TREFLE),
        ))
        assertEquals("Paquet de 2 cartes", paquet2Cartes.toString() )
    }

    @Test
    fun testGetCartes() {
        val paquet = Paquet(emptyList())
        val paquetSize = paquet.cartes.size
        assertEquals(32,paquetSize)
        assertEquals(NomCarte.AS,paquet.cartes[0].nom)



    }

    @Test
    fun fabriqueDe32Cartes() {
    val jeu2Carte32 = createJeu32Cartes()
    assertEquals(32,jeu2Carte32.size)
        // TODO test fabriqueDe32Cartes à implémenter
    }

    @Test
    fun fabriqueDe52Cartes() {
        val jeu2Carte52 = createJeu52Cartes()
        assertEquals(52,jeu2Carte52.size)
        // TODO test fabriqueDe52Cartes à implémenter :

    }
    @Test
    fun testBatcarte() {
        val paquetDeuxCartes = Paquet(
            listOf(
                Carte(NomCarte.SIX, CouleurCarte.CARREAU),
                Carte(NomCarte.SEPT, CouleurCarte.COEUR),
                Carte(NomCarte.HUIT, CouleurCarte.COEUR),
                Carte(NomCarte.NEUF, CouleurCarte.CARREAU),
                Carte(NomCarte.DIX, CouleurCarte.PIQUE),
            )
        )

        val paquetMixed = paquetDeuxCartes.batLesCartes(paquetDeuxCartes.cartes)
        assertNotEquals(paquetDeuxCartes.cartes[0], paquetMixed[0])// test si la fonction batLesCarte fonctionne bien

    }
}
