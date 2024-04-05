package com.example.sneakersstore.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakersstore.Data.DataSource
import com.example.sneakersstore.R
import com.example.sneakersstore.models.Product
import com.example.sneakersstore.ui.components.CustomButton
import com.example.sneakersstore.ui.theme.SneakersStoreTheme
import java.text.NumberFormat

@Composable
fun OrderSummaryScreen(
    shoppingCart: List<Product>,
    modifier: Modifier = Modifier
){
    Log.d("shopping", shoppingCart.toString())
    var checkedState by rememberSaveable { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(25.dp)
        ) {
            shoppingCart.forEach { item ->
                Row(
                    modifier = modifier
                        .padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = checkedState,
                        onCheckedChange = { checkedState = it },
                        colors = CheckboxDefaults.colors(
                            colorResource(R.color.button_color)
                        )
                    )

                    Box(
                        modifier = modifier
                            .size(100.dp)
                            .background(
                                colorResource(R.color.second_color),
                                shape = RoundedCornerShape(8.dp)
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            modifier = modifier
                                .size(80.dp),
                            painter = painterResource(id = item.productImageRes),
                            contentDescription = stringResource(item.productName))
                    }

                    Spacer(modifier = modifier.width(20.dp))

                    Column {
                        Text(
                            text = stringResource(id = item.productName),
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp

                        )

                        Spacer(modifier = modifier.height(5.dp) )

                        Text(
                            text = stringResource(R.string.size),
                            color = colorResource(R.color.second_color)
                        )

                        Row(
                            modifier = modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = NumberFormat.getCurrencyInstance().format(item.productPrice),
                                fontWeight = FontWeight.Bold
                            )
                            Row(
                                modifier = modifier,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                DecreaseButton()
                                Spacer(modifier = modifier.width(9.dp))
                                Text(text = item.quantity.toString())
                                IncreaseButton()
                            }
                        }
                    }
                }
            }
        }

        CustomButton(
            onClick = {  },
            label = R.string.checkout_button,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 20.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
fun IncreaseButton(modifier: Modifier = Modifier){
    IconButton(
        onClick = { },
    ) {
        Icon(
            Icons.Default.Add,
            tint = Color.White,
            contentDescription = null,
            modifier = modifier
                .background(
                    colorResource(R.color.button_color),
                    shape = RoundedCornerShape(5.dp)
                )
        )
    }
}

@Composable
fun DecreaseButton(modifier: Modifier = Modifier){
    Box(
        modifier = modifier
            .clickable { }
            .size(28.dp)
            .background(
                colorResource(R.color.second_color),
                shape = RoundedCornerShape(5.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icons_menos_24),
            tint = colorResource(R.color.decrease_color),
            contentDescription = null,
            modifier = Modifier
                .width(15.dp)
                .height(20.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun OrderSummaryPreview(){
    SneakersStoreTheme {
        OrderSummaryScreen(
            shoppingCart = DataSource.products
        )
    }
}