.class public Luk/co/dancingganesh/pocketvedas/o;
.super Landroid/support/v4/app/t;


# instance fields
.field private a:Ljava/util/ArrayList;

.field private b:I

.field private c:Ljava/lang/String;

.field private d:Ljava/lang/Float;

.field private e:Luk/co/dancingganesh/pocketvedas/ReadingActivity;

.field private f:Luk/co/dancingganesh/pocketvedas/a/d;

.field private g:I

.field private h:I


# direct methods
.method public constructor <init>(Landroid/support/v4/app/l;Ljava/lang/String;Ljava/lang/String;FILuk/co/dancingganesh/pocketvedas/ReadingActivity;Landroid/os/Bundle;)V
    .locals 3

    const/4 v1, 0x0

    invoke-direct {p0, p1}, Landroid/support/v4/app/t;-><init>(Landroid/support/v4/app/l;)V

    iput-object p3, p0, Luk/co/dancingganesh/pocketvedas/o;->c:Ljava/lang/String;

    invoke-static {p4}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v0

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->d:Ljava/lang/Float;

    iput p5, p0, Luk/co/dancingganesh/pocketvedas/o;->g:I

    iput-object p6, p0, Luk/co/dancingganesh/pocketvedas/o;->e:Luk/co/dancingganesh/pocketvedas/ReadingActivity;

    new-instance v0, Ljava/util/ArrayList;

    invoke-direct {v0}, Ljava/util/ArrayList;-><init>()V

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->a:Ljava/util/ArrayList;

    const-string v0, "+"

    invoke-virtual {p2, v0}, Ljava/lang/String;->endsWith(Ljava/lang/String;)Z

    move-result v0

    if-eqz v0, :cond_0

    const/4 v0, 0x1

    invoke-virtual {p2}, Ljava/lang/String;->length()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    invoke-virtual {p2, v1, v2}, Ljava/lang/String;->substring(II)Ljava/lang/String;

    move-result-object p2

    move v1, v0

    :cond_0
    invoke-static {}, Luk/co/dancingganesh/pocketvedas/a/c;->a()Luk/co/dancingganesh/pocketvedas/a/c;

    move-result-object v0

    iget-object v2, p0, Luk/co/dancingganesh/pocketvedas/o;->a:Ljava/util/ArrayList;

    invoke-virtual {v0, p2, v2}, Luk/co/dancingganesh/pocketvedas/a/c;->a(Ljava/lang/String;Ljava/util/ArrayList;)I

    move-result v0

    iput v0, p0, Luk/co/dancingganesh/pocketvedas/o;->b:I

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->a:Ljava/util/ArrayList;

    iget-object v2, p0, Luk/co/dancingganesh/pocketvedas/o;->a:Ljava/util/ArrayList;

    invoke-virtual {v2}, Ljava/util/ArrayList;->size()I

    move-result v2

    add-int/lit8 v2, v2, -0x1

    invoke-virtual {v0, v2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Luk/co/dancingganesh/pocketvedas/a/a;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/a/d;->a(Luk/co/dancingganesh/pocketvedas/a/a;)Luk/co/dancingganesh/pocketvedas/a/d;

    move-result-object v0

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->f:Luk/co/dancingganesh/pocketvedas/a/d;

    if-eqz v1, :cond_1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->f:Luk/co/dancingganesh/pocketvedas/a/d;

    if-eqz v0, :cond_1

    iget v0, p0, Luk/co/dancingganesh/pocketvedas/o;->b:I

    add-int/lit8 v0, v0, 0x1

    iput v0, p0, Luk/co/dancingganesh/pocketvedas/o;->b:I

    :cond_1
    return-void
.end method


# virtual methods
.method public a(I)Landroid/support/v4/app/Fragment;
    .locals 4

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->a:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge p1, v0, :cond_2

    new-instance v1, Luk/co/dancingganesh/pocketvedas/k;

    invoke-direct {v1}, Luk/co/dancingganesh/pocketvedas/k;-><init>()V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->a:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Luk/co/dancingganesh/pocketvedas/a/a;

    invoke-virtual {v0}, Luk/co/dancingganesh/pocketvedas/a/a;->b()I

    move-result v2

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->a:Ljava/util/ArrayList;

    invoke-virtual {v0, p1}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Luk/co/dancingganesh/pocketvedas/a/a;

    invoke-virtual {v0}, Luk/co/dancingganesh/pocketvedas/a/a;->d()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1, v2, v0}, Luk/co/dancingganesh/pocketvedas/k;->a(ILjava/lang/String;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->c:Ljava/lang/String;

    if-eqz v0, :cond_0

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->c:Ljava/lang/String;

    invoke-virtual {v1, v0}, Luk/co/dancingganesh/pocketvedas/k;->a(Ljava/lang/String;)V

    :cond_0
    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->d:Ljava/lang/Float;

    if-eqz v0, :cond_1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->d:Ljava/lang/Float;

    invoke-virtual {v0}, Ljava/lang/Float;->floatValue()F

    move-result v0

    invoke-virtual {v1, v0}, Luk/co/dancingganesh/pocketvedas/k;->a(F)V

    const/4 v0, 0x0

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->d:Ljava/lang/Float;

    :cond_1
    move-object v0, v1

    :goto_0
    return-object v0

    :cond_2
    new-instance v1, Luk/co/dancingganesh/pocketvedas/h;

    invoke-direct {v1}, Luk/co/dancingganesh/pocketvedas/h;-><init>()V

    new-instance v2, Ljava/lang/StringBuilder;

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->a:Ljava/util/ArrayList;

    iget-object v3, p0, Luk/co/dancingganesh/pocketvedas/o;->a:Ljava/util/ArrayList;

    invoke-virtual {v3}, Ljava/util/ArrayList;->size()I

    move-result v3

    add-int/lit8 v3, v3, -0x1

    invoke-virtual {v0, v3}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Luk/co/dancingganesh/pocketvedas/a/a;

    invoke-virtual {v0}, Luk/co/dancingganesh/pocketvedas/a/a;->d()Ljava/lang/String;

    move-result-object v0

    invoke-static {v0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v0

    invoke-direct {v2, v0}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    const/16 v0, 0x2b

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(C)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    iget-object v2, p0, Luk/co/dancingganesh/pocketvedas/o;->f:Luk/co/dancingganesh/pocketvedas/a/d;

    invoke-virtual {v1, v2, v0}, Luk/co/dancingganesh/pocketvedas/h;->a(Luk/co/dancingganesh/pocketvedas/a/d;Ljava/lang/String;)V

    move-object v0, v1

    goto :goto_0
.end method

.method public a(ILandroid/content/Intent;)V
    .locals 2

    iget v0, p0, Luk/co/dancingganesh/pocketvedas/o;->g:I

    if-eqz v0, :cond_1

    if-eqz p1, :cond_0

    invoke-static {}, Luk/co/dancingganesh/pocketvedas/a/c;->a()Luk/co/dancingganesh/pocketvedas/a/c;

    move-result-object v0

    invoke-virtual {v0, p1}, Luk/co/dancingganesh/pocketvedas/a/c;->d(I)Z

    move-result v0

    if-eqz v0, :cond_1

    :cond_0
    const-string v0, "uk.co.dancingganesh.pocketvedas.BOOKMARK_ROWID"

    iget v1, p0, Luk/co/dancingganesh/pocketvedas/o;->g:I

    invoke-virtual {p2, v0, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    const/4 v0, 0x0

    iput v0, p0, Luk/co/dancingganesh/pocketvedas/o;->g:I

    :cond_1
    return-void
.end method

.method public a(ILjava/lang/String;F)V
    .locals 3

    iget v0, p0, Luk/co/dancingganesh/pocketvedas/o;->g:I

    if-eqz v0, :cond_0

    iget v0, p0, Luk/co/dancingganesh/pocketvedas/o;->h:I

    if-ne p1, v0, :cond_0

    invoke-static {}, Luk/co/dancingganesh/pocketvedas/a/b;->a()Luk/co/dancingganesh/pocketvedas/a/b;

    move-result-object v0

    iget v1, p0, Luk/co/dancingganesh/pocketvedas/o;->g:I

    int-to-long v1, v1

    invoke-virtual {v0, v1, v2, p2, p3}, Luk/co/dancingganesh/pocketvedas/a/b;->a(JLjava/lang/String;F)V

    :cond_0
    return-void
.end method

.method public b()I
    .locals 2

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->a:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->f:Luk/co/dancingganesh/pocketvedas/a/d;

    if-nez v0, :cond_0

    const/4 v0, 0x0

    :goto_0
    add-int/2addr v0, v1

    return v0

    :cond_0
    const/4 v0, 0x1

    goto :goto_0
.end method

.method public b(Landroid/view/ViewGroup;ILjava/lang/Object;)V
    .locals 3

    invoke-super {p0, p1, p2, p3}, Landroid/support/v4/app/t;->b(Landroid/view/ViewGroup;ILjava/lang/Object;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->a:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ge p2, v0, :cond_1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->a:Ljava/util/ArrayList;

    invoke-virtual {v0, p2}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Luk/co/dancingganesh/pocketvedas/a/a;

    invoke-virtual {v0}, Luk/co/dancingganesh/pocketvedas/a/a;->b()I

    move-result v0

    iput v0, p0, Luk/co/dancingganesh/pocketvedas/o;->h:I

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->e:Luk/co/dancingganesh/pocketvedas/ReadingActivity;

    invoke-static {}, Luk/co/dancingganesh/pocketvedas/a/c;->a()Luk/co/dancingganesh/pocketvedas/a/c;

    move-result-object v1

    iget v2, p0, Luk/co/dancingganesh/pocketvedas/o;->h:I

    invoke-virtual {v1, v2}, Luk/co/dancingganesh/pocketvedas/a/c;->a(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->setTitle(Ljava/lang/CharSequence;)V

    :cond_0
    :goto_0
    return-void

    :cond_1
    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->a:Ljava/util/ArrayList;

    invoke-virtual {v0}, Ljava/util/ArrayList;->size()I

    move-result v0

    if-ne p2, v0, :cond_0

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->e:Luk/co/dancingganesh/pocketvedas/ReadingActivity;

    const-string v1, "Pocket Vedas"

    invoke-virtual {v0, v1}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->setTitle(Ljava/lang/CharSequence;)V

    const/4 v0, 0x0

    iput v0, p0, Luk/co/dancingganesh/pocketvedas/o;->h:I

    goto :goto_0
.end method

.method public c()I
    .locals 1

    iget v0, p0, Luk/co/dancingganesh/pocketvedas/o;->b:I

    return v0
.end method

.method public c(I)V
    .locals 0

    iput p1, p0, Luk/co/dancingganesh/pocketvedas/o;->g:I

    return-void
.end method

.method public d()Ljava/lang/String;
    .locals 5

    const/4 v1, 0x1

    const/4 v4, 0x0

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/o;->a:Ljava/util/ArrayList;

    invoke-virtual {v0, v4}, Ljava/util/ArrayList;->get(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Luk/co/dancingganesh/pocketvedas/a/a;

    invoke-virtual {v0}, Luk/co/dancingganesh/pocketvedas/a/a;->d()Ljava/lang/String;

    move-result-object v0

    const-string v2, "/"

    invoke-virtual {v0, v2}, Ljava/lang/String;->split(Ljava/lang/String;)[Ljava/lang/String;

    move-result-object v2

    array-length v0, v2

    const/4 v3, 0x2

    if-le v0, v3, :cond_1

    new-instance v3, Ljava/lang/StringBuffer;

    aget-object v0, v2, v4

    invoke-direct {v3, v0}, Ljava/lang/StringBuffer;-><init>(Ljava/lang/String;)V

    move v0, v1

    :goto_0
    array-length v1, v2

    add-int/lit8 v1, v1, -0x1

    if-lt v0, v1, :cond_0

    invoke-virtual {v3}, Ljava/lang/StringBuffer;->toString()Ljava/lang/String;

    move-result-object v0

    :goto_1
    return-object v0

    :cond_0
    const-string v1, "/"

    invoke-virtual {v3, v1}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    move-result-object v1

    aget-object v4, v2, v0

    invoke-virtual {v1, v4}, Ljava/lang/StringBuffer;->append(Ljava/lang/String;)Ljava/lang/StringBuffer;

    add-int/lit8 v0, v0, 0x1

    goto :goto_0

    :cond_1
    aget-object v0, v2, v1

    const-string v1, "index"

    invoke-virtual {v0, v1}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-eqz v0, :cond_2

    const/4 v0, 0x0

    goto :goto_1

    :cond_2
    new-instance v0, Ljava/lang/StringBuilder;

    aget-object v1, v2, v4

    invoke-static {v1}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-direct {v0, v1}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    const-string v1, "/index"

    invoke-virtual {v0, v1}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    goto :goto_1
.end method
