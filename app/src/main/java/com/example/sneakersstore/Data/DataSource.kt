package com.example.sneakersstore.Data

import androidx.compose.runtime.mutableStateOf
import com.example.sneakersstore.R
import com.example.sneakersstore.models.Card
import com.example.sneakersstore.models.Parts
import com.example.sneakersstore.models.Product

object DataSource {
    val products = mutableListOf(
        Product(
            "1",
            R.drawable.nike_pegasus,
            R.string.product_name1,
            R.string.product_desc1,
            89.83,
            mutableStateOf(false),
            listOf(
                Parts(
                    "1",
                    R.drawable.part1
                )
            )
        ),

        Product(
            "2",
            R.drawable.nike_running_estrada,
            R.string.product_name3,
            R.string.product_desc1,
            70.20,
            mutableStateOf(false),
            listOf(
                Parts(
                    "1",
                    R.drawable.part1
                ),
                Parts(
                    "2",
                    R.drawable.part2
                ),
                Parts(
                    "3",
                    R.drawable.part3
                )
            )
        ),

        Product(
            "3",
            R.drawable.sapatilhas_de_running_estrada,
            R.string.product_name4,
            R.string.product_desc1,
            60.2,
            mutableStateOf(false),
            listOf(
                Parts(
                    "1",
                    R.drawable.part1
                )
            )
        ),

        Product(
            "4",
            R.drawable.nike_pagasus_33,
            R.string.product_name2,
            R.string.product_desc1,
            59.2,
            mutableStateOf(false),
            listOf(
                Parts(
                    "1",
                    R.drawable.part1
                )
            )
        )
    )

    val cards = listOf<Card>(
        Card(
            "1",
            R.drawable.kisspng_sneakers_nike,
            R.string.card_title1,
            R.string.card_description1
        ),

        Card(
            "2",
            R.drawable.kisspng_nike,
            R.string.card_title2,
            R.string.card_description2
        ),

        Card(
            "3",
            R.drawable.kisspng_nike_air,
            R.string.card_title3,
            R.string.card_description3
        )
    )
}