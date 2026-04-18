.class Luk/co/dancingganesh/pocketvedas/l;
.super Ljava/lang/Object;

# interfaces
.implements Landroid/webkit/WebView$PictureListener;


# instance fields
.field final synthetic a:Luk/co/dancingganesh/pocketvedas/k;


# direct methods
.method constructor <init>(Luk/co/dancingganesh/pocketvedas/k;)V
    .locals 0

    iput-object p1, p0, Luk/co/dancingganesh/pocketvedas/l;->a:Luk/co/dancingganesh/pocketvedas/k;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method static synthetic a(Luk/co/dancingganesh/pocketvedas/l;)Luk/co/dancingganesh/pocketvedas/k;
    .locals 1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/l;->a:Luk/co/dancingganesh/pocketvedas/k;

    return-object v0
.end method


# virtual methods
.method public onNewPicture(Landroid/webkit/WebView;Landroid/graphics/Picture;)V
    .locals 2

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/l;->a:Luk/co/dancingganesh/pocketvedas/k;

    invoke-virtual {v0, p1}, Luk/co/dancingganesh/pocketvedas/k;->a(Landroid/webkit/WebView;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/l;->a:Luk/co/dancingganesh/pocketvedas/k;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/k;->b(Luk/co/dancingganesh/pocketvedas/k;)F

    move-result v0

    const/4 v1, 0x0

    cmpl-float v0, v0, v1

    if-lez v0, :cond_0

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/l;->a:Luk/co/dancingganesh/pocketvedas/k;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/k;->c(Luk/co/dancingganesh/pocketvedas/k;)Landroid/webkit/WebView;

    move-result-object v0

    new-instance v1, Luk/co/dancingganesh/pocketvedas/m;

    invoke-direct {v1, p0}, Luk/co/dancingganesh/pocketvedas/m;-><init>(Luk/co/dancingganesh/pocketvedas/l;)V

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->post(Ljava/lang/Runnable;)Z

    :cond_0
    return-void
.end method
