.class public Luk/co/dancingganesh/pocketvedas/BooksActivity;
.super Landroid/app/Activity;


# instance fields
.field private a:Landroid/app/ProgressDialog;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Landroid/app/Activity;-><init>()V

    return-void
.end method

.method static synthetic a(Luk/co/dancingganesh/pocketvedas/BooksActivity;)Landroid/app/ProgressDialog;
    .locals 1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/BooksActivity;->a:Landroid/app/ProgressDialog;

    return-object v0
.end method


# virtual methods
.method protected onCreate(Landroid/os/Bundle;)V
    .locals 6

    const/4 v3, 0x1

    const/4 v5, 0x0

    invoke-super {p0, p1}, Landroid/app/Activity;->onCreate(Landroid/os/Bundle;)V

    new-instance v0, Landroid/app/ProgressDialog;

    invoke-direct {v0, p0}, Landroid/app/ProgressDialog;-><init>(Landroid/content/Context;)V

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/BooksActivity;->a:Landroid/app/ProgressDialog;

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/BooksActivity;->a:Landroid/app/ProgressDialog;

    const-string v1, "Unpacking books"

    invoke-virtual {v0, v1}, Landroid/app/ProgressDialog;->setMessage(Ljava/lang/CharSequence;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/BooksActivity;->a:Landroid/app/ProgressDialog;

    invoke-virtual {v0, v3}, Landroid/app/ProgressDialog;->setIndeterminate(Z)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/BooksActivity;->a:Landroid/app/ProgressDialog;

    invoke-virtual {v0, v5}, Landroid/app/ProgressDialog;->setProgressStyle(I)V

    const v0, 0x7f030002

    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->setContentView(I)V

    const v0, 0x7f090001

    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/GridView;

    new-instance v1, Luk/co/dancingganesh/pocketvedas/j;

    invoke-direct {v1, p0}, Luk/co/dancingganesh/pocketvedas/j;-><init>(Landroid/content/Context;)V

    new-instance v2, Luk/co/dancingganesh/pocketvedas/f;

    invoke-direct {v2, p0, v1, v0}, Luk/co/dancingganesh/pocketvedas/f;-><init>(Luk/co/dancingganesh/pocketvedas/BooksActivity;Luk/co/dancingganesh/pocketvedas/j;Landroid/widget/GridView;)V

    new-array v3, v3, [Ljava/lang/String;

    const-string v4, ""

    aput-object v4, v3, v5

    invoke-virtual {v2, v3}, Luk/co/dancingganesh/pocketvedas/f;->execute([Ljava/lang/Object;)Landroid/os/AsyncTask;

    const v2, -0xbfbfc0

    invoke-virtual {v0, v2}, Landroid/widget/GridView;->setBackgroundColor(I)V

    invoke-virtual {v0, v1}, Landroid/widget/GridView;->setAdapter(Landroid/widget/ListAdapter;)V

    new-instance v1, Luk/co/dancingganesh/pocketvedas/g;

    invoke-direct {v1, p0}, Luk/co/dancingganesh/pocketvedas/g;-><init>(Luk/co/dancingganesh/pocketvedas/BooksActivity;)V

    invoke-virtual {v0, v1}, Landroid/widget/GridView;->setOnItemClickListener(Landroid/widget/AdapterView$OnItemClickListener;)V

    return-void
.end method

.method public onCreateOptionsMenu(Landroid/view/Menu;)Z
    .locals 3
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "NewApi"
        }
    .end annotation

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->getMenuInflater()Landroid/view/MenuInflater;

    move-result-object v0

    const v1, 0x7f080002

    invoke-virtual {v0, v1, p1}, Landroid/view/MenuInflater;->inflate(ILandroid/view/Menu;)V

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0xb

    if-lt v0, v1, :cond_0

    const-string v0, "search"

    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/app/SearchManager;

    const v1, 0x7f090012

    invoke-interface {p1, v1}, Landroid/view/Menu;->findItem(I)Landroid/view/MenuItem;

    move-result-object v1

    invoke-interface {v1}, Landroid/view/MenuItem;->getActionView()Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/SearchView;

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->getComponentName()Landroid/content/ComponentName;

    move-result-object v2

    invoke-virtual {v0, v2}, Landroid/app/SearchManager;->getSearchableInfo(Landroid/content/ComponentName;)Landroid/app/SearchableInfo;

    move-result-object v0

    invoke-virtual {v1, v0}, Landroid/widget/SearchView;->setSearchableInfo(Landroid/app/SearchableInfo;)V

    :cond_0
    const/4 v0, 0x1

    return v0
.end method

.method public onOptionsItemSelected(Landroid/view/MenuItem;)Z
    .locals 4

    const/4 v0, 0x1

    invoke-interface {p1}, Landroid/view/MenuItem;->getItemId()I

    move-result v1

    packed-switch v1, :pswitch_data_0

    invoke-super {p0, p1}, Landroid/app/Activity;->onOptionsItemSelected(Landroid/view/MenuItem;)Z

    move-result v0

    :goto_0
    return v0

    :pswitch_0
    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->onSearchRequested()Z

    move-result v0

    goto :goto_0

    :pswitch_1
    new-instance v1, Landroid/content/Intent;

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->getBaseContext()Landroid/content/Context;

    move-result-object v2

    const-class v3, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;

    invoke-direct {v1, v2, v3}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p0, v1}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->startActivity(Landroid/content/Intent;)V

    goto :goto_0

    :pswitch_2
    new-instance v1, Landroid/content/Intent;

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->getBaseContext()Landroid/content/Context;

    move-result-object v2

    const-class v3, Luk/co/dancingganesh/pocketvedas/SettingsActivity;

    invoke-direct {v1, v2, v3}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p0, v1}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->startActivity(Landroid/content/Intent;)V

    goto :goto_0

    :pswitch_3
    new-instance v1, Landroid/content/Intent;

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->getBaseContext()Landroid/content/Context;

    move-result-object v2

    const-class v3, Luk/co/dancingganesh/pocketvedas/AboutActivity;

    invoke-direct {v1, v2, v3}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p0, v1}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->startActivity(Landroid/content/Intent;)V

    goto :goto_0

    nop

    :pswitch_data_0
    .packed-switch 0x7f09000f
        :pswitch_2
        :pswitch_3
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method
