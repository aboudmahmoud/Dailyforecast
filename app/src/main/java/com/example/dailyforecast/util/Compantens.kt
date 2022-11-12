package com.example.dailyforecast.util

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.BaselineShift
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties

import com.example.dailyforecast.R
import kotlinx.coroutines.delay

/*
Here is Our Coustem Commpantes To use
*/
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CoustemTextFielsSerah(    text: String,
                              onTextChange: (String) -> Unit,
                              onCloseClicke: () -> Unit,
                              onSerachCliced: (String) -> Unit,) {
    val AlphaAware = 1.0f

    TextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange ={onTextChange(it) },
        shape = RoundedCornerShape(0.dp),
        placeholder = {
            Text(
                modifier = Modifier.alpha(alpha = AlphaAware),
                text = "Serach Here",
                color = Color.Black
            )
        },
        textStyle = TextStyle(
            fontSize = MaterialTheme.typography.labelLarge.fontSize
        ),
        singleLine = true,

        leadingIcon = {
            IconButton(onClick = {
                if (text.isNotEmpty()) {
                    onTextChange("")
                } else {
                    onCloseClicke()
                }
            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon",
                    tint = Color.Black
                )
            }
        },
        trailingIcon = {
            IconButton(modifier = Modifier.alpha(alpha = AlphaAware), onClick = {onCloseClicke()} ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Close Icon",
                    tint = Color.Black
                )
            }
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search,

            ),
        keyboardActions = KeyboardActions(
            onSearch = {
                onSerachCliced(text)
                Log.d("Aboud", "SerachBar:${text} ")
            }

        ),

        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            disabledTextColor = Color.Transparent,
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        )
    )
}



//WitherisCompants

