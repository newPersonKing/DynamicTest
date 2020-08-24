package com.kangf.plugin

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import java.lang.Exception

object LoadUtils {

    private var mResources: Resources? = null


    /*
        只有mResources为空时才创建
     */
    fun getResources(context: Context): Resources {

        if (mResources == null) {
            mResources = loadAsset(context)
        }
        return mResources!!
    }

    /*在插件中将插件中的资源加载到 宿主中的资源加载器中*/
    private fun loadAsset(context: Context): Resources? {
        try {
            // 创建AssetManager对象
            val assetManager = AssetManager::class.java.newInstance()
            // 执行addAssetPath方法，添加资源加载路径
            val addAssetPathMethod =
                assetManager::class.java.getDeclaredMethod("addAssetPath", String::class.java)
            addAssetPathMethod.isAccessible = true
            addAssetPathMethod.invoke(assetManager, "/sdcard/cccc.apk")
            // 创建Resources
            return Resources(
                assetManager,
                context.resources.displayMetrics,
                context.resources.configuration
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}