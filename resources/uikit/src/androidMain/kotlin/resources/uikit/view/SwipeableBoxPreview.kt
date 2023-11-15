package resources.uikit.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview(showSystemUi = true)
@Composable
fun SwipeableBoxPreview() {

    SwipeableBox(
        modifier = Modifier.height(60.dp),
        contentModifier = Modifier.height(60.dp),
        menuWidth = 120.dp,
        onMenuVisibilityChanged = { _ -> },
        menuContent = {
            Row(
                modifier = Modifier.fillMaxHeight().background(Color.Cyan),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = null
                )

                Image(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Default.Email,
                    contentDescription = null
                )

                Image(
                    modifier = Modifier.size(40.dp),
                    imageVector = Icons.Default.Delete,
                    contentDescription = null
                )
            }
        }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                imageVector = Icons.Default.Face,
                contentDescription = null
            )

            Text(
                text = "Example text",
                modifier = Modifier
            )

            Image(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = null
            )
        }
    }
}