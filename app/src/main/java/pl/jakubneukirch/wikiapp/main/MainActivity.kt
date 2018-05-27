package pl.jakubneukirch.wikiapp.main

import android.os.Bundle
import android.support.v4.view.ViewPager
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.standard_appbar.*
import pl.jakubneukirch.wikiapp.R
import pl.jakubneukirch.wikiapp.app.WikiApp
import pl.jakubneukirch.wikiapp.base.BaseActivity
import pl.jakubneukirch.wikiapp.di.AcitivityModule

class MainActivity : BaseActivity<MainView, MainPresenter>(), MainView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        presenter.onCreate()
    }

    override fun setup() {
        setupToolbar()
        setupViewPager()
        setupListeners()
    }

    private fun setupToolbar() {
        setSupportActionBar(standardToolbar)
    }

    private fun setupViewPager() {
        viewPager.adapter = MainPagerAdapter(supportFragmentManager)
        viewPager.currentItem = RANDOM_FRAGMENT
        navigationBar.selectedItemId = R.id.randomNav
    }

    private fun setupListeners() {
        navigationBar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.infoNav -> viewPager.currentItem = INFO_FRAGMENT
                R.id.randomNav -> viewPager.currentItem = RANDOM_FRAGMENT
                R.id.savedNav -> viewPager.currentItem = SAVED_FRAGMENT
            }
            true
        }
        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) = Unit

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) = Unit

            override fun onPageSelected(position: Int) {
                when (position) {
                    INFO_FRAGMENT -> navigationBar.selectedItemId = R.id.infoNav
                    RANDOM_FRAGMENT -> navigationBar.selectedItemId = R.id.randomNav
                    SAVED_FRAGMENT -> navigationBar.selectedItemId = R.id.savedNav
                }
            }
        })
    }

    override fun injectDependencies() {
        WikiApp.component
                .getAcitivityComponent(AcitivityModule(this))
                .inject(this)
    }

}
