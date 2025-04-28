package com.example.resepkita
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.resepkita.R
import com.example.resepkita.ui.theme.ResepKitaTheme

// Dummy Data
val sampleRecipes = listOf(
    Recipe("Nasi Goreng", "nasi_goreng", listOf("1 porsi nasi dingin, 1 butir telur, 2 siung bawang putih cincang, 1/2 bawang merah cincang, 2 sendok makan kecap manis, 1 sendok makan minyak goreng, serta garam dan merica secukupnya."), "1. Cincang bawang putih, bawang merah, dan bahan pelengkap lainnya.\n" +
            "\n" +
            "2. Tumis bawang putih dan bawang merah hingga harum.\n" +
            "\n" +
            "3. Kocok telur dan masukkan ke dalam wajan, orak-arik hingga matang.\n" +
            "\n" +
            "4. Masukkan nasi dingin ke dalam wajan, aduk rata dengan telur dan bumbu.\n" +
            "\n" +
            "5. Tambahkan kecap manis, garam, dan merica. Aduk rata hingga nasi tercampur bumbu dengan baik.\n" +
            "\n" +
            "6. Angkat dan sajikan nasi goreng dengan pelengkap sesuai selera."),
    Recipe("Mie Goreng", "mie_goreng", listOf("1 bungkus mie instan, 1 sendok makan minyak goreng, 1 butir telur (opsional), 1 siung bawang putih cincang halus, 1 batang daun bawang iris tipis, saus sambal, kecap manis secukupnya, serta sayuran seperti kol, wortel, atau cabai sesuai selera (opsional)."), "1. Masak mie instan sesuai petunjuk pada kemasan, tiriskan.\n" +
            "\n" +
            "2. Panaskan minyak di wajan, tumis bawang putih hingga harum.\n" +
            "\n" +
            "3. Jika menggunakan telur, pecahkan dan orak-arik dalam wajan bersama bawang putih.\n" +
            "\n" +
            "4. Masukkan mie yang sudah direbus, aduk rata.\n" +
            "\n" +
            "5. Tambahkan bumbu mie instan (seperti kecap manis, saus sambal) dan aduk hingga rata.\n" +
            "\n" +
            "6. Jika suka, tambahkan sayuran atau bahan lainnya, aduk rata.\n" +
            "\n" +
            "7. Setelah mie tercampur dengan bumbu dan matang, angkat dan sajikan."),
    Recipe("Sate Ayam", "sate_ayam", listOf("500 gram daging ayam fillet yang dipotong dadu, 10-15 tusuk sate, serta bahan marinasi seperti 3 siung bawang putih, 2 sdm kecap manis, 1 sdt kecap asin, 1 sdt air jeruk nipis, 1 sdt gula merah serut (opsional), 1/2 sdt merica bubuk, 1 sdm minyak goreng, dan garam secukupnya. Untuk bumbu kacang, siapkan 100 gram kacang tanah goreng dan haluskan, 2 sdm kecap manis, 1 siung bawang putih, 1-2 cabai merah (opsional), 1/2 sdt air jeruk nipis, dan 100 ml air matang."), "1. Campurkan semua bahan marinasi, aduk rata, dan rendam potongan ayam selama 30 menit hingga 1 jam agar bumbu meresap.\n" +
            "\n" +
            "2. Setelah marinasi, tusuk ayam yang telah dibumbui ke dalam tusuk sate secara merata.\n" +
            "\n" +
            "3. Panggang sate di atas bara api atau grill pan, balik sesekali hingga ayam matang dan berwarna kecokelatan. Oleskan sedikit bumbu marinasi di atas sate selama memanggang agar lebih gurih.\n" +
            "\n" +
            "4. Campurkan kacang tanah yang telah digoreng dan haluskan dengan kecap manis, bawang putih, cabai, air jeruk nipis, dan air matang. Aduk rata hingga teksturnya cukup kental.\n" +
            "\n" +
            "5. Sajikan sate ayam dengan bumbu kacang di sampingnya."),
    Recipe("Ayam Geprek", "ayam_geprek", listOf("250 gram ayam fillet, 100 gram tepung terigu, 2 sendok makan tepung maizena, 1 butir telur, garam dan merica secukupnya, minyak goreng. Untuk sambal: 5 buah cabai rawit, 2 siung bawang putih, 1/4 sendok teh garam, 1/2 sendok teh gula pasir, dan 2 sendok makan minyak panas."), "1. Bumbui ayam dengan garam dan merica, diamkan 10–15 menit agar meresap.\n" +
            "\n" +
            "2. Campur tepung terigu dan maizena di satu wadah.\n" +
            "\n" +
            "3. Celupkan ayam ke dalam telur kocok, lalu balurkan ke campuran tepung hingga rata.\n" +
            "\n" +
            "4. Goreng ayam dalam minyak panas sampai berwarna keemasan dan matang, angkat dan tiriskan.\n" +
            "\n" +
            "5. Untuk sambal, ulek cabai rawit, bawang putih, garam, dan gula sampai agak halus.")
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ResepKitaTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ResepKitaApp()
                }
            }
        }
    }
}

