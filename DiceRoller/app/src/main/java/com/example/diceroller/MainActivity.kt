/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.diceroller

import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.widget.SwitchCompat
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {

    /**
     * This method is called when the Activity is created.
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find the Button in the layout
        val rollButton: Button = findViewById(R.id.button)
        val switch: SwitchCompat = findViewById(R.id.switch1)
        // Set a click listener on the button to roll the dice when the user taps the button
        rollButton.setOnClickListener {
            rollDice(switch.isChecked)
        }
    }

    /**
     * Roll the dice and update the screen with the result.
     */
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun rollDice(showMessage : Boolean) {
        // Create new Dice object with 6 sides and roll it
        val dice = Dice(6)
        val diceRoll = dice.roll()
        if (showMessage) showMessage (diceRoll, dice.luckyNumber)
        // Update the screen with the dice roll
//        val resultTextView: TextView = findViewById(R.id.textView)
        val diceImage : ImageView = findViewById(R.id.imageView)
        diceImage.setImageDrawable(getDrowableIds().get(diceRoll))
//        resultTextView.text = diceRoll.toString()
    }

    private fun showMessage(diceNumber : Int, luckyNumber : Int){
        val message =
            if (diceNumber == luckyNumber) getString(R.string.congratulation) +" "+ diceNumber else getString(R.string.message) +" "+ diceNumber
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    private fun getDrowableIds(): HashMap<Int, Drawable>{
        val diceDrowable = HashMap<Int, Drawable>()
        getDrawable(R.drawable.dice_1)?.let { diceDrowable.put(1, it) }
        getDrawable(R.drawable.dice_2)?.let { diceDrowable.put(2, it) }
        getDrawable(R.drawable.dice_3)?.let { diceDrowable.put(3, it) }
        getDrawable(R.drawable.dice_4)?.let { diceDrowable.put(4, it) }
        getDrawable(R.drawable.dice_5)?.let { diceDrowable.put(5, it) }
        getDrawable(R.drawable.dice_6)?.let { diceDrowable.put(6, it) }
        return diceDrowable
    }
}

/**
 * Dice with a fixed number of sides.
 */
class Dice(private val numSides: Int) {

    val luckyNumber = numSides/2 + 1

    /**
     * Do a random dice roll and return the result.
     */
    fun roll(): Int {
        return (1..numSides).random()
    }
}