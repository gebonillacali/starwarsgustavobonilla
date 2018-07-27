package com.transferwise.gustavobonilla.starwarstransferwisetechtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import com.transferwise.gustavobonilla.starwarstransferwisetechtest.home.view.HomeFragment

class MainActivity: AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    initApplication()
  }

  private fun initApplication() {
    val homeFragment = HomeFragment.newInstance()
    addFragment(homeFragment)
  }

  private fun addFragment(fragment: Fragment) {
    val transaction = supportFragmentManager.beginTransaction()
    transaction.add(R.id.fragmentContainer, fragment)
    transaction.commit()
  }
}
