package com.example.landscapeandsavestates

import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    // lateinit on variables so they can be used in savedStates
    lateinit var btn1: Button
    lateinit var enterTxt: EditText

    lateinit var clickText: TextView
    lateinit var counter: TextView
    lateinit var encourage: TextView
    lateinit var btn2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // linking button and editText box
        btn1 = findViewById(R.id.btnMode)
        enterTxt = findViewById(R.id.edtTxtStuff)

        // linking textViews and button
        clickText = findViewById(R.id.tvText)
        counter = findViewById(R.id.txtCounter)
        encourage = findViewById(R.id.tvEncourage)
        btn2 = findViewById(R.id.btnClickIt)

        /*
        when btn1 is clicked it checks if there is anything entered in the edit text box
        then pops up a toast message with either what was entered or tells them nothing was entered
         */
        btn1.setOnClickListener {
            if(enterTxt.text.toString() == "") {
                Toast.makeText(this@MainActivity,
                    "You didn't enter nothin",
                    Toast.LENGTH_SHORT).show()
            }else {
                Toast.makeText(this@MainActivity,
                    enterTxt.text.toString(),
                    Toast.LENGTH_SHORT).show()
            }
        }

        /*
        when btn 2 is clicked it increments a counter which is displayed on the screen as well as
        swapping out the get clickin text for a different message as well as changing the message
        below the counter depending on the number of times the button has been clicked
         */
        var count = 0
        counter.setText(count.toString())

        btn2.setOnClickListener{
            // this bit makes sure the count doesn't reset when you transition from portrait to landscape
            count = counter.text.toString().toInt()
            // changing the text in the top textview
            clickText.setText("You Clicked it")
            // increment the count
            count += 1
            counter.setText(count.toString())
            // change the bottom text depending on how many times the button has been clicked
            when (count){
                0 -> encourage.setText("You gonna click it or what?")
                in 1..5 -> encourage.setText("Clickity Click!")
                in 6..10 -> encourage.setText("Click away!")
                in 11 ..20-> encourage.setText("You're a clickin fool!")
                in 21..30 -> encourage.setText("Ok, let's calm down.")
                in 31..50 -> encourage.setText("Still going huh?")
                in 51..100 ->encourage.setText("That's probably enough.")
                else -> encourage.setText("Seriously...Stop!")
            }
        }

    }
    // save all our changed text
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("entered_text", enterTxt.text.toString())
        outState.putString("clicked_text", clickText.text.toString())
        outState.putString("counter", counter.text.toString())
        outState.putString("encouragement", encourage.text.toString())
    }
    // set text boxes to their saved states
    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        enterTxt.setText(savedInstanceState.getString("entered_text"))
        clickText.setText(savedInstanceState.getString("clicked_text"))
        counter.setText(savedInstanceState.getString("counter"))
        encourage.setText(savedInstanceState.getString("encouragement"))
    }

}