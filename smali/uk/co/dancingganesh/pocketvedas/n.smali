.class Luk/co/dancingganesh/pocketvedas/n;
.super Landroid/webkit/WebViewClient;


# instance fields
.field final synthetic a:Luk/co/dancingganesh/pocketvedas/k;


# direct methods
.method constructor <init>(Luk/co/dancingganesh/pocketvedas/k;)V
    .locals 0

    iput-object p1, p0, Luk/co/dancingganesh/pocketvedas/n;->a:Luk/co/dancingganesh/pocketvedas/k;

    invoke-direct {p0}, Landroid/webkit/WebViewClient;-><init>()V

    return-void
.end method


# virtual methods
.method public onScaleChanged(Landroid/webkit/WebView;FF)V
    .locals 2

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x13

    if-ge v0, v1, :cond_0

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/n;->a:Luk/co/dancingganesh/pocketvedas/k;

    invoke-virtual {v0, p1}, Luk/co/dancingganesh/pocketvedas/k;->a(Landroid/webkit/WebView;)V

    :cond_0
    return-void
.end method

.method public shouldOverrideUrlLoading(Landroid/webkit/WebView;Ljava/lang/String;)Z
    .locals 3

    const-string v0, "veda:"

    invoke-virtual {p2, v0}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    new-instance v0, Landroid/content/Intent;

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/n;->a:Luk/co/dancingganesh/pocketvedas/k;

    invoke-virtual {v1}, Luk/co/dancingganesh/pocketvedas/k;->h()Landroid/support/v4/app/h;

    move-result-object v1

    const-class v2, Luk/co/dancingganesh/pocketvedas/ReadingActivity;

    invoke-direct {v0, v1, v2}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const-string v1, "uk.co.dancingganesh.pocketvedas.BOOK_ADDRESS"

    const/4 v2, 0x5

    invoke-virtual {p2, v2}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/n;->a:Luk/co/dancingganesh/pocketvedas/k;

    invoke-static {v1}, Luk/co/dancingganesh/pocketvedas/k;->d(Luk/co/dancingganesh/pocketvedas/k;)Luk/co/dancingganesh/pocketvedas/o;

    move-result-object v1

    iget-object v2, p0, Luk/co/dancingganesh/pocketvedas/n;->a:Luk/co/dancingganesh/pocketvedas/k;

    invoke-static {v2}, Luk/co/dancingganesh/pocketvedas/k;->e(Luk/co/dancingganesh/pocketvedas/k;)I

    move-result v2

    invoke-virtual {v1, v2, v0}, Luk/co/dancingganesh/pocketvedas/o;->a(ILandroid/content/Intent;)V

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/n;->a:Luk/co/dancingganesh/pocketvedas/k;

    invoke-virtual {v1, v0}, Luk/co/dancingganesh/pocketvedas/k;->a(Landroid/content/Intent;)V

    const/4 v0, 0x1

    :goto_0
    return v0

    :cond_0
    const/4 v0, 0x0

    goto :goto_0
.end method
