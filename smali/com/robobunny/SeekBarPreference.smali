.class public Lcom/robobunny/SeekBarPreference;
.super Landroid/preference/Preference;

# interfaces
.implements Landroid/widget/SeekBar$OnSeekBarChangeListener;


# instance fields
.field private final a:Ljava/lang/String;

.field private b:I

.field private c:I

.field private d:I

.field private e:I

.field private f:Ljava/lang/String;

.field private g:Ljava/lang/String;

.field private h:Landroid/widget/SeekBar;

.field private i:Landroid/widget/TextView;


# direct methods
.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    invoke-direct {p0, p1, p2}, Landroid/preference/Preference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;)V

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/robobunny/SeekBarPreference;->a:Ljava/lang/String;

    const/16 v0, 0x64

    iput v0, p0, Lcom/robobunny/SeekBarPreference;->b:I

    const/4 v0, 0x0

    iput v0, p0, Lcom/robobunny/SeekBarPreference;->c:I

    const/4 v0, 0x1

    iput v0, p0, Lcom/robobunny/SeekBarPreference;->d:I

    const-string v0, ""

    iput-object v0, p0, Lcom/robobunny/SeekBarPreference;->f:Ljava/lang/String;

    const-string v0, ""

    iput-object v0, p0, Lcom/robobunny/SeekBarPreference;->g:Ljava/lang/String;

    invoke-direct {p0, p1, p2}, Lcom/robobunny/SeekBarPreference;->a(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method public constructor <init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V
    .locals 1

    invoke-direct {p0, p1, p2, p3}, Landroid/preference/Preference;-><init>(Landroid/content/Context;Landroid/util/AttributeSet;I)V

    invoke-virtual {p0}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/Class;->getName()Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/robobunny/SeekBarPreference;->a:Ljava/lang/String;

    const/16 v0, 0x64

    iput v0, p0, Lcom/robobunny/SeekBarPreference;->b:I

    const/4 v0, 0x0

    iput v0, p0, Lcom/robobunny/SeekBarPreference;->c:I

    const/4 v0, 0x1

    iput v0, p0, Lcom/robobunny/SeekBarPreference;->d:I

    const-string v0, ""

    iput-object v0, p0, Lcom/robobunny/SeekBarPreference;->f:Ljava/lang/String;

    const-string v0, ""

    iput-object v0, p0, Lcom/robobunny/SeekBarPreference;->g:Ljava/lang/String;

    invoke-direct {p0, p1, p2}, Lcom/robobunny/SeekBarPreference;->a(Landroid/content/Context;Landroid/util/AttributeSet;)V

    return-void
.end method

.method private a(Landroid/util/AttributeSet;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
    .locals 1

    invoke-interface {p1, p2, p3}, Landroid/util/AttributeSet;->getAttributeValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-nez v0, :cond_0

    :goto_0
    return-object p4

    :cond_0
    move-object p4, v0

    goto :goto_0
.end method

.method private a(Landroid/content/Context;Landroid/util/AttributeSet;)V
    .locals 1

    invoke-direct {p0, p2}, Lcom/robobunny/SeekBarPreference;->a(Landroid/util/AttributeSet;)V

    const v0, 0x7f030008

    invoke-virtual {p0, v0}, Lcom/robobunny/SeekBarPreference;->setWidgetLayoutResource(I)V

    return-void
.end method

.method private a(Landroid/util/AttributeSet;)V
    .locals 3

    const-string v0, "http://schemas.android.com/apk/res/android"

    const-string v1, "max"

    const/16 v2, 0x64

    invoke-interface {p1, v0, v1, v2}, Landroid/util/AttributeSet;->getAttributeIntValue(Ljava/lang/String;Ljava/lang/String;I)I

    move-result v0

    iput v0, p0, Lcom/robobunny/SeekBarPreference;->b:I

    const-string v0, "http://robobunny.com"

    const-string v1, "min"

    const/4 v2, 0x0

    invoke-interface {p1, v0, v1, v2}, Landroid/util/AttributeSet;->getAttributeIntValue(Ljava/lang/String;Ljava/lang/String;I)I

    move-result v0

    iput v0, p0, Lcom/robobunny/SeekBarPreference;->c:I

    const-string v0, "http://robobunny.com"

    const-string v1, "unitsLeft"

    const-string v2, ""

    invoke-direct {p0, p1, v0, v1, v2}, Lcom/robobunny/SeekBarPreference;->a(Landroid/util/AttributeSet;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/robobunny/SeekBarPreference;->f:Ljava/lang/String;

    const-string v0, "http://robobunny.com"

    const-string v1, "units"

    const-string v2, ""

    invoke-direct {p0, p1, v0, v1, v2}, Lcom/robobunny/SeekBarPreference;->a(Landroid/util/AttributeSet;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    const-string v1, "http://robobunny.com"

    const-string v2, "unitsRight"

    invoke-direct {p0, p1, v1, v2, v0}, Lcom/robobunny/SeekBarPreference;->a(Landroid/util/AttributeSet;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    iput-object v0, p0, Lcom/robobunny/SeekBarPreference;->g:Ljava/lang/String;

    :try_start_0
    const-string v0, "http://robobunny.com"

    const-string v1, "interval"

    invoke-interface {p1, v0, v1}, Landroid/util/AttributeSet;->getAttributeValue(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;

    move-result-object v0

    if-eqz v0, :cond_0

    invoke-static {v0}, Ljava/lang/Integer;->parseInt(Ljava/lang/String;)I

    move-result v0

    iput v0, p0, Lcom/robobunny/SeekBarPreference;->d:I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :cond_0
    :goto_0
    return-void

    :catch_0
    move-exception v0

    iget-object v1, p0, Lcom/robobunny/SeekBarPreference;->a:Ljava/lang/String;

    const-string v2, "Invalid interval value"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0
.end method


# virtual methods
.method protected a(Landroid/view/View;)V
    .locals 3

    const v0, 0x7f09000b

    :try_start_0
    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iput-object v0, p0, Lcom/robobunny/SeekBarPreference;->i:Landroid/widget/TextView;

    iget-object v0, p0, Lcom/robobunny/SeekBarPreference;->i:Landroid/widget/TextView;

    iget v1, p0, Lcom/robobunny/SeekBarPreference;->e:I

    invoke-static {v1}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    iget-object v0, p0, Lcom/robobunny/SeekBarPreference;->i:Landroid/widget/TextView;

    const/16 v1, 0x1e

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setMinimumWidth(I)V

    iget-object v0, p0, Lcom/robobunny/SeekBarPreference;->h:Landroid/widget/SeekBar;

    iget v1, p0, Lcom/robobunny/SeekBarPreference;->e:I

    iget v2, p0, Lcom/robobunny/SeekBarPreference;->c:I

    sub-int/2addr v1, v2

    invoke-virtual {v0, v1}, Landroid/widget/SeekBar;->setProgress(I)V

    const v0, 0x7f09000a

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iget-object v1, p0, Lcom/robobunny/SeekBarPreference;->g:Ljava/lang/String;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    const v0, 0x7f09000c

    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/TextView;

    iget-object v1, p0, Lcom/robobunny/SeekBarPreference;->f:Ljava/lang/String;

    invoke-virtual {v0, v1}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :goto_0
    return-void

    :catch_0
    move-exception v0

    iget-object v1, p0, Lcom/robobunny/SeekBarPreference;->a:Ljava/lang/String;

    const-string v2, "Error updating seek bar preference"

    invoke-static {v1, v2, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I

    goto :goto_0
.end method

.method public onBindView(Landroid/view/View;)V
    .locals 4

    invoke-super {p0, p1}, Landroid/preference/Preference;->onBindView(Landroid/view/View;)V

    const v0, 0x7f09000e

    :try_start_0
    invoke-virtual {p1, v0}, Landroid/view/View;->findViewById(I)Landroid/view/View;

    move-result-object v0

    check-cast v0, Landroid/widget/SeekBar;

    iput-object v0, p0, Lcom/robobunny/SeekBarPreference;->h:Landroid/widget/SeekBar;

    iget-object v0, p0, Lcom/robobunny/SeekBarPreference;->h:Landroid/widget/SeekBar;

    iget v1, p0, Lcom/robobunny/SeekBarPreference;->b:I

    iget v2, p0, Lcom/robobunny/SeekBarPreference;->c:I

    sub-int/2addr v1, v2

    invoke-virtual {v0, v1}, Landroid/widget/SeekBar;->setMax(I)V

    iget-object v0, p0, Lcom/robobunny/SeekBarPreference;->h:Landroid/widget/SeekBar;

    invoke-virtual {v0, p0}, Landroid/widget/SeekBar;->setOnSeekBarChangeListener(Landroid/widget/SeekBar$OnSeekBarChangeListener;)V
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    :goto_0
    if-eqz p1, :cond_0

    invoke-virtual {p1}, Landroid/view/View;->isEnabled()Z

    move-result v0

    if-nez v0, :cond_0

    iget-object v0, p0, Lcom/robobunny/SeekBarPreference;->h:Landroid/widget/SeekBar;

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Landroid/widget/SeekBar;->setEnabled(Z)V

    :cond_0
    invoke-virtual {p0, p1}, Lcom/robobunny/SeekBarPreference;->a(Landroid/view/View;)V

    return-void

    :catch_0
    move-exception v0

    iget-object v1, p0, Lcom/robobunny/SeekBarPreference;->a:Ljava/lang/String;

    new-instance v2, Ljava/lang/StringBuilder;

    const-string v3, "Error binding view: "

    invoke-direct {v2, v3}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {v0}, Ljava/lang/Exception;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v0

    invoke-virtual {v0}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    invoke-static {v1, v0}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    goto :goto_0
.end method

.method protected onCreateView(Landroid/view/ViewGroup;)Landroid/view/View;
    .locals 3

    invoke-super {p0, p1}, Landroid/preference/Preference;->onCreateView(Landroid/view/ViewGroup;)Landroid/view/View;

    move-result-object v1

    move-object v0, v1

    check-cast v0, Landroid/widget/LinearLayout;

    const/4 v2, 0x1

    invoke-virtual {v0, v2}, Landroid/widget/LinearLayout;->setOrientation(I)V

    return-object v1
.end method

.method public onDependencyChanged(Landroid/preference/Preference;Z)V
    .locals 2

    invoke-super {p0, p1, p2}, Landroid/preference/Preference;->onDependencyChanged(Landroid/preference/Preference;Z)V

    iget-object v0, p0, Lcom/robobunny/SeekBarPreference;->h:Landroid/widget/SeekBar;

    if-eqz v0, :cond_0

    iget-object v1, p0, Lcom/robobunny/SeekBarPreference;->h:Landroid/widget/SeekBar;

    if-eqz p2, :cond_1

    const/4 v0, 0x0

    :goto_0
    invoke-virtual {v1, v0}, Landroid/widget/SeekBar;->setEnabled(Z)V

    :cond_0
    return-void

    :cond_1
    const/4 v0, 0x1

    goto :goto_0
.end method

.method protected onGetDefaultValue(Landroid/content/res/TypedArray;I)Ljava/lang/Object;
    .locals 1

    const/16 v0, 0x32

    invoke-virtual {p1, p2, v0}, Landroid/content/res/TypedArray;->getInt(II)I

    move-result v0

    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v0

    return-object v0
.end method

.method public onProgressChanged(Landroid/widget/SeekBar;IZ)V
    .locals 3

    iget v0, p0, Lcom/robobunny/SeekBarPreference;->c:I

    add-int/2addr v0, p2

    iget v1, p0, Lcom/robobunny/SeekBarPreference;->b:I

    if-le v0, v1, :cond_1

    iget v0, p0, Lcom/robobunny/SeekBarPreference;->b:I

    :cond_0
    :goto_0
    invoke-static {v0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    invoke-virtual {p0, v1}, Lcom/robobunny/SeekBarPreference;->callChangeListener(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_3

    iget v0, p0, Lcom/robobunny/SeekBarPreference;->e:I

    iget v1, p0, Lcom/robobunny/SeekBarPreference;->c:I

    sub-int/2addr v0, v1

    invoke-virtual {p1, v0}, Landroid/widget/SeekBar;->setProgress(I)V

    :goto_1
    return-void

    :cond_1
    iget v1, p0, Lcom/robobunny/SeekBarPreference;->c:I

    if-ge v0, v1, :cond_2

    iget v0, p0, Lcom/robobunny/SeekBarPreference;->c:I

    goto :goto_0

    :cond_2
    iget v1, p0, Lcom/robobunny/SeekBarPreference;->d:I

    const/4 v2, 0x1

    if-eq v1, v2, :cond_0

    iget v1, p0, Lcom/robobunny/SeekBarPreference;->d:I

    rem-int v1, v0, v1

    if-eqz v1, :cond_0

    int-to-float v0, v0

    iget v1, p0, Lcom/robobunny/SeekBarPreference;->d:I

    int-to-float v1, v1

    div-float/2addr v0, v1

    invoke-static {v0}, Ljava/lang/Math;->round(F)I

    move-result v0

    iget v1, p0, Lcom/robobunny/SeekBarPreference;->d:I

    mul-int/2addr v0, v1

    goto :goto_0

    :cond_3
    iput v0, p0, Lcom/robobunny/SeekBarPreference;->e:I

    iget-object v1, p0, Lcom/robobunny/SeekBarPreference;->i:Landroid/widget/TextView;

    invoke-static {v0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object v2

    invoke-virtual {v1, v2}, Landroid/widget/TextView;->setText(Ljava/lang/CharSequence;)V

    invoke-virtual {p0, v0}, Lcom/robobunny/SeekBarPreference;->persistInt(I)Z

    goto :goto_1
.end method

.method protected onSetInitialValue(ZLjava/lang/Object;)V
    .locals 5

    if-eqz p1, :cond_0

    iget v1, p0, Lcom/robobunny/SeekBarPreference;->e:I

    invoke-virtual {p0, v1}, Lcom/robobunny/SeekBarPreference;->getPersistedInt(I)I

    move-result v1

    iput v1, p0, Lcom/robobunny/SeekBarPreference;->e:I

    :goto_0
    return-void

    :cond_0
    const/4 v2, 0x0

    :try_start_0
    move-object v0, p2

    check-cast v0, Ljava/lang/Integer;

    move-object v1, v0

    invoke-virtual {v1}, Ljava/lang/Integer;->intValue()I
    :try_end_0
    .catch Ljava/lang/Exception; {:try_start_0 .. :try_end_0} :catch_0

    move-result v1

    :goto_1
    invoke-virtual {p0, v1}, Lcom/robobunny/SeekBarPreference;->persistInt(I)Z

    iput v1, p0, Lcom/robobunny/SeekBarPreference;->e:I

    goto :goto_0

    :catch_0
    move-exception v1

    iget-object v1, p0, Lcom/robobunny/SeekBarPreference;->a:Ljava/lang/String;

    new-instance v3, Ljava/lang/StringBuilder;

    const-string v4, "Invalid default value: "

    invoke-direct {v3, v4}, Ljava/lang/StringBuilder;-><init>(Ljava/lang/String;)V

    invoke-virtual {p2}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object v4

    invoke-virtual {v3, v4}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    move-result-object v3

    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v3

    invoke-static {v1, v3}, Landroid/util/Log;->e(Ljava/lang/String;Ljava/lang/String;)I

    move v1, v2

    goto :goto_1
.end method

.method public onStartTrackingTouch(Landroid/widget/SeekBar;)V
    .locals 0

    return-void
.end method

.method public onStopTrackingTouch(Landroid/widget/SeekBar;)V
    .locals 0

    invoke-virtual {p0}, Lcom/robobunny/SeekBarPreference;->notifyChanged()V

    return-void
.end method

.method public setEnabled(Z)V
    .locals 1

    invoke-super {p0, p1}, Landroid/preference/Preference;->setEnabled(Z)V

    iget-object v0, p0, Lcom/robobunny/SeekBarPreference;->h:Landroid/widget/SeekBar;

    invoke-virtual {v0, p1}, Landroid/widget/SeekBar;->setEnabled(Z)V

    return-void
.end method
