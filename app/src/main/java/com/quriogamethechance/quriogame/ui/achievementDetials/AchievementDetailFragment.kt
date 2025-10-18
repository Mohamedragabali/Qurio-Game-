package com.quriogamethechance.quriogame.ui.achievementDetials

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.quriogamethechance.quriogame.R
import com.quriogamethechance.quriogame.databinding.FragmentAchievementDetialBinding
import com.quriogamethechance.quriogame.presenter.achievement.Achievement
import com.quriogamethechance.quriogame.presenter.achievementDetiale.AchievementDetailsPresenter
import com.quriogamethechance.quriogame.ui.QurioApp
import com.quriogamethechance.quriogame.ui.base.BaseDialogFragment
import jakarta.inject.Inject

class AchievementDetailFragment : BaseDialogFragment<FragmentAchievementDetialBinding>(), AchievementDetailsViewInterface {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAchievementDetialBinding
        get() = FragmentAchievementDetialBinding::inflate


    @Inject
    lateinit var achievementDetailsPresenter: AchievementDetailsPresenter
    private lateinit var currentAchievement : Achievement
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp).appComponent.inject(this)
    }
    override fun setup() {
        achievementDetailsPresenter.view = this
        initButton()
        initButtonText()
        receiveData()
    }

    private fun receiveData() {
        val args = AchievementDetailFragmentArgs.fromBundle(requireArguments())
        achievementDetailsPresenter.getAchievement(args.achievementName)
    }


    private fun initButton() {
        binding.shareWithFriendButton.shareIcon.visibility = View.VISIBLE
        binding.okButton.setOnClickListener {
            findNavController().popBackStack(R.id.homeFragment, inclusive = false)
        }
        binding.exitButton.setOnClickListener {
            dismiss()
        }

        binding.shareWithFriendButton.root.setOnClickListener {
            val achievementShareText = " I just unlocked a new achievement in Qurio Game! :) \n\n" +
                    "Achievement: ${currentAchievement.name}\n" +
                    "How I got it: ${currentAchievement.howToGetIt} \n\n" +
                    " Can you unlock it too?"
            val sendIntent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, achievementShareText)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, "Share using")
            requireContext().startActivity(shareIntent)
        }
    }

    private fun initButtonText() {
        val includedView = binding.shareWithFriendButton
        val buttonText = includedView.buttonText
        buttonText.text = getString(R.string.share_with_friends)
    }

    override fun onGetAchievementSuccess(achievement: Achievement) {
        currentAchievement = achievement
        achievement.apply {
            binding.achievementName.text = name
            val achievementDescription = "${achievement.description}\nHow to get:\n${achievement.howToGetIt}"
            binding.achievementDescription.text = achievementDescription
            if(achievement.isOpen){
                binding.achievementImage.setImageResource(openImage)
                binding.achievementBackgroundOpened.visibility = View.VISIBLE
                binding.shareWithFriendButton.root.visibility = View.VISIBLE
            }else{
                binding.achievementImage.setImageResource(closeImage)
            }

        }
    }

    override fun onLoading() {

    }

    override fun onGetDataSuccess() {

    }

    override fun onGetDataError() {

    }
}