package com.raywenderlich.android.raytv.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raywenderlich.android.raytv.R
import com.raywenderlich.android.raytv.ui.theme.RayTVTheme

@Composable
fun Splash(modifier: Modifier = Modifier) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(color = colorResource(R.color.teal_700))
  ) {
    Image(
      painter = painterResource(id = R.mipmap.ic_launcher),
      contentDescription = "Splash",
      modifier = Modifier
        .size(50.dp)
        .align(Alignment.Center)
    )
    Text(
      modifier = Modifier
        .align(Alignment.BottomEnd)
        .padding(2.dp),
      fontSize = 20.sp,
      text = stringResource(R.string.api_credits)
    )
  }
}

@Composable
@Preview
fun SplashPreview() {
  RayTVTheme {
    Splash()
  }
}