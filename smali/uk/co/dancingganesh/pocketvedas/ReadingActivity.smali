.class public Luk/co/dancingganesh/pocketvedas/ReadingActivity;
.super Landroid/support/v4/app/h;


# instance fields
.field private m:Luk/co/dancingganesh/pocketvedas/o;

.field private n:Landroid/support/v4/view/ViewPager;


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Landroid/support/v4/app/h;-><init>()V

    return-void
.end method


# virtual methods
.method public f()Luk/co/dancingganesh/pocketvedas/o;
    .locals 1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->m:Luk/co/dancingganesh/pocketvedas/o;

    return-object v0
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 8
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "NewApi"
        }
    .end annotation

    invoke-static {p0}, Luk/co/dancingganesh/pocketvedas/a/c;->a(Landroid/content/Context;)V

    invoke-static {p0}, Luk/co/dancingganesh/pocketvedas/a/b;->a(Landroid/content/Context;)V

    invoke-super {p0, p1}, Landroid/support/v4/app/h;->onCreate(Landroid/os/Bundle;)V

    const v0, 0x7f030003

    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->setContentView(I)V

    const v0, 0x7f090002

    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/support/v4/view/ViewPager;

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->n:Landroid/support/v4/view/ViewPager;

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->getIntent()Landroid/content/Intent;

    move-result-object v0

    const-string v1, "uk.co.dancingganesh.pocketvedas.BOOK_ADDRESS"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v2

    if-nez v2, :cond_0

    invoke-virtual {v0}, Landroid/content/Intent;->getData()Landroid/net/Uri;

    move-result-object v1

    invoke-virtual {v1}, Landroid/net/Uri;->toString()Ljava/lang/String;

    move-result-object v2

    const-string v1, "veda:"

    invoke-virtual {v2, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    const/4 v1, 0x5

    invoke-virtual {v2, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v2

    const-string v1, "//"

    invoke-virtual {v2, v1}, Ljava/lang/String;->startsWith(Ljava/lang/String;)Z

    move-result v1

    if-eqz v1, :cond_0

    const/4 v1, 0x2

    invoke-virtual {v2, v1}, Ljava/lang/String;->substring(I)Ljava/lang/String;

    move-result-object v2

    :cond_0
    const-string v1, "uk.co.dancingganesh.pocketvedas.SEARCH_TEXT"

    invoke-virtual {v0, v1}, Landroid/content/Intent;->getStringExtra(Ljava/lang/String;)Ljava/lang/String;

    move-result-object v3

    const-string v1, "uk.co.dancingganesh.pocketvedas.BOOKMARK_OFFSET"

    const/4 v4, 0x0

    invoke-virtual {v0, v1, v4}, Landroid/content/Intent;->getFloatExtra(Ljava/lang/String;F)F

    move-result v4

    const-string v1, "uk.co.dancingganesh.pocketvedas.BOOKMARK_ROWID"

    const/4 v5, 0x0

    invoke-virtual {v0, v1, v5}, Landroid/content/Intent;->getIntExtra(Ljava/lang/String;I)I

    move-result v5

    new-instance v0, Luk/co/dancingganesh/pocketvedas/o;

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->e()Landroid/support/v4/app/l;

    move-result-object v1

    move-object v6, p0

    move-object v7, p1

    invoke-direct/range {v0 .. v7}, Luk/co/dancingganesh/pocketvedas/o;-><init>(Landroid/support/v4/app/l;Ljava/lang/String;Ljava/lang/String;FILuk/co/dancingganesh/pocketvedas/ReadingActivity;Landroid/os/Bundle;)V

    iput-object v0, p0, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->m:Luk/co/dancingganesh/pocketvedas/o;

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->n:Landroid/support/v4/view/ViewPager;

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->m:Luk/co/dancingganesh/pocketvedas/o;

    invoke-virtual {v0, v1}, Landroid/support/v4/view/ViewPager;->setAdapter(Landroid/support/v4/view/x;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->n:Landroid/support/v4/view/ViewPager;

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->m:Luk/co/dancingganesh/pocketvedas/o;

    invoke-virtual {v1}, Luk/co/dancingganesh/pocketvedas/o;->c()I

    move-result v1

    invoke-virtual {v0, v1}, Landroid/support/v4/view/ViewPager;->setCurrentItem(I)V

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0xb

    if-lt v0, v1, :cond_1

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->getActionBar()Landroid/app/ActionBar;

    move-result-object v0

    const/4 v1, 0x1

    invoke-virtual {v0, v1}, Landroid/app/ActionBar;->setDisplayHomeAsUpEnabled(Z)V

    :cond_1
    return-void
.end method

.method public onCreateOptionsMenu(Landroid/view/Menu;)Z
    .locals 3
    .annotation build Landroid/annotation/SuppressLint;
        value = {
            "NewApi"
        }
    .end annotation

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->getMenuInflater()Landroid/view/MenuInflater;

    move-result-object v0

    const v1, 0x7f080003

    invoke-virtual {v0, v1, p1}, Landroid/view/MenuInflater;->inflate(ILandroid/view/Menu;)V

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0xb

    if-lt v0, v1, :cond_0

    const-string v0, "search"

    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->getSystemService(Ljava/lang/String;)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/app/SearchManager;

    const v1, 0x7f090012

    invoke-interface {p1, v1}, Landroid/view/Menu;->findItem(I)Landroid/view/MenuItem;

    move-result-object v1

    invoke-interface {v1}, Landroid/view/MenuItem;->getActionView()Landroid/view/View;

    move-result-object v1

    check-cast v1, Landroid/widget/SearchView;

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->getComponentName()Landroid/content/ComponentName;

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

    const/4 v1, 0x1

    invoke-interface {p1}, Landroid/view/MenuItem;->getItemId()I

    move-result v0

    sparse-switch v0, :sswitch_data_0

    invoke-super {p0, p1}, Landroid/support/v4/app/h;->onOptionsItemSelected(Landroid/view/MenuItem;)Z

    move-result v0

    :goto_0
    return v0

    :sswitch_0
    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->m:Luk/co/dancingganesh/pocketvedas/o;

    invoke-virtual {v0}, Luk/co/dancingganesh/pocketvedas/o;->d()Ljava/lang/String;

    move-result-object v2

    if-eqz v2, :cond_0

    new-instance v0, Landroid/content/Intent;

    const-class v3, Luk/co/dancingganesh/pocketvedas/ReadingActivity;

    invoke-direct {v0, p0, v3}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const-string v3, "uk.co.dancingganesh.pocketvedas.BOOK_ADDRESS"

    invoke-virtual {v0, v3, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    :goto_1
    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->startActivity(Landroid/content/Intent;)V

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->finish()V

    move v0, v1

    goto :goto_0

    :cond_0
    new-instance v0, Landroid/content/Intent;

    const-class v2, Luk/co/dancingganesh/pocketvedas/BooksActivity;

    invoke-direct {v0, p0, v2}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    goto :goto_1

    :sswitch_1
    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->onSearchRequested()Z

    move-result v0

    goto :goto_0

    :sswitch_2
    new-instance v0, Landroid/content/Intent;

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->getBaseContext()Landroid/content/Context;

    move-result-object v2

    const-class v3, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;

    invoke-direct {v0, v2, v3}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->startActivity(Landroid/content/Intent;)V

    move v0, v1

    goto :goto_0

    :sswitch_3
    new-instance v0, Landroid/content/Intent;

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->getBaseContext()Landroid/content/Context;

    move-result-object v2

    const-class v3, Luk/co/dancingganesh/pocketvedas/SettingsActivity;

    invoke-direct {v0, v2, v3}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->startActivity(Landroid/content/Intent;)V

    move v0, v1

    goto :goto_0

    :sswitch_4
    new-instance v0, Landroid/content/Intent;

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->getBaseContext()Landroid/content/Context;

    move-result-object v2

    const-class v3, Luk/co/dancingganesh/pocketvedas/AboutActivity;

    invoke-direct {v0, v2, v3}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/ReadingActivity;->startActivity(Landroid/content/Intent;)V

    move v0, v1

    goto :goto_0

    :sswitch_data_0
    .sparse-switch
        0x102002c -> :sswitch_0
        0x7f09000f -> :sswitch_3
        0x7f090010 -> :sswitch_4
        0x7f090011 -> :sswitch_2
        0x7f090012 -> :sswitch_1
    .end sparse-switch
.end method
