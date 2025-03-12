package com.example.localisationwithcompose


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.localisationwithcompose.ui.theme.AppIcons
import com.example.localisationwithcompose.ui.theme.LocalisationWIthComposeTheme
import com.example.localisationwithcompose.ui.theme.dimens


@Composable
fun LanguageRow(language: Language, isSelected: Boolean, onLanguageClick: (Language) -> Unit) {
    Card(
        modifier = Modifier
            .padding(MaterialTheme.dimens.regular)
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.small
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally)
                .clickable {
                    onLanguageClick(language)
                },
        ) {

            Column {
                Text(
                    text = language.displayLanguage,
                    style = MaterialTheme.typography.bodyMedium,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .padding(8.dp)
                )
                Text(
                    text = language.code,
                    style = MaterialTheme.typography.bodySmall,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 8.dp, bottom = 8.dp)
                )
            }

            if (isSelected) {
                Icon(imageVector = AppIcons.Check, contentDescription = "Selected")
            }
        }
        HorizontalDivider(
            modifier = Modifier.height(1.dp),
            thickness = 1.dp,
            color = MaterialTheme.colorScheme.onTertiaryContainer
        )
    }
}


@Preview(showBackground = true)
@Composable
fun ItemPreview() {
    LocalisationWIthComposeTheme {
        LanguageRow(
            language = Language(
                "ne",
                "Nepali"
            ),
            true
        ) {

        }
    }
}