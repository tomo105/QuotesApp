package com.example.quotesapp.db

import androidx.lifecycle.LiveData

class QuoteDbRepository(private val quoteDbDao: QuoteDbDao) {
    val allAuthor: LiveData<List<QuoteDb>> = quoteDbDao.getAlphabetizedAuthors()

    suspend fun insert(quoteDb: QuoteDb) {
        quoteDbDao.insertAll(quoteDb)
    }
}