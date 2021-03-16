package com.example.animeapi

import android.app.AlertDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

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
        return inflater.inflate(R.layout.fragment_second, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_logout).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
}