package com.example.lclogin

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.lclogin.SPService.Companion.APP_PREFERENCES_LOGIN
import com.example.lclogin.SPService.Companion.LOGIN
import com.example.lclogin.SPService.Companion.PASSWORD
import com.example.lclogin.SPService.Companion.mSettings


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sps:SPService = SPService(activity)
        if(sps.contains(APP_PREFERENCES_LOGIN)!!) {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        view.findViewById<Button>(R.id.button_login).setOnClickListener {
            val login = view.findViewById(R.id.editLogin) as EditText
            val password = view.findViewById(R.id.editPassword) as EditText
            if (login.text.toString() == LOGIN && password.text.toString() == PASSWORD) {
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
            } else {
                val builder = AlertDialog.Builder(view.context)
                builder.setTitle("Ошибка входа")
                builder.setMessage("Неверный логин и пароль")
                builder.setPositiveButton("OK") { dialog, id ->
                    // User cancelled the dialog
                }
                builder.show()
            }
        }
    }
}