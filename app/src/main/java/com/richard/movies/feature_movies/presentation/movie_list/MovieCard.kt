package com.richard.movies.feature_movies.presentation.movie_list

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Error
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.OndemandVideo
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.richard.movies.R
import com.richard.movies.feature_movies.domain.model.Movie
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieCard(
    movie: Movie,
    modifier: Modifier
) {
    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
        ),
        shape = MaterialTheme.shapes.large
    ) {


        GlideImage(
            imageModel = movie.getFullImagePath(),
            contentScale = ContentScale.Crop,
            shimmerParams = ShimmerParams(
                baseColor = MaterialTheme.colorScheme.onSurfaceVariant,
                highlightColor = Color.White,
                durationMillis = 350,
                dropOff = 0.65f,
                tilt = 20f
            ),
            // shows an error text message when request failed.
            failure = {
                Icon(
                    Icons.Outlined.Error,
                    contentDescription = stringResource(id = R.string.failed),
                    modifier = Modifier.align(Alignment.Center),
                    tint = MaterialTheme.colorScheme.error
                )
                Text(
                    text = "image request failed.",
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(top = 24.dp)
                )
            },
            modifier = Modifier
                .clip(MaterialTheme.shapes.large)
                .fillMaxWidth()
                .aspectRatio(3f / 2f),
        )




        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = movie.name,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AssistChip(
                    onClick = { },
                    colors = AssistChipDefaults.assistChipColors(
                        leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.FavoriteBorder,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = "Like")
                    }
                )
                AssistChip(
                    onClick = { },
                    colors = AssistChipDefaults.assistChipColors(
                        leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.Share,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = "Share ")
                    }
                )

                AssistChip(
                    onClick = { },
                    colors = AssistChipDefaults.assistChipColors(
                        leadingIconContentColor = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Outlined.OndemandVideo,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = "Play")
                    }
                )

            }
        }
    }
}