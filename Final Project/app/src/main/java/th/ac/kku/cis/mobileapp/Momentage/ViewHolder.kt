package th.ac.kku.cis.mobileapp.Momentage

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    var titleitem: TextView = view.findViewById(R.id.titleitem)
    var imageView: ImageView = view.findViewById(R.id.imageView)
    var cardView: CardView = view.findViewById(R.id.cardView)
}