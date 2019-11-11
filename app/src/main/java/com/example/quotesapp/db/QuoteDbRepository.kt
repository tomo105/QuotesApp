package com.example.quotesapp.db

import androidx.lifecycle.LiveData

class QuoteDbRepository(private val quoteDbDao: QuoteDbDao) {
    val allAuthor: LiveData<List<QuoteDb>> = quoteDbDao.getAlphabetizedAuthors()
    val allAuthorSize :LiveData<Int> = quoteDbDao.capacity()

    suspend fun insert(quoteDb: QuoteDb) {
        quoteDbDao.insertAll(quoteDb)
    }

//    suspend fun capacity(): Int {
//        return quoteDbDao.capacity()
//    }
}