package hu.bme.aut.bang

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_role_list.*

class RoleListActivity : AppCompatActivity() {

    companion object {
        const val SHERIFF = 1
        const val VICE = 2
        const val BANDIT = 3
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_list)

        //amelyik gomra rányomunk, aszerint adja tovább a következő activitynek a szerepet
        btnSheriff.setOnClickListener{

            val intent = Intent(this, ChooseActivity::class.java)
            intent.putExtra(ChooseActivity.ROLE_TYPE, SHERIFF)
            startActivity(intent)

        }

        btnVice.setOnClickListener{
            val intent = Intent(this, ChooseActivity::class.java)
            intent.putExtra(ChooseActivity.ROLE_TYPE, VICE)
            startActivity(intent)
        }

        btnBandit.setOnClickListener {
            val intent = Intent(this, ChooseActivity::class.java)
            intent.putExtra(ChooseActivity.ROLE_TYPE, BANDIT)
            startActivity(intent)
        }
    }
}