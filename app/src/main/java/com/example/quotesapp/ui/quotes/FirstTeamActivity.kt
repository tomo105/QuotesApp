package com.example.quotesapp.ui.quotes

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.db.QuoteDb
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.main.*

class FirstTeamActivity : AppCompatActivity() {

    private lateinit var quoteDbViewModel: QuotesDbViewModel
    private val newQuoteActivityRequestedCode = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = QuotesListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        quoteDbViewModel = ViewModelProvider(this).get(QuotesDbViewModel::class.java)
        quoteDbViewModel.allQuotes.observe(this, Observer { quotes ->
            //update the coached copy of the quotes in the adapter
            quotes?.let { adapter.setQuotes(it) }  //!!!!!!!!!!!!!!!!!!!!!!!!!!

        })
        quoteDbViewModel.capacityAuthors.observe(this, Observer { size ->
            textNumber.text = size.toString()
        })

        //gdy dodoaje nowy wpis
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, NewQuoteActivity::class.java)
            startActivityForResult(intent, newQuoteActivityRequestedCode)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newQuoteActivityRequestedCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewQuoteActivity.EXTRA_REPLY)?.let {
                val quote = QuoteDb(null, it, "sd")
                quoteDbViewModel.insert(quote)
            }
//        } else if (requestCode == newQuoteActivityRequestedCode2 && resultCode == Activity.RESULT_OK) {
//            data?.getStringExtra(NewQuoteActivity.EXTRA_REPLY)?.let {
//                val quote = QuoteDb(null, it, "sd")
//                quoteDbViewModel.insert(quote)
//            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()

        }
    }
}