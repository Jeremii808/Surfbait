package com.example.surfbait30.ui.honolii

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.surfbait30.INetworkAPI
import com.example.surfbait30.R
import com.example.surfbait30.TimestampConverter.getTodayDate
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.schedulers.IoScheduler
import kotlinx.android.synthetic.main.fragment_honolii.*

class HonoliiFragment : Fragment() {
    private lateinit var disposable: Disposable

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_honolii, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // MSW logo is placed above the used content as required by the API terms of use.
        // When clicked, the user is taken to MSW website
        val clickMe: ImageView = view.findViewById(R.id.powered_by_logo_honolii)
        clickMe.setOnClickListener {
            val i = Intent(Intent.ACTION_VIEW, Uri.parse("http://magicseaweed.com"))
            startActivity(i)
        }

        // Sets today's date
        val date: TextView = view.findViewById(R.id.dateToday_honolii)
        date.text = getTodayDate()

        rv_list_posts_honolii.layoutManager = LinearLayoutManager(requireContext())

        // Get all JSON posts for Honolii Beach, spot_id = 3815
        val response = INetworkAPI.create().getAllPosts("3815")

        disposable = response.observeOn(AndroidSchedulers.mainThread()).subscribeOn(IoScheduler())
            .subscribe {
                rv_list_posts_honolii.adapter = PostItemAdapterHonolii(it, requireContext())
            }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposable.dispose()
    }
}