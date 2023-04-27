package com.example.imagegallery

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.imagegallery.widgets.ImageList

@Composable
fun SearchScreen(viewModal: ImageViewModal){
    Column {
        SearchBar()
        ImageList(viewModel = viewModal)
    }

}

@Composable
fun SearchBar() {
    val state = rememberSaveable(stateSaver = TextFieldValue.Saver) {
        mutableStateOf(TextFieldValue(""))
    }

//    val tempViewModel = ViewModelProvider(
//        LocalContext.current as TemplateActivity,
//        TemplateViewModelFactory(getLanguageIso(LocalContext.current))
//    )[TemplateViewModel::class.java]

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .border(
                width = 1.dp, color = MaterialTheme.colorScheme.onBackground, shape = CircleShape
            )
    ) {
        BasicTextField(value = state.value,
            onValueChange = {
                state.value = it
              //  tempViewModel.searchNow(it.text)
            },
            modifier = Modifier
                .heightIn(min = 45.dp)
                .testTag("search_bar")
                .fillMaxWidth(),
            textStyle = TextStyle(
                color = MaterialTheme.colorScheme.onBackground
            ),
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onBackground),
            maxLines = 1,
            singleLine = true,
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                    )
                    Box(
                        modifier = Modifier
                            .weight(1F)
                            .padding(horizontal = 10.dp),
                        contentAlignment = Alignment.CenterStart
                    ) {
                        if (state.value == TextFieldValue("")) Text(
                            text = "SearchImage",
                            fontWeight = FontWeight.ExtraLight,
                        )
                        innerTextField()
                    }
                    if (state.value != TextFieldValue("")) {
                        Icon(imageVector = Icons.Default.Close,
                            contentDescription = "clear",
                            modifier = Modifier
                                .clickable {
                                    state.value = TextFieldValue("")
                               //     tempViewModel.searchNow("")

                                }
                                .testTag("clear_search"))
                    }
                }
            })
    }
}