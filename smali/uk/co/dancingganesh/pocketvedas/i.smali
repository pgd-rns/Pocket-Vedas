.class Luk/co/dancingganesh/pocketvedas/i;
.super Landroid/webkit/WebViewClient;


# instance fields
.field final synthetic a:Luk/co/dancingganesh/pocketvedas/h;


# direct methods
.method constructor <init>(Luk/co/dancingganesh/pocketvedas/h;)V
    .locals 0

    iput-object p1, p0, Luk/co/dancingganesh/pocketvedas/i;->a:Luk/co/dancingganesh/pocketvedas/h;

    invoke-direct {p0}, Landroid/webkit/WebViewClient;-><init>()V

    return-void
.end method


# virtual methods
.method public shouldOverrideUrlLoading(Landroid/webkit/WebView;Ljava/lang/String;)Z
    .locals 4

    const/4 v0, 0x0

    const-string v1, "veda:"

    invoke-virtual {p2, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    new-instance v1, Landroid/content/Intent;

    iget-object v2, p0, Luk/co/dancingganesh/pocketvedas/i;->a:Luk/co/dancingganesh/pocketvedas/h;

    invoke-virtual {v2}, Luk/co/dancingganesh/pocketvedas/h;->h()Landroid/support/v4/app/h;

    move-result-object v2

    const-class v3, Luk/co/dancingganesh/pocketvedas/ReadingActivity;

    invoke-direct {v1, v2, v3}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const-string v2, "uk.co.dancingganesh.pocketvedas.BOOK_ADDRESS"

    const/4 v3, 0x5

    invoke-virtual {p2, v3}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v1, v2, v3}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    iget-object v2, p0, Luk/co/dancingganesh/pocketvedas/i;->a:Luk/co/dancingganesh/pocketvedas/h;

    invoke-static {v2}, Luk/co/dancingganesh/pocketvedas/h;->a(Luk/co/dancingganesh/pocketvedas/h;)Luk/co/dancingganesh/pocketvedas/o;

    move-result-object v2

    invoke-virtual {v2, v0, v1}, Luk/co/dancingganesh/pocketvedas/o;->a(ILandroid/content/Intent;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/i;->a:Luk/co/dancingganesh/pocketvedas/h;

    invoke-virtual {v0, v1}, Luk/co/dancingganesh/pocketvedas/h;->a(Landroid/content/Intent;)V

    const/4 v0, 0x1

    :cond_0
    return v0
.end method
