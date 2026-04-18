.class public Luk/co/dancingganesh/pocketvedas/k;
.super Landroid/support/v4/app/Fragment;


# static fields
.field private static a:I

.field private static b:Ljava/util/WeakHashMap;


# instance fields
.field private c:Landroid/webkit/WebView;

.field private d:Landroid/widget/LinearLayout;

.field private e:I

.field private f:Ljava/lang/String;

.field private g:F

.field private h:Ljava/lang/String;

.field private transient i:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const/4 v0, 0x0

    sput v0, Luk/co/dancingganesh/pocketvedas/k;->a:I

    new-instance v0, Ljava/util/WeakHashMap;

    invoke-direct {v0}, Ljava/util/WeakHashMap;-><init>()V

    sput-object v0, Luk/co/dancingganesh/pocketvedas/k;->b:Ljava/util/WeakHashMap;

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    const/4 v1, 0x0

    invoke-direct {p0}, Landroid/support/v4/app/Fragment;-><init>()V

    iput-object v1, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    iput-object v1, p0, Luk/co/dancingganesh/pocketvedas/k;->d:Landroid/widget/LinearLayout;

    const/4 v0, 0x0

    iput v0, p0, Luk/co/dancingganesh/pocketvedas/k;->g:F

    iput-object v1, p0, Luk/co/dancingganesh/pocketvedas/k;->i:Ljava/lang/String;

    sget-object v0, Luk/co/dancingganesh/pocketvedas/k;->b:Ljava/util/WeakHashMap;

    const/4 v1, 0x0

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-virtual {v0, p0, v1}, Ljava/util/WeakHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method public static a(Luk/co/dancingganesh/pocketvedas/k;)V
    .locals 2

    sget-object v0, Luk/co/dancingganesh/pocketvedas/k;->b:Ljava/util/WeakHashMap;

    invoke-virtual {v0}, Ljava/util/WeakHashMap;->keySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :cond_0
    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-nez v0, :cond_1

    return-void

    :cond_1
    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Luk/co/dancingganesh/pocketvedas/k;

    if-eq v0, p0, :cond_0

    invoke-virtual {v0}, Luk/co/dancingganesh/pocketvedas/k;->a()V

    goto :goto_0
.end method

.method static synthetic b(Luk/co/dancingganesh/pocketvedas/k;)F
    .locals 1

    iget v0, p0, Luk/co/dancingganesh/pocketvedas/k;->g:F

    return v0
.end method

.method static synthetic c(Luk/co/dancingganesh/pocketvedas/k;)Landroid/webkit/WebView;
    .locals 1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    return-object v0
.end method

.method static synthetic d(Luk/co/dancingganesh/pocketvedas/k;)Luk/co/dancingganesh/pocketvedas/o;
    .locals 1

    invoke-direct {p0}, Luk/co/dancingganesh/pocketvedas/k;->y()Luk/co/dancingganesh/pocketvedas/o;

    move-result-object v0

    return-object v0
.end method

.method static synthetic e(Luk/co/dancingganesh/pocketvedas/k;)I
    .locals 1

    iget v0, p0, Luk/co/dancingganesh/pocketvedas/k;->e:I

    return v0
.end method

