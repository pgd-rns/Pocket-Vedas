.class public Luk/co/dancingganesh/pocketvedas/h;
.super Landroid/support/v4/app/Fragment;


# instance fields
.field private a:Landroid/webkit/WebView;

.field private b:Landroid/widget/LinearLayout;

.field private c:Ljava/lang/String;

.field private d:Ljava/lang/String;

.field private e:Ljava/lang/String;

.field private f:Ljava/lang/String;


# direct methods
.method public constructor <init>()V
    .locals 1

    const/4 v0, 0x0

    invoke-direct {p0}, Landroid/support/v4/app/Fragment;-><init>()V

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/h;->a:Landroid/webkit/WebView;

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/h;->b:Landroid/widget/LinearLayout;

    return-void
.end method

.method static synthetic a(Luk/co/dancingganesh/pocketvedas/h;)Luk/co/dancingganesh/pocketvedas/o;
    .locals 1

    invoke-direct {p0}, Luk/co/dancingganesh/pocketvedas/h;->w()Luk/co/dancingganesh/pocketvedas/o;

    move-result-object v0

    return-object v0
.end method

.method private a()V
    .locals 6

    const/4 v2, -0x1

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/h;->h()Landroid/support/v4/app/h;

    move-result-object v0

    if-nez v0, :cond_0

    :goto_0
    return-void

    :cond_0
    new-instance v1, Landroid/webkit/WebView;

    invoke-direct {v1, v0}, Landroid/webkit/WebView;-><init>(Landroid/content/Context;)V

    iput-object v1, p0, Luk/co/dancingganesh/pocketvedas/h;->a:Landroid/webkit/WebView;

    new-instance v0, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v0, v2, v2}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/h;->a:Landroid/webkit/WebView;

    invoke-virtual {v1, v0}, Landroid/webkit/WebView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/h;->a:Landroid/webkit/WebView;

    new-instance v1, Luk/co/dancingganesh/pocketvedas/i;

    invoke-direct {v1, p0}, Luk/co/dancingganesh/pocketvedas/i;-><init>(Luk/co/dancingganesh/pocketvedas/h;)V

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->setWebViewClient(Landroid/webkit/WebViewClient;)V

    const-string v1, "file:///android_asset/web/"

    const-string v0, "<html><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"><STYLE TYPE=\"text/css\">@font-face {font-family: DejaVu; src: url(DejaVuSans.ttf); }@font-face {font-family: DejaVuBold; src: url(DejaVuSans-Bold.ttf); }@font-face {font-family: DejaVuItalic; src: url(DejaVuSans-Oblique.ttf); }BODY {margin-top: 1%; background: #00c8f0 url(bg-texture.png) repeat; color: #ffffff;font-weight: normal; font-style: normal; font-variant: normal; font-size: 15pt; font-family: DejaVu;}.name {margin-top: 20px; font-family: DejaVuItalic; }#top {display:table; width: 100%; height: 50%; padding-left: 10%; padding-right: 10%; }#end {display:table-cell; vertical-align: middle; text-align: left; }#bottom {display:table; width: 100%; height: 50%; padding-left: 10%; padding-right: 10%; [nextVisible] }#next {display:table-cell; vertical-align: top; text-align: right; }a {color: #ffffff;}</STYLE></head><body><div id=\"top\"><div id=\"end\">[thusEnds]</div></div></div><div id=\"bottom\"><div id=\"next\">Next:<a href=\"veda:[nextUrl]\"><div class=\"name\">[nextTitle]</div></a></div></div></body></html>"

    const-string v2, "[thusEnds]"

    iget-object v3, p0, Luk/co/dancingganesh/pocketvedas/h;->c:Ljava/lang/String;

    invoke-virtual {v0, v2, v3}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v0

    iget-object v2, p0, Luk/co/dancingganesh/pocketvedas/h;->d:Ljava/lang/String;

    if-nez v2, :cond_1

    const-string v2, "[nextVisible]"

    const-string v3, "display: none;"

    invoke-virtual {v0, v2, v3}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v2

    :goto_1
    const-string v3, "text/html"

    const-string v4, "UTF-8"

    const/4 v5, 0x0

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/h;->a:Landroid/webkit/WebView;

    invoke-virtual/range {v0 .. v5}, Landroid/webkit/WebView;->loadDataWithBaseURL(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/h;->b:Landroid/widget/LinearLayout;

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/h;->a:Landroid/webkit/WebView;

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    goto :goto_0

    :cond_1
    const-string v2, "[nextVisible]"

    const-string v3, ""

    invoke-virtual {v0, v2, v3}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v0

    const-string v2, "[nextTitle]"

    iget-object v3, p0, Luk/co/dancingganesh/pocketvedas/h;->d:Ljava/lang/String;

    invoke-virtual {v0, v2, v3}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v0

    const-string v2, "[nextUrl]"

    iget-object v3, p0, Luk/co/dancingganesh/pocketvedas/h;->e:Ljava/lang/String;

    invoke-virtual {v0, v2, v3}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v2

    goto :goto_1
.end method

.method private w()Luk/co/dancingganesh/pocketvedas/o;
    .locals 1

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/h;->h()Landroid/support/v4/app/h;

    move-result-object v0

    check-cast v0, Luk/co/dancingganesh/pocketvedas/ReadingActivity;

    invoke-virtual {v0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->f()Luk/co/dancingganesh/pocketvedas/o;

    move-result-object v0

    return-object v0
.end method


# virtual methods
.method public a(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 2

    if-eqz p3, :cond_0

    const-string v0, "TEXT"

    invoke-virtual {p3, v0}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/h;->c:Ljava/lang/String;

    const-string v0, "TITLE"

    invoke-virtual {p3, v0}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/h;->d:Ljava/lang/String;

    const-string v0, "URL"

    invoke-virtual {p3, v0}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/h;->e:Ljava/lang/String;

    const-string v0, "PATH"

    invoke-virtual {p3, v0}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/h;->f:Ljava/lang/String;

    :cond_0
    new-instance v0, Landroid/widget/LinearLayout;

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/h;->h()Landroid/support/v4/app/h;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/h;->b:Landroid/widget/LinearLayout;

    invoke-direct {p0}, Luk/co/dancingganesh/pocketvedas/h;->a()V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/h;->b:Landroid/widget/LinearLayout;

    return-object v0
.end method

.method public a(Luk/co/dancingganesh/pocketvedas/a/d;Ljava/lang/String;)V
    .locals 1

    invoke-virtual {p1}, Luk/co/dancingganesh/pocketvedas/a/d;->a()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/h;->c:Ljava/lang/String;

    invoke-virtual {p1}, Luk/co/dancingganesh/pocketvedas/a/d;->b()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/h;->d:Ljava/lang/String;

    invoke-virtual {p1}, Luk/co/dancingganesh/pocketvedas/a/d;->c()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/h;->e:Ljava/lang/String;

    iput-object p2, p0, Luk/co/dancingganesh/pocketvedas/h;->f:Ljava/lang/String;

    return-void
.end method

.method public a(Landroid/view/MenuItem;)Z
    .locals 3

    invoke-interface {p1}, Landroid/view/MenuItem;->getItemId()I

    move-result v0

    packed-switch v0, :pswitch_data_0

    invoke-super {p0, p1}, Landroid/support/v4/app/Fragment;->a(Landroid/view/MenuItem;)Z

    move-result v0

    :goto_0
    return v0

    :pswitch_0
    new-instance v0, Luk/co/dancingganesh/pocketvedas/a;

    invoke-direct {v0}, Luk/co/dancingganesh/pocketvedas/a;-><init>()V

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/h;->f:Ljava/lang/String;

    const/4 v2, 0x0

    invoke-virtual {v0, v1, v2}, Luk/co/dancingganesh/pocketvedas/a;->a(Ljava/lang/String;F)V

    invoke-direct {p0}, Luk/co/dancingganesh/pocketvedas/h;->w()Luk/co/dancingganesh/pocketvedas/o;

    move-result-object v1

    invoke-virtual {v0, v1}, Luk/co/dancingganesh/pocketvedas/a;->a(Luk/co/dancingganesh/pocketvedas/o;)V

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/h;->h()Landroid/support/v4/app/h;

    move-result-object v1

    invoke-virtual {v1}, Landroid/support/v4/app/h;->e()Landroid/support/v4/app/l;

    move-result-object v1

    const-string v2, "bookmark"

    invoke-virtual {v0, v1, v2}, Luk/co/dancingganesh/pocketvedas/a;->a(Landroid/support/v4/app/l;Ljava/lang/String;)V

    const/4 v0, 0x1

    goto :goto_0

    nop

    :pswitch_data_0
    .packed-switch 0x7f090013
        :pswitch_0
    .end packed-switch
.end method

.method public e(Landroid/os/Bundle;)V
    .locals 2

    const-string v0, "TEXT"

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/h;->c:Ljava/lang/String;

    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    const-string v0, "TITLE"

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/h;->d:Ljava/lang/String;

    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    const-string v0, "URL"

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/h;->e:Ljava/lang/String;

    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    const-string v0, "PATH"

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/h;->f:Ljava/lang/String;

    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public o()V
    .locals 4

    invoke-super {p0}, Landroid/support/v4/app/Fragment;->o()V

    invoke-direct {p0}, Luk/co/dancingganesh/pocketvedas/h;->w()Luk/co/dancingganesh/pocketvedas/o;

    move-result-object v0

    const/4 v1, 0x0

    iget-object v2, p0, Luk/co/dancingganesh/pocketvedas/h;->f:Ljava/lang/String;

    const/4 v3, 0x0

    invoke-virtual {v0, v1, v2, v3}, Luk/co/dancingganesh/pocketvedas/o;->a(ILjava/lang/String;F)V

    return-void
.end method
