package com.example.tonisapp


// Main Nr funktioner:

fun randomMainNr(): Int {

    mainNr = (2..9).random()
    return mainNr

}
fun setImage(cardList: MutableList<Card>): Int {

    return cardList[randomMainNr() - 1].imageId
}


// Övriga funktioner:

fun random1or2():Int {
    return (1..2).random()
}