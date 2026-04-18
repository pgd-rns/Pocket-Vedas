.class public Luk/co/dancingganesh/pocketvedas/a/a;
.super Ljava/lang/Object;


# instance fields
.field private a:I

.field private b:I

.field private c:I

.field private d:Ljava/lang/String;


# direct methods
.method public constructor <init>(IIILjava/lang/String;)V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    iput p1, p0, Luk/co/dancingganesh/pocketvedas/a/a;->a:I

    iput p2, p0, Luk/co/dancingganesh/pocketvedas/a/a;->b:I

    iput p3, p0, Luk/co/dancingganesh/pocketvedas/a/a;->c:I

    iput-object p4, p0, Luk/co/dancingganesh/pocketvedas/a/a;->d:Ljava/lang/String;

    return-void
.end method


# virtual methods
.method public a()I
    .locals 1

    iget v0, p0, Luk/co/dancingganesh/pocketvedas/a/a;->a:I

    return v0
.end method

.method public b()I
    .locals 1

    iget v0, p0, Luk/co/dancingganesh/pocketvedas/a/a;->b:I

    return v0
.end method

.method public c()I
    .locals 1

    iget v0, p0, Luk/co/dancingganesh/pocketvedas/a/a;->c:I

    return v0
.end method

.method public d()Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/a/a;->d:Ljava/lang/String;

    return-object v0
.end method
