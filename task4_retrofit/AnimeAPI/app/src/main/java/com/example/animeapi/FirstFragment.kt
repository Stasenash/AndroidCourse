package com.example.animeapi

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        GlobalScope.launch(Dispatchers.IO) {
            val response = ApiService.instance().getAnimeTitle("1")

            val listResponse = ApiService.instance().getListAnimeTitlesByPopularity()

            Log.d("TEST_RESP", response.body().toString())



            withContext(Dispatchers.Main) {
                val anime = listResponse.body()?.animeTitleList!!

                val rv = view?.findViewById<RecyclerView>(R.id.rec_view)

                rv?.layoutManager = LinearLayoutManager(view?.context)
                val adapter = AnimeTitleAdapter()
                rv?.adapter = adapter

                adapter.update(anime)
            }
        }
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.changeButton).setOnClickListener {
            val textView = view.findViewById(R.id.textToBeChanged) as TextView
            val textEdit = view.findViewById(R.id.textInput) as EditText
            textView.text = textEdit.text
        }
    }
}