package com.example.quotesapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [QuoteDb::class], version = 1)

abstract class QuoteDbRoomDatabase : RoomDatabase() {

    //  You make database provides its DAOs by creating an abstract "getter" method for each @Dao.
    abstract fun quoteDbDao(): QuoteDbDao

    private class QuoteDbDatabaseCallback(private val scope: CoroutineScope) :
        RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.quoteDbDao())
                }
            }
        }

        suspend fun populateDatabase(quoteDbDao: QuoteDbDao) {
           quoteDbDao.deleteAll()// Delete all content here.


            // Add sample words.
//            var quote = QuoteDb(null, "ala ", "kot")
//            quoteDbDao.insertAll(quote)
//            quote = QuoteDb(null, "call me ", "maybe")
//            quoteDbDao.insertAll(quote)

            //adding data do it from repository !!!
        }
    }

    // Singleton prevents multiple instances of database opening at the
    // same time.
    companion object {
        @Volatile
        private var INSTANCE: QuoteDbRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope)
                : QuoteDbRoomDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuoteDbRoomDatabase::class.java,
                    "quotes_db"
                )
                    .addCallback(QuoteDbDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }

}