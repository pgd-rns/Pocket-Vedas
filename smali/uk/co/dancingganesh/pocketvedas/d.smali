.class Luk/co/dancingganesh/pocketvedas/d;
.super Landroid/os/AsyncTask;


# instance fields
.field final synthetic a:Luk/co/dancingganesh/pocketvedas/BookmarksActivity;

.field private final synthetic b:Landroid/widget/ListView;


# direct methods
.method constructor <init>(Luk/co/dancingganesh/pocketvedas/BookmarksActivity;Landroid/widget/ListView;)V
    .locals 0

    iput-object p1, p0, Luk/co/dancingganesh/pocketvedas/d;->a:Luk/co/dancingganesh/pocketvedas/BookmarksActivity;

    iput-object p2, p0, Luk/co/dancingganesh/pocketvedas/d;->b:Landroid/widget/ListView;

    invoke-direct {p0}, Landroid/os/AsyncTask;-><init>()V

    return-void
.end method


# virtual methods
.method protected varargs a([Ljava/lang/String;)Landroid/database/Cursor;
    .locals 1

    invoke-static {}, Luk/co/dancingganesh/pocketvedas/a/b;->a()Luk/co/dancingganesh/pocketvedas/a/b;

    move-result-object v0

    invoke-virtual {v0}, Luk/co/dancingganesh/pocketvedas/a/b;->b()Landroid/database/Cursor;

    move-result-object v0

    return-object v0
.end method

.method protected a(Landroid/database/Cursor;)V
    .locals 4

    invoke-super {p0, p1}, Landroid/os/AsyncTask;->onPostExecute(Ljava/lang/Object;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/d;->a:Luk/co/dancingganesh/pocketvedas/BookmarksActivity;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->a(Luk/co/dancingganesh/pocketvedas/BookmarksActivity;)Landroid/app/ProgressDialog;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/ProgressDialog;->dismiss()V

    new-instance v0, Luk/co/dancingganesh/pocketvedas/e;

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/d;->a:Luk/co/dancingganesh/pocketvedas/BookmarksActivity;

    iget-object v2, p0, Luk/co/dancingganesh/pocketvedas/d;->a:Luk/co/dancingganesh/pocketvedas/BookmarksActivity;

    const/4 v3, 0x0

    invoke-direct {v0, v1, v2, p1, v3}, Luk/co/dancingganesh/pocketvedas/e;-><init>(Luk/co/dancingganesh/pocketvedas/BookmarksActivity;Landroid/content/Context;Landroid/database/Cursor;I)V

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/d;->a:Luk/co/dancingganesh/pocketvedas/BookmarksActivity;

    invoke-virtual {v1, v0}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->setListAdapter(Landroid/widget/ListAdapter;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/d;->b:Landroid/widget/ListView;

    invoke-virtual {v0}, Landroid/widget/ListView;->postInvalidate()V

    return-void
.end method

.method protected varargs a([Ljava/lang/Integer;)V
    .locals 1

    invoke-super {p0, p1}, Landroid/os/AsyncTask;->onProgressUpdate([Ljava/lang/Object;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/d;->a:Luk/co/dancingganesh/pocketvedas/BookmarksActivity;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->a(Luk/co/dancingganesh/pocketvedas/BookmarksActivity;)Landroid/app/ProgressDialog;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/ProgressDialog;->show()V

    return-void
.end method

.method protected varargs synthetic doInBackground([Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    check-cast p1, [Ljava/lang/String;

    invoke-virtual {p0, p1}, Luk/co/dancingganesh/pocketvedas/d;->a([Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v0

    return-object v0
.end method

.method protected synthetic onPostExecute(Ljava/lang/Object;)V
    .locals 0

    check-cast p1, Landroid/database/Cursor;

    invoke-virtual {p0, p1}, Luk/co/dancingganesh/pocketvedas/d;->a(Landroid/database/Cursor;)V

    return-void
.end method

.method protected onPreExecute()V
    .locals 1

    invoke-super {p0}, Landroid/os/AsyncTask;->onPreExecute()V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/d;->a:Luk/co/dancingganesh/pocketvedas/BookmarksActivity;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/BookmarksActivity;->a(Luk/co/dancingganesh/pocketvedas/BookmarksActivity;)Landroid/app/ProgressDialog;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/ProgressDialog;->show()V

    return-void
.end method

.method protected varargs synthetic onProgressUpdate([Ljava/lang/Object;)V
    .locals 0

    check-cast p1, [Ljava/lang/Integer;

    invoke-virtual {p0, p1}, Luk/co/dancingganesh/pocketvedas/d;->a([Ljava/lang/Integer;)V

    return-void
.end method
