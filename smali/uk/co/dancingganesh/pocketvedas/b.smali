.class Luk/co/dancingganesh/pocketvedas/b;
.super Ljava/lang/Object;

# interfaces
.implements Landroid/content/DialogInterface$OnClickListener;


# instance fields
.field final synthetic a:Luk/co/dancingganesh/pocketvedas/a;

.field private final synthetic b:Landroid/view/View;


# direct methods
.method constructor <init>(Luk/co/dancingganesh/pocketvedas/a;Landroid/view/View;)V
    .locals 0

    iput-object p1, p0, Luk/co/dancingganesh/pocketvedas/b;->a:Luk/co/dancingganesh/pocketvedas/a;

    iput-object p2, p0, Luk/co/dancingganesh/pocketvedas/b;->b:Landroid/view/View;

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method


# virtual methods
.method public onClick(Landroid/content/DialogInterface;I)V
    .locals 6

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/b;->b:Landroid/view/View;

    const v1, 0x7f090006

    invoke-virtual {v0, v1}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/EditText;

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/b;->b:Landroid/view/View;

    const v2, 0x7f090007

    invoke-virtual {v1, v2}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/CheckBox;

    invoke-static {}, Luk/co/dancingganesh/pocketvedas/a/b;->a()Luk/co/dancingganesh/pocketvedas/a/b;

    move-result-object v2

    iget-object v3, p0, Luk/co/dancingganesh/pocketvedas/b;->a:Luk/co/dancingganesh/pocketvedas/a;

    invoke-static {v3}, Luk/co/dancingganesh/pocketvedas/a;->a(Luk/co/dancingganesh/pocketvedas/a;)Ljava/lang/String;

    move-result-object v3

    invoke-virtual {v0}, Landroid/widget/EditText;->getText()Landroid/text/Editable;

    move-result-object v0

    invoke-interface {v0}, Landroid/text/Editable;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v1}, Landroid/widget/CheckBox;->isChecked()Z

    move-result v4

    iget-object v5, p0, Luk/co/dancingganesh/pocketvedas/b;->a:Luk/co/dancingganesh/pocketvedas/a;

    invoke-static {v5}, Luk/co/dancingganesh/pocketvedas/a;->b(Luk/co/dancingganesh/pocketvedas/a;)F

    move-result v5

    invoke-virtual {v2, v3, v0, v4, v5}, Luk/co/dancingganesh/pocketvedas/a/b;->a(Ljava/lang/String;Ljava/lang/String;ZF)J

    move-result-wide v2

    invoke-virtual {v1}, Landroid/widget/CheckBox;->isChecked()Z

    move-result v0

    if-eqz v0, :cond_0

    sget-object v0, Ljava/lang/System;->out:Ljava/io/PrintStream;

    new-instance v1, Ljava/lang/StringBuilder;

    const-string v4, "Track current page for bookmark "

    invoke-direct {v1, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v1, v2, v3}, Ljava/lang/StringBuilder;->append(J)Ljava/lang/StringBuilder;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Ljava/io/PrintStream;->println(Ljava/lang/String;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/b;->a:Luk/co/dancingganesh/pocketvedas/a;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/a;->c(Luk/co/dancingganesh/pocketvedas/a;)Luk/co/dancingganesh/pocketvedas/o;

    move-result-object v0

    long-to-int v1, v2

    invoke-virtual {v0, v1}, Luk/co/dancingganesh/pocketvedas/o;->c(I)V

    :cond_0
    return-void
.end method
