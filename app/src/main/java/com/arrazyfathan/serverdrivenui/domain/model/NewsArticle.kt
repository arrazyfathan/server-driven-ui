package com.arrazyfathan.serverdrivenui.domain.model

import com.arrazyfathan.serverdrivenui.R

/**
 * Created by Ar Razy Fathan Rabbani on 01/09/23.
 */
data class NewsArticle(
    val title: String,
    val image: Int,
    val subtitle: String,
)

val listNewsArticles = listOf(
    NewsArticle(
        "Mikel and Ramires added to Legends roster",
        R.drawable.ramires,
        "John Mikel Obi and Ramires are the latest 2012 Champions League winners confirmed to be taking part in the Chelsea Legends vs Bayern Munich Legends match at Stamford Bridge in September.",
    ),
    NewsArticle(
        "Chelsea vs Bayern Munich: The Legends of Europe are coming to Stamford Bridge!",
        R.drawable.terry,
        "Later this year, join us at Stamford Bridge to see the Legends of Europe in action.",
    ),
    NewsArticle(
        "Three more champions added to Legends game line-up",
        R.drawable.example_2,
        "Chelsea’s first Premier League title-winning season will forever be cherished and fans attending the Legends of Europe match at Stamford Bridge in September will be able rekindle cherished memories of that era, as well as other significant successes in the club’s history.",
    ),
    NewsArticle(
        "Legends of Europe ticket details confirmed as Zola joins the team",
        R.drawable.zola_1,
        "Tickets to see Chelsea take on Bayern Munich in the Legends of Europe game at Stamford Bridge will go on sale this week, with Gianfranco Zola the latest Blues hero revealed to be returning to the pitch in SW6.",
    ),
)
