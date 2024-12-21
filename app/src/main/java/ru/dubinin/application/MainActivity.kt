package ru.dubinin.application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.dubinin.application.fragment.enter.EnterFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        if (savedInstanceState == null) {
            val enter = EnterFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, enter)
                .commit()
        }
    }
}