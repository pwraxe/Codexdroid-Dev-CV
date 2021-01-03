package com.codexdroid.codexdroiddevcv.fragments


import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.os.Vibrator
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import com.codexdroid.codexdroiddevcv.R
import com.codexdroid.codexdroiddevcv.databinding.FragmentContactBinding
import com.codexdroid.codexdroiddevcv.databinding.MenuEducationBinding
import com.codexdroid.codexdroiddevcv.databinding.MenuMyselfBinding
import com.codexdroid.codexdroiddevcv.extra_utility.UserData
import com.codexdroid.codexdroiddevcv.extra_utility.VibrationProvider
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.database.FirebaseDatabase
import com.yarolegovich.slidingrootnav.SlideGravity
import com.yarolegovich.slidingrootnav.SlidingRootNav
import com.yarolegovich.slidingrootnav.SlidingRootNavBuilder
import me.samlss.bloom.Bloom
import me.samlss.bloom.effector.BloomEffector
import tyrantgit.explosionfield.ExplosionField

class ContactFragment : Fragment(){

    private lateinit var binding : FragmentContactBinding
    private val message = "Hello Akshay, I just viewed your CV :)"
    private lateinit var explodeField : ExplosionField

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_contact, container, false)

        explodeField = ExplosionField(context)

        binding.toolbar.setNavigationOnClickListener {
            VibrationProvider(requireContext()).vibrateFor50M()
            activity?.onBackPressed()
        }

        binding.idSubmitButton.setOnClickListener { getValidData() }

        binding.idContactEmailText.setOnClickListener {
            val vibrator = context?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            vibrator.vibrate(50)
            openEmail() }

        return binding.root
    }



    private fun openEmail(){

        //Uri.parse("mailto:developer.codexdroid@gmail.com")
        val intent = Intent(Intent.ACTION_SEND)
        intent.data = Uri.parse("mailto:")
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf("developer.codexdroid@gmail.com"))
        intent.putExtra(Intent.EXTRA_SUBJECT,"Viewed your CV")
        intent.putExtra(Intent.EXTRA_TEXT,message)
        intent.type = "message/rfc822"
        intent.setPackage("com.google.android.gm")
        startActivity(Intent.createChooser(intent,"Send with : "))
    }

    private fun getValidData() {

        val userName = binding.idContactName.text.toString().trim()
        val userEmail = binding.idContactEmailAddress.text.toString().trim()
        val userMobile = binding.idContactMobile.text.toString().trim()
        val userMessage = binding.idContactMessage.text.toString().trim()

/** ---------------------------------------------------------------------------------------------**/
        if(userName.isEmpty()) {
            setBoxError(binding.idContactNameBox,"Name Cannot be Empty")
            return
        } else if(userName.length < 5 || userName.length > 30){
            setBoxError(binding.idContactNameBox,"Your name either too short or too long")
            return
        }else {

            for (ch in userName.toCharArray()){
                if(Character.isDigit(ch)) {
                    setBoxError(binding.idContactNameBox,"Name should not contain number")
                    return
                }
            }
            setBoxOK(binding.idContactNameBox)
            binding.idContactNameBox.endIconDrawable = resources.getDrawable(R.drawable.ic_ok,null)
        }
/** ---------------------------------------------------------------------------------------------**/
        if(userEmail.isEmpty()){
            setBoxError(binding.idContactEmailAddBox,"Please Specify your Valid Email ID")
            return
        }else if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches() || !userEmail.endsWith("@gmail.com")){
            setBoxError(binding.idContactEmailAddBox,"Invalid Email ID")
            return
        } else{
            setBoxOK(binding.idContactEmailAddBox)
            binding.idContactEmailAddBox.endIconDrawable = resources.getDrawable(R.drawable.ic_ok,null)
        }

/** ---------------------------------------------------------------------------------------------**/
        if(userMobile.isEmpty()){
            setBoxError(binding.idContactMobileBox,"Mobile No. Should not Empty")
            return
        }else if(userMobile.length != 10){
            setBoxError(binding.idContactMobileBox,"I need Exact 10 Digit Mobile Number")
            return
        }else{
            setBoxOK(binding.idContactMobileBox)
            binding.idContactMobileBox.endIconDrawable = resources.getDrawable(R.drawable.ic_ok,null)
        }

/** ---------------------------------------------------------------------------------------------**/
        if(userMessage.isEmpty()){
            setBoxError(binding.idContactMessageBox,"You left message box empty")
            return
        }else{
            setBoxOK(binding.idContactMessageBox)
            binding.idContactMessageBox.endIconDrawable = resources.getDrawable(R.drawable.ic_ok,null)
        }

        binding.idSubmitButton.visibility = View.GONE
        binding.idProgress.visibility = View.VISIBLE


        //get firebase obj & store data
        val fireRef = FirebaseDatabase.getInstance().getReference("UsersWhoContactedMe")
        fireRef.push().setValue(UserData(userName,userEmail,userMobile,userMessage))
            .addOnSuccessListener {
                binding.idLottieContactDone.visibility = View.VISIBLE
                binding.formDataContainer.visibility = View.GONE
                Toast.makeText(context, "Thanks for contacting me...\uD83D\uDE0E", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                binding.idLottieContactDone.visibility = View.GONE
                binding.formDataContainer.visibility = View.VISIBLE
                Toast.makeText(context, "Something went wrong, please mail me", Toast.LENGTH_SHORT).show()
            }
    }

    private fun setBoxError(til : TextInputLayout,errorMsg : String){
        til.error = errorMsg
        til.setErrorIconDrawable(R.drawable.ic_error)
        til.isErrorEnabled = true
        til.isCounterEnabled = true
        til.requestFocus()
    }

    private fun setBoxOK(til: TextInputLayout){
        til.isErrorEnabled = false
        til.boxBackgroundColor = Color.WHITE
        til.isHelperTextEnabled = false
        til.endIconDrawable = resources.getDrawable(R.drawable.ic_ok,null)

    }
}
