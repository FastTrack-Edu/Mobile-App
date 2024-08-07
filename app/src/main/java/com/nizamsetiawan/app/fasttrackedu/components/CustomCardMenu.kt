package com.nizamsetiawan.app.fasttrackedu.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.nizamsetiawan.app.fasttrackedu.R

class CustomCardMenu @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val ivIcon: ImageView
    private val tvIconHeadline: TextView

    init {
        LayoutInflater.from(context).inflate(R.layout.custom_card_menu, this, true)
        ivIcon = findViewById(R.id.iv_icon)
        tvIconHeadline = findViewById(R.id.tv_icon_headline)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomCardMenu,
            0, 0
        ).apply {
            try {
                ivIcon.setImageDrawable(getDrawable(R.styleable.CustomCardMenu_iconSrc))
                tvIconHeadline.text = getString(R.styleable.CustomCardMenu_headlineText)
            } finally {
                recycle()
            }
        }
    }

    fun setIconSrc(resourceId: Int) {
        ivIcon.setImageResource(resourceId)
    }

    fun setHeadlineText(text: String) {
        tvIconHeadline.text = text
    }
}
