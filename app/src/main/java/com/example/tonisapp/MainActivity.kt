package com.example.tonisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

open class MainActivity : LvlParentActivity() {             // LVL ett activity!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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

        // Ta bort på TORSDAG! Ta även bort dom i XML. Todo

        val lvl2Button = findViewById<Button>(R.id.lvl2Button)
        val lvl3Button = findViewById<Button>(R.id.lvl3Button)
        val winButton = findViewById<Button>(R.id.winButton)


    }





    // Player card funktioner:


 private fun randomPlayerCard(cardList: MutableList<Card>) {
        val placement = random1or2()

        val lowerNr =
            mainNr - 1     // Skapade dessa variabler då något blev fel när jag skrev mmain -1
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

            rightAnswerImageId =
                cardList[rightNr - 1].imageId       // Sparar imageId för senare använding

            rightAnswerCard = 1


        } else if (placement == 2) {
            playerCard1View.setImageResource(cardList[wrongNr - 1].imageId)
            playerCard2View.setImageResource(cardList[rightNr - 1].imageId)

            rightAnswerImageId =
                cardList[rightNr - 1].imageId       // Sparar imageId för senare använding

            rightAnswerCard = 2

        }
    }

    // Svar funktioner:


    fun answeringCard1(view: View) {

        if (rightAnswerCard == 1) {
            score++
            scoreTextView.text = "$score"
            playRightAnswerSound()

            view.visibility = View.INVISIBLE
            playerCard2View.visibility = View.INVISIBLE

            if (score >= 10) {
                startNextLvlActivity()
            } else {
                reload()
            }
            if (hiOrLow == 1 ) {

                lowerView.setImageResource(rightAnswerImageId)
                lowerView.setBackgroundResource(R.drawable.roundedcorner)

            }
            else if (hiOrLow == 2) {

                higherView.setImageResource(rightAnswerImageId)
                higherView.setBackgroundResource(R.drawable.roundedcorner)
            }

        } else if (score > 0) {
            score--
            scoreTextView.text = "$score"       // Varför uppdateras inte detta automatiskt?

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

            view.visibility = View.GONE
            playerCard1View.visibility = View.GONE

            if (score >= 10) {
                startNextLvlActivity()
            } else {
                reload()
            }


        } else if (score > 0) {
            score--
            scoreTextView.text = "$score"       // Varför uppdateras inte detta automatiskt?

            view.visibility = View.INVISIBLE

        } else {

            view.visibility = View.INVISIBLE
        }


    }


// Övriga funktioner:

    private fun reload() {

        lowerView.visibility = View.GONE
        lowerView.setImageResource(R.drawable.blankbgcolor)
        lowerView.setBackgroundResource(R.drawable.faded)
        higherView.visibility = View.GONE
        higherView.setImageResource(R.drawable.blankbgcolor)
        higherView.setBackgroundResource(R.drawable.faded)

        playerCard1View.visibility = View.VISIBLE
        playerCard2View.visibility = View.VISIBLE

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
        startActivity(intent)
    }

    fun startWinActivity(view: View) {

        val intent = Intent(this, WinActivity::class.java)
        startActivity(intent)
    }

}


