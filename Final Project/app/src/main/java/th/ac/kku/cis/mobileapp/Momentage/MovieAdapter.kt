package th.ac.kku.cis.mobileapp.Momentage

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MovieAdapter (val movielist: List<Model>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.itemvertical, parent,false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val dataModel = movielist[position]
        holder.titleitem.text = dataModel.title
        Picasso.get().load(dataModel.image)
            .error(R.drawable.placeholder)
            .placeholder(R.drawable.placeholder)
            .into(holder.imageView)
        holder.cardView.setOnClickListener(View.OnClickListener { view ->
            val readActivity = Intent(view.context,DetailActivity::class.java)
            readActivity.putExtra("key", dataModel.key)
            view.context.startActivity(readActivity)
            Log.d("Application", dataModel.title.toString())
        })
    }

    override fun getItemCount(): Int {
        return movielist.size
    }

}