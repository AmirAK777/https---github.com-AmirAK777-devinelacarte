package org.sio.slam.devine.core

import kotlin.math.log2

class Jeu(val avecAide: Boolean, val paquet: Paquet, paramCarteADeviner: Carte? = null) {
    val carteADeviner: Carte
        // le getter par défaut, inutile de le redéclarer (juste pour la démonstration)
        // field est ici synonyme de carteADeviner (implicite backing memory de la propriété)
        // REM : faire référence à carteADeviner au lieu de field entrainerait une récursion incontrôlée
        get() = field
    // set(value) { field = value } <== impossible car la propriété est en lecture seule (val)

    init {
        // si le paramètre paramCarteADeviner du constructeur a comme valeur null
        // alors on demande au paquet de nous fournir une carte à deviner
        // sinon on retient la valeur de carte transmise
        this.carteADeviner = paramCarteADeviner ?: this.paquet.getCarteADeviner()
    }

    /**
     * Permettre de savoir si la proposition de carte EST bien la carte à deviner, ou non
     */
    fun isMatch(carteProposee: Carte): Boolean {
        return this.carteADeviner == carteProposee
    }

    /**
     * Analyse la partie du joueur, a-t-il abandonné la partie,
     *  a-t-il trouvé la carte en un nombre de fois "convenable" ou "inconvenable",
     *  a-t-il eu de la chance ?
     */
    fun strategiePartie(abandonner: Boolean, nbEssai: Int): String {

        if (!abandonner) {
            if (avecAide) {
                val logEssai = log2(paquet.cartes.size.toDouble())
                if (nbEssai.toDouble() >= logEssai * 2) {
                    return "Le nombre d'essai n'est pas parfaite: $nbEssai"
                } else if (nbEssai.toDouble() >= logEssai && nbEssai.toDouble() < logEssai * 2) {
                    return "La strategie est bonne mais n'est pas parfaite, nombre d'essai: $nbEssai"
                } else if (nbEssai == logEssai.toInt()) {
                    return "La strategie est parfaite, nombre d'essai: $nbEssai"
                } else if (nbEssai.toDouble() < logEssai) {
                    return "Vous reflechisiez mieux qu'un robot, nombre d'essai: $nbEssai"
                }
            } else {
                val pourcentageChance = (nbEssai.toDouble() / paquet.cartes.size.toDouble()) * 100
                if (pourcentageChance <= 0.50) {
                    return "Vous avez de la chance, ${pourcentageChance.toInt()}% de chance, nomre d'essai: $nbEssai"
                } else {
                    return "Vous n'avez pas de chance, ${pourcentageChance.toInt()}% de chance, nomre d'essai: $nbEssai "
                }
            }
            return "Probleme, nombre d'essai :$nbEssai "
        } else {
            return "Vous avez abandonner"
        }


    }
}