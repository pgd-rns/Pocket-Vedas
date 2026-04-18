.class Luk/co/dancingganesh/pocketvedas/m;
.super Ljava/lang/Object;

# interfaces
.implements Ljava/lang/Runnable;


# instance fields
.field final synthetic a:Luk/co/dancingganesh/pocketvedas/l;


# direct methods
.method constructor <init>(Luk/co/dancingganesh/pocketvedas/l;)V
    .locals 0

    iput-object p1, p0, Luk/co/dancingganesh/pocketvedas/m;->a:Luk/co/dancingganesh/pocketvedas/l;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public run()V
    .locals 3

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/m;->a:Luk/co/dancingganesh/pocketvedas/l;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/l;->a(Luk/co/dancingganesh/pocketvedas/l;)Luk/co/dancingganesh/pocketvedas/k;

    move-result-object v0

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/k;->c(Luk/co/dancingganesh/pocketvedas/k;)Landroid/webkit/WebView;

    move-result-object v0

    invoke-virtual {v0}, Landroid/webkit/WebView;->getContentHeight()I

    move-result v0

    int-to-float v0, v0

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/m;->a:Luk/co/dancingganesh/pocketvedas/l;

    invoke-static {v1}, Luk/co/dancingganesh/pocketvedas/l;->a(Luk/co/dancingganesh/pocketvedas/l;)Luk/co/dancingganesh/pocketvedas/k;

    move-result-object v1

    invoke-static {v1}, Luk/co/dancingganesh/pocketvedas/k;->b(Luk/co/dancingganesh/pocketvedas/k;)F

    move-result v1

    mul-float/2addr v0, v1

    float-to-int v0, v0

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/m;->a:Luk/co/dancingganesh/pocketvedas/l;

    invoke-static {v1}, Luk/co/dancingganesh/pocketvedas/l;->a(Luk/co/dancingganesh/pocketvedas/l;)Luk/co/dancingganesh/pocketvedas/k;

    move-result-object v1

    invoke-static {v1}, Luk/co/dancingganesh/pocketvedas/k;->c(Luk/co/dancingganesh/pocketvedas/k;)Landroid/webkit/WebView;

    move-result-object v1

    const/4 v2, 0x0

    invoke-virtual {v1, v2, v0}, Landroid/webkit/WebView;->scrollTo(II)V

    return-void
.end method
