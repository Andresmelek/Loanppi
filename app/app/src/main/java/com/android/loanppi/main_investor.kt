package com.android.loanppi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import kotlinx.android.synthetic.main.fragment_main_investor.*

/**
 * A simple [Fragment] subclass.
 * Use the [main_investor.newInstance] factory method to
 * create an instance of this fragment.
 */
class main_investor(bundle: Bundle?) : Fragment() {
    private lateinit var accessInfo: Bundle
    private lateinit var account: Bundle
    private var bundle: Bundle? = bundle

    // Main worker buttons
    private lateinit var btn_invest: Button
    private lateinit var btn_my_investment: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_main_investor, container, false)

        btn_invest = view.findViewById(R.id.btn_invest)
        btn_my_investment = view.findViewById(R.id.btn_my_investments)

        accessInfo = bundle?.getBundle("accessInfo") as Bundle
        account = bundle?.getBundle("account") as Bundle

        val firstName: String? = account.getString("firstName")

        var urlPhoto: String? = ""
        if (accessInfo.get("accessWith") == "facebook") {
            val facebookAccount = accessInfo.get("facebookAccount") as Bundle
            urlPhoto = facebookAccount.get("urlUserPhoto") as String
        } else {
            val googleAccount = accessInfo.get("googleAccount") as GoogleSignInAccount
            urlPhoto = googleAccount.photoUrl.toString()
        }

        view.findViewById<TextView>(R.id.txt_grettings).setText("Hola, "+ firstName)
        Glide.with(this).load(urlPhoto).into(view.findViewById(R.id.img_user_photo))

        btn_invest.setOnClickListener(View.OnClickListener {
            if (account.get("investStack").toString().toFloat() >= 50000) {
                replaceFragment(invest_options(account), parentFragmentManager)
            }
        })

        btn_my_investment.setOnClickListener(View.OnClickListener {
            replaceFragment(my_investment_options(bundle), parentFragmentManager)
        })

        return view
    }
}
