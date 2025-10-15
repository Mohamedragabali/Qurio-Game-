package com.quriogamethechance.quriogame.ui.achievement

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.quriogamethechance.quriogame.databinding.AchievementBinding
import com.quriogamethechance.quriogame.databinding.FragmentAchievementBinding
import com.quriogamethechance.quriogame.presenter.achievement.Achievement
import com.quriogamethechance.quriogame.presenter.achievement.AchievementPresenter
import com.quriogamethechance.quriogame.ui.QurioApp
import com.quriogamethechance.quriogame.ui.base.BaseDialogFragment
import jakarta.inject.Inject


class AchievementFragment : BaseDialogFragment<FragmentAchievementBinding>(),
    AchievementViewInterface {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAchievementBinding
        get() = FragmentAchievementBinding::inflate


    @Inject
    lateinit var achievementPresenter: AchievementPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (requireActivity().application as QurioApp).appComponent.inject(this)
    }
    override fun setup() {
        achievementPresenter.view = this
        achievementPresenter.getAchievements()
        initButton()
    }


    private fun initButton() {
        binding.exitButton.setOnClickListener {
            dismiss()
        }
        binding.cancelButton.setOnClickListener {
            dismiss()
        }


    }



    private fun onClickCharacter(achievementName: String){
       val action = AchievementFragmentDirections.actionAchievementFragmentToAchievementDetialFragment(
           achievementName,
           false
       )
        findNavController().navigate(action)
    }

    override fun onGetAchievementsSuccess(achievementsList: List<Achievement>) {
        achievementsList.forEach {achievementItem ->
            val achievement = AchievementBinding.inflate(layoutInflater,binding.achievements,false)
            if(achievementItem.isOpen){
                achievement.achievementImage.setImageResource(achievementItem.openImage)
            }else{
                achievement.achievementImage.setImageResource(achievementItem.closeImage)
            }
            achievement.achievementName.text = achievementItem.name
            achievement.root.setOnClickListener {
                onClickCharacter(achievementItem.nickname)
            }
            binding.achievements.addView(achievement.root)
        }
    }

    override fun onLoading() {

    }

    override fun onGetDataSuccess() {

    }

    override fun onGetDataError() {

    }


}
