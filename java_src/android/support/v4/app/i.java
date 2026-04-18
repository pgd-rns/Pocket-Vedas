package android.support.v4.app;

import android.os.Handler;
import android.os.Message;

/* loaded from: classes.dex */
class i extends Handler {
    final /* synthetic */ h a;

    i(h hVar) {
        this.a = hVar;
    }

    @Override // android.os.Handler
    public void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                if (this.a.e) {
                    this.a.a(false);
                    break;
                }
                break;
            case 2:
                this.a.a();
                this.a.b.e();
                break;
            default:
                super.handleMessage(message);
                break;
        }
    }
}
