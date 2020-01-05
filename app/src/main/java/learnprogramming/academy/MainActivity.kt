package learnprogramming.academy

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

//private const val TAG = "MyMainActivity"

class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.d(TAG, "onCreate called")

    }

    private inner class DownloadData : AsyncTask<String, Void, String>() {

        private val TAG = "DownloadData"

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

        override fun doInBackground(vararg p0: String?): String {
            return ""
        }

    }
}
