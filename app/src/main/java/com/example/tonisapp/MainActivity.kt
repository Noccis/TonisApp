package com.example.tonisapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button

open class MainActivity : LvlParentActivity() {             // Lvl ett activity!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        howManyPlayerCards = 2

        // Binder ihop mina view variabler med mina views.
        mainNrView = findViewById(R.id.mainNrView)
        scoreTextView = findViewById(R.id.scoreView)
        hiOrLowTextView = findViewById(R.id.hiLowTextView)
        playerCard1View = findViewById(R.id.playerCard1)
        playerCard2View = findViewById(R.id.playerCard3)
        lowerView = findViewById(R.id.lowerView)
        higherView = findViewById(R.id.higherView)

        hiLowViewsInvisible()

        score = intent.getIntExtra("score", 0)      // Denna är en intent för att ifall man hoppar tillbaka från lvl 2 ska man få med sig score.

        scoreTextView.text = "$score"

        mainNrView.setImageResource(setImage(listOfcards))      //Skapar random siffra/kort i mainNr


        hiOrLowTextView.text =
            hiOrLow()                        //Bestämmer om man ska lägga högre eller lägre.


        randomPlayerCard(listOfcards)       // Genererar rätt och fel svar till random playerCard

        // Ta bort på TORSDAG! Ta även bort dom i XML. Todo

        val lvl2Button = findViewById<Button>(R.id.lvl2Button)
        val lvl3Button = findViewById<Button>(R.id.lvl3Button)
        val winButton = findViewById<Button>(R.id.winButton)


    }


    // Player card funktioner:

    private fun randomPlayerCard(cardList: MutableList<Card>) {

        val placement = random1or2()
        val lowerNr = mainNr - 1     // Skapade dessa variabler då något blev fel när jag skrev main -1
        val higherNr = mainNr + 1

        if (hiOrLow == 1) {

            rightNr = (1..lowerNr).random()
            wrongNr = (higherNr..10).random()

        } else if (hiOrLow == 2) {

            rightNr = (higherNr..10).random()
            wrongNr = (1..lowerNr).random()

        }

        if (placement == 1) {
            playerCard1View.setImageResource(cardList[rightNr - 1].imageId)
            playerCard2View.setImageResource(cardList[wrongNr - 1].imageId)

            rightAnswerCard = 1


        } else if (placement == 2) {
            playerCard1View.setImageResource(cardList[wrongNr - 1].imageId)
            playerCard2View.setImageResource(cardList[rightNr - 1].imageId)

            rightAnswerCard = 2

        }
    }

    // Svar funktioner:

    fun answeringCard1(view: View) {

        if (rightAnswerCard == 1) {
            score++
            scoreTextView.text = "$score"
            playRightAnswerSound()
            allPlayerCardsGone()

            if (score >= 10) {
                startNextLvlActivity()
            } else {
                reload()
            }

        } else if (score > 0) {
            score--
            scoreTextView.text = "$score"

            view.visibility = View.INVISIBLE
        } else {
            view.visibility = View.INVISIBLE
        }


    }

    fun answeringCard2(view: View) {

        if (rightAnswerCard == 2) {
            score++
            scoreTextView.text = "$score"

            playRightAnswerSound()
            allPlayerCardsGone()

            if (score >= 10) {
                startNextLvlActivity()
            } else {
                reload()
            }


        } else if (score > 0) {
            score--
            scoreTextView.text = "$score"

            view.visibility = View.INVISIBLE

        } else {

            view.visibility = View.INVISIBLE
        }


    }


// Övriga funktioner:

    private fun reload() {

        hiLowViewsInvisible()
        allPlayerCardsVisible()

        mainNrView.setImageResource(setImage(listOfcards))

        hiOrLowTextView.text = hiOrLow()

        randomPlayerCard(listOfcards)

    }


    private fun startNextLvlActivity() {

        val intent = Intent(this, LvlTwoActivity::class.java)
        intent.putExtra("score", score)
        startActivity(intent)
    }

    // Funktioner som jag ska ta bort på torsdag! Todo

    fun startLvl2Activity(view: View) {
        val intent = Intent(this, LvlTwoActivity::class.java)
        score = 18
        intent.putExtra("score", score)
        startActivity(intent)

    }

    fun startLvl3Activity(view: View) {

        val intent = Intent(this, LvlThreeActivity::class.java)
        score = 22
        intent.putExtra("score", score)
        startActivity(intent)
    }

    fun startWinActivity(view: View) {

        val intent = Intent(this, WinActivity::class.java)
        startActivity(intent)
    }

}


