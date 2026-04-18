.class public Luk/co/dancingganesh/pocketvedas/BookmarksActivity;
.super Landroid/app/ListActivity;


# static fields
.field private static a:Ljava/util/WeakHashMap;


# instance fields
.field private b:Landroid/app/ProgressDialog;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Ljava/util/WeakHashMap;

    invoke-direct {v0}, Ljava/util/WeakHashMap;-><init>()V

    sput-object v0, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->a:Ljava/util/WeakHashMap;

    return-void
.end method

.method public constructor <init>()V
    .locals 2

    invoke-direct {p0}, Landroid/app/ListActivity;-><init>()V

    sget-object v0, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->a:Ljava/util/WeakHashMap;

    const/4 v1, 0x0

    invoke-static {v1}, Ljava/lang/Boolean;->valueOf(Z)Ljava/lang/Boolean;

    move-result-object v1

    invoke-virtual {v0, p0, v1}, Ljava/util/WeakHashMap;->put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;

    return-void
.end method

.method static synthetic a(Luk/co/dancingganesh/pocketvedas/BookmarksActivity;)Landroid/app/ProgressDialog;
    .locals 1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->b:Landroid/app/ProgressDialog;

    return-object v0
.end method

.method public static a()V
    .locals 3

    sget-object v0, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->a:Ljava/util/WeakHashMap;

    invoke-virtual {v0}, Ljava/util/WeakHashMap;->keySet()Ljava/util/Set;

    move-result-object v0

    invoke-interface {v0}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v1

    :goto_0
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v0

    if-nez v0, :cond_0

    return-void

    :cond_0
    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;

    const v2, 0x102000a

    invoke-virtual {v0, v2}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ListView;

    invoke-virtual {v0}, Landroid/widget/ListView;->getAdapter()Landroid/widget/ListAdapter;

    move-result-object v0

    check-cast v0, Luk/co/dancingganesh/pocketvedas/e;

    invoke-static {}, Luk/co/dancingganesh/pocketvedas/a/b;->a()Luk/co/dancingganesh/pocketvedas/a/b;

    move-result-object v2

    invoke-virtual {v2}, Luk/co/dancingganesh/pocketvedas/a/b;->b()Landroid/database/Cursor;

    move-result-object v2

    invoke-virtual {v0, v2}, Luk/co/dancingganesh/pocketvedas/e;->a(Landroid/database/Cursor;)V

    goto :goto_0
.end method


