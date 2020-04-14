package xyz.mcomella.searchsuggestions

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: RvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = RvAdapter().also {
                this@MainActivity.adapter = it
            }
        }

        text.setOnClickListener {
            adapter.count += 1
            adapter.notifyItemRangeChanged(0, 3)
        }
    }
}

class RvAdapter : RecyclerView.Adapter<RvViewHolder>() {

    var count = 1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        Log.e("lol", "onCreate")
        val view = LayoutInflater.from(parent.context).inflate(R.layout.suggestion, parent, false)
        return RvViewHolder(view)
    }

    override fun getItemCount(): Int = 3

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        Log.e("lol", "onBind $position")
        holder.textView.text = "g".repeat(count * (position + 1))
    }
}

class RvViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textView: TextView = itemView.findViewById(R.id.text)
}
