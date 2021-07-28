package dev.danielprado.patrimon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import dev.danielprado.patrimon.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit private var viewBinding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}