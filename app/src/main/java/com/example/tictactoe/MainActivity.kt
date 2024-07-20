package com.example.tictactoe

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    enum class Turn
    {
        CROSS,
        ZERO
    }
    private var currentTurn = Turn.CROSS
    private var board = mutableListOf<Button>()
    private var asa=0
    private var scorep1=0
    private var scorep2=0

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()


    }
    private fun asawin(bla: CharSequence)
    {
        if(bla=="X" && asa%2==0)
        {
            winmsg("Player 1 wins!")
            scorep1++
        }
        else if(bla=="X" && asa%2==1)
        {
            winmsg("Player 2 wins!")
            scorep2++
        }
        else if(bla=="O" && asa%2==0)
        {
            winmsg("Player 2 wins!")
            scorep2++
        }
        else
        {
            winmsg("Player 1 wins!")
            scorep1++
        }
    }
    private fun win()
    {
        if(binding.button00.text==binding.button01.text && binding.button01.text==binding.button02.text && binding.button00.text!=" ")
        {
            asawin(binding.button02.text)

        }
        else if(binding.button10.text==binding.button11.text && binding.button11.text==binding.button12.text && binding.button12.text!=" ")
        {
            asawin(binding.button12.text)
        }
        else if(binding.button20.text==binding.button21.text && binding.button21.text==binding.button22.text && binding.button22.text!=" ")
        {
            asawin(binding.button21.text)
        }
        else if(binding.button00.text==binding.button10.text && binding.button10.text==binding.button20.text && binding.button20.text!=" ")
        {
            asawin(binding.button00.text)
        }
        else if(binding.button01.text==binding.button11.text && binding.button11.text==binding.button21.text && binding.button21.text!=" ")
        {
            asawin(binding.button01.text)
        }
        else if(binding.button02.text==binding.button12.text && binding.button12.text==binding.button22.text && binding.button22.text!=" ")
        {
            asawin(binding.button02.text)
        }
        else if(binding.button00.text==binding.button11.text && binding.button11.text==binding.button22.text && binding.button00.text!=" ")
        {
            asawin(binding.button00.text)
        }
        else if(binding.button02.text==binding.button11.text && binding.button11.text==binding.button20.text && binding.button02.text!=" ")
        {
            asawin(binding.button02.text)
        }
        else if(fullboard())
        {
            AlertDialog.Builder(this)
                .setTitle("DRAW")
                .setPositiveButton("Ok")
                {_,_ ->
                    reset()
                    setscore()

                }
                .setCancelable(false)
                .show()
        }
    }
    private fun winmsg(title: String)
    {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setPositiveButton("Ok")
            {_,_ ->
                reset()
                setscore()

            }
            .setCancelable(false)
            .show()

    }
    @SuppressLint("SetTextI18n")
    private fun setscore()
    {
        if(asa%2==0)
        {
            binding.p1TV.text = "Player 1: $scorep1 (X)"
            binding.p2TV.text = "Player 2: $scorep2 (O)"
        }
        else if(asa%2==1)
        {
            binding.p1TV.text= "Player 1: $scorep1 (O)"
            binding.p2TV.text= "Player 2: $scorep2 (X)"
        }
    }

    private fun initBoard() {
        board.add(binding.button00)
        board.add(binding.button01)
        board.add(binding.button02)
        board.add(binding.button10)
        board.add(binding.button11)
        board.add(binding.button12)
        board.add(binding.button20)
        board.add(binding.button21)
        board.add(binding.button22)
    }
    fun boardTap(view:View)
    {
        if (view !is Button)
            return
        addToBoard(view)
        win()
    }
    private fun reset()
    {
        asa++
        currentTurn=Turn.CROSS
        setTurn()
        binding.button00.text=" "
        binding.button01.text=" "
        binding.button02.text=" "
        binding.button10.text=" "
        binding.button11.text=" "
        binding.button12.text=" "
        binding.button20.text=" "
        binding.button21.text=" "
        binding.button22.text=" "

    }
    fun resetBoard(view: View)
    {
        reset()
        asa=0
        scorep1=0
        scorep2=0
        setscore()
    }

    private fun addToBoard(button: Button)
    {

        if(button.text!=" ")
            return
        if(currentTurn==Turn.CROSS)
        {
            button.text="X"
            currentTurn=Turn.ZERO
        }
        else if(currentTurn==Turn.ZERO)
        {
            button.text="O"
            currentTurn=Turn.CROSS
        }
        setTurn()
    }

    private fun setTurn() {
        var ttxt=""
        if (currentTurn==Turn.ZERO)
        {
            ttxt="TURN: O"
        }
        if (currentTurn==Turn.CROSS)
        {
            ttxt="TURN: X"
        }
        binding.turnTV.text=ttxt
    }

    private fun fullboard() :Boolean
    {
        for (button in board)
        {
            if(button.text==" ")
            {
                return false
            }
        }
        return true
    }

}