.method private w()V
    .locals 8
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "NewApi"
        }
    .end annotation

    const/16 v3, 0x13

    const/4 v2, -0x1

    const/4 v7, 0x0

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/k;->h()Landroid/support/v4/app/h;

    move-result-object v0

    if-nez v0, :cond_0

    :goto_0
    return-void

    :cond_0
    new-instance v1, Landroid/webkit/WebView;

    invoke-direct {v1, v0}, Landroid/webkit/WebView;-><init>(Landroid/content/Context;)V

    iput-object v1, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    new-instance v0, Landroid/widget/LinearLayout$LayoutParams;

    invoke-direct {v0, v2, v2}, Landroid/widget/LinearLayout$LayoutParams;-><init>(II)V

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    invoke-virtual {v1, v0}, Landroid/webkit/WebView;->setLayoutParams(Landroid/view/ViewGroup$LayoutParams;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    new-instance v1, Luk/co/dancingganesh/pocketvedas/l;

    invoke-direct {v1, p0}, Luk/co/dancingganesh/pocketvedas/l;-><init>(Luk/co/dancingganesh/pocketvedas/k;)V

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->setPictureListener(Landroid/webkit/WebView$PictureListener;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    new-instance v1, Luk/co/dancingganesh/pocketvedas/n;

    invoke-direct {v1, p0}, Luk/co/dancingganesh/pocketvedas/n;-><init>(Luk/co/dancingganesh/pocketvedas/k;)V

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->setWebViewClient(Landroid/webkit/WebViewClient;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    invoke-virtual {v0}, Landroid/webkit/WebView;->getSettings()Landroid/webkit/WebSettings;

    move-result-object v0

    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    if-ge v1, v3, :cond_2

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/webkit/WebSettings;->setBuiltInZoomControls(Z)V

    :goto_1
    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/k;->h()Landroid/support/v4/app/h;

    move-result-object v1

    invoke-static {v1}, Landroid/preference/PreferenceManager;->getDefaultSharedPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v6

    const-string v1, "pref_zoom"

    const/16 v2, 0x85

    invoke-interface {v6, v1, v2}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v1

    sput v1, Luk/co/dancingganesh/pocketvedas/k;->a:I

    sget v1, Landroid/os/Build$VERSION;->SDK_INT:I

    if-ge v1, v3, :cond_3

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    sget v1, Luk/co/dancingganesh/pocketvedas/k;->a:I

    invoke-virtual {v0, v1}, Landroid/webkit/WebView;->setInitialScale(I)V

    :goto_2
    const-string v1, "file:///android_asset/web/"

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->i:Ljava/lang/String;

    if-nez v0, :cond_1

    invoke-static {}, Luk/co/dancingganesh/pocketvedas/a/c;->a()Luk/co/dancingganesh/pocketvedas/a/c;

    move-result-object v0

    iget v2, p0, Luk/co/dancingganesh/pocketvedas/k;->e:I

    iget-object v3, p0, Luk/co/dancingganesh/pocketvedas/k;->f:Ljava/lang/String;

    invoke-virtual {v0, v2, v3}, Luk/co/dancingganesh/pocketvedas/a/c;->a(ILjava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->i:Ljava/lang/String;

    :cond_1
    invoke-direct {p0}, Luk/co/dancingganesh/pocketvedas/k;->z()Ljava/lang/String;

    move-result-object v0

    iget-object v2, p0, Luk/co/dancingganesh/pocketvedas/k;->h:Ljava/lang/String;

    if-eqz v2, :cond_4

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-static {v0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-direct {v2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->i:Ljava/lang/String;

    iget-object v3, p0, Luk/co/dancingganesh/pocketvedas/k;->h:Ljava/lang/String;

    invoke-static {v0, v3}, Luk/co/dancingganesh/pocketvedas/SearchActivity;->b(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    :goto_3
    const-string v3, "text/html"

    const-string v4, "UTF-8"

    const/4 v5, 0x0

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    invoke-virtual/range {v0 .. v5}, Landroid/webkit/WebView;->loadDataWithBaseURL(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    const-string v0, "pref_keep_awake"

    invoke-interface {v6, v0, v7}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    invoke-virtual {v1, v0}, Landroid/webkit/WebView;->setKeepScreenOn(Z)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->d:Landroid/widget/LinearLayout;

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->addView(Landroid/view/View;)V

    goto/16 :goto_0

    :cond_2
    invoke-virtual {v0, v7}, Landroid/webkit/WebSettings;->setBuiltInZoomControls(Z)V

    invoke-virtual {v0, v7}, Landroid/webkit/WebSettings;->setSupportZoom(Z)V

    goto :goto_1

    :cond_3
    sget v1, Luk/co/dancingganesh/pocketvedas/k;->a:I

    invoke-virtual {v0, v1}, Landroid/webkit/WebSettings;->setTextZoom(I)V

    goto :goto_2

    :cond_4
    new-instance v2, Ljava/lang/StringBuilder;

    invoke-static {v0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-direct {v2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->i:Ljava/lang/String;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    goto :goto_3
.end method

.method private x()F
    .locals 3

    const/high16 v0, 0x3f800000    # 1.0f

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    invoke-virtual {v1}, Landroid/webkit/WebView;->getScrollY()I

    move-result v1

    iget-object v2, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    invoke-virtual {v2}, Landroid/webkit/WebView;->getContentHeight()I

    move-result v2

    int-to-float v1, v1

    int-to-float v2, v2

    div-float/2addr v1, v2

    cmpl-float v2, v1, v0

    if-lez v2, :cond_0

    :goto_0
    return v0

    :cond_0
    move v0, v1

    goto :goto_0
.end method

.method private y()Luk/co/dancingganesh/pocketvedas/o;
    .locals 1

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/k;->h()Landroid/support/v4/app/h;

    move-result-object v0

    check-cast v0, Luk/co/dancingganesh/pocketvedas/ReadingActivity;

    invoke-virtual {v0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->f()Luk/co/dancingganesh/pocketvedas/o;

    move-result-object v0

    return-object v0
.end method

.method private z()Ljava/lang/String;
    .locals 9

    const/4 v8, 0x1

    const-string v1, "<HTML xmlns:vb=\"http://www.vedabase.com\"><HEAD> <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\"> <STYLE TYPE=\"text/css\">@font-face {font-family: DejaVu; src: url(DejaVuSans.ttf); }@font-face {font-family: DejaVuBold; src: url(DejaVuSans-Bold.ttf); }@font-face {font-family: DejaVuItalic; src: url(DejaVuSans-Oblique.ttf); }BODY {margin-top: 1%; padding: 0; text-indent: 0px; text-align: justify; background: [background] url(bg-texture.png) repeat;color: [foreground]; font-weight: normal; font-style: normal; font-variant: normal; font-size: 12pt; font-family: DejaVu;margin-top: 32pt; margin-bottom: 100pt; margin-left: [margin]px; margin-right: [margin]px; -webkit-text-size-adjust: none; }.space {clear: both; }.header {color: #009966; text-align: center; font-family: DejaVuBold; text-decoration: underline; margin-bottom: 18pt; }.text {[textOn]}.text-inner {}.verse {text-indent: 0px; display: table; margin: 10px auto; }.verse br {display: none; }.prelude {text-align: center; margin-top: 0pt; }.odd-line {text-align: left; margin-top: 0pt; }.even-line {text-indent: 20px; text-align: left; margin-top: 0pt; }.section-title {display: none; clear: both; text-align: center; font-family: DejaVuBold; text-decoration: underline;margin-top: 18pt; margin-bottom: 18pt; }.synonyms {[synonymOn] text-indent: 0; margin-top: 0pt; }.synonym {display: inline; }.word {color: #009966; display: inline; }.meaning {display: inline; }.translation {[translationOn] font-family: DejaVuBold; }.purport {[purportOn] text-indent: 24pt; }.body {text-indent: 24pt; }.paragraph {margin-bottom: 0; margin-top: 18pt; }.heading {margin-top: 18pt; border-top: 1px solid [foreground]; border-bottom: 1px solid [foreground]; font-weight: bold; text-indent: 0; }OL LI { text-indent: 0pt; margin-top: 5pt; }A.link:link {color: #990000; text-decoration: none; }A.link:active  {color: #990000; text-decoration: none; }A.link:visited {color: #990000; text-decoration: none; }.quote {text-align: center; margin-top: 10pt; }.foreign {font-family: DejaVuItalic; display: inline; }.highlight {background-color: yellow; color: black; display: inline; }UL {list-style-type: none; text-indent: 0px; -webkit-padding-start: 0px; }UL LI {margin-bottom: 10px; }.indexTarget {display: inline; }.indexTitle {display: inline; }</style><META NAME=\"viewport\" CONTENT=\"width=480, maximum-scale=8.0, initial-scale=1.0, user-scalable=yes\"/>"

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/k;->i()Landroid/content/res/Resources;

    move-result-object v0

    const/high16 v2, 0x7f050000

    invoke-virtual {v0, v2}, Landroid/content/res/Resources;->getBoolean(I)Z

    move-result v0

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/k;->h()Landroid/support/v4/app/h;

    move-result-object v2

    invoke-static {v2}, Landroid/preference/PreferenceManager;->getDefaultSharedPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v2

    const-string v3, "pref_text"

    invoke-interface {v2, v3, v8}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v3

    const-string v4, "pref_synonyms"

    invoke-interface {v2, v4, v8}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v4

    const-string v5, "pref_translation"

    invoke-interface {v2, v5, v8}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v5

    const-string v6, "pref_purport"

    invoke-interface {v2, v6, v8}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v6

    const-string v7, "pref_reverse"

    invoke-interface {v2, v7, v8}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v2

    const-string v7, "[margin]"

    if-eqz v0, :cond_0

    const-string v0, "30"

    :goto_0
    invoke-virtual {v1, v7, v0}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v1

    const-string v7, "[textOn]"

    if-eqz v3, :cond_1

    const-string v0, ""

    :goto_1
    invoke-virtual {v1, v7, v0}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v1

    const-string v3, "[synonymOn]"

    if-eqz v4, :cond_2

    const-string v0, ""

    :goto_2
    invoke-virtual {v1, v3, v0}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v1

    const-string v3, "[translationOn]"

    if-eqz v5, :cond_3

    const-string v0, ""

    :goto_3
    invoke-virtual {v1, v3, v0}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v1

    const-string v3, "[purportOn]"

    if-eqz v6, :cond_4

    const-string v0, ""

    :goto_4
    invoke-virtual {v1, v3, v0}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v1

    const-string v3, "[background]"

    if-eqz v2, :cond_5

    const-string v0, "white"

    :goto_5
    invoke-virtual {v1, v3, v0}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v1

    const-string v3, "[foreground]"

    if-eqz v2, :cond_6

    const-string v0, "black"

    :goto_6
    invoke-virtual {v1, v3, v0}, Ljava/lang/String;->replace(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;

    move-result-object v0

    return-object v0

    :cond_0
    const-string v0, "5"

    goto :goto_0

    :cond_1
    const-string v0, "display:none;"

    goto :goto_1

    :cond_2
    const-string v0, "display:none;"

    goto :goto_2

    :cond_3
    const-string v0, "display:none;"

    goto :goto_3

    :cond_4
    const-string v0, "display:none;"

    goto :goto_4

    :cond_5
    const-string v0, "black"

    goto :goto_5

    :cond_6
    const-string v0, "white"

    goto :goto_6
.end method


# virtual methods
.method public a(Landroid/view/LayoutInflater;Landroid/view/ViewGroup;Landroid/os/Bundle;)Landroid/view/View;
    .locals 2

    if-eqz p3, :cond_0

    const-string v0, "DIVISION"

    invoke-virtual {p3, v0}, Landroid/os/Bundle;->getInt(Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Luk/co/dancingganesh/pocketvedas/k;->e:I

    const-string v0, "PATH"

    invoke-virtual {p3, v0}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->f:Ljava/lang/String;

    const-string v0, "POSITION"

    invoke-virtual {p3, v0}, Landroid/os/Bundle;->getFloat(Ljava/lang/String;)F

    move-result v0

    iput v0, p0, Luk/co/dancingganesh/pocketvedas/k;->g:F

    const-string v0, "HIGHLIGHT"

    invoke-virtual {p3, v0}, Landroid/os/Bundle;->getString(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->h:Ljava/lang/String;

    :cond_0
    const/4 v0, 0x1

    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/k;->c(Z)V

    const/4 v0, 0x0

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    new-instance v0, Landroid/widget/LinearLayout;

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/k;->h()Landroid/support/v4/app/h;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/widget/LinearLayout;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->d:Landroid/widget/LinearLayout;

    invoke-direct {p0}, Luk/co/dancingganesh/pocketvedas/k;->w()V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->d:Landroid/widget/LinearLayout;

    return-object v0
.end method

.method public a()V
    .locals 2

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    if-eqz v0, :cond_0

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->d:Landroid/widget/LinearLayout;

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    invoke-virtual {v0, v1}, Landroid/widget/LinearLayout;->removeView(Landroid/view/View;)V

    const/4 v0, 0x0

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/k;->c:Landroid/webkit/WebView;

    :cond_0
    invoke-direct {p0}, Luk/co/dancingganesh/pocketvedas/k;->w()V

    return-void
.end method

.method public a(F)V
    .locals 0

    iput p1, p0, Luk/co/dancingganesh/pocketvedas/k;->g:F

    return-void
.end method

.method public a(ILjava/lang/String;)V
    .locals 0

    iput p1, p0, Luk/co/dancingganesh/pocketvedas/k;->e:I

    iput-object p2, p0, Luk/co/dancingganesh/pocketvedas/k;->f:Ljava/lang/String;

    return-void
.end method

.method protected a(Landroid/webkit/WebView;)V
    .locals 3

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x13

    if-lt v0, v1, :cond_1

    :cond_0
    :goto_0
    return-void

    :cond_1
    const/high16 v0, 0x42c80000    # 100.0f

    invoke-virtual {p1}, Landroid/webkit/WebView;->getScale()F

    move-result v1

    mul-float/2addr v0, v1

    float-to-int v0, v0

    sget v1, Luk/co/dancingganesh/pocketvedas/k;->a:I

    if-eq v1, v0, :cond_0

    sput v0, Luk/co/dancingganesh/pocketvedas/k;->a:I

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/k;->h()Landroid/support/v4/app/h;

    move-result-object v0

    if-eqz v0, :cond_2

    invoke-static {v0}, Landroid/preference/PreferenceManager;->getDefaultSharedPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v0

    invoke-interface {v0}, Landroid/content/SharedPreferences;->edit()Landroid/content/SharedPreferences$Editor;

    move-result-object v0

    const-string v1, "pref_zoom"

    sget v2, Luk/co/dancingganesh/pocketvedas/k;->a:I

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences$Editor;->putInt(Ljava/lang/String;I)Landroid/content/SharedPreferences$Editor;

    invoke-interface {v0}, Landroid/content/SharedPreferences$Editor;->commit()Z

    :cond_2
    invoke-static {p0}, Luk/co/dancingganesh/pocketvedas/k;->a(Luk/co/dancingganesh/pocketvedas/k;)V

    goto :goto_0
.end method

.method public a(Ljava/lang/String;)V
    .locals 0

    iput-object p1, p0, Luk/co/dancingganesh/pocketvedas/k;->h:Ljava/lang/String;

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

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/k;->f:Ljava/lang/String;

    invoke-direct {p0}, Luk/co/dancingganesh/pocketvedas/k;->x()F

    move-result v2

    invoke-virtual {v0, v1, v2}, Luk/co/dancingganesh/pocketvedas/a;->a(Ljava/lang/String;F)V

    invoke-direct {p0}, Luk/co/dancingganesh/pocketvedas/k;->y()Luk/co/dancingganesh/pocketvedas/o;

    move-result-object v1

    invoke-virtual {v0, v1}, Luk/co/dancingganesh/pocketvedas/a;->a(Luk/co/dancingganesh/pocketvedas/o;)V

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/k;->h()Landroid/support/v4/app/h;

    move-result-object v1

    invoke-virtual {v1}, Landroid/support/v4/app/h;->e()Landroid/support/v4/app/l;

    move-result-object v1

    const-string v2, "bookmark"

    invoke-virtual {v0, v1, v2}, Luk/co/dancingganesh/pocketvedas/a;->a(Landroid/support/v4/app/l;Ljava/lang/String;)V

    const/4 v0, 0x1

    goto :goto_0

    :pswitch_data_0
    .packed-switch 0x7f090013
        :pswitch_0
    .end packed-switch
.end method

.method public e(Landroid/os/Bundle;)V
    .locals 2

    const-string v0, "DIVISION"

    iget v1, p0, Luk/co/dancingganesh/pocketvedas/k;->e:I

    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->putInt(Ljava/lang/String;I)V

    const-string v0, "PATH"

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/k;->f:Ljava/lang/String;

    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    invoke-direct {p0}, Luk/co/dancingganesh/pocketvedas/k;->x()F

    move-result v0

    const-string v1, "POSITION"

    invoke-virtual {p1, v1, v0}, Landroid/os/Bundle;->putFloat(Ljava/lang/String;F)V

    const-string v0, "HIGHLIGHT"

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/k;->h:Ljava/lang/String;

    invoke-virtual {p1, v0, v1}, Landroid/os/Bundle;->putString(Ljava/lang/String;Ljava/lang/String;)V

    return-void
.end method

.method public o()V
    .locals 4

    invoke-super {p0}, Landroid/support/v4/app/Fragment;->o()V

    invoke-direct {p0}, Luk/co/dancingganesh/pocketvedas/k;->y()Luk/co/dancingganesh/pocketvedas/o;

    move-result-object v0

    iget v1, p0, Luk/co/dancingganesh/pocketvedas/k;->e:I

    iget-object v2, p0, Luk/co/dancingganesh/pocketvedas/k;->f:Ljava/lang/String;

    invoke-direct {p0}, Luk/co/dancingganesh/pocketvedas/k;->x()F

    move-result v3

    invoke-virtual {v0, v1, v2, v3}, Luk/co/dancingganesh/pocketvedas/o;->a(ILjava/lang/String;F)V

    return-void
.end method
