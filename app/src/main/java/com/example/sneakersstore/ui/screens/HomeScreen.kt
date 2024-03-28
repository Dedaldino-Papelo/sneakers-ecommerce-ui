package com.example.sneakersstore.ui.screens

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.sneakersstore.Data.DataStore
import com.example.sneakersstore.R
import com.example.sneakersstore.ui.theme.SneakersStoreTheme
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(modifier: Modifier = Modifier){
    var searchText by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 18.dp)
        ) {
            SearchBar(
                value = searchText ,
                onValueChange = { searchText = it },
                modifier = Modifier
            )
            Spacer(modifier = Modifier.size(20.dp))
            CardImage()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier,
    value: String,
    onValueChange: (String) -> Unit
){
    TextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = "What are you looking for?")},
        shape = RoundedCornerShape(50.dp),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        leadingIcon = {
            Icon(
                Icons.Default.Search,
                contentDescription = null,
                tint = Color.Gray
            )
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CardImage(modifier: Modifier = Modifier){

    val cards = DataStore.cards
    val pagerState = rememberPagerState(pageCount = {
        cards.size
    })

   LaunchedEffect(Unit){
        while (true){
            delay(3000)
            val nextPage = (pagerState.currentPage + 1) % pagerState.pageCount
            pagerState.scrollToPage(nextPage)
        }
   }

    Card(
        modifier = Modifier
            .height(180.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(R.color.black),
        )
    ) {
        HorizontalPager(state = pagerState) {currentPage ->
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .fillMaxHeight(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = cards[currentPage].title),
                        color = colorResource(R.color.white),
                        style = MaterialTheme.typography.displayMedium,
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                    )
                    Text(
                        text = stringResource(id = cards[currentPage].description),
                        lineHeight = 20.sp,
                        color = colorResource(R.color.gray),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                            .width(120.dp)
                    )
                    Button(
                        onClick = { },
                        modifier = Modifier
                            .padding(top = 10.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = colorResource(R.color.button_color)
                        )
                    ) {
                        Text(text = "Shop Now")
                    }
                }
                Image(
                    modifier = Modifier
                        .size(200.dp),
                    painter = painterResource(id = cards[currentPage].imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                )
        }
    }

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
       repeat(pagerState.pageCount){ iteration ->
            val color = if(pagerState.currentPage == iteration) Color(0xff373737) else Color(0xA83373737)
           Box(
               modifier = Modifier
                   .padding(2.dp)
                   .clip(CircleShape)
                   .background(color)
                   .size(10.dp)
           )
       }
    }
}



@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    SneakersStoreTheme {
        HomeScreen()
    }
}
