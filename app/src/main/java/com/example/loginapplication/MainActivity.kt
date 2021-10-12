package com.example.loginapplication

import android.annotation.SuppressLint
import android.content.ContextWrapper
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.pixplicity.easyprefs.library.Prefs
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        Prefs.Builder()
            .setContext(this)
            .setMode(ContextWrapper.MODE_PRIVATE)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()

        if (Prefs.getBoolean(PREFS_KEY_IS_LOGIN, false)) {
            openHomePage()
        } else {
            setContentView(R.layout.activity_main)

            setupWidget()


            signUp.setOnClickListener {
                signUp.background = resources.getDrawable(R.drawable.switch_trcks, null)
                signUp.setTextColor(resources.getColor(R.color.textColor, null))
                logIn.background = null
                signUpLayout.visibility = View.VISIBLE
                logInLayout.visibility = View.GONE
                logIn.setTextColor(resources.getColor(R.color.pinkColor, null))


            }
            logIn.setOnClickListener {
                signUp.background = null
                signUp.setTextColor(resources.getColor(R.color.pinkColor, null))
                logIn.background = resources.getDrawable(R.drawable.switch_trcks, null)
                signUpLayout.visibility = View.GONE
                logInLayout.visibility = View.VISIBLE
                logIn.setTextColor(resources.getColor(R.color.textColor, null))


            }

        }
    }


    private fun openHomePage() {
        signIn.setOnClickListener {
            val intent = Intent(this@MainActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun setupWidget() {
        signIn.setOnClickListener {
            validate()
        }
    }

        private fun validate() {
            val username = eMail.text.toString()
            val password = passwords.text.toString()

            if (username.isEmpty() || password.isEmpty()) {
                showToast("Username or Password is Empty")
                return
            }

            if (username == "login@gmail.com" && password == "123456789") {
                Prefs.putBoolean(PREFS_KEY_IS_LOGIN, true)
                Prefs.putString(PREFS_KEY_USERNAME, username)
                openHomePage()
                return
            }
            showToast("Username or Password incorrect")
        }
    }





