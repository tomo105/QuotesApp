package com.example.quotesapp.ui.quotes

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.example.quotesapp.R

class NewQuoteActivity : AppCompatActivity() {
    private lateinit var editQuoteView: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_quote)
        editQuoteView = findViewById(R.id.edit_quote)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val replyIntent = Intent()
            if(TextUtils.isEmpty(editQuoteView.text)) {
                setResult(Activity.RESULT_CANCELED, replyIntent)
            }
            else{
                val quote = editQuoteView.text.toString()
                replyIntent.putExtra(EXTRA_REPLY,quote)
                setResult(Activity.RESULT_OK,replyIntent)
            }
        }
    }
    companion object {
        const val EXTRA_REPLY = "com.example.quotelistsql.REPLY"
    }
}
