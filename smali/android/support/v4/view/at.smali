.class final Landroid/support/v4/view/at;
.super Ljava/lang/Object;

# interfaces
.implements Ljava/util/Comparator;


# direct methods
.method constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public a(Landroid/support/v4/view/aw;Landroid/support/v4/view/aw;)I
    .locals 2

    iget v0, p1, Landroid/support/v4/view/aw;->b:I

    iget v1, p2, Landroid/support/v4/view/aw;->b:I

    sub-int/2addr v0, v1

    return v0
.end method

.method public synthetic compare(Ljava/lang/Object;Ljava/lang/Object;)I
    .locals 1

    check-cast p1, Landroid/support/v4/view/aw;

    check-cast p2, Landroid/support/v4/view/aw;

    invoke-virtual {p0, p1, p2}, Landroid/support/v4/view/at;->a(Landroid/support/v4/view/aw;Landroid/support/v4/view/aw;)I

    move-result v0

    return v0
.end method
