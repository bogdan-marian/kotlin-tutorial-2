package learnprogramming.academy

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var userInput: EditText? = null
    private var button: Button? = null
    private var textView: TextView? = null

    private var numTimesClicked = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        userInput = findViewById(R.id.editText)
        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        textView?.movementMethod = ScrollingMovementMethod()
        textView?.text = ""

        button?.setOnClickListener(object : View.OnClickListener{
            override fun onClick(p0: View?) {
                numTimesClicked += 1
                textView?.append("The button got tapped $numTimesClicked time")
                if (numTimesClicked != 1){
                    textView?.append("s\n")
                }else{
                    textView?.append("\n")
                }
            }

        })
    }
}
