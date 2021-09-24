package com.example.tonisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class LvlTwoActivity : AppCompatActivity() {






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
        setContentView(R.layout.activity_lvl_two)

        // Hämtar score från förra aktiviteten.
        score = intent.getIntExtra("score", 0)

        // Binder ihop mina view variabler med mina views.
        mainNrView = findViewById(R.id.mainNrView)
        scoreTextView = findViewById(R.id.scoreView)
        hiOrLowTextView = findViewById(R.id.hiLowTextView)
        playerCard1View = findViewById(R.id.playerCard1)
        playerCard2View = findViewById(R.id.playerCard2)
        playerCard3View = findViewById(R.id.playerCard3)
        lowerView = findViewById(R.id.lowerView)
        higherView = findViewById(R.id.higherView)

        lowerView.visibility = View.GONE
        higherView.visibility = View.GONE


        scoreTextView.text = "$score"

        mainNrView.setImageResource(setImage(listOfcards))      //Skapar random siffra/kort i mainNr


        hiOrLowTextView.text =
            hiOrLow()                        //Bestämmer om man ska lägga högre eller lägre.



        // Genererar rätt och fel svar till random playerCard
        randomPlayerCard(listOfcards)


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

            string =
                "Lägg lägre än"                                                                    // Fixa så den pekar på strängen i XML filen
            lowerView.visibility = View.VISIBLE
        } else if (hiOrLow == 2) {
            string =
                "Lägg högre än"                                                                    // Fixa så den pekar på strängen i XML filen
            higherView.visibility = View.VISIBLE
        } else {
            string = "Error"
        }
        return string
    }

    // Player card funktioner:


    fun randomPlayerCard(cardList: MutableList<Card>) {
        val placement = (1..3).random()

        val lowerNr =
            mainNr - 1     // Skapade dessa variabler då något blev fel när jag skrev mmain -1
        val higherNr = mainNr + 1

        if (hiOrLow == 1) {

            rightNr = (1..lowerNr).random()
            wrongNr = (higherNr..10).random()
            wrongNr2 = (higherNr..10).random()

            if (mainNr <9) {        // För att appen inte ska krasha om wrong nr bara kan vara en siffra.

            while (wrongNr == wrongNr2) {       // ser till att dom fel svaren inte är samma.

                if (wrongNr == wrongNr2) {

                wrongNr2 = (higherNr..10).random()
                }
            }
            }

        } else if (hiOrLow == 2) {

            rightNr = (higherNr..10).random()
            wrongNr = (1..lowerNr).random()
            wrongNr2 = (1..lowerNr).random()

            if (mainNr >2) {        // För att appen inte ska krasha om wrong nr bara kan vara en siffra.

                while (wrongNr == wrongNr2) {       // ser till att dom fel svaren inte är samma.
                    if (wrongNr == wrongNr2) {

                        wrongNr2 = (1..lowerNr).random()
                    }


                }
            }

        }

        if (placement == 1) {
            playerCard1View.setImageResource(cardList[rightNr - 1].imageId)
            playerCard2View.setImageResource(cardList[wrongNr - 1].imageId)
            playerCard3View.setImageResource(cardList[wrongNr2 - 1].imageId)

            rightAnswerImageId =
                cardList[rightNr - 1].imageId       // Sparar imageId för senare använding

            rightAnswerCard = 1


        } else if (placement == 2) {
            playerCard1View.setImageResource(cardList[wrongNr - 1].imageId)
            playerCard2View.setImageResource(cardList[rightNr - 1].imageId)
            playerCard3View.setImageResource(cardList[wrongNr2 - 1].imageId)

            rightAnswerImageId =
                cardList[rightNr - 1].imageId       // Sparar imageId för senare använding

            rightAnswerCard = 2

        }
        else if (placement == 3) {
            playerCard1View.setImageResource(cardList[wrongNr - 1].imageId)
            playerCard2View.setImageResource(cardList[wrongNr2 - 1].imageId)
            playerCard3View.setImageResource(cardList[rightNr - 1].imageId)

            rightAnswerImageId =
                cardList[rightNr - 1].imageId       // Sparar imageId för senare använding

            rightAnswerCard = 3
        }
    }

    // Svar funktioner:

    fun answeringCard1(view: View) {

        if (rightAnswerCard == 1) {
            score++
            scoreTextView.text = "$score"

            view.visibility = View.INVISIBLE
            playerCard2View.visibility = View.INVISIBLE
            playerCard3View.visibility = View.INVISIBLE

            if (score >= 20) {
                startNextLvlActivity()
            }
            else {
                reload()
            }


          /*
           Ta upp detta med delay om tid finns.
           if (hiOrLow == 1) {

                lowerView.setImageResource(rightAnswerImageId)
                lowerView.setBackgroundResource(R.drawable.roundedcorner)

            } else if (hiOrLow == 2) {

                higherView.setImageResource(rightAnswerImageId)
                higherView.setBackgroundResource(R.drawable.roundedcorner)
            }

           */



        } else if (score > 7) {
            score--
            scoreTextView.text = "$score"       // Varför uppdateras inte detta automatiskt?

            view.visibility = View.INVISIBLE
        }
        else if (score <= 7) {

            returnToMainActivity()
        }
        else {
            view.visibility = View.INVISIBLE
        }


    }

    fun answeringCard2(view: View) {


        if (rightAnswerCard == 2) {
            score++
            scoreTextView.text = "$score"

            view.visibility = View.GONE
            playerCard1View.visibility = View.GONE
            playerCard3View.visibility = View.GONE

            if (score >= 20) {
                startNextLvlActivity()
            }
            else {
                reload()
            }


            /*
            if (hiOrLow == 1) {

                lowerView.setImageResource(rightAnswerImageId)
                lowerView.setBackgroundResource(R.drawable.roundedcorner)

            } else if (hiOrLow == 2) {

                higherView.setImageResource(rightAnswerImageId)
                higherView.setBackgroundResource(R.drawable.roundedcorner)

            }

             */



        } else if (score > 7) {
            score--
            scoreTextView.text = "$score"       // Varför uppdateras inte detta automatiskt?

            view.visibility = View.INVISIBLE
        }
        else if (score <= 7) {

            returnToMainActivity()


        } else {

            view.visibility = View.INVISIBLE
        }


    }

    fun answeringCard3(view: View) {


        if (rightAnswerCard == 3) {
            score++
            scoreTextView.text = "$score"

            view.visibility = View.GONE
            playerCard1View.visibility = View.GONE
            playerCard2View.visibility = View.GONE

            if (score >= 20) {
                startNextLvlActivity()
            }
            else {
                reload()
            }


            /*
            if (hiOrLow == 1) {

                lowerView.setImageResource(rightAnswerImageId)
                lowerView.setBackgroundResource(R.drawable.roundedcorner)

            } else if (hiOrLow == 2) {

                higherView.setImageResource(rightAnswerImageId)
                higherView.setBackgroundResource(R.drawable.roundedcorner)

            }

             */



        } else if (score > 7) {
            score--
            scoreTextView.text = "$score"       // Varför uppdateras inte detta automatiskt?

            view.visibility = View.INVISIBLE
        }
        else if (score <= 7) {

            score --
            returnToMainActivity()
        }
        else {

            view.visibility = View.INVISIBLE
        }


    }

    // Övriga funktioner:

    fun random1or2(): Int {
        return (1..2).random()
    }

    fun reload() {

        lowerView.visibility = View.GONE
        lowerView.setImageResource(R.drawable.blankbgcolor)
        lowerView.setBackgroundResource(R.drawable.faded)
        higherView.visibility = View.GONE
        higherView.setImageResource(R.drawable.blankbgcolor)
        higherView.setBackgroundResource(R.drawable.faded)

        playerCard1View.visibility = View.VISIBLE
        playerCard2View.visibility = View.VISIBLE
        playerCard3View.visibility = View.VISIBLE

        mainNrView.setImageResource(setImage(listOfcards))

        hiOrLowTextView.text = hiOrLow()

        randomPlayerCard(listOfcards)

    }

    private fun startNextLvlActivity() {

        val intent = Intent(this, LvlThreeActivity::class.java)
        intent.putExtra("score", score)
        startActivity(intent)

        Log.d("nummer", "Nu startas nästa aktivitet.")
    }

    private fun returnToMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("score", score)
        startActivity(intent)
    }
}