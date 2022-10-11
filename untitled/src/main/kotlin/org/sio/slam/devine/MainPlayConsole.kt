package org.sio.slam.devine

import org.sio.slam.devine.core.Carte
import org.sio.slam.devine.core.Jeu
import org.sio.slam.devine.core.Paquet
import org.sio.slam.devine.enum.CouleurCarte
import org.sio.slam.devine.enum.NomCarte
import org.sio.slam.devine.enum.getCouleurCarteFromString
import org.sio.slam.devine.enum.getNomCarteFromString
import org.sio.slam.devine.fabrique.createJeu32Cartes
import org.sio.slam.devine.fabrique.createJeu52Cartes

fun main(args: Array<String>) {

    var aide = false
    println("Voulez vous de l'aide?")
    val demandeAide: String = (readLine()?.uppercase()) + ""
    if (demandeAide == "OUI") {
        aide = true
    }
    // TODO (A) demander au joueur avec quel jeu de cartes 32 ou 52 il souhaite jouer

    var paquetDeCartes: Paquet

    do {
        println("Entrez le jeu de carte (exemple : 32, 52)")
        val demandeTypeCarte: String = readLine() + ""

        if (demandeTypeCarte == "32") {
            paquetDeCartes = Paquet(createJeu32Cartes())
        } else {
            paquetDeCartes = Paquet(createJeu52Cartes())
        }
    } while (demandeTypeCarte != "32" && demandeTypeCarte != "52")

    println("Création d'un paquet de $paquetDeCartes")

    println(" ==== Instanciation du jeu, début de la partie. ====")
    val jeu = Jeu(aide, paquetDeCartes)
    var nbEssai = 0
    var abandonner = false
do {
    var repeat = 0
    nbEssai++

    println("Entrez un nom de carte dans le jeu (exemples : Roi, sept, six, As...) :")
    // TODO (optionnel) permettre de saisir un chiffre au lieu d'une chaine : 7 au lieu de Sept...

    val nomCarteUserStr: String = readLine() + ""
    val nomCarteUser: NomCarte? = getNomCarteFromString(nomCarteUserStr.trim().uppercase())


    println("Entrez un nom de \"couleur\" de carte parmi : Pique, Trefle, Coeur, Carreau : ")
    val couleurCarteUserStr: String = readLine() + ""
    val couleurCarteUser: CouleurCarte? = getCouleurCarteFromString(couleurCarteUserStr.trim().uppercase())

    if (nomCarteUser != null && couleurCarteUser != null) {
        // prise en compte de la carte du joueur
        val carteDuJoueur: Carte = Carte(nomCarteUser, couleurCarteUser)

        if (jeu.isMatch(carteDuJoueur)) {
            println("Bravo, vous avez trouvé la bonne carte !")
            break
        } else {
            println("Ce n'est pas la bonne carte !")
            println("votre proposition  : $carteDuJoueur")
            if (aide) {
                if (carteDuJoueur < jeu.carteADeviner) {
                    println("la carte a deviner est plus grande")
                } else {
                    println("la carte a deviner est plus petite")


                    // TODO: (A) si l'aide est activée, alors dire si la carte proposée est
                    //  plus petite ou plus grande que la carte à deviner
                }
            }
        }
    } else {
        // utilisateur a mal renseigné sa carte
        val nomCarte = if (nomCarteUserStr == "") "?" else nomCarteUserStr
        val couleurCarte = if (couleurCarteUserStr == "") "?" else couleurCarteUserStr

        println("Désolé, mauvaise définition de carte ! (${nomCarte} de ${couleurCarte})")
    }
if (repeat == 0){
    println("Voulez vous réessayer ?")
    var abandon: String = readLine() + ""
    do {
        if (abandon.trim().uppercase() != "OUI" && abandon.trim().uppercase() != "NON"){
            println("Veillez réessayer")
            abandon =readLine() + ""

            }
        if (abandon.trim().uppercase() == "OUI"){
            print("")
        }
        if (abandon.trim().uppercase() == "NON"){
            repeat++
            abandonner = true
        }
    } while (abandon.trim().uppercase() != "OUI" && abandon.trim().uppercase() != "NON")
}
} while (repeat !=1)
    // TODO (A) permettre au joueur de retenter une autre carte (sans relancer le jeu) ou d'abandonner la partie


    println(" ==== Fin prématurée de la partie ====")

    // TODO (A) Présenter à la fin la carte à deviner
    println("La carte a deviner était ${jeu.carteADeviner} ")

    // TODO (challenge-4) la stratégie de jeu est à implémenter... à faire lorsque les autres TODOs auront été réalisés
    println("Votre stratégie de jeu : ${jeu.strategiePartie(abandonner,nbEssai)} ")

    println(" ==== Fin de la partie. ====")
}
