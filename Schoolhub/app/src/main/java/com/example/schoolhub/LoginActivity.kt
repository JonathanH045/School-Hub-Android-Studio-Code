package com.example.schoolhub

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.ForegroundColorSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.semantics.text
import androidx.core.content.res.ResourcesCompat

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login) // Replace with your login layout file

        val alracc = findViewById<TextView>(R.id.noacc) // Assuming alracc is the ID of your TextView
        val textacc = SpannableString("Sign Up") // Text with hyperlink

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                // Intent to navigate to LoginActivity
                val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
                startActivity(intent)
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = Color.parseColor("#4998FF")
                ds.isUnderlineText = true
            }
        }

// Apply the clickable span to the "Login" part of the text
        textacc.setSpan(clickableSpan, 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        alracc.text = textacc
        alracc.movementMethod = LinkMovementMethod.getInstance()



        val textView = findViewById<TextView>(R.id.textRegister)
        val text = SpannableString("SCHOOL\nHUB")

        text.setSpan(
            ForegroundColorSpan(Color.BLACK),
            0, 6,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        text.setSpan(
            ForegroundColorSpan(Color.YELLOW),
            6, 10,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = text
        val typeface = ResourcesCompat.getFont(this, R.font.inter_extrabold)
        textView.typeface = typeface
        val buttonLogin = findViewById<Button>(R.id.btnLogin) // Replace with your login button ID
        buttonLogin.setOnClickListener {
            val email = findViewById<EditText>(R.id.inputEmail).text.toString()
            val password = findViewById<EditText>(R.id.inputPassword).text.toString()

            // Input Validation
            if (email.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Login Logic (using Shared Preferences)
            val sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)
            val storedEmail = sharedPreferences.getString("email", "")
            val storedPassword = sharedPreferences.getString("password", "")

            if (email == storedEmail && password == storedPassword) {
                // Successful login
                Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()
                // Navigate to the main activity or desired destination
                val intent = Intent(this, MainActivity::class.java) // Replace with your main activity
                startActivity(intent)
            } else {
                // Invalid credentials
                Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
            }
        }
    }
}