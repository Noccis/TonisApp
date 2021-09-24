package com.example.tonisapp

import android.provider.Settings.Global.getString
import android.view.View
import android.widget.TextView


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
/*
fun hiOrLow(lowerView: TextView, higherView: TextView): String {

    hiOrLow = random1or2()
    val string: String
    if (hiOrLow == 1) {

        string = getString(R.string.lower)                                                                   // Fixa så den pekar på strängen i XML filen
        lowerView.visibility = View.VISIBLE
    } else if (hiOrLow == 2) {
        string = getString(R.string.higher)                                                                    // Fixa så den pekar på strängen i XML filen
        higherView.visibility = View.VISIBLE
    } else {
        string = getString(R.string.error)
    }
    return string
}

 */