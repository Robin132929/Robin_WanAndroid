package com.robin.robin_wanandroid.util
class encodeCookieUtil {

    fun  encodeCookie(cookies: List<String>): String {
        val sb = StringBuilder()
        val set = HashSet<String>()
        cookies
                .map(fun(cookie: String): Array<String> {
                    return cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                })
                .forEach { it ->
                    it.filterNot { set.contains(it) }.forEach { set.add(it) }
                }
        val ite = set.iterator()
        while (ite.hasNext()) {
            val cookie = ite.next()
            sb.append(cookie).append(";")
        }
        val last = sb.lastIndexOf(";")
        if (sb.length - 1 == last) {
            sb.deleteCharAt(last)
        }
        return sb.toString()
    }
}