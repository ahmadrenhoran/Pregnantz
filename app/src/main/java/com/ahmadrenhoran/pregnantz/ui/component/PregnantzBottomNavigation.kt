package com.ahmadrenhoran.pregnantz.ui.component

import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ahmadrenhoran.pregnantz.ui.feature.PregnantzHomeScreen

@Composable
fun PregnantzBottomNavigation(
    navController: NavHostController,
    items: List<PregnantzHomeScreen>
) {
    NavigationBar(
        tonalElevation = 200.dp,
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.primary,

    ) {
        val currentRoute = currentRoute(navController)
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = screen.name) },
                label = { Text(stringResource(id = screen.title)) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.primary
                ),
                selected = currentRoute == screen.name,
                alwaysShowLabel = true,
                onClick = {
                    if (currentRoute != screen.name) {
                        val route = currentRoute
                        navController.navigate(screen.name) {
                            if (route != null) {
                                popUpTo(route) {
                                    inclusive = true
                                }
                            }
                        }
                    }
                }
            )
        }
    }
}
/**
 * Copied from similar function in NavigationUI.kt
 *
 * https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:navigation/navigation-ui/src/main/java/androidx/navigation/ui/NavigationUI.kt
 */
@Composable
fun currentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}

private val NavGraph.startDestination: NavDestination?
    get() = findNode(startDestinationId)

private tailrec fun findStartDestination(graph: NavDestination): NavDestination {
    return if (graph is NavGraph) findStartDestination(graph.startDestination!!) else graph
}