package com.example.quotesapp.ui.quotes.firstteam

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quotesapp.R

class FirstTeamFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {
            val intent = Intent(activity, FirstTeamActivity::class.java)
            startActivity(intent)
        }
        return inflater.inflate(R.layout.firstteam_activity, container, false)

    }

}