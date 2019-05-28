package com.interview.aaronl.myapplication

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Before
import java.util.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    private lateinit var appContext: Context
    @Before
    fun setContext() {
        appContext = InstrumentationRegistry.getTargetContext()
    }

    @Test
    fun useAppContext() {
        // Context of the app under test.
        assertEquals("com.interview.aaronl.myapplication", appContext.packageName)
    }

    @Test
    fun leastCommonParent() {
        val view = FrameLayout(appContext)
        view.id = 0

        val view1 = FrameLayout(appContext)
        view1.id = 1

        val view2 = FrameLayout(appContext)
        view2.id = 2

        val view3 = FrameLayout(appContext)
        view3.id = 2

        for (i in 3..10) {
            val v = FrameLayout(appContext)
            v.id = 2
            view3.addView(v)
        }

        view.addView(view1)
        view.addView(view2)
        view.addView(view3)
        val v = leastCommonParent(view, 5, 6)
        assert(v?.id == 3)
    }

    private fun leastCommonParent(root: ViewGroup?, a: Int, b: Int): View? {
        if (root == null) return null
        if (root.id == a || root.id == b) return root

        val s = Stack<View>()

        for (i in 0 until root.childCount) {
            val v = leastCommonParent(root.getChildAt(i) as ViewGroup, a, b)
            if (v != null) {
                s.push(v)
            }
        }
        return when {
            s.size >= 2 -> root
            s.size == 1 -> s.peek()
            else -> null
        }
    }
}
