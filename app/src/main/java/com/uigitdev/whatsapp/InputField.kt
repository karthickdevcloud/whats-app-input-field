package com.uigitdev.whatsapp

import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.PorterDuff
import android.text.Editable
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import com.uigitdev.whats_app_input_field.R

class InputField(context: Context, attributeSet: AttributeSet): RelativeLayout(context, attributeSet) {
    private var inputParent: RelativeLayout? = null
    private var fieldInput: EditText? = null
    private var cardParent: CardView? = null
    private var iconCamera: ImageView? = null
    private var iconAttachment: ImageView? = null
    private var iconEmoji: ImageView? = null
    private var iconMicCardParent: CardView? = null
    private var iconMicClickParent: RelativeLayout? = null
    private var iconMicIcon: ImageView? = null
    private var lineCount = 1
    private var buttonStatus: BUTTON_STATUS = BUTTON_STATUS.MIC_BUTTON

    enum class BUTTON_STATUS {
        MIC_BUTTON, SEND_BUTTON
    }

    private var defaultMicIcon: Int = R.drawable.ic_mic_black_24dp
    private var defaultSendIcon: Int = R.drawable.ic_send_black_24dp

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.ui_input_field, this, true)
        setType(view)
        textChange()
    }

    private fun setType(view: View) {
        inputParent = view.findViewById(R.id.item_input_parent)
        fieldInput = view.findViewById(R.id.item_input)
        cardParent = view.findViewById(R.id.item_input_card_parent)
        iconCamera = view.findViewById(R.id.item_camera_icon)
        iconAttachment = view.findViewById(R.id.item_attachment_icon)
        iconEmoji = view.findViewById(R.id.item_emoji_icon)
        iconMicCardParent = view.findViewById(R.id.item_mic_card_parent)
        iconMicClickParent = view.findViewById(R.id.item_mic_click_parent)
        iconMicIcon = view.findViewById(R.id.item_mic_icon)
    }

    private fun textChange() {
        fieldInput!!.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                autoResize()
                buttonStyle()
            }

            override fun afterTextChanged(s: Editable) {}
        })
    }

    private fun autoResize() {
        if (fieldInput!!.lineCount <= fieldInput!!.maxLines) {
            if (fieldInput!!.lineCount != lineCount) {
                inputParent!!.layoutParams.height =
                    dpToPixel(fieldInput!!.layoutParams.height)
                lineCount = fieldInput!!.lineCount
            }
        }
    }

    private fun buttonStyle() {
        if (fieldInput!!.text.isNotEmpty()) {
            buttonStatus = BUTTON_STATUS.SEND_BUTTON
            setMicVectorIcon(defaultSendIcon)
        } else {
            buttonStatus = BUTTON_STATUS.MIC_BUTTON
            setMicVectorIcon(defaultMicIcon)
        }
    }

    private fun dpToPixel(dp: Int): Int {
        return (dp * Resources.getSystem().displayMetrics.density).toInt()
    }

    fun setItemBackgroundColor(color: String?) {
        cardParent!!.setCardBackgroundColor(Color.parseColor(color))
    }

    fun setCameraVectorIcon(iconId: Int, color: String?) {
        iconCamera!!.setImageResource(iconId)
        iconCamera!!.setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
    }

    fun setAttachmentVectorIcon(iconId: Int, color: String?) {
        iconAttachment!!.setImageResource(iconId)
        iconAttachment!!.setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
    }

    fun setEmojiVectorIcon(iconId: Int, color: String?) {
        iconEmoji!!.setImageResource(iconId)
        iconEmoji!!.setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
    }

    fun setMicBackgroundColor(color: String?) {
        iconMicCardParent!!.setCardBackgroundColor(Color.parseColor(color))
    }

    fun setMicVectorIcon(iconId: Int) {
        iconMicIcon!!.setImageResource(iconId)
    }

    fun setMicIconColor(color: String?) {
        iconMicIcon!!.setColorFilter(Color.parseColor(color), PorterDuff.Mode.SRC_IN)
    }

    fun setMaxLineCount(maxLineCount: Int) {
        fieldInput!!.maxLines = maxLineCount
    }

    fun setHint(hint: String?) {
        fieldInput!!.hint = hint
    }

    fun setHintColor(color: String?) {
        fieldInput!!.setHintTextColor(Color.parseColor(color))
    }

    fun setTextColor(color: String?) {
        fieldInput!!.setTextColor(Color.parseColor(color))
    }

    fun changeDefaultIcons(defaultMicIcon: Int, defaultSendIcon: Int) {
        this.defaultMicIcon = defaultMicIcon
        this.defaultSendIcon = defaultSendIcon
    }

    fun getButtonStatus(): BUTTON_STATUS? {
        return buttonStatus
    }

    fun setInputText(text: String?) {
        fieldInput!!.setText(text)
    }

    fun getInputText(): String? {
        return fieldInput!!.text.toString()
    }

    fun getCamera(): ImageView? {
        return iconCamera
    }

    fun getAttachment(): ImageView? {
        return iconAttachment
    }

    fun getEmoji(): ImageView? {
        return iconEmoji
    }

    fun getButton(): RelativeLayout? {
        return iconMicClickParent
    }
}