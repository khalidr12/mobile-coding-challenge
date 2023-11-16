package com.audiobooks.codingchallenge.utils

fun formatHtmlText(input: String): String {
    var formattedText = input.replace(Regex("<p>"), "\n\n")
    formattedText = formattedText.replace(Regex("<br\\s*/?>"), "\n")
    formattedText = formattedText.replace(Regex("<[^>]*>"), "")
    return formattedText.trim()
}