@Composable
fun ResepKitaApp() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navController) }
        composable("detail/{recipeName}") { backStackEntry ->
            val recipeName = backStackEntry.arguments?.getString("recipeName") ?: ""
            val recipe = sampleRecipes.find { it.name == recipeName }
            recipe?.let { DetailScreen(it) }
        }
    }
}

@Composable
fun HomeScreen(navController: NavHostController) {
    var searchQuery by remember { mutableStateOf("") }
    val filteredRecipes = sampleRecipes.filter {
        it.name.contains(searchQuery, ignoreCase = true) ||
                it.ingredients.any { ingredient -> ingredient.contains(searchQuery, ignoreCase = true) }
    }

    Column(modifier = Modifier.padding(16.dp)) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Cari Resep") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn {
            items(filteredRecipes) { recipe ->
                RecipeItem(recipe) {
                    navController.navigate("detail/${recipe.name}")
                }
            }
        }
    }
}

@Composable
fun RecipeItem(recipe: Recipe, onClick: () -> Unit) {
    // Ukuran tetap untuk card
    val cardHeight = 180.dp  // Atur sesuai keinginan

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight)  // Set height untuk card agar konsisten
            .padding(vertical = 8.dp)
            .clickable { onClick() },  // Cukup onClick untuk navigasi
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFFE57F))  // Menggunakan oranye cerah lebih mendekati putih
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = recipe.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(4.dp))

            // Menampilkan bahan-bahan dengan maxLines dan ellipsize
            Text(
                text = "Bahan: ${recipe.ingredients.joinToString()}",
                fontSize = 14.sp,
                maxLines = 2,  // Batasi hanya 2 baris untuk bahan
                overflow = TextOverflow.Ellipsis  // Tampilkan ... jika melebihi
            )
        }
    }
}


@Composable
fun DetailScreen(recipe: Recipe) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFE57F)  // Ganti dengan warna background yang kamu mau
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = getImageResource(recipe.imageRes)),
                contentDescription = recipe.name,
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(recipe.name, fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Bahan-bahan: ${recipe.ingredients.joinToString(", ")}", fontSize = 16.sp)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Langkah-langkah:", fontSize = 18.sp, fontWeight = FontWeight.SemiBold)
            Text(recipe.steps, fontSize = 16.sp, modifier = Modifier.padding(top = 4.dp))
        }
    }
}


// Helper
fun getImageResource(name: String): Int {
    return when(name) {
        "nasi_goreng" -> R.drawable.nasi_goreng
        "mie_goreng" -> R.drawable.mie_goreng
        "sate_ayam" -> R.drawable.sate_ayam
        "ayam_geprek" -> R.drawable.ayam_geprek
        else -> R.drawable.placeholder
    }
}

data class Recipe(
    val name: String,
    val imageRes: String,
    val ingredients: List<String>,
    val steps: String
)
