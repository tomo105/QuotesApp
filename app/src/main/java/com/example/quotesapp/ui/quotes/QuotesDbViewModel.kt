package com.example.quotesapp.ui.quotes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.db.QuoteDb
import com.example.quotesapp.db.QuoteDbRepository
import com.example.quotesapp.db.QuoteDbRoomDatabase
import kotlinx.coroutines.launch

class QuotesDbViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: QuoteDbRepository  // hold a reference to the repository.

    val allQuotes: LiveData<List<QuoteDb>>

    init {
        val quotesDbDao = QuoteDbRoomDatabase.getDatabase(application,viewModelScope).quoteDbDao()//reference to the WordDao from the WordRoomDatabase
        repository = QuoteDbRepository(quotesDbDao)
        allQuotes = repository.allAuthor
    }

    fun insert(quoteDb: QuoteDb) = viewModelScope.launch {
        repository.insert(quoteDb)
    }
    //fun capacity()

}

//--------------------------------------IMPORTANT---------------------------------------------

//wrapper insert() method that calls the Repository's insert() method. In this way,
// the implementation of insert() is encapsulated from the UI.
// We don't want insert to block the main thread, so we're launching a new coroutine
// and calling the repository's insert, which is a suspend function.
// As mentioned, ViewModels have a coroutine scope based on their life cycle called viewModelScope,
// which we use here.