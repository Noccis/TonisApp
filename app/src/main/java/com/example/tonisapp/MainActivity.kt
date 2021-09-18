package com.example.tonisapp

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
    var score = 0

    // View variabler:
    lateinit var mainNrView: ImageView
    lateinit var scoreTextView: TextView
    lateinit var hiOrLowTextView: TextView
    lateinit var playerCard1View: ImageView
    lateinit var playerCard2View: ImageView
    lateinit var lowerView: TextView
    lateinit var higherView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // mainNrView.setImageResource(card1.imageId)    Sätter mainNr bilden till det id som är på card1.

        // Binder ihop mina view variabler med mina views.
        mainNrView = findViewById(R.id.mainNrView)
        scoreTextView = findViewById(R.id.scoreView)
        hiOrLowTextView = findViewById(R.id.hiLowTextView)
        playerCard1View = findViewById(R.id.playerCard1)
        playerCard2View = findViewById(R.id.playerCard2)
        lowerView = findViewById(R.id.lowerView)
        higherView = findViewById(R.id.higherView)

        lowerView.visibility = View.INVISIBLE
        higherView.visibility = View.GONE


        scoreTextView.text = "$score"

        mainNrView.setImageResource(setImage(listOfcards))      //Skapar random siffra/kort i mainNr

        hiOrLowTextView.text = hiOrLow()                        //Bestämmer om man ska lägga högre eller lägre.

        Log.d("Dodo", "$mainNr")

        // Ring playercard funktionen här
         randomPlayerCard(listOfcards)






    }

    // Main Nr funktioner:
    fun randomMainNr ():Int {

        mainNr = (2..9).random()
        return mainNr
        Log.d("nummer", "Main nr är $mainNr")
    }

    fun setImage(cardList: MutableList<Card>): Int {

        return cardList[randomMainNr() - 1].imageId
    }



    // Hi Low funktioner:

    fun hiOrLow():String {

        hiOrLow = random1or2()
        val string: String
        if (hiOrLow == 1) {

            string = "Lägg lägre än"                                                                    // Fixa så den pekar på strängen i XML filen
            lowerView.visibility = View.VISIBLE
        }
        else  if (hiOrLow == 2){
            string = "Lägg högre än"                                                                    // Fixa så den pekar på strängen i XML filen
            higherView.visibility = View.VISIBLE
        }
        else {
            string = "Error"
        }
        return string
    }

    // Player card funktioner:

   /* fun playerCardsRightNr(cardList: MutableList<Card>): Int {

        if (hiOrLow == 1) {

            rightNr = (1..mainNr).random()
           // wrongNr = (mainNr..10).random()
        }
        else if (hiOrLow == 2) {

            rightNr = (mainNr..10).random()
          //  wrongNr = (1..mainNr).random()
        }
        return cardList[rightNr - 1].imageId
        Log.d("nummer", "$rightNr")
    }

    */

    fun randomPlayerCard(cardList: MutableList<Card>) {
        val placement = random1or2()                    // Skapar variabel för vänter eller höger
        Log.d("nummer", "$placement")

        if (hiOrLow == 1) {

            rightNr = (1..mainNr -1).random()
            wrongNr = (mainNr -1..10).random()

            Log.d("nummer", "Rätt nr: $rightNr, fel nr: $wrongNr ")
        }
        else if (hiOrLow == 2) {                        // Här någonstans blir det fel.

            rightNr = (mainNr -1..10).random()
            wrongNr = (1..mainNr-1).random()
            Log.d("nummer", "Rätt nr: $rightNr, fel nr: $wrongNr ")
        }

        if(placement == 1) {
            playerCard1View.setImageResource(cardList[rightNr -1].imageId)
            playerCard2View.setImageResource(cardList[wrongNr -1].imageId)
        }
        else if (placement == 2){
            playerCard1View.setImageResource(cardList[wrongNr -1].imageId)
            playerCard2View.setImageResource(cardList[rightNr -1].imageId)
        }
    }

    // Övriga funktioner:

    fun random1or2():Int {
        return (1..2).random()
    }

}



/*  Fixa en random som sätter nummret i mainNr                                                       x
    Fixa så att högre eller lägre än visas random på skärmen                                         x
    Fixa så att player cards visar ett rätt och ett fel svar, rätt svar ska även sparas som int.     x
    Fixa så att en ruta dyker upp bredvid mainNrView.
    Kolla varför right och wrong Nr spårade ur helt plötsligt.... Efter jag lagt till lower och higher view.
    Fixa så att score ökas om man svarar rätt, eller minskas om man svarar fel.
    Fixa så att mainNr laddas om när man svarat rätt.
    Fixa så att strängarna i hiOrLow func refererar till strings-xml
    Städa bort alla kontroller.


 */