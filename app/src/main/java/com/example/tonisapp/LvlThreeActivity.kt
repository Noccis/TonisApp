package com.example.tonisapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView

class LvlThreeActivity :  LvlParentActivity() {

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
        setContentView(R.layout.activity_lvl_three)

        score = intent.getIntExtra("score", 0)

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

        mainNrView.setImageResource(setImage(listOfcards))

        hiOrLowTextView.text = hiOrLow()

        randomPlayerCard(listOfcards)
    }


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


    fun randomPlayerCard(cardList: MutableList<Card>) {
        val placement = (1..3).random()

        val lowerNr =
            mainNr - 1
        val higherNr = mainNr + 1

        if (hiOrLow == 1) {

            rightNr = (1..lowerNr).random()
            wrongNr = (higherNr..19).random()
            wrongNr2 = (higherNr..19).random()

            if (mainNr < 19) {

                while (wrongNr == wrongNr2) {

                    if (wrongNr == wrongNr2) {

                        wrongNr2 = (higherNr..19).random()
                    }
                }
            }

        } else if (hiOrLow == 2) {

            rightNr = (higherNr..20).random()
            wrongNr = (1..lowerNr).random()
            wrongNr2 = (1..lowerNr).random()

            if (mainNr > 2) {

                while (wrongNr == wrongNr2) {
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
                cardList[rightNr - 1].imageId

            rightAnswerCard = 1


        } else if (placement == 2) {
            playerCard1View.setImageResource(cardList[wrongNr - 1].imageId)
            playerCard2View.setImageResource(cardList[rightNr - 1].imageId)
            playerCard3View.setImageResource(cardList[wrongNr2 - 1].imageId)

            rightAnswerImageId =
                cardList[rightNr - 1].imageId

            rightAnswerCard = 2

        } else if (placement == 3) {
            playerCard1View.setImageResource(cardList[wrongNr - 1].imageId)
            playerCard2View.setImageResource(cardList[wrongNr2 - 1].imageId)
            playerCard3View.setImageResource(cardList[rightNr - 1].imageId)

            rightAnswerImageId =
                cardList[rightNr - 1].imageId

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

            if (score >= 40) {
                startNextLvlActivity()
            } else {
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


        } else if (score > 17) {
            score--
            scoreTextView.text = "$score"       // Varför uppdateras inte detta automatiskt?

            view.visibility = View.INVISIBLE
        } else if (score <= 17) {

            score--
            returnToLvl2()
        } else {
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

            if (score >= 40) {
                startNextLvlActivity()
            } else {
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


        } else if (score > 17) {
            score--
            scoreTextView.text = "$score"       // Varför uppdateras inte detta automatiskt?

            view.visibility = View.INVISIBLE
        } else if (score <= 17) {

            score--
            returnToLvl2()


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

            if (score >= 40) {
                startNextLvlActivity()
            } else {
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


        } else if (score > 17) {
            score--
            scoreTextView.text = "$score"       // Varför uppdateras inte detta automatiskt?

            view.visibility = View.INVISIBLE
        } else if (score <= 17) {

            score--
            returnToLvl2()
        } else {

            view.visibility = View.INVISIBLE
        }


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

        val intent = Intent(this, WinActivity::class.java)
        startActivity(intent)
    }


    private fun returnToLvl2() {
        val intent = Intent(this, LvlTwoActivity::class.java)
        intent.putExtra("score", score)
        startActivity(intent)
    }
}