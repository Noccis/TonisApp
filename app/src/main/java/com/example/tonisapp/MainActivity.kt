package com.example.tonisapp

import android.media.ImageReader
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // Alla mina olika kort
    val card1 = Card(1, R.drawable.one)
    val card2 = Card(2, R.drawable.two)
    val card3 = Card(3, R.drawable.three)
    val card4 = Card(4, R.drawable.four)
    val card5 = Card(5, R.drawable.five)
    val card6 = Card(6, R.drawable.six)
    val card7= Card(7, R.drawable.seven)
    val card8= Card(8, R.drawable.eight)
    val card9 = Card(9, R.drawable.nine)
    val card10 = Card(10, R.drawable.ten)


    // Lista för alla bilder och nr

    val listOfcards = mutableListOf<Card>(card1, card2, card3, card4, card5, card6, card7, card8, card9, card10)

    // Int variabler:

    var mainNr = 0
    var hiOrLow = 0
    var rightNr = 0
    var wrongNr = 0

    // View variabler:
    lateinit var mainNrView: ImageView
    lateinit var scoreTextView: TextView
    lateinit var hiOrLowTextView: TextView
    lateinit var playerCard1View: ImageView
    lateinit var playerCard2View: ImageView
    //Vi väntar med dessa för att inte krångla till det.
    // lateinit var lowerView: ImageView
    // lateinit var higherView: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainNrView = findViewById(R.id.mainNrView)
       // mainNrView.setImageResource(card1.imageId)    Sätter mainNr bilden till det id som är på card1.
        mainNrView.setImageResource(setImage(listOfcards))

        var testingFunctions = setImage(listOfcards)


        Log.d("Dodo", "$testingFunctions")

        scoreTextView = findViewById(R.id.scoreView)
        hiOrLowTextView = findViewById(R.id.hiLowTextView)
        playerCard1View = findViewById(R.id.playerCard1)
        playerCard2View = findViewById(R.id.playerCard2)



    }
}

fun randomMainNr ():Int {
    val temp = (2..9).random()
    // return (2..9).random()
    Log.d("Dodo", "$temp")
    return temp                     // städa denna sen så det bara står det som är bortkommenterat.
}

fun setImage (cardList: MutableList<Card>): Int{
    val cardId = cardList [randomMainNr()-1].imageId
    return cardId
}

/*  Fixa en random som sätter nummret i mainNr                                      x
    Fixa så att högre eller lägre än visas random på skärmen
    Fixa så att player cards visar ett rätt och ett fel svar, rätt svar ska även sparas som int.
    Fixa så att score ökas om man svarar rätt, eller minskas om man svarar fel.
    Fixa så att mainNr laddas om när man svarat rätt.


 */