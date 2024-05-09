
import androidx.compose.runtime.Composable
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.IN2000_prosjekt.R

@Composable
fun NavigationMenu(
    navController: NavController,
    modifier: Modifier = Modifier) {

    NavigationBar(modifier = modifier) {
        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.mapicon),
                    contentDescription = stringResource(R.string.map_content_description)
                )
            },
            label = { Text(stringResource(R.string.map)) },
            selected = false,
            onClick = { navController.navigate("MapScreen")}
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.signicon),
                    contentDescription = stringResource(R.string.sign_content_description)
                )
            },
            label = { Text(stringResource(R.string.sign)) },
            selected = false,
            onClick = { navController.navigate("CategoryScreen") }
        )

        NavigationBarItem(
            icon = {
                Icon(
                    painterResource(id = R.drawable.settingsicon),
                    contentDescription = stringResource(R.string.settings_content_description)
                )
            },
            label = { Text(stringResource(R.string.settings)) },
            selected = false,
            onClick = { navController.navigate("SettingsScreen") }
        )
    }
}
