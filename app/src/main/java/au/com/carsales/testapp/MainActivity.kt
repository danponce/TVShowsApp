package au.com.carsales.testapp

import au.com.carsales.testapp.utils.base.BaseNavActivity

class MainActivity : BaseNavActivity() {
    override fun layoutId(): Int = R.layout.activity_main
    override fun navHostFragmentId(): Int = R.id.nav_host_fragment

}