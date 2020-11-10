package fr.aylan.provider

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView



class FAQAdapter(private val myDataset: Array<FAQQuestion>, private val context: Context) :
    RecyclerView.Adapter<FAQAdapter.MyViewHolder>() {

    class MyViewHolder(textView: View) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.simple_list_item_1, parent, false) as View

        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.findViewById<TextView>(R.id.tvQuestion).text = myDataset[position].question
        holder.itemView.findViewById<TextView>(R.id.tvAnswer).text = myDataset[position].answer
    }

    override fun getItemCount() = myDataset.size
}