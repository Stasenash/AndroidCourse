package com.example.animeapi

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.animeapi.db.AppDatabase
import com.example.animeapi.db.UserDao
import com.example.animeapi.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    var dao : UserDao? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        dao = AppDatabase.createDb(requireContext()).userDao()
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button_login).setOnClickListener {
            val login = view.findViewById<EditText>(R.id.editLogin).text.toString()
            val password = view.findViewById<EditText>(R.id.editPassword).text.toString()

            GlobalScope.launch(Dispatchers.IO) {
                withContext(Dispatchers.IO) {
                    val userList = dao?.getUserByLogin(login)

                    withContext(Main) {
                        var user : User? = null
                        if (!userList.isNullOrEmpty()){
                            user = userList[0]
                        }

                        if (login == user?.login && password == user?.password) {
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
        }

        view.findViewById<Button>(R.id.button_registration).setOnClickListener {
            val dialogView = LayoutInflater.from(view.context).inflate(R.layout.registration_dialog, null)
            val builder = AlertDialog.Builder(view.context)
                .setView(dialogView)
                .setTitle("Форма регистрации")
            val  alertDialog = builder.show()

            dialogView.findViewById<Button>(R.id.button_reg_confirm).setOnClickListener {
                alertDialog.dismiss()
//                TODO: пароль по хэшу
                val login = dialogView.findViewById<EditText>(R.id.text_login).text.toString()
                val email = dialogView.findViewById<EditText>(R.id.text_email).text.toString()
                val password = dialogView.findViewById<EditText>(R.id.text_password).text.toString()

                GlobalScope.launch(Dispatchers.IO) {
                    withContext(Dispatchers.IO) {
                        dao!!.insert(User(login, email, password))
                    }
                }
            }

            dialogView.findViewById<Button>(R.id.button_close).setOnClickListener {
                alertDialog.dismiss()
            }
        }
    }
}