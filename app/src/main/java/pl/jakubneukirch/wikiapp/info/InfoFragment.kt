package pl.jakubneukirch.wikiapp.info

import android.app.Activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import pl.jakubneukirch.wikiapp.R
import pl.jakubneukirch.wikiapp.app.WikiApp
import pl.jakubneukirch.wikiapp.base.BaseFragment
import pl.jakubneukirch.wikiapp.di.AcitivityModule

class InfoFragment: BaseFragment<InfoView, InfoPresenter>(), InfoView {


    companion object {
        fun newInstance(): Fragment {
            return InfoFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.info_fragment, container, false)
    }

    override fun injectDependencies() {
        WikiApp.component
                .getAcitivityComponent(AcitivityModule(activity as Activity))
                .inject(this)
    }
}