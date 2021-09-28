package com.example.tonisapp

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

open class LvlParentActivity : AppCompatActivity() {

    // Int variabler:
    var mainNr = 0
    var hiOrLow = 0
    var rightNr = 0
    var wrongNr = 0
    var wrongNr2 = 0
    var score = 0
    var rightAnswerCard = 0     // Håller reda på vilket kort som är rätt
    var rightAnswerImageId = 0  // Sparar imageId för rätta svaret för att återanvända kortet


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
    val card11 = Card(11, R.drawable.eleven)
    val card12 = Card(12, R.drawable.twelve)
    val card13 = Card(13, R.drawable.thirteen)
    val card14 = Card(14, R.drawable.fourteen)
    val card15 = Card(15, R.drawable.fifteen)
    val card16 = Card(16, R.drawable.sixteen)
    val card17 = Card(17, R.drawable.seventeen)
    val card18 = Card(18, R.drawable.eighteen)
    val card19 = Card(19, R.drawable.nineteen)
    val card20 = Card(20, R.drawable.twenty)


// Lista för alla bilder och nr

    val listOfcards = mutableListOf(
        card1,
        card2,
        card3,
        card4,
        card5,
        card6,
        card7,
        card8,
        card9,
        card10,
        card11,
        card12,
        card13,
        card14,
        card15,
        card16,
        card17,
        card18,
        card19,
        card20)



    // View variabler:
    lateinit var mainNrView: ImageView
    lateinit var scoreTextView: TextView
    lateinit var hiOrLowTextView: TextView
    lateinit var playerCard1View: ImageView
    lateinit var playerCard2View: ImageView
    lateinit var playerCard3View: ImageView
    lateinit var lowerView: ImageView
    lateinit var higherView: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

/*


        // Binder ihop mina view variabler med mina views.
        mainNrView = findViewById(R.id.mainNrView)
        scoreTextView = findViewById(R.id.scoreView)
        hiOrLowTextView = findViewById(R.id.hiLowTextView)
        playerCard1View = findViewById(R.id.playerCard1)
        playerCard2View = findViewById(R.id.playerCard3)
        lowerView = findViewById(R.id.lowerView)
        higherView = findViewById(R.id.higherView)

        lowerView.visibility = View.GONE
        higherView.visibility = View.GONE

        score = intent.getIntExtra("score", 0)

        scoreTextView.text = "$score"

        mainNrView.setImageResource(setImage(listOfcards))      //Skapar random siffra/kort i mainNr


        hiOrLowTextView.text =
            hiOrLow()                        //Bestämmer om man ska lägga högre eller lägre.


        randomPlayerCard(listOfcards)       // Genererar rätt och fel svar till random playerCard


 */

    }

    // Main Nr funktioner:

    fun randomMainNr(): Int {

        mainNr = (2..9).random()
        return mainNr

    }
    fun setImage(cardList: MutableList<Card>): Int {

        return cardList[randomMainNr() - 1].imageId
    }

    // Hi Low funktioner:

    fun hiOrLow(): String {

        hiOrLow = random1or2()
        val string: String
        if (hiOrLow == 1) {

            string = getString(R.string.lower)                                                                    // Fixa så den pekar på strängen i XML filen
            lowerView.visibility = View.VISIBLE

        } else if (hiOrLow == 2) {
            string = getString(R.string.higher)                                                                     // Fixa så den pekar på strängen i XML filen
            higherView.visibility = View.VISIBLE

        } else {
            string = getString(R.string.error)
        }
        return string
    }


// Övriga funktioner:

    fun random1or2():Int {
        return (1..2).random()
    }

    fun playRightAnswerSound() {
        var mediaPlayer = MediaPlayer.create(this, R.raw.win)
        mediaPlayer.start()
    }




}