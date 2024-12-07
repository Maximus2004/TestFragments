package com.example.testfragments

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.presentation.converter.ui.ConverterFragment
import com.example.presentation.creation.ui.CategoryCreationFragment
import com.example.presentation.creation.ui.CategoryCreationInterface
import com.example.presentation.creation.ui.NoteCreationFragment
import com.example.presentation.creation.ui.NoteCreationInterface
import com.example.presentation.main.ui.MainScreenFragment
import com.example.presentation.main.ui.MainScreenInterface
import com.example.presentation.message.ui.MessageFragmentInterface
import org.koin.android.ext.android.inject

class MainActivity :
    AppCompatActivity(),
    MainScreenInterface,
    MessageFragmentInterface,
    NoteCreationInterface,
    CategoryCreationInterface {

    private val converterFragment: ConverterFragment by inject()
    private val mainScreenFragment: MainScreenFragment by inject()
    private val noteCreationFragment: NoteCreationFragment by inject()
    private val categoryCreationFragment: CategoryCreationFragment by inject()

    private val router = RootActivityRouter(supportFragmentManager = supportFragmentManager)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val statusBars = insets.getInsets(WindowInsetsCompat.Type.statusBars())
            v.setPadding(statusBars.left, statusBars.top, statusBars.right, statusBars.bottom)
            insets
        }
    }

    override fun onClickTranslate(translate: String) {
        router.pushToFragment(
            fragment = converterFragment.getConverterFragment(translate)
        )
    }

    override fun toNoteCreation() {
        router.pushToFragment(
            fragment = noteCreationFragment.getNoteCreationFragment()
        )
    }

    override fun onClickReadyButton() {
        router.pushToFragment(
            fragment = mainScreenFragment.getMainConverterFragment()
        )
    }

    override fun onClickCategoryCreationButton() {
        router.pushToFragment(
            fragment = categoryCreationFragment.getCategoryCreationFragment()
        )
    }

    override fun toBackFragment() {
        router.toBackFragment()
    }
}