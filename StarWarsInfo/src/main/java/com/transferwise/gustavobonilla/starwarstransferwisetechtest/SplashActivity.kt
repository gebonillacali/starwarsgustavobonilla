/*
 * Copyright (c) 2018 TransferWise Tech Test
 * StarWarsTransferWiseTechTest
 * SplashActivity.kt
 * Author: Gustavo E Bonilla <gebonilla@gmail.com>
 * Date: July 29, 2018
 */

package com.transferwise.gustavobonilla.starwarstransferwisetechtest

import android.animation.ObjectAnimator
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.MediaController
import kotlinx.android.synthetic.main.activity_splash.continueButton
import kotlinx.android.synthetic.main.activity_splash.continueText
import kotlinx.android.synthetic.main.activity_splash.video

class SplashActivity: AppCompatActivity() {

  //region Activity Lifecycle
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_splash)
    playVideo()
    startAnimation()
    setContinueListener()
  }
  //endregion

  //region private
  /**
   * Plays the intro video of the app.
   */
  private fun playVideo() {
    video.setVideoURI(Uri.parse("android.resource://" + packageName + "/" + R.raw.intro))
    video.setMediaController(MediaController(this))
    video.requestFocus()
    video.setOnCompletionListener {
      startAppActivity()
    }
    video.start()
  }

  /**
   * Starts the vanish animation for the text with the continue information.
   */
  private fun startAnimation() {
    val anim = ObjectAnimator.ofFloat(continueText, "alpha", 0.0f)
    anim.duration = 10000
    anim.start()
  }

  /**
   * Sets the listener for the button that allows to skip intro.
   */
  private fun setContinueListener() {
    continueButton.setOnClickListener {
      video.stopPlayback()
      startAppActivity()
    }
  }

  private fun startAppActivity() {
    val intent = Intent(this, MainActivity::class.java)
    startActivity(intent)
    finish()
  }
  //endregion
}
