package com.example.testfragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

class RootActivityRouter(
    private val supportFragmentManager: FragmentManager,
    private val containerId: Int = R.id.fragmentContainerView
) {
    fun pushToFragment(
        fragment: Fragment,
        tag: String? = null
    ) {
        if (supportFragmentManager.findFragmentById(containerId)?.javaClass != fragment.javaClass) {
            supportFragmentManager.beginTransaction().apply {
                setReorderingAllowed(true)
                replace(containerId, fragment, tag)
                addToBackStack(null)
                commitAllowingStateLoss()
            }
        }
    }

    fun toBackFragment() {
        supportFragmentManager.popBackStack()
    }
}