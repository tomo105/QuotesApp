package com.example.quotesapp.ui.quotes.firstteam

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.db.QuoteDb
import com.example.quotesapp.ui.quotes.MainActivity
import com.example.quotesapp.ui.quotes.NewQuoteActivity
import com.example.quotesapp.ui.quotes.QuotesDbViewModel
import com.example.quotesapp.ui.quotes.QuotesListAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.firstteam_activity.*
import kotlinx.android.synthetic.main.fragment_main.*

class FirstTeamActivity : AppCompatActivity() {

    private lateinit var quoteDbViewModel: QuotesDbViewModel
    private val newQuoteActivityRequestedCode = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.firstteam_activity)
        setSupportActionBar(toolbarFirstTeam)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = QuotesListAdapter(this)

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        quoteDbViewModel = ViewModelProvider(this).get(QuotesDbViewModel::class.java)
        quoteDbViewModel.allQuotes.observe(this, Observer { quotes ->
            //update the coached copy of the quotes in the adapter
            quotes?.let { adapter.setQuotes(it) }  //!!!!!!!!!!!!!!!!!!!!!!!!!!

        })
//        quoteDbViewModel.capacityAuthors.observe(this, Observer { size ->
////            textNumber.text = size.toString()
////        }) jak wyswietlalem ilosc w druzynie to to bylo

        //gdy dodoaje nowy wpis
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            d("tomek", "clicked a fab")
            val intent = Intent(this, NewQuoteActivity::class.java)
            startActivityForResult(intent, newQuoteActivityRequestedCode)
        }
            //todo remove one of the rider
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newQuoteActivityRequestedCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewQuoteActivity.EXTRA_REPLY)?.let {
                val quote = QuoteDb(null, it, "sd")
                quoteDbViewModel.insert(quote)

                d("tomek", "OKKK")
                Toast.makeText(
                    applicationContext,
                    R.string.app_name,
                    Toast.LENGTH_LONG
                ).show()
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

    override fun onBackPressed() {

        d("tomek", "pressed back ")
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

}