package br.com.fiap.navegacao

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import br.com.fiap.navegacao.screens.LoginScreen
import br.com.fiap.navegacao.screens.MenuScreen
import br.com.fiap.navegacao.screens.PedidosScreen
import br.com.fiap.navegacao.screens.PerfilScreen
import br.com.fiap.navegacao.ui.theme.NavegaçãoTheme
import com.google.accompanist.navigation.animation.rememberAnimatedNavController


class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavegaçãoTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = "login"
                    ) {

                        composable(route = "login") {
                            LoginScreen(navController)
                        }
                        composable(route = "menu") {
                            MenuScreen(navController)
                        }


                        // Com lista de argumentos
                        composable(
                            route = "perfil/{nome}/{idade}",
                            arguments = listOf(
                                navArgument(name = "nome") {
                                    type = NavType.StringType
                                },
                                navArgument(name = "idade") {
                                    type = NavType.IntType
                                }
                            )) {
                            val nome = it.arguments?.getString("nome")
                            val idade = it.arguments?.getInt("idade")
                            PerfilScreen(navController, nome!!, idade!!)
                        }

                        // Com a possibilidade de ter Null

                        composable(
                            route = "pedidos?numero={numero}",
                            arguments = listOf(navArgument(name = "numero") {
                                defaultValue = "sem valor"
                            })
                        ) {
                            PedidosScreen(it.arguments?.getString("numero")!!)
                        }
                    }
                }
            }
        }
    }
}


//  @Preview(showBackground = true, showSystemUi = true)
// @Composable
//fun LoginScreenPreview() {
//   LoginScreen()

//}



