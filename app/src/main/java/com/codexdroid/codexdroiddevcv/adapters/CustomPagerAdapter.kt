package com.codexdroid.codexdroiddevcv.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.viewpager.widget.PagerAdapter
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.activities.HomeActivity
import com.jem.liquidswipe.base.LiquidSwipeLayout
import com.jem.liquidswipe.clippathprovider.LiquidSwipeClipPathProvider

class CustomPagerAdapter(
    private val context: Context,
    private val liquidSwipeClipPathProvider: Array<LiquidSwipeClipPathProvider>) : PagerAdapter() {

    private lateinit var lottieAnim : LottieAnimationView
    private lateinit var textView : TextView
    private lateinit var subText : TextView
    private lateinit var pageCount : TextView
    private lateinit var skipButton : Button
    private lateinit var letsGoButton: Button


    private var textColor = arrayListOf(
        Color.parseColor("#000000"),
        Color.parseColor("#ffffff"),
        Color.parseColor("#000000"),
        Color.parseColor("#ffffff"),
        Color.parseColor("#000000"))

    private var lottieFiles = arrayOf(
        R.raw.hello_welcome,
        R.raw.girl_boy_education,
        R.raw.camera_music_pic,
        R.raw.code_failuar,
        R.raw.happy_monkey_dancing)

    private var textList = arrayOf(
        context.getString(R.string.hello_world),
        context.getString(R.string.education),
        context.getString(R.string.activities),
        context.getString(R.string.projects),
        context.getString(R.string.codexdroid))

    private var subTextList = arrayOf(
        context.getString(R.string.welcome2cv),
    context.getString(R.string.education_desc),
    context.getString(R.string.activity_desc),
    context.getString(R.string.project_desc),
    context.getString(R.string.my_brand_desc))

    val bgColor: ArrayList<Int> = arrayListOf(
        Color.parseColor("#ffffff"),
        Color.parseColor("#0000a0"),
        Color.parseColor("#ffffff"),
        Color.parseColor("#0000a0"),
        Color.parseColor("#ffffff")
    )

    var count = arrayOf("1","2","3","4","5")


    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layout = LayoutInflater.from(context).inflate(R.layout.fragment_start_page1,container,false)
        layout.setBackgroundColor(bgColor[position])

        lottieAnim = layout.findViewById(R.id.id_lottieStartPage1)
        textView = layout.findViewById(R.id.id_textView)
        subText = layout.findViewById(R.id.id_textDesc)
        pageCount = layout.findViewById(R.id.id_pageCount)
        skipButton = layout.findViewById(R.id.id_skipButton)
        letsGoButton = layout.findViewById(R.id.id_letsGoButton)


        lottieAnim.setAnimation(lottieFiles[position])
        lottieAnim.repeatCount = LottieDrawable.INFINITE
        lottieAnim.repeatMode = LottieDrawable.REVERSE
        lottieAnim.playAnimation()


        textView.text = textList[position]
        textView.setTextColor(textColor[position])
        subText.text = subTextList[position]
        subText.setTextColor(textColor[position])

        pageCount.text = "${count[position]}/5"
        pageCount.setTextColor(textColor[position])

        if(count[position] == "5"){
            letsGoButton.visibility = View.VISIBLE
            skipButton.visibility = View.GONE
        }
        letsGoButton.setOnClickListener {
            context.startActivity(Intent(context,HomeActivity::class.java))
            (context as Activity).finishAffinity()
        }
        skipButton.setTextColor(textColor[position])
        skipButton.setOnClickListener {

            context.startActivity(Intent(context,HomeActivity::class.java))
            (context as Activity).finishAffinity()
        }


        (layout as LiquidSwipeLayout).clipPathProvider = liquidSwipeClipPathProvider[position]

        container.addView(layout)
        return layout

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean  = `object` == view

    override fun getCount(): Int = 5

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}