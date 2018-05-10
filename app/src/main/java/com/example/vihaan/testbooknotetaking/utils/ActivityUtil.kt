package com.example.vihaan.testbooknotetaking.utils

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View

/**
 * Created by vihaan on 3/17/18.
 */

fun AppCompatActivity.showFragment(id: Int, fragment: Fragment) {
    supportFragmentManager.transact {
        replace(id, fragment)
    }
}

private inline fun FragmentManager.transact(action: FragmentTransaction.() -> Unit) {
    beginTransaction().apply {
        action()
    }.commit()
}

//fun AppCompatActivity.setupActionBar(title: String, subtitle: String, action:
//AppCompatActivity.() -> Unit) {
//    val toolbar = findViewById<View>(R.id.toolbar_actionbar) as Toolbar
//    Common.setToolbarBackAndTitles(toolbar, title, subtitle).setOnClickListener {
//        action()
//    }
//    setSupportActionBar(toolbar)
//}