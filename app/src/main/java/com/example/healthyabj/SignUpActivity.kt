package com.example.healthyabj

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.CalendarView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.signin.*
import java.util.*

import com.google.firebase.firestore.Query

class SignUpActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signin)
        var num = 2


        val email =  SignInEmail.text.toString()
        val nome = SignInName.text.toString()
        val password = SignInPassword.text.toString()
        val dataNascimento = signinBirthdate.text.toString()
        //val cc = signinCC.text.toString()

        auth= FirebaseAuth.getInstance()

        SignInFoto.setOnClickListener{
            Log.d("SignupActivity","Try to show photo selector")
            val intent = Intent(Intent.ACTION_PICK)
            intent.type="image/*"
            startActivityForResult(intent,0)


        }

        signinbtVoltar.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }


        signinBirthdate.setOnClickListener{
            val builder = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.dialog_calendario_signup,null)
            val nascimento = view.findViewById<CalendarView>(R.id.calendarioselecionar)
            builder.setView(view)
            builder.setPositiveButton("Ok", DialogInterface.OnClickListener { _, _->

            })
            builder.setNegativeButton("Cancelar", DialogInterface.OnClickListener { _, _->

            })

            builder.show()
        }




        //Verifica se alguns dos campos estao vazios, se estiver manda mensagem de erro
        SignInCreateAccount.setOnClickListener {

                auth.createUserWithEmailAndPassword(SignInEmail.text.toString(), SignInPassword.text.toString())
                .addOnCompleteListener(this) {task ->
                    if (task.isSuccessful) {
                        //Caso nao exista nenhum erro ao criar conta, vai para a tela de login
                        // Sign in success, update UI with the signed-in user's information

                        uplaodImageToFirebaseStorage()

                        val user = auth.currentUser

                       // startActivity(Intent(this,MainActivity::class.java))


                        startActivity(Intent(this,MainActivity::class.java))




                     //   SaveData()


                    } else {
                        //Caso exista algum erro ao criar conta, manda mensagem de erro
                        // If sign in fails, display a message to the user.
                        Toast.makeText(baseContext, "Error 404, please try again! ", Toast.LENGTH_SHORT).show()
                    }
                }


        }


    }








    var selectedPhotoUri: Uri?= null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?){
        super.onActivityResult(requestCode, resultCode,data)
        if (requestCode == 0 && resultCode == Activity.RESULT_OK && data !=null)
            Log.d("SignupActivity"," photo selected")
            selectedPhotoUri=data?.data
        val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, selectedPhotoUri)
        selectphoto_imageview.setImageBitmap(bitmap)
        //selectphoto_imageview.rotation = 90
        SignInFoto.alpha =0f
    }

    private fun uplaodImageToFirebaseStorage () {
        if (selectedPhotoUri == null)return
        val filename = UUID.randomUUID().toString()
        val reffoto = FirebaseStorage.getInstance().getReference("/images/$filename")
        reffoto.putFile(selectedPhotoUri!!)
            .addOnSuccessListener {
                Log.d("SignupActivity","Successfully uploaded image: ${it.metadata?.path}")
                reffoto.downloadUrl.addOnSuccessListener {
                    Log.d("SignupActivity","File Location $it")
                    saveUserToFirabaseDatabase(it.toString())
                }
            }


    }


    private fun saveUserToFirabaseDatabase(profileImageUrl:String){
        val database = Firebase
        val uid = FirebaseAuth.getInstance().uid
        val ref = FirebaseFirestore.getInstance()
        //val users = User.User(SignInEmail.text.toString(),SignInPassword.text.toString(),SignInName.text.toString())
        val users = User.User (uid.toString(), SignInEmail.text.toString(),SignInName.text.toString() ,SignInPassword.text.toString(),profileImageUrl,0)

val cena = SignInEmail.text.toString()

        ref.collection("User").document(cena)
            .set(users)
            .addOnSuccessListener {  }
            .addOnFailureListener{}







//        ref.setValue(users)
//            .addOnSuccessListener {
//                Log.d("SignupActivity","Finaly we saved the user to firebase ")
//            }

        // ref.child("users").setValue(users)
        // ref.child("/Users/$uid").setValue(users)
        //ref.child("/Users/$uid").setValue(user)
        //database.child("users").child(userId).setValue(user)
    }





}












