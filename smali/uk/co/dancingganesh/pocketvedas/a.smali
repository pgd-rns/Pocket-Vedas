.class public Luk/co/dancingganesh/pocketvedas/a;
.super Landroid/support/v4/app/e;


# instance fields
.field private W:Ljava/lang/String;

.field private X:F

.field private Y:Luk/co/dancingganesh/pocketvedas/o;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Landroid/support/v4/app/e;-><init>()V

    return-void
.end method

.method static synthetic a(Luk/co/dancingganesh/pocketvedas/a;)Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/a;->W:Ljava/lang/String;

    return-object v0
.end method

.method static synthetic b(Luk/co/dancingganesh/pocketvedas/a;)F
    .locals 1

    iget v0, p0, Luk/co/dancingganesh/pocketvedas/a;->X:F

    return v0
.end method

.method static synthetic c(Luk/co/dancingganesh/pocketvedas/a;)Luk/co/dancingganesh/pocketvedas/o;
    .locals 1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/a;->Y:Luk/co/dancingganesh/pocketvedas/o;

    return-object v0
.end method


# virtual methods
.method public a(Ljava/lang/String;F)V
    .locals 0

    iput-object p1, p0, Luk/co/dancingganesh/pocketvedas/a;->W:Ljava/lang/String;

    iput p2, p0, Luk/co/dancingganesh/pocketvedas/a;->X:F

    return-void
.end method

.method public a(Luk/co/dancingganesh/pocketvedas/o;)V
    .locals 0

    iput-object p1, p0, Luk/co/dancingganesh/pocketvedas/a;->Y:Luk/co/dancingganesh/pocketvedas/o;

    return-void
.end method

.method public c(Landroid/os/Bundle;)Landroid/app/Dialog;
    .locals 4

    new-instance v0, Landroid/app/AlertDialog$Builder;

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/a;->h()Landroid/support/v4/app/h;

    move-result-object v1

    invoke-direct {v0, v1}, Landroid/app/AlertDialog$Builder;-><init>(Landroid/content/Context;)V

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/a;->h()Landroid/support/v4/app/h;

    move-result-object v1

    invoke-virtual {v1}, Landroid/support/v4/app/h;->getLayoutInflater()Landroid/view/LayoutInflater;

    move-result-object v1

    const v2, 0x7f030006

    const/4 v3, 0x0

    invoke-virtual {v1, v2, v3}, Landroid/view/LayoutInflater;->inflate(ILandroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/app/AlertDialog$Builder;->setView(Landroid/view/View;)Landroid/app/AlertDialog$Builder;

    const v2, 0x7f060003

    invoke-virtual {v0, v2}, Landroid/app/AlertDialog$Builder;->setTitle(I)Landroid/app/AlertDialog$Builder;

    const v2, 0x7f06000c

    new-instance v3, Luk/co/dancingganesh/pocketvedas/b;

    invoke-direct {v3, p0, v1}, Luk/co/dancingganesh/pocketvedas/b;-><init>(Luk/co/dancingganesh/pocketvedas/a;Landroid/view/View;)V

    invoke-virtual {v0, v2, v3}, Landroid/app/AlertDialog$Builder;->setPositiveButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    const v1, 0x7f06000d

    new-instance v2, Luk/co/dancingganesh/pocketvedas/c;

    invoke-direct {v2, p0}, Luk/co/dancingganesh/pocketvedas/c;-><init>(Luk/co/dancingganesh/pocketvedas/a;)V

    invoke-virtual {v0, v1, v2}, Landroid/app/AlertDialog$Builder;->setNegativeButton(ILandroid/content/DialogInterface$OnClickListener;)Landroid/app/AlertDialog$Builder;

    invoke-virtual {v0}, Landroid/app/AlertDialog$Builder;->create()Landroid/app/AlertDialog;

    move-result-object v0

    return-object v0
.end method
