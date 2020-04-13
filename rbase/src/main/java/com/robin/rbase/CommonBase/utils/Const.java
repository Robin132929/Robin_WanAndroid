package com.robin.rbase.CommonBase.utils;

import android.content.Context;

import com.robin.rbase.CommonBase.Cache.Cache;
import com.robin.rbase.CommonBase.Cache.CacheType;
import com.robin.rbase.CommonBase.Cache.IntelligentCache;

import androidx.annotation.NonNull;

public class Const {
    static volatile Cache.Factory factory;

    public static Cache.Factory getCacheFactory(Context context) {
        if (factory == null) {
            factory = new Cache.Factory() {
                @NonNull
                @Override
                public Cache build(CacheType type) {
                    //若想自定义 LruCache 的 size, 或者不想使用 LruCache, 想使用自己自定义的策略
                    //使用 GlobalConfigModule.Builder#cacheFactory() 即可扩展
                    switch (type.getCacheTypeId()) {
                        //Activity、Fragment 以及 Extras 使用 IntelligentCache (具有 LruCache 和 可永久存储数据的 Map)
                        case CacheType.EXTRAS_TYPE_ID:
                        case CacheType.ACTIVITY_CACHE_TYPE_ID:
                        case CacheType.FRAGMENT_CACHE_TYPE_ID:
                            return new IntelligentCache(type.calculateCacheSize(context));
                        //其余使用 LruCache (当达到最大容量时可根据 LRU 算法抛弃不合规数据)
                        default:
                            return new IntelligentCache(type.calculateCacheSize(context));
                    }
                }
            };
        }
        return factory;

    }

}
