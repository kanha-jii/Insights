package com.insights.extensions

fun main(args: Array<String>) {
    println(lpSub("aabccbaa"))

}

fun lpSub(st:String):String {

    var ans: String = ""
    for (i in 0..st.length) {

        for (j in i + 1..st.length) {
            var sub = st.substring(i, j)
            if (sub == myReverse(sub)) {

                if (sub.length > ans.length) {
                    ans = sub
                }
            }
        }

    }

    return ans;
}

fun myReverse(st:String):String {

    var mid:Int = st.length/2

    var ans:String =""

    for(i in st.length-1 downTo  0) {

        ans += st[i]
    }

    return ans;
}