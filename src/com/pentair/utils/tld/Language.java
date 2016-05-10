package com.pentair.utils.tld;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Created by HZC on 2016/5/10.
 */
public class Language extends SimpleTagSupport {

    private String key;

    private String language;

    @Override
    public void doTag() throws JspException, IOException {

        JspWriter out = this.getJspContext().getOut();

        String key = getKey();
        String language = getLanguage();
        Locale l = Locale.CHINA;
        if (null == language || language.equals("")) {
            language = (String) SessionTld.getSession().getAttribute("language");
        }
        if (language.equals("english")) {
            l = Locale.US;
        }
        ResourceBundle bundle = ResourceBundle.getBundle("message", l);

        out.println(bundle.getString(key));

    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

}
