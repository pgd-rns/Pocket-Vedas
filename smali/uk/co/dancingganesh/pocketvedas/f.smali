.class Luk/co/dancingganesh/pocketvedas/f;
.super Landroid/os/AsyncTask;


# instance fields
.field final synthetic a:Luk/co/dancingganesh/pocketvedas/BooksActivity;

.field private final synthetic b:Luk/co/dancingganesh/pocketvedas/j;

.field private final synthetic c:Landroid/widget/GridView;


# direct methods
.method constructor <init>(Luk/co/dancingganesh/pocketvedas/BooksActivity;Luk/co/dancingganesh/pocketvedas/j;Landroid/widget/GridView;)V
    .locals 0

    iput-object p1, p0, Luk/co/dancingganesh/pocketvedas/f;->a:Luk/co/dancingganesh/pocketvedas/BooksActivity;

    iput-object p2, p0, Luk/co/dancingganesh/pocketvedas/f;->b:Luk/co/dancingganesh/pocketvedas/j;

    iput-object p3, p0, Luk/co/dancingganesh/pocketvedas/f;->c:Landroid/widget/GridView;

    invoke-direct {p0}, Landroid/os/AsyncTask;-><init>()V

    return-void
.end method


# virtual methods
.method protected varargs a([Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/f;->a:Luk/co/dancingganesh/pocketvedas/BooksActivity;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/a/c;->a(Landroid/content/Context;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/f;->a:Luk/co/dancingganesh/pocketvedas/BooksActivity;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/a/b;->a(Landroid/content/Context;)V

    const/4 v0, 0x0

    return-object v0
.end method

.method protected a(Ljava/lang/String;)V
    .locals 1

    invoke-super {p0, p1}, Landroid/os/AsyncTask;->onPostExecute(Ljava/lang/Object;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/f;->a:Luk/co/dancingganesh/pocketvedas/BooksActivity;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->a(Luk/co/dancingganesh/pocketvedas/BooksActivity;)Landroid/app/ProgressDialog;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/ProgressDialog;->dismiss()V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/f;->b:Luk/co/dancingganesh/pocketvedas/j;

    invoke-virtual {v0}, Luk/co/dancingganesh/pocketvedas/j;->notifyDataSetChanged()V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/f;->c:Landroid/widget/GridView;

    invoke-virtual {v0}, Landroid/widget/GridView;->postInvalidate()V

    return-void
.end method

.method protected varargs a([Ljava/lang/Integer;)V
    .locals 1

    invoke-super {p0, p1}, Landroid/os/AsyncTask;->onProgressUpdate([Ljava/lang/Object;)V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/f;->a:Luk/co/dancingganesh/pocketvedas/BooksActivity;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->a(Luk/co/dancingganesh/pocketvedas/BooksActivity;)Landroid/app/ProgressDialog;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/ProgressDialog;->show()V

    return-void
.end method

.method protected varargs synthetic doInBackground([Ljava/lang/Object;)Ljava/lang/Object;
    .locals 1

    check-cast p1, [Ljava/lang/String;

    invoke-virtual {p0, p1}, Luk/co/dancingganesh/pocketvedas/f;->a([Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    return-object v0
.end method

.method protected synthetic onPostExecute(Ljava/lang/Object;)V
    .locals 0

    check-cast p1, Ljava/lang/String;

    invoke-virtual {p0, p1}, Luk/co/dancingganesh/pocketvedas/f;->a(Ljava/lang/String;)V

    return-void
.end method

.method protected onPreExecute()V
    .locals 1

    invoke-super {p0}, Landroid/os/AsyncTask;->onPreExecute()V

    iget-object v0, p0, Luk/co/dancingganesh/pocketvedas/f;->a:Luk/co/dancingganesh/pocketvedas/BooksActivity;

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/BooksActivity;->a(Luk/co/dancingganesh/pocketvedas/BooksActivity;)Landroid/app/ProgressDialog;

    move-result-object v0

    invoke-virtual {v0}, Landroid/app/ProgressDialog;->show()V

    return-void
.end method

.method protected varargs synthetic onProgressUpdate([Ljava/lang/Object;)V
    .locals 0

    check-cast p1, [Ljava/lang/Integer;

    invoke-virtual {p0, p1}, Luk/co/dancingganesh/pocketvedas/f;->a([Ljava/lang/Integer;)V

    return-void
.end method
