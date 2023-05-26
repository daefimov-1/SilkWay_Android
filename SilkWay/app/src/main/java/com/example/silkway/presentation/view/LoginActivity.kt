package com.example.silkway.presentation.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider
import com.example.silkway.R
import com.example.silkway.data.model.CatalogItem
import com.example.silkway.data.model.User
import com.example.silkway.data.storage.LoginStorage
import com.example.silkway.databinding.ActivityLoginBinding
import com.example.silkway.presentation.utils.likeString
import com.example.silkway.presentation.utils.toBitmap
import com.example.silkway.presentation.viewmodel.MainViewModel
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginStorage by inject<LoginStorage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        super.onCreate(savedInstanceState)

        binding.tvForgotPassword.setOnClickListener {
            ForgotPasswordActivity.start(this)
        }
        binding.btnLogIn.setOnClickListener {
            when(binding.textInputEdittextEmail.text.toString()) {
                "rkarabash@edu.hse.ru" -> {
                    loginStorage.saveUserInfo(
                        User(
                            id = 0,
                            name = "Karabash Radimir",
                            email = "rkarabash@edu.hse.ru",
                            isByer = false
                        )
                    )
                    goToNextScreen()
                }
                "daefimov@edu.hse.ru" -> {
                    loginStorage.saveUserInfo(
                        User(
                            id = 0,
                            name = "Daniil Efimov",
                            email = "daefimov@edu.hse.ru",
                            isByer = true
                        )
                    )
                    goToNextScreen()
                }
                else -> {
                    if (binding.textInputEdittextEmail.text!!.toString() ==
                        loginStorage.getUserInfo().email) {
                        goToNextScreen()
                    }
                    else {
                        Toast.makeText(
                            this,
                            "Incorrect data",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }
        binding.tvDontHaveAnAccount.setOnClickListener {
            SignUpActivity.start(this)
        }
        binding.tvSignUp.setOnClickListener {
            SignUpActivity.start(this)
        }
    }

    companion object {
        fun start(caller: FragmentActivity?) {
            val intent: Intent = Intent(caller, LoginActivity::class.java)
            caller?.startActivity(intent)
        }
    }

    private fun goToNextScreen() {
        val mainViewModel : MainViewModel = ViewModelProvider(this)[MainViewModel::class.java]
        mainViewModel.insertCatalogList(makeCatalogList())
        MainActivity.start(this)
    }

    private fun makeCatalogList(): List<CatalogItem> {
        return listOf<CatalogItem>(
            CatalogItem(
                id = 0,
                price = 412,
                currency = "₽",
                name = "Elizar",
                section = "Cleaning",
                youRequested = 10,
                currentAmountRequests = 23,
                minAmountRequests = 100,
                image = R.drawable.wcatalog_image4.toBitmap(this).likeString(),
                favourite = false,
                description = "Get acquainted with the ways of using the oxygen cleaner on the packaging or on our card. The powder is diluted with boiling water, as a result of which the reaction of the release of active oxygen begins, which contributes to the splitting of contaminants. We do not recommend using it for products made of wool, silk, down and feather, leather, suede, nubuck, membrane fabrics, copper, aluminum. Cleaning the house now does not take much time and effort, because you will have our Brandfree miracle cleaner!",
            ),
            CatalogItem(
                id = 1,
                price = 1340,
                currency = "$",
                name = "Realme",
                section = "Smartphones",
                youRequested = 0,
                currentAmountRequests = 37,
                minAmountRequests = 100,
                image = R.drawable.wcatalog_image3.toBitmap(this).likeString(),
                favourite = false,
                description = "Get acquainted with the ways of using the oxygen cleaner on the packaging or on our card. The powder is diluted with boiling water, as a result of which the reaction of the release of active oxygen begins, which contributes to the splitting of contaminants. We do not recommend using it for products made of wool, silk, down and feather, leather, suede, nubuck, membrane fabrics, copper, aluminum. Cleaning the house now does not take much time and effort, because you will have our Brandfree miracle cleaner!",
                isMine = true,
            ),
            CatalogItem(
                id = 2,
                price = 879,
                currency = "₽",
                name = "Easy Clean",
                section = "Cleaning",
                youRequested = 0,
                currentAmountRequests = 25,
                minAmountRequests = 200,
                image = R.drawable.wcatalog_image1.toBitmap(this).likeString(),
                favourite = false,
                description = "The Easy Clean stain remover is an excellent helper in cleaning, washing and removing complex organic contaminants. ECO bleach - can be used for both manual and machine washing. It enhances the effects of classic powders. Oxygen stain remover gently cleans clothes, upholstered furniture, dishes, jewelry, can also be used for carpets.\n" +
                        "\n" +
                        "Suitable for hand washing, soaking, machine washing: it is completely rinsed out of the fabric when using boiling water. The powder is suitable for colored underwear and white clothes, children's and adult clothing. It helps old things to get an updated appearance: it cleans frozen dirt, lime, fogging from clothes without any problems. Eco bleach is a reliable cleaning agent, safe for you and your children, does not cause allergies. Keep away from children, only sharing.\n" +
                        "\n" +
                        "With its help, you will get the result after the first application. Sodium percarbonate removes blood, is suitable for upholstered furniture and fat removal. Measuring spoon included. Household chemicals Easy Clean will allow you to most effectively overcome the problems associated with the cleanliness of your home.",
            ),
            CatalogItem(
                id = 3,
                price = 2500,
                currency = "₽",
                name = "BRANDFREE",
                section = "Cleaning",
                youRequested = 0,
                currentAmountRequests = 10,
                minAmountRequests = 320,
                image = R.drawable.wcatalog_image2.toBitmap(this).likeString(),
                favourite = false,
                description = "You can get acquainted with the ways of using the oxygen cleaner on the packaging or on our card. The powder is diluted with boiling water, as a result of which the reaction of the release of active oxygen begins, which contributes to the splitting of contaminants. We do not recommend using it for products made of wool, silk, down and feather, leather, suede, nubuck, membrane fabrics, copper, aluminum. Cleaning the house now does not take much time and effort, because you will have our Brandfree miracle cleaner!",
            ),
            CatalogItem(
                id = 4,
                price = 1499,
                currency = "₽",
                name = "Likato Professional",
                section = "Face spray",
                youRequested = 0,
                currentAmountRequests = 461,
                minAmountRequests = 1500,
                image = R.drawable.catalog_example_item.toBitmap(this).likeString(),
                favourite = true,
                description = resources.getString(R.string.example_description1),
            ),
            CatalogItem(
                id = 5,
                price = 50,
                currency = "$",
                name = "Crocs",
                section = "Shoe",
                youRequested = 20,
                currentAmountRequests = 33,
                minAmountRequests = 2000,
                image = R.drawable.wcatalog_image5.toBitmap(this).likeString(),
                favourite = false,
                description = "Get acquainted with the ways of using the oxygen cleaner on the packaging or on our card. The powder is diluted with boiling water, as a result of which the reaction of the release of active oxygen begins, which contributes to the splitting of contaminants. We do not recommend using it for products made of wool, silk, down and feather, leather, suede, nubuck, membrane fabrics, copper, aluminum. Cleaning the house now does not take much time and effort, because you will have our Brandfree miracle cleaner!",
            ),
            CatalogItem(
                id = 6,
                price = 1450,
                currency = "$",
                name = "Realme2",
                section = "Smartphones",
                youRequested = 0,
                currentAmountRequests = 13,
                minAmountRequests = 70,
                image = R.drawable.wcatalog_image6.toBitmap(this).likeString(),
                favourite = false,
                description = "Get acquainted with the ways of using the oxygen cleaner on the packaging or on our card. The powder is diluted with boiling water, as a result of which the reaction of the release of active oxygen begins, which contributes to the splitting of contaminants. We do not recommend using it for products made of wool, silk, down and feather, leather, suede, nubuck, membrane fabrics, copper, aluminum. Cleaning the house now does not take much time and effort, because you will have our Brandfree miracle cleaner!",
                isMine = true,
            ),
            CatalogItem(
                id = 7,
                price = 18,
                currency = "$",
                name = "Sanfor",
                section = "Cleaning",
                youRequested = 0,
                currentAmountRequests = 116,
                minAmountRequests = 212,
                image = R.drawable.wcatalog_image7.toBitmap(this).likeString(),
                favourite = false,
                description = "I am your remedy for everything! The Brandfree universal miracle cleaner effectively combats various contaminants. Any housewife will appreciate this ecological bleach, because it is an ideal tool for the home. Suitable for washing white, colored and black clothes. It perfectly removes old stains from clothes, stains from sweat and deodorant, herbs, berries, coffee, puree, fat, oil, blood, wine, drinks, any organic contamination, and also returns the white color to yellowed things.\n" +
                        "\n" +
                        "It does not contain chlorine, due to which the clothes do not lose color. It does not contain perfumes, so it is safe for allergy sufferers and newborns. The cleaner is suitable for bathroom and plumbing, toilet and pipes, furniture and carpets, dishes and cutlery, gold and silver products without stones, will cope with mold and mildew.\n" +
                        "\n" +
                        "Brandfree bleach is included in the category of “household chemicals\" and has more than 3000 reviews. It is hypoallergenic and odorless, chlorine-free. Effectively removes dirt from various surfaces: tiles, glass, stone, plastic, rubber, enameled surfaces, stainless steel, earthenware, glass ceramics, upholstered furniture and carpets. It has a disinfecting effect and neutralizes unpleasant odors. The universal stain remover has more than 100 ways of application.\n" +
                        "\n" +
                        "You can get acquainted with the ways of using the oxygen cleaner on the packaging or on our card. The powder is diluted with boiling water, as a result of which the reaction of the release of active oxygen begins, which contributes to the splitting of contaminants. We do not recommend using it for products made of wool, silk, down and feather, leather, suede, nubuck, membrane fabrics, copper, aluminum. Cleaning the house now does not take much time and effort, because you will have our Brandfree miracle cleaner!",
            ),
        )
    }
}