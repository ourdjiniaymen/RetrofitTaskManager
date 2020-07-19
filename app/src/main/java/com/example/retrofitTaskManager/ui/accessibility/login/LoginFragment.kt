package com.example.retrofitTaskManager.ui.accessibility.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.retrofitTaskManager.R
import com.example.retrofitTaskManager.ui.tasks.MainActivity
import kotlinx.android.synthetic.main.login_fragment.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class LoginFragment : Fragment(), KodeinAware {

    override val kodein by closestKodein()
    private val viewModel: LoginViewModel by instance()

    private lateinit var navController: NavController


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        navController =
            Navigation.findNavController(requireActivity(), R.id.accessibility_nav_fragment)

        sign_up.setOnClickListener {
            navController.navigate(R.id.to_sign_up_fragment_action)
        }

        login.setOnClickListener {
            login(email.text.toString(), password.text.toString())
        }

        //if the password or email are empty disable login button
        watchEditTexts()
    }

    private fun login(email: String, password: String) {
        showProgressBar()
        hideKeyBoard()
        CoroutineScope(Dispatchers.IO).launch {
            viewModel.login(email, password)
        }
        if (viewModel.user == null) {
            Toast.makeText(activity, "Username or password incorrect", Toast.LENGTH_SHORT).show()
            hideProgressBar()
        } else {
            val intent = Intent(activity, MainActivity::class.java)
            startActivity(intent)
            Toast.makeText(activity,"You are connected as ${viewModel.user!!.userName}",Toast.LENGTH_SHORT).show()
            hideProgressBar()
        }
    }

    private fun showProgressBar() {
        loading.visibility = View.VISIBLE
    }
    private fun hideProgressBar(){
        loading.visibility = View.GONE
    }

    private fun hideKeyBoard() {
        val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    private fun watchEditTexts(){
        email.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.trim()?.isNotEmpty()!!){
                    password.addTextChangedListener(object : TextWatcher {
                        override fun afterTextChanged(s: Editable?) {}
                        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                            login.isEnabled = s?.trim()?.isNotEmpty()!!
                        }
                    })
                }else{
                    login.isEnabled = false
                }
            }
        })

    }

}
