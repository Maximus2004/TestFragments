package com.example.testfragments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.presentation.blank.ui.BlankFragmentInterface
import com.example.presentation.converter.ui.ConverterFragment
import com.example.presentation.message.ui.MessageFragment
import com.example.presentation.message.ui.MessageFragmentInterface
import com.example.presentation.notes.ui.NotesFragment
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), BlankFragmentInterface, MessageFragmentInterface {

    private val messageFragment: MessageFragment by inject()
    private val converterFragment: ConverterFragment by inject()
    private val notesFragment: NotesFragment by inject()

    private val router = RootActivityRouter(supportFragmentManager = supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onClickedButton() {
        router.pushToMessageFragment(
            fragment = notesFragment.getNotesFragment()
        )
    }

    override fun onClickTranslate(translate: String) {
        router.pushToMessageFragment(
            fragment = converterFragment.getConverterFragment(translate)
        )
    }
}