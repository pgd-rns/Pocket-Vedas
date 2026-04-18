.class public Luk/co/dancingganesh/pocketvedas/a/d;
.super Ljava/lang/Object;


# static fields
.field private static d:Ljava/util/regex/Pattern;


# instance fields
.field private a:Ljava/lang/String;

.field private b:Ljava/lang/String;

.field private c:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    const-string v0, "<DIV CLASS=\"paragraph\">(Thus end[ s][^<]*)</DIV>"

    invoke-static {v0}, Ljava/util/regex/Pattern;->compile(Ljava/lang/String;)Ljava/util/regex/Pattern;

    move-result-object v0

    sput-object v0, Luk/co/dancingganesh/pocketvedas/a/d;->d:Ljava/util/regex/Pattern;

    return-void
.end method

.method private constructor <init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput-object p1, p0, Luk/co/dancingganesh/pocketvedas/a/d;->a:Ljava/lang/String;

    iput-object p2, p0, Luk/co/dancingganesh/pocketvedas/a/d;->b:Ljava/lang/String;

    iput-object p3, p0, Luk/co/dancingganesh/pocketvedas/a/d;->c:Ljava/lang/String;

    return-void
.end method

.method public static a(Luk/co/dancingganesh/pocketvedas/a/a;)Luk/co/dancingganesh/pocketvedas/a/d;
    .locals 6

    const/4 v2, 0x0

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/a/a;->c()I

    move-result v0

    if-nez v0, :cond_1

    :cond_0
    :goto_0
    return-object v2

    :cond_1
    new-instance v1, Ljava/lang/StringBuffer;

    invoke-direct {v1}, Ljava/lang/StringBuffer;-><init>()V

    new-instance v4, Ljava/lang/StringBuffer;

    invoke-direct {v4}, Ljava/lang/StringBuffer;-><init>()V

    invoke-static {}, Luk/co/dancingganesh/pocketvedas/a/c;->a()Luk/co/dancingganesh/pocketvedas/a/c;

    move-result-object v0

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/a/a;->b()I

    move-result v3

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/a/a;->a()I

    move-result v5

    invoke-virtual {v0, v3, v5, v1, v4}, Luk/co/dancingganesh/pocketvedas/a/c;->a(IILjava/lang/StringBuffer;Ljava/lang/StringBuffer;)Z

    move-result v0

    if-eqz v0, :cond_0

    invoke-static {}, Luk/co/dancingganesh/pocketvedas/a/c;->a()Luk/co/dancingganesh/pocketvedas/a/c;

    move-result-object v0

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/a/a;->b()I

    move-result v3

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/a/a;->d()Ljava/lang/String;

    move-result-object v5

    invoke-virtual {v0, v3, v5}, Luk/co/dancingganesh/pocketvedas/a/c;->a(ILjava/lang/String;)Ljava/lang/String;

    move-result-object v0

    sget-object v3, Luk/co/dancingganesh/pocketvedas/a/d;->d:Ljava/util/regex/Pattern;

    invoke-virtual {v3, v0}, Ljava/util/regex/Pattern;->matcher(Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;

    move-result-object v0

    invoke-virtual {v0}, Ljava/util/regex/Matcher;->find()Z

    move-result v3

    if-eqz v3, :cond_3

    const/4 v3, 0x1

    invoke-virtual {v0, v3}, Ljava/util/regex/Matcher;->group(I)Ljava/lang/String;

    move-result-object v0

    :goto_1
    new-instance v3, Luk/co/dancingganesh/pocketvedas/a/d;

    invoke-virtual {v1}, Ljava/lang/StringBuffer;->length()I

    move-result v5

    if-lez v5, :cond_4

    invoke-virtual {v1}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object v1

    :goto_2
    invoke-virtual {v4}, Ljava/lang/StringBuffer;->length()I

    move-result v5

    if-lez v5, :cond_2

    invoke-virtual {v4}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object v2

    :cond_2
    invoke-direct {v3, v0, v1, v2}, Luk/co/dancingganesh/pocketvedas/a/d;-><init>(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V

    move-object v2, v3

    goto :goto_0

    :cond_3
    new-instance v0, Ljava/lang/StringBuilder;

    const-string v3, "Thus ends "

    invoke-direct {v0, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-static {}, Luk/co/dancingganesh/pocketvedas/a/c;->a()Luk/co/dancingganesh/pocketvedas/a/c;

    move-result-object v3

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/a/a;->c()I

    move-result v5

    invoke-virtual {v3, v5}, Luk/co/dancingganesh/pocketvedas/a/c;->a(I)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_1

    :cond_4
    move-object v1, v2

    goto :goto_2
.end method


# virtual methods
.method public a()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/a/d;->a:Ljava/lang/String;

    return-object v0
.end method

.method public b()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/a/d;->b:Ljava/lang/String;

    return-object v0
.end method

.method public c()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/a/d;->c:Ljava/lang/String;

    return-object v0
.end method
