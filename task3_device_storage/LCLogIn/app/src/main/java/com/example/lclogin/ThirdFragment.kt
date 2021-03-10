package com.example.lclogin

import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.lclogin.db.AppDatabase
import com.example.lclogin.db.UnicornDao
import com.example.lclogin.models.Unicorn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import main.kotlin.generators.UnicornGroupGenerator

class ThirdFragment  : Fragment() {

    var dao : UnicornDao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dao = AppDatabase.createDb(requireContext()).unicornDao()
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_generate).setOnClickListener {
            val ugg =  UnicornGroupGenerator(1, 5)
            val unicornList = ugg.getUnicornGroup()

            GlobalScope.launch(Dispatchers.IO) {

                withContext(Dispatchers.IO) {

                    for (i in 0 until unicornList.size){
                        dao!!.insert(unicornList[i])
                    }
                }
            }
        }

        view.findViewById<Button>(R.id.button_show).setOnClickListener {
            val list = view.findViewById(R.id.list_unicorn) as TextView
            GlobalScope.launch(Dispatchers.IO) {

                withContext(Dispatchers.IO) {
                    val unicornList = dao!!.getUnicorns()
                    var str = ""
                    for (unicorn in unicornList){
                        str += unicorn.toString() + "\n\n"
                    }
                    list.text = str
                }
            }
            list.movementMethod = ScrollingMovementMethod();
        }

        view.findViewById<Button>(R.id.button_clear).setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {

                withContext(Dispatchers.IO) {
                    dao!!.deleteAll()
                }
            }
            val list = view.findViewById(R.id.list_unicorn) as TextView
            list.text = "Здесь будет список с единорогами"
        }
    }
}