package com.example.codepath_android_prework

import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.res.ResourcesCompat

private const val TAG = "ActivityMain"

class MainActivity : AppCompatActivity() {

    private lateinit var etFirstName: EditText
    private lateinit var etLastName: EditText
    private lateinit var tvFirstName: TextView
    private lateinit var tvLastName: TextView
    private lateinit var tvGreetingLabel: TextView
    private lateinit var tvTraitLabel: TextView
    private lateinit var tvSomeAboutMe: TextView
    private lateinit var rgStyle: RadioGroup
    private lateinit var btnStyle: RadioButton
    private lateinit var btnSomeAboutMe: Button
    private lateinit var ivSomeAboutMe: ImageView
    private lateinit var tvFavoriteColor: TextView
    private lateinit var npFavoriteColor: NumberPicker
    private lateinit var viewLeftSide: View
    private lateinit var viewRightSide: View
    private lateinit var npFavoriteActivity: NumberPicker
    private lateinit var ivActivity: ImageView
    private lateinit var tvFavoriteActivity: TextView
    private lateinit var tvFavoriteMovie: TextView
    private lateinit var npFavoriteMovie: NumberPicker
    private lateinit var ivMovie: ImageView
    private lateinit var btnSummaryLaunch: Button
    private lateinit var ivLeftSideDialog: ImageView
    private lateinit var ivRightSideDialog: ImageView
    private lateinit var viewCover: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etFirstName = findViewById(R.id.etFirstName)
        etLastName = findViewById(R.id.etLastName)
        tvFirstName = findViewById(R.id.tvFirstName)
        tvLastName = findViewById(R.id.tvLastName)
        tvGreetingLabel = findViewById(R.id.tvGreetingLabel)
        tvTraitLabel = findViewById(R.id.tvTraitLabel)
        tvSomeAboutMe = findViewById(R.id.tvSomeAboutMeLabel)
        rgStyle = findViewById(R.id.rgStyle)
        btnStyle = findViewById(R.id.btnClassic)
        btnSomeAboutMe = findViewById(R.id.btnSomeAboutMe)
        ivSomeAboutMe = findViewById(R.id.ivSomeAboutMe)
        tvFavoriteColor = findViewById(R.id.tvFavoriteColor)
        npFavoriteColor = findViewById(R.id.npFavoriteColor)
        viewLeftSide = findViewById(R.id.viewLeftSide)
        viewRightSide = findViewById(R.id.viewRightSide)
        npFavoriteActivity = findViewById(R.id.npFavoriteActivity)
        ivActivity = findViewById(R.id.ivActivity)
        tvFavoriteActivity = findViewById(R.id.tvFavoriteActivity)
        tvFavoriteMovie = findViewById(R.id.tvFavoriteMovie)
        npFavoriteMovie = findViewById(R.id.npFavoriteMovie)
        ivMovie = findViewById(R.id.ivMovie)
        btnSummaryLaunch = findViewById(R.id.btnSummaryLaunch)

        // This variable show the slide for the SomeAboutMe section
        var someAboutMeSlideNum = 0

        // Initialize String value for colorsPicker
        var colors = resources.getStringArray(R.array.colorsPicker)
        npFavoriteColor.minValue = 0
        npFavoriteColor.maxValue = 8
        npFavoriteColor.displayedValues = colors
        var chosenColor = ""

        // Initialize String value for activitiesPicker
        var activities = resources.getStringArray(R.array.activitiesPicker)
        npFavoriteActivity.minValue = 0
        npFavoriteActivity.maxValue = 7
        npFavoriteActivity.displayedValues = activities
        var chosenActivity = 0

        // Initialize String value for moviesPicker
        var movies = resources.getStringArray(R.array.moviesPicker)
        npFavoriteMovie.minValue = 0
        npFavoriteMovie.maxValue = 7
        npFavoriteMovie.displayedValues = movies
        var chosenMovie = 0

        rgStyle.setOnCheckedChangeListener { _, i ->
            var rb: RadioButton = findViewById<RadioButton>(i)
            if (rb != null) {
                changeFontStyleAndSize(rb.text.toString())
                Log.i(TAG, "Selected radio button: ${rb.text.toString()}")
            }
        }

        btnSomeAboutMe.setOnClickListener {
            Log.i(TAG, "SomeAboutMe Button Clicked!")
            changeImageAndLabel(someAboutMeSlideNum)
            someAboutMeSlideNum++
            if (someAboutMeSlideNum > 3)
                someAboutMeSlideNum = 0
        }

        npFavoriteColor.setOnValueChangedListener { _, _, newVal ->
            Log.i(TAG, "Selected color: ${colors[newVal]}")
            changeSideViewColor(colors[newVal])
            chosenColor = colors[newVal]
        }

