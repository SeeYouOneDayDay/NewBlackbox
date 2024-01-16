package top.niunaijun.blackbox.app

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context
import android.util.Log
import top.niunaijun.bcore.utils.MinRefPlanA

class App : Application() {
    companion object {
        @SuppressLint("StaticFieldLeak")
        @Volatile
        private lateinit var mContext: Context

        @JvmStatic
        fun getContext(): Context {
            return mContext
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        mContext = base!!

        var res = MinRefPlanA.unseal(base!!)
        Log.d("sanbo", "MinRefPlanA res ${res}")
//        long result = MinRefPlanA.unseal(App.this);
//        EL.i("MinRefPlanA.unseal result: " + result);
        AppManager.doAttachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()
        AppManager.doOnCreate()
    }
}
