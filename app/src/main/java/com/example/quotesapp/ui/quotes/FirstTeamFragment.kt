package com.example.quotesapp.ui.quotes

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.quotesapp.R
import kotlinx.android.synthetic.main.firstteam_activity.*

class FirstTeamFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let {

           // recyclerview.layoutManager =LinearLayoutManager(context)
           // recyclerview.adapter = context?.let { it1 -> QuotesListAdapter(it1) }
            val intent = Intent(activity,FirstTeamActivity::class.java)
            startActivity(intent)
        }
        return inflater.inflate(R.layout.firstteam_activity, container,false)

    }

}