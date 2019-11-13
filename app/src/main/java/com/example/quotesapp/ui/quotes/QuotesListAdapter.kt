package com.example.quotesapp.ui.quotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.db.QuoteDb

class QuotesListAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<QuotesListAdapter.QuotesDbViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var quotes = emptyList<QuoteDb>() // Cached copy of words !!!!!!!!!!!!!!

    inner class QuotesDbViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val quotesItemView: TextView = itemView.findViewById(R.id.textView)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): QuotesDbViewHolder {
        val itemView = inflater.inflate(R.layout.recyclerview_item, parent, false)

        return QuotesDbViewHolder(itemView)
    }

    override fun getItemCount(): Int = quotes.size


    override fun onBindViewHolder(holder: QuotesDbViewHolder, position: Int) {
        val current = quotes[position]
        holder.quotesItemView.text = current.quote
    }

    internal fun setQuotes(quotes: List<QuoteDb>) {
        this.quotes = quotes
        notifyDataSetChanged()
    }
//    internal fun setSize() {
//        notifyDataSetChanged()
//    }
}