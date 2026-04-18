package uk.co.dancingganesh.pocketvedas.a;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: classes.dex */
public class e {
    public static void a(InputStream inputStream, OutputStream outputStream) throws IOException {
        byte[] bArr = new byte[1024];
        while (true) {
            try {
                int i = inputStream.read(bArr);
                if (i <= 0) {
                    break;
                } else {
                    outputStream.write(bArr, 0, i);
                }
            } catch (Throwable th) {
                try {
                    if (outputStream != null) {
                        try {
                            outputStream.flush();
                        } finally {
                        }
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                    throw th;
                } finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
        }
        if (outputStream != null) {
            try {
                try {
                    outputStream.flush();
                } finally {
                }
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }
            }
        }
    }
}
