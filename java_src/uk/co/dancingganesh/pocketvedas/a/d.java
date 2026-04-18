package uk.co.dancingganesh.pocketvedas.a;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class d {
    private static Pattern d = Pattern.compile("<DIV CLASS=\"paragraph\">(Thus end[ s][^<]*)</DIV>");
    private String a;
    private String b;
    private String c;

    private d(String str, String str2, String str3) {
        this.a = str;
        this.b = str2;
        this.c = str3;
    }

    public static d a(a aVar) {
        if (aVar.c() == 0) {
            return null;
        }
        StringBuffer stringBuffer = new StringBuffer();
        StringBuffer stringBuffer2 = new StringBuffer();
        if (!c.a().a(aVar.b(), aVar.a(), stringBuffer, stringBuffer2)) {
            return null;
        }
        Matcher matcher = d.matcher(c.a().a(aVar.b(), aVar.d()));
        return new d(matcher.find() ? matcher.group(1) : "Thus ends " + c.a().a(aVar.c()), stringBuffer.length() > 0 ? stringBuffer.toString() : null, stringBuffer2.length() > 0 ? stringBuffer2.toString() : null);
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public String c() {
        return this.c;
    }
}
