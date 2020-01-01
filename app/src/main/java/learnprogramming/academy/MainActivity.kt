package learnprogramming.academy

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

private val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private var textView: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userInput: EditText = findViewById(R.id.editText)
        val button: Button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        textView?.movementMethod = ScrollingMovementMethod()
        textView?.text = ""
        userInput.setText("")

        button.setOnClickListener {
            Log.d(TAG, "onClick: called")
            textView?.append(userInput.text.toString() + "\n")
            userInput.setText("")
        }
    }
}
