package com.example.schoolhub

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout

// ... other imports ...

class MainActivity3 : AppCompatActivity() {
    private lateinit var drawerLayout: DrawerLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3) // Set content view first
        enableEdgeToEdge()
        val navview: DrawerLayout = findViewById(R.id.homesc1)
        val imageView3: ImageView = findViewById(R.id.imageView3)
        drawerLayout = findViewById(R.id.homesc1)
        imageView3.setOnClickListener {
            navview.openDrawer(GravityCompat.START)
        }

        val pg1:ImageView = findViewById(R.id.imageView6)
        val pg2:ImageView = findViewById(R.id.imageView4)
        val pg3:ImageView = findViewById(R.id.imageView9)
        pg1.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        pg2.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
        pg3.setOnClickListener {
            val intent = Intent(this, MainActivity4::class.java)
            startActivity(intent)
        }

        val textView = findViewById<TextView>(R.id.text)
        val text = SpannableString("SCHOOLHUB")

        text.setSpan(
            ForegroundColorSpan(Color.BLACK),
            0, 6,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        text.setSpan(
            ForegroundColorSpan(Color.YELLOW),
            6, 9,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        textView.text = text
        val typeface = ResourcesCompat.getFont(this, R.font.inter_extrabold)
        textView.typeface = typeface
    }
    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}