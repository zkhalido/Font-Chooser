package com.example.fontchooser

import android.graphics.Typeface
import android.graphics.Typeface.BOLD
import android.graphics.Typeface.BOLD_ITALIC
import android.graphics.Typeface.ITALIC
import android.graphics.Typeface.NORMAL
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Half.toFloat
import android.util.Log
import android.util.TypedValue
import android.util.TypedValue.COMPLEX_UNIT_SP
import android.view.View
import android.widget.*
import com.divyanshu.colorseekbar.ColorSeekBar
import kotlinx.android.synthetic.main.activity_main.*
import java.time.format.TextStyle

class MainActivity : AppCompatActivity() {

    var TAG = "Main Acitvity"

    val typeFaceArray = arrayOf("Default", "Default Bold", "Monospace", "Sans Serif", "Serif")
    val styleArray = arrayOf("Bold", "Bold Italic", "Italic", "Normal")

    var typeFaceField: Typeface = Typeface.DEFAULT
    lateinit var typeface: Typeface
    lateinit var textStyle: TextStyle
    var textSize = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d(TAG," On Create")

        var defaultText = findViewById<TextView>(R.id.textString)

        color_seek_bar.setOnColorChangeListener(object : ColorSeekBar.OnColorChangeListener {
            override fun onColorChangeListener(color: Int) {
                //gives the selected color
                defaultText.setTextColor(color)
            }
        })

        val typeFaceSpinner = findViewById<Spinner>(R.id.spinnerTypeface)
        if (typeFaceSpinner != null) {
            Log.d(TAG," Type Face Spinner")
            val arrayAdapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, typeFaceArray)
            typeFaceSpinner.adapter = arrayAdapter

            typeFaceSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if (position == 0){
                        defaultText.setTypeface(Typeface.DEFAULT)
                        typeFaceField = Typeface.DEFAULT
                    }
                    if (position == 1){
                        defaultText.setTypeface(Typeface.DEFAULT_BOLD)
                        typeFaceField = Typeface.DEFAULT_BOLD
                    }
                    if (position == 2){
                        defaultText.setTypeface(Typeface.MONOSPACE)
                        typeFaceField = Typeface.MONOSPACE
                    }
                    if (position == 3){
                        defaultText.setTypeface(Typeface.SANS_SERIF)
                        typeFaceField = Typeface.SANS_SERIF
                    }
                    if (position == 4){
                        defaultText.setTypeface(Typeface.SERIF)
                        typeFaceField = Typeface.SERIF
                    }
                }
            }
        }

        val textStyleSpinner = findViewById<Spinner>(R.id.spinnerStyle)
        if (textStyleSpinner != null) {
            Log.d(TAG," Type Face Spinner")
            val arrayAdapter =
                ArrayAdapter(this, android.R.layout.simple_spinner_item, styleArray)
            textStyleSpinner.adapter = arrayAdapter

            textStyleSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                    if (position == 0){
                        defaultText.setTypeface(typeFaceField, BOLD)
                    }
                    if (position == 1){
                        defaultText.setTypeface(typeFaceField, BOLD_ITALIC)
                    }
                    if (position == 2){
                        defaultText.setTypeface(typeFaceField, ITALIC)
                    }
                    if (position == 3){
                        defaultText.setTypeface(typeFaceField, NORMAL)
                    }
                }
            }
        }

        val textSizeEdit = findViewById<EditText>(R.id.editTextSize)
        textSizeEdit.addTextChangedListener(object : TextWatcher {
            var textSize: String = ""
            override fun onTextChanged(p0: CharSequence, p1: Int, p2: Int, p3: Int) {
                Log.d(TAG, " OnTextChanged")
                textSize = p0.toString()
                // Prevents from crashing when float = ""
                if (textSize == ""){
                    Log.d(TAG, " Changing Float to 0")
                    textSize = "0"
                }
                // Does not allow text size above 26sp
                if (textSize.toInt() > 26) {
                    textSize = "26"
                }
                defaultText.setTextSize(textSize.toString().toFloat())

            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun afterTextChanged(p0: Editable?) {
            }
        })

        }
    }

