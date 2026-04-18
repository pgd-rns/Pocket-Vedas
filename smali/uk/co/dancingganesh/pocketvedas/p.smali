.class Luk/co/dancingganesh/pocketvedas/p;
.super Landroid/os/AsyncTask;


# instance fields
.field final synthetic a:Luk/co/dancingganesh/pocketvedas/SearchActivity;


# direct methods
.method constructor <init>(Luk/co/dancingganesh/pocketvedas/SearchActivity;)V
    .locals 0

    iput-object p1, p0, Luk/co/dancingganesh/pocketvedas/p;->a:Luk/co/dancingganesh/pocketvedas/SearchActivity;

    invoke-direct {p0}, Landroid/os/AsyncTask;-><init>()V

    return-void
.end method


# virtual methods
.method protected varargs a([Ljava/lang/String;)Landroid/database/Cursor;
    .locals 2

    invoke-static {}, Luk/co/dancingganesh/pocketvedas/a/c;->a()Luk/co/dancingganesh/pocketvedas/a/c;

    move-result-object v0

    const/4 v1, 0x0

    aget-object v1, p1, v1

    invoke-virtual {v0, v1}, Luk/co/dancingganesh/pocketvedas/a/c;->a(Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v0

    return-object v0
.end method

.method protected a(Landroid/database/Cursor;)V
    .locals 4

    invoke-super {p0, p1}, Landroid/os/AsyncTask;->onPostExecute(Ljava/lang/Object;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/p;->a:Luk/co/dancingganesh/pocketvedas/SearchActivity;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/SearchActivity;->a(Luk/co/dancingganesh/pocketvedas/SearchActivity;)Landroid/app/ProgressDialog;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/ProgressDialog;->dismiss()V

    new-instance v0, Luk/co/dancingganesh/pocketvedas/q;

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/p;->a:Luk/co/dancingganesh/pocketvedas/SearchActivity;

    iget-object v2, p0, Luk/co/dancingganesh/pocketvedas/p;->a:Luk/co/dancingganesh/pocketvedas/SearchActivity;

    const/4 v3, 0x0

    invoke-direct {v0, v1, v2, p1, v3}, Luk/co/dancingganesh/pocketvedas/q;-><init>(Luk/co/dancingganesh/pocketvedas/SearchActivity;Landroid/content/Context;Landroid/database/Cursor;I)V

    iget-object v1, p0, Luk/co/dancingganesh/pocketvedas/p;->a:Luk/co/dancingganesh/pocketvedas/SearchActivity;

    invoke-virtual {v1, v0}, Luk/co/dancingganesh/pocketvedas/SearchActivity;->setListAdapter(Landroid/widget/ListAdapter;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/p;->a:Luk/co/dancingganesh/pocketvedas/SearchActivity;

    const v1, 0x102000a

    invoke-virtual {v0, v1}, Luk/co/dancingganesh/pocketvedas/SearchActivity;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/ListView;

    invoke-virtual {v0}, Landroid/widget/ListView;->postInvalidate()V

    return-void
.end method

.method protected varargs a([Ljava/lang/Integer;)V
    .locals 1

    invoke-super {p0, p1}, Landroid/os/AsyncTask;->onProgressUpdate([Ljava/lang/Object;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/p;->a:Luk/co/dancingganesh/pocketvedas/SearchActivity;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/SearchActivity;->a(Luk/co/dancingganesh/pocketvedas/SearchActivity;)Landroid/app/ProgressDialog;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/ProgressDialog;->show()V

    return-void
.end method

.method protected varargs synthetic doInBackground([Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    check-cast p1, [Ljava/lang/String;

    invoke-virtual {p0, p1}, Luk/co/dancingganesh/pocketvedas/p;->a([Ljava/lang/String;)Landroid/database/Cursor;

    move-result-object v0

    return-object v0
.end method

.method protected synthetic onPostExecute(Ljava/lang/Object;)V
    .locals 0

    check-cast p1, Landroid/database/Cursor;

    invoke-virtual {p0, p1}, Luk/co/dancingganesh/pocketvedas/p;->a(Landroid/database/Cursor;)V

    return-void
.end method

.method protected onPreExecute()V
    .locals 1

    invoke-super {p0}, Landroid/os/AsyncTask;->onPreExecute()V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/p;->a:Luk/co/dancingganesh/pocketvedas/SearchActivity;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/SearchActivity;->a(Luk/co/dancingganesh/pocketvedas/SearchActivity;)Landroid/app/ProgressDialog;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/ProgressDialog;->show()V

    return-void
.end method

.method protected varargs synthetic onProgressUpdate([Ljava/lang/Object;)V
    .locals 0

    check-cast p1, [Ljava/lang/Integer;

    invoke-virtual {p0, p1}, Luk/co/dancingganesh/pocketvedas/p;->a([Ljava/lang/Integer;)V

    return-void
.end method
