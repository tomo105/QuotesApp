package com.example.quotesapp.ui.quotes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log.d
import android.view.MenuItem
import androidx.core.view.GravityCompat
import com.example.quotesapp.R
import com.example.quotesapp.ui.quotes.firstteam.FirstTeamFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_main.*

class MainActivity : AppCompatActivity() {
  //  private lateinit var quoteDbViewModel: QuotesDbViewModel
   // private val newQuoteActivityRequestedCode = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbarFirstTeam)

        supportFragmentManager.beginTransaction()
            .replace(R.id.frameLayout, MainFragment())
            .commit()

        navigationView.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.actionHome -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout, MainFragment())
                        .commit()
                    d("tomek", "pressed action home")

                }
                R.id.actionFirstTeam -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frameLayout,
                            FirstTeamFragment()
                        )
                        .commit()
                    d("tomek", "pressed action home")
                }
            }
            it.isChecked = true
            drawerLayout.closeDrawers()
            true
        }

//        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
//        val adapter = QuotesListAdapter(this@MainActivity)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(this)
//
//        quoteDbViewModel = ViewModelProvider(this).get(QuotesDbViewModel::class.java)
//        quoteDbViewModel.allQuotes.observe(this, Observer { quotes ->
//            //update the coached copy of the quotes in the adapter
//            quotes?.let { adapter.setQuotes(it) }  //!!!!!!!!!!!!!!!!!!!!!!!!!!
//
//        })
//        quoteDbViewModel.capacityAuthors.observe(this, Observer { size ->
//            textNumber.text = size.toString()
//        })
//
//        //gdy dodoaje nowy wpis
//        val fab = findViewById<FloatingActionButton>(R.id.fab)
//        fab.setOnClickListener {
//            val intent = Intent(this@MainActivity, NewQuoteActivity::class.java)
//            startActivityForResult(intent, newQuoteActivityRequestedCode)
//        }

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp)
        }

    }

    //--------------------------------------


//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == newQuoteActivityRequestedCode && resultCode == Activity.RESULT_OK) {
//            data?.getStringExtra(NewQuoteActivity.EXTRA_REPLY)?.let {
//                val quote = QuoteDb(null, it, "sd")
//                quoteDbViewModel.insert(quote)
//            }
////        } else if (requestCode == newQuoteActivityRequestedCode2 && resultCode == Activity.RESULT_OK) {
////            data?.getStringExtra(NewQuoteActivity.EXTRA_REPLY)?.let {
////                val quote = QuoteDb(null, it, "sd")
////                quoteDbViewModel.insert(quote)
////            }
//        } else {
//            Toast.makeText(
//                applicationContext,
//                R.string.empty_not_saved,
//                Toast.LENGTH_LONG
//            ).show()
//
//        }
//    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        drawerLayout.openDrawer(GravityCompat.START)
        return true
    }
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId == R.id.actionCard) {
//            startActivity(Intent(this, CartActivity::class.java))
//            return true
//        }
//        drawerLayout.openDrawer(GravityCompat.START)
//        return true
//    }
}
