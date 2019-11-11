package com.example.quotesapp.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuoteDbDao {

    @Query("SELECT * FROM quotes_db")
    fun getAll(): List<QuoteDb>

    @Query("SELECT * FROM quotes_db ORDER BY author ASC")
    fun getAlphabetizedAuthors(): LiveData<List<QuoteDb>>

    @Insert
    suspend fun insertAll(vararg quoteDb: QuoteDb)

    @Query("DELETE FROM quotes_db")
    suspend fun deleteAll()



    //   @Query("SELECT * FROM todoentity WHERE title LIKE :title")
    //  fun findByTitle(title: String): LiveData<List<TodoEntity>>
    //    Here, you are returning a LiveData Object holding a list of TodoEntries
    //  which you can observer in your activity.

}