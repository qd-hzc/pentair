package com.pentair.utils.tld;

import javax.servlet.http.HttpSession;

/**
 * Created by HZC on 2016/5/10.
 */
public class SessionTld {

    private static HttpSession session;

    public static HttpSession getSession() {
        return session;
    }

    public static void setSession(HttpSession session) {
        SessionTld.session = session;
    }
}