@Composable
fun ColumnScope.BottomDayDetials(date:String?) {
  if(date!=null){
      Row(modifier = Modifier
          .weight(2f)
          .padding(2.dp), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {

          val monthName=  Constans.getAbbreviatedFromDateTime(
              dateTime=date,
              dateFormat="yyyy-MM-dd HH:mm:ss",
              field="MMMM"
          )
          val dayOfWeek=  Constans.getAbbreviatedFromDateTime(
              dateTime=date,
              dateFormat="yyyy-MM-dd HH:mm:ss",
              field="E"
          )
          val hours=  Constans.getAbbreviatedFromDateTime(
              dateTime=date,
              dateFormat="yyyy-MM-dd HH:mm:ss",
              field=" hh:mm"
          )

          val dayOfMonth=  Constans.getAbbreviatedFromDateTime(
              dateTime=date,
              dateFormat="yyyy-MM-dd HH:mm:ss",
              field="dd"
          )
          if (hours != null) {
              Text(text = hours)
          }
          Spacer(modifier = Modifier.width(5.dp))
          if (dayOfWeek != null) {
              Text(text = dayOfWeek)
          }
          Spacer(modifier = Modifier.width(5.dp))
          if (dayOfMonth != null) {
              Text(text = dayOfMonth)
          }
          Spacer(modifier = Modifier.width(5.dp))
          if (monthName != null) {
              Text(text = monthName)
          }


      }
  }
}

@Composable
fun ColumnScope.WitherBoxImagesData(
    Temp: String?,
    MainWitherState: String?,
    detials: String?,
    humidity: String?
) {
    Box(modifier = Modifier.weight(6f)) {
        val matrix = ColorMatrix()
        matrix.setToSaturation(0.2F)
        Image(painter = painterResource(id = R.drawable.wall), contentDescription = null,
            colorFilter = ColorFilter.colorMatrix( matrix))
        Column(modifier = Modifier
            .padding(15.dp)
            .fillMaxSize()) {
            Row(modifier = Modifier.padding(5.dp)) {
                if (Temp != null) {
                    SuperScripetText(normalText = Temp, superText ="°C" )
                }
             //   Text(modifier = Modifier.weight(3f), text = Temp)
                Spacer(modifier = Modifier.width(10.dp))
                WetherDetils(
                    MainWitherState = MainWitherState,
                    detials = detials,
                    humidity = humidity
                )
            }
        }
    }
}

@Composable
fun RowScope.WetherDetils(
    MainWitherState: String?,
    detials: String?,
    humidity: String?
) {
    Column(
        Modifier.padding(10.dp)
    ) {
      //  Text(text = "°C")
        if (MainWitherState != null) {
            Text(text = MainWitherState,
                style = TextStyle(
                    color=Color.Black,
                    fontWeight = FontWeight.ExtraBold
                )
            )
        }
        if (detials != null) {
            Text(text = detials,
                style = TextStyle(
                    color=Color.Black,
                    fontWeight = FontWeight.ExtraBold
                ))
        }
        if (humidity != null) {
            Text(text = "humidity:${humidity}%",
                style = TextStyle(
                    color=Color.Black,
                    fontWeight = FontWeight.ExtraBold
                ))
        }

    }
}
//End of WitherisCompants


@Composable
fun LoadingAnimation(

    circleColor: Color = Color.Magenta,
    animationDelay: Int = 1500
) {

    // 3 circles
    val circles = listOf(
        remember {
            Animatable(initialValue = 0f)
        },
        remember {
            Animatable(initialValue = 0f)
        },
        remember {
            Animatable(initialValue = 0f)
        }
    )

    circles.forEachIndexed { index, animatable ->
        LaunchedEffect(Unit) {
            // Use coroutine delay to sync animations
            // divide the animation delay by number of circles
            delay(timeMillis = (animationDelay / 3L) * (index + 1))

            animatable.animateTo(
                targetValue = 1f,
                animationSpec = infiniteRepeatable(
                    animation = tween(
                        durationMillis = animationDelay,
                        easing = LinearEasing
                    ),
                    repeatMode = RepeatMode.Restart
                )
            )
        }
    }

    // outer circle
    Box(
        modifier = Modifier
            .size(size = 200.dp)
            .background(color = Color.Transparent)
    ) {
        // animating circles
        circles.forEachIndexed { index, animatable ->
            Box(
                modifier = Modifier
                    .scale(scale = animatable.value)
                    .size(size = 200.dp)
                    .clip(shape = CircleShape)
                    .background(
                        color = circleColor
                            .copy(alpha = (1 - animatable.value))
                    )
            ) {
            }
        }
    }
}


@Composable
fun SuperScripetText(
    normalText: String,
    superText: String,
) {
    Text(buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                color=Color.Black,
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontWeight = FontWeight.ExtraBold
            )
        ) {
            append(normalText)
        }

        withStyle( style = SpanStyle(
            color=Color.Black,
            fontSize = MaterialTheme.typography.titleSmall.fontSize,
            fontWeight = FontWeight.Bold,
            baselineShift = BaselineShift.Superscript
        )
        ){
            append(superText)
        }
    })
}
@Composable
fun SubScripetText(
    normalText: String,
    SubText: String,
) {
    Text(buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontSize = MaterialTheme.typography.labelMedium.fontSize
            )
        ) {
            append(normalText)
        }

        withStyle( style = SpanStyle(
            fontSize = MaterialTheme.typography.labelSmall.fontSize,
            fontWeight = FontWeight.Normal,
            baselineShift = BaselineShift.Subscript
        )
        ){
            append(SubText)
        }
    })}

@Composable
fun CoustemDiloage(dialogOpene:Boolean,
errorMessage:String) {
    var dialogOpen by remember {
        mutableStateOf(dialogOpene)
    }

    if (dialogOpen) {
        AlertDialog(
            onDismissRequest = {
                // Dismiss the dialog when the user clicks outside the dialog or on the back
                // button. If you want to disable that functionality,
                // simply leave this block empty.
                dialogOpen = false
            },
            confirmButton = {
                TextButton(onClick = {
                    // perform the confirm action
                    dialogOpen = false
                }) {
                    Text(text = "Confirm")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    dialogOpen = false
                }) {
                    Text(text = "Dismiss")
                }
            },
            title = {
                Text(text = "Error")
            },
            text = {
                Text(text = errorMessage)
            },
            modifier = Modifier // Set the width and padding
                .fillMaxWidth()
                .padding(32.dp),
            shape = RoundedCornerShape(5.dp),
            containerColor = Color.White,
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true
            )
        )
    }

}

@Composable
fun LoadingBrogessFullWidth() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LoadingAnimation()  
    }
 
}