# virtual methods
.method public onContextItemSelected(Landroid/view/MenuItem;)Z
    .locals 3

    const/4 v1, 0x0

    invoke-interface {p1}, Landroid/view/MenuItem;->getMenuInfo()Landroid/view/ContextMenu$ContextMenuInfo;

    move-result-object v0

    check-cast v0, Landroid/widget/AdapterView$AdapterContextMenuInfo;

    invoke-interface {p1}, Landroid/view/MenuItem;->getItemId()I

    move-result v2

    packed-switch v2, :pswitch_data_0

    move v0, v1

    :goto_0
    return v0

    :pswitch_0
    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->getListAdapter()Landroid/widget/ListAdapter;

    move-result-object v2

    iget v0, v0, Landroid/widget/AdapterView$AdapterContextMenuInfo;->position:I

    invoke-interface {v2, v0}, Landroid/widget/ListAdapter;->getItem(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/database/Cursor;

    invoke-interface {v0, v1}, Landroid/database/Cursor;->getInt(I)I

    move-result v0

    invoke-static {}, Luk/co/dancingganesh/pocketvedas/a/b;->a()Luk/co/dancingganesh/pocketvedas/a/b;

    move-result-object v1

    invoke-virtual {v1, v0}, Luk/co/dancingganesh/pocketvedas/a/b;->a(I)V

    const/4 v0, 0x1

    goto :goto_0

    nop

    :pswitch_data_0
    .packed-switch 0x7f090014
        :pswitch_0
    .end packed-switch
.end method

.method public onCreate(Landroid/os/Bundle;)V
    .locals 5

    const/4 v4, 0x1

    const/4 v3, 0x0

    invoke-static {p0}, Luk/co/dancingganesh/pocketvedas/a/c;->a(Landroid/content/Context;)V

    invoke-static {p0}, Luk/co/dancingganesh/pocketvedas/a/b;->a(Landroid/content/Context;)V

    invoke-super {p0, p1}, Landroid/app/ListActivity;->onCreate(Landroid/os/Bundle;)V

    const v0, 0x7f030001

    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->setContentView(I)V

    const v0, 0x102000a

    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ListView;

    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->registerForContextMenu(Landroid/view/View;)V

    new-instance v1, Landroid/app/ProgressDialog;

    invoke-direct {v1, p0}, Landroid/app/ProgressDialog;-><init>(Landroid/content/Context;)V

    iput-object v1, p0, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->b:Landroid/app/ProgressDialog;

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->b:Landroid/app/ProgressDialog;

    const-string v2, "Loading"

    invoke-virtual {v1, v2}, Landroid/app/ProgressDialog;->setMessage(Ljava/lang/CharSequence;)V

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->b:Landroid/app/ProgressDialog;

    invoke-virtual {v1, v4}, Landroid/app/ProgressDialog;->setIndeterminate(Z)V

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->b:Landroid/app/ProgressDialog;

    invoke-virtual {v1, v3}, Landroid/app/ProgressDialog;->setProgressStyle(I)V

    new-instance v1, Luk/co/dancingganesh/pocketvedas/d;

    invoke-direct {v1, p0, v0}, Luk/co/dancingganesh/pocketvedas/d;-><init>(Luk/co/dancingganesh/pocketvedas/BookmarksActivity;Landroid/widget/ListView;)V

    new-array v0, v4, [Ljava/lang/String;

    const-string v2, ""

    aput-object v2, v0, v3

    invoke-virtual {v1, v0}, Luk/co/dancingganesh/pocketvedas/d;->execute([Ljava/lang/Object;)Landroid/os/AsyncTask;

    return-void
.end method

.method public onCreateContextMenu(Landroid/view/ContextMenu;Landroid/view/View;Landroid/view/ContextMenu$ContextMenuInfo;)V
    .locals 2

    invoke-super {p0, p1, p2, p3}, Landroid/app/ListActivity;->onCreateContextMenu(Landroid/view/ContextMenu;Landroid/view/View;Landroid/view/ContextMenu$ContextMenuInfo;)V

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->getMenuInflater()Landroid/view/MenuInflater;

    move-result-object v0

    const v1, 0x7f080005

    invoke-virtual {v0, v1, p1}, Landroid/view/MenuInflater;->inflate(ILandroid/view/Menu;)V

    return-void
.end method

.method public onCreateOptionsMenu(Landroid/view/Menu;)Z
    .locals 2

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->getMenuInflater()Landroid/view/MenuInflater;

    move-result-object v0

    const v1, 0x7f080001

    invoke-virtual {v0, v1, p1}, Landroid/view/MenuInflater;->inflate(ILandroid/view/Menu;)V

    const/4 v0, 0x1

    return v0
.end method

.method public onListItemClick(Landroid/widget/ListView;Landroid/view/View;IJ)V
    .locals 5

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->getListAdapter()Landroid/widget/ListAdapter;

    move-result-object v0

    invoke-interface {v0, p3}, Landroid/widget/ListAdapter;->getItem(I)Ljava/lang/Object;

    move-result-object v0

    check-cast v0, Landroid/database/Cursor;

    const/4 v1, 0x1

    invoke-interface {v0, v1}, Landroid/database/Cursor;->getString(I)Ljava/lang/String;

    move-result-object v1

    const/4 v2, 0x4

    invoke-interface {v0, v2}, Landroid/database/Cursor;->getFloat(I)F

    move-result v2

    invoke-static {v2}, Ljava/lang/Float;->valueOf(F)Ljava/lang/Float;

    move-result-object v2

    new-instance v3, Landroid/content/Intent;

    const-class v4, Luk/co/dancingganesh/pocketvedas/ReadingActivity;

    invoke-direct {v3, p0, v4}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    const-string v4, "uk.co.dancingganesh.pocketvedas.BOOK_ADDRESS"

    invoke-virtual {v3, v4, v1}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;

    const-string v1, "uk.co.dancingganesh.pocketvedas.BOOKMARK_OFFSET"

    invoke-virtual {v3, v1, v2}, Landroid/content/Intent;->putExtra(Ljava/lang/String;Ljava/io/Serializable;)Landroid/content/Intent;

    const/4 v1, 0x5

    invoke-interface {v0, v1}, Landroid/database/Cursor;->getInt(I)I

    move-result v1

    if-eqz v1, :cond_0

    const-string v1, "uk.co.dancingganesh.pocketvedas.BOOKMARK_ROWID"

    const/4 v2, 0x0

    invoke-interface {v0, v2}, Landroid/database/Cursor;->getInt(I)I

    move-result v0

    invoke-virtual {v3, v1, v0}, Landroid/content/Intent;->putExtra(Ljava/lang/String;I)Landroid/content/Intent;

    :cond_0
    invoke-virtual {p0, v3}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->startActivity(Landroid/content/Intent;)V

    return-void
.end method

.method public onOptionsItemSelected(Landroid/view/MenuItem;)Z
    .locals 4

    const/4 v0, 0x1

    invoke-interface {p1}, Landroid/view/MenuItem;->getItemId()I

    move-result v1

    packed-switch v1, :pswitch_data_0

    invoke-super {p0, p1}, Landroid/app/ListActivity;->onOptionsItemSelected(Landroid/view/MenuItem;)Z

    move-result v0

    :goto_0
    return v0

    :pswitch_0
    new-instance v1, Landroid/content/Intent;

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->getBaseContext()Landroid/content/Context;

    move-result-object v2

    const-class v3, Luk/co/dancingganesh/pocketvedas/SettingsActivity;

    invoke-direct {v1, v2, v3}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p0, v1}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->startActivity(Landroid/content/Intent;)V

    goto :goto_0

    :pswitch_1
    new-instance v1, Landroid/content/Intent;

    invoke-virtual {p0}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->getBaseContext()Landroid/content/Context;

    move-result-object v2

    const-class v3, Luk/co/dancingganesh/pocketvedas/AboutActivity;

    invoke-direct {v1, v2, v3}, Landroid/content/Intent;-><init>(Landroid/content/Context;Ljava/lang/Class;)V

    invoke-virtual {p0, v1}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->startActivity(Landroid/content/Intent;)V

    goto :goto_0

    nop

    :pswitch_data_0
    .packed-switch 0x7f09000f
        :pswitch_0
        :pswitch_1
    .end packed-switch
.end method
