package com.example.quotesapp.ui.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quotesapp.R
import com.example.quotesapp.data.Quote
import com.example.quotesapp.utilities.InjectionUtils
import kotlinx.android.synthetic.main.activity_quotes.*
import java.lang.StringBuilder

class QuotesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quotes)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = QuotesListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        //initializeUi()


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
}
