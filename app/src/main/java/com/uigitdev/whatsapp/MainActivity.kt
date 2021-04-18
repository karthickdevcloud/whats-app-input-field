package com.uigitdev.whatsapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.uigitdev.whats_app_input_field.R

class MainActivity : AppCompatActivity() {
    private lateinit var inputField: InputField

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initInputField()
    }

    private fun initInputField(){
        initType()
        initStyle()
        initButton()
    }

    private fun initType(){
        inputField = findViewById(R.id.element)
    }

    private fun initStyle(){
        inputField.setHint("Type a message")
        inputField.setHintColor("#757C82")
        inputField.setTextColor("#FFFEFF")
        inputField.setItemBackgroundColor("#2C373D")
        inputField.setCameraVectorIcon(R.drawable.ic_photo_camera_black_24dp, "#757C82")
        inputField.setAttachmentVectorIcon(R.drawable.ic_attach_file_black_24dp, "#757C82")
        inputField.setEmojiVectorIcon(R.drawable.ic_insert_emoticon_black_24dp, "#757C82")
        inputField.setMicBackgroundColor("#01AF9B")
        inputField.setMicVectorIcon(R.drawable.ic_mic_black_24dp)
        inputField.setMicIconColor("#FFFEFF")
        inputField.setMaxLineCount(6)
        inputField.changeDefaultIcons(R.drawable.ic_mic_black_24dp, R.drawable.ic_send_black_24dp)
    }

    private fun initButton(){
        inputField.getCamera()!!.setOnClickListener {
            Toast.makeText(this, "Camera click", Toast.LENGTH_LONG).show()
        }

        inputField.getAttachment()!!.setOnClickListener {
            Toast.makeText(this, "Attachment click", Toast.LENGTH_LONG).show()
        }

        inputField.getEmoji()!!.setOnClickListener {
            Toast.makeText(this, "Emoji click", Toast.LENGTH_LONG).show()
        }

        inputField.getButton()!!.setOnClickListener {
            if (inputField.getButtonStatus() === InputField.BUTTON_STATUS.MIC_BUTTON) {
                Toast.makeText(this, "Mic click", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Send click, Text: " + inputField.getInputText(), Toast.LENGTH_LONG).show()
                inputField.setInputText("")
            }
        }
    }
}