        npFavoriteActivity.setOnValueChangedListener { _, _, newVal ->
            Log.i(TAG, "Selected activity: ${activities[newVal]}")
            changeActivityImage(newVal)
            chosenActivity = newVal
        }

        npFavoriteMovie.setOnValueChangedListener { _, _, newVal ->
            Log.i(TAG, "Selected movie: ${movies[newVal]}")
            changeMovieImage(newVal)
            chosenMovie = newVal
        }

        btnSummaryLaunch.setOnClickListener {
            val mapFormView = LayoutInflater.from(this).inflate(R.layout.dialog_intro_summary, null)
            val dialog = AlertDialog.Builder(this)
                .setView(mapFormView)
                .setNegativeButton("Nice Meeting you!", null)
                .show()

            var favActivity = activities[chosenActivity]
            if (favActivity.equals("Video game") || favActivity.equals("Sports"))
                favActivity = "play $favActivity "

            var summary =
                "Hello, my name is ${etFirstName.text} ${etLastName.text}. I was born in " +
                        "Vietnam. I had a little puppy named Oreo. Currently I'm living in Texas and studying " +
                        "at UTSA. My favorite color is ${chosenColor}. During my free time I like to ${activities[chosenActivity]}. " +
                        "I also love watching movies, one of my favorite is ${movies[chosenMovie]}. Thank you for " +
                        "watching."
            val tvSummaryText = dialog.findViewById(R.id.tvSummaryText) as TextView?
            tvSummaryText?.text = summary
            ivLeftSideDialog = dialog.findViewById(R.id.ivLeftSideDialog)!!
            ivRightSideDialog = dialog.findViewById(R.id.ivRightSideDialog)!!
            viewCover = dialog.findViewById(R.id.viewCover)!!
            changeActivityImageDialog(chosenActivity)
            changeMovieImageDialog(chosenMovie)
            changeCoverViewColorDialog(chosenColor)

            dialog.findViewById<Button>(R.id.btnFareWell)?.setOnClickListener {
                dialog.dismiss()
            }
        }
    }
    private fun changeCoverViewColorDialog(color: String) {
        when (color) {
            "White" -> viewCover.setBackgroundColor(Color.WHITE)
            "Red" -> viewCover.setBackgroundColor(Color.RED)
            "Blue" -> viewCover.setBackgroundColor(Color.BLUE)
            "Green" -> viewCover.setBackgroundColor(Color.GREEN)
            "Yellow" -> viewCover.setBackgroundColor(Color.YELLOW)
            "Magenta" -> viewCover.setBackgroundColor(Color.MAGENTA)
            "Gray" -> viewCover.setBackgroundColor(Color.GRAY)
            "Black" -> viewCover.setBackgroundColor(Color.BLACK)
            "Cyan" -> viewCover.setBackgroundColor(Color.CYAN)
        }
    }

    private fun changeMovieImageDialog(imageNum: Int) {
        when (imageNum) {
            0 -> ivLeftSideDialog.setImageResource(R.drawable.left_arrow)
            1 -> ivLeftSideDialog.setImageResource(R.drawable.dark_knight)
            2 -> ivLeftSideDialog.setImageResource(R.drawable.endgame)
            3 -> ivLeftSideDialog.setImageResource(R.drawable.the_godfather)
            4 -> ivLeftSideDialog.setImageResource(R.drawable.madmax)
            5 -> ivLeftSideDialog.setImageResource(R.drawable.die_hard)
            6 -> ivLeftSideDialog.setImageResource(R.drawable.wolf)
            7 -> ivLeftSideDialog.setImageResource(R.drawable.fight_club)
        }
    }

    private fun changeActivityImageDialog(imageNum: Int) {
        when (imageNum) {
            0 -> ivRightSideDialog.setImageResource(R.drawable.left_arrow)
            1 -> ivRightSideDialog.setImageResource(R.drawable.sports)
            2 -> ivRightSideDialog.setImageResource(R.drawable.travel)
            3 -> ivRightSideDialog.setImageResource(R.drawable.food)
            4 -> ivRightSideDialog.setImageResource(R.drawable.mario)
            5 -> ivRightSideDialog.setImageResource(R.drawable.reading)
            6 -> ivRightSideDialog.setImageResource(R.drawable.hiking)
            7 -> ivRightSideDialog.setImageResource(R.drawable.fishing)
        }
    }

    private fun changeMovieImage(imageNum: Int) {
        when (imageNum) {
            0 -> ivMovie.setImageResource(R.drawable.left_arrow)
            1 -> ivMovie.setImageResource(R.drawable.dark_knight)
            2 -> ivMovie.setImageResource(R.drawable.endgame)
            3 -> ivMovie.setImageResource(R.drawable.the_godfather)
            4 -> ivMovie.setImageResource(R.drawable.madmax)
            5 -> ivMovie.setImageResource(R.drawable.die_hard)
            6 -> ivMovie.setImageResource(R.drawable.wolf)
            7 -> ivMovie.setImageResource(R.drawable.fight_club)
        }
    }

    private fun changeActivityImage(imageNum: Int) {
        when (imageNum) {
            0 -> ivActivity.setImageResource(R.drawable.left_arrow)
            1 -> ivActivity.setImageResource(R.drawable.sports)
            2 -> ivActivity.setImageResource(R.drawable.travel)
            3 -> ivActivity.setImageResource(R.drawable.food)
            4 -> ivActivity.setImageResource(R.drawable.mario)
            5 -> ivActivity.setImageResource(R.drawable.reading)
            6 -> ivActivity.setImageResource(R.drawable.hiking)
            7 -> ivActivity.setImageResource(R.drawable.fishing)
        }
    }

    private fun changeSideViewColor(color: String) {
        when (color) {
            "White" -> {
                viewLeftSide.setBackgroundColor(Color.WHITE)
                viewRightSide.setBackgroundColor(Color.WHITE)
            }
            "Red" -> {
                viewLeftSide.setBackgroundColor(Color.RED)
                viewRightSide.setBackgroundColor(Color.RED)
            }
            "Blue" -> {
                viewLeftSide.setBackgroundColor(Color.BLUE)
                viewRightSide.setBackgroundColor(Color.BLUE)
            }
            "Green" -> {
                viewLeftSide.setBackgroundColor(Color.GREEN)
                viewRightSide.setBackgroundColor(Color.GREEN)
            }
            "Yellow" -> {
                viewLeftSide.setBackgroundColor(Color.YELLOW)
                viewRightSide.setBackgroundColor(Color.YELLOW)
            }
            "Magenta" -> {
                viewLeftSide.setBackgroundColor(Color.MAGENTA)
                viewRightSide.setBackgroundColor(Color.MAGENTA)
            }
            "Gray" -> {
                viewLeftSide.setBackgroundColor(Color.GRAY)
                viewRightSide.setBackgroundColor(Color.GRAY)
            }
            "Black" -> {
                viewLeftSide.setBackgroundColor(Color.BLACK)
                viewRightSide.setBackgroundColor(Color.BLACK)
            }
            "Cyan" -> {
                viewLeftSide.setBackgroundColor(Color.CYAN)
                viewRightSide.setBackgroundColor(Color.CYAN)
            }
        }
    }

    private fun changeImageAndLabel(slide: Int) {
        when (slide) {
            0 -> {
                ivSomeAboutMe.setImageResource(R.drawable.vietnamflag)
                tvSomeAboutMe.text = "I was born in Vietnam"
            }
            1 -> {
                ivSomeAboutMe.setImageResource(R.drawable.texasflag)
                tvSomeAboutMe.text = "My home is now in Texas"
            }
            2 -> {
                ivSomeAboutMe.setImageResource(R.drawable.oreo)
                tvSomeAboutMe.text = "I have a little puppy named Oreo"
            }
            3 -> {
                ivSomeAboutMe.setImageResource(R.drawable.utsalogo)
                tvSomeAboutMe.text = "I'm currently studying at UTSA"
            }
        }
    }

    private fun changeFontStyleAndSize(type: String) {
        var font: Int = 800000;
        var size: Float = 20F;
        when (type) {
            "Classic" -> {
                font = R.font.arapey
                size = 20F
            }
            "Stylish" -> {
                font = R.font.alex_brush
                size = 22F
            }
            "Bold" -> {
                font = R.font.archivo_black
                size = 16F
            }
        }
        val customFont: Typeface? = ResourcesCompat.getFont(this, font)
        tvFirstName.typeface = customFont
        tvFirstName.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        etFirstName.typeface = customFont
        etFirstName.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        etLastName.typeface = customFont
        etLastName.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        tvLastName.typeface = customFont
        tvLastName.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        tvGreetingLabel.typeface = customFont
        tvGreetingLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        tvTraitLabel.typeface = customFont
        tvTraitLabel.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        tvSomeAboutMe.typeface = customFont
        tvSomeAboutMe.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        tvFavoriteColor.typeface = customFont
        tvFavoriteColor.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        tvFavoriteActivity.typeface = customFont
        tvFavoriteActivity.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)
        tvFavoriteMovie.typeface = customFont
        tvFavoriteMovie.setTextSize(TypedValue.COMPLEX_UNIT_SP, size)

    }
}