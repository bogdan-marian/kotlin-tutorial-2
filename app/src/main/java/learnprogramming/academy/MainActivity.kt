package learnprogramming.academy

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.content_main.*

private val TAG = "MainActivity"

private val flickrRecyclerViewAdapter = FlickrRecyclerViewAdapter(ArrayList())

class MainActivity : BaseActivity(),
    GetRawData.OnDownloadComplete,
    GetFlickrJsonData.OnDataAvailable,
    RecyclerItemClickListener.OnRecyclerClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate called")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        activateToolbar(false)

        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.addOnItemTouchListener(RecyclerItemClickListener(this, recycler_view, this))
        recycler_view.adapter = flickrRecyclerViewAdapter

        val url = createUri(
            "https://api.flickr.com/services/feeds/photos_public.gne",
            "android,oreo",
            "en-us",
            true
        )
        val getRawData = GetRawData(this)
        getRawData.execute(url)

        Log.d(TAG, "onCreate ends")
    }

    override fun onItemClick(view: View, position: Int) {
        Log.d(TAG, ".onItemClick: starts")
        Toast.makeText(this, "Normal tap at position $position", Toast.LENGTH_SHORT).show()
    }

    override fun onItemLongClick(view: View, position: Int) {
        Log.d(TAG, ".onItemLongClick: starts")
//        Toast.makeText(this, "Long tap on osition $position", Toast.LENGTH_SHORT).show()
        val photo = flickrRecyclerViewAdapter.getPhoto(position)
        if (null != photo) {
            val intent = Intent(this, PhotoDetailsActivity::class.java)
            intent.putExtra(PHOTO_TRANSFER, photo)
            startActivity(intent)
        }
    }

    private fun createUri(
        baseURL: String,
        searchCriteria: String,
        lang: String,
        matchAll: Boolean
    ): String {
        Log.d(TAG, ".createUri starts")

        var uri = Uri.parse(baseURL)
        var builder = uri.buildUpon()
        builder = builder.appendQueryParameter("tags", searchCriteria)
        builder = builder.appendQueryParameter("tagmode", if (matchAll) "ALL" else "ANY")
        builder = builder.appendQueryParameter("format", "json")
        builder = builder.appendQueryParameter("nojsoncallback", "1")
        uri = builder.build()

        return uri.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d(TAG, "onCreateOptionsMenu called")
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }


    override fun onDownloadComplete(data: String, status: DownloadStatus) {
        if (status == DownloadStatus.OK) {
            Log.d(TAG, "onDownloadComplete called")

            val getFlickrJsonData = GetFlickrJsonData(this)
            getFlickrJsonData.execute(data)
        } else {
            // download failed
            Log.d(TAG, "onDownloadComplete failed with status $status. Error message is: $data")
        }
    }

    override fun onDataAvailable(data: List<Photo>) {
        Log.d(TAG, ".onDataAvailable called: ")
        flickrRecyclerViewAdapter.loadNewData(data)
    }

    override fun onError(exception: Exception) {
        Log.d(TAG, ".onError ${exception.message}")
    }
}
