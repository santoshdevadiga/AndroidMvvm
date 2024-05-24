package com.santoshdevadiga.androidmvvm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import com.santoshdevadiga.androidmvvm.api.RecipesAPI
import com.santoshdevadiga.androidmvvm.screen.ReceipScreen
import com.santoshdevadiga.androidmvvm.ui.theme.AndroidMvvmTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Scope


data class BottomNavigationItem(
    val title:String,
    val selectedIcon:ImageVector,
    val unselectedIcon:ImageVector,
    val hasSavedReceip:Boolean,
    val badgeCount:Int?=null
)

@AndroidEntryPoint
@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var receipAPI:RecipesAPI



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            AndroidMvvmTheme {
                val scrollbehavior=TopAppBarDefaults.pinnedScrollBehavior()
                val navBottomitems= listOf(
                    BottomNavigationItem("Home",Icons.Filled.Home,Icons.Outlined.Home,false),
                    BottomNavigationItem("Saved",Icons.Filled.Favorite,Icons.Outlined.Favorite,true,0),
                    BottomNavigationItem("Setting",Icons.Filled.Settings,Icons.Outlined.Settings,true)
                )

                val navSideitems= listOf(
                    BottomNavigationItem("Home",Icons.Filled.Home,Icons.Outlined.Home,false),
                    BottomNavigationItem("Saved",Icons.Filled.Favorite,Icons.Outlined.Favorite,true,0),
                    BottomNavigationItem("Setting",Icons.Filled.Settings,Icons.Outlined.Settings,true)
                )

                var selectedBottomNavItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }
                var selectedSideNavItemIndex by rememberSaveable {
                    mutableStateOf(0)
                }
                var drawerState= rememberDrawerState(initialValue = DrawerValue.Closed)
                val coroutineScope = rememberCoroutineScope()
                ModalNavigationDrawer  (
                    drawerState = drawerState,
                    drawerContent = {
                        ModalDrawerSheet {
                            Spacer(Modifier.height(16.dp))
                            navSideitems.forEachIndexed{ index, sideNavigationItem ->
                                NavigationDrawerItem(
                                    label = {
                                        Text(text = sideNavigationItem.title)
                                    },
                                    selected = index==selectedSideNavItemIndex,
                                    onClick = {
                                        selectedSideNavItemIndex=index
                                        coroutineScope.launch {
                                            drawerState.close()
                                        }

                                    },
                                    icon = {
                                        Icon(imageVector = if(index==selectedBottomNavItemIndex)
                                            sideNavigationItem.selectedIcon
                                        else sideNavigationItem.unselectedIcon,
                                        contentDescription = sideNavigationItem.title)
                                    },
                                    badge = {
                                        sideNavigationItem.badgeCount?.let {
                                            Text(text = sideNavigationItem.badgeCount.toString())
                                        }

                                    },
                                    modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding)

                                )
                            }
                        }
                    }
                ){
                    Scaffold(modifier = Modifier
                        .fillMaxSize()
                        .nestedScroll(scrollbehavior.nestedScrollConnection),
                        topBar = {
                            TopAppBar(
                                title = { Text("Receip List") },
                                navigationIcon = {
                                    IconButton(onClick = {
                                        coroutineScope.launch {
                                            drawerState.open()
                                        }
                                    }) {
                                        Icon(
                                            imageVector = Icons.Default.Menu,
                                            contentDescription = "Back"
                                        )
                                    }
                                },
                                actions = {
                                    IconButton(onClick = {}) {
                                        Icon(
                                            imageVector = Icons.Default.Refresh,
                                            contentDescription = "Refresh"
                                        )
                                    }
                                    IconButton(onClick = {}) {
                                        Icon(
                                            imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                                            contentDescription = "Refresh"
                                        )
                                    }
                                },
                                scrollBehavior = scrollbehavior
                            )
                        },
                        bottomBar = {
                            NavigationBar {
                                navBottomitems.forEachIndexed { index, bottomNavigationItem ->
                                    NavigationBarItem(
                                        selected = selectedBottomNavItemIndex == index,
                                        onClick = {
                                            selectedBottomNavItemIndex = index

                                        },
                                        label = { Text(bottomNavigationItem.title) },
                                        icon = {
                                            BadgedBox(badge = {
                                                if (bottomNavigationItem.badgeCount != null) {
                                                    Badge {
                                                        Text(bottomNavigationItem.badgeCount.toString())
                                                    }
                                                } else if (bottomNavigationItem.hasSavedReceip) {
                                                    Badge()
                                                }
                                            }) {
                                                Icon(
                                                    imageVector = if (index == selectedBottomNavItemIndex) {
                                                        bottomNavigationItem.selectedIcon
                                                    } else {
                                                        bottomNavigationItem.unselectedIcon
                                                    },
                                                    contentDescription = bottomNavigationItem.title
                                                )
                                            }
                                        }
                                    )

                                }
                            }
                        },
                        floatingActionButton = {
                            FloatingActionButton(onClick = {}) {
                                IconButton(onClick = {}) {
                                    Icon(
                                        imageVector = Icons.Default.Add,
                                        contentDescription = "Refresh"
                                    )
                                }
                            }
                        }
                    ) { innerPadding ->
                        ReceipScreen(
                            modifier = Modifier.padding(innerPadding)
                        )

                    }
                }
            }
        }

    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidMvvmTheme {
        ReceipScreen(modifier=Modifier)
    }
}