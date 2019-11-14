package com.example.quotesapp.ui.quotes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.quotesapp.R
import kotlinx.android.synthetic.main.start_main.view.*

class MainFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.start_main, container, false)
        root.buttonfirstteam.setOnClickListener {
            activity?.let {

                val intent = Intent(activity, FirstTeamActivity::class.java)
                startActivity(intent)
            }
        }


        return root
    }

//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//    }
}