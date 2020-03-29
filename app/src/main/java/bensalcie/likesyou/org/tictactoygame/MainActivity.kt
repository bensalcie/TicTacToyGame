package bensalcie.likesyou.org.tictactoygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun buClick(view: View) {
        var selectedBtn=view as Button


        var btnId=0
        when(selectedBtn.id)
        {
            R.id.bu1->btnId=1
            R.id.bu2->btnId=2
            R.id.bu3->btnId=3
            R.id.bu4->btnId=4
            R.id.bu5->btnId=5
            R.id.bu6->btnId=6
            R.id.bu7->btnId=7
            R.id.bu8->btnId=8
            R.id.bu9->btnId=9



        }
        PlayGame(btnId,selectedBtn)
    }
    var activePlayer=1
    var player1=ArrayList<Int>()
    var player2=ArrayList<Int>()


    fun PlayGame(callId:Int,selectedBtn:Button)
    {
        if (activePlayer==1)
        {
            selectedBtn.text="X"
            selectedBtn.setBackgroundResource(R.color.blue)
            player1.add(callId)
            activePlayer=2
            autoPlay()
        }else{
            selectedBtn.text="0"
            selectedBtn.setBackgroundResource(R.color.darkGreen)
            player2.add(callId)
            activePlayer=1

        }
        selectedBtn.isEnabled=false
        checkWinner()

    }

    private fun autoPlay() {

        var emptyCells=ArrayList<Int>()

        for (cellId in 1..9)
        {
            if (!player1.contains(cellId) || player2.contains(cellId))
            {
                emptyCells.add(cellId)
            }
        }


        if (emptyCells.size==0)
        {
            restartGame()
        }

        var r= Random()
        var indexCell=r.nextInt(emptyCells.size)
        var cellId=emptyCells[indexCell]

        var buselectedBtn:Button?
        buselectedBtn=  when(cellId)
        {
            1->bu1
            2->bu2
            3->bu3
            4->bu4
            5->bu5
            6->bu6
            7->bu7
            8->bu8
            9->bu9
            else ->{ bu1}
        }

        PlayGame(cellId,buselectedBtn)

    }

    private fun checkWinner() {

        var winner=-1

        //row 1
        if(player1.contains(1) && player1.contains(2) && player1.contains(3))
        {
            winner=1
        }
        if (player2.contains(1) && player2.contains(2) && player2.contains(3))
        {
            winner=2
        }

        //row 2

        if(player1.contains(4) && player1.contains(5) && player1.contains(6))
        {
            winner=1
        }
        if (player2.contains(4) && player2.contains(5) && player2.contains(6))
        {
            winner=2
        }
        //row 3

        if(player1.contains(7) && player1.contains(8) && player1.contains(9))
        {
            winner=1
        }
        if (player2.contains(7) && player2.contains(8) && player2.contains(9))
        {
            winner=2
        }

        //col 1
        if(player1.contains(1) && player1.contains(4) && player1.contains(7))
        {
            winner=1
        }
        if (player2.contains(1) && player2.contains(4) && player2.contains(7))
        {
            winner=2
        }

        //col 2
        if(player1.contains(2) && player1.contains(5) && player1.contains(8))
        {
            winner=1
        }
        if (player2.contains(2) && player2.contains(5) && player2.contains(8))
        {
            winner=2
        }

        //col 3
        if(player1.contains(3) && player1.contains(6) && player1.contains(9))
        {
            winner=1
        }
        if (player2.contains(3) && player2.contains(6) && player2.contains(9))
        {
            winner=2
        }


        if (winner==1)
        {
            player1Wins+=1
            Toast.makeText(this,"Player one wins",Toast.LENGTH_LONG).show()
            restartGame()
        }
        else if(winner==2){
            player2Wins+=1
            Toast.makeText(this,"Player Two wins",Toast.LENGTH_LONG).show()
            restartGame()


        }

    }


    var player1Wins=0
    var player2Wins=0

    fun restartGame()
    {
        var activePlayer=1
         player1.clear()
       player2.clear()
        var buselectedBtn:Button?

        for (cellIndex in 1..9) {
            buselectedBtn = when (cellIndex) {
                1 -> bu1
                2 -> bu2
                3 -> bu3
                4 -> bu4
                5 -> bu5
                6 -> bu6
                7 -> bu7
                8 -> bu8
                9 -> bu9
                else -> {
                    bu1
                }
            }
            buselectedBtn.text=""
            buselectedBtn.setBackgroundResource(R.color.whileBu)
            buselectedBtn.isEnabled=true

        }
        Toast.makeText(this,"Player One wins: $player1Wins  Player Two wins: $player2Wins",Toast.LENGTH_LONG).show()


    }
}
