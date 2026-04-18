.class Luk/co/dancingganesh/pocketvedas/g;
.super Ljava/lang/Object;

# interfaces
.implements Landroid/widget/AdapterView$OnItemClickListener;


# instance fields
.field final synthetic a:Luk/co/dancingganesh/pocketvedas/BooksActivity;


# direct methods
.method constructor <init>(Luk/co/dancingganesh/pocketvedas/BooksActivity;)V
    .locals 0

    iput-object p1, p0, Luk/co/dancingganesh/pocketvedas/g;->a:Luk/co/dancingganesh/pocketvedas/BooksActivity;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onItemClick(Landroid/widget/AdapterView;Landroid/view/View;IJ)V
    .locals 4

    new-instance v0, Landroid/content/Intent;

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/g;->a:Luk/co/dancingganesh/pocketvedas/BooksActivity;

    const-class v2, Luk/co/dancingganesh/pocketvedas/ReadingActivity;

    invoke-direct {v0, v1, v2}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const-string v1, "uk.co.dancingganesh.pocketvedas.BOOK_ADDRESS"

    new-instance v2, Ljava/lang/StringBuilder;

    invoke-static {}, Luk/co/dancingganesh/pocketvedas/a/c;->a()Luk/co/dancingganesh/pocketvedas/a/c;

    move-result-object v3

    invoke-virtual {v3, p3}, Luk/co/dancingganesh/pocketvedas/a/c;->c(I)Ljava/lang/String;

    move-result-object v3

    invoke-static {v3}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v3

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    const-string v3, "/index"

    invoke-virtual {v2, v3}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v2

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v0, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/g;->a:Luk/co/dancingganesh/pocketvedas/BooksActivity;

    invoke-virtual {v1, v0}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->startActivity(Landroid/content/Intent;)V

    return-void
.end method
