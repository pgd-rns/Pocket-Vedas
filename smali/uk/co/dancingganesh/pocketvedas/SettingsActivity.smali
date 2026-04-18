.class public Luk/co/dancingganesh/pocketvedas/SettingsActivity;
.super Landroid/preference/PreferenceActivity;


# instance fields
.field private a:I

.field private b:Z

.field private c:Z

.field private d:Z

.field private e:Z

.field private f:Z

.field private g:Z


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Landroid/preference/PreferenceActivity;-><init>()V

    return-void
.end method


# virtual methods
.method public onCreate(Landroid/os/Bundle;)V
    .locals 4

    const/4 v3, 0x1

    invoke-super {p0, p1}, Landroid/preference/PreferenceActivity;->onCreate(Landroid/os/Bundle;)V

    sget v0, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v1, 0x13

    if-ge v0, v1, :cond_0

    const/high16 v0, 0x7f040000

    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->addPreferencesFromResource(I)V

    :goto_0
    invoke-static {p0}, Landroid/preference/PreferenceManager;->getDefaultSharedPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v0

    const-string v1, "pref_zoom"

    const/16 v2, 0x85

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v1

    iput v1, p0, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->a:I

    const-string v1, "pref_text"

    invoke-interface {v0, v1, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v1

    iput-boolean v1, p0, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->b:Z

    const-string v1, "pref_synonyms"

    invoke-interface {v0, v1, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v1

    iput-boolean v1, p0, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->c:Z

    const-string v1, "pref_translation"

    invoke-interface {v0, v1, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v1

    iput-boolean v1, p0, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->d:Z

    const-string v1, "pref_purport"

    invoke-interface {v0, v1, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v1

    iput-boolean v1, p0, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->e:Z

    const-string v1, "pref_reverse"

    invoke-interface {v0, v1, v3}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v1

    iput-boolean v1, p0, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->f:Z

    const-string v1, "pref_keep_awake"

    const/4 v2, 0x0

    invoke-interface {v0, v1, v2}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v0

    iput-boolean v0, p0, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->g:Z

    return-void

    :cond_0
    const v0, 0x7f040001

    invoke-virtual {p0, v0}, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->addPreferencesFromResource(I)V

    goto :goto_0
.end method

.method public onStop()V
    .locals 9

    const/4 v7, 0x1

    invoke-super {p0}, Landroid/preference/PreferenceActivity;->onStop()V

    invoke-static {p0}, Landroid/preference/PreferenceManager;->getDefaultSharedPreferences(Landroid/content/Context;)Landroid/content/SharedPreferences;

    move-result-object v1

    iget v0, p0, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->a:I

    sget v2, Landroid/os/Build$VERSION;->SDK_INT:I

    const/16 v3, 0x13

    if-lt v2, v3, :cond_0

    const-string v0, "pref_zoom"

    const/16 v2, 0x85

    invoke-interface {v1, v0, v2}, Landroid/content/SharedPreferences;->getInt(Ljava/lang/String;I)I

    move-result v0

    :cond_0
    const-string v2, "pref_text"

    invoke-interface {v1, v2, v7}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v2

    const-string v3, "pref_synonyms"

    invoke-interface {v1, v3, v7}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v3

    const-string v4, "pref_translation"

    invoke-interface {v1, v4, v7}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v4

    const-string v5, "pref_purport"

    invoke-interface {v1, v5, v7}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v5

    const-string v6, "pref_reverse"

    invoke-interface {v1, v6, v7}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v6

    const-string v7, "pref_keep_awake"

    const/4 v8, 0x0

    invoke-interface {v1, v7, v8}, Landroid/content/SharedPreferences;->getBoolean(Ljava/lang/String;Z)Z

    move-result v1

    iget v7, p0, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->a:I

    if-ne v7, v0, :cond_1

    iget-boolean v0, p0, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->b:Z

    if-ne v0, v2, :cond_1

    iget-boolean v0, p0, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->c:Z

    if-ne v0, v3, :cond_1

    iget-boolean v0, p0, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->d:Z

    if-ne v0, v4, :cond_1

    iget-boolean v0, p0, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->e:Z

    if-ne v0, v5, :cond_1

    iget-boolean v0, p0, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->f:Z

    if-ne v0, v6, :cond_1

    iget-boolean v0, p0, Luk/co/dancingganesh/pocketvedas/SettingsActivity;->g:Z

    if-eq v0, v1, :cond_2

    :cond_1
    const/4 v0, 0x0

    invoke-static {v0}, Luk/co/dancingganesh/pocketvedas/k;->a(Luk/co/dancingganesh/pocketvedas/k;)V

    :cond_2
    return-void
.end method
