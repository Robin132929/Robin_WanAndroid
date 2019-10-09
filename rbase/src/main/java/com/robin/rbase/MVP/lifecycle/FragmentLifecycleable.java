package com.robin.rbase.MVP.lifecycle;

import com.trello.rxlifecycle2.RxLifecycle;
import com.trello.rxlifecycle2.android.FragmentEvent;

import androidx.fragment.app.Fragment;

/**
 * ================================================
 * 让 {@link Fragment} 实现此接口,即可正常使用 {@link RxLifecycle}
 *
 * ================================================
 */
public interface FragmentLifecycleable extends Lifecycleable<FragmentEvent> {
}
