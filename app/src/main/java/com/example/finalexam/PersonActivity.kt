package com.example.finalexam

import android.content.Intent
import android.graphics.Color
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class PersonActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button

    private lateinit var scorexp:TextView
    private lateinit var scoreop:TextView

    private lateinit var nameEditText: EditText
    private lateinit var saveButton: Button
    private lateinit var nameTextView: TextView


    private var playerX = 0
    private var playerO = 0


    private var activePlayer = 1
    private var firstPlayer = ArrayList<Int>()
    private var secondPlayer = ArrayList<Int>()

    private var activedPlayer = 1
    private var setPlayer = 1


    private lateinit var passwordChangeButton: Button
    private lateinit var logoutButton: Button

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: DatabaseReference

    private fun resetAuto() {
        button1.setBackgroundColor(Color.RED)
        button2.setBackgroundColor(Color.RED)
        button3.setBackgroundColor(Color.RED)
        button4.setBackgroundColor(Color.RED)
        button5.setBackgroundColor(Color.RED)
        button6.setBackgroundColor(Color.RED)
        button7.setBackgroundColor(Color.RED)
        button8.setBackgroundColor(Color.RED)
        button9.setBackgroundColor(Color.RED)

        button1.text = ""
        button2.text = ""
        button3.text = ""
        button4.text = ""
        button5.text = ""
        button6.text = ""
        button7.text = ""
        button8.text = ""
        button9.text = ""

        firstPlayer.clear()
        secondPlayer.clear()
        activedPlayer = 1

        button1.isEnabled = true
        button2.isEnabled = true
        button3.isEnabled = true
        button4.isEnabled = true
        button5.isEnabled = true
        button6.isEnabled = true
        button7.isEnabled = true
        button8.isEnabled = true
        button9.isEnabled = true
        activePlayer = 1
        countClick = 0

        setPlayer = 1


    }

    fun resetGame(view: View)
    {

        button1.setBackgroundColor(Color.RED)
        button2.setBackgroundColor(Color.RED)
        button3.setBackgroundColor(Color.RED)
        button4.setBackgroundColor(Color.RED)
        button5.setBackgroundColor(Color.RED)
        button6.setBackgroundColor(Color.RED)
        button7.setBackgroundColor(Color.RED)
        button8.setBackgroundColor(Color.RED)
        button9.setBackgroundColor(Color.RED)

        button1.text = ""
        button2.text = ""
        button3.text = ""
        button4.text = ""
        button5.text = ""
        button6.text = ""
        button7.text = ""
        button8.text = ""
        button9.text = ""

        firstPlayer.clear()
        secondPlayer.clear()
        activedPlayer = 1

        button1.isEnabled = true
        button2.isEnabled = true
        button3.isEnabled = true
        button4.isEnabled = true
        button5.isEnabled = true
        button6.isEnabled = true
        button7.isEnabled = true
        button8.isEnabled = true
        button9.isEnabled = true
        activePlayer = 1
        countClick = 0

        setPlayer = 1

    }




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)


        mAuth = FirebaseAuth.getInstance()
        db = FirebaseDatabase.getInstance().getReference("UserInfo")

        nameEditText = findViewById(R.id.nameEditText)
        nameTextView = findViewById(R.id.nameTextView)
        saveButton = findViewById(R.id.saveButton)

        passwordChangeButton = findViewById(R.id.gotoPasswordChangeButton)
        logoutButton = findViewById(R.id.logoutButton)

        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val personInfo = PersonInfo(name)
            if (mAuth.currentUser?.uid != null) {
                db.child(mAuth.currentUser?.uid!!).setValue(personInfo).addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show()
                        nameEditText.text = null
                    }else {
                        Toast.makeText(this, "Error!", Toast.LENGTH_SHORT).show()
                    }
                }

            }
            if (mAuth.currentUser?.uid != null) {
                db.child(mAuth.currentUser?.uid!!)
                    .addValueEventListener(object : ValueEventListener {
                    override fun onCancelled(error: DatabaseError) {
                        Toast.makeText(this@PersonActivity, "Error!", Toast.LENGTH_SHORT).show()
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val p = snapshot.getValue(PersonInfo::class.java)
                        if (p != null) {
                            nameTextView.text = p.name
                        }

                    }


                })
            }

        }



        passwordChangeButton.setOnClickListener {
            startActivity(Intent(this, PasswordChangeActivity::class.java))
            finish()

        }
        logoutButton.setOnClickListener {
            mAuth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }

        init()
    }

    private fun init(){

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        scorexp = findViewById(R.id.scoreViewX)
        scoreop = findViewById(R.id.scoreViewO)


        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)

    }




    override fun onClick(clickedView: View?) {

        if(clickedView is Button){

            var buttonNumber = 0

            when(clickedView.id){
                R.id.button1 -> buttonNumber = 1
                R.id.button2 -> buttonNumber = 2
                R.id.button3 -> buttonNumber = 3
                R.id.button4 -> buttonNumber = 4
                R.id.button5 -> buttonNumber = 5
                R.id.button6 -> buttonNumber = 6
                R.id.button7 -> buttonNumber = 7
                R.id.button8 -> buttonNumber = 8
                R.id.button9 -> buttonNumber = 9
            }

            if(buttonNumber  != 0) {
                playGame(buttonNumber, clickedView)
            }


        }

    }



    private fun playGame(buttonNumber: Int, clickedView: Button) {




        if (activePlayer == 1) {
            clickedView.text = "X"
            clickedView.setBackgroundColor(Color.GRAY)
            firstPlayer.add(buttonNumber)
            countClick +=1
            activePlayer = 2
        }

        else {
            clickedView.text = "O"
            clickedView.setBackgroundColor(Color.DKGRAY)
            secondPlayer.add(buttonNumber)
            activePlayer = 1
        }


        clickedView.isEnabled = false

        check()

    }



    private var countClick = 0

    private fun updateScore(){
        scoreop.text = playerO.toString()
        scorexp.text = playerX.toString()
    }

    private fun check() {





        var winnerPlayer = 0


        if (firstPlayer.contains(1) && firstPlayer.contains(2) && firstPlayer.contains(3)) {
            winnerPlayer = 1
            playerX++
        }
        if (secondPlayer.contains(1) && secondPlayer.contains(2) && secondPlayer.contains(3)) {
            winnerPlayer = 2
            playerO++
        }
        if (firstPlayer.contains(4) && firstPlayer.contains(5) && firstPlayer.contains(6)) {
            winnerPlayer = 1
            playerX++
        }
        if (secondPlayer.contains(4) && secondPlayer.contains(5) && secondPlayer.contains(6)) {
            winnerPlayer = 2
            playerO++
        }
        if (firstPlayer.contains(7) && firstPlayer.contains(8) && firstPlayer.contains(9)) {
            winnerPlayer = 1
            playerX++
        }
        if (secondPlayer.contains(7) && secondPlayer.contains(8) && secondPlayer.contains(9)) {
            winnerPlayer = 2
            playerO++
        }
        if (firstPlayer.contains(1) && firstPlayer.contains(4) && firstPlayer.contains(7)) {
            winnerPlayer = 1
            playerX++
        }
        if (secondPlayer.contains(1) && secondPlayer.contains(4) && secondPlayer.contains(7)) {
            winnerPlayer = 2
            playerO++
        }
        if (firstPlayer.contains(2) && firstPlayer.contains(5) && firstPlayer.contains(8)) {
            winnerPlayer = 1
            playerX++
        }
        if (secondPlayer.contains(2) && secondPlayer.contains(5) && secondPlayer.contains(8)) {
            winnerPlayer = 2
            playerO++
        }
        if (firstPlayer.contains(3) && firstPlayer.contains(6) && firstPlayer.contains(9)) {
            winnerPlayer = 1
            playerX++
        }
        if (secondPlayer.contains(3) && secondPlayer.contains(6) && secondPlayer.contains(9)) {
            winnerPlayer = 2
            playerO++
        }
        if (firstPlayer.contains(1) && firstPlayer.contains(5) && firstPlayer.contains(9)) {
            winnerPlayer = 1
            playerX++
        }
        if (secondPlayer.contains(1) && secondPlayer.contains(5) && secondPlayer.contains(9)) {
            winnerPlayer = 2
            playerO++
        }
        if (firstPlayer.contains(3) && firstPlayer.contains(5) && firstPlayer.contains(7)) {
            winnerPlayer = 1
            playerX++
        }
        if (secondPlayer.contains(3) && secondPlayer.contains(5) && secondPlayer.contains(7)) {
            winnerPlayer = 2
            playerO++
        }

        if(countClick == 5 && winnerPlayer == 0){
            Toast.makeText(this, "ფრეა!! ", Toast.LENGTH_LONG).show()
            resetAuto()
        }


        if(winnerPlayer != 0){
            if(winnerPlayer == 1){
                Toast.makeText(this, "X wins", Toast.LENGTH_LONG).show()
            }
            if (winnerPlayer == 2){
                Toast.makeText(this, "O wins", Toast.LENGTH_LONG).show()
            }
            updateScore()
            dissableButtons()
        }

    }

    private fun dissableButtons(){

        button1.isEnabled = false
        button2.isEnabled = false
        button3.isEnabled = false
        button4.isEnabled = false
        button5.isEnabled = false
        button6.isEnabled = false
        button7.isEnabled = false
        button8.isEnabled = false
        button9.isEnabled = false

    }
}