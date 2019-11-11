package com.example.quotesapp.ui.quotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.db.QuoteDb
import com.example.quotesapp.db.QuoteDbRoomDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    private lateinit var quoteDbViewModel: QuotesDbViewModel
    private val newQuoteActivityRequestedCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)
        // setSupportActionBar(toolbar)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = QuotesListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        //initializeUi()

        quoteDbViewModel = ViewModelProvider(this).get(QuotesDbViewModel::class.java)
        quoteDbViewModel.allQuotes.observe(this, Observer { quotes ->
            //update the coached copy of the quotes in the adapter
            quotes?.let { adapter.setQuotes(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewQuoteActivity::class.java)
            startActivityForResult(intent, newQuoteActivityRequestedCode)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newQuoteActivityRequestedCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewQuoteActivity.EXTRA_REPLY)?.let {
                val quote = QuoteDb(null, it,"sd")
                quoteDbViewModel.insert(quote)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG
            ).show()

        }
    }
}
//    private fun initializeUi() {
//        val factory = InjectionUtils.provideQuotesViewModelFactory()
//        val viewModel = ViewModelProviders.of(this, factory)
//            .get(QuotesViewModel::class.java)
//
//        viewModel.getQuotes().observe(this, Observer { quotes ->
//            val stringBuilder = StringBuilder()
//            quotes.forEach { quote ->
//                stringBuilder.append("$quote\n\n")
//            }
//            textView_quotes.text = stringBuilder.toString()
//        })
//        button_add_quote.setOnClickListener {
//            val quote = Quote(editText_quote.text.toString(), editText_author.text.toString())
//            viewModel.addQuotes(quote)
//            editText_quote.setText("")
//            editText_author.setText("")
//        }
//